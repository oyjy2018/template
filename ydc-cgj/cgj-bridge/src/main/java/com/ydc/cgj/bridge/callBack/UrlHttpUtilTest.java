package com.ydc.cgj.bridge.callBack;

import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.urlHttp.UrlHttpUtil;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class UrlHttpUtilTest {
    public static void main(String[] args) {
        Map<String, Object> paramsMap=new HashMap<>();
        paramsMap.put("time","2018222");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","姓名");
        jsonObject.put("age","20");
        byte[] bytes=jsonObject.toString().getBytes();
        paramsMap.put("data",new String(Base64.getUrlEncoder().encode(bytes)));
        paramsMap.put("sign","000000000");
        UrlHttpUtil.get("http://127.0.0.1:12000/test/get",paramsMap,new CallBackResultTest());
    }
}