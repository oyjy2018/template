package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalDeposit implements Serializable {
    private static final long serialVersionUID = -8274693087661017921L;
    /**
     * t_rental_deposit.id
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Integer id;

    /**
     * 订单类型（1：普通订单；2：企业订单）
     */
    private Integer orderType;

    /**
     * t_rental_deposit.member_id (用户ID)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Integer memberId;

    /**
     * t_rental_deposit.order_id (订单ID)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Integer orderId;

    /**
     * t_rental_deposit.rent_car_order_no (租车订单号)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private String rentCarOrderNo;

    /**
     * t_rental_deposit.violation_order_no (违章订单号)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private String violationOrderNo;

    /**
     * t_rental_deposit.serial_num (流水号)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private String serialNum;

    /**
     * t_rental_deposit.payment_mode (支付方式 1：芝麻 2：信用卡 3：现金)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Byte paymentMode;

    /**
     * t_rental_deposit.payable_amount (应付金额)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private BigDecimal payableAmount;

    /**
     * t_rental_deposit.actual_amount (实付金额)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private BigDecimal actualAmount;

    /**
     * t_rental_deposit.deposit_type (押金类型 1：租车 2违章)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Byte depositType;

    /**
     * t_rental_deposit.payment_time (支付时间)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Date paymentTime;

    /**
     * t_rental_deposit.payment_status (押金状态 1：未支付，2：已支付，3：已退还，9：退还失败)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Byte paymentStatus;

    /**
     * t_rental_deposit.plan_refund_time (计划押金退还时间)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Date planRefundTime;

    /**
     * t_rental_deposit.actual_refund_time (实际押金退还时间)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Date actualRefundTime;

    /**
     * t_rental_deposit.payable_refund_amount (应退还金额)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private BigDecimal payableRefundAmount;

    /**
     * t_rental_deposit.actual_refund_amount (实际退还金额)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private BigDecimal actualRefundAmount;

    /**
     * t_rental_deposit.remark (备注)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private String remark;

    /**
     * t_rental_deposit.delete_status (删除状态)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Byte deleteStatus;

    /**
     * t_rental_deposit.create_time (创建时间)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Date createTime;

    /**
     * t_rental_deposit.create_by (创建人)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Integer createBy;

    /**
     * t_rental_deposit.update_time (修改时间)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Date updateTime;

    /**
     * t_rental_deposit.update_by (修改人)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Integer updateBy;


    /**
     * t_rental_deposit.refund_voucher_img_name (退还凭证名)
     * @ibatorgenerated 2019-01-07 16:30:47
     */
    private String refundVoucherImgName;

    /**
     * t_rental_deposit.refund_voucher_img_url (退还凭证路径)
     * @ibatorgenerated 2019-01-07 16:30:47
     */
    private String refundVoucherImgUrl;

    /**
     * t_rental_deposit.pay_voucher_img_name (支付凭证名)
     * @ibatorgenerated 2019-01-07 16:30:47
     */
    private String payVoucherImgName;

    /**
     * t_rental_deposit.pay_voucher_img_url (支付凭证路径)
     * @ibatorgenerated 2019-01-07 16:30:47
     */
    private String payVoucherImgUrl;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getRentCarOrderNo() {
        return rentCarOrderNo;
    }

    public void setRentCarOrderNo(String rentCarOrderNo) {
        this.rentCarOrderNo = rentCarOrderNo;
    }

    public String getViolationOrderNo() {
        return violationOrderNo;
    }

    public void setViolationOrderNo(String violationOrderNo) {
        this.violationOrderNo = violationOrderNo;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Byte getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Byte paymentMode) {
        this.paymentMode = paymentMode;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Byte getDepositType() {
        return depositType;
    }

    public void setDepositType(Byte depositType) {
        this.depositType = depositType;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Byte getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPlanRefundTime() {
        return planRefundTime;
    }

    public void setPlanRefundTime(Date planRefundTime) {
        this.planRefundTime = planRefundTime;
    }

    public Date getActualRefundTime() {
        return actualRefundTime;
    }

    public void setActualRefundTime(Date actualRefundTime) {
        this.actualRefundTime = actualRefundTime;
    }

    public BigDecimal getPayableRefundAmount() {
        return payableRefundAmount;
    }

    public void setPayableRefundAmount(BigDecimal payableRefundAmount) {
        this.payableRefundAmount = payableRefundAmount;
    }

    public BigDecimal getActualRefundAmount() {
        return actualRefundAmount;
    }

    public void setActualRefundAmount(BigDecimal actualRefundAmount) {
        this.actualRefundAmount = actualRefundAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Byte deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getRefundVoucherImgName() {
        return refundVoucherImgName;
    }

    public void setRefundVoucherImgName(String refundVoucherImgName) {
        this.refundVoucherImgName = refundVoucherImgName;
    }

    public String getRefundVoucherImgUrl() {
        return refundVoucherImgUrl;
    }

    public void setRefundVoucherImgUrl(String refundVoucherImgUrl) {
        this.refundVoucherImgUrl = refundVoucherImgUrl;
    }

    public String getPayVoucherImgName() {
        return payVoucherImgName;
    }

    public void setPayVoucherImgName(String payVoucherImgName) {
        this.payVoucherImgName = payVoucherImgName;
    }

    public String getPayVoucherImgUrl() {
        return payVoucherImgUrl;
    }

    public void setPayVoucherImgUrl(String payVoucherImgUrl) {
        this.payVoucherImgUrl = payVoucherImgUrl;
    }
}