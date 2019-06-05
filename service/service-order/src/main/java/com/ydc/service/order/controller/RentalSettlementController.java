package com.ydc.service.order.controller;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.zhiMaCredit.AliPayFaceFundAuth;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.constant.rental.RentalDictionaryConstant;
import com.ydc.commom.constant.rental.RentalPayWatercourseConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalPayWatercourseDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalPayWatercourseVO;
import com.ydc.commom.view.vo.cgj.rental.RentalSettlement2VO;
import com.ydc.commom.view.vo.cgj.rental.RentalSettlementVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import com.ydc.model.cgj.rental.RentalSettlement;
import com.ydc.service.order.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 租车结算
 *
 * @author
 * @create 2018-11-26 14:56
 **/
@RestController
@RequestMapping(value = "/rentalSettlement")
public class RentalSettlementController {

    private final Logger logger = LogManager.getLogger(RentalSettlementController.class);

    @Autowired
    RentalSettlementService rentalSettlementService;
    @Autowired
    RentalPayWatercourseService rentalPayWatercourseService;
    @Autowired
    RentalDepositService rentalDepositService;
    @Autowired
    DictionaryDetailService dictionaryDetailService;
    @Autowired
    DepositService depositService;
    @Autowired
    RentalOrderService rentalOrderService;

    /**
     * 返回结算需要订单信息
     * @param
     * @return
     */
    @PostMapping(value = "/getRentalSettlement")
    public String getRentalSettlement(@RequestParam("orderId") Integer orderId){
        logger.info("subject:{},orderId:{}","返回结算需要订单信息",orderId);
        try{
            RentalSettlementVO rentalSettlementVO = rentalSettlementService.getRentalSettlement(orderId);
            rentalSettlementVO.setRealRentDays(DateUtil.longTimeToDay(rentalSettlementVO.getComeCarTime(),rentalSettlementVO.getRepayCarTime()));
            rentalSettlementVO.setOverdueDays(DateUtil.longTimeToDay(rentalSettlementVO.getAppointmentRepayCarTime(),rentalSettlementVO.getRepayCarTime()));
            //结算明细
            RentalSettlement2VO rentalSettlement2VO = rentalSettlementService.getRentalSettlement2VOByOrderId(orderId);
            if(rentalSettlement2VO != null){
                //结算支付流水
                List<RentalPayWatercourseVO> rentalPayWatercourseVOS = rentalPayWatercourseService.getPayWatercourseByOrderId(new RentalPayWatercourseDTO(orderId,RentalPayWatercourseConstant.DEPOSIT_TYPE_3));
                rentalSettlement2VO.setRentalPayWatercourseVOS(rentalPayWatercourseVOS);
            }
            rentalSettlementVO.setRentalSettlement2VO(rentalSettlement2VO);
            //违章预授权额
            RentalDeposit rentalDeposit = new RentalDeposit();
            rentalDeposit.setOrderId(orderId);
            rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS1.getStatus());
            rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_2);
            rentalDeposit.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
            List<RentalDeposit> rentalDeposits = rentalDepositService.selectRentalDeposit(rentalDeposit);
            if(rentalDeposits == null || rentalDeposits.isEmpty()){
                Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(RentalDictionaryConstant.RENTAL_VIOLATION_AUTHORIZATION_AMOUNT, RentalDictionaryConstant.AUTHORIZATION_AMOUNT)
                        .orElseGet(() -> dictionaryDetailService.getDictionaryDetailByDictKey(RentalDictionaryConstant.RENTAL_VIOLATION_AUTHORIZATION_AMOUNT, RentalDictionaryConstant.AUTHORIZATION_AMOUNT)));
                rentalSettlementVO.setViolationAuthPayableAmount(dic.isPresent() ? BigDecimal.valueOf(Double.valueOf(dic.get().getDictValue())) : BigDecimal.valueOf(3000));
            }else{
                rentalSettlementVO.setViolationAuthPayableAmount(rentalDeposits.get(0).getPayableAmount());
            }
            logger.info("subject:{},rentalSettlementVO:{}","返回结算需要订单信息",JsonUtil.gsonStr(rentalSettlementVO));
            return Result.success(rentalSettlementVO).toJSON();
        }catch (Exception e){
            logger.error("返回结算需要订单信息异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 首次结算信息入库
     * @param record
     * @return
     */
    @PostMapping(value = "/saveRentalSettlement")
    public Result saveRentalSettlement(@RequestBody RentalSettlementDTO record){
        logger.info("subject:{},record:{}","首次结算信息入库",JsonUtil.gsonStr(record));
        try{
           return rentalSettlementService.insert(record) > 0 ? Result.success("成功") :  Result.failure("失败");
        }catch (ServiceRuntimeException se){
            logger.error("subject:{}",se.getMessage());
            return Result.failure(se.getMessage());
        }catch (Exception e){
            logger.error("首次结算信息入库异常",e);
            return Result.failure();
        }
    }

    /**
     * 非结算信息入库
     * @param rentalPayWatercourse
     * @return
     */
    @PostMapping(value = "/saveRentalPayWatercourse")
    public String saveRentalPayWatercourse(@RequestBody RentalPayWatercourse rentalPayWatercourse){
        logger.info("subject:{},record:{}","非结算信息入库",JsonUtil.gsonStr(rentalPayWatercourse));
        try{
            return rentalPayWatercourseService.insert(rentalPayWatercourse) > 0 ? Result.success("成功").toJSON() :  Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("非结算信息入库异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 结算信息查询
     * @param orderId
     * @return
     */
    @PostMapping(value = "/getRentalSettlementByOrderId")
    public RentalSettlement getRentalSettlementByOrderId(@RequestParam("orderId") Integer orderId) {
        logger.info("subject:{},record:{}","订单查询结算信息",orderId);
        try{
            return rentalSettlementService.getRentalSettlementByOrderId(orderId);
        }catch (Exception e){
            logger.error("subject:{},e:{}","结算信息查询"+orderId,e);
            return new RentalSettlement();
        }
    }

    /**
     * 更新支付宝结算参数
     */
    @PostMapping(value = "/alipay/update")
    public Result updateAliPaySettleDTO(@RequestParam("orderId")Integer orderId,
                                    @RequestParam("depositType") Integer depositType){
        if (StringUtil.isEmpty(orderId) || StringUtil.isEmpty(depositType)){
            return  Result.failure("参数错误");
        }
        //查询押金
        RentalDeposit rentalDeposit= depositService.queryRentalDepositByOrderAndType(orderId, depositType,RentalDepositEnum.OrderType.STATUS1.getStatus());
        if( null==rentalDeposit
                ||StringUtil.isEmpty(rentalDeposit.getPaymentMode() )
                || rentalDeposit.getPaymentMode().compareTo(RentalDepositConstant.PAYMENT_MODE_1)!=0){
            logger.info("不是支付宝预授权");
            return Result.failure("不是支付宝预授权");
        }
        if (rentalDeposit.getPaymentStatus().compareTo(RentalDepositConstant.PAYMENT_STATUS_2)!=0){
            logger.info("订单不能结算");
            return  Result.failure("订单不能结算");
        }
        RentalOrder rentalOrder= rentalOrderService.getRentalOrderByOrderId(orderId);
        //查询流水 支付流水
        RentalPayWatercourse rentalPayWatercourse=rentalPayWatercourseService.getRentalPayWatercourseByOrderNoAndDepositType(rentalOrder.getOrderNo(),depositType);
        if (null == rentalPayWatercourse || StringUtil.isEmpty(rentalPayWatercourse.getPaymentMode())
                || rentalPayWatercourse.getPaymentMode().compareTo(RentalDepositConstant.PAYMENT_MODE_1)!=0){
            logger.info("没有支付流水");
            return Result.failure("没有支付流水");
        }
        //查询结算表
        RentalSettlement rentalSettlement= rentalSettlementService.getRentalSettlementByOrderId(orderId);
        if ( null== rentalSettlement){
            logger.info("订单还未结算");
            return  Result.failure("订单还未结算");
        }
        BigDecimal amount = null;
        BigDecimal unfreezeAmount=null;
        if(depositType.byteValue()==RentalDepositConstant.DEPOSIT_TYPE_1.byteValue()){
            amount=rentalSettlement.getRentCarPreAuthorizationAmount().subtract(rentalSettlement.getReturnRentCarPreAuthorizationAmount());
            unfreezeAmount=rentalSettlement.getRentCarPreAuthorizationAmount();
        }
        if(depositType.byteValue()==RentalDepositConstant.DEPOSIT_TYPE_2.byteValue()){
            amount=rentalSettlement.getViolationPayroll();
            unfreezeAmount=rentalSettlement.getViolationAuthRefundAmount();
        }
        if (rentalPayWatercourse.getActualAmount().compareTo(amount) <0){
            logger.info("退还金额大于支付金额");
            return Result.failure("退还金额大于支付金额");
        }
        Result result;
        if (BigDecimal.ZERO.compareTo(amount) != 0){
            //调用结算
            result= AliPayFaceFundAuth.createAlipayTradePayModel(rentalPayWatercourse.getOrderNo(),
                    StringUtil.bigDecimalToString(amount),
                    rentalPayWatercourse.getThirdParthOrderNo(),
                    rentalPayWatercourse.getPaymentAccount(),depositType.byteValue());
        }else if (BigDecimal.ZERO.compareTo(unfreezeAmount) != 0){
            //调用解冻
            result=  AliPayFaceFundAuth.createAlipayFundAuthOrderUnfreezeModel(rentalPayWatercourse.getOrderNo(),
                    StringUtil.bigDecimalToString(unfreezeAmount),
                    rentalPayWatercourse.getThirdParthOrderNo(),depositType.byteValue());
        }else {
            logger.info("调用支付宝参数错误 amount{ }，unfreezeAmount{}",amount,unfreezeAmount);
            return  Result.failure("金额错误");
        }

    /*    //处理订单押金
        Result result= depositService.payOrderDeposit(alipayTradePayResponseDTO);
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS ){
            RentalPayWatercourse rentalPayWatercourse1= (RentalPayWatercourse) result.getData();
            rentalPayWatercourseService.insert(rentalPayWatercourse1);
        }*/
        return result;
    }
}
