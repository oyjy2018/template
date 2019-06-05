package com.ydc.service.order.service.impl;

import com.ydc.beans.zhiMaCredit.AliPayFaceFundAuth;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.enums.common.CommonEnum;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import com.ydc.dao.cgj.rental.*;
import com.ydc.model.cgj.rental.*;
import com.ydc.service.order.service.RentalDepositService;
import com.ydc.service.order.service.RentalPayWatercourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author
 * @create 2018-11-24 9:23
 **/
@Service
public class RentalDepositServiceImpl implements RentalDepositService {

    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private final Logger logger = LogManager.getLogger(RentalDepositService.class);

    @Autowired
    RentalDepositDao rentalDepositDao;
    @Autowired
    RentalEnterpriseOrderDao rentalEnterpriseOrderDao;
    @Autowired
    RentalFeeVoucherDao rentalFeeVoucherDao;
    @Autowired
    RentalPayWatercourseService rentalPayWatercourseService;
    @Autowired
    RentalCarPublishDao rentalCarPublishDao;
    @Autowired
    RentalCarPublishNumDao rentalCarPublishNumDao;

    @Override
    public int insert(RentalDeposit record) {
        return rentalDepositDao.insert(record);
    }

    @Override
    public int updatePaymentStatus(RentalDeposit rentalDeposit) {
        return rentalDepositDao.updatePaymentStatus(rentalDeposit);
    }

    @Override
    public List<RentalDeposit> selectRentalDeposit(RentalDeposit record) {
        return rentalDepositDao.selectRentalDeposit(record);
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public void paymentDeposit(RentalDepositDTO dto){
        //软删未支付押金单（存在的删除） 避免出现重复押金单
        rentalDepositDao.updateDeleteStatusByOrderIdAndOrederTypeAndDepositTypeAndPaymentStatus(CommonEnum.DeleteStatusEnum.STATUS_0.getCode(),dto.getOrderId(),RentalDepositEnum.OrderType.STATUS2.getStatus(),RentalDepositConstant.DEPOSIT_TYPE_1,RentalDepositConstant.PAYMENT_STATUS_1);
        //验证是否存在保证金信息
        RentalDeposit rentalDeposit = new RentalDeposit();
//        rentalDeposit.setOrderId(dto.getOrderId());
//        rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS2.getStatus());
//        rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_1);
//        rentalDeposit.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
//        List<RentalDeposit> rentalDeposits = selectRentalDeposit(rentalDeposit);
//        if(rentalDeposits != null && rentalDeposits.size() > 0){
//            throw new RuntimeException("订单已存在保证金记录");
//        }
        int resultCount;
        Date date = new Date();
        rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS2.getStatus());
        rentalDeposit.setMemberId(dto.getDemandSideId());
        rentalDeposit.setSerialNum(UUID.randomUUID().toString());
        rentalDeposit.setOrderId(dto.getOrderId());
        rentalDeposit.setRentCarOrderNo(dto.getRentCarOrderNo());
        rentalDeposit.setPaymentMode(dto.getPaymentMode().byteValue());
        rentalDeposit.setActualAmount(dto.getActualAmount());
        rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_1);
        rentalDeposit.setPaymentTime(date);
        rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_2);
        rentalDeposit.setCreateTime(date);
        rentalDeposit.setCreateBy(dto.getUserId());
        rentalDeposit.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        rentalDeposit.setPayVoucherImgName(dto.getPayVoucherImgName());
        rentalDeposit.setPayVoucherImgUrl(dto.getPayVoucherImgUrl());
        logger.info("subject:{},rentalCarPublish:{}","新增保证金",JsonUtil.gsonStr(rentalDeposit));
        resultCount = insert(rentalDeposit);
        if(resultCount == 0)throw new ServiceRuntimeException("新增保证金失败");
        RentalEnterpriseOrder rentalEnterpriseOrder = new RentalEnterpriseOrder();
        rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS30.getRealStatus());
        rentalEnterpriseOrder.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS30.getFlowOneStatus());
        rentalEnterpriseOrder.setFlowTwoStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS30.getFlowTwoStatus());
        rentalEnterpriseOrder.setFlowThreeStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS30.getFlowThreeStatus());
        rentalEnterpriseOrder.setId(dto.getOrderId());
        rentalEnterpriseOrder.setUpdateBy(dto.getUserId());
        resultCount = rentalEnterpriseOrderDao.updateByPrimaryKeySelective(rentalEnterpriseOrder);
        if(resultCount == 0)throw new ServiceRuntimeException("更新租车订单失败");
        //更新发布车辆
        rentalEnterpriseOrder = rentalEnterpriseOrderDao.selectByPrimaryKey(dto.getOrderId());
        Integer reserveCount = StringUtil.isEmpty(rentalEnterpriseOrder.getReserveCount()) ? 0 : rentalEnterpriseOrder.getReserveCount();
        RentalCarPublish rentalCarPublish = rentalCarPublishDao.selectByPrimaryKey(rentalEnterpriseOrder.getResourceInfoId());
        if(rentalCarPublish == null)throw new ServiceRuntimeException("外部车辆发布信息不存在");
        Integer publishNum = StringUtil.isEmpty(rentalCarPublish.getPublishNum()) ? 0 : rentalCarPublish.getPublishNum() ;
        rentalCarPublish.setPublishNum(reserveCount > publishNum ? 0 : (publishNum - reserveCount));
        rentalCarPublish.setUpdateTime(date);
        rentalCarPublish.setUpdateBy(dto.getUserId());
        logger.info("subject:{},rentalCarPublish:{}","更新车辆发布",JsonUtil.gsonStr(rentalCarPublish));
        resultCount = rentalCarPublishDao.updateByPrimaryKey(rentalCarPublish);
        if(resultCount == 0)throw new ServiceRuntimeException("更新车辆发布失败");
        //更新车辆发布明细表
        RentalCarPublishNum rentalCarPublishNum = new RentalCarPublishNum();
        rentalCarPublishNum.setPublishId(rentalEnterpriseOrder.getResourceInfoId());
        rentalCarPublishNum.setCarLevel(rentalEnterpriseOrder.getCarLevel());
        rentalCarPublishNum.setCarBrand(rentalEnterpriseOrder.getCarBrand());
        rentalCarPublishNum.setCarSeries(rentalEnterpriseOrder.getCarSeries());
        rentalCarPublishNum.setCarModel(rentalEnterpriseOrder.getCarModel());
        List<RentalCarPublishNum> rentalCarPublishNums = rentalCarPublishNumDao.getRentalCarPublishNum(rentalCarPublishNum);
        if(rentalCarPublishNums == null || rentalCarPublishNums.isEmpty())throw new ServiceRuntimeException("车辆发布明细为空");
        rentalCarPublishNum = rentalCarPublishNums.get(0);
        Integer carNum = StringUtil.isEmpty(rentalCarPublishNum.getCarNum()) ? 0 : rentalCarPublishNum.getCarNum() ;
        rentalCarPublishNum.setCarNum(reserveCount > carNum ? 0 : (carNum - reserveCount));
        rentalCarPublishNumDao.updateByPrimaryKey(rentalCarPublishNum);
        if(resultCount == 0)throw new ServiceRuntimeException("更新车辆发布明细失败");
    }

    @Override
    public void refundDeposit(RentalDepositDTO dto){
        RentalDeposit rentalDeposit = new RentalDeposit();
        rentalDeposit.setOrderId(dto.getOrderId());
        rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS2.getStatus());
        rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_1);
        rentalDeposit.setPaymentStatus(Byte.parseByte(RentalDepositEnum.PaymentStatus.status2.getStatus().toString())); //查询条件加入支付状态
        rentalDeposit.setDeleteStatus(Byte.parseByte(CommonEnum.DeleteStatusEnum.STATUS_1.getCode()+"")); //加状态
        List<RentalDeposit> rentalDeposits= rentalDepositDao.selectRentalDeposit(rentalDeposit);
        if(rentalDeposits == null)throw new RuntimeException("查询保证金失败");
        rentalDeposit = rentalDeposits.get(0);
        if(rentalDeposit.getPaymentMode() == RentalDepositConstant.PAYMENT_MODE_1.intValue()){
            throw new RuntimeException("授权方式为芝麻,不能进行手动退还");
        }
        if(rentalDeposit.getPaymentStatus().intValue() != RentalDepositConstant.PAYMENT_STATUS_2){
            //"保证金状态不等于已支付，不可以进行退还操作"
            throw new RuntimeException("保证金【"
                    +RentalDepositEnum.PaymentStatus.getPaymentStatusByStatus(rentalDeposit.getPaymentStatus().intValue()).getDbPaymentStatus()+"】状态，不可以进行退还操作");
        }
        Date date = new Date();
        rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_3);
        rentalDeposit.setActualRefundAmount(rentalDeposit.getActualAmount());
        rentalDeposit.setActualRefundTime(dto.getActualRefundTime());
        rentalDeposit.setRefundVoucherImgName(dto.getRefundVoucherImgName());
        rentalDeposit.setRefundVoucherImgUrl(dto.getRefundVoucherImgUrl());
        rentalDeposit.setUpdateTime(date);
        rentalDeposit.setUpdateBy(dto.getUserId());
        int resultCount;
        resultCount = rentalDepositDao.updateByPrimaryKeySelective(rentalDeposit);
        if(resultCount == 0)throw new RuntimeException("退还保证金失败");
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public Result insertRentPayment(RentalFeeVoucher record){
        //TODO
        //保证金支付方式=支付宝预授权，则调用支付宝接口将授权金额解冻，调用成功，则更新保证金状态=已退还，若调用接口失败，则不更新保证金状态；
        RentalDeposit rentalDeposit = new RentalDeposit();
        rentalDeposit.setOrderId(record.getOrderId());
        rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS2.getStatus());
        rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_1);
        rentalDeposit.setDeleteStatus(Byte.parseByte(CommonEnum.DeleteStatusEnum.STATUS_1.getCode().toString()));
        List<RentalDeposit> rentalDeposits= rentalDepositDao.selectRentalDeposit(rentalDeposit);
        if(rentalDeposits == null)throw new RuntimeException("查询保证金失败");
        rentalDeposit = rentalDeposits.get(0);
        if(rentalDeposit.getPaymentMode() == RentalDepositConstant.PAYMENT_MODE_1.intValue()
                && rentalDeposit.getPaymentStatus() == RentalDepositConstant.PAYMENT_STATUS_2){
            //更新保证金状态为处理中
            rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_4);
            rentalDeposit.setUpdateBy(record.getCreateBy());
            int resultCount;
            resultCount = rentalDepositDao.updateByPrimaryKeySelective(rentalDeposit);
            logger.info("subject:{},orderId:{},resultCount:{}","企业租车后台列表:录入租金支付信息:更新保证金状态为处理中",record.getOrderId(),resultCount);
           //调用解冻的接口
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        logger.info("subject:{},orderId:{}","企业租车后台列表:录入租金支付信息:自动解冻授权",record.getOrderId());
                        Result result = unfreezeAlipay(new RentalEnterpriseOrderDTO(record.getOrderId()));
                        if(ResultConstant.RESULT_CODE_SUCCESS == result.getCode()){
                            //更新保证金状态为已退还
                            RentalDeposit rentalDeposit = rentalDeposits.get(0);
                            rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_3);
                            rentalDeposit.setActualRefundAmount(rentalDeposit.getActualAmount());
                            rentalDeposit.setActualRefundTime(record.getCreateTime());
                            rentalDeposit.setUpdateBy(record.getCreateBy());
                            int resultCount;
                            resultCount = rentalDepositDao.updateByPrimaryKeySelective(rentalDeposit);
                            logger.info("subject:{},orderId:{},resultCount:{}","企业租车后台列表:录入租金支付信息:更新保证金状态为已退还",record.getOrderId(),resultCount);
                        }
                    }catch (Exception e){
                        logger.error("subject:{},e:{}","企业租车后台列表:录入租金支付信息:自动解冻授权:更新保证金状态为已退还",e);
                    }
                }
            });
        }
        int resultCount;
        resultCount = rentalFeeVoucherDao.insert(record);
        if(resultCount == 0)throw new RuntimeException("录入租金支付信息失败");
        RentalEnterpriseOrder rentalEnterpriseOrder = new RentalEnterpriseOrder();
        rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS40.getRealStatus());
        rentalEnterpriseOrder.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS40.getFlowOneStatus());
        rentalEnterpriseOrder.setFlowTwoStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS40.getFlowTwoStatus());
        rentalEnterpriseOrder.setFlowThreeStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS40.getFlowThreeStatus());
        rentalEnterpriseOrder.setCarRentalFee(record.getOneMoney());
        //rentalEnterpriseOrder.setCashPledge(record.getTwoMoney());
        rentalEnterpriseOrder.setId(record.getOrderId());
        rentalEnterpriseOrder.setUpdateBy(record.getCreateBy());
        resultCount = rentalEnterpriseOrderDao.updateByPrimaryKeySelective(rentalEnterpriseOrder);
        if(resultCount == 0)throw new RuntimeException("更新租车订单失败");
        return Result.success(rentalEnterpriseOrder);
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public void insertRentTransfer(RentalFeeVoucher record){
        int resultCount;
        resultCount = rentalFeeVoucherDao.insert(record);
        if(resultCount == 0)throw new RuntimeException("录入租金转账信息失败");
        RentalEnterpriseOrder rentalEnterpriseOrder = new RentalEnterpriseOrder();
        rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS60.getRealStatus());
        rentalEnterpriseOrder.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS60.getFlowOneStatus());
        rentalEnterpriseOrder.setFlowTwoStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS60.getFlowTwoStatus());
        rentalEnterpriseOrder.setFlowThreeStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS60.getFlowThreeStatus());
        rentalEnterpriseOrder.setId(record.getOrderId());
        rentalEnterpriseOrder.setUpdateBy(record.getCreateBy());
        resultCount = rentalEnterpriseOrderDao.updateByPrimaryKeySelective(rentalEnterpriseOrder);
        if(resultCount == 0)throw new RuntimeException("更新租车订单失败");
    }

    @Override
    public Result unfreezeAlipay(RentalEnterpriseOrderDTO dto) {
        RentalEnterpriseOrder reo = rentalEnterpriseOrderDao.selectByPrimaryKey(dto.getOrderId());
        if(reo == null) return Result.failure("订单丢失");
        RentalDeposit param = new RentalDeposit();
        param.setRentCarOrderNo(reo.getOrderNo());
        List<RentalDeposit> rentalDepositList = selectRentalDeposit(param);
        if(rentalDepositList == null || rentalDepositList.isEmpty()) return Result.failure("该订单无保证金记录");

        RentalDeposit rentalDeposit = rentalDepositList.get(0);
        logger.info("subject:{},reo:{},rentalDeposit:{}","解绑支付宝",JsonUtil.gsonStr(reo),JsonUtil.gsonStr(rentalDeposit));
        System.out.println(rentalDeposit.getPaymentMode().intValue() == RentalDepositConstant.PAYMENT_MODE_1.intValue());
        if(rentalDeposit.getPaymentMode().intValue() != RentalDepositConstant.PAYMENT_MODE_1.intValue())
            return Result.failure("此订单非支付宝授权");

        if(RentalDepositEnum.PaymentStatus.status2.getStatus().compareTo(rentalDeposit.getPaymentStatus().intValue()) != 0)
            return Result.failure("此订单无法解绑支付宝");
        RentalPayWatercourse rpw = rentalPayWatercourseService.getRentalPayWatercourseByOrderNoAndDepositType(reo.getOrderNo(), rentalDeposit.getDepositType().intValue());
        logger.info("subject:{},rpw:{}","解绑支付宝",JsonUtil.gsonStr(rpw));
        Result result = AliPayFaceFundAuth.createAlipayFundAuthOrderUnfreezeModelComm(rpw.getOrderNo(), rpw.getActualAmount().toString(),
                rpw.getThirdParthOrderNo(), rpw.getDepositType(), "enterprise/unfreeze", "租车解冻预授资金");
        return result;
    }
}
