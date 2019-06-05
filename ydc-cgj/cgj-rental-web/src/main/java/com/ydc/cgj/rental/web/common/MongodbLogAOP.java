package com.ydc.cgj.rental.web.common;


import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.commom.view.vo.cgj.MongoLogMessageVO;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class MongodbLogAOP {
    private Logger logger = LogManager.getLogger("mongoAppender");

    @Pointcut("execution(public * com.ydc.cgj.rental.web.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        MongoLogMessageVO mongoLogMessageVO = new MongoLogMessageVO();
        mongoLogMessageVO.setUrl(request.getRequestURL().toString());
        mongoLogMessageVO.setHttpMethod(request.getMethod());
        mongoLogMessageVO.setIp(SystemUtil.getIpAddress(request));
        User user = WebShiroTokenManager.getUser();
        if (user != null){
            Map userMap = new HashMap();
            userMap.put("userId",user.getId());
            userMap.put("userName",user.getUserName());
            mongoLogMessageVO.setSubject(userMap);
        }
        mongoLogMessageVO.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        mongoLogMessageVO.setArgs(Arrays.toString(joinPoint.getArgs()));
        logger.info("REQUEST : "+JsonUtil.jsonStr(mongoLogMessageVO));
    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

    @AfterThrowing(throwing = "ex", pointcut = "webLog()")
    public void throwss(JoinPoint jp, Exception ex){
        logger.info("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp){
//        logger.info("方法最后执行.....");
    }

}
