package com.ydc.beans.filter;

import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(filterName = "securityFilter")
public class SecurityFilter implements Filter {
    private final Logger logger = LogManager.getLogger(SecurityFilter.class);

    private String[] prefixIignores ;
    private String ignoresParam;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ignoresParam = filterConfig.getInitParameter("exclusions");
        if (StringUtils.isNotEmpty(ignoresParam)) {
            prefixIignores = ignoresParam.split(",");
        }
        logger.info("subject:{},prefixIignores:{}","获取需要拦截url",JsonUtil.gsonStr(prefixIignores));
        return;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (logger.isDebugEnabled()) {
            logger.debug("这里是需要处理的url进入的方法");
        }
        if (this.canIgnore(httpServletRequest)) {
            logger.info("subject:{},url:{}","这里是不需要拦截的请求",((HttpServletRequest) request).getServletPath());
            chain.doFilter(request, response);
            return;
        }
        try {
            YDCHttpServletRequestWrapper reqw = new YDCHttpServletRequestWrapper(httpServletRequest);
            chain.doFilter(reqw, response);
        }catch (ServiceRuntimeException e){
            request.setAttribute("ServiceRuntimeException", e);
            request.getRequestDispatcher("/login/error").forward(request, response);
        }

    }


    @Override
    public void destroy() {
        prefixIignores=null;
    }

    private boolean canIgnore(HttpServletRequest request) {
//        boolean isExcludedPage = false;
//        String path = request.getServletPath();

        return ExclusionsEnum.verifyParam(request.getServletPath());
//        if(ExclusionsEnum.verifyParam(path)){
//            isExcludedPage = true;
//        }
//        logger.info("subject:{},path:{},isExcludedPage:{}","校验是否需要拦截",path,isExcludedPage);
//        return isExcludedPage;
    }
}
