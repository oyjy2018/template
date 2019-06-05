package com.ydc.cgj.bridge.service.impl;

import com.ydc.cgj.bridge.feignService.WeiXinPayFeignService;
import com.ydc.cgj.bridge.service.WeiXinPayService;
import com.ydc.commom.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {

    @Autowired
    private WeiXinPayFeignService weiXinPayFeignService;


    @Override
    public Result doWeiXinPayNotify(String payWater, String transactionId) {
        return weiXinPayFeignService.doWeiXinPayNotify(payWater, transactionId);
    }
}
