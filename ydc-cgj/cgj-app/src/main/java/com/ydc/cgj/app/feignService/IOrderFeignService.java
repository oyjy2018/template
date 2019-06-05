package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.order.CancelOrderDTO;
import com.ydc.commom.view.dto.cgj.order.MyOrderDTO;
import com.ydc.commom.view.dto.cgj.order.OrderDetailsDTO;
import com.ydc.commom.view.vo.cgj.shopCart.CreateOrderParamVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-order")
public interface IOrderFeignService {

    /**
     * H5我的订单
     *
     * @param myOrderDTO
     * @return
     */
    @PostMapping(value = "/order/getOrderList")
    String getOrderList(@RequestBody MyOrderDTO myOrderDTO);

    /**
     * H5查询订单详情
     *
     * @return
     */
    @PostMapping(value = "/order/getOrderCommodityDetail")
    String getOrderCommodityDetail(@RequestBody OrderDetailsDTO orderDetailsDTO);

    /**
     * H5取消订单
     *
     * @param cancelOrderDTO
     * @return
     */
    @PostMapping(value = "/order/cancelOrder")
    String cancelOrder(@RequestBody CancelOrderDTO cancelOrderDTO);


    /**
     * 查看物流
     *
     * @param orderId
     * @return
     */
    @PostMapping(value = "/order/getOrderLogistics")
    String getOrderLogistics(@RequestParam("orderId") Integer orderId);

    /**
     * 确认收货
     *
     * @param orderId
     * @param memberId
     * @return
     */
    @PostMapping(value = "/order/notarizeReceiving")
    String notarizeReceiving(@RequestParam("orderId") Integer orderId, @RequestParam("memberId") Integer memberId);


    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    Result createOrder(@RequestBody CreateOrderParamVO createOrderParamVO,
                       @RequestParam("memberId") Integer memberId);

    /**
     * 查询订单环节数量
     *
     * @param memberId
     * @return
     */
    @PostMapping(value = "/order/getOrderStatusNum")
    String getOrderStatusNum(@RequestParam("memberId") Integer memberId);
}
