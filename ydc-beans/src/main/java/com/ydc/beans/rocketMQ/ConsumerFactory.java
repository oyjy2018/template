package com.ydc.beans.rocketMQ;

import com.ydc.beans.config.RocketMQConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.Random;


public class ConsumerFactory {
    public static DefaultMQPushConsumer createMQPushConsumer(RocketMQConfig rocketMQConfig,String param){
        DefaultMQPushConsumer defaultMQPushConsumer =
                new DefaultMQPushConsumer(rocketMQConfig.getConsumerGroupName()+ param);
        defaultMQPushConsumer.setVipChannelEnabled(false);
        defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
        defaultMQPushConsumer.setNamesrvAddr(rocketMQConfig.getNameserAddr());
        defaultMQPushConsumer.setInstanceName(Long.toString(System.currentTimeMillis()));
        //设置消费顺序:第一次启动从队列初始位置消费，后续再启动接着上次消费的进度开始消费
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        return defaultMQPushConsumer;
    }
}
