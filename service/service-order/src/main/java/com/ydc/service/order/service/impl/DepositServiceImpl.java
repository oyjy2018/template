package com.ydc.service.order.service.impl;

import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.constant.rental.RentalPayWatercourseConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.BigDecimalUtil;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.dao.cgj.rental.RentalDepositDao;
import com.ydc.dao.cgj.rental.RentalOrderDao;
import com.ydc.dao.cgj.rental.RentalPayWatercourseDao;
import com.ydc.dao.cgj.rental.RentalSettlementDao;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import com.ydc.model.cgj.rental.RentalSettlement;
import com.ydc.service.order.service.DepositService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    private static final Logger logger = LogManager.getLogger(DepositServiceImpl.class);

    @Resource
    RentalDepositDao rentalDepositDao;
    @Resource
    RentalOrderDao rentalOrderDao;
    @Resource
    RentalPayWatercourseDao rentalPayWatercourseDao;
    @Resource
    RentalSettlementDao rentalSettlementDao;


    @Override
    public RentalDeposit selectByPrimaryKey(Integer id) {
        return rentalDepositDao.selectByPrimaryKey(id);
    }

    @Override
    public int insert(RentalDeposit record) {
        return rentalDepositDao.insert(record);
    }

    @Override
    public int insertSelective(RentalDeposit record) {
        return rentalDepositDao.insertSelective(record);
    }

    @Override
    public RentalDeposit queryRentalDepositByOrderAndType(Integer orderId, Integer type,Integer orderType) {
        RentalDeposit rentalDeposit = new RentalDeposit();
        rentalDeposit.setOrderId(orderId);
        rentalDeposit.setOrderType(orderType);
        rentalDeposit.setDepositType(type.byteValue());
        rentalDeposit.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        List<RentalDeposit> rentalDeposits = rentalDepositDao.selectRentalDeposit(rentalDeposit);
        if (rentalDeposits == null || rentalDeposits.isEmpty()) {
            return null;
        }
        return rentalDeposits.get(0);
    }

    @Override
    public Result updateAliPayOrderDeposit(PayRentalDepositRequestDTO ailPayFundAuthNotifyDTO) {
        //查询押金是否已经交过
        RentalDeposit rentalDeposit = new RentalDeposit();
        rentalDeposit.setSerialNum(ailPayFundAuthNotifyDTO.getOut_request_no());
        rentalDeposit.setRentCarOrderNo(ailPayFundAuthNotifyDTO.getOut_order_no());
        rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS1.getStatus());
        List<RentalDeposit> rentalDeposits = rentalDepositDao.selectRentalDeposit(rentalDeposit);
        if (null == rentalDeposits || rentalDeposits.isEmpty()) {
            logger.info("押金查询错误 流水号{}，订单号{}", ailPayFundAuthNotifyDTO.getOut_request_no(), ailPayFundAuthNotifyDTO.getOut_order_no());
            return Result.failure("订单不存在");
        }
        RentalDeposit rentalDepositTemp = rentalDeposits.get(0);
        rentalDepositTemp.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_2);
        rentalDepositTemp.setPaymentMode(RentalDepositConstant.PAYMENT_MODE_1);
        rentalDepositTemp.setActualAmount(BigDecimalUtil.convertString(ailPayFundAuthNotifyDTO.getAmount()));
        rentalDepositTemp.setPaymentTime(DateUtil.getDate(ailPayFundAuthNotifyDTO.getGmt_trans()));
        rentalDepositDao.updateByPrimaryKeySelective(rentalDeposit);

        RentalOrder rentalOrder = new RentalOrder();
        rentalOrder.setOrderNo(ailPayFundAuthNotifyDTO.getOut_order_no());
        List<RentalOrder> rentalOrders = rentalOrderDao.selectRentalOrder(rentalOrder);
        if (null == rentalOrders || rentalOrders.isEmpty()) {
            logger.info("订单查询错误 ，订单号{}", ailPayFundAuthNotifyDTO.getOut_order_no());
            return Result.failure("订单不存在");
        }
        rentalOrder = rentalOrders.get(0);
        //押金支付完成
        rentalOrder.setFlowTwoStatus(RentalOrderStatusConstant.RENTAL_ORDER_FLOW_STATUS_100);
        rentalOrderDao.updateByPrimaryKey(rentalOrder);

        //插入流水记录
        RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
        rentalPayWatercourse.setMemberId(rentalOrder.getMemberId());
        rentalPayWatercourse.setOrderId(rentalOrder.getId());
        rentalPayWatercourse.setOrderNo(ailPayFundAuthNotifyDTO.getOut_order_no());
        rentalPayWatercourse.setThirdParthOrderNo(ailPayFundAuthNotifyDTO.getAuth_no());
        rentalPayWatercourse.setThirdPartySerialNumber(ailPayFundAuthNotifyDTO.getOperation_id());
        rentalPayWatercourse.setOneselfSerialNumber(ailPayFundAuthNotifyDTO.getOut_request_no());
        rentalPayWatercourse.setPaymentMode(RentalPayWatercourseConstant.PAYMENT_MODE_1);
        rentalPayWatercourse.setPayableAmount(rentalDeposit.getPayableAmount());
        rentalPayWatercourse.setActualAmount(BigDecimalUtil.convertString(ailPayFundAuthNotifyDTO.getAmount()));
        rentalPayWatercourse.setDepositType(RentalPayWatercourseConstant.DEPOSIT_TYPE_3);
        rentalPayWatercourse.setPayeeAccount(ailPayFundAuthNotifyDTO.getPayee_user_id());
        rentalPayWatercourse.setPaymentAccount(ailPayFundAuthNotifyDTO.getPayer_user_id());
        rentalPayWatercourseDao.insert(rentalPayWatercourse);

        return null;
    }

    /**
     * 插入冻结信息
     *
     * @param aliPayFundAuthNotifyDTO
     * @return
     */
    @Override
    public Result freezeOrderDeposit(PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO) {
        //更新押金表
        RentalDeposit rentalDeposit = new RentalDeposit();
        //押金流水
        rentalDeposit.setSerialNum(aliPayFundAuthNotifyDTO.getOut_request_no());
        if (RentalDepositConstant.DEPOSIT_TYPE_1.compareTo(aliPayFundAuthNotifyDTO.getDepositType()) == 0) {
            rentalDeposit.setRentCarOrderNo(aliPayFundAuthNotifyDTO.getOut_order_no());
        } else if (RentalDepositConstant.DEPOSIT_TYPE_2.compareTo(aliPayFundAuthNotifyDTO.getDepositType()) == 0) {
            rentalDeposit.setViolationOrderNo(aliPayFundAuthNotifyDTO.getOut_order_no());
        }
        List<RentalDeposit> rentalDeposits = rentalDepositDao.selectRentalDeposit(rentalDeposit);
        if (null == rentalDeposits || rentalDeposits.isEmpty()) {
            logger.info("押金查询错误 流水号{}，订单号{}", aliPayFundAuthNotifyDTO.getOut_request_no(), aliPayFundAuthNotifyDTO.getOut_order_no());
            return Result.failure("订单不存在");
        }
        RentalDeposit rentalDepositTemp = rentalDeposits.get(0);
        rentalDepositTemp.setPaymentStatus(aliPayFundAuthNotifyDTO.getPayment_status().byteValue());//支付状态
        rentalDepositTemp.setPaymentMode(aliPayFundAuthNotifyDTO.getPayment_mode()); //支付方式
        rentalDepositTemp.setActualAmount(BigDecimalUtil.convertString(aliPayFundAuthNotifyDTO.getAmount()));//冻结金额
        Date date = DateUtil.getDate(aliPayFundAuthNotifyDTO.getGmt_trans());
        rentalDepositTemp.setPaymentTime(date);//支付时间
        rentalDepositTemp.setPlanRefundTime(DateUtil.dateAdd(date, 5, 30));
        rentalDepositTemp.setUpdateTime(new Date());
        if (rentalDepositDao.updateByPrimaryKeySelective(rentalDepositTemp) != 1) {
            logger.info("更新失败");
            rentalDepositDao.updateByPrimaryKeySelective(rentalDepositTemp);
        }
        //设置应付金额
        aliPayFundAuthNotifyDTO.setTotal_freeze_amount(StringUtil.bigDecimalToString(rentalDepositTemp.getPayableAmount()));
        //插入流水
        insertRentalPayWatercourse(aliPayFundAuthNotifyDTO, rentalDepositTemp.getMemberId(), rentalDepositTemp.getOrderId());
        return Result.success(rentalDepositTemp.getOrderId());
    }


    /**
     * 手动授权   使用场景 b端手动授权   c端手动授权，修改授权状态
     *
     * @param manualFreezeRentalDepositDTO
     * @return
     */
    @Override
    public Result manualOrderDeposit(ManualFreezeRentalDepositDTO manualFreezeRentalDepositDTO) {
        RentalDeposit rentalDeposit = new RentalDeposit();
        //押金流水
        rentalDeposit.setOrderId(manualFreezeRentalDepositDTO.getOrderId());
        rentalDeposit.setDepositType(manualFreezeRentalDepositDTO.getDepositType());
        rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS1.getStatus());

        //查询押金类型
        List<RentalDeposit> rentalDeposits = rentalDepositDao.selectRentalDeposit(rentalDeposit);
        if (null == rentalDeposits || rentalDeposits.isEmpty()) {
            logger.info("押金查询错误 订单id{}", manualFreezeRentalDepositDTO.getOrderId());
            return Result.failure("订单押金不存在");
        }

        RentalDeposit rentalDepositTemp = rentalDeposits.get(0);
        if (RentalDepositConstant.PAYMENT_STATUS_3.compareTo(rentalDepositTemp.getPaymentStatus()) == 0) {
            logger.info("已经退还，不可以修改");
            return Result.failure("已退还，不可更改");
        }
        //判断结算状态
        if (!updateRentalSettlementAmount(manualFreezeRentalDepositDTO.getOrderId(),
                rentalDepositTemp.getDepositType(),
                manualFreezeRentalDepositDTO.getPaymentStatus(),
                manualFreezeRentalDepositDTO.getAmount())) {
            return Result.failure("结算订单，不可更改");
        }
        if (RentalDepositConstant.PAYMENT_STATUS_1.byteValue() != manualFreezeRentalDepositDTO.getPaymentStatus().byteValue()) {
            //改为未授权时不需要修改
            rentalDepositTemp.setPaymentMode(manualFreezeRentalDepositDTO.getPaymentMode()); //支付方式
            rentalDepositTemp.setActualAmount(manualFreezeRentalDepositDTO.getAmount());//冻结金额
            rentalDepositTemp.setPayableAmount(manualFreezeRentalDepositDTO.getAmount());
            Date date = manualFreezeRentalDepositDTO.getPaymentTime();
            rentalDepositTemp.setPaymentTime(date);//支付时间
            rentalDepositTemp.setPlanRefundTime(DateUtil.dateAdd(date, 5, 30));
            //更新结算的支付金额
        }
        rentalDepositTemp.setPaymentStatus(manualFreezeRentalDepositDTO.getPaymentStatus().byteValue());//支付状态
        rentalDepositTemp.setUpdateBy(manualFreezeRentalDepositDTO.getOperationMemberId());
        rentalDepositTemp.setUpdateTime(new Date());
        rentalDepositDao.updateByPrimaryKeySelective(rentalDepositTemp);

        if (manualFreezeRentalDepositDTO.getPaymentStatus().byteValue() != RentalDepositConstant.PAYMENT_STATUS_1.byteValue()) {
            insertRentalPayWatercourse(manualFreezeRentalDepositDTO, rentalDeposit, "现金支付押金冻结");
        }
        return Result.success(manualFreezeRentalDepositDTO.getOrderId());
    }


    /**
     * 更新结算信息  只用于结算
     *
     * @param alipayTradePayResponseDTO
     * @return
     */
    @Override
    public Result payOrderDeposit(AlipayTradePayResponseDTO alipayTradePayResponseDTO) {
        logger.info("更新结算信息{}", alipayTradePayResponseDTO.toString());
        //更新押金表
        RentalDeposit rentalDeposit = new RentalDeposit();
        //押金流水

        if (RentalDepositConstant.DEPOSIT_TYPE_1.compareTo(alipayTradePayResponseDTO.getDepositType()) == 0) {
            rentalDeposit.setRentCarOrderNo(alipayTradePayResponseDTO.getOutTradeNo());
        } else if (RentalDepositConstant.DEPOSIT_TYPE_2.compareTo(alipayTradePayResponseDTO.getDepositType()) == 0) {
            rentalDeposit.setViolationOrderNo(alipayTradePayResponseDTO.getOutTradeNo());
        }
        rentalDeposit.setPaymentMode(alipayTradePayResponseDTO.getPaymentMode());
        //查询押金类型
        List<RentalDeposit> rentalDeposits = rentalDepositDao.selectRentalDeposit(rentalDeposit);
        if (null == rentalDeposits || rentalDeposits.isEmpty()) {
            logger.info("押金查询错误 订单号{}", alipayTradePayResponseDTO.getOutTradeNo());
            return Result.failure("订单不存在");
        }
        RentalDeposit rentalDepositTemp = rentalDeposits.get(0);
        if (RentalDepositConstant.PAYMENT_STATUS_2.compareTo(rentalDepositTemp.getPaymentStatus()) != 0) {
            logger.info("订单无需结算 订单号{}", alipayTradePayResponseDTO.getOutTradeNo());
            return Result.failure("订单无需结算");
        }
        //实际退还金额 支付费用
        BigDecimal payAmount = BigDecimalUtil.convertString(alipayTradePayResponseDTO.getReceiptAmount());
        //实际退还金额
        BigDecimal bigDecimal = rentalDepositTemp.getActualAmount().subtract(payAmount);
        rentalDepositTemp.setPaymentStatus(alipayTradePayResponseDTO.getPaymentStatus().byteValue());//退还
        rentalDepositTemp.setActualRefundAmount(bigDecimal);//实际退还金额
        rentalDepositTemp.setActualRefundTime(alipayTradePayResponseDTO.getGmtPayment());
        rentalDepositDao.updateByPrimaryKeySelective(rentalDepositTemp);
        //更新结算为结清
        updateRentalSettlement(rentalDepositTemp.getOrderId(), alipayTradePayResponseDTO.getDepositType(), (byte) 10, bigDecimal, payAmount);
        //插入流水
        RentalPayWatercourse rentalPayWatercourse = createRentalPayWatercourse(alipayTradePayResponseDTO, rentalDepositTemp, rentalDepositTemp.getMemberId(), rentalDepositTemp.getOrderId());
        return Result.success(rentalPayWatercourse);
    }

    @Override
    public Result unfreezeOrderDeposit(AlipayTradePayResponseDTO alipayTradePayResponseDTO) {
        logger.info("更新结算信息{}", alipayTradePayResponseDTO.toString());
        //更新押金表
        RentalDeposit rentalDeposit = new RentalDeposit();
        //先根据流水号查询出对应押金信息
        if (RentalDepositConstant.DEPOSIT_TYPE_1.compareTo(alipayTradePayResponseDTO.getDepositType()) == 0) {
            rentalDeposit.setRentCarOrderNo(alipayTradePayResponseDTO.getOutTradeNo());
        } else if (RentalDepositConstant.DEPOSIT_TYPE_2.compareTo(alipayTradePayResponseDTO.getDepositType()) == 0) {
            rentalDeposit.setViolationOrderNo(alipayTradePayResponseDTO.getOutTradeNo());
        }
        rentalDeposit.setPaymentMode(alipayTradePayResponseDTO.getPaymentMode());
        //查询押金类型
        List<RentalDeposit> rentalDeposits = rentalDepositDao.selectRentalDeposit(rentalDeposit);
        if (null == rentalDeposits || rentalDeposits.isEmpty()) {
            logger.info("押金查询错误 订单号{}", alipayTradePayResponseDTO.getOutTradeNo());
            return Result.failure("订单不存在");
        }
        RentalDeposit rentalDepositTemp = rentalDeposits.get(0);
        //获取剩余冻结金额（ 也就是支付费用）
        BigDecimal payAmount = BigDecimalUtil.convertString(alipayTradePayResponseDTO.getReceiptAmount());
        //实际退还金额 = 授权冻结金额 - 剩余冻结金额（支付费用）
        BigDecimal bigDecimal = rentalDepositTemp.getActualAmount().subtract(payAmount);
        rentalDepositTemp.setPaymentStatus(alipayTradePayResponseDTO.getPaymentStatus().byteValue());//退还
        rentalDepositTemp.setActualRefundAmount(bigDecimal);//实际退还金额
        rentalDepositTemp.setActualRefundTime(alipayTradePayResponseDTO.getGmtPayment());
        rentalDepositDao.updateByPrimaryKeySelective(rentalDepositTemp);
        //插入解冻流水
        insertRentalUnfreezeWatercourse(alipayTradePayResponseDTO,rentalDepositTemp,rentalDepositTemp.getMemberId(),rentalDepositTemp.getOrderId());
        return Result.success(rentalDepositTemp);
    }


    private void insertRentalPayWatercourse(ManualFreezeRentalDepositDTO manualFreezeRentalDepositDTO, RentalDeposit rentalDeposit, String remark) {
        RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
        rentalPayWatercourse.setMemberId(rentalDeposit.getMemberId());
        rentalPayWatercourse.setOrderId(manualFreezeRentalDepositDTO.getOrderId());
        rentalPayWatercourse.setOrderNo(rentalDeposit.getRentCarOrderNo() == null ? rentalDeposit.getViolationOrderNo() : rentalDeposit.getRentCarOrderNo());
        rentalPayWatercourse.setPaymentMode(manualFreezeRentalDepositDTO.getPaymentMode());
        rentalPayWatercourse.setPayableAmount(rentalDeposit.getPayableAmount());
        rentalPayWatercourse.setActualAmount(manualFreezeRentalDepositDTO.getAmount());
        rentalPayWatercourse.setDepositType(manualFreezeRentalDepositDTO.getDepositType());
        rentalPayWatercourse.setOperationUserId(manualFreezeRentalDepositDTO.getOperationMemberId());
        rentalPayWatercourse.setOperationUserName(manualFreezeRentalDepositDTO.getOperationMemberName());
        rentalPayWatercourse.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        Date date = new Date();
        rentalPayWatercourse.setCreateTime(date);
        rentalPayWatercourse.setUpdateTime(date);
        rentalPayWatercourse.setOperationTime(date);
        rentalPayWatercourse.setRemark(remark);
        rentalPayWatercourseDao.insert(rentalPayWatercourse);

    }


    private RentalPayWatercourse createRentalPayWatercourse(AlipayTradePayResponseDTO alipayTradePayResponseDTO, RentalDeposit rentalDeposit, Integer memberId, Integer orderId) {
        //插入流水
        RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
        rentalPayWatercourse.setMemberId(memberId);
        rentalPayWatercourse.setOrderId(orderId);
        rentalPayWatercourse.setOrderNo(alipayTradePayResponseDTO.getOutTradeNo());
        rentalPayWatercourse.setThirdParthOrderNo(alipayTradePayResponseDTO.getTradeNo());
        rentalPayWatercourse.setThirdPartySerialNumber(alipayTradePayResponseDTO.getSettlementId());
        rentalPayWatercourse.setOneselfSerialNumber(rentalDeposit.getSerialNum());
        rentalPayWatercourse.setPaymentMode(alipayTradePayResponseDTO.getPaymentMode());
        rentalPayWatercourse.setPayableAmount(BigDecimalUtil.convertString(alipayTradePayResponseDTO.getReceiptAmount()));
        rentalPayWatercourse.setActualAmount(BigDecimalUtil.convertString(alipayTradePayResponseDTO.getReceiptAmount()));
        Byte depositType = null;
        if (RentalDepositConstant.DEPOSIT_TYPE_1.compareTo(alipayTradePayResponseDTO.getDepositType()) == 0) {
            //租车结算
            depositType = RentalPayWatercourseConstant.DEPOSIT_TYPE_3;
        } else if (RentalDepositConstant.DEPOSIT_TYPE_2.compareTo(alipayTradePayResponseDTO.getDepositType()) == 0) {
            //违章结算
            depositType = RentalPayWatercourseConstant.DEPOSIT_TYPE_4;
        }
        rentalPayWatercourse.setDepositType(depositType);
        rentalPayWatercourse.setPayeeAccount(alipayTradePayResponseDTO.getPayeeAccount());
        rentalPayWatercourse.setPaymentAccount(alipayTradePayResponseDTO.getBuyerUserId());
        return rentalPayWatercourse;
    }

    /**
     * 解冻回调流水插入
     * @param alipayTradePayResponseDTO
     * @param rentalDeposit
     * @param memberId
     * @param orderId
     */
    private void insertRentalUnfreezeWatercourse(AlipayTradePayResponseDTO alipayTradePayResponseDTO, RentalDeposit rentalDeposit, Integer memberId, Integer orderId) {
        //插入流水
        RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
        rentalPayWatercourse.setMemberId(memberId);
        rentalPayWatercourse.setOrderId(orderId);
        rentalPayWatercourse.setOrderNo(alipayTradePayResponseDTO.getOutTradeNo());
        rentalPayWatercourse.setThirdParthOrderNo(alipayTradePayResponseDTO.getTradeNo());
        rentalPayWatercourse.setThirdPartySerialNumber(alipayTradePayResponseDTO.getSettlementId());
        rentalPayWatercourse.setOneselfSerialNumber(rentalDeposit.getSerialNum());
        rentalPayWatercourse.setPaymentMode(alipayTradePayResponseDTO.getPaymentMode());
        rentalPayWatercourse.setPayableAmount(BigDecimalUtil.convertString(alipayTradePayResponseDTO.getReceiptAmount()));
        rentalPayWatercourse.setActualAmount(BigDecimalUtil.convertString(alipayTradePayResponseDTO.getReceiptAmount()));
        rentalPayWatercourse.setDepositType(alipayTradePayResponseDTO.getDepositType());
        rentalPayWatercourse.setPayeeAccount(alipayTradePayResponseDTO.getPayeeAccount());
        rentalPayWatercourse.setPaymentAccount(alipayTradePayResponseDTO.getBuyerUserId());
        rentalPayWatercourse.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        Date date = new Date();
        rentalPayWatercourse.setCreateTime(date);
        rentalPayWatercourse.setUpdateTime(date);
        rentalPayWatercourse.setOperationTime(date);
        rentalPayWatercourse.setRemark("支付宝回调插入");
        rentalPayWatercourseDao.insert(rentalPayWatercourse);
    }

    private void insertRentalPayWatercourse(PayRentalDepositRequestDTO payRentalDepositRequestDTO, Integer memberId, Integer orderId) {
        //插入流水
        RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
        rentalPayWatercourse.setMemberId(memberId);
        rentalPayWatercourse.setOrderId(orderId);
        rentalPayWatercourse.setOrderNo(payRentalDepositRequestDTO.getOut_order_no());
        rentalPayWatercourse.setThirdParthOrderNo(payRentalDepositRequestDTO.getAuth_no());
        rentalPayWatercourse.setThirdPartySerialNumber(payRentalDepositRequestDTO.getOperation_id());
        rentalPayWatercourse.setOneselfSerialNumber(payRentalDepositRequestDTO.getOut_request_no());
        rentalPayWatercourse.setPaymentMode(payRentalDepositRequestDTO.getPayment_mode());
        rentalPayWatercourse.setPayableAmount(BigDecimalUtil.convertString(payRentalDepositRequestDTO.getTotal_freeze_amount()));
        rentalPayWatercourse.setActualAmount(BigDecimalUtil.convertString(payRentalDepositRequestDTO.getAmount()));
        rentalPayWatercourse.setDepositType(payRentalDepositRequestDTO.getDepositType());
        rentalPayWatercourse.setPayeeAccount(payRentalDepositRequestDTO.getPayee_user_id());
        rentalPayWatercourse.setPaymentAccount(payRentalDepositRequestDTO.getPayer_user_id());
        rentalPayWatercourse.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        Date date = new Date();
        rentalPayWatercourse.setCreateTime(date);
        rentalPayWatercourse.setUpdateTime(date);
        rentalPayWatercourse.setOperationTime(date);
        rentalPayWatercourse.setRemark("支付宝回调插入");
        rentalPayWatercourseDao.insert(rentalPayWatercourse);
    }

    /**
     * 更新结算表的违章金额
     *
     * @param orderId
     * @param depositType
     * @param paymentStatus
     * @param amount
     * @return
     */
    private boolean updateRentalSettlementAmount(Integer orderId, Byte depositType, Byte paymentStatus, BigDecimal amount) {
        RentalSettlement rentalSettlement = rentalSettlementDao.getRentalSettlementByOrderId(orderId);
        //未结算可以改
        if (null == rentalSettlement) {
            return true;
        }
        //租车计算后不可以改
        if (RentalDepositConstant.DEPOSIT_TYPE_1.compareTo(depositType) == 0) {
            return false;
        }
        //只有 违章  未结算 支持 可以修改
        if (RentalDepositConstant.DEPOSIT_TYPE_2.compareTo(depositType) == 0
                && RentalPayWatercourseConstant.AUTH_STATUS_1.compareTo(rentalSettlement.getViolationAuthStatus()) == 0
                && RentalDepositConstant.PAYMENT_STATUS_2.compareTo(paymentStatus) == 0) {
            //可以修改
            rentalSettlement.setViolationPreAuthorizationAmount(amount);
            rentalSettlementDao.updateByPrimaryKeySelective(rentalSettlement);
            return true;
        }
        return false;
    }

    /**
     * 更新结算表
     *
     * @param orderId
     * @param depositType
     * @param authStatus   结算状态
     * @param refundAmount 退还费用
     * @param payAmount    支付费用
     */
    private void updateRentalSettlement(Integer orderId, Byte depositType, Byte authStatus, BigDecimal refundAmount, BigDecimal payAmount) {
        RentalSettlement rentalSettlement = rentalSettlementDao.getRentalSettlementByOrderId(orderId);
        //租车计算后不可以改
        if (RentalDepositConstant.DEPOSIT_TYPE_1.compareTo(depositType) == 0) {
            rentalSettlement.setReturnRentCarPreAuthorizationAmount(refundAmount);//退还租车预授权费用
            rentalSettlement.setRentalAuthStatus(authStatus);
        } else if (RentalDepositConstant.DEPOSIT_TYPE_2.compareTo(depositType) == 0) {
            rentalSettlement.setViolationAuthRefundAmount(refundAmount);
            rentalSettlement.setViolationPayroll(payAmount);
            rentalSettlement.setViolationAuthStatus(authStatus);
        }
        rentalSettlementDao.updateByPrimaryKeySelective(rentalSettlement);
    }
}
