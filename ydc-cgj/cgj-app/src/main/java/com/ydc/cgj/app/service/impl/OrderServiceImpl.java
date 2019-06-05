package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IOrderFeignService;
import com.ydc.cgj.app.service.OrderService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.order.CancelOrderDTO;
import com.ydc.commom.view.dto.cgj.order.MyOrderDTO;
import com.ydc.commom.view.dto.cgj.order.OrderDetailsDTO;
import com.ydc.commom.view.vo.cgj.shopCart.CreateOrderParamVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 订单
 *
 * @author gongjin
 * @create 2018-09-10 20:05
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    IOrderFeignService iOrderFeignService;

    @Override
    public String getOrderList(MyOrderDTO myOrderDTO) {
        return iOrderFeignService.getOrderList(myOrderDTO);
    }

    @Override
    public String getOrderCommodityDetail(OrderDetailsDTO orderDetailsDTO) {
        return iOrderFeignService.getOrderCommodityDetail(orderDetailsDTO);
    }

    @Override
    public String cancelOrder(CancelOrderDTO cancelOrderDTO) {
        return iOrderFeignService.cancelOrder(cancelOrderDTO);
    }

    @Override
    public String getOrderLogistics(Integer orderId) {
        return iOrderFeignService.getOrderLogistics(orderId);
    }

    @Override
    public String notarizeReceiving(Integer orderId, Integer memberId) {
        return iOrderFeignService.notarizeReceiving(orderId, memberId);
    }

    @Override
    public Result createOrder(CreateOrderParamVO createOrderParamVO, Integer memberId) {
        return iOrderFeignService.createOrder(createOrderParamVO, memberId);
    }

    @Override
    public String getOrderStatusNum(Integer memberId) {
        return iOrderFeignService.getOrderStatusNum(memberId);
    }
}
