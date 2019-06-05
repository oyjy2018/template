package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.dto.cgj.order.MemberWaterDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "service-order")
public interface OrderFeignService {

    /**
     * 获取用户订单列表
     *
     * @param orderReqDTO
     * @return
     */
    @GetMapping(value = "/order/getOrderSonList")
    String getOrderSonList(@RequestBody OrderReqDTO orderReqDTO);

    /**
     * 获取商品订单列表
     *
     * @param orderReqDTO
     * @return
     */
    @PostMapping(value = "/order/getOrderCommodityList")
    public String getOrderCommodityList(@RequestBody OrderReqDTO orderReqDTO );

    /**
     * 全部发货
     *
     * @return
     */
    @PostMapping(value = "/order/allShipments")
    public String allShipments(@RequestBody OrderReqDTO ord);

    /**
     * 部分发货
     *
     * @param ord
     * @return
     */
    @PostMapping(value = "/order/portionShipments")
    public String portionShipments(@RequestBody OrderReqDTO ord);
    /**
     * 查询交易流水
     * @author: hejiangping
     * @date: 2019/1/15
     */
    @PostMapping(value = "/order/getMemberSaters")
    public String getMemberSaters(@RequestBody MemberWaterDTO memberWaterDTO);
}
