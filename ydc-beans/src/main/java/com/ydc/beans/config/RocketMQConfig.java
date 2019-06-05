package com.ydc.beans.config;

import com.ydc.beans.commom.EnvironmentFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Configuration
@DependsOn(value = "environmentFactory")
public class RocketMQConfig {
    private  String nameserAddr;
    private  String consumerGroupName;
    private  String consumerInstanceName;
    private  String produceGroupName;
    private  String produceInstanceName;

    @Bean(name = "internalRocketMQ",initMethod = "initMethodInternalRocketMQ")
    public RocketMQConfig initMethodInternalRocketMQ() {
        RocketMQConfig rocketMQConfig =new RocketMQConfig();
        rocketMQConfig.setNameserAddr(RocketMQPropertiesConfig.ROCKETMQ_NAMESER_ADDR);
        rocketMQConfig.setConsumerGroupName(RocketMQPropertiesConfig.ROCKETMQ_CONSUMER_GROUP);
        rocketMQConfig.setConsumerInstanceName(RocketMQPropertiesConfig.ROCKETMQ_CONSUMER_INSTANCE_NAME);
        rocketMQConfig.setProduceGroupName(RocketMQPropertiesConfig.ROCKETMQ_PRODUCE_GROUP);
        rocketMQConfig.setProduceInstanceName(RocketMQPropertiesConfig.ROCKETMQ_PRODUCE_INSTANCE_NAME);
        return rocketMQConfig;

    }

    @Bean(name = "bRocketMQ",initMethod = "initMethodBRocketMQ")
    public RocketMQConfig initMethodBRocketMQ() {
        RocketMQConfig rocketMQConfig =new RocketMQConfig();
        rocketMQConfig.setNameserAddr(RocketMQPropertiesConfig.ROCKETMQ_THIRD_PARTY_B_NAMESER_ADDR);
        rocketMQConfig.setConsumerGroupName(RocketMQPropertiesConfig.ROCKETMQ_CONSUMER_GROUP);
        rocketMQConfig.setConsumerInstanceName(RocketMQPropertiesConfig.ROCKETMQ_CONSUMER_INSTANCE_NAME);
      /*  rocketMQConfig.setProduceGroupName(RocketMQPropertiesConfig.ROCKETMQ_PRODUCE_GROUP);
        rocketMQConfig.setProduceInstanceName(RocketMQPropertiesConfig.ROCKETMQ_PRODUCE_INSTANCE_NAME);*/
        return rocketMQConfig;

    }

    public String getProduceInstanceName() {
        return produceInstanceName;
    }

    public void setProduceInstanceName(String produceInstanceName) {
        this.produceInstanceName = produceInstanceName;
    }

    public String getNameserAddr() {
        return nameserAddr;
    }

    public void setNameserAddr(String nameserAddr) {
        this.nameserAddr = nameserAddr;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public String getConsumerInstanceName() {
        return consumerInstanceName;
    }

    public void setConsumerInstanceName(String consumerInstanceName) {
        this.consumerInstanceName = consumerInstanceName;
    }

    public String getProduceGroupName() {
        return produceGroupName;
    }

    public void setProduceGroupName(String produceGroupName) {
        this.produceGroupName = produceGroupName;
    }

}
