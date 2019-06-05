package com.ydc.commom.urlHttp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class DefaultResponseStrCallBack extends CallBackUtil<String> {

    private final Logger logger = LogManager.getLogger(DefaultResponseStrCallBack.class);

    @Override
    public String onParseResponse(RealResponse response) {
        try {
            return getRetString(response.inputStream);
        } catch (Exception e) {
            throw new RuntimeException("failure");
        }
    }
}
