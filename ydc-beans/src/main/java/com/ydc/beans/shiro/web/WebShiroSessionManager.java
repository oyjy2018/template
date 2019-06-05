package com.ydc.beans.shiro.web;

import com.ydc.commom.constant.ShiroConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Description:shiro框架 自定义session获取方式
 *
 * @author
 * @create 2018-11-16 9:50
 **/
public class WebShiroSessionManager extends DefaultWebSessionManager {
    private static final Logger logger = LogManager.getLogger();

    public WebShiroSessionManager(){
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
        String id = WebUtils.toHttp(request).getHeader(Constants.USER_TOKEN);
       logger.info("id："+id);
        if(StringUtils.isEmpty(id)){
            //如果没有携带id参数则按照父类的方式在cookie进行获取
           logger.info("super："+super.getSessionId(request, response));
            return super.getSessionId(request, response);
        }else if(ShiroConstant.NO_SESSIONID_URL.contains(WebUtils.getPathWithinApplication(WebUtils.toHttp(request)))){
            return null;
        }else{
            //如果请求头中有 Authorization 则其值为sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return id;
        }
    }
}
