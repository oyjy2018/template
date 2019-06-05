package com.ydc.beans.filter;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 过滤无需加密
 *
 * @author
 * @create 2019-01-10 11:14
 **/
public enum ExclusionsEnum{
    PATH_URL_1("/performance/","手机号码登录"),
    PATH_URL_2("/getLoginParam","获取钉钉扫码配置"),
    PATH_URL_3("/credit/","芝麻信用授权处理"),
    PATH_URL_4("/ydhc/","一点好车登录"),
    PATH_URL_5("/login/error","网络竟然崩溃了");

    String url;
    String des;

    ExclusionsEnum(String url, String des) {
        this.url = url;
        this.des = des;
    }


    public static String getExclusionsEnum(){
        List<String> list = Arrays.asList(ExclusionsEnum.values()).stream().map(m ->m.url).collect(Collectors.toList());
        System.out.println(list.stream().collect(Collectors.joining(",")));
        return list.stream().collect(Collectors.joining(","));
    }

    public static boolean verifyParam(String path){
        Optional<String> opt = Arrays.asList(ExclusionsEnum.values()).stream().filter(item ->path.equals(item.url) || path.startsWith(item.url)).map(item -> item.url).findAny();
        return opt.isPresent();
    }

    public static void main(String[] args) {
        getExclusionsEnum();
        System.out.println(verifyParam("/performance"));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
