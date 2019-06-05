package com.ydc.beans.weiXinPay;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.constant.PayConstant;

import java.io.InputStream;

public class MyWXPayConfig extends WXPayConfig {

    private int payEnvType;

    MyWXPayConfig(){
        payEnvType = PayConstant.IN_WEIXIN_PAY;
    }

    MyWXPayConfig(int payEnvType){
        this.payEnvType = payEnvType;
    }

    @Override
    public String getAppID() {
        return PayConstant.IN_APP_PAY == payEnvType ? SystemPropertiesConfig.WEIXIN_PAY_OPEN_APPID : SystemPropertiesConfig.WEIXIN_PAY_APPID;
//        return PayConstant.IN_APP_PAY == payEnvType ? SystemPropertiesConfig.WEIXIN_PAY_APPID : SystemPropertiesConfig.WEIXIN_PAY_OPEN_APPID;
//        return SystemPropertiesConfig.WEIXIN_PAY_OPEN_APPID;
    }

    @Override
    public String getMchID() {
        return SystemPropertiesConfig.WEIXIN_PAY_MCHID;
    }

    @Override
    public String getKey() {
        return SystemPropertiesConfig.WEIXIN_PAY_APIKEY;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new MyWXPayDomain();
    }

    @Override
    public boolean shouldAutoReport() {
        return false;
    }
}
