package com.ydc.beans.mq.sms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * @author gongjin
 * @create 2018-10-17 16:28
 **/
@Configuration
public class SMSSendMQConfig {

    private  String topic;
    private  String tag;
    @Resource
    Environment environment;

    //  发送短信
    @Bean(name = "sendSMS",initMethod = "initMethodSendSMS")
    public SMSSendMQConfig initMethodSendSMS() {
        SMSSendMQConfig sMSSendMQConfig =new SMSSendMQConfig();
        sMSSendMQConfig.setTopic(environment.getProperty("rocketmq.send.sms.topic"));
        sMSSendMQConfig.setTag(environment.getProperty("rocketmq.send.sms.topic.tagA"));
        return sMSSendMQConfig;
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
