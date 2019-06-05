package com.ydc.service.order.mq;

import com.ydc.beans.rocketMQ.ConsumerFactory;
import com.ydc.beans.config.RocketMQConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class OrderConsumerClient {
    private static Logger logger = LogManager.getLogger(OrderConsumerClient.class);

    @Resource
    private RocketMQConfig internalRocketMQ;

    @Resource
    private OrderMQConfig orderPayMQConfig;

    @Resource
    private MyMessageListener myMessageListener;

    @PostConstruct
    public void consumer(){
        logger.info("初始化 消费者---------------------------------");
        createOrderConsumer();
    }


    private void createOrderConsumer(){

        try {
            DefaultMQPushConsumer defaultMQPushConsumer=ConsumerFactory.createMQPushConsumer(internalRocketMQ,String.valueOf(300));
            defaultMQPushConsumer.subscribe(orderPayMQConfig.getTopic(),"*");
            defaultMQPushConsumer.registerMessageListener(myMessageListener);
            defaultMQPushConsumer.start();

        } catch (Exception e) {
            logger.info("初始化 消费者异常："+e.getMessage());
        }
    }

}
