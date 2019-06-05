package com.ydc.cgj.bridge.service.impl;

import com.ydc.cgj.bridge.feignService.OrderFeignService;
import com.ydc.cgj.bridge.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderFeignService orderFeignService;

    @Override
    public Integer getOrderMemberId(String payWater) {
        return orderFeignService.getOrderMemberId(payWater);
    }

    @Override
    public void updateSendRollOrderStatus(List<String> orderList) {
        orderFeignService.updateSendRollOrderStatus(orderList);
    }
}
