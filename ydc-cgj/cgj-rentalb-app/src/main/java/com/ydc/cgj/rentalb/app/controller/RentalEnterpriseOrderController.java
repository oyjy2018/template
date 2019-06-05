package com.ydc.cgj.rentalb.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.IdemPotenceUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rentalb.app.service.RentalEnterpriseOrderService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 企业订单
 *
 * @author
 * @create 2019-01-04 10:10
 **/
@RestController
@RequestMapping(value = "/rentalEnterpriseOrder")
public class RentalEnterpriseOrderController {


    private static final Logger logger = LogManager.getLogger(RentalEnterpriseOrderController.class);


    @Autowired
    RentalEnterpriseOrderService rentalEnterpriseOrderService;



    /**
     * 查询外部订单列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getStoreRentalEnterpriseOrderList")
    public String getStoreRentalEnterpriseOrderList(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","查询外部订单列表",data);
        RentalEnterpriseOrderDTO dto = JSONObject.parseObject(data,RentalEnterpriseOrderDTO.class);
        User user = WebShiroTokenManager.getUser();
        if (user == null) {
            return Result.failure("未获取到用户信息").toJSON();
        }
        String viewOrgId = user.getViewOrgId();
        if (StringUtil.isEmpty(viewOrgId)) {
            return Result.failure("无负责的门店").toJSON();
        }
        dto.setViewOrgId(viewOrgId);
        return rentalEnterpriseOrderService.getStoreRentalEnterpriseOrderList(dto);
    }

    /**
     * 外部订单:查看详情
     * @param data
     * @return
     */
    @PostMapping(value = "/getStoreRentalEnterpriseOrderId")
    public String getStoreRentalEnterpriseOrderId(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","外部订单:查看详情",data);
        RentalEnterpriseOrderDTO dto = JSONObject.parseObject(data,RentalEnterpriseOrderDTO.class);
        return rentalEnterpriseOrderService.getStoreRentalEnterpriseOrderId(dto);
    }

    /**
     * 外部订单:取消订单
     * @param data
     * @return
     */
    @PostMapping(value = "/cancelRentalEnterpriseOrder")
    public String cancelRentalEnterpriseOrder(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","外部订单:取消订单",data);
        RentalEnterpriseOrderDTO dto = JSONObject.parseObject(data,RentalEnterpriseOrderDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_CANCEL.getPrefix(),dto.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.cancelRentalEnterpriseOrder(dto);
    }

    /**
     * 外部订单:拒绝订单
     * @param data
     * @return
     */
    @PostMapping(value = "/rejectRentalEnterpriseOrder")
    public String rejectRentalEnterpriseOrder(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","外部订单:拒绝订单",data);
        RentalEnterpriseOrderDTO dto = JSONObject.parseObject(data,RentalEnterpriseOrderDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_REJECT.getPrefix(),dto.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.rejectRentalEnterpriseOrder(dto);
    }

    /**
     * 外部订单:确认订单
     * @param data
     * @return
     */
    @PostMapping(value = "/notarizeRentalEnterpriseOrder")
    public String notarizeRentalEnterpriseOrder(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","外部订单:确认订单",data);
        RentalEnterpriseOrderDTO dto = JSONObject.parseObject(data,RentalEnterpriseOrderDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_NOTARIZE.getPrefix(),dto.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.notarizeRentalEnterpriseOrder(dto);
    }

    /**
     * 外部订单:订单id查询车辆信息
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalCheckCarByOrderId")
    public String getRentalCheckCarByOrderId(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","外部订单:订单id查询车辆信息",data);
        RentalCheckCarDTO dto = JSONObject.parseObject(data,RentalCheckCarDTO.class);
        return rentalEnterpriseOrderService.getRentalCheckCarByOrderId(dto);
    }
}
