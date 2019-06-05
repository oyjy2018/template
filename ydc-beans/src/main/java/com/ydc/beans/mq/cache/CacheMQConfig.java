package com.ydc.beans.mq.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 *
 * @author
 * @create 2018-10-22 16:13
 **/
@Configuration
public class CacheMQConfig {

    private  String topic;
    private  String tag;
    @Resource
    Environment environment;

    //  发送MQ塞入缓存
    @Bean(name = "setCache",initMethod = "initMethodSetCache")
    public CacheMQConfig initMethodSetCache() {
        CacheMQConfig cacheMQConfig= new CacheMQConfig();
        cacheMQConfig.setTopic(environment.getProperty("rocketmq.set.cache.topic"));
        cacheMQConfig.setTag(environment.getProperty("rocketmq.set.cache.topic.tagA"));
        return cacheMQConfig;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
