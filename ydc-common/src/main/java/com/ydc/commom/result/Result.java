package com.ydc.commom.result;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class Result<T> implements Serializable {

    //系统级别的code
    private int code;
    //中文提示信息
    private String message;
    //返回参数
    private T data;
    //扩展字段
    private Object extra;


    public  Result() {

    }

    private Result(String message) {
        this.message = message;
    }

    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(int code, String message, T data, Object extra) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.extra = extra;
    }


    public static <T> Result<T> getResult() {
        return new Result<>();
    }

    public static <T> Result<T> getResult(int code, String message) {
        return new Result<>(code, message);
    }

    public static Result success() {
        Result result = new Result<>(ResultConstant.RESULT_CODE_SUCCESS, "成功");
        return result;
    }

    public static Result success(String message) {
        Result result = new Result<>(ResultConstant.RESULT_CODE_SUCCESS, message);
        return result;
    }

    public static <T> Result success(T data) {
        Result result = new Result<>(ResultConstant.RESULT_CODE_SUCCESS, "成功", data);
        return result;
    }

    public static <T> Result success(String message, T data) {
        Result result = new Result<>(ResultConstant.RESULT_CODE_SUCCESS, message, data);
        return result;
    }


    public static Result failure() {
        Result result = new Result<>(ResultConstant.RESULT_CODE_FAILURE, "失败");
        return result;
    }

    public static Result failure(String message) {
        Result result = new Result<>(ResultConstant.RESULT_CODE_FAILURE, message);
        return result;
    }

    public static Result exception(String message) {
        Result result = new Result<>(ResultConstant.RESULT_CODE_EXCEPTION, message);
        return result;
    }

    public static Result exception(int code, String message) {
        Result result = new Result<>(code, message);
        return result;
    }

    public static Result notLogin(){
        return new Result(ResultConstant.NOT_LOGIN_FAILURE, "您未登录，请先登录");
    }

    public String toJSON() {
        return JSON.toJSONString(this);
    }

    public Result toResult(String jsonStr) {
        return JSON.parseObject(jsonStr, Result.class);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

}
