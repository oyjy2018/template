package com.ydc.cgj.bridge.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.bridge.mq.service.ThirdPartyMessageConsumeService;
import com.ydc.cgj.bridge.service.MemberAppointmentService;
import com.ydc.commom.constant.cgj.appointment.BOrderResponseDTOConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 消费B端推送的 订单状态改变 mq
 */
@Service(value = "updateAppointmentByBOrderStatusMsg")
public class UpdateAppointByBOrderStatusMsgConsumeServiceImpl implements ThirdPartyMessageConsumeService {
    private final Logger logger = LogManager.getLogger(UpdateAppointByBOrderStatusMsgConsumeServiceImpl.class);

    @Autowired
    private MemberAppointmentService memberAppointmentService;

    @Override
    public boolean consumeMessage(byte[] content, final boolean isRetry) {
        String contentStr =null;
        try {
            contentStr= new String(content,"utf-8");
            logger.info("MQ订单修改消费, B端发送参数: {}", contentStr);
            BOrderResponseDTO bOrderResponseDTO = JSONObject.parseObject(contentStr,BOrderResponseDTO.class);
             return memberAppointmentService.updateAppointStatusByBAppointStatus(bOrderResponseDTO,new Integer(1));
        }catch (Exception e){
            logger.info("MQ消费异常，B端参数{}",contentStr ,e);
            return true;
        }
    }
}
