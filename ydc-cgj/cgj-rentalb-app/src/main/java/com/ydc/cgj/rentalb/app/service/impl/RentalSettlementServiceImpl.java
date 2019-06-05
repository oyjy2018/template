package com.ydc.cgj.rentalb.app.service.impl;

import com.ydc.cgj.rentalb.app.common.UserUtil;
import com.ydc.cgj.rentalb.app.feignService.DepositFeignService;
import com.ydc.cgj.rentalb.app.feignService.RentalSettlementFeignService;
import com.ydc.cgj.rentalb.app.service.RentalOrderService;
import com.ydc.cgj.rentalb.app.service.RentalSettlementService;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.constant.rental.RentalPayWatercourseConstant;
import com.ydc.commom.constant.rental.RentalSettlementConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.AnnotationDealUtil;
import com.ydc.commom.util.BigDecimalUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalPayWatercourseDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import com.ydc.model.cgj.rental.RentalSettlement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author
 * @create 2018-11-26 16:41
 **/
@Service
public class RentalSettlementServiceImpl implements RentalSettlementService {

    private static final Logger logger = LogManager.getLogger(RentalSettlementService.class);
    @Autowired
    RentalOrderService rentalOrderService;
    @Autowired
    DepositFeignService depositFeignService;
    @Autowired
    RentalSettlementFeignService rentalSettlementFeignService;

    @Override
    public String getRentalSettlement(Integer orderId) {
        return rentalSettlementFeignService.getRentalSettlement(orderId);
    }

    @Override
    public Result saveRentalSettlement(RentalSettlementDTO rentalSettlementDTO) {
        if(rentalSettlementDTO == null)return Result.failure("录入结算信息为空");
        RentalSettlement rentalSettlement = getRentalSettlementByOrderId(rentalSettlementDTO.getOrderId());
        if(rentalSettlement != null){
            return Result.failure("结算信息已存在，请刷新页面");
        }
        Map<String, Object> checkResult = AnnotationDealUtil.validate(rentalSettlementDTO);
        logger.info("subject:{},e:{}","校验参数",checkResult);
        if(checkResult.get("result").equals(false)){
            return Result.failure(checkResult.get("message").toString());
        }
        RentalDeposit rentalDeposit = depositFeignService.getRentalOrderDeposit(rentalSettlementDTO.getOrderId(),(int)RentalDepositConstant.DEPOSIT_TYPE_1);
        if ( rentalDeposit== null){
            return Result.failure("查看错误，请联系系统管理员");
        }
        if(rentalSettlementDTO.getViolationPreAuthorizationAmount() == null ||
                rentalSettlementDTO.getViolationPreAuthorizationAmount().compareTo(BigDecimal.valueOf(0)) != 1){
            return Result.failure("违章预授权额不能小于等于0");
        }
        if(rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount() != null &&
                 rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount().compareTo(rentalSettlementDTO.getRentCarPreAuthorizationAmount()) == 1
                ){
            return Result.failure("扣除租车预授权额不能大于"+rentalSettlementDTO.getRentCarPreAuthorizationAmount());
        }
        //如果扣除租车预授权金额大于0，调用支付宝接口，进行扣款
        if(rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount() != null
                && rentalDeposit.getPaymentMode().intValue() == RentalDepositConstant.PAYMENT_MODE_1
                && rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount().compareTo(BigDecimal.valueOf(0.0)) == 1){
            //单独只新增流水
            logger.info("subject:{}","支付接口暂未接通");
        }

        User user = UserUtil.getUser();
        RentalOrder rentalOrder = rentalOrderService.getRentalOrderByOrderId(rentalSettlementDTO.getOrderId());
        rentalSettlementDTO.setMemberId(rentalOrder.getMemberId());
        BigDecimal payAmount =
                BigDecimalUtil.retBigDecimal(rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount())
                        .add(BigDecimalUtil.retBigDecimal(rentalSettlementDTO.getOfflineSettlementAmount()));
        if(payAmount.compareTo(rentalSettlementDTO.getShouldChargeTotal()) == 1){
            return Result.failure("支付金额不能大于应收总额");
        }
        if(rentalSettlementDTO.getShouldChargeTotal().compareTo(payAmount) == 1){
            rentalSettlementDTO.setRentalAuthStatus(RentalSettlementConstant.RENTAL_AUTH_STATUS_2);
        }else{
            rentalSettlementDTO.setRentalAuthStatus(RentalSettlementConstant.RENTAL_AUTH_STATUS_10);
        }
        Date date = new Date();
        rentalSettlementDTO.setSettleUserId(user.getId());
        rentalSettlementDTO.setSettleUserName(user.getUserName());
        rentalSettlementDTO.setStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        rentalSettlementDTO.setSettleStatus(RentalSettlementConstant.SETTLE_STATUS_2);
        rentalSettlementDTO.setSettleTime(date);
        rentalSettlementDTO.setCreateTime(date);
        rentalSettlementDTO.setCreateBy(user.getId());
        rentalSettlementDTO.setActualAmount(BigDecimalUtil.retBigDecimal(rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount())
                .add(BigDecimalUtil.retBigDecimal(rentalSettlementDTO.getOfflineSettlementAmount())));
        rentalSettlementDTO.setViolationPayroll(BigDecimal.valueOf(0.00));
        rentalSettlementDTO.setViolationAuthStatus(RentalSettlementConstant.VIOLATION_AUTH_STATUS_1);
        rentalSettlementDTO.setViolationAuthRefundAmount(BigDecimal.valueOf(0.00));

        //押金支付方式不等于芝麻
        List<RentalPayWatercourse> rentalPayWatercourseList = Lists.newArrayList();
        if(rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount() != null
//                && rentalDeposit.getPaymentMode().intValue() != RentalDepositConstant.PAYMENT_MODE_1
//                && rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount().compareTo(BigDecimal.valueOf(0.0)) == 1
                ){
            RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
            rentalPayWatercourse.setOrderId(rentalOrder.getId());
            rentalPayWatercourse.setMemberId(rentalOrder.getMemberId());
            rentalPayWatercourse.setOrderNo(rentalOrder.getOrderNo());
            rentalPayWatercourse.setOneselfSerialNumber(UUID.randomUUID().toString());
            rentalPayWatercourse.setPaymentMode(rentalDeposit.getPaymentMode());
            rentalPayWatercourse.setPayableAmount(rentalSettlementDTO.getShouldChargeTotal());
            rentalPayWatercourse.setActualAmount(rentalSettlementDTO.getDeductRentCarPreAuthorizationAmount());
            rentalPayWatercourse.setDepositType(RentalPayWatercourseConstant.DEPOSIT_TYPE_3);
            rentalPayWatercourse.setOperationUserId(user.getId());
            rentalPayWatercourse.setOperationUserName(user.getUserName());
            rentalPayWatercourse.setOperationTime(date);
            rentalPayWatercourse.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
            rentalPayWatercourse.setCreateBy(user.getId());
            rentalPayWatercourse.setCreateTime(date);
            rentalPayWatercourseList.add(rentalPayWatercourse);
        }

        if(rentalSettlementDTO.getOfflineSettlementAmount() != null
                && rentalSettlementDTO.getOfflineSettlementAmount().compareTo(BigDecimal.valueOf(0)) == 1){
            RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
            rentalPayWatercourse.setOrderId(rentalOrder.getId());
            rentalPayWatercourse.setMemberId(rentalOrder.getMemberId());
            rentalPayWatercourse.setOrderNo(rentalOrder.getOrderNo());
            rentalPayWatercourse.setOneselfSerialNumber(UUID.randomUUID().toString());
            rentalPayWatercourse.setPaymentMode(RentalPayWatercourseConstant.PAYMENT_MODE_3);
            rentalPayWatercourse.setPayableAmount(rentalSettlementDTO.getPayableAmount());
            rentalPayWatercourse.setActualAmount(rentalSettlementDTO.getOfflineSettlementAmount());
            rentalPayWatercourse.setDepositType(RentalPayWatercourseConstant.DEPOSIT_TYPE_3);
            rentalPayWatercourse.setOperationUserId(user.getId());
            rentalPayWatercourse.setOperationUserName(user.getUserName());
            rentalPayWatercourse.setOperationTime(date);
            rentalPayWatercourse.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
            rentalPayWatercourse.setCreateBy(user.getId());
            rentalPayWatercourse.setCreateTime(date);
            rentalPayWatercourseList.add(rentalPayWatercourse);
        }
        rentalSettlementDTO.setRentalPayWatercourseList(rentalPayWatercourseList);
        logger.info("subject:{},rentalSettlementDTO:{}","首次结算数据", JsonUtil.gsonStr(rentalSettlementDTO));
        Result result = rentalSettlementFeignService.saveRentalSettlement(rentalSettlementDTO);
        result.setData(rentalDeposit);
        return result;
    }

    @Override
    public String saveRentalPayWatercourse(RentalPayWatercourseDTO rentalPayWatercourseDTO) {
        Date date = new Date();
        RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
        rentalPayWatercourse.setOrderId(rentalPayWatercourseDTO.getOrderId());
        RentalOrder rentalOrder = rentalOrderService.getRentalOrderByOrderId(rentalPayWatercourseDTO.getOrderId());
        rentalPayWatercourse.setMemberId(rentalOrder.getMemberId());
        rentalPayWatercourse.setOrderNo(rentalOrder.getOrderNo());
        rentalPayWatercourse.setOneselfSerialNumber(UUID.randomUUID().toString());
        rentalPayWatercourse.setPaymentMode(RentalPayWatercourseConstant.PAYMENT_MODE_3);
        rentalPayWatercourse.setPayableAmount(rentalPayWatercourseDTO.getPayableAmount());
        rentalPayWatercourse.setActualAmount(rentalPayWatercourseDTO.getActualAmount());
        rentalPayWatercourse.setDepositType(RentalPayWatercourseConstant.DEPOSIT_TYPE_3);
        User user = UserUtil.getUser();
        rentalPayWatercourse.setOperationUserId(user.getId());
        rentalPayWatercourse.setOperationUserName(user.getUserName());
        rentalPayWatercourse.setOperationTime(date);
        rentalPayWatercourse.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        rentalPayWatercourse.setCreateBy(user.getId());
        rentalPayWatercourse.setCreateTime(date);
        return rentalSettlementFeignService.saveRentalPayWatercourse(rentalPayWatercourse);
    }

    @Override
    public RentalSettlement getRentalSettlementByOrderId(Integer orderId) {
        return rentalSettlementFeignService.getRentalSettlementByOrderId(orderId);
    }

    @Override
    public Result updateSettleAliPayDeposit(Integer orderId, Integer type) {
        return rentalSettlementFeignService.updateAliPaySettleDTO(orderId,type);
    }


}
