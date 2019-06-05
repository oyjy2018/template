package com.ydc.commom.urlHttp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class DefaultCallBack extends CallBackUtil<String> {

    private final Logger logger = LogManager.getLogger(DefaultCallBack.class);

    @Override
    public String onParseResponse(RealResponse response) {
        try {
            return getRetString(response.inputStream);
        } catch (Exception e) {
            throw new RuntimeException("failure");
        }
    }

    @Override
    public void onFailure(int code, String errorMessage) {
        logger.error("http请求失败, code: {}, errorMessage: {}", code, errorMessage);
    }
}
