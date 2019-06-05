package com.ydc.beans.weiXinPay;


import com.ydc.commom.constant.PayConstant;
import com.ydc.commom.util.ObjectUtil;

import java.util.Map;

public class WeiXinPay {
    private String body;
    private String spbill_create_ip;
    private String trade_type;
    private String scene_info;
    private String out_trade_no;
    private String total_fee;
    private String openid;

    private WeiXinPay(String spbill_create_ip, String trade_type, String out_trade_no, String total_fee, String openid){
        this.spbill_create_ip = spbill_create_ip;
        this.trade_type = trade_type;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.openid = openid;
        this.body = "特惠商品";
        this.scene_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://yidianche.net\",\"wap_name\": \"一点车\"}}";
    }

    public static Map<String, String> getH5PayParams(String spbill_create_ip, String out_trade_no, String total_fee, String trade_type){
        WeiXinPay weiXinH5Pay = new WeiXinPay(spbill_create_ip, trade_type, out_trade_no, total_fee, null);
        return ObjectUtil.getObjectMap(weiXinH5Pay);
    }

    public static Map<String, String> getJSPayParams(String spbill_create_ip, String out_trade_no, String total_fee, String openid){
        WeiXinPay weiXinJSPay = new WeiXinPay(spbill_create_ip, PayConstant.TRADE_TYPE_JS, out_trade_no, total_fee, openid);
        return ObjectUtil.getObjectMap(weiXinJSPay);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getScene_info() {
        return scene_info;
    }

    public void setScene_info(String scene_info) {
        this.scene_info = scene_info;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
