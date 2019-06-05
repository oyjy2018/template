package com.ydc.cgj.bridge.callBack;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.result.Result;
import com.ydc.commom.urlHttp.CallBackUtil;
import com.ydc.commom.urlHttp.RealResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CallBackResultTest extends CallBackUtil<Result> {

    private  static Logger logger=LogManager.getLogger(CallBackResultTest.class);

    @Override
    public Result onParseResponse(RealResponse response) {
        try(    InputStreamReader isr=new InputStreamReader(response.inputStream);
                BufferedReader reader=new BufferedReader(isr)
        ) {
            StringBuilder buffer = new StringBuilder();
            String str;
            while((str = reader.readLine()) != null ) {
                buffer.append(str);
            }
            JSONObject object= JSON.parseObject(buffer.toString());
            return object.toJavaObject(Result.class);
        }catch (Exception e){
           logger.info("请求失败");
        }
       logger.info("请求失败");

        return null;
    }

    @Override
    public void onFailure(int code, String errorMessage)
    {
       logger.info(code+"____"+errorMessage);
    }

    @Override
    public void onResponse(Result response) {

       logger.info(response.toJSON());
    }
}
