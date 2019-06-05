package com.ydc.message.mq.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 缓存
 * @author
 * @create 2018-10-22 14:56
 **/
@Component("cacheMessageListener")
public class CacheMessageListener implements MessageListenerConcurrently {

    private static Logger logger =LogManager.getLogger(CacheMessageListener.class);

    @Resource
    Environment environment;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> extList, ConsumeConcurrentlyContext context) {
        if (null==extList || extList.isEmpty()){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        logger.info("subject:{},extList:{}","塞入缓存消费",extList.size());
        MessageExt messageExt = extList.get(0);
        return consumerByTopic(messageExt);
    }
    private ConsumeConcurrentlyStatus consumerByTopic(MessageExt messageExt){
        String tags=messageExt.getTags();
        byte[] content=messageExt.getBody();
        if(tags.equals(environment.getProperty("rocketmq.set.cache.topic.tagA"))){
            logger.info("塞入缓存-消费者  开始消费message ["+new String(messageExt.getBody())+"],tagName["+messageExt.getTags()+"]-------");
            if(setCacheMessage(content)){
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        logger.info("无效 tags ，无法消费  tags："+tags+" content"+new String(content));
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private boolean setCacheMessage(byte[] content){
        JSONObject jsonObject = null;
        try{
            String contentString = new String(content);
            jsonObject = JSON.parseObject(contentString);
            String key = jsonObject.getString("key");
            if(StringUtil.isNotEmpty(key)){
                boolean result = RedisUtil.exists(key);
                logger.info("subject:{},result:{},contentString:{}","塞入缓存",result,contentString);
                if(!result){
                    List<DictionaryDetail> dictionaryDetails = JsonUtil.jsonToList(jsonObject.get("param").toString(),DictionaryDetail.class);
                    RedisUtil.redisSet(key,dictionaryDetails,null);
                }
            }
        }catch (Exception e){
            logger.error("塞入缓存异常"+jsonObject,e);
            return false;
        }
        return true;
    }
}
