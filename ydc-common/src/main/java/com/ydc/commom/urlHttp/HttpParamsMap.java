package com.ydc.commom.urlHttp;

import com.ydc.commom.constant.HttpConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求map
 */
public class HttpParamsMap extends HashMap<String, Object> {
    private static final String KEY = "nuhcuienncuewfe";

    private HttpParamsMap() {
        super();
    }

    public static HttpParamsMap getBHttpParamsMap(){
       return getBHttpParamsMap(KEY);
    }

    public static HttpParamsMap getBHttpParamsMap(String key){
        HttpParamsMap httpParamsMap = new HttpParamsMap();
        httpParamsMap.put(HttpConstant.PARAM_KEY, key);
        return httpParamsMap;
    }
}
