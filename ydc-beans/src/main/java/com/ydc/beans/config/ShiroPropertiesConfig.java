package com.ydc.beans.config;

import com.ydc.beans.commom.EnvironmentFactory;

/**
 * shiro配置
 */
public class ShiroPropertiesConfig {

    /**
     * sessionId
     */
    public static String SHIRO_SESSION = EnvironmentFactory.getProperty("shiro.seession")+":";

    /**
     * 失效时间
     */
    public static String SHIRO_EXPIRES = EnvironmentFactory.getProperty("shiro.expires");

    public static String SHIRO_TOKEN = EnvironmentFactory.getProperty("shiro.token");

    /**
     * shiro user
     */
    public static String SHIRO_USER = EnvironmentFactory.getProperty("shiro.user")+":";
}
