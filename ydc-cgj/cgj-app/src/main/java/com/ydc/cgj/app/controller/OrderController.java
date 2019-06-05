package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.OrderService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.NumberUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.order.CancelOrderDTO;
import com.ydc.commom.view.dto.cgj.order.MyOrderDTO;
import com.ydc.commom.view.dto.cgj.order.OrderDTO;
import com.ydc.commom.view.dto.cgj.order.OrderDetailsDTO;
import com.ydc.commom.view.vo.cgj.shopCart.BuyerOrderCommodityParamVO;
import com.ydc.commom.view.vo.cgj.shopCart.CreateOrderParamVO;
import com.ydc.commom.view.vo.cgj.shopCart.ShopCartParamVO;
import com.ydc.model.cgj.Member;
import com.ydc.commom.exception.ServiceRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 订单
 *
 * @author gongjin
 * @create 2018-09-10 20:07
 **/
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    protected static final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    /**
     * H5 我的订单
     *
     * @return
     */
    @PostMapping(value = "/getOrderList")
    public String getOrderList(@RequestParam("data") String data) {
        logger.info("subject:{},myOrderDTO:{}","H5 我的订单",data);
        try {
            Member member = SubjectUtil.getMember();
            if (member == null) return Result.failure("用户未登陆").toJSON();
            MyOrderDTO myOrderDTO = JSONObject.parseObject(data,MyOrderDTO.class);
            myOrderDTO.setMemberId(member.getId());
            return orderService.getOrderList(myOrderDTO);
        } catch (ServiceRuntimeException se) {
            logger.error(se.getMessage(), se);
            return Result.failure(se.getMessage()).toJSON();
        } catch (Exception e) {
            logger.error("H5 我的订单", e);
            return Result.failure().toJSON();
        }
    }

    /**
     * H5 订单详情
     *
     * @return
     */
    @PostMapping(value = "/getOrderCommodityDetail")
    public String getOrderCommodityDetail(@RequestParam("data") String data) {
        logger.info("subject:{},orderDetailsDTO:{}","H5 订单详情",data);
        try {
            Member member = SubjectUtil.getMember();
            if (member == null) return Result.failure("用户未登陆").toJSON();
            OrderDetailsDTO orderDetailsDTO = JSONObject.parseObject(data,OrderDetailsDTO.class);
            orderDetailsDTO.setMemberId(member.getId());
            return orderService.getOrderCommodityDetail(orderDetailsDTO);
        } catch (Exception e) {
            logger.error("H5 订单详情", e);
            return Result.failure().toJSON();
        }
    }

    /**
     * H5取消订单
     *
     * @return
     */
    @PostMapping(value = "/cancelOrder")
    public String cancelOrder(@RequestParam("data") String data) {
        logger.info("subject:{},cancelOrderDTO:{}","H5 取消订单",data);
        try {
            Member member = SubjectUtil.getMember();
            if (member == null) return Result.failure("用户未登陆").toJSON();
            CancelOrderDTO cancelOrderDTO = JSONObject.parseObject(data,CancelOrderDTO.class);
            cancelOrderDTO.setMemberId(member.getId());
            return orderService.cancelOrder(cancelOrderDTO);
        } catch (Exception e) {
            logger.error("取消订单失败", e);
            return Result.failure("取消订单失败").toJSON();
        }
    }

    /**
     * 查看物流
     *
     * @return
     */
    @PostMapping(value = "/getOrderLogistics")
    public String getOrderLogistics(@RequestParam("data") String data) {
        logger.info("subject:{},orderDTO:{}","H5 查看物流",data);
        try {
            OrderDTO orderDTO = JSONObject.parseObject(data,OrderDTO.class);
            return orderService.getOrderLogistics(orderDTO.getOrderId());
        } catch (Exception e) {
            logger.error("查看物流失败", e);
            return Result.failure("查看物流失败").toJSON();
        }
    }

    /**
     * 确认收货
     *
     * @return
     */
    @PostMapping(value = "/notarizeReceiving")
    public String logicalNotarizeOrder(@RequestParam("data") String data) {
        logger.info("subject:{},orderDTO:{}","H5 确认收货",data);
        try {
            Member member = SubjectUtil.getMember();
            if (member == null) return Result.failure("用户未登陆").toJSON();
            OrderDTO orderDTO = JSONObject.parseObject(data,OrderDTO.class);
            return orderService.notarizeReceiving(orderDTO.getOrderId(), member.getId());
        } catch (Exception e) {
            logger.error("确认收货失败", e);
            return Result.failure("确认收货失败").toJSON();
        }
    }

    /**
     * 创建订单
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String createMemberOrder(@RequestParam("data") String data) {
        logger.info("subject:{},orderDTO:{}","H5 创建订单",data);
        CreateOrderParamVO createOrderParamVO = JSONObject.parseObject(data,CreateOrderParamVO.class);
        if (!verifyCreateOrderParamVO(createOrderParamVO)) {
            return Result.failure("数据错误").toJSON();
        }
        Member member = SubjectUtil.getMember();
        if (null == member) {
            return Result.failure("用户未登陆").toJSON();
        }
        Result result = orderService.createOrder(createOrderParamVO, member.getId());
        return result.toJSON();
    }

    private boolean verifyCreateOrderParamVO(CreateOrderParamVO createOrderParamVO) {
        if (null == createOrderParamVO) return false;
        if (StringUtil.isEmpty(createOrderParamVO.getSerial())
                || StringUtil.isEmpty(createOrderParamVO.getMemberDeliveryAddressId())
                || (null == createOrderParamVO.getBuyerOrderCommodityParamVOS() || createOrderParamVO.getBuyerOrderCommodityParamVOS().isEmpty()))
            return false;

        for (BuyerOrderCommodityParamVO buyerOrderCommodityParamVO : createOrderParamVO.getBuyerOrderCommodityParamVOS()) {
            if (StringUtil.isEmpty(buyerOrderCommodityParamVO.getSupplierCode())
                    || StringUtil.isEmpty(buyerOrderCommodityParamVO.getDeliveryMethod())
                    || (null == buyerOrderCommodityParamVO.getShopCartParamVOS() || buyerOrderCommodityParamVO.getShopCartParamVOS().isEmpty()))
                return false;

            for (ShopCartParamVO shopCartParamVO : buyerOrderCommodityParamVO.getShopCartParamVOS()) {
                if (StringUtil.isEmpty(shopCartParamVO.getId()) && StringUtil.isEmpty(shopCartParamVO.getModelId()))
                    return false;
                if (!StringUtil.isEmpty(shopCartParamVO.getModelId()) && !NumberUtil.hasPositiveNumber(shopCartParamVO.getNumber()))
                    return false;
            }
        }
        return true;
    }


    /**
     * 查询订单环节数量
     *
     * @return
     */
    @PostMapping(value = "/getOrderStatusNum")
    public String getOrderStatusNum() {
        Member member = SubjectUtil.getMember();
        logger.info("subject:{},member:{}","查询订单环节数量", JsonUtil.gsonStr(member));
        if (member == null) return Result.failure("用户未登陆").toJSON();
        return orderService.getOrderStatusNum(member.getId());
    }


}
