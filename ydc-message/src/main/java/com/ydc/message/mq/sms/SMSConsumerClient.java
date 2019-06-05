package com.ydc.message.mq.sms;

import com.ydc.beans.rocketMQ.ConsumerFactory;
import com.ydc.beans.config.RocketMQConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * MQ 短信消费
 * @author gongjin
 * @create 2018-10-17 16:00
 **/
@Component
public class SMSConsumerClient {

    private static Logger logger = LogManager.getLogger(SMSConsumerClient.class);

    @Resource
    private RocketMQConfig rocketMQConfig;
    @Resource
    private SMSMessageListener sMSMessageListener;
    @Resource
    Environment environment;


    @PostConstruct
    public void consumer(){
        logger.info("初始化 消费者---------------------------------");
        createConsumer();
    }

    private void createConsumer(){

        try {
            DefaultMQPushConsumer defaultMQPushConsumer=ConsumerFactory.createMQPushConsumer(rocketMQConfig,String.valueOf(500));
            defaultMQPushConsumer.subscribe(environment.getProperty("rocketmq.send.sms.topic"),"*");
            defaultMQPushConsumer.registerMessageListener(sMSMessageListener);
            defaultMQPushConsumer.start();

        } catch (Exception e) {
            logger.info("初始化 消费者异常："+e.getMessage());
        }
    }

}
