package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.commom.enums.rental.RentalOrderEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author
 * @create 2018-11-27 17:41
 **/
public class RentalSettlement2VO implements Serializable {
    private static final long serialVersionUID = -2729903825791382023L;

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
     * t_rental_settlement.violation_pre_authorization_amount (违章预授权额)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private BigDecimal violationPreAuthorizationAmount;

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
     * t_rental_settlement.settle_time (结算时间)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private String settleTime;

    /**
     * 剩余应收总额
     */
    private BigDecimal payableAmount;

    /**
     * t_rental_settlement.rental_auth_status (租车预授权结算状态 1：未结算，2：已结算未付清，10已结清)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private String rentalOrderStatusName;

    /**
     * t_rental_settlement.rental_auth_status (租车预授权结算状态 1：未结算，2：已结算未付清，10已结清)
     * @ibatorgenerated 2018-11-26 14:32:57
     */
    private Integer rentalAuthStatus;

    /**
     * 扣除租车预授权额
     */
    private BigDecimal deductRentCarPreAuthorizationAmount;

    /**
     * 结算流水
     */
    private List<RentalPayWatercourseVO> rentalPayWatercourseVOS;

    //状态
    private Integer status;

    //流程一状态
    private Integer flowOneStatus;


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

    public BigDecimal getViolationPreAuthorizationAmount() {
        return violationPreAuthorizationAmount;
    }

    public void setViolationPreAuthorizationAmount(BigDecimal violationPreAuthorizationAmount) {
        this.violationPreAuthorizationAmount = violationPreAuthorizationAmount;
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

    public String getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(String settleTime) {
        this.settleTime = settleTime;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getRentalOrderStatusName() {
        return RentalOrderEnum.getDescribe(this.status,this.flowOneStatus);
    }

    public void setRentalOrderStatusName(String rentalOrderStatusName) {
        this.rentalOrderStatusName = rentalOrderStatusName;
    }

    public Integer getRentalAuthStatus() {
        return rentalAuthStatus;
    }

    public void setRentalAuthStatus(Integer rentalAuthStatus) {
        this.rentalAuthStatus = rentalAuthStatus;
    }

    public BigDecimal getDeductRentCarPreAuthorizationAmount() {
        return deductRentCarPreAuthorizationAmount;
    }

    public void setDeductRentCarPreAuthorizationAmount(BigDecimal deductRentCarPreAuthorizationAmount) {
        this.deductRentCarPreAuthorizationAmount = deductRentCarPreAuthorizationAmount;
    }

    public List<RentalPayWatercourseVO> getRentalPayWatercourseVOS() {
        return rentalPayWatercourseVOS;
    }

    public void setRentalPayWatercourseVOS(List<RentalPayWatercourseVO> rentalPayWatercourseVOS) {
        this.rentalPayWatercourseVOS = rentalPayWatercourseVOS;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlowOneStatus() {
        return flowOneStatus;
    }

    public void setFlowOneStatus(Integer flowOneStatus) {
        this.flowOneStatus = flowOneStatus;
    }
}
