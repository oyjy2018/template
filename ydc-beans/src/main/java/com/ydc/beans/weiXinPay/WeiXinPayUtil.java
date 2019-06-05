package com.ydc.beans.weiXinPay;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayUtil;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WeiXinPayUtil {
    private static Logger logger = LogManager.getLogger(WXPayUtil.class);

    private static final String RESULT_SUCCESS = "SUCCESS";
    private static final String RESULT_FAIL = "FAIL";

    /**
     * 微信支付
     * @param params
     * @param notifyUrl
     * @return
     * @throws Exception
     */
    public static Map<String, String> pay(Map<String, String> params, String notifyUrl, int payEnvType) throws ServiceRuntimeException{
        if (params == null || params.size() == 0)
            throw new ServiceRuntimeException("params is empty");
        if (StringUtil.isEmpty(notifyUrl))
            throw new ServiceRuntimeException("notifyUrl is empty");
        try {
        MyWXPayConfig myWXPayConfig = new MyWXPayConfig(payEnvType);
        WXPay wxPay = new WXPay(myWXPayConfig, notifyUrl, false);
        return wxPay.unifiedOrder(params);
        }catch (Exception e){
            throw  new ServiceRuntimeException("请稍后重试",ResultConstant.RESULT_CODE_FAILURE);
        }
    }

    /**
     * H5微信支付（处理返回结果）
     * @param params
     * @param notifyUrl
     * @return 正常则返回支付跳转链接，异常返回null
     * @throws Exception
     */
    public static String payThenDealResultH5(Map<String, String> params, String notifyUrl, int payEnvType) throws Exception{
        logger.info("subject: {}, params: {}, notifyUrl: {}", "微信支付参数", JsonUtil.gsonStr(params), notifyUrl);
        Map<String, String> payResult = pay(params, notifyUrl, payEnvType);
        logger.info("subject: {}, result: {}", "微信支付结果", JsonUtil.gsonStr(payResult));
        //支付成功
        if (RESULT_SUCCESS.equals(payResult.get("result_code")) && RESULT_SUCCESS.equals(payResult.get("return_code"))){
            return payResult.get("mweb_url");
        }
        throw new ServiceRuntimeException("微信支付，" + payResult.get("return_msg"));
    }

    /**
     * 公众号微信支付（处理返回结果）
     * @param params
     * @param notifyUrl
     * @return 正常则返回支付码，异常返回null
     * @throws Exception
     */
    public static String payThenDealResultJS(Map<String, String> params, String notifyUrl, int payEnvType) throws ServiceRuntimeException{
        logger.info("subject: {}, params: {}, notifyUrl: {}", "微信支付参数", JsonUtil.gsonStr(params), notifyUrl);
        Map<String, String> payResult = pay(params, notifyUrl, payEnvType);
        logger.info("subject: {}, result: {}", "微信支付结果", JsonUtil.gsonStr(payResult));
        //支付成功
        if (RESULT_SUCCESS.equals(payResult.get("result_code")) && RESULT_SUCCESS.equals(payResult.get("return_code"))){
            return payResult.get("prepay_id");
        }
        throw new ServiceRuntimeException("微信支付，" + payResult.get("return_msg"));
    }

    /**
     * 获取微信内支付前端需要的数据
     * @param prepayId
     * @return
     */
    public static Map<String, String> getJSPayParams(String prepayId, int payEnvType) throws Exception{
        Map<String, String> map = new HashMap<>();
        WXPayConfig wxPayConfig = new MyWXPayConfig(payEnvType);
        map.put("appId", wxPayConfig.getAppID());
        map.put("timeStamp", String.valueOf(DateUtil.getSecondTimeStamp(new Date())));
        map.put("nonceStr", WXPayUtil.generateNonceStr());
        map.put("package", "prepay_id=" + prepayId);
        map.put("signType", "MD5");
        map.put("paySign", WXPayUtil.generateSignature(map, wxPayConfig.getKey()));
        return map;
    }

    /**
     * 获取app内支付前端需要的数据
     * @param prepayId
     * @return
     */
    public static Map<String, String> getAPPPayParams(String prepayId, int payEnvType) throws Exception{
        Map<String, String> map = new HashMap<>();
        WXPayConfig wxPayConfig = new MyWXPayConfig(payEnvType);
        map.put("appid", wxPayConfig.getAppID());
        map.put("partnerid", wxPayConfig.getMchID());
        map.put("timestamp", String.valueOf(DateUtil.getSecondTimeStamp(new Date())));
        map.put("noncestr", WXPayUtil.generateNonceStr());
        map.put("package", "Sign=WXPay");
        map.put("prepayid", prepayId);
        map.put("sign", WXPayUtil.generateSignature(map, wxPayConfig.getKey()));
        return map;
    }

    /**
     * 获取微信支付通知的对象
     * @param params
     * @return
     * @throws Exception
     */
    public static WeiXinPayResult getWeiXinPayResult(String params) throws Exception{
        if (params == null) return null;
        XMLSerializer xmlSerializer = new XMLSerializer();
        String resutStr = xmlSerializer.read(params).toString();
        JSONObject result = JSONObject.fromObject(resutStr);
        return (WeiXinPayResult) JSONObject.toBean(result, WeiXinPayResult.class);
    }

    /**
     * 验签
     * @param requestData
     * @return
     */
    public static boolean checkSign(Map<String, String> requestData) throws Exception {
        return requestData != null && requestData.get("sign").equals(WXPayUtil.generateSignature(requestData, SystemPropertiesConfig.WEIXIN_PAY_APIKEY));
    }

//    /**
//     * 获取参数map
//     * @param payEnvType
//     * @param createIp
//     * @param openId
//     * @return
//     */
//    public static Map<String, String> getPayParams(int payEnvType, String createIp, String openId, String payWater, String amount){
//        Map<String, String> params = new HashMap<>();
//        params.put("body", "订单总额");
////        params.put("spbill_create_ip", "113.116.114.71");
//        params.put("spbill_create_ip", createIp);
//        params.put("trade_type", payEnvType == PayConstant.IN_WEIXIN_PAY ? "JSAPI" : "MWEB");
//        params.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://pay.qq.com\",\"wap_name\": \"腾讯充值\"}}");
//        params.put("out_trade_no", payWater);
//        params.put("total_fee", amount);
//        if (openId != null) params.put("openid", openId);
//        return params;
//    }

    /**
     * 根据微信支付的请求支付url获取支付id
     * @param mwebUrl
     * @return
     */
    public static String getPrepayIdByUrl(String mwebUrl){
        if (mwebUrl == null){
            return null;
        }
        String tempPrepayId = mwebUrl.split("prepay_id=")[1];
        return tempPrepayId.split("&")[0];
    }
}
