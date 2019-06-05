package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.order.CancelOrderDTO;
import com.ydc.commom.view.dto.cgj.order.MyOrderDTO;
import com.ydc.commom.view.dto.cgj.order.OrderDetailsDTO;
import com.ydc.commom.view.vo.cgj.shopCart.CreateOrderParamVO;

/**
 * 订单
 */
public interface OrderService {

    /**
     * H5 我的订单
     *
     * @param myOrderDTO
     * @return
     */
    String getOrderList(MyOrderDTO myOrderDTO);

    /**
     * H5 订单详情
     *
     * @param orderDetailsDTO
     * @return
     */
    String getOrderCommodityDetail(OrderDetailsDTO orderDetailsDTO);

    /**
     * H5取消订单
     *
     * @param cancelOrderDTO
     * @return
     */
    String cancelOrder(CancelOrderDTO cancelOrderDTO);

    /**
     * 查看物流
     *
     * @param orderId
     * @return
     */
    String getOrderLogistics(Integer orderId);

    /**
     * 确认收货
     *
     * @param orderId
     * @param memberId
     * @return
     */
    String notarizeReceiving(Integer orderId, Integer memberId);

    Result createOrder(CreateOrderParamVO createOrderParamVO, Integer memberId);

    /**
     * 查询订单环节数量
     *
     * @param memberId
     * @return
     */
    String getOrderStatusNum(Integer memberId);

}
