package com.ydc.commom.enums;

/**
 * 登陆错误码
 *
 * @author gongjin
 * @create 2018-09-07 11:09
 **/
public enum LoginEnum {


    LG_405(405, "获取钉钉开放应用的ACCESS_TOKEN，请稍后再试"),
    LG_406(406, "钉钉服务器繁忙，请稍后再试"),
    LG_407(407, "获取持久授权码异常，请稍后再试"),
    LG_408(408, "获取SNS_TOKEN异常，请稍后再试"),
    LG_409(409, "获取成员的userid异常，请稍后再试"),
    LG_410(410, "获取成员详情异常，请稍后再试");
    /**
     * 功能码
     */
    private int code;

    /**
     * 接口描述
     */
    private String message;


    LoginEnum(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
