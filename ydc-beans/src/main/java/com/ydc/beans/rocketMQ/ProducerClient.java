package com.ydc.beans.rocketMQ;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("producerClient")
public class ProducerClient {


    //messageDelayLevel=1s 10s 30s 1m 5m 9m 10m 20m 30m 1h 2h 15d 30d

    private static Logger logger = LogManager.getLogger(ProducerClient.class);

    @Resource
    private ProducerSingleBean producerSingleBean;


    public void sendMessage(Message message) throws Exception{
            logger.info(String.format("   11 message [%s] send success!", new String(message.getBody())));
            DefaultMQProducer producer=producerSingleBean.getProducer();
            //发送消息
            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    logger.info(String.format("message [%s] send success!", new String(message.getBody())));
                }
                public void onException(Throwable throwable) {
                    logger.info("发送失败: "+message +"异常为："+throwable.getMessage());
                }
            });
    }

}
