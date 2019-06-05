package com.ydc.commom.dingtalk.demo;

public class OApiException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public OApiException(int errCode, String errMsg) {
        super("error code: " + errCode + ", error message: " + errMsg);
    }
}
