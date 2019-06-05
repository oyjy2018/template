package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.web.service.OrderService;
import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.dto.cgj.order.MemberWaterDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private static final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 获取用户订单列表
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getOrderSonList", method = RequestMethod.POST)
    public String getOrderSonList(@RequestParam("data") String data) {
        logger.info("subject:{},orderReqDTO:{}", "获取用户订单列表", data);
        OrderReqDTO orderReqDTO = JSONObject.parseObject(data,OrderReqDTO.class);
        return orderService.getOrderSonList(orderReqDTO.changeDTO());
    }

    /**
     * 获取商品订单列表
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getOrderCommodityList", method = RequestMethod.POST)
    public String getOrderCommodityList(@RequestParam("data") String data) {
        logger.info("subject:{},orderReqDTO:{}", "获取商品订单列表", data);
        OrderReqDTO orderReqDTO = JSONObject.parseObject(data,OrderReqDTO.class);
        return orderService.getOrderCommodityList(orderReqDTO.changeDTO());
    }

    /**
     * 全部发货
     *
     * @return
     */
    @RequestMapping(value = "/allShipments", method = RequestMethod.POST)
    public String allShipments(@RequestParam("data") String data) {
        logger.info("subject:{},orderReqDTO:{}", "全部发货", data);
        OrderReqDTO orderReqDTO = JSONObject.parseObject(data,OrderReqDTO.class);
        return orderService.allShipments(orderReqDTO);
    }

    /**
     * 部分发货
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/portionShipments", method = RequestMethod.POST)
    public String portionShipments(@RequestParam("data") String data) {
        logger.info("subject:{},orderReqDTO:{}", "全部发货", data);
        OrderReqDTO orderReqDTO = JSONObject.parseObject(data,OrderReqDTO.class);
        return orderService.portionShipments(orderReqDTO);
    }

    /**
     * 查询交易流水列表
     * @author: hejiangping
     * @date: 2019/1/15
     */
    @RequestMapping(value = "/getMemberSaters", method = RequestMethod.POST)
    public String getMemberSaters(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "查询交易流水列表", data);
        MemberWaterDTO memberWaterDTO = JSONObject.parseObject(data,MemberWaterDTO.class);
        memberWaterDTO.Suffix();
        return orderService.getMemberSaters(memberWaterDTO);
    }
}
