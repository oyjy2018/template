package com.ydc.cgj.bridge.feignService;

import com.ydc.commom.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-order")
public interface WeiXinPayFeignService {

    /**
     * 微信支付成功回调通知
     * @param payWater
     */
    @PostMapping(value = "/order/doWeiXinPayNotify")
    Result doWeiXinPayNotify(@RequestParam("payWater") String payWater, @RequestParam("transactionId") String transactionId);
}
