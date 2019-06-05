package com.ydc.beans.commom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class EnvironmentFactory {

    private final static Logger logger = LogManager.getLogger(EnvironmentFactory.class);
    @Autowired
    Environment environment;

    private static Environment YDC_ENVIRONMENT;

    @PostConstruct
    public void init() {
        YDC_ENVIRONMENT = environment;
    }

    public static String getProperty(String name) {
        try {
            return YDC_ENVIRONMENT.getProperty(name);
        } catch (Exception e) {
            logger.error("获取配置文件{}异常", name, e);
            return null;
        }
    }

    public static String[] getActiveProfiles() {
        return YDC_ENVIRONMENT.getActiveProfiles();
    }

    public static String[] getDefaultProfiles() {
        return YDC_ENVIRONMENT.getDefaultProfiles();
    }


}
