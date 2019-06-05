package com.ydc.beans.shiro.web;

import com.ydc.commom.result.ResultConstant;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @create 2018-11-16 10:44
 **/
public class WebShiroUserFormAuthenticationFilter extends FormAuthenticationFilter {


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("code", String.valueOf(ResultConstant.LOGIN_FAILURE_414));
        return false;
    }
}
