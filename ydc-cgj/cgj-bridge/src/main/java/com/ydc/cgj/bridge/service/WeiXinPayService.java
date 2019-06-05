package com.ydc.cgj.bridge.service;

import com.ydc.commom.result.Result;

public interface WeiXinPayService {

    /**
     * 微信支付成功回调通知
     * @param payWater
     */
    Result doWeiXinPayNotify(String payWater, String transactionId);
}
