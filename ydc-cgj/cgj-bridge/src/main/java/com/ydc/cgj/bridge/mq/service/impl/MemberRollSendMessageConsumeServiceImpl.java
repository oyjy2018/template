package com.ydc.cgj.bridge.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.cgj.bridge.mq.service.ThirdPartyMessageConsumeService;
import com.ydc.cgj.bridge.service.MemberRollService;
import com.ydc.cgj.bridge.service.OrderService;
import com.ydc.cgj.bridge.service.SysErrorLogHttpService;
import com.ydc.cgj.bridge.service.TicketService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.RollConstant;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 第三方派发用户优惠券
 */
@Service(value = "memberRollSend")
public class MemberRollSendMessageConsumeServiceImpl implements ThirdPartyMessageConsumeService {
    private final Logger logger = LogManager.getLogger(MemberRollSendMessageConsumeServiceImpl.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SysErrorLogHttpService sysErrorLogHttpService;

    @Autowired
    private MemberRollService memberRollService;

    @Autowired
    private OrderService orderService;

    @Override
    public boolean consumeMessage(byte[] content, final boolean isRetry) {
        String contentStr = new String(content);
        logger.info("消费http请求第三方派发用户券接口消息, 参数: {}", contentStr);

        JSONObject jsonObject = JSON.parseObject(contentStr);
        Map<String, Object> paramsMap = HttpParamsMap.getBHttpParamsMap();
        String params = jsonObject.getString("list");
        paramsMap.put("userCoupons", params);
        logger.info("http请求第三方派发用户券接口, 参数: {}", params);
        final String url = SystemPropertiesConfig.B_SERVICE_URL + "yhqg/batchBindUser";
        final String callBackUrl = SystemPropertiesConfig.SELF_BRIDGE_SERVICE_URL + "thirdParty/preHttp/memberRollSend";
        UrlHttpUtil.post(url, paramsMap, new DefaultResponseStrCallBack() {
            @Override
            public void onResponse(String response) {
                logger.info("http请求第三方派发用户券接口成功, 结果: {}", response);
                try {
                    UrlHttpUtil.doResponse(response, (data) ->{
                        List<Map<String, Object>> dataList = JsonUtil.parseToListMap(data);
                        List<String> rollCodeList = dataList.parallelStream().map(map ->
                                (String) map.get("couponNo")).collect(Collectors.toList());
                        //更新空券状态
                        logger.info("subject: {}, rollCodeList: {}", "第三方派发用户券接口成功处理", rollCodeList);
                        ticketService.batchUpdateBlankRollStatus(rollCodeList, RollConstant.RollStatusEnum.ROLL_STATUS_1.getKey());

                        //更新会员券状态和借款单派券信息
                        Object loanIdsObj = jsonObject.get("loanIds");
                        logger.info("subject: {}, loanIdsObj: {}", "第三方派发用户券接口成功处理", loanIdsObj);
                        List<Integer> loanIds = loanIdsObj == null ? null : (List) loanIdsObj;
                        memberRollService.batchUpdateMemberRollStatus(rollCodeList, CodeConstant.NORMAL_STATUS, loanIds);

                        //更新订单状态
                        Object orderNosObj = jsonObject.get("orderNos");
                        logger.info("subject: {}, orderNosObj: {}", "第三方派发用户券接口成功处理", orderNosObj);
                        if (orderNosObj != null) {
                            List<String> orderNoList = (List) orderNosObj;
                            orderService.updateSendRollOrderStatus(orderNoList);
                        }

                    });
                } catch (Exception e) {
                    logger.error("http请求第三方派发用户券接口异常, 参数: {}", contentStr, e);
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
