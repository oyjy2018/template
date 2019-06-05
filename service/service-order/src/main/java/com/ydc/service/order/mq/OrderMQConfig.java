package com.ydc.service.order.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
public class OrderMQConfig {

    private  String topic;
    private  String tag;
    @Resource
    Environment environment;

    //  订单支付超时
    @Bean(name = "orderPayMQConfig",initMethod = "initMethodOrderPay")
    public OrderMQConfig initMethodOrderPay() {
        OrderMQConfig orderMQConfig=new OrderMQConfig();
        orderMQConfig.setTopic(environment.getProperty("rocketmq.order.pay.topic"));
        orderMQConfig.setTag(environment.getProperty("rocketmq.order.pay.topic.tagA"));
        return orderMQConfig;
    }

    //  订单自动确认收货
    @Bean(name = "orderAutoConfirmReceipt",initMethod = "initMethodAutoConfirmReceipt")
    public OrderMQConfig initMethodAutoConfirmReceipt() {
        OrderMQConfig orderMQConfig=new OrderMQConfig();
        orderMQConfig.setTopic(environment.getProperty("rocketmq.order.pay.topic"));
        orderMQConfig.setTag(environment.getProperty("rocketmq.order.pay.topic.tagB"));
        return orderMQConfig;
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
