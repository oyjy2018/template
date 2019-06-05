package com.ydc.service.order.controller;

import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalEnterpriseOrder;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import com.ydc.service.order.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  押金
 */
@RestController
@RequestMapping(value = "/deposit")
public class DepositController {

    private final Logger logger = LogManager.getLogger(DepositController.class);

    @Autowired
    RentalOrderService rentalOrderService;
    @Autowired
    RentalEnterpriseOrderService rentalEnterpriseOrderService;
    @Autowired
    DepositService depositService;
    @Autowired
    RentalPayWatercourseService rentalPayWatercourseService;
    @Autowired
    RentalDepositService rentalDepositService;


    /**
     * 新增租赁押金
     * @param rentalDeposit
     * @return
     */
    @PostMapping(value = "/addRentalDeposit")
    public String addRentalDeposit(@RequestBody RentalDeposit rentalDeposit){
        logger.info("subject:{},rentalDeposit:{}","新增租赁押金信息", JsonUtil.gsonStr(rentalDeposit));
        //判断是否已经生成初始押金记录
        RentalDeposit param = new RentalDeposit();
        param.setRentCarOrderNo(rentalDeposit.getRentCarOrderNo());
        List<RentalDeposit> rentalDepositList = rentalDepositService.selectRentalDeposit(param);
        if(rentalDepositList != null && rentalDepositList.size() > 0) return Result.success(rentalDepositList.get(0)).toJSON();

        RentalEnterpriseOrder reo = rentalEnterpriseOrderService.selectByPrimaryKey(rentalDeposit.getOrderId());
        if(reo.getStatus().intValue() == RentalEnterpriseOrderEnum.OrderStatus.STATUS20.getRealStatus().intValue()
                && reo.getFlowOneStatus().intValue() == RentalEnterpriseOrderEnum.OrderStatus.STATUS20.getFlowOneStatus().intValue()){
            Integer id = depositService.insert(rentalDeposit);
            if(id == 0){
                logger.error("新增租赁押金信息失败");
                return Result.failure("新增租赁押金信息失败").toJSON();
            }
            return Result.success(rentalDeposit).toJSON();
        }else{
            return Result.failure("当前环节不可进行保证金预授权").toJSON();
        }
    }

    /**
     * 获取订单押金
     * @param orderId
     * @param type 押金类型 1：租车冻结 2违章冻结
     * @return
     */
    @PostMapping(value = "/order/get")
    public Result<HashMap> getOrderDeposit(@RequestParam(value = "orderId") Integer orderId,
                                  @RequestParam(value = "type") Integer type ){
        logger.info("subject:{}","查询订单押金");
        try{
            if (StringUtil.isEmpty(orderId) || StringUtil.isEmpty(type)){
                logger.info("参数错误  orderId");
                return Result.failure("参数错误");
            }
            RentalOrder rentalOrder= rentalOrderService.getRentalOrderByOrderId(orderId);
            if ( null == rentalOrder){
                return Result.failure("订单不存在");
            }
            RentalDeposit rentalDeposit= depositService.queryRentalDepositByOrderAndType(orderId, type,RentalDepositEnum.OrderType.STATUS1.getStatus());
            //这里只会查询出 支付押金的流水
            RentalPayWatercourse rentalPayWatercourse=rentalPayWatercourseService.getRentalPayWatercourseByOrderNoAndDepositType(rentalOrder.getOrderNo(),type);
            if ( null == rentalDeposit){
                return Result.failure("查看押金错误");
            }
            HashMap<String,Object> resultMap=new HashMap<>();
            resultMap.put("rentalOrder",rentalOrder);
            resultMap.put("rentalDeposit",rentalDeposit);
            resultMap.put("rentalPayWatercourse",rentalPayWatercourse);
            return Result.success(resultMap);
        }catch (Exception e){
            logger.error("获取订单押金异常",e);
            return Result.failure();
        }
    }


    /**
     * 回调 押金冻结
     * @param aliPayFundAuthNotifyDTO
     * @return
     */
    @PostMapping(value = "/freeze/update")
    public Result freezeDeposit(@RequestBody PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO){
        if (null == aliPayFundAuthNotifyDTO || null==aliPayFundAuthNotifyDTO.getDepositType()){
            return Result.failure("参数错误");
        }
        //处理订单押金
        Result result= depositService.freezeOrderDeposit(aliPayFundAuthNotifyDTO);
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS
                && aliPayFundAuthNotifyDTO.getDepositType().intValue()==RentalDepositConstant.DEPOSIT_TYPE_1){
            //更新订单
            Integer orderId= (Integer) result.getData();
            rentalOrderService.updateConsentAuthorization(orderId,RentalOrderStatusConstant.RENTAL_ORDER_FLOW_STATUS_100);
        }
        return result;
    }

    /**
     * 回调 保证金冻结(企业租车)
     * @param aliPayFundAuthNotifyDTO
     * @return
     */
    @PostMapping(value = "/enterprise/freeze/update")
    public Result freezeEnterpriseDeposit(@RequestBody PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO){
        logger.info("subject:{}","回调 保证金冻结(企业租车)");
        if (null == aliPayFundAuthNotifyDTO || null==aliPayFundAuthNotifyDTO.getDepositType()){
            return Result.failure("参数错误");
        }
        //处理订单押金
        Result result= depositService.freezeOrderDeposit(aliPayFundAuthNotifyDTO);
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS
                && aliPayFundAuthNotifyDTO.getDepositType().intValue()==RentalDepositConstant.DEPOSIT_TYPE_1){
            System.out.println(result.toJSON());
            //更新订单
            Integer orderId= (Integer) result.getData();
            rentalEnterpriseOrderService.updateAuthorizationStatus(orderId, RentalOrderStatusConstant.RENTAL_ORDER_FLOW_STATUS_100);
        }
        return result;
    }

    /**
     *  手动押金冻结
     */
    @PostMapping(value = "/freeze/manual/update")
    public Result manualOrderDeposit(@RequestBody ManualFreezeRentalDepositDTO manualFreezeRentalDepositDTO) {
        if (null == manualFreezeRentalDepositDTO || null==manualFreezeRentalDepositDTO.getDepositType()){
            return Result.failure("参数错误");
        }
        //处理订单押金
        Result result= depositService.manualOrderDeposit(manualFreezeRentalDepositDTO);
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS
                && manualFreezeRentalDepositDTO.getDepositType().intValue()==RentalDepositConstant.DEPOSIT_TYPE_1){
            //更新订单
            Integer orderId= (Integer) result.getData();
            Integer status;
            if (manualFreezeRentalDepositDTO.getPaymentStatus().byteValue()==RentalDepositConstant.PAYMENT_STATUS_1.byteValue()){
                status =RentalOrderStatusConstant.RENTAL_ORDER_FLOW_STATUS_0;
            }else if(manualFreezeRentalDepositDTO.getPaymentStatus().byteValue()==RentalDepositConstant.PAYMENT_STATUS_2.byteValue()){
                status =RentalOrderStatusConstant.RENTAL_ORDER_FLOW_STATUS_100;
            }else {
                return result;
            }
            rentalOrderService.updateConsentAuthorization(orderId,status);
        }
        return result;
    }

    /**
     *  押金结算扣除
     */
    @PostMapping(value = "/pay/update")
    public Result<String> payDeposit(@RequestBody AlipayTradePayResponseDTO alipayTradePayResponseDTO){
        if (null == alipayTradePayResponseDTO || null==alipayTradePayResponseDTO.getDepositType()){
            return Result.failure("参数错误");
        }
        //处理订单押金
        Result result= depositService.payOrderDeposit(alipayTradePayResponseDTO);
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS ){
            RentalPayWatercourse rentalPayWatercourse= (RentalPayWatercourse) result.getData();
            rentalPayWatercourseService.insert(rentalPayWatercourse);
        }

        return Result.success("成功",alipayTradePayResponseDTO.getOutTradeNo());
    }

    /**
     *  解冻押金
     */
    @PostMapping(value = "/unfreezeDeposit")
    public Result<String> unfreezeDeposit(@RequestBody AlipayTradePayResponseDTO alipayTradePayResponseDTO){
        if (null == alipayTradePayResponseDTO || null==alipayTradePayResponseDTO.getDepositType()){
            return Result.failure("参数错误");
        }
        //处理订单押金
        Result result= depositService.unfreezeOrderDeposit(alipayTradePayResponseDTO);
        return Result.success("成功",alipayTradePayResponseDTO.getOutTradeNo());
    }



    /**
     * 获取订单押金
     * @param orderId
     * @param type
     * @return
     */
    @PostMapping(value = "/getRentalOrderDeposit")
    public RentalDeposit getRentalOrderDeposit(@RequestParam(value = "orderId") Integer orderId,
                                  @RequestParam(value = "type") Integer type ){
        logger.info("subject:{}","查询订单押金");
        try{
            return depositService.queryRentalDepositByOrderAndType(orderId, type,RentalDepositEnum.OrderType.STATUS1.getStatus());
        }catch (Exception e){
            logger.error("获取订单押金异常",e);
            return new RentalDeposit();
        }
    }

    /**
     * 获取插入的流水
     */
    @PostMapping(value = "/getRentalPayWatercourse")
    public List<RentalPayWatercourse> getRentalPayWatercourse(@RequestParam(value = "orderNo") String orderNo,
                                                              @RequestParam(value = "thirdParthOrderNo") String thirdParthOrderNo ){
        logger.info("subject:{}","查询订单押金");
        try{
            return  rentalPayWatercourseService.getRentalPayWatercourse( orderNo, thirdParthOrderNo);
        }catch (Exception e){
            logger.error("获取插入的流水",e);
            return null;
        }
    }

}
