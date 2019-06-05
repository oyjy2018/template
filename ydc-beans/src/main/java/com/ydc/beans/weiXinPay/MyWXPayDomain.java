package com.ydc.beans.weiXinPay;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;

public class MyWXPayDomain implements IWXPayDomain {

    @Override
    public void report(String s, long l, Exception e) {

    }

    @Override
    public DomainInfo getDomain(WXPayConfig wxPayConfig) {
        return new DomainInfo(WXPayConstants.DOMAIN_API, true);
    }
}
