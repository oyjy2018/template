package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.service.OrderFeignService;
import com.ydc.cgj.web.service.OrderService;
import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.dto.cgj.order.MemberWaterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderFeignService orderFeignService;

    @Override
    public String getOrderSonList(OrderReqDTO orderReqDTO) {
        return orderFeignService.getOrderSonList(orderReqDTO);
    }

    @Override
    public String getOrderCommodityList(OrderReqDTO orderReqDTO) {
        return orderFeignService.getOrderCommodityList(orderReqDTO);
    }

    @Override
    public String allShipments(OrderReqDTO ord) {
        return orderFeignService.allShipments(ord);
    }

    @Override
    public String portionShipments(OrderReqDTO ord) {
        return orderFeignService.portionShipments(ord);
    }

    @Override
    public String getMemberSaters(MemberWaterDTO memberWaterDTO) {
        return orderFeignService.getMemberSaters(memberWaterDTO);
    }
}
