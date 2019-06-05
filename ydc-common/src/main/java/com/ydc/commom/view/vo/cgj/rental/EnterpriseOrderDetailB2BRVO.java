package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.util.BigDecimalUtil;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EnterpriseOrderDetailB2BRVO {
    private Integer status;
    @JSONField(serialize = false)
    private Integer flowOneStatus;
    @JSONField(serialize = false)
    private Integer flowTwoStatus;
    @JSONField(serialize = false)
    private Integer flowThreeStatus;
    private String statusCH;
    private String carBrand;
    private String carSeries;
    private String carModel;
    private String carStructure;
    private String carSeat;
    @JSONField(serialize = false)
    private String carModelImgName;
    @JSONField(serialize = false)
    private String carModelImgUrl;
    private String viewCarModelImgUrl;
    private Integer workdayRent;
    private Integer weekendRent;
    private Integer holidayRent;
    private Integer dayServiceCharge;
    private BigDecimal marginLimit;
    private Integer orderId;
    private String orderNo;
    private Integer reserveCount;
    private String createTime;
    private Date appointmentFetchCarTime;
    private Date appointmentRepayCarTime;
    private String useCarTimeFrame;
    private Integer carStoreId;
    private String carStoreName;
    @JSONField(serialize = false)
    private String storeRegisterProvince;
    @JSONField(serialize = false)
    private String storeRegisterCity;
    @JSONField(serialize = false)
    private String storeRegisterRegion;
    private String storeDetailsAddress;
    private Integer fetchCarMode;
    @JSONField(serialize = false)
    private String appointmentFetchCarProvince;
    @JSONField(serialize = false)
    private String appointmentFetchCarCity;
    @JSONField(serialize = false)
    private String appointmentFetchCarRegion;
    private String appointmentFetchCarDetailsAddress;
    private Integer repayCarMode;
    @JSONField(serialize = false)
    private String appointmentRepayCarProvince;
    @JSONField(serialize = false)
    private String appointmentRepayCarCity;
    @JSONField(serialize = false)
    private String appointmentRepayCarRegion;
    private String appointmentRepayCarDetailsAddress;
    private BigDecimal carRentalFee;
    private BigDecimal baseServiceFee;
    private BigDecimal cashPledge;
    private BigDecimal totalMoney;  //总计
    //租金押金信息
    private BigDecimal accountRentTotal;   //租金总额
    private BigDecimal accountCashPledge;  //押金
    private String rentAccountDate;  //租金与押金到账日期

    //结算信息
    private BigDecimal settleMoney;
    private String settleDate;

    //押金退还信息
    private BigDecimal violationFee;    //违章收取费用
    private BigDecimal returnCashPledge;    //退还押金
    private String returnDate;  //退还时间

    private String closeDate;

    private List<String> carNumberList;

    public Integer getStatus() {
        Optional<RentalEnterpriseOrderEnum.OrderStatus> optional =
                RentalEnterpriseOrderEnum.OrderStatus.getOrderStatus(status,flowOneStatus,flowTwoStatus,flowThreeStatus);
        return optional.isPresent() ? optional.get().getStatus():status;
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

    public Integer getFlowTwoStatus() {
        return flowTwoStatus;
    }

    public void setFlowTwoStatus(Integer flowTwoStatus) {
        this.flowTwoStatus = flowTwoStatus;
    }

    public Integer getFlowThreeStatus() {
        return flowThreeStatus;
    }

    public void setFlowThreeStatus(Integer flowThreeStatus) {
        this.flowThreeStatus = flowThreeStatus;
    }

    public String getStatusCH() {
        Optional<RentalEnterpriseOrderEnum.OrderStatus> optional =
                RentalEnterpriseOrderEnum.OrderStatus.getOrderStatus(status,flowOneStatus,flowTwoStatus,flowThreeStatus);
        return optional.isPresent() ? optional.get().getDescribeResource():"";
    }

    public void setStatusCH(String statusCH) {
        this.statusCH = statusCH;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }

    public String getCarModelImgName() {
        return carModelImgName;
    }

    public void setCarModelImgName(String carModelImgName) {
        this.carModelImgName = carModelImgName;
    }

    public String getCarModelImgUrl() {
        return carModelImgUrl;
    }

    public void setCarModelImgUrl(String carModelImgUrl) {
        this.carModelImgUrl = carModelImgUrl;
    }

    public String getViewCarModelImgUrl() {
        return viewCarModelImgUrl;
    }

    public void setViewCarModelImgUrl(String viewCarModelImgUrl) {
        this.viewCarModelImgUrl = viewCarModelImgUrl;
    }

    public Integer getWorkdayRent() {
        return workdayRent;
    }

    public void setWorkdayRent(Integer workdayRent) {
        this.workdayRent = workdayRent;
    }

    public Integer getWeekendRent() {
        return weekendRent;
    }

    public void setWeekendRent(Integer weekendRent) {
        this.weekendRent = weekendRent;
    }

    public Integer getHolidayRent() {
        return holidayRent;
    }

    public void setHolidayRent(Integer holidayRent) {
        this.holidayRent = holidayRent;
    }

    public Integer getDayServiceCharge() {
        return dayServiceCharge;
    }

    public void setDayServiceCharge(Integer dayServiceCharge) {
        this.dayServiceCharge = dayServiceCharge;
    }

    public BigDecimal getMarginLimit() {
        return marginLimit;
    }

    public void setMarginLimit(BigDecimal marginLimit) {
        this.marginLimit = marginLimit;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getReserveCount() {
        return reserveCount;
    }

    public void setReserveCount(Integer reserveCount) {
        this.reserveCount = reserveCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = DateUtil.formatDateAndTime(createTime);
    }

    public String getAppointmentFetchCarTime() {
        return DateUtil.formatDateAndTime(appointmentFetchCarTime);
    }

    public void setAppointmentFetchCarTime(Date appointmentFetchCarTime) {
        this.appointmentFetchCarTime = appointmentFetchCarTime;
    }

    public String getAppointmentRepayCarTime() {
        return DateUtil.formatDateAndTime(appointmentRepayCarTime);
    }

    public void setAppointmentRepayCarTime(Date appointmentRepayCarTime) {
        this.appointmentRepayCarTime = appointmentRepayCarTime;
    }

    public String getUseCarTimeFrame() {
        return DateUtil.format(appointmentFetchCarTime,"MM月dd日HH时") + " 至 " + DateUtil.format(appointmentRepayCarTime,"MM月dd日HH时");
    }

    public void setUseCarTimeFrame(String useCarTimeFrame) {
        this.useCarTimeFrame = useCarTimeFrame;
    }

    public Integer getCarStoreId() {
        return carStoreId;
    }

    public void setCarStoreId(Integer carStoreId) {
        this.carStoreId = carStoreId;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public String getStoreRegisterProvince() {
        return storeRegisterProvince;
    }

    public void setStoreRegisterProvince(String storeRegisterProvince) {
        this.storeRegisterProvince = storeRegisterProvince;
    }

    public String getStoreRegisterCity() {
        return storeRegisterCity;
    }

    public void setStoreRegisterCity(String storeRegisterCity) {
        this.storeRegisterCity = storeRegisterCity;
    }

    public String getStoreRegisterRegion() {
        return storeRegisterRegion;
    }

    public void setStoreRegisterRegion(String storeRegisterRegion) {
        this.storeRegisterRegion = storeRegisterRegion;
    }

    public String getStoreDetailsAddress() {
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.ifnull(storeRegisterProvince, ""))
                .append(StringUtil.ifnull(storeRegisterCity, ""))
                .append(StringUtil.ifnull(storeRegisterRegion, ""))
                .append(StringUtil.ifnull(storeDetailsAddress, ""));
        return sb.toString();
    }

    public void setStoreDetailsAddress(String storeDetailsAddress) {
        this.storeDetailsAddress = storeDetailsAddress;
    }

    public Integer getFetchCarMode() {
        return fetchCarMode;
    }

    public void setFetchCarMode(Integer fetchCarMode) {
        this.fetchCarMode = fetchCarMode;
    }

    public String getAppointmentFetchCarProvince() {
        return appointmentFetchCarProvince;
    }

    public void setAppointmentFetchCarProvince(String appointmentFetchCarProvince) {
        this.appointmentFetchCarProvince = appointmentFetchCarProvince;
    }

    public String getAppointmentFetchCarCity() {
        return appointmentFetchCarCity;
    }

    public void setAppointmentFetchCarCity(String appointmentFetchCarCity) {
        this.appointmentFetchCarCity = appointmentFetchCarCity;
    }

    public String getAppointmentFetchCarRegion() {
        return appointmentFetchCarRegion;
    }

    public void setAppointmentFetchCarRegion(String appointmentFetchCarRegion) {
        this.appointmentFetchCarRegion = appointmentFetchCarRegion;
    }

    public String getAppointmentFetchCarDetailsAddress() {
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.ifnull(appointmentFetchCarProvince, ""))
                .append(StringUtil.ifnull(appointmentFetchCarCity, ""))
                .append(StringUtil.ifnull(appointmentFetchCarRegion, ""))
                .append(StringUtil.ifnull(appointmentFetchCarDetailsAddress, ""));
        return sb.toString();
    }

    public void setAppointmentFetchCarDetailsAddress(String appointmentFetchCarDetailsAddress) {
        this.appointmentFetchCarDetailsAddress = appointmentFetchCarDetailsAddress;
    }

    public Integer getRepayCarMode() {
        return repayCarMode;
    }

    public void setRepayCarMode(Integer repayCarMode) {
        this.repayCarMode = repayCarMode;
    }

    public String getAppointmentRepayCarProvince() {
        return appointmentRepayCarProvince;
    }

    public void setAppointmentRepayCarProvince(String appointmentRepayCarProvince) {
        this.appointmentRepayCarProvince = appointmentRepayCarProvince;
    }

    public String getAppointmentRepayCarCity() {
        return appointmentRepayCarCity;
    }

    public void setAppointmentRepayCarCity(String appointmentRepayCarCity) {
        this.appointmentRepayCarCity = appointmentRepayCarCity;
    }

    public String getAppointmentRepayCarRegion() {
        return appointmentRepayCarRegion;
    }

    public void setAppointmentRepayCarRegion(String appointmentRepayCarRegion) {
        this.appointmentRepayCarRegion = appointmentRepayCarRegion;
    }

    public String getAppointmentRepayCarDetailsAddress() {
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.ifnull(appointmentRepayCarProvince, ""))
                .append(StringUtil.ifnull(appointmentRepayCarCity, ""))
                .append(StringUtil.ifnull(appointmentRepayCarRegion, ""))
                .append(StringUtil.ifnull(appointmentRepayCarDetailsAddress, ""));
        return sb.toString();
    }

    public void setAppointmentRepayCarDetailsAddress(String appointmentRepayCarDetailsAddress) {
        this.appointmentRepayCarDetailsAddress = appointmentRepayCarDetailsAddress;
    }

    public BigDecimal getCarRentalFee() {
        return carRentalFee;
    }

    public void setCarRentalFee(BigDecimal carRentalFee) {
        this.carRentalFee = carRentalFee;
    }

    public BigDecimal getBaseServiceFee() {
        return baseServiceFee;
    }

    public void setBaseServiceFee(BigDecimal baseServiceFee) {
        this.baseServiceFee = baseServiceFee;
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public BigDecimal getTotalMoney() {
        return BigDecimalUtil.addAllowNull(BigDecimalUtil.addAllowNull(carRentalFee, baseServiceFee), cashPledge);
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getAccountRentTotal() {
        return accountRentTotal;
    }

    public void setAccountRentTotal(BigDecimal accountRentTotal) {
        this.accountRentTotal = accountRentTotal;
    }

    public BigDecimal getAccountCashPledge() {
        return accountCashPledge;
    }

    public void setAccountCashPledge(BigDecimal accountCashPledge) {
        this.accountCashPledge = accountCashPledge;
    }

    public String getRentAccountDate() {
        return rentAccountDate;
    }

    public void setRentAccountDate(String rentAccountDate) {
        this.rentAccountDate = rentAccountDate;
    }

    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = DateUtil.formatDate(settleDate);
    }

    public BigDecimal getViolationFee() {
        return violationFee;
    }

    public void setViolationFee(BigDecimal violationFee) {
        this.violationFee = violationFee;
    }

    public BigDecimal getReturnCashPledge() {
        return returnCashPledge;
    }

    public void setReturnCashPledge(BigDecimal returnCashPledge) {
        this.returnCashPledge = returnCashPledge;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = DateUtil.formatDateAndTime(closeDate);
    }

    public List<String> getCarNumberList() {
        return carNumberList;
    }

    public void setCarNumberList(List<String> carNumberList) {
        this.carNumberList = carNumberList;
    }
}
