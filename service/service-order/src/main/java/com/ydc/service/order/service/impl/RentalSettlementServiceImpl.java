package com.ydc.service.order.service.impl;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.constant.rental.RentalDictionaryConstant;
import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalOrderStatusFourEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.rental.RentalOrderNoUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalSettlement2VO;
import com.ydc.commom.view.vo.cgj.rental.RentalSettlementVO;
import com.ydc.dao.cgj.rental.RentalDepositDao;
import com.ydc.dao.cgj.rental.RentalPayWatercourseDao;
import com.ydc.dao.cgj.rental.RentalSettlementDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalSettlement;
import com.ydc.service.order.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author
 * @create 2018-11-26 14:55
 **/
@Service
public class RentalSettlementServiceImpl implements RentalSettlementService {

    private final static Logger logger = LoggerFactory.getLogger(RentalSettlementService.class);

    @Autowired
    RentalSettlementDao rentalSettlementDao;
    @Autowired
    RentalPayWatercourseService rentalPayWatercourseService;
    @Autowired
    RentalDepositService rentalDepositService;
    @Autowired
    RentalPayWatercourseDao rentalPayWatercourseDao;
    @Autowired
    RentalOrderService rentalOrderService;
    @Resource
    RentalDepositDao rentalDepositDao;
    @Autowired
    DictionaryDetailService dictionaryDetailService;

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public int insert(RentalSettlementDTO record) {
        //新增结算表
        int rentalSettlementResult = rentalSettlementDao.insert(record);
        if(rentalSettlementResult <=0)
            throw new ServiceRuntimeException(Result.failure("新增结算失败").toJSON());
        //新增结算流水表
        record.getRentalPayWatercourseList().stream().forEach(item -> item.setSettlementId(record.getId()));
        //线下结算金额不为空
        Date date = new Date();
        if(record.getRentalPayWatercourseList() != null && record.getRentalPayWatercourseList().size() > 0){
            rentalPayWatercourseDao.batchInsert(record.getRentalPayWatercourseList());
            RentalDeposit rentalDeposit = new RentalDeposit();
            rentalDeposit.setOrderId(record.getOrderId());
            rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS1.getStatus());
            rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_2);
            rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_1);
            List<RentalDeposit> rentalDeposits= rentalDepositDao.selectRentalDeposit(rentalDeposit);
            logger.info("subject:{},rentalDeposit:{},rentalDeposits:{}","查询结算信息",JsonUtil.gsonStr(rentalDeposit),JsonUtil.gsonStr(rentalDeposits));
            if (null ==rentalDeposits && rentalDeposits.isEmpty()){
                throw new ServiceRuntimeException("结算失败");
            }
            rentalDeposit=rentalDeposits.get(0);
//            rentalDeposit.setRentCarOrderNo(UUID.randomUUID().toString());
//            rentalDeposit.setPaymentTime(date);
            rentalDeposit.setUpdateBy(record.getCreateBy());


            if (RentalDepositConstant.PAYMENT_MODE_1.compareTo(rentalDeposit.getPaymentMode())!=0){
                //不是支付宝授权
                rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_3);
                rentalDeposit.setActualRefundTime(new Date());
                rentalDeposit.setPayableRefundAmount(record.getReturnRentCarPreAuthorizationAmount());
                rentalDeposit.setActualRefundAmount(record.getReturnRentCarPreAuthorizationAmount());
            }else {
                rentalDeposit.setPayableRefundAmount(record.getReturnRentCarPreAuthorizationAmount());
            }
            logger.info("subject:{},deductRentCarPreAuthorizationAmount:{},rentalDeposit:{}","首次结算信息",record.getDeductRentCarPreAuthorizationAmount(),JsonUtil.gsonStr(rentalDeposit));
            rentalDepositService.updatePaymentStatus(rentalDeposit);

        }
        //新增违章租赁押金
        RentalDeposit rentalDeposit = new RentalDeposit();
        rentalDeposit.setOrderId(record.getOrderId());
        rentalDeposit.setMemberId(record.getMemberId());
        rentalDeposit.setPayableAmount(record.getViolationPreAuthorizationAmount());
        rentalDeposit.setViolationOrderNo(RentalOrderNoUtil.getOrderNo());
        rentalDeposit.setSerialNum(UUID.randomUUID().toString());
        Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(RentalDictionaryConstant.RENTAL_VIOLATION_AUTHORIZATION_AMOUNT, RentalDictionaryConstant.AUTHORIZATION_AMOUNT)
                .orElseGet(() -> dictionaryDetailService.getDictionaryDetailByDictKey(RentalDictionaryConstant.RENTAL_VIOLATION_AUTHORIZATION_AMOUNT, RentalDictionaryConstant.AUTHORIZATION_AMOUNT)));
        rentalDeposit.setPaymentMode(dic.isPresent() ? StringUtil.isEmpty(dic.get().getRemark2()) ? RentalDepositConstant.PAYMENT_MODE_1 : Integer.valueOf(dic.get().getRemark2()).byteValue() : RentalDepositConstant.PAYMENT_MODE_1);
        rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_2);
        rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_1);
        rentalDeposit.setCreateTime(date);
        rentalDeposit.setCreateBy(record.getCreateBy());
        rentalDeposit.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        int rentalDepositResult = rentalDepositService.insert(rentalDeposit);
        if(rentalDepositResult <=0)
            throw new ServiceRuntimeException("新增违章租赁押金失败");
        //更新订单表状态等于结算
        UpdateRentalOrderDTO updateRentalOrderDTO = new UpdateRentalOrderDTO();
        updateRentalOrderDTO.setOrderId(record.getOrderId());
        updateRentalOrderDTO.setUserId(record.getCreateBy());
        updateRentalOrderDTO.setStatus(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_4);
        //根据应还总额和实还总额判断结算状态，用flowOneStatus
        if(record.getShouldChargeTotal().compareTo(record.getActualAmount()) != 0){
            updateRentalOrderDTO.setFlowOneStatus(RentalOrderStatusFourEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_1.getFlowOneStatus());
        }else{
            updateRentalOrderDTO.setFlowOneStatus(RentalOrderStatusFourEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.getFlowOneStatus());
        }
        int rentalOrderResult = rentalOrderService.updateRentalOrderStatus(updateRentalOrderDTO);
        if(rentalOrderResult <=0)
            throw new ServiceRuntimeException("更新订单状态失败");
        return 1 ;
    }

    @Override
    public RentalSettlementVO getRentalSettlement(Integer orderId) {
        return rentalSettlementDao.getRentalSettlement(orderId);
    }

    @Override
    public RentalSettlement getRentalSettlementByOrderId(Integer orderId) {
        return rentalSettlementDao.getRentalSettlementByOrderId(orderId);
    }

    @Override
    public int updateByPrimaryKey(RentalSettlement record) {
        return rentalSettlementDao.updateByPrimaryKey(record);
    }

    @Override
    public RentalSettlement2VO getRentalSettlement2VOByOrderId(Integer orderId) {
        return rentalSettlementDao.getRentalSettlement2VOByOrderId(orderId);
    }
}
