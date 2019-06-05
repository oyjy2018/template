package com.ydc.model.cgj.rental;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalPayWatercourse implements Serializable {
    private static final long serialVersionUID = 2893757292231612300L;
    /**
     * t_rental_pay_watercourse.id
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Integer id;

    /**
     * t_rental_pay_watercourse.member_id (用户ID)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Integer memberId;

    /**
     * t_rental_pay_watercourse.order_id (订单ID)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Integer orderId;

    /**
     * t_rental_pay_watercourse.settlement_id (结算ID)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Integer settlementId;

    /**
     * t_rental_pay_watercourse.order_no (订单号)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String orderNo;

    /**
     * t_rental_pay_watercourse.third_parth_order_no (第三方订单号)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String thirdParthOrderNo;

    /**
     * t_rental_pay_watercourse.oneself_serial_number (自己流水号)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String oneselfSerialNumber;

    /**
     * t_rental_pay_watercourse.third_party_serial_number (第三方流水号)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String thirdPartySerialNumber;

    /**
     * t_rental_pay_watercourse.payment_mode (支付方式 1：芝麻 2：信用卡 3：现金)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Byte paymentMode;

    /**
     * t_rental_pay_watercourse.payable_amount (应付金额)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private BigDecimal payableAmount;

    /**
     * t_rental_pay_watercourse.actual_amount (实付金额)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private BigDecimal actualAmount;

    /**
     * t_rental_pay_watercourse.deposit_type (流水类型 1:租车冻结 2：违章冻结 3:租车押金结算 4：违章押金结算  8：机务)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Byte depositType;

    /**
     * t_rental_pay_watercourse.operation_user_id (操作人员id)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Integer operationUserId;

    /**
     * t_rental_pay_watercourse.operation_user_name (操作人员姓名)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String operationUserName;

    /**
     * t_rental_pay_watercourse.operation_time (操作时间)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Date operationTime;

    /**
     * t_rental_pay_watercourse.payee_user_name (收款人姓名)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String payeeUserName;

    /**
     * t_rental_pay_watercourse.payee_account (收款人员账号)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String payeeAccount;

    /**
     * t_rental_pay_watercourse.payment_user_name (付款人姓名)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String paymentUserName;

    /**
     * t_rental_pay_watercourse.payment_account (付款人员账号)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String paymentAccount;

    /**
     * t_rental_pay_watercourse.remark (备注)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String remark;

    /**
     * t_rental_pay_watercourse.delete_status (删除状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Byte deleteStatus;

    /**
     * t_rental_pay_watercourse.create_time (创建时间)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Date createTime;

    /**
     * t_rental_pay_watercourse.create_by (创建人)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Integer createBy;

    /**
     * t_rental_pay_watercourse.update_time (修改时间)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Date updateTime;

    /**
     * t_rental_pay_watercourse.update_by (修改人)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Integer settlementId) {
        this.settlementId = settlementId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getThirdParthOrderNo() {
        return thirdParthOrderNo;
    }

    public void setThirdParthOrderNo(String thirdParthOrderNo) {
        this.thirdParthOrderNo = thirdParthOrderNo;
    }

    public String getOneselfSerialNumber() {
        return oneselfSerialNumber;
    }

    public void setOneselfSerialNumber(String oneselfSerialNumber) {
        this.oneselfSerialNumber = oneselfSerialNumber;
    }

    public String getThirdPartySerialNumber() {
        return thirdPartySerialNumber;
    }

    public void setThirdPartySerialNumber(String thirdPartySerialNumber) {
        this.thirdPartySerialNumber = thirdPartySerialNumber;
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

    public Integer getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Integer operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getPayeeUserName() {
        return payeeUserName;
    }

    public void setPayeeUserName(String payeeUserName) {
        this.payeeUserName = payeeUserName;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getPaymentUserName() {
        return paymentUserName;
    }

    public void setPaymentUserName(String paymentUserName) {
        this.paymentUserName = paymentUserName;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
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
}