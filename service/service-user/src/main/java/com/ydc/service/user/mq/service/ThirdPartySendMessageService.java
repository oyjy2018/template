package com.ydc.service.user.mq.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.mq.sms.service.SMSSendMessageService;
import com.ydc.beans.mq.thirdParty.ThirdPartySendMQConfig;
import com.ydc.beans.rocketMQ.ProducerClient;
import com.ydc.commom.util.JsonUtil;
import com.ydc.service.user.service.SysErrorLogRocketmqService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ThirdPartySendMessageService {

    private static Logger logger = LogManager.getLogger(SMSSendMessageService.class);

    @Resource
    private ProducerClient producerClient;
    @Resource
    private ThirdPartySendMQConfig sendMemberRollMQConfig;

    @Resource
    private ThirdPartySendMQConfig updateRollStatusMQConfig;

    @Autowired
    SysErrorLogRocketmqService sysErrorLogRocketmqService;


    /**
     * 通知第三方派券
     *
     * @param list 派券信息
     */
    public void sendSendingMemberRollMessage(List<Map<String, Object>> list, List<Integer> loanIds, List<String> orderNos) {
        Message message = new Message();
        try {
            message.setTopic(sendMemberRollMQConfig.getTopic());
            message.setTags(sendMemberRollMQConfig.getTag());
            Map<String, Object> map = new HashMap<>(2);
            map.put("list", list);
            map.put("loanIds", loanIds);
            map.put("orderNos", orderNos);
            message.setBody(JsonUtil.gsonStr(map).getBytes());
            producerClient.sendMessage(message);
            logger.info("MQ 提供消息 - 通知第三方派券[" + JsonUtil.gsonStr(list) + "]");
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "通知第三方派券异常", e);
            sysErrorLogRocketmqService.consumeFailure(new String(message.getBody()), message.getTopic(), message.getTags(), message.getDelayTimeLevel());
        }
    }

    /**
     * 第三方更新券状态
     *
     * @param rollCode
     * @param rollStatus
     */
    public void sendUpdateRollStatusMessage(String rollCode, Integer rollStatus) {
        Message message = new Message();
        try {
            List<Map<String, Object>> list = new ArrayList<>(1);
            Map<String, Object> map = new HashMap<>(2);
            map.put("coupon", rollCode);
            map.put("couponStatus", rollStatus);
            list.add(map);
            message.setTopic(updateRollStatusMQConfig.getTopic());
            message.setTags(updateRollStatusMQConfig.getTag());
            message.setBody(JsonUtil.gsonStr(list).getBytes());
            producerClient.sendMessage(message);
            logger.info("MQ 提供消息 - 第三方更新券状态[" + JsonUtil.gsonStr(list) + "]");
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "第三方更新券状态异常", e);
            sysErrorLogRocketmqService.consumeFailure(new String(message.getBody()), message.getTopic(), message.getTags(), message.getDelayTimeLevel());
        }
    }
}
