package com.ydc.beans.shiro.app;

import com.ydc.commom.result.Result;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(UserFormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        PrintWriter out;
        try {
            out = httpServletResponse.getWriter();
            out.println(Result.notLogin().toJSON());
            out.flush();
            out.close();
        } catch (IOException e1) {
            log.info(e1.getMessage());
        }
        return false;
    }


}
