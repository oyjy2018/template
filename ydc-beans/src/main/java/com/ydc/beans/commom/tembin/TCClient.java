package com.ydc.beans.commom.tembin;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.constant.TianchengConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TCClient {
    private static final Logger logger = LogManager.getLogger(TCClient.class);
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    
//    private static final String aeskey = "ogjX24W3AnaJxi922lGPbISqAmMd9LVa";
    private static final String aeskey = SystemPropertiesConfig.NEW_TIANCHENG_JMMY;

    @SuppressWarnings("unchecked")
//    public static Map<String, Object> OkHttpPost(String url, String orgCode, String orgName, String userName, String userPassword, String functionCode, String businessData) throws Exception {
//        Map<String, Object> result = null;
//        if(null == url || "".equals(url)) {
//            throw new NullPointerException("url is not empty");
//        }
//        if(null == orgCode || "".equals(orgCode)) {
//            throw new NullPointerException("orgCode is not empty");
//        }
//        if(null == orgName || "".equals(orgName)) {
//            throw new NullPointerException("orgName is not empty");
//        }
//        if(null == userPassword || "".equals(userPassword)) {
//            throw new NullPointerException("userPassword is not empty");
//        }
//        if(null == functionCode || "".equals(functionCode)) {
//            throw new NullPointerException("functionCode is not empty");
//        }
//        try {
//            RequestHead header = new RequestHead(orgCode, orgName, userName, userPassword, functionCode);
//            String rsaBusines = AESUtil.encrypt(businessData, aeskey);
//            RequestData<String> requestData = new RequestData<String>(header, rsaBusines);
//            String requestJson = JSONObject.toJSONString(requestData);
//            logger.info("subject:{},requestJson:{}","请求报文",requestJson);
//            RequestBody requestBody = RequestBody.create(JSON, requestJson);
//            Request request = new Request.Builder().url(url).post(requestBody).build();
//            OkHttpClient httpClient = new OkHttpClient();
//            Response response = httpClient.newCall(request).execute();
//            String responseBody = response.body().string();
//            result = JsonUtil.jsonToMap(responseBody);
//            if(null != responseBody && !"".equals(responseBody)) {
//                logger.info("subject:{},responseBody:{}","响应报文",responseBody);
//                ResponseData<String> responseData = JSONObject.parseObject(responseBody, ResponseData.class);
//                ResponseHead resHead = responseData.getHeader();
//                if(resHead.getRtCode().equals("E000000")) {
//                    logger.info("subject:{}","返回成功");
//                }
//                result.put("data",StringUtil.isEmpty(responseData.getBusinessData())?null:JsonUtil.jsonToMap(AESUtil.decrypt(responseData.getBusinessData(), aeskey)));
//            }
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public static ResponseData<String> OkHttpPost(String url, String orgCode, String orgName, String userName,
                                                  String userPassword, String functionCode, String businessData) throws Exception {
        if(null == url || "".equals(url)) {
            throw new NullPointerException("url is not empty");
        }
        if(null == orgCode || "".equals(orgCode)) {
            throw new NullPointerException("orgCode is not empty");
        }
        if(null == orgName || "".equals(orgName)) {
            throw new NullPointerException("orgName is not empty");
        }
        if(null == userPassword || "".equals(userPassword)) {
            throw new NullPointerException("userPassword is not empty");
        }
        if(null == functionCode || "".equals(functionCode)) {
            throw new NullPointerException("functionCode is not empty");
        }
        RequestHead header = new RequestHead(orgCode, orgName, userName, userPassword, functionCode);
        String rsaBusines = AESUtil.encrypt(businessData, aeskey);
        RequestData<String> requestData = new RequestData<>(header, rsaBusines);
//        String requestJson = JSONObject.toJSONString(requestData);
        String requestJson = JsonUtil.gsonStr(requestData);
        logger.info("subject:{},requestJson:{}","请求报文",requestJson);
        RequestBody requestBody = RequestBody.create(JSON, requestJson);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(6,TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(10,TimeUnit.SECONDS)//设置读取超时时间
                .build();
        Call call = httpClient.newCall(request);
        Response response = null;
        ResponseHead responseHead = new ResponseHead();
        ResponseData<String> responseData = null;
        try{
            response = call.execute();
        }catch (Exception e){
            logger.error("subject:{},e:{}","天秤接口异常",e);
            if(e instanceof SocketTimeoutException){//判断超时异常
                responseHead.setRtCode(TianchengConstant.IDENTITY_ABNORMAL_CODE);
                responseHead.setRtMsg("接口请求超时");
            }
            if(e instanceof ConnectException){//判断连接异常，我这里是报Failed to connect to 10.7.5.144
                responseHead.setRtCode(TianchengConstant.IDENTITY_ABNORMAL_CODE);
                responseHead.setRtMsg("连接异常");
            }
            responseData = new ResponseData<>();
            responseData.setHeader(responseHead);
            return responseData;
        }
        String responseBody = response.body().string();
        logger.info("subject:{},responseBody:{}","响应报文",responseBody);
        responseData = returnResponseData(responseBody,responseData,responseHead);
        if (TianchengConstant.IDENTITY_SUCCESS_CODE.equals(responseData.getHeader().getRtCode())){
            responseData.setBusinessData(StringUtil.isEmpty(responseData.getBusinessData()) ? null :
                    AESUtil.decrypt(responseData.getBusinessData(), aeskey));
        }
        return responseData;
    }

    private static ResponseData<String> returnResponseData(String responseBody,ResponseData<String> responseData,ResponseHead responseHead){
        if(responseBody != null){
            return JSONObject.parseObject(responseBody, ResponseData.class);
        }
        responseHead.setRtCode(TianchengConstant.IDENTITY_FAILING_CODE);
        responseHead.setRtMsg("响应数据为空");
        responseData.setHeader(responseHead);
        return responseData;
    }
    

    
    

    public static void main(String[] args) throws Exception {
        Map<String, String> reqmap = new HashMap<>();
        reqmap.put("idNumber", "");
        reqmap.put("name", "");
        String functionCode = "CHNL_10000001";
        String jsonString = JSONObject.toJSONString(reqmap);
        logger.info("subject:{},jsonString:{}","身份证验证请求业务数据明文",jsonString);
        long start  = System.currentTimeMillis();
        ResponseData<String> responseBody = TCClient.OkHttpPost("http://uat.libradata.com.cn/tc-boss-web-charge/uap", "1004", "一点车贷", "yidian", "2wsxcde3", functionCode, jsonString);
       logger.info("耗时:"+(System.currentTimeMillis() - start));
       logger.info("请求结果："+JsonUtil.gsonStr(responseBody));
    }
}
