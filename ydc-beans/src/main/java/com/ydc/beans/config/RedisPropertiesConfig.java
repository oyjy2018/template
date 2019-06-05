package com.ydc.beans.config;

import com.ydc.beans.commom.EnvironmentFactory;

/**
 * redis配置
 *
 * @author
 * @create 2018-12-05 13:56
 **/
public class RedisPropertiesConfig {


    // Redis服务器地址
    public static String REDIS_HOST = EnvironmentFactory.getProperty("redis.host");

    //Redis服务器连接端口
    public static String REDIS_PORT = EnvironmentFactory.getProperty("redis.port");

    //Redis服务器连接密码（默认为空）
    public static String REDIS_PASSWORD = EnvironmentFactory.getProperty("redis.password");

    //连接超时时间（毫秒）
    public static String REDIS_TIMEOUT = EnvironmentFactory.getProperty("redis.timeout");

    //保存数据源
    public static String REDIS_DATABASE = EnvironmentFactory.getProperty("redis.database");


//    // mongodb服务器地址
//    public static String MONGODB_HOST = EnvironmentFactory.getProperty("mongodb.host");
//
//    //mongodb服务器连接端口
//    public static String MONGODB_PORT = EnvironmentFactory.getProperty("mongodb.port");
//
//    //mongodb服务器连接密码（默认为空）
//    public static String MONGODB_PASSWORD = EnvironmentFactory.getProperty("mongodb.password");
//
//    //mongodb 服务器用户名
//    public static String MONGODB_USERNAME = EnvironmentFactory.getProperty("mongodb.username");
//
//    //连接超时时间（毫秒）
//    public static String MONGODB_TIMEOUT = EnvironmentFactory.getProperty("mongodb.timeout");
//
//    //保存数据源
//    public static String MONGODB_DATABASE = EnvironmentFactory.getProperty("mongodb.database");

}
