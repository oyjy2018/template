package com.ydc.beans.shiro.app;

import com.ydc.commom.constant.ShiroConstant;
import com.ydc.commom.util.StringUtil;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager {

    public MySessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String requestURI = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
        //登录、注册一些uri需要重新生成sessionId
        if (ShiroConstant.NO_SESSIONID_URL.contains(requestURI)) {
            return null;
        }
        String id = WebUtils.toHttp(request).getHeader(ShiroConstant.AUTHORIZATION);
        if (StringUtil.isNotEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
        return super.getSessionId(request, response);
    }
}
