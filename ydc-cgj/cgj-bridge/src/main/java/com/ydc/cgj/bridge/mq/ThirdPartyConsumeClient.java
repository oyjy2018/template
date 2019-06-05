package com.ydc.cgj.bridge.mq;

import com.ydc.beans.mq.thirdParty.ThirdPartySendMQConfig;
import com.ydc.beans.rocketMQ.ConsumerFactory;
import com.ydc.beans.config.RocketMQConfig;
import com.ydc.commom.constant.MQConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class ThirdPartyConsumeClient {
    private static Logger logger = LogManager.getLogger(ThirdPartyConsumeClient.class);

    @Resource
    private RocketMQConfig internalRocketMQ;

    @Resource
    private RocketMQConfig bRocketMQ;

    @Resource
    private ThirdPartySendMQConfig addAppointmentOrderMQConfig;

    @Resource
    private ThirdPartySendMQConfig bOrderStatus;


    @Resource
    private ThirdPartyMessageListener thirdPartyMessageListener;

    @Resource
    private MemberAppointmentMsgListener memberAppointmentMsgListener;


    @PostConstruct
    public void consumer() {
        logger.info("初始化 消费者---------------------------------");
        createThirdPartyConsumer();
        createMemberAppointmentConsumer();
    }

    // 创建 第三方消费服务
    private void createThirdPartyConsumer() {
        try {
            DefaultMQPushConsumer defaultMQPushConsumer =ConsumerFactory.createMQPushConsumer(internalRocketMQ,String.valueOf(100));
            defaultMQPushConsumer.subscribe(addAppointmentOrderMQConfig.getTopic(), "*");
            defaultMQPushConsumer.registerMessageListener(thirdPartyMessageListener);
            defaultMQPushConsumer.start();
            logger.info("第三方消费");
        } catch (Exception e) {
            logger.error("初始化 第三方消费者异常", e);
        }
    }


    //B端mq 消费
    private void createMemberAppointmentConsumer() {
        try {
            DefaultMQPushConsumer defaultMQPushConsumer=ConsumerFactory.createMQPushConsumer(bRocketMQ,String.valueOf(200));
            defaultMQPushConsumer.subscribe(bOrderStatus.getTopic(), "*");
            defaultMQPushConsumer.setInstanceName(Long.toString(System.currentTimeMillis()));
            //注册消息回调，如果需要顺序消费，需要注册MessageListenerOrderly的实现
            defaultMQPushConsumer.registerMessageListener(memberAppointmentMsgListener);
            defaultMQPushConsumer.start();
            logger.info("预约消费");
        } catch (Exception e) {
            logger.error("初始化 预约消费者异常 " ,e);
        }
    }

}
