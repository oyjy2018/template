package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IPayFeignService;
import com.ydc.cgj.app.service.PayService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.order.BuyRollParamVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayServiceImpl implements PayService {
    @Resource
    IPayFeignService iPayFeignService;

    @Override
    public Result payOrder(PayOrderParamVO payOrderParamVO, Integer memberId) {
        return iPayFeignService.payOrder(payOrderParamVO, memberId);
    }

    @Override
    public Result buyRoll(BuyRollParamVO buyRollParamVO, Integer memberId) {
        return iPayFeignService.buyRoll(buyRollParamVO, memberId);
    }
}
