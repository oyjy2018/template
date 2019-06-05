package com.ydc.beans.rocketMQ;

import com.ydc.beans.config.RocketMQConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 单例创建 生产者
 */
@Component
@Scope(scopeName="singleton")
public class ProducerSingleBean {

    private static Logger logger = LogManager.getLogger(ProducerSingleBean.class);
    @Resource
    private RocketMQConfig internalRocketMQ;
    private final static String lock="0";

    public ProducerSingleBean(){}

    @PostConstruct
    public void init(){
       // initProducer();
    }


    private static DefaultMQProducer producer;

    private void initProducer(){
        logger.info("生产者启动：");
        synchronized (lock){
            if (null == producer){
                producer=new DefaultMQProducer(internalRocketMQ.getProduceGroupName());
            }
        }
        producer.setNamesrvAddr(internalRocketMQ.getNameserAddr());
      //  producer.setInstanceName(rocketMQConfig.getProduceInstanceName());
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
        } catch (MQClientException e) {
            logger.info("生产者启动异常："+e.getMessage());
        }
    }


    public DefaultMQProducer getProducer() {
        if (null==producer){
            initProducer();
        }
        return producer;
    }




}
