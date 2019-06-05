package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.IsEmpty;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import com.ydc.model.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 违章提交
 *
 * @author
 * @create 2018-11-26 15:02
 **/
public class RentalSettlementDTO implements Serializable {
    private static final long serialVersionUID = 3498378498745155919L;

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
    @IsEmpty(message = "订单ID不能为空")
        private Integer orderId;

    /**
     * t_rental_settlement.real_rent_days (实际租车天数)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "实际租车天数不能为空")
    private BigDecimal realRentDays;

    /**
     * t_rental_settlement.rent_total (租金总额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "租金总额不能为空")
    private BigDecimal rentTotal;

    /**
     * t_rental_settlement.integrated_service_fee (综合服务费)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "综合服务费不能为空")
    private BigDecimal integratedServiceFee;

    /**
     * t_rental_settlement.overdue_days (超出天数)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "超出天数不能为空")
    private BigDecimal overdueDays;

    /**
     * t_rental_settlement.overdue_fee (超时费)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "超时费不能为空")
    private BigDecimal overdueFee;

    /**
     * t_rental_settlement.exceed_mileage_fee (超里程费)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "超里程费不能为空")
    private BigDecimal exceedMileageFee;

    /**
     * t_rental_settlement.fuel_fee (燃油费)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "燃油费不能为空")
    private BigDecimal fuelFee;

    /**
     * t_rental_settlement.other_fee (其他费用)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "其他费用不能为空")
    private BigDecimal otherFee;

    /**
     * t_rental_settlement.should_charge_total (应收总额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "应收总额不能为空")
    private BigDecimal shouldChargeTotal;

    /**
     * t_rental_settlement.actual_amount (实际支付总额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "实际支付总额不能为空")
    private BigDecimal actualAmount;

    /**
     * t_rental_settlement.rent_car_pre_authorization_amount (租车预授权额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "租车预授权额不能为空")
    private BigDecimal rentCarPreAuthorizationAmount;

    /**
     * t_rental_settlement.return_rent_car_pre_authorization_amount (退还租车预授权额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "退还租车预授权额不能为空")
    private BigDecimal returnRentCarPreAuthorizationAmount;

    /**
     * t_rental_settlement.violation_pre_authorization_amount (违章预授权额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    @IsEmpty(message = "违章预授权额不能为空")
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
    private Integer rentalAuthStatus;

    /**
     * t_rental_settlement.violation_auth_status (违章预授权结算状态 1：未结算，2：已结算未付清，10已结清)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Integer violationAuthStatus;

    /**
     * t_rental_settlement.settle_status (结算状态 1：未结算，2：已结算未付清，10已结清 9：结算失败)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Integer settleStatus;

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
     * 租赁支付流水表
     */
    private List<RentalPayWatercourse> rentalPayWatercourseList;


    private BigDecimal deductRentCarPreAuthorizationAmount;//扣除租车预授权额

    private BigDecimal offlineSettlementAmount;//线下已结算金额

    private BigDecimal payableAmount;//剩余应收总额


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
        this.rentTotal = StringUtil.isEmpty(rentTotal)
                ? rentTotal : BigDecimal.valueOf(Double.valueOf(rentTotal.toString().trim()));
    }

    public BigDecimal getIntegratedServiceFee() {
        return integratedServiceFee;
    }

    public void setIntegratedServiceFee(BigDecimal integratedServiceFee) {
        this.integratedServiceFee = StringUtil.isEmpty(integratedServiceFee)
                ? integratedServiceFee : BigDecimal.valueOf(Double.valueOf(integratedServiceFee.toString().trim()));
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
        this.overdueFee = StringUtil.isEmpty(overdueFee)
                ? overdueFee : BigDecimal.valueOf(Double.valueOf(overdueFee.toString().trim()));
    }

    public BigDecimal getExceedMileageFee() {
        return exceedMileageFee;
    }

    public void setExceedMileageFee(BigDecimal exceedMileageFee) {
        this.exceedMileageFee = StringUtil.isEmpty(exceedMileageFee)
                ? exceedMileageFee : BigDecimal.valueOf(Double.valueOf(exceedMileageFee.toString().trim()));
    }

    public BigDecimal getFuelFee() {
        return fuelFee;
    }

    public void setFuelFee(BigDecimal fuelFee) {
        this.fuelFee = StringUtil.isEmpty(fuelFee)
                ? fuelFee : BigDecimal.valueOf(Double.valueOf(fuelFee.toString().trim()));
    }

    public BigDecimal getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = StringUtil.isEmpty(otherFee)
                ? otherFee : BigDecimal.valueOf(Double.valueOf(otherFee.toString().trim()));
    }

    public BigDecimal getShouldChargeTotal() {
        return shouldChargeTotal;
    }

    public void setShouldChargeTotal(BigDecimal shouldChargeTotal) {
        this.shouldChargeTotal = StringUtil.isEmpty(shouldChargeTotal)
                ? shouldChargeTotal : BigDecimal.valueOf(Double.valueOf(shouldChargeTotal.toString().trim()));
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = StringUtil.isEmpty(actualAmount)
                ? actualAmount : BigDecimal.valueOf(Double.valueOf(actualAmount.toString().trim()));
    }

    public BigDecimal getRentCarPreAuthorizationAmount() {
        return rentCarPreAuthorizationAmount;
    }

    public void setRentCarPreAuthorizationAmount(BigDecimal rentCarPreAuthorizationAmount) {
        this.rentCarPreAuthorizationAmount = StringUtil.isEmpty(rentCarPreAuthorizationAmount)
                ? rentCarPreAuthorizationAmount : BigDecimal.valueOf(Double.valueOf(rentCarPreAuthorizationAmount.toString().trim()));
    }

    public BigDecimal getReturnRentCarPreAuthorizationAmount() {
        return returnRentCarPreAuthorizationAmount;
    }

    public void setReturnRentCarPreAuthorizationAmount(BigDecimal returnRentCarPreAuthorizationAmount) {
        this.returnRentCarPreAuthorizationAmount = StringUtil.isEmpty(returnRentCarPreAuthorizationAmount)
                ? returnRentCarPreAuthorizationAmount : BigDecimal.valueOf(Double.valueOf(returnRentCarPreAuthorizationAmount.toString().trim()));
    }

    public BigDecimal getViolationPreAuthorizationAmount() {
        return violationPreAuthorizationAmount;
    }

    public void setViolationPreAuthorizationAmount(BigDecimal violationPreAuthorizationAmount) {
        this.violationPreAuthorizationAmount = StringUtil.isEmpty(violationPreAuthorizationAmount)
                ? violationPreAuthorizationAmount : BigDecimal.valueOf(Double.valueOf(violationPreAuthorizationAmount.toString().trim()));
    }

    public BigDecimal getViolationAuthRefundAmount() {
        return violationAuthRefundAmount;
    }

    public void setViolationAuthRefundAmount(BigDecimal violationAuthRefundAmount) {
        this.violationAuthRefundAmount = StringUtil.isEmpty(violationAuthRefundAmount)
                ? violationAuthRefundAmount : BigDecimal.valueOf(Double.valueOf(violationAuthRefundAmount.toString().trim()));
    }

    public BigDecimal getViolationPayroll() {
        return violationPayroll;
    }

    public void setViolationPayroll(BigDecimal violationPayroll) {
        this.violationPayroll = violationPayroll;
    }

    public Integer getRentalAuthStatus() {
        return rentalAuthStatus;
    }

    public void setRentalAuthStatus(Integer rentalAuthStatus) {
        this.rentalAuthStatus = rentalAuthStatus;
    }

    public Integer getViolationAuthStatus() {
        return violationAuthStatus;
    }

    public void setViolationAuthStatus(Integer violationAuthStatus) {
        this.violationAuthStatus = violationAuthStatus;
    }

    public Integer getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Integer settleStatus) {
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

    public List<RentalPayWatercourse> getRentalPayWatercourseList() {
        return rentalPayWatercourseList;
    }

    public void setRentalPayWatercourseList(List<RentalPayWatercourse> rentalPayWatercourseList) {
        this.rentalPayWatercourseList = rentalPayWatercourseList;
    }

    public BigDecimal getDeductRentCarPreAuthorizationAmount() {
        return deductRentCarPreAuthorizationAmount;
    }

    public void setDeductRentCarPreAuthorizationAmount(BigDecimal deductRentCarPreAuthorizationAmount) {
        this.deductRentCarPreAuthorizationAmount = StringUtil.isEmpty(deductRentCarPreAuthorizationAmount)
                ? deductRentCarPreAuthorizationAmount : BigDecimal.valueOf(Double.valueOf(deductRentCarPreAuthorizationAmount.toString().trim()));
    }
    public BigDecimal getOfflineSettlementAmount() {
        return offlineSettlementAmount;
    }

    public void setOfflineSettlementAmount(BigDecimal offlineSettlementAmount) {
        this.offlineSettlementAmount = StringUtil.isEmpty(offlineSettlementAmount)
                ? offlineSettlementAmount : BigDecimal.valueOf(Double.valueOf(offlineSettlementAmount.toString().trim()));
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = StringUtil.isEmpty(payableAmount)
                ? payableAmount : BigDecimal.valueOf(Double.valueOf(payableAmount.toString().trim()));
    }
}
