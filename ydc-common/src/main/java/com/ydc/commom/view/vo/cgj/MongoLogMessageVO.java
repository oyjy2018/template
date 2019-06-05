package com.ydc.commom.view.vo.cgj;

import java.util.Map;

public class MongoLogMessageVO {
    private String url;
    private String httpMethod;
    private String ip;
    private Map subject;
    private String classMethod;
    private String args;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map getSubject() {
        return subject;
    }

    public void setSubject(Map subject) {
        this.subject = subject;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
