package com.ydc.service.order.mq.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.mq.thirdParty.ThirdPartySendMQConfig;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.rocketMQ.ProducerClient;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.service.order.mq.OrderMQConfig;
import com.ydc.service.order.service.DictionaryDetailService;
import com.ydc.service.order.service.SysErrorLogRocketmqService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * MQ消息提供存放接口
 *
 * @author gongjin
 * @create 2018-09-26 11:21
 **/
@Component
public class SendMessageService {


    private static Logger logger =LogManager.getLogger(SendMessageService.class);

    @Resource
    private ProducerClient producerClient;

    @Autowired
    private OrderMQConfig orderAutoConfirmReceipt;

    @Resource
    private OrderMQConfig orderPayMQConfig;

    @Resource
    private ThirdPartySendMQConfig appointmentBOrderStatus;

    @Autowired
    DictionaryDetailService dictionaryDetailService;

    @Autowired
    SysErrorLogRocketmqService sysErrorLogRocketmqService;

    /**
     * 自动确认收货
     * @param orderId
     */
    public void sendOrderAutoConfirmReceiptMessage(Integer orderId){
        Message message=new Message();
        try {
            message.setTopic(orderAutoConfirmReceipt.getTopic());
            message.setTags(orderAutoConfirmReceipt.getTag());
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDQRSH, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDQRSH, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)));
            message.setDelayTimeLevel(Integer.parseInt(dic.get().getDictValue()));
            message.setBody(String.valueOf(orderId).getBytes());
            producerClient.sendMessage(message);
            logger.info("MQ 提供消息 - 自动确认收货["+orderId+"],DelayTimeLevel["+message.getDelayTimeLevel()+"]");
        }catch (Exception e){
            logger.error("subject:{},e:{}","自动确认收货异常",e);
            sysErrorLogRocketmqService.consumeFailure(new String(message.getBody()),message.getTopic(),message.getTags(),message.getDelayTimeLevel());
        }
    }

    /**
     * 自动关闭订单
     * @param orderIds
     * @param memberId
     */
    public void sendPayMessage(List<Integer> orderIds, Integer memberId){
        Message message=new Message();
        try{
            message.setTopic(orderPayMQConfig.getTopic());
            message.setTags(orderPayMQConfig.getTag());
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDGBDD, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDGBDD, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)));
            message.setDelayTimeLevel(Integer.parseInt(dic.get().getDictValue()));
            long string=System.currentTimeMillis();
           logger.info(string);
            JSONArray jsonArray=new JSONArray();
            for (Integer id:orderIds){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("memberId",memberId);
                jsonObject.put("orderId",id);
                jsonArray.add(jsonObject);
            }
            message.setBody(jsonArray.toString().getBytes());
            producerClient.sendMessage(message);
            logger.info("MQ 提供消息 - 自动关闭订单["+jsonArray.toString()+"],DelayTimeLevel["+message.getDelayTimeLevel()+"]");
        }catch (Exception e){
            logger.error("subject:{},e:{}","自动关闭订单异常",e.getMessage());
            sysErrorLogRocketmqService.consumeFailure(new String(message.getBody()),message.getTopic(),message.getTags(),message.getDelayTimeLevel());
        }
    }

    //发送  取消预约
    public void sendCancelMemberAppointmentMessage(String jsonStr){
        Message message=new Message();
        try{
            message.setTopic(appointmentBOrderStatus.getTopic());
            message.setTags(appointmentBOrderStatus.getTag());
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDGBDD, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDGBDD, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)));
           // message.setDelayTimeLevel(Integer.parseInt(dic.get().getDictValue()));
            message.setBody(jsonStr.getBytes());
            producerClient.sendMessage(message);
            logger.info("MQ 提供消息--用户取消预约 {} ",jsonStr);
        }catch (Exception e){
            logger.error("subject:{},e:{}","取消预约异常",e);
            sysErrorLogRocketmqService.consumeFailure(new String(message.getBody()),message.getTopic(),message.getTags(),message.getDelayTimeLevel());
        }
    }


}
