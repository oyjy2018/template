package com.ydc.cgj.bridge.mq;

import com.ydc.cgj.bridge.mq.service.ThirdPartyMessageConsumeService;
import com.ydc.commom.util.ServiceFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 与第三方交互的消息监听器
 */
@Component(value = "thirdPartyMessageListener")
public class ThirdPartyMessageListener implements MessageListenerConcurrently {

    private static Logger logger = LogManager.getLogger(ThirdPartyMessageListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (null==list ||  list.isEmpty()){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        String tags=messageExt.getTags();
        if (tags == null || ("").equals(tags)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        byte[] content=messageExt.getBody();
        logger.info("开始消费message ["+new String(content)+"],tagName["+tags+"]-------");
        ThirdPartyMessageConsumeService thirdPartyMessageConsumeService = ServiceFactoryUtil.getBean(
                tags.substring(tags.lastIndexOf("_") + 1));
        return thirdPartyMessageConsumeService.consumeMessage(content, false) ?
                ConsumeConcurrentlyStatus.CONSUME_SUCCESS : ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }
}
