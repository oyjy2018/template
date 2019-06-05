package com.ydc.cgj.rental.company.app.controller;

import com.ydc.beans.redis.IdemPotenceUtil;
import com.ydc.cgj.rental.company.app.common.CompanyUtil;
import com.ydc.cgj.rental.company.app.service.EnterpriseOrderService;
import com.ydc.commom.constant.rental.RentalEnterpriseOrderConstant;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.view.dto.cgj.rental.AddRentalEnterpriseOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarSeriesDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarPublishDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.PCRentalEnterpriseOrderVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalEnterpriseCarVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.model.cgj.rental.RentalEnterpriseOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rentalEnterpriseOrder")
public class RentalEnterpriseOrderController {

    private final Logger logger = LogManager.getLogger(RentalEnterpriseOrderController.class);

    @Autowired
    EnterpriseOrderService enterpriseOrderService;

    /**
     * 新增企业租车订单
     * @param data
     * @return
     */
    @PostMapping(value = "/addRentalOrder")
    public String addRentalOrder(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","新增企业租车订单信息", JsonUtil.gsonStr(data));
        AddRentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, AddRentalEnterpriseOrderDTO.class);
        RentalCompanyCustomerVO customer = CompanyUtil.getCompanyCustomer();
        dto.setDemandSideId(customer.getId());
        dto.setDemandSide(customer.getRegisteredCompanyName());
        dto.setUserId(customer.getId());
        dto.setUserName(customer.getRegisteredCompanyName());
        boolean res = IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_ADD.getPrefix(),
                dto.getDemandSideId().toString(),Long.valueOf(5));
        if(res) return Result.failure("请勿重复提交").toJSON();

        Map<String, Object> vaildMap = ParamVaildateUtil.vaildate(data, AddRentalEnterpriseOrderDTO.class);
        if("1".equals(vaildMap.get("code"))){
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        return enterpriseOrderService.addRentalOrder(dto);
    }

    /**
     * 取消订单
     * @param data
     * @return
     */
    @PostMapping(value = "/cancelOrder")
    public String cancelOrder(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","取消订单信息", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, RentalEnterpriseOrderDTO.class);
        if(dto.getOrderId() == null) return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少订单ID").toJSON();
        return enterpriseOrderService.cancelOrder(dto);
    }

    /**
     * 拒绝订单
     * @param data
     * @return
     */
    @PostMapping(value = "/refuseOrder")
    public String refuseOrder(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","拒绝订单信息", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, RentalEnterpriseOrderDTO.class);
        if(dto.getOrderId() == null) return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少订单ID").toJSON();
        return enterpriseOrderService.refuseOrder(dto);
    }

    /**
     * 查询需求方订单列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getEnterpriseOrderListB2BD")
    public String getEnterpriseOrderListB2BD(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","查询需求方订单列表信息", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, RentalEnterpriseOrderDTO.class);
        if(dto.getDemandSideId() == null) return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少客户ID").toJSON();
        return enterpriseOrderService.getEnterpriseOrderListB2BD(dto);
    }

    /**
     * 查询资源方订单列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getEnterpriseOrderListB2BR")
    public String getEnterpriseOrderListB2BR(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","查询资源方订单列表信息", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, RentalEnterpriseOrderDTO.class);
        if(dto.getResourceSideId() == null) return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少客户ID").toJSON();
        return enterpriseOrderService.getEnterpriseOrderListB2BR(dto);
    }

    /**
     * 查询车商端租车订单详情
     * @param data
     * @return
     */
    @PostMapping(value = "/getEnterpriseOrderDetailB2BD")
    public String getEnterpriseOrderDetailB2BD(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","查询车商端租车订单详情信息", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, RentalEnterpriseOrderDTO.class);
        if(dto.getOrderId() == null) return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少订单ID").toJSON();
        return enterpriseOrderService.getEnterpriseOrderDetailB2BD(dto);
    }

    /**
     * 车商端出租订单详情
     * @param data
     * @return
     */
    @PostMapping(value = "/getEnterpriseOrderDetailB2BR")
    public String getEnterpriseOrderDetailB2BR(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","车商端出租订单详情信息", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, RentalEnterpriseOrderDTO.class);
        if(dto.getOrderId() == null) return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少订单ID").toJSON();
        return enterpriseOrderService.getEnterpriseOrderDetailB2BR(dto);
    }

    /**
     * 确认订单（资源方）
     * @param data
     * @return
     */
    @PostMapping(value = "/confirmOrder")
    public String confirmOrder(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","确认订单", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, RentalEnterpriseOrderDTO.class);
        if(dto.getOrderId() == null) return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少订单ID").toJSON();
        return enterpriseOrderService.confirmOrder(dto);
    }

    /**
     * 获取发布信息
     * @param data
     * @return
     */
    @PostMapping(value = "/getPublishInfo")
    public String getPublishInfo(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","获取发布信息", JsonUtil.gsonStr(data));
        RentalCarPublishDTO dto = JsonUtil.jsonToBean(data, RentalCarPublishDTO.class);
        if(dto.getPublishId() == null) return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少发布信息ID").toJSON();
        return enterpriseOrderService.getPublishInfo(dto);
    }
}
