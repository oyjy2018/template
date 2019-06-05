package com.ydc.service.order.controller;

import com.ydc.commom.constant.rental.RentalEnterpriseOrderConstant;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.*;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.*;
import com.ydc.model.cgj.rental.*;
import com.ydc.service.order.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/rentalEnterpriseOrder")
public class RentalEnterpriseOrderController {

    private final Logger logger = LogManager.getLogger(RentalEnterpriseOrderController.class);

    @Autowired
    RentalEnterpriseOrderService rentalEnterpriseOrderService;
    @Autowired
    CommCarSeriesService commCarSeriesService;
    @Autowired
    RentalCheckCarService rentalCheckCarService;
    @Autowired
    RentalEnterpriseSettlementService rentalEnterpriseSettlementService;
    @Autowired
    RentalOrderFileService rentalOrderFileService;
    @Autowired
    RentalDepositService rentalDepositService;
    @Autowired
    RentalFeeVoucherService rentalFeeVoucherService;
    @Autowired
    RentalPayWatercourseService rentalPayWatercourseService;
    @Autowired
    RentalCarPublishService rentalCarPublishService;
    @Autowired
    RentalCarService rentalCarService;

    /**
     * 新增企业租车订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/addRentalOrder")
    public String addRentalOrder(@RequestBody AddRentalEnterpriseOrderDTO dto){
        try{
            logger.info("subject:{},AddRentalEnterpriseOrderDTO:{}","新增企业租车订单信息", JsonUtil.gsonStr(dto));
            Result result = rentalEnterpriseOrderService.addRentalOrder(dto);
            if(result.getCode() != ResultConstant.RESULT_CODE_SUCCESS) return result.toJSON();
            Integer orderId = (Integer)result.getData();
            if(orderId == null || orderId == 0)
                return Result.failure("新增订单失败").toJSON();
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("orderId", orderId);
            return Result.success("新增订单成功", jMap).toJSON();
        }catch (Exception e){
            logger.error("新增企业租车订单异常",e);
            return Result.failure("新增企业租车订单异常").toJSON();
        }
    }

    /**
     * 取消订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/cancelOrder")
    public String cancelOrder(@RequestBody RentalEnterpriseOrderDTO dto){
        try{
            logger.info("subject:{},RentalEnterpriseOrderDTO:{}","取消订单信息", JsonUtil.gsonStr(dto));
            RentalEnterpriseOrder reo = rentalEnterpriseOrderService.selectByPrimaryKey(dto.getOrderId());
            if(reo == null) return Result.failure("订单丢失").toJSON();
            if(reo.getStatus() == RentalEnterpriseOrderConstant.ORDER_STATUS_98) return Result.failure("订单已被取消，请勿重复操作").toJSON();
            //记录关闭之前的订单状态
            reo.setCloseBeforeStatus(reo.getStatus());
            reo.setCloseDate(new Date());
            reo.setStatus(RentalEnterpriseOrderConstant.ORDER_STATUS_98);
            //设置子状态
            reo.setFlowOneStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setFlowTwoStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setFlowThreeStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setCancelSide(RentalEnterpriseOrderEnum.CancelSide.STATUS1.getStatus());
            reo.setCancelPersonId(dto.getUserId());
            reo.setCancelPerson(dto.getUserName());
            rentalEnterpriseOrderService.updateByPrimaryKeySelective(reo);
            return Result.success("订单取消成功").toJSON();
        }catch (Exception e){
            logger.error("取消订单异常",e);
            return Result.failure("取消订单异常").toJSON();
        }
    }

    /**
     * 拒绝订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/refuseOrder")
    public String refuseOrder(@RequestBody RentalEnterpriseOrderDTO dto){
        try{
            logger.info("subject:{},RentalEnterpriseOrderDTO:{}","拒绝订单信息", JsonUtil.gsonStr(dto));
            RentalEnterpriseOrder reo = rentalEnterpriseOrderService.selectByPrimaryKey(dto.getOrderId());
            if(reo == null) return Result.failure("订单丢失").toJSON();
            if(reo.getStatus() == RentalEnterpriseOrderConstant.ORDER_STATUS_99) return Result.failure("订单已被拒绝，请勿重复操作").toJSON();
            //记录关闭之前的订单状态
            reo.setCloseBeforeStatus(reo.getStatus());
            reo.setCloseDate(new Date());
            reo.setStatus(RentalEnterpriseOrderConstant.ORDER_STATUS_99);
            //设置子状态
            reo.setFlowOneStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setFlowTwoStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setFlowThreeStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setRefusePersonId(dto.getUserId());
            reo.setRefusePerson(dto.getUserName());
            rentalEnterpriseOrderService.updateByPrimaryKeySelective(reo);
            return Result.success("订单拒绝成功").toJSON();
        }catch (Exception e){
            logger.error("拒绝订单异常",e);
            return Result.failure("拒绝订单异常").toJSON();
        }
    }

    /**
     * 查询需求方订单列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getEnterpriseOrderListB2BD")
    public String getEnterpriseOrderListB2BD(@RequestBody RentalEnterpriseOrderDTO dto){
        try{
            logger.info("subject:{},RentalEnterpriseOrderDTO:{}","查询需求方订单列表信息", JsonUtil.gsonStr(dto));
            //处理页码
            dto.convertSQLForm();
            List<RentalEnterpriseOrderListB2BDVO> orderList = rentalEnterpriseOrderService.getEnterpriseOrderListB2BD(dto);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("rows", orderList);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("查询需求方订单列表异常",e);
            return Result.failure("查询需求方订单列表异常").toJSON();
        }
    }

    /**
     * 查询资源方订单列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getEnterpriseOrderListB2BR")
    public String getEnterpriseOrderListB2BR(@RequestBody RentalEnterpriseOrderDTO dto){
        try{
            logger.info("subject:{},RentalEnterpriseOrderDTO:{}","查询资源方订单列表信息", JsonUtil.gsonStr(dto));
            //处理页码
            dto.convertSQLForm();
            List<RentalEnterpriseOrderListB2BRVO> orderList = rentalEnterpriseOrderService.getEnterpriseOrderListB2BR(dto);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("rows", orderList);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("查询资源方订单列表异常",e);
            return Result.failure("查询资源方订单列表异常").toJSON();
        }
    }

    /**
     * 查询车商端租车订单详情
     * @param dto
     * @return
     */
    @PostMapping(value = "/getEnterpriseOrderDetailB2BD")
    public String getEnterpriseOrderDetailB2BD(@RequestBody RentalEnterpriseOrderDTO dto){
        try{
            logger.info("subject:{},RentalEnterpriseOrderDTO:{}","查询车商端租车订单详情信息", JsonUtil.gsonStr(dto));
            EnterpriseOrderDetailB2BDVO orderDetail = rentalEnterpriseOrderService.getEnterpriseOrderDetailB2BD(dto.getOrderId());
            if(orderDetail == null) return Result.failure("订单数据丢失").toJSON();
            return Result.success(orderDetail).toJSON();
        }catch (Exception e){
            logger.error("查询资源方订单列表异常",e);
            return Result.failure("查询资源方订单列表异常").toJSON();
        }
    }

    /**
     * 车商端出租订单详情
     * @param dto
     * @return
     */
    @PostMapping(value = "/getEnterpriseOrderDetailB2BR")
    public String getEnterpriseOrderDetailB2BR(@RequestBody RentalEnterpriseOrderDTO dto){
        try{
            logger.info("subject:{},RentalEnterpriseOrderDTO:{}","车商端出租订单详情", JsonUtil.gsonStr(dto));
            EnterpriseOrderDetailB2BRVO orderDetail = rentalEnterpriseOrderService.getEnterpriseOrderDetailB2BR(dto.getOrderId());
            if(orderDetail == null) return Result.failure("订单数据丢失").toJSON();
            return Result.success(orderDetail).toJSON();
        }catch (Exception e){
            logger.error("车商端出租订单详情异常",e);
            return Result.failure("车商端出租订单详情异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getPCRentalEnterpriseOrderList")
    public String getPCRentalEnterpriseOrderList(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},dto:{}","企业租车后台列表", JsonUtil.gsonStr(dto));
        try{
            List<PCRentalEnterpriseOrderVO> list = rentalEnterpriseOrderService.getPCRentalEnterpriseOrderList(dto);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(list));
            jMap.put("rows", list);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表异常",e);
            return Result.failure("企业租车后台列表异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:查询初始化参数
     * @return
     */
    @PostMapping(value = "/getPCRentalEnterpriseOrderInit")
    public String getPCRentalEnterpriseOrderInit(){
        logger.info("subject:{}","企业租车后台列表:初始化参数");
        try{
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("brands", commCarSeriesService.getCarBrand());
            jMap.put("orderStatus", RentalEnterpriseOrderEnum.QueryStatusEnum.getPCQueryRentalStatusParam());
            logger.info("subject:{},jMap:{}","企业租车后台列表:初始化参数：数据集",JsonUtil.gsonStr(jMap));
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:初始化参数异常",e);
            return Result.failure("企业租车后台列表:初始化参数异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:车辆车型
     * @param dto
     * @return
     */
    @PostMapping(value = "/getCarSeries")
    public String getCarSeries(@RequestBody CommCarSeriesDTO dto){
        logger.info("subject:{},dto:{}"," 企业租车后台列表:车辆车型", JsonUtil.gsonStr(dto));
        try{
            List<RentalEnterpriseCarVO> rentalEnterpriseCarVOS = commCarSeriesService.getCarSeries(dto);
            logger.info("subject:{},rentalEnterpriseCarVOS:{}"," 企业租车后台列表:车辆车型数据集", JsonUtil.gsonStr(rentalEnterpriseCarVOS));
            return Result.success(rentalEnterpriseCarVOS).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:车辆车型异常",e);
            return Result.failure("查询车型异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:车辆车系
     * @param dto
     * @return
     */
    @PostMapping(value = "/getCarModel")
    public String getCarModel(@RequestBody CommCarSeriesDTO dto){
        logger.info("subject:{},dto:{}"," 企业租车后台列表:车辆车系", JsonUtil.gsonStr(dto));
        try{
            List<RentalEnterpriseCarVO> rentalEnterpriseCarVOS = commCarSeriesService.getCarModel(dto);
            logger.info("subject:{},rentalEnterpriseCarVOS:{}"," 企业租车后台列表:车辆车系数据集", JsonUtil.gsonStr(rentalEnterpriseCarVOS));
            return Result.success(rentalEnterpriseCarVOS).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:车辆车系异常",e);
            return Result.failure("查询车系异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:订单详情
     * @param dto
     * @return
     */
    @PostMapping(value = "/getPCRentalEnterpriseOrderId")
    public String getPCRentalEnterpriseOrderId(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},dto:{}","企业租车后台列表:订单详情", JsonUtil.gsonStr(dto));
        try{
            return rentalEnterpriseOrderService.getPCRentalEnterpriseOrderId(dto);
        }catch (Exception e){
            logger.error("企业租车后台列表:订单详情异常",e);
            return Result.failure("订单详情异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:上传订单资料
     * @param rentalOrderFile
     * @return
     */
    @PostMapping(value = "/insertPCRentalEnterpriseOrderFile")
    public String insertPCRentalEnterpriseOrderFile(@RequestBody RentalOrderFile rentalOrderFile){
        logger.info("subject:{},rentalOrderFile:{}","企业租车后台列表:上传订单资料", JsonUtil.gsonStr(rentalOrderFile));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(rentalOrderFile.getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.insertPCRentalEnterpriseOrderFile(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }

            return rentalOrderFileService.insert(rentalOrderFile) > 0 ? Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:上传订单资料异常",e);
            return Result.failure("上传订单资料异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:查询订单资料
     * @param rentalOrderFile
     * @return
     */
    @PostMapping(value = "/getRentalOrderFiles")
    public String getRentalOrderFiles(@RequestBody RentalOrderFile rentalOrderFile){
        logger.info("subject:{},rentalOrderFile:{}","企业租车后台列表:查询订单资料", JsonUtil.gsonStr(rentalOrderFile));
        try{
            List<RentalOrderFileVO> rentalOrderFiles = rentalOrderFileService.getRentalOrderFiles(rentalOrderFile);
            logger.info("subject:{},rentalOrderFiles:{}","企业租车后台列表:查询订单资料数据集", JsonUtil.gsonStr(rentalOrderFiles));
            return Result.success(rentalOrderFiles).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:查询订单资料异常",e);
            return Result.failure("查询订单资料异常").toJSON();
        }
    }



    /**
     * 企业租车后台列表:删除订单资料
     * @param rentalOrderFile
     * @return
     */
    @PostMapping(value = "/deleteRentalEnterpriseOrderFile")
    public String deleteRentalEnterpriseOrderFile(@RequestBody RentalOrderFile rentalOrderFile){
        logger.info("subject:{},rentalOrderFile:{}","企业租车后台列表:删除订单资料", JsonUtil.gsonStr(rentalOrderFile));
        try{
            return rentalOrderFileService.updateByPrimaryKey(rentalOrderFile) > 0 ? Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:删除订单资料异常",e);
            return Result.failure("删除订单资料异常").toJSON();
        }
    }


    /**
     * 企业租车后台列表:已交保证金
     * @param dto
     * @return
     */
    @PostMapping(value = "/paymentDeposit")
    public String paymentDeposit(@RequestBody RentalDepositDTO dto){
        logger.info("subject:{},dto:{}","企业租车后台列表:已交保证金", JsonUtil.gsonStr(dto));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(dto.getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.paymentDeposit(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalDepositService.paymentDeposit(dto);
            return Result.success("成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error("企业租车后台列表:缴纳交保证金异常",se.getMessage());
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:已交保证金异常",e);
            return Result.failure("缴纳交保证金异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:保证金已退还
     * @param dto
     * @return
     */
    @PostMapping(value = "/refundDeposit")
    public String refundDeposit(@RequestBody RentalDepositDTO dto){
        logger.info("subject:{},dto:{}","企业租车后台列表:保证金已退还", JsonUtil.gsonStr(dto));
        try{
            rentalDepositService.refundDeposit(dto);
            return Result.success("成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error("企业租车后台列表:保证金已退还异常",se.getMessage());
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:保证金已退还异常",e);
            return Result.failure("保证金已退还异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:录入租金支付信息
     * @return
     */
    @PostMapping(value = "/insertRentPayment")
    public String insertRentPayment(@RequestBody RentalFeeVoucher record){
        logger.info("subject:{},record:{}","企业租车后台列表:录入租金支付信息", JsonUtil.gsonStr(record));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(record.getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.insertRentPayment(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalDepositService.insertRentPayment(record);
            return Result.success("成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error("企业租车后台列表:录入租金支付信息异常",se.getMessage());
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:录入租金支付信息异常",e);
            return Result.failure("录入租金支付信息异常").toJSON();
        }
    }


    /**
     * 企业租车后台列表:录入出车信息
     * @return
     */
    @PostMapping(value = "/insertRentalCheckComeCar")
    public String insertRentalCheckComeCar(@RequestBody List<RentalCheckCarDTO> list){
        logger.info("subject:{},list:{}","企业租车后台列表:录入出车信息", JsonUtil.gsonStr(list));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(list.get(0).getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.insertRentalCheckComeCar(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalCheckCarService.insertRentalCheckComeCar(list);
            return Result.success("成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error("企业租车后台列表:录入出车信息异常",se.getMessage());
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:录入出车信息异常",e);
            return Result.failure("录入出车信息异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:录入租金转账信息
     * @return
     */
    @PostMapping(value = "/insertRentTransfer")
    public String insertRentTransfer(@RequestBody RentalFeeVoucher record){
        logger.info("subject:{},record:{}","企业租车后台列表:录入租金转账信息", JsonUtil.gsonStr(record));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(record.getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.insertRentTransfer(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalDepositService.insertRentTransfer(record);
            return Result.success("成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error("企业租车后台列表:录入租金转账信息异常",se.getMessage());
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:录入租金转账信息异常",e);
            return Result.failure("录入租金转账信息异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:获取还车信息
     * @param dto
     * @return
     */
    @PostMapping(value = "/getRentalCheckRepayCar")
    public String getRentalCheckRepayCar(@RequestBody RentalCheckCarDTO dto){
        logger.info("subject:{},dto:{}","企业租车后台列表:获取还车还车信息", JsonUtil.gsonStr(dto));
        try{
            List<RentalCheckCar> rentalCheckCars = rentalCheckCarService.getRentalCheckCar(dto);
            logger.info("subject:{},rentalCheckCars:{}"," 外部订单:订单id查询车辆信息数据集",JsonUtil.gsonStr(rentalCheckCars));
            return Result.success(RentalCheckCarOderIdVO.getRentalCheckCarOderIdVOs(rentalCheckCars)).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:获取还车还车信息异常",e);
            return Result.failure("获取还车还车信息异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:录入还车信息
     * @return
     */
    @PostMapping(value = "/insertRentalCheckRepayCar")
    public String insertRentalCheckRepayCar(@RequestBody List<RentalCheckCarDTO> list){
        logger.info("subject:{},list:{}","企业租车后台列表:录入还车信息", JsonUtil.gsonStr(list));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(list.get(0).getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.insertRentalCheckRepayCar(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalCheckCarService.insertRentalCheckRepayCar(list);
            return Result.success("成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error("企业租车后台列表:录入还车信息异常",se.getMessage());
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:录入还车信息异常",e);
            return Result.failure("录入还车信息异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:录入结算信息
     * @return
     */
    @PostMapping(value = "/insertEnterpriseSettlement")
    public String insertEnterpriseSettlement(@RequestBody RentalEnterpriseSettlement rentalEnterpriseSettlement){
        logger.info("subject:{},rentalEnterpriseSettlement:{}","企业租车后台列表:录入结算信息", JsonUtil.gsonStr(rentalEnterpriseSettlement));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(rentalEnterpriseSettlement.getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.insertEnterpriseSettlement(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            RentalEnterpriseSettlementDTO dto = new RentalEnterpriseSettlementDTO();
            dto.setOrderId(rentalEnterpriseSettlement.getOrderId());
            if(rentalEnterpriseSettlementService.getEnterpriseSettlement(dto) != null)return Result.failure("租车结算信息已经存在").toJSON();
            rentalEnterpriseSettlementService.insertEnterpriseSettlement(rentalEnterpriseSettlement);
            return Result.success("成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error("企业租车后台列表:录入结算信息异常",se.getMessage());
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:录入结算信息异常",e);
            return Result.failure("录入结算信息异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:录入押金退还信息
     * @return
     */
    @PostMapping(value = "/insertDepositRefund")
    public String insertDepositRefund(@RequestBody List<RentalFeeVoucherDTO> list){
        logger.info("subject:{},list:{}","企业租车后台列表:录入押金退还信息", JsonUtil.gsonStr(list));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(list.get(0).getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.insertDepositRefund(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalFeeVoucherService.batchInsert(list);
            return Result.success("成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error("企业租车后台列表:录入押金退还信息异常",se.getMessage());
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:录入押金退还信息异常",e);
            return Result.failure("录入押金退还信息异常").toJSON();
        }
    }

    /**
     * 获取节假日配置
     * @return
     */
    @PostMapping(value = "/getHoliday")
    public String getHoliday(){
        try{
            Map<String, Object> holidayMap = rentalEnterpriseOrderService.getHoliday();
            if(holidayMap == null || holidayMap.isEmpty()) return Result.failure("获取节假日配置失败").toJSON();
            return Result.success(holidayMap).toJSON();
        }catch (Exception e){
            logger.error("获取节假日配置异常",e);
            return Result.failure("获取节假日配置异常").toJSON();
        }
    }

    /**
     * 手动解绑支付宝
     * @param dto
     * @return
     */
    @PostMapping(value = "/unfreezeAlipay")
    public String unfreezeAlipay(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},list:{}","手动解绑支付宝", JsonUtil.gsonStr(dto));
        try{
            Result result = rentalDepositService.unfreezeAlipay(dto);
            if(ResultConstant.RESULT_CODE_SUCCESS != result.getCode()) return result.toJSON();
            return Result.success("支付宝解绑成功").toJSON();
        }catch (Exception e){
            logger.error("解绑支付宝异常",e);
            return Result.failure("解绑支付宝异常").toJSON();
        }
    }

    /**
     * 确认订单（资源方）
     * @param dto
     * @return
     */
    @PostMapping(value = "/confirmOrder")
    public String confirmOrder(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},list:{}","确认订单（资源方）", JsonUtil.gsonStr(dto));
        try{
            Result result = rentalEnterpriseOrderService.confirmOrder(dto);
            if(ResultConstant.RESULT_CODE_SUCCESS != result.getCode()) return result.toJSON();
            return Result.success("确认订单成功").toJSON();
        }catch (Exception e){
            logger.error("确认订单异常",e);
            return Result.failure("确认订单异常").toJSON();
        }
    }

    /**
     * 获取发布信息
     * @param dto
     * @return
     */
    @PostMapping(value = "/getPublishInfo")
    public String getPublishInfo(@RequestBody RentalCarPublishDTO dto){
        logger.info("subject:{},list:{}","获取发布信息", JsonUtil.gsonStr(dto));
        try{
            PublishInfoVO publishInfoVO = rentalCarPublishService.getPublishInfo(dto);
            if(publishInfoVO == null) return Result.failure("发布信息不存在").toJSON();
            return Result.success(publishInfoVO).toJSON();
        }catch (Exception e){
            logger.error("获取发布信息异常", e);
            return Result.failure("获取发布信息异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:上传资料列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getRentalEnterpriseOrderFile")
    public String getRentalEnterpriseOrderFile(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},dto:{}","企业租车后台列表:上传资料", JsonUtil.gsonStr(dto));
        try{
            List<PCRentalEnterpriseDetailsFileVO> rentalEnterpriseDetailsFileVOS = rentalOrderFileService.getRentalEnterpriseOrderFile(dto.getOrderId());
            logger.info("subject:{},rentalEnterpriseDetailsFileVOS:{}","企业租车后台列表:上传资料数据集", JsonUtil.gsonStr(rentalEnterpriseDetailsFileVOS));
            return Result.success(rentalEnterpriseDetailsFileVOS).toJSON();
        }catch (Exception e){
            logger.error("上传资料列表异常", e);
            return Result.failure("上传资料列表异常").toJSON();
        }
    }

    /**
     * 企业租车后台列表:获取已发布车辆
     * @param rentalCar
     * @return
     */
    @PostMapping(value = "/getRentalCar")
    public String getRentalCar(@RequestBody RentalCar rentalCar){
        logger.info("subject:{},dto:{}","企业租车后台列表:获取已发布车辆", JsonUtil.gsonStr(rentalCar));
        try{
            List<RentalCar> rentalCars = rentalCarService.getRentalCar(rentalCar);
            if(rentalCars == null || rentalCars.isEmpty())return Result.failure("查询企业信息为空").toJSON();
            rentalCar.setStoreId(null);
            rentalCar.setCompanyId(rentalCars.get(0).getCompanyId());
            List<RentalCarVO> rentalCarVOS = RentalCarVO.getRentalCarVO(rentalCarService.getRentalCar(rentalCar));
            logger.info("subject:{},rentalCarVOS:{}","企业租车后台列表:获取已发布车辆：数据集",JsonUtil.gsonStr(rentalCarVOS));
            return Result.success(rentalCarVOS).toJSON();
        }catch (Exception e){
            logger.error("企业租车后台列表:获取已发布车辆异常", e);
            return Result.failure("获取已发布车辆异常").toJSON();
        }
    }
}
