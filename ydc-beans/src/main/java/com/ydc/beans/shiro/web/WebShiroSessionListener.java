package com.ydc.beans.shiro.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * session监听器
 *
 * @author gongjin
 * @create 2018-09-06 9:23
 **/
@Component
public class WebShiroSessionListener implements SessionListener {
    private static final Logger logger = LogManager.getLogger();
    private final AtomicInteger sessionCount = new AtomicInteger(0);


    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
       logger.info("登录+1==" + sessionCount.get());
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
       logger.info("登录退出-1==" + sessionCount.get());
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
       logger.info("登录过期-1==" + sessionCount.get());
    }

    public int getSessionCount() {
        return sessionCount.get();
    }
}
