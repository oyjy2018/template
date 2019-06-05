package com.ydc.beans.shiro.web;

import com.ydc.beans.config.RedisPropertiesConfig;
import com.ydc.beans.config.ShiroPropertiesConfig;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author
 * @create 2018-12-05 19:17
 **/
@Component
@Order(10)
@DependsOn(value = "environmentFactory")
public class AuthApplicationRunner implements ApplicationRunner {
    private static Logger logger = LogManager.getLogger(AuthApplicationRunner.class);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("---------------------开始重启服务，清空用户登录信息--------------------");
        Object obj = RedisUtil.getRedisTemplate().opsForHash().keys(ShiroPropertiesConfig.SHIRO_TOKEN);
        logger.info("subject:{},obj:{}","清空用户登录信息",JsonUtil.gsonStr(obj));
        if (obj == null) return;
        RedisUtil.getRedisTemplate().delete(ShiroPropertiesConfig.SHIRO_TOKEN);
        RedisUtil.getRedisTemplate().delete(RedisUtil.getRedisTemplate().keys(ShiroPropertiesConfig.SHIRO_SESSION +"*"));
        logger.info("---------------------结束重启服务，清空用户登录信息--------------------");
    }
}
