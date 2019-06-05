package com.ydc.beans.mq.sms.service;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.mq.sms.SMSSendMQConfig;
import com.ydc.beans.rocketMQ.ProducerClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MQ消息提供存放接口
 *
 * @author gongjin
 * @create 2018-09-26 11:21
 **/
@Component("smsSendMessageService")
public class SMSSendMessageService {


    private static Logger logger =LogManager.getLogger(SMSSendMessageService.class);

    @Resource
    private ProducerClient producerClient;

    @Resource
    private SMSSendMQConfig sendSMS;


    /**
     * 发送短信
     * @param mobilePhone 会员id
     * @param smsContent 内容
     * @param validateCode 模板code
     */
    public void sendSMSMessage(String mobilePhone,String smsContent,String validateCode){
        logger.info("subject:{},mobilePhone:{};smsContent:{};validateCode:{}","提供消费-发送短信",mobilePhone,smsContent,validateCode);
        try {
            Message message = new Message();
            message.setTopic(sendSMS.getTopic());
            message.setTags(sendSMS.getTag());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("mobilePhone",mobilePhone);
            jsonObject.put("smsContent",smsContent);
            jsonObject.put("validateCode",validateCode);
            message.setBody(jsonObject.toString().getBytes());
            producerClient.sendMessage(message);
        }catch (Exception e){
            logger.error("subject:{},e:{}","提供消费-发送短信异常",e);
        }

    }
}
