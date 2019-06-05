package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalSettlement implements Serializable {
    private static final long serialVersionUID = 2625486882472759690L;
    /**
     * t_rental_settlement.id
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Integer id;

    /**
     * t_rental_settlement.member_id (用户ID)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Integer memberId;

    /**
     * t_rental_settlement.order_id (订单ID)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Integer orderId;

    /**
     * t_rental_settlement.real_rent_days (实际租车天数)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal realRentDays;

    /**
     * t_rental_settlement.rent_total (租金总额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal rentTotal;

    /**
     * t_rental_settlement.integrated_service_fee (综合服务费)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal integratedServiceFee;

    /**
     * t_rental_settlement.overdue_days (超出天数)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal overdueDays;

    /**
     * t_rental_settlement.overdue_fee (超时费)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal overdueFee;

    /**
     * t_rental_settlement.exceed_mileage_fee (超里程费)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal exceedMileageFee;

    /**
     * t_rental_settlement.fuel_fee (燃油费)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal fuelFee;

    /**
     * t_rental_settlement.other_fee (其他费用)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal otherFee;

    /**
     * t_rental_settlement.should_charge_total (应收总额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal shouldChargeTotal;

    /**
     * t_rental_settlement.actual_amount (实际支付总额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal actualAmount;

    /**
     * t_rental_settlement.rent_car_pre_authorization_amount (租车预授权额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal rentCarPreAuthorizationAmount;

    /**
     * t_rental_settlement.return_rent_car_pre_authorization_amount (退还租车预授权额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal returnRentCarPreAuthorizationAmount;

    /**
     * t_rental_settlement.violation_pre_authorization_amount (违章预授权额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal violationPreAuthorizationAmount;

    /**
     * t_rental_settlement.violation_auth_refund_amount (违章预授权退还金额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal violationAuthRefundAmount;

    /**
     * t_rental_settlement.violation_payroll (违章代付费用)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal violationPayroll;

    /**
     * t_rental_settlement.rental_auth_status (租车预授权结算状态 1：未结算，2：已结算未付清，10已结清)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Byte rentalAuthStatus;

    /**
     * t_rental_settlement.violation_auth_status (违章预授权结算状态 1：未结算，2：已结算未付清，10已结清)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Byte violationAuthStatus;

    /**
     * t_rental_settlement.settle_status (结算状态 1：未结算，2：已结算未付清，10已结清 9：结算失败)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Byte settleStatus;

    /**
     * t_rental_settlement.settle_user_id (结算人员id)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Integer settleUserId;

    /**
     * t_rental_settlement.settle_user_name (结算人员时间)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private String settleUserName;

    /**
     * t_rental_settlement.settle_time (结算时间)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Date settleTime;

    /**
     * t_rental_settlement.status (状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Byte status;

    /**
     * t_rental_settlement.create_time
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Date createTime;

    /**
     * t_rental_settlement.create_by
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Integer createBy;

    /**
     * t_rental_settlement.update_time
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Date updateTime;

    /**
     * t_rental_settlement.update_by
     * @ibatorgenerated 2018-11-26 14:32:57
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

    public BigDecimal getRealRentDays() {
        return realRentDays;
    }

    public void setRealRentDays(BigDecimal realRentDays) {
        this.realRentDays = realRentDays;
    }

    public BigDecimal getRentTotal() {
        return rentTotal;
    }

    public void setRentTotal(BigDecimal rentTotal) {
        this.rentTotal = rentTotal;
    }

    public BigDecimal getIntegratedServiceFee() {
        return integratedServiceFee;
    }

    public void setIntegratedServiceFee(BigDecimal integratedServiceFee) {
        this.integratedServiceFee = integratedServiceFee;
    }

    public BigDecimal getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(BigDecimal overdueDays) {
        this.overdueDays = overdueDays;
    }

    public BigDecimal getOverdueFee() {
        return overdueFee;
    }

    public void setOverdueFee(BigDecimal overdueFee) {
        this.overdueFee = overdueFee;
    }

    public BigDecimal getExceedMileageFee() {
        return exceedMileageFee;
    }

    public void setExceedMileageFee(BigDecimal exceedMileageFee) {
        this.exceedMileageFee = exceedMileageFee;
    }

    public BigDecimal getFuelFee() {
        return fuelFee;
    }

    public void setFuelFee(BigDecimal fuelFee) {
        this.fuelFee = fuelFee;
    }

    public BigDecimal getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getShouldChargeTotal() {
        return shouldChargeTotal;
    }

    public void setShouldChargeTotal(BigDecimal shouldChargeTotal) {
        this.shouldChargeTotal = shouldChargeTotal;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getRentCarPreAuthorizationAmount() {
        return rentCarPreAuthorizationAmount;
    }

    public void setRentCarPreAuthorizationAmount(BigDecimal rentCarPreAuthorizationAmount) {
        this.rentCarPreAuthorizationAmount = rentCarPreAuthorizationAmount;
    }

    public BigDecimal getReturnRentCarPreAuthorizationAmount() {
        return returnRentCarPreAuthorizationAmount;
    }

    public void setReturnRentCarPreAuthorizationAmount(BigDecimal returnRentCarPreAuthorizationAmount) {
        this.returnRentCarPreAuthorizationAmount = returnRentCarPreAuthorizationAmount;
    }

    public BigDecimal getViolationPreAuthorizationAmount() {
        return violationPreAuthorizationAmount;
    }

    public void setViolationPreAuthorizationAmount(BigDecimal violationPreAuthorizationAmount) {
        this.violationPreAuthorizationAmount = violationPreAuthorizationAmount;
    }

    public BigDecimal getViolationAuthRefundAmount() {
        return violationAuthRefundAmount;
    }

    public void setViolationAuthRefundAmount(BigDecimal violationAuthRefundAmount) {
        this.violationAuthRefundAmount = violationAuthRefundAmount;
    }

    public BigDecimal getViolationPayroll() {
        return violationPayroll;
    }

    public void setViolationPayroll(BigDecimal violationPayroll) {
        this.violationPayroll = violationPayroll;
    }

    public Byte getRentalAuthStatus() {
        return rentalAuthStatus;
    }

    public void setRentalAuthStatus(Byte rentalAuthStatus) {
        this.rentalAuthStatus = rentalAuthStatus;
    }

    public Byte getViolationAuthStatus() {
        return violationAuthStatus;
    }

    public void setViolationAuthStatus(Byte violationAuthStatus) {
        this.violationAuthStatus = violationAuthStatus;
    }

    public Byte getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Byte settleStatus) {
        this.settleStatus = settleStatus;
    }

    public Integer getSettleUserId() {
        return settleUserId;
    }

    public void setSettleUserId(Integer settleUserId) {
        this.settleUserId = settleUserId;
    }

    public String getSettleUserName() {
        return settleUserName;
    }

    public void setSettleUserName(String settleUserName) {
        this.settleUserName = settleUserName;
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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