package com.ydc.commom.exception;

/**
 * service异常
 *
 * @author gongjin
 * @create 2017-05-08 10:23
 **/
public class ServiceRuntimeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4228802987242247929L;

    private int code;

    public ServiceRuntimeException() {
        super();
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }
    public ServiceRuntimeException(String message,int code) {
        super(message);
        this.code=code;
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }

    protected ServiceRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
