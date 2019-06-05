package com.ydc.beans.mq.thirdParty;

import com.ydc.commom.constant.MQConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
public class ThirdPartySendMQConfig {

    private String topic;
    private String tag;
    @Resource
    private Environment environment;

    // 第三方生成预约订单
    @Bean(value = "addAppointmentOrderMQConfig", initMethod = "initMethodAddAppointmentOrder")
    public ThirdPartySendMQConfig initMethodAddAppointmentOrder() {
        ThirdPartySendMQConfig thirdPartyConfig = new ThirdPartySendMQConfig();
        thirdPartyConfig.setTopic(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_TOPIC));
        thirdPartyConfig.setTag(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_TOPIC_ADDAPPOINTMENTORDER));
        return thirdPartyConfig;
    }

    // 第三方派发优惠券
    @Bean(value = "sendMemberRollMQConfig", initMethod = "initMethodSendMemberRoll")
    public ThirdPartySendMQConfig initMethodSendMemberRoll() {
        ThirdPartySendMQConfig thirdPartyConfig = new ThirdPartySendMQConfig();
        thirdPartyConfig.setTopic(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_TOPIC));
        thirdPartyConfig.setTag(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_TOPIC_MEMBERROLLSEND));
        return thirdPartyConfig;
    }

    // 第三方券状态更新
    @Bean(value = "updateRollStatusMQConfig", initMethod = "initMethodUpdateRollStatus")
    public ThirdPartySendMQConfig initMethodUpdateRollStatus() {
        ThirdPartySendMQConfig thirdPartyConfig = new ThirdPartySendMQConfig();
        thirdPartyConfig.setTopic(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_TOPIC));
        thirdPartyConfig.setTag(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_TOPIC_ROLLSTATUSUPDATE));
        return thirdPartyConfig;
    }

    //更新预约订单状态
    @Bean(name = "appointmentBOrderStatus",initMethod = "initMethodUpdateAppointmentBOrderStatus")
    public ThirdPartySendMQConfig initMethodUpdateAppointmentBOrderStatus() {
        ThirdPartySendMQConfig orderMQConfig=new ThirdPartySendMQConfig();
        orderMQConfig.setTopic(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_TOPIC));
        orderMQConfig.setTag(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_TOPIC_UPDATE_APPOINTMENTORDER));
        return orderMQConfig;
    }


    //B端主动更新预约订单状态
    @Bean(name = "bOrderStatus",initMethod = "initMethodBOrderStatus")
    public ThirdPartySendMQConfig initMethodBOrderStatus() {
        ThirdPartySendMQConfig orderMQConfig=new ThirdPartySendMQConfig();
        orderMQConfig.setTopic(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_B_UPDATE_ORDER_TOPIC));
        orderMQConfig.setTag(environment.getProperty(MQConstant.ROCKETMQ_THIRD_PARTY_B_UPDATE_ORDER_TAG));
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
