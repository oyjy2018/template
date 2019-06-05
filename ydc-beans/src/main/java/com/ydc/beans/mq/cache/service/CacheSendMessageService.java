package com.ydc.beans.mq.cache.service;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.mq.cache.CacheMQConfig;
import com.ydc.beans.rocketMQ.ProducerClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author
 * @create 2018-10-22 16:15
 **/
@Component("cacheSendMessageService")
public class CacheSendMessageService {

    private static Logger logger =LogManager.getLogger(CacheSendMessageService.class);

    @Resource
    private ProducerClient producerClient;

    @Resource
    private CacheMQConfig setCache;

    /**
     * 发送MQ塞入缓存
     * @param obj
     */
    public void setCacheMessage(Object obj,String key){
        logger.info("subject:{},obj:{},key:{}","发送MQ塞入缓存",obj,key);
        try{
            Message message = new Message();
            message.setTopic(setCache.getTopic());
            message.setTags(setCache.getTag());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("param",obj);
            jsonObject.put("key",key);
            message.setBody(jsonObject.toString().getBytes());
            producerClient.sendMessage(message);
        }catch (Exception e){
            logger.error("subject:{},e:{}","发送MQ塞入缓存异常",e);
        }

    }

}
