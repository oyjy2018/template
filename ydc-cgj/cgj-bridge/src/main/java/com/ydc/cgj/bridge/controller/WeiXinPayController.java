package com.ydc.cgj.bridge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.wxpay.sdk.WXPayUtil;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.weiXinPay.WeiXinPayResult;
import com.ydc.beans.weiXinPay.WeiXinPayUtil;
import com.ydc.cgj.bridge.service.MemberRollService;
import com.ydc.cgj.bridge.service.OrderService;
import com.ydc.cgj.bridge.service.WeiXinPayService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RollConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping(value = "/weixin/pay")
public class WeiXinPayController {
    private final Logger logger = LogManager.getLogger(WeiXinPayController.class);

    @Autowired
    private WeiXinPayService weiXinPayService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberRollService memberRollService;

    /**
     * 微信支付成功通知地址
     *
     * @param request
     */
    @RequestMapping(value = "/notify")
    public String notify(HttpServletRequest request) throws Exception {
//        WeiXinPayResult weiXinPayResult = getNotifyParams(request);
        Map<String, String> weiXinPayResult = getNotifyParamsMap(request);
        logger.info("subject: {}, weiXinPayResult: {}", "微信支付成功通知回调", weiXinPayResult);
        if (weiXinPayResult == null){
            return getFailNotify();
        }
        //验签
        if (!WeiXinPayUtil.checkSign(weiXinPayResult)){
            logger.error("subject: {}, weiXinPayResult: {}", "微信支付回调验签失败", weiXinPayResult);
            return getSuccessNotify();
        }
        //支付状态为成功
        if (!"SUCCESS".equals(weiXinPayResult.get("return_code"))){
            logger.error("subject: {}, weiXinPayResult: {}", "微信支付回调支付状态异常", weiXinPayResult.get("return_code"));
            return getSuccessNotify();
        }

        String payWater = weiXinPayResult.get("out_trade_no");
        Result notifyResult = weiXinPayService.doWeiXinPayNotify(payWater, weiXinPayResult.get("transaction_id"));
        logger.info("subject: {}, notifyResult: {}", "微信支付成功通知回调处理订单结果", JsonUtil.gsonStr(notifyResult));
        if (notifyResult.getCode() == ResultConstant.RESULT_CODE_SUCCESS) {
            final List<Map<String, Object>> rollSizeList = (List<Map<String, Object>>) notifyResult.getData();
            if (rollSizeList != null && rollSizeList.size() > 0) {
                //发送券
                String rollSize = JsonUtil.gsonStr(rollSizeList);
                new Thread(() -> payAfterSendRoll(payWater, rollSize)).start();
            }
        }
        return getSuccessNotify();
    }

    /**
     * 支付成功之后派发券
     * @param payWater
     * @param rollSize
     */
    @PostMapping(value = "/payAfterSendRoll")
    public void payAfterSendRoll(@RequestParam(value = "payWater") String payWater,
                                 @RequestParam(value = "rollSize") String rollSize){
        logger.info("subject: {}, payWater: {}, rollSizeList: {}", "支付成功之后派发券", payWater, rollSize);
        List<Map<String, Object>> rollSizeList = JSON.parseObject(rollSize, new TypeReference<List<Map<String, Object>>>(){});
        //券信息
        final Map<String, Integer> rollTypeMap = new HashMap<>(2);
        rollTypeMap.put(RollConstant.WASH_ROLL_CODE, 1); //洗车券
        rollTypeMap.put(RollConstant.MAINTAIN_ROLL_CODE, 2); //保养券
        //发送券的券信息
        rollSizeList.parallelStream().forEach(map -> map.put(RollConstant.ROLL_TYPE, rollTypeMap.get(map.get(RollConstant.ROLL_TYPE))));

        //用户信息
        Integer memberId = orderService.getOrderMemberId(payWater);
        List<Map<String, Integer>> memberInfos = new ArrayList<>(1);
        Map<String, Integer> memberMap = new HashMap<>(1);
        memberMap.put(RollConstant.MEMBER_ID, memberId);
        memberInfos.add(memberMap);

        //发放参数
        Map<String, Object> params = new HashMap<>();
        DictionaryDetail validDays = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.ROLL_VALID_DAYS, DictionaryConstant.PARENT_CODE_ROLL).orElse(null);
        DictionaryDetail validTime = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.ROLL_VALID_TIME, DictionaryConstant.PARENT_CODE_ROLL).orElse(null);
        params.put("sendDescription", "用户兑换");
        params.put("validDays", validDays == null ? null : validDays.getDictValue());
        params.put("validTime", validTime == null ? null : validTime.getDictValue());
        params.put("userId", 1);
        logger.info("subject: {}, memberInfos: {}, rollSizeList: {}, params: {}",
                "微信支付成功之后回调派券", JsonUtil.gsonStr(memberInfos), JsonUtil.gsonStr(rollSizeList), JsonUtil.gsonStr(params));
        memberRollService.sendRoll(JsonUtil.gsonStr(memberInfos), JsonUtil.gsonStr(rollSizeList), JsonUtil.gsonStr(params));
    }

    private String getNotifyParamsStr(HttpServletRequest request) throws Exception{
        InputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                outStream.flush();
                return new String(outStream.toByteArray(), "UTF-8");
            }
        }finally {
            IOUtils.closeQuietly(inStream);
            IOUtils.closeQuietly(outStream);
        }
        return null;
    }

    private WeiXinPayResult getNotifyParamsObj(HttpServletRequest request) throws Exception{
        return WeiXinPayUtil.getWeiXinPayResult(getNotifyParamsStr(request));
    }

    private Map<String, String> getNotifyParamsMap(HttpServletRequest request) throws Exception{
        return WXPayUtil.xmlToMap(Objects.requireNonNull(getNotifyParamsStr(request)));
    }

    private String getSuccessNotify(){
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg> </xml>";
    }

    private String getFailNotify(){
        return "<xml><return_code><![CDATA[FAIL]]></return_code>";
    }
}
