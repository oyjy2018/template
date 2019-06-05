package com.ydc.message.mq.cache;

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
 * MQ 塞入缓存
 * @author
 * @create 2018-10-22 15:06
 **/
@Component
public class CacheConsumerClient {

    private static Logger logger = LogManager.getLogger(CacheConsumerClient.class);

    @Resource
    private RocketMQConfig rocketMQConfig;

    @Resource
    private CacheMessageListener cacheMessageListener;

    @Resource
    Environment environment;

    @PostConstruct
    public void consumer(){
        logger.info("初始化 消费者 MQ 塞入缓存---------------------------------");
        createConsumer();
    }

    private void createConsumer(){

        try {
            DefaultMQPushConsumer defaultMQPushConsumer=ConsumerFactory.createMQPushConsumer(rocketMQConfig,String.valueOf(400));
            defaultMQPushConsumer.subscribe(environment.getProperty("rocketmq.set.cache.topic"),"*");
            defaultMQPushConsumer.registerMessageListener(cacheMessageListener);
            defaultMQPushConsumer.start();

        } catch (Exception e) {
            logger.info("初始化 消费者 MQ 塞入缓存异常：",e);
        }
    }
}
