package com.ydc.beans.mq.common;

import com.ydc.beans.rocketMQ.ProducerClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 失败MQ重发
 * @author
 * @create 2018-11-12 19:13
 **/
@Service(value = "repetitionMQService")
public class RepetitionMQService {

    private static Logger logger =LogManager.getLogger(RepetitionMQService.class);

    @Resource
    private ProducerClient producerClient;

    /**
     * 异常重新消费
     * @param id
     * @param topic
     * @param tag
     * @param delayTimeLevel
     * @param content
     */
    public void repetitionManage(Integer id,String topic,String tag,Integer delayTimeLevel,String content) throws Exception{
        logger.info("subject:{},id:{};topic:{};tag:{};delayTimeLevel:{};content:{}","MQ重新消费",id,topic,tag,delayTimeLevel,content);
        Message message = new Message();
        message.setTopic(topic);
        message.setTags(tag);
        message.setDelayTimeLevel(delayTimeLevel);
        message.setBody(content.getBytes());
        producerClient.sendMessage(message);
    }
}
