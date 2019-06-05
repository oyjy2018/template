package com.ydc.cgj.bridge.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.cgj.bridge.mq.service.ThirdPartyMessageConsumeService;
import com.ydc.cgj.bridge.service.MemberAppointmentService;
import com.ydc.cgj.bridge.service.SysErrorLogHttpService;
import com.ydc.cgj.bridge.service.TicketService;
import com.ydc.commom.constant.RollConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.urlHttp.DefaultCallBack;
import com.ydc.commom.urlHttp.DefaultResponseStrCallBack;
import com.ydc.commom.urlHttp.HttpParamsMap;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.MemberAppointment;
import com.ydc.model.cgj.SysErrorLogHttp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 第三方添加预约订单
 */
@Service(value = "appointmentOrderAdd")
public class AppointmentOrderAddMessageConsumeServiceImpl implements ThirdPartyMessageConsumeService {
    private final Logger logger = LogManager.getLogger(AppointmentOrderAddMessageConsumeServiceImpl.class);

    @Autowired
    private MemberAppointmentService memberAppointmentService;

    @Autowired
    private SysErrorLogHttpService sysErrorLogHttpService;

    @Autowired
    private TicketService ticketService;

    @Override
    public boolean consumeMessage(byte[] content, final boolean isRetry) {
        String contentStr = new String(content);
        logger.info("http请求第三方添加预约订单接口, 参数: {}", contentStr);
        JSONObject jsonObject = JSON.parseObject(contentStr);
        Map<String, Object> paramsMap = HttpParamsMap.getBHttpParamsMap();
        final String orderCode = jsonObject.getString("orderCode");
        final String rollCodes = jsonObject.getString("rollCodes");
        paramsMap.put("storeNo", jsonObject.getString("storeCode"));
        paramsMap.put("coupons", rollCodes);
        paramsMap.put("arrTime", jsonObject.getString("appointTime"));
        paramsMap.put("cOrderNo", orderCode);

        final String url = SystemPropertiesConfig.B_SERVICE_URL + "store/addOrder";
        final String callBackUrl = SystemPropertiesConfig.SELF_BRIDGE_SERVICE_URL + "thirdParty/preHttp/appointmentOrderAdd";
        UrlHttpUtil.post(url, paramsMap, new DefaultResponseStrCallBack(){
            //请求成功
            @Override
            public void onResponse(String response) {
                logger.info("http请求第三方添加预约订单接口成功, 结果: {}", response);
                try {
                    //状态码为失败时
                    UrlHttpUtil.doResponse(response, (data) -> {
                        if (data != null && !("").equals(data)) {
                            updateFailStatus(JSON.parseObject(data).getString("cOrderNo"));
                        }
                    //状态码为成功时时
                    }, (data) -> {
                        JSONObject jsonObject = JSON.parseObject(data);
                        MemberAppointment memberAppointment = new MemberAppointment();
                        memberAppointment.setOrderNo(jsonObject.getString("cOrderNo")); //订单号
                        memberAppointment.setbOrderNo(jsonObject.getString("orderNo")); //B端订单号
                        memberAppointment.setbAppointStatus(jsonObject.getInteger("orderStatus")); //订单号状态
                        memberAppointmentService.updateAppointBInfo(memberAppointment);

//                        //更新空白券状态
//                        List<Map<String, String>> rollCodeMap = JsonUtil.jsonToListMap(rollCodes);
//                        List<String> rollCodeList = rollCodeMap.parallelStream().map(map -> map.get("couponNo")).collect(Collectors.toList());
//                        ticketService.batchUpdateBlankRollStatus(rollCodeList, RollConstant.RollStatusEnum.ROLL_STATUS_1.getKey());
                    });
                } catch (ServiceRuntimeException serviceException) {
                    logger.info("第三方添加预约订单接口请求成功，请求结果为失败，message: {}", serviceException.getMessage());
                } catch (Exception e) {
                    logger.error("处理第三方添加预约订单接口结果异常", e);
                }
            }

            //请求异常
            @Override
            public void onFailure(int code, String errorMessage) {
                logger.error("http请求失败, code: {}, errorMessage: {}", code, errorMessage);
                //更新订单状态
                updateFailStatus(orderCode);
                if (!isRetry) {
                    //添加异常记录
                    sysErrorLogHttpService.insert(SysErrorLogHttp.getBPartyErrorLogHttp(url, contentStr, callBackUrl));
                }
            }
        });
        return true;
    }

    //更新订单状态为失败
    private Result updateFailStatus(String orderCode){
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setOrderNo(orderCode); //订单号
        memberAppointment.setCloseTime(new Date());
        memberAppointment.setAppointStatus(RollConstant.AppointStatusEnum.APPOINT_STATUS_ENUM_4.getKey());
        memberAppointment.setbAppointStatus(RollConstant.AppointStatusEnum.APPOINT_STATUS_ENUM_8.getKey());
        return memberAppointmentService.updateAppointBInfo(memberAppointment);
    }
}
