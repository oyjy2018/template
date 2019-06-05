package com.ydc.beans.shiro;

import com.ydc.beans.config.RedisPropertiesConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * redis管理
 *
 * @author
 * @create 2018-12-05 13:55
 **/
@Component
@Scope(scopeName="singleton")
@DependsOn(value = "environmentFactory")
public class RedisManagerBean {

    private static Logger logger = LogManager.getLogger(RedisManagerBean.class);

    private static RedisManager redisManager;
    private final static String lock="0";

    private void initProducer(){
        logger.info("redis配置：");
        synchronized (lock){
            if (null == redisManager){
                redisManager = new RedisManager();
                redisManager.setHost(RedisPropertiesConfig.REDIS_HOST);
                redisManager.setPort(Integer.valueOf(RedisPropertiesConfig.REDIS_PORT));
                redisManager.setTimeout(Integer.valueOf(RedisPropertiesConfig.REDIS_TIMEOUT));
                redisManager.setPassword(RedisPropertiesConfig.REDIS_PASSWORD);
                redisManager.setDatabase(Integer.valueOf(RedisPropertiesConfig.REDIS_DATABASE));
            }
        }
    }

    public RedisManager getRedisManager() {
        if (null==redisManager){
            initProducer();
        }
        return redisManager;
    }
}
