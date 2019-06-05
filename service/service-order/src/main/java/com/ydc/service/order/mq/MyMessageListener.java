package com.ydc.service.order.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.order.CancelOrderDTO;
import com.ydc.service.order.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Component("myMessageListener")
public class MyMessageListener implements MessageListenerConcurrently {

    private static Logger logger =LogManager.getLogger(MyMessageListener.class);


    @Resource
    private OrderMQConfig orderPayMQConfig;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMQConfig orderAutoConfirmReceipt;



    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (null==list ||  list.isEmpty()){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        for (MessageExt messageExt:list){
            //根据不同的topic 和 tag 调用不同的方法
           return consumerByTopic(messageExt);
        }
        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }
    private ConsumeConcurrentlyStatus consumerByTopic(MessageExt messageExt){
        String tags=messageExt.getTags();
        byte[] content=messageExt.getBody();
        if (tags.equals(orderPayMQConfig.getTag())){
           logger.info("自动关闭订单消费 {}",new String(content));
            if (overtimePay(content)){
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }else if(tags.equals(orderAutoConfirmReceipt.getTag())){
          logger.info("自动确认收货消费 {}",new String(content));
           if (autoConfirmReceipt(content)){
               return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
           }
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }else if (tags.equals("TagTest")){
            Long longTemp=Long.parseLong(new String(content)),
                    endLong=System.currentTimeMillis();
            long l=endLong-longTemp;
            logger.info("消费测试 tags{},content{}",tags,new String(content));
        }
        logger.info("无效 tags 无法消费  tags：{},content {}",tags,new String(content));
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private boolean autoConfirmReceipt(byte[] content){
        String contentString =new String(content);
       Integer result=orderService.updateAutoConfirmReceipt(Integer.valueOf(contentString));
        logger.info("subject:{},contentString:{},result:{}","自动确认收货-消费者",contentString,result);
       if (StringUtil.isNotEmpty(result) && result.intValue()>0){
           return true;
       }else {
           return  false;
       }
    }


    private boolean overtimePay(byte[] content){
        String contentString =new String(content);
        JSONArray jsonArray=JSON.parseArray(contentString);
        Iterator<Object> iterator= jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject jsonObject= (JSONObject) iterator.next();
            try {
                CancelOrderDTO cancelOrderDTO = new CancelOrderDTO(jsonObject.getInteger("orderId"),jsonObject.getInteger("memberId"),"超时未支付");
                orderService.cancelOrder(cancelOrderDTO);
                logger.info("subject:{},objString:{}","超时支付内容",jsonObject.toString());
            } catch (Exception e) {
                logger.info("处理超时支付异常"+e.getMessage());
                return false;
            }
        }
        return true;
    }
}
