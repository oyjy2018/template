package com.ydc.cgj.bridge.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.bridge.mq.service.ThirdPartyMessageConsumeService;
import com.ydc.cgj.bridge.service.MemberAppointmentService;
import com.ydc.commom.urlHttp.CallBackUtil;
import com.ydc.commom.urlHttp.HttpParamsMap;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 第三方更新预约订单状态
 */
@Service(value = "appointmentOrderUpdate")
public class UpdateAppointmentMsgConsumeServiceImpl implements ThirdPartyMessageConsumeService {
    private final Logger logger = LogManager.getLogger(UpdateAppointmentMsgConsumeServiceImpl.class);

    @Autowired
    private MemberAppointmentService memberAppointmentService;

    @Override
    public  boolean consumeMessage(byte[] content, final boolean isRetry) {
        String contentStr = new String(content);
        logger.info("http请求第三方添加预约订单接口, 参数: {}", contentStr);
        //发送消息给B端
        JSONObject jsonObject=JSON.parseObject(contentStr);
        Map<String, Object> paramsMap = HttpParamsMap.getBHttpParamsMap();
        paramsMap.put("orderNo",jsonObject.getString("bOrderNo"));
        paramsMap.put("orderStatus",jsonObject.getString("orderStatus"));
        paramsMap.put("updTime",jsonObject.getString("updTime"));
        paramsMap.put("telno",jsonObject.getInteger("telno"));
        String url=jsonObject.getString("url");
        if (StringUtil.isEmpty(url)){
            return true;
        }
        try {
            UrlHttpUtil.post(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(int code, String errorMessage) {
                     /*   //发送失败
                    SysErrorLogHttp sysErrorLogHttp=new SysErrorLogHttp();
                    sysErrorLogHttp.setCallBackClassName(CallBackString.class.getName());
                    sysErrorLogHttp.setParamType("map");
                    sysErrorLogHttp.setRepetitionNum(3);
                    sysErrorLogHttp.setRequestMethod("post");
                    sysErrorLogHttp.setSendNum(0);
                    sysErrorLogHttp.setStatus(0);
                    sysErrorLogHttp.setUrl(url);
                    sysErrorLogHttp.setRequestParam(param.toString());*/

                }
                @Override
                public void onResponse(String response) {
                        //解析数据，  更新成功，更新失败
                    JSONObject jsonObject = JSON.parseObject(response);
                    logger.info("取消预约返回, 参数: {}", jsonObject.toString());
                    if(Integer.valueOf(jsonObject.get("status").toString()).intValue() != 1){
                        logger.info("取消预约失败, 参数: {}", jsonObject.toString());
                        return;
                    }
                    BOrderResponseDTO bOrderResponseDTO = JSONObject.parseObject(jsonObject.get("data").toString(),BOrderResponseDTO.class);
                    memberAppointmentService.updateAppointStatusByBAppointStatus(bOrderResponseDTO,new Integer(1));
                }
            });
        }catch (Exception e){
            logger.error("http请求第三方添加预约订单接口异常, 参数: {}", contentStr, e);
            return false;
        }
        return true;
    }
}
