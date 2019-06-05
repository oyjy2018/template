package com.ydc.beans.config;

import com.ydc.beans.commom.EnvironmentFactory;

/**
 * 读取rocketmq配置文件
 */
public class RocketMQPropertiesConfig {


    public static String ROCKETMQ_NAMESER_ADDR = EnvironmentFactory.getProperty("rocketmq.nameser.addr");
    public static String ROCKETMQ_CONSUMER_GROUP = EnvironmentFactory.getProperty("rocketmq.consumer.group");
    public static String ROCKETMQ_CONSUMER_INSTANCE_NAME = EnvironmentFactory.getProperty("rocketmq.consumer.instance.name");
    public static String ROCKETMQ_PRODUCE_GROUP = EnvironmentFactory.getProperty("rocketmq.produce.group");
    public static String ROCKETMQ_PRODUCE_INSTANCE_NAME = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");

    public static String ROCKETMQ_ORDER_PAY_TOPIC = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");

    public static String ROCKETMQ_ORDER_PAY_TOPIC_TAGA = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    public static String ROCKETMQ_ORDER_PAY_TOPIC_TAGB = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    //发送短信
    public static String ROCKETMQ_SEND_SMS_TOPIC = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    public static String ROCKETMQ_SEND_SMS_TOPIC_TAGA = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    //塞入缓存
    public static String ROCKETMQ_SET_CACHE_TOPIC = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    public static String ROCKETMQ_SET_CACHE_TOPIC_TAGA = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");

    //第三方交互的mq
    public static String ROCKETMQ_THIRD_PARTY_TOPIC = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    // 第三方创建订单
    public static String ROCKETMQ_THIRD_PARTY_TOPIC_APPOINTMENTORDERADD = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    // 第三方派发优惠券
    public static String ROCKETMQ_THIRD_PARTY_TOPIC_MEMBERROLLSEND = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    //第三方券状态更新
    public static String ROCKETMQ_THIRD_PARTY_TOPIC_ROLLSTATUSUPDATE = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    // 第三方更新订单
    public static String ROCKETMQ_THIRD_PARTY_TOPIC_APPOINTMENTORDERUPDATE = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");

   /* public static String ROCKETMQ_THIRD_PARTY_NAMESER_ADDR = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    public static String ROCKETMQ_THIRD_PARTY_B_UPDATE_ORDER_TOPIC = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");
    public static String ROCKETMQ_THIRD_PARTY_B_UPDATE_ORDER_TAG = EnvironmentFactory.getProperty("rocketmq.produce.instance.name");*/


    //B端 namesrv.addr
    public static final String ROCKETMQ_THIRD_PARTY_B_NAMESER_ADDR = EnvironmentFactory.getProperty("rocketmq.third.party.b.nameser.addr");

    //B端topic
    public static final String ROCKETMQ_THIRD_PARTY_B_UPDATE_ORDER_TOPIC= EnvironmentFactory.getProperty("rocketmq.third.party.b.update.order.topic");
    //B端tag
    public static final String ROCKETMQ_THIRD_PARTY_B_UPDATE_ORDER_TAG =EnvironmentFactory.getProperty( "rocketmq.third.party.b.update.order.tag");


}
