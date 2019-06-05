package com.ydc.service.order.mq.service;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.mq.sms.service.SMSSendMessageService;
import com.ydc.beans.mq.thirdParty.ThirdPartySendMQConfig;
import com.ydc.beans.rocketMQ.ProducerClient;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.service.order.service.SysErrorLogRocketmqService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class ThirdPartySendMessageService {

    private static Logger logger = LogManager.getLogger(SMSSendMessageService.class);

    @Resource
    private ProducerClient producerClient;
    @Resource
    private ThirdPartySendMQConfig addAppointmentOrderMQConfig;
    @Autowired
    SysErrorLogRocketmqService sysErrorLogRocketmqService;


    /**
     * 第三方创建订单
     *
     * @param orderCode   订单号
     * @param storeCode   门店号
     * @param appointTime 预约时间
     * @param rollCodes   券码
     */
    public void sendAddAppointmentOrderMessage(String orderCode, String storeCode, Date appointTime, List<String> rollCodes) {
        Message message = new Message();
        List<Map<String, String>> rollCodeList = new ArrayList<>();
        if (rollCodes != null && rollCodes.size() > 0){
            rollCodes.parallelStream().forEach(rollCode ->{
                Map<String, String> map = new HashMap<>(1);
                map.put("coupon", rollCode);
                rollCodeList.add(map);
            });
        }
        try {
            message.setTopic(addAppointmentOrderMQConfig.getTopic());
            message.setTags(addAppointmentOrderMQConfig.getTag());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderCode", orderCode);
            jsonObject.put("storeCode", storeCode);
            jsonObject.put("appointTime", DateUtil.getSecondTimeStamp(appointTime));
            jsonObject.put("rollCodes", JsonUtil.gsonStr(rollCodeList));
            message.setBody(jsonObject.toString().getBytes());
            producerClient.sendMessage(message);
            logger.info("MQ 提供消息 - 第三方创建预约订单[" + jsonObject.toString() + "]");
        }catch (Exception e){
            logger.error("subject:{},e:{}","第三方创建订单异常",e);
            sysErrorLogRocketmqService.consumeFailure(new String(message.getBody()),message.getTopic(),message.getTags(),message.getDelayTimeLevel());
        }
    }
}
