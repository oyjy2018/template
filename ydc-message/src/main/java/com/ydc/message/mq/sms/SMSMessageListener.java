package com.ydc.message.mq.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.util.AliyunSmsUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.message.service.DictionaryDetailService;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gongjin
 * @create 2018-10-17 16:02
 **/
@Component("sMSMessageListener")
public class SMSMessageListener implements MessageListenerConcurrently {

    private static Logger logger =LogManager.getLogger(SMSMessageListener.class);

    @Autowired
    DictionaryDetailService dictionaryDetailService;
    @Resource
    Environment environment;


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (null==list ||  list.isEmpty()){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        logger.info("subject:{},size:{}","发送短信消费",list.size());
        for (MessageExt messageExt:list){
            //根据不同的topic 和 tag 调用不同的方法
            return consumerByTopic(messageExt);
        }
        return null;
    }

    private ConsumeConcurrentlyStatus consumerByTopic(MessageExt messageExt){
        String tags=messageExt.getTags();
        byte[] content=messageExt.getBody();
        if(tags.equals(environment.getProperty("rocketmq.send.sms.topic.tagA"))){
            logger.info("发送短信-消费者  开始消费message ["+new String(messageExt.getBody())+"],tagName["+messageExt.getTags()+"]-------");
            if(sendSMSMessage(content)){
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        logger.info("无效 tags ，无法消费  tags："+tags+" content"+new String(content));
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private boolean sendSMSMessage(byte[] content){
        String contentString =new String(content);
        JSONObject jsonObject = JSON.parseObject(contentString);
        try {
            DictionaryDetail alydx = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX));
            // 发送阿里云短信
            logger.info("subject:{},contentString:{},alydx:{}","发送短信",contentString,JsonUtil.gsonStr(alydx));
            AliyunSmsUtil.sendValidateCode(jsonObject.getString("mobilePhone"), jsonObject.getString("smsContent"), jsonObject.getString("validateCode"), alydx.getDictValue(), alydx.getRemark1(), alydx.getRemark2());
        }catch (Exception e){
            logger.error("发送短信异常,参数["+contentString+"]",e);
            return false;
        }
        return true;
    }
}
