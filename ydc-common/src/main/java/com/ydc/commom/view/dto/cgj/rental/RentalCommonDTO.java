package com.ydc.commom.view.dto.cgj.rental;

import java.math.BigDecimal;

public class RentalCommonDTO {

    private Integer companyId;

    private BigDecimal workdayRent;

    private BigDecimal weekendRent;

    private BigDecimal holidayRent;

    private BigDecimal dayServiceCharge;

    private BigDecimal cashPledge;

    private String rentCarStartTime;

    private String rentCarEndTime;

    private Integer reserveCount;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getWorkdayRent() {
        return workdayRent;
    }

    public void setWorkdayRent(BigDecimal workdayRent) {
        this.workdayRent = workdayRent;
    }

    public BigDecimal getWeekendRent() {
        return weekendRent;
    }

    public void setWeekendRent(BigDecimal weekendRent) {
        this.weekendRent = weekendRent;
    }

    public BigDecimal getHolidayRent() {
        return holidayRent;
    }

    public void setHolidayRent(BigDecimal holidayRent) {
        this.holidayRent = holidayRent;
    }

    public BigDecimal getDayServiceCharge() {
        return dayServiceCharge;
    }

    public void setDayServiceCharge(BigDecimal dayServiceCharge) {
        this.dayServiceCharge = dayServiceCharge;
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public String getRentCarStartTime() {
        return rentCarStartTime;
    }

    public void setRentCarStartTime(String rentCarStartTime) {
        this.rentCarStartTime = rentCarStartTime;
    }

    public String getRentCarEndTime() {
        return rentCarEndTime;
    }

    public void setRentCarEndTime(String rentCarEndTime) {
        this.rentCarEndTime = rentCarEndTime;
    }

    public Integer getReserveCount() {
        return reserveCount;
    }

    public void setReserveCount(Integer reserveCount) {
        this.reserveCount = reserveCount;
    }
}
