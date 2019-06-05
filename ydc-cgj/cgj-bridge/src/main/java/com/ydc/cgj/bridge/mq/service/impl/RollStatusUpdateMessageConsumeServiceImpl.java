package com.ydc.cgj.bridge.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.cgj.bridge.mq.service.ThirdPartyMessageConsumeService;
import com.ydc.cgj.bridge.service.SysErrorLogHttpService;
import com.ydc.cgj.bridge.service.TicketService;
import com.ydc.commom.urlHttp.DefaultCallBack;
import com.ydc.commom.urlHttp.DefaultResponseStrCallBack;
import com.ydc.commom.urlHttp.HttpParamsMap;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.SysErrorLogHttp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 更新用户券状态
 */
@Service(value = "rollStatusUpdate")
public class RollStatusUpdateMessageConsumeServiceImpl implements ThirdPartyMessageConsumeService {
    private final Logger logger = LogManager.getLogger(RollStatusUpdateMessageConsumeServiceImpl.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SysErrorLogHttpService sysErrorLogHttpService;

    @Override
    public boolean consumeMessage(byte[] content, final boolean isRetry) {
        String contentStr = new String(content);
        logger.info("http请求第三方更新用户券状态接口, 参数: {}", contentStr);

        Map<String, Object> paramsMap = HttpParamsMap.getBHttpParamsMap();
        paramsMap.put("couponNos", contentStr);
        final String url = SystemPropertiesConfig.B_SERVICE_URL + "yhqg/batchUpdYhqStatus";
        final String callBackUrl = SystemPropertiesConfig.SELF_BRIDGE_SERVICE_URL + "thirdParty/preHttp/rollStatusUpdate";
        UrlHttpUtil.post(url, paramsMap, new DefaultResponseStrCallBack() {
            @Override
            public void onResponse(String response) {
                logger.info("http请求第三方更新用户券状态接口成功, 结果: {}", response);
                try {
                    UrlHttpUtil.doResponse(response, (data) ->{
                        JSONObject jsonObject = JSON.parseObject(data);
                        List<Map<String, Object>> dataList = JsonUtil.parseToListMap(jsonObject.getString("couponNos"));
                        String rollCode = (String) dataList.get(0).get("couponNo");
                        Integer rollStatus = (Integer) dataList.get(0).get("status");
                        List<String> rollCodes = new ArrayList<>();
                        rollCodes.add(rollCode);
                        ticketService.batchUpdateBlankRollStatus(rollCodes, rollStatus);
                    });
                } catch (Exception e) {
                    logger.error("http请求第三方更新用户券状态接口异常, 参数: {}", contentStr, e);
                }
            }

            @Override
            public void onFailure(int code, String errorMessage) {
                logger.error("http请求失败, code: {}, errorMessage: {}", code, errorMessage);
                //添加异常记录
                if (!isRetry) sysErrorLogHttpService.insert(SysErrorLogHttp.getBPartyErrorLogHttp(url, contentStr, callBackUrl));
            }
        });
        return true;
    }
}
