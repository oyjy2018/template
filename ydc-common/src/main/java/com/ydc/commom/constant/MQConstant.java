package com.ydc.commom.constant;

public class MQConstant {

    //第三方交互mq的topic
    public static final String ROCKETMQ_THIRD_PARTY_TOPIC = "rocketmq.third.party.topic";

    //第三方创建订单tag
    public static final String ROCKETMQ_THIRD_PARTY_TOPIC_ADDAPPOINTMENTORDER = "rocketmq.third.party.topic.appointmentOrderAdd";

    //第三方派发优惠券
    public static final String ROCKETMQ_THIRD_PARTY_TOPIC_MEMBERROLLSEND="rocketmq.third.party.topic.memberRollSend";

    //第三方券状态更新
    public static final String ROCKETMQ_THIRD_PARTY_TOPIC_ROLLSTATUSUPDATE="rocketmq.third.party.topic.rollStatusUpdate";


    //第三方创建订单tag
    public static final String ROCKETMQ_THIRD_PARTY_TOPIC_UPDATE_APPOINTMENTORDER = "rocketmq.third.party.topic.appointmentOrderUpdate";


    //B端 namesrv.addr
    public static final String ROCKETMQ_THIRD_PARTY_B_NAMESER_ADDR = "rocketmq.third.party.b.nameser.addr";

    //B端topic
    public static final String ROCKETMQ_THIRD_PARTY_B_UPDATE_ORDER_TOPIC= "rocketmq.third.party.b.update.order.topic";
    //B端tag
    public static final String ROCKETMQ_THIRD_PARTY_B_UPDATE_ORDER_TAG = "rocketmq.third.party.b.update.order.tag";
}
