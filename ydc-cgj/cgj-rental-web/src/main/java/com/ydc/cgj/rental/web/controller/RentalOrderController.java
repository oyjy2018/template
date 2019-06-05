package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.IdemPotenceUtil;
import com.ydc.cgj.rental.web.service.RentalOrderService;
import com.ydc.cgj.rental.web.service.RentalSettlementService;
import com.ydc.cgj.rental.web.service.RentalViolationService;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.AddRentalOrderPCDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 租车订单
 *
 * @author
 * @create 2018-11-22 13:55
 **/
@RestController
@RequestMapping(value = "/rentalOrder")
public class RentalOrderController {

    private static final Logger logger = LogManager.getLogger(RentalOrderController.class);


    @Autowired
    RentalOrderService rentalOrderService;

    @Autowired
    private RentalViolationService rentalViolationService;

    @Autowired
    RentalSettlementService rentalSettlementService;


    /**
     * 查询车辆等级
     * @param
     * @return
     */
    @PostMapping(value = "/getCarLevelGroup")
    public String getCarLevelGroup(){
        logger.info("subject:{}","查询车辆等级");
        return rentalOrderService.getCarLevelGroup();
    }

    /**
     * 查询车辆品牌
     * @param
     * @return
     */
    @PostMapping(value = "/getBrandByCarLevel")
    public String getBrandByCarLevel(@RequestParam("data") String data){
        logger.info("subject:{},commCarDTO:{}","查询车辆品牌",data);
        CommCarDTO commCarDTO =  JSONObject.parseObject(data,CommCarDTO.class);
        return rentalOrderService.getBrandByCarLevel(commCarDTO);
    }

    /**
     * 查询车辆车系
     * @param
     * @return
     */
    @PostMapping(value = "/getSeriesByBrand")
    public String getSeriesByBrand(@RequestParam("data") String data){
        logger.info("subject:{},commCarDTO:{}","查询车辆品牌",data);
        CommCarDTO commCarDTO =  JSONObject.parseObject(data,CommCarDTO.class);
        return rentalOrderService.getSeriesByBrand(commCarDTO);
    }

    /**
     * 查询车辆车型
     * @param
     * @return
     */
    @PostMapping(value = "/getModelBySeries")
    public String getModelBySeries(@RequestParam("data") String data){
        logger.info("subject:{},commCarDTO:{}","查询车辆车型",data);
        CommCarDTO commCarDTO =  JSONObject.parseObject(data,CommCarDTO.class);
        return rentalOrderService.getModelBySeries(commCarDTO);
    }

    /**
     * 新增租车订单
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:order:manage:order:view:addOrder"})
    @PostMapping(value = "/insertOrder")
    public String insertOrder(@RequestParam("data") String data){
        logger.info("subject:{},addRentalOrderPCDTO:{}","查询车辆车型",data);
        AddRentalOrderPCDTO addRentalOrderPCDTO = JSONObject.parseObject(data,AddRentalOrderPCDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.ADD_RENTAL_ORDER.getPrefix(),addRentalOrderPCDTO.getIdCard(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() :
                rentalOrderService.insertOrder(addRentalOrderPCDTO);
    }

    /**
     * 返回所有门店
     * @return
     */
    @PostMapping(value = "/getAllStore")
    public String getAllStore() {
        logger.info("subject:{}","返回所有门店");
        return rentalOrderService.getAllStore();
    }

    /**
     * 获取订单列表
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:order:manage:order:view:query"})
    @PostMapping(value = "/getRentalOrderList")
    public String getRentalOrderList(@RequestParam("data") String data){
        logger.info("subject:{},req:{}","获取订单列表参数",data);
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if(req == null){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少列表参数").toJSON();
        }
        if(req.containsKey("status") && !"".equals(req.get("status").toString())){
            String status = req.get("status").toString();
            RentalOrderEnum.StatusReqEnum rose = RentalOrderEnum.StatusReqEnum.getEnumByStatus(status);
            if(rose == null){
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "此订单状态不存在").toJSON();
            }
            req.putAll(rose.getSearchParamMap());
        }
        if(!req.containsKey("page")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少页码参数").toJSON();
        }
        if(!req.containsKey("rows")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少展示行数参数").toJSON();
        }
        return rentalOrderService.getRentalOrderList(req);
    }

    /**
     * 查询订单详情
     * @param data
     * @return
     */
   // @RequiresPermissions(value = {"rental:order:manage:order:view:detail"})
    @PostMapping(value = "/getRentalOrderDetailsPC")
    public String getRentalOrderDetailsPC(@RequestParam("data") String data) {
        logger.info("subject:{},req:{}","获取订单列表参数",data);
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if(!req.containsKey("rentalOrderId")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少订单ID参数").toJSON();
        }
        return rentalOrderService.getRentalOrderDetailsPC(req);
    }

    /**
     * 修改订单流程状态
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:order:manage:order:view:updateOrderStatus"})
    @PostMapping(value = "/updateRentalOrderFlowStatus")
    public String updateRentalOrderFlowStatus(@RequestParam("data") String data) {
        logger.info("subject:{},req:{}","修改订单流程状态参数",data);
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if(req == null){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少参数").toJSON();
        }
        if(req.containsKey("status") && !"".equals(req.get("status").toString())){
            String status = req.get("status").toString();
            RentalOrderEnum.StatusReqEnum rose = RentalOrderEnum.StatusReqEnum.getEnumByStatus(status);
            if(rose == null){
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "此订单状态不存在").toJSON();
            }
            req.putAll(rose.getUpdateParamMap());
        }
        return rentalOrderService.updateRentalOrderFlowStatus(req);
    }

    /**
     * 提交违章结算单
     * @param data
     * @return
     * @author df
     */
    @RequiresPermissions(value = {"rental:order:manage:order:view:addViolation"})
    @PostMapping(value = "/updateViolationSettlement")
    public String updateViolationSettlement(@RequestParam("data") String data){
        logger.info("subject:{},req:{}","获取订单列表参数",data);
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(req.get("orderId"))) {
            throw new ServiceRuntimeException("订单id不能为空");
        }
        if (StringUtil.isEmpty(req.get("updateBy"))) {
            throw new ServiceRuntimeException("更新人id不能为空");
        }
        if (StringUtil.isEmpty(req.get("updateMan"))) {
            throw new ServiceRuntimeException("更新人不能为空");
        }
        if (StringUtil.isEmpty(req.get("violationPayroll"))) {
            throw new ServiceRuntimeException("违章代付费用不能为空");
        }
        if (StringUtil.isEmpty(req.get("violationPreAuthorizationAmount"))) {
            throw new ServiceRuntimeException("违章预授权额不能为空");
        }
        if (StringUtil.isEmpty(req.get("violationAuthRefundAmount"))) {
            throw new ServiceRuntimeException("违章预授权退还金额不能为空");
        }
        Result result=rentalViolationService.updateViolationSettlement(req);
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS  ) {
            int type= (int) result.getData();
            if ( RentalDepositConstant.PAYMENT_MODE_1.intValue()==type){
                logger.info("调用支付宝结算");
                new Thread(()->
                        rentalSettlementService.updateAliPaySettleDTO((Integer) req.get("orderId"),RentalDepositConstant.DEPOSIT_TYPE_2.intValue())).start();
            }
        }
        return result.toJSON();
    }
}
