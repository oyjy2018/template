package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.rental.RentalOrderCarImg;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RentalOrderDetailsVO {
    private Integer rentalOrderId;
    private Integer status;
    @JSONField(serialize = false)
    private Integer flowOneStatus;
    @JSONField(serialize = false)
    private Integer flowTwoStatus;
    @JSONField(serialize = false)
    private Integer flowThreeStatus;
    private String statusCH;
    private String closeCause;
    private String name;
    private String idCard;
    private String mobilePhone;
    private String brand;
    private String series;
    private String model;
    private String fetchCarMode;
    private String appointmentFetchCarTime;
    private String appointmentRepayCarTime;
    @JSONField(serialize = false)
    private String appointmentFetchCarProvince;
    @JSONField(serialize = false)
    private String appointmentFetchCarCity;
    @JSONField(serialize = false)
    private String appointmentFetchCarRegion;
    private String appointmentFetchCarDetailsAddress;
    @JSONField(serialize = false)
    private String appointmentRepayCarProvince;
    @JSONField(serialize = false)
    private String appointmentRepayCarCity;
    @JSONField(serialize = false)
    private String appointmentRepayCarRegion;
    private String appointmentRepayCarDetailsAddress;
    private String carNumber;
    private String comeCarTime;
    private String comeWarehouseMileage;
    private Integer comeWarehouseOilAmount;
    private Integer comeWarehouseStoreId;
    private String comeWarehouseStoreName;
    private String coachmanName;
    private String comeCarRemark;
    private String repayCarTime;
    private String repayCarMileage;
    private Integer repayCarOilAmount;
    private Integer useOilMass;
    private Integer repayCarWarehouseStoreId;
    private String repayCarWarehouseStoreName;
    private String repayCarCoachmanName;
    private String repayCarRemark;
    private Integer realRentDays;
    private BigDecimal rentTotal;
    private BigDecimal integratedServiceFee;
    private BigDecimal overdueDays;
    private BigDecimal overdueFee;
    private BigDecimal exceedMileageFee;
    private BigDecimal fuelFee;
    private BigDecimal otherFee;
    private BigDecimal shouldChargeTotal;
    private BigDecimal rentCarPreAuthorizationAmount;
    private BigDecimal returnRentCarPreAuthorizationAmount;
    private BigDecimal deductRentCarCashPledge;
    private String settleTime;
    private BigDecimal violationPreAuthorizationAmount;
    private BigDecimal violationAuthRefundAmount;
    private BigDecimal violationPayroll;
    private List<RentalPayWatercourseVO> settleBlotterList;
    private List<RentalOrderCarImg> rociList;

    public Integer getRentalOrderId() {
        return rentalOrderId;
    }

    public void setRentalOrderId(Integer rentalOrderId) {
        this.rentalOrderId = rentalOrderId;
    }

    public Integer getStatus() {
        RentalOrderEnum.StatusStoreEnum enumObj =
                RentalOrderEnum.StatusStoreEnum.getStatusStoreEnum(this.status, this.flowOneStatus, this.flowTwoStatus, this.flowThreeStatus);
        if(enumObj == null){
            return status;
        }else{
            return enumObj.getStatus();
        }
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
        RentalOrderEnum.StatusStoreEnum enumObj =
                RentalOrderEnum.StatusStoreEnum.getStatusStoreEnum(this.status, this.flowOneStatus, this.flowTwoStatus, this.flowThreeStatus);
        if(enumObj == null){
            return "";
        }else{
            return enumObj.getDescribeAPPC();
        }
    }

    public void setStatusCH(String statusCH) {
        this.statusCH = statusCH;
    }

    public String getCloseCause() {
        return closeCause;
    }

    public void setCloseCause(String closeCause) {
        this.closeCause = closeCause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFetchCarMode() {
        return fetchCarMode;
    }

    public void setFetchCarMode(Integer fetchCarMode) {
        RentalOrderEnum.FetchCarMode enumObj = RentalOrderEnum.FetchCarMode.getFetchCarModeByStatus(fetchCarMode);
        if(enumObj == null){
            this.fetchCarMode = "";
        }else{
            this.fetchCarMode = enumObj.getStatusCH();
        }
    }

    public String getAppointmentFetchCarTime() {
        return appointmentFetchCarTime;
    }

    public void setAppointmentFetchCarTime(Date appointmentFetchCarTime) {
        this.appointmentFetchCarTime = DateUtil.format(appointmentFetchCarTime, "yyyy-MM-dd HH");;
    }

    public String getAppointmentRepayCarTime() {
        return appointmentRepayCarTime;
    }

    public void setAppointmentRepayCarTime(Date appointmentRepayCarTime) {
        this.appointmentRepayCarTime = DateUtil.format(appointmentRepayCarTime, "yyyy-MM-dd HH");;
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
        sb.append(StringUtil.ifnull(this.appointmentFetchCarProvince, ""))
                .append(StringUtil.ifnull(this.appointmentFetchCarCity, ""))
                .append(StringUtil.ifnull(this.appointmentFetchCarRegion, ""))
                .append(StringUtil.ifnull(this.appointmentFetchCarDetailsAddress, ""));
        return sb.toString();
    }

    public void setAppointmentFetchCarDetailsAddress(String appointmentFetchCarDetailsAddress) {
        this.appointmentFetchCarDetailsAddress = appointmentFetchCarDetailsAddress;
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
        sb.append(StringUtil.ifnull(this.appointmentRepayCarProvince, ""))
                .append(StringUtil.ifnull(this.appointmentRepayCarCity, ""))
                .append(StringUtil.ifnull(this.appointmentRepayCarRegion, ""))
                .append(StringUtil.ifnull(this.appointmentRepayCarDetailsAddress, ""));
        return sb.toString();
    }

    public void setAppointmentRepayCarDetailsAddress(String appointmentRepayCarDetailsAddress) {
        this.appointmentRepayCarDetailsAddress = appointmentRepayCarDetailsAddress;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(Date comeCarTime) {
        this.comeCarTime = DateUtil.formatDateAndTime(comeCarTime);
    }

    public String getComeWarehouseMileage() {
        return comeWarehouseMileage;
    }

    public void setComeWarehouseMileage(String comeWarehouseMileage) {
        this.comeWarehouseMileage = comeWarehouseMileage;
    }

    public Integer getComeWarehouseOilAmount() {
        return comeWarehouseOilAmount;
    }

    public void setComeWarehouseOilAmount(Integer comeWarehouseOilAmount) {
        this.comeWarehouseOilAmount = comeWarehouseOilAmount;
    }

    public Integer getComeWarehouseStoreId() {
        return comeWarehouseStoreId;
    }

    public void setComeWarehouseStoreId(Integer comeWarehouseStoreId) {
        this.comeWarehouseStoreId = comeWarehouseStoreId;
    }

    public String getComeWarehouseStoreName() {
        return comeWarehouseStoreName;
    }

    public void setComeWarehouseStoreName(String comeWarehouseStoreName) {
        this.comeWarehouseStoreName = comeWarehouseStoreName;
    }

    public String getCoachmanName() {
        return coachmanName;
    }

    public void setCoachmanName(String coachmanName) {
        this.coachmanName = coachmanName;
    }

    public String getComeCarRemark() {
        return comeCarRemark;
    }

    public void setComeCarRemark(String comeCarRemark) {
        this.comeCarRemark = comeCarRemark;
    }

    public String getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(Date repayCarTime) {
        this.repayCarTime = DateUtil.formatDateAndTime(repayCarTime);
    }

    public String getRepayCarMileage() {
        return repayCarMileage;
    }

    public void setRepayCarMileage(String repayCarMileage) {
        this.repayCarMileage = repayCarMileage;
    }

    public Integer getRepayCarOilAmount() {
        return repayCarOilAmount;
    }

    public void setRepayCarOilAmount(Integer repayCarOilAmount) {
        this.repayCarOilAmount = repayCarOilAmount;
    }

    public Integer getUseOilMass() {
        if(this.repayCarOilAmount == null) return this.repayCarOilAmount;
        if(this.repayCarOilAmount - (this.comeWarehouseOilAmount == null ? 0:this.comeWarehouseOilAmount) <= 0){
            return 0;
        }else{
            return this.repayCarOilAmount - (this.comeWarehouseOilAmount == null ? 0:this.comeWarehouseOilAmount);
        }
    }

    public void setUseOilMass(Integer useOilMass) {
        this.useOilMass = useOilMass;
    }

    public Integer getRepayCarWarehouseStoreId() {
        return repayCarWarehouseStoreId;
    }

    public void setRepayCarWarehouseStoreId(Integer repayCarWarehouseStoreId) {
        this.repayCarWarehouseStoreId = repayCarWarehouseStoreId;
    }

    public String getRepayCarWarehouseStoreName() {
        return repayCarWarehouseStoreName;
    }

    public void setRepayCarWarehouseStoreName(String repayCarWarehouseStoreName) {
        this.repayCarWarehouseStoreName = repayCarWarehouseStoreName;
    }

    public String getRepayCarCoachmanName() {
        return repayCarCoachmanName;
    }

    public void setRepayCarCoachmanName(String repayCarCoachmanName) {
        this.repayCarCoachmanName = repayCarCoachmanName;
    }

    public String getRepayCarRemark() {
        return repayCarRemark;
    }

    public void setRepayCarRemark(String repayCarRemark) {
        this.repayCarRemark = repayCarRemark;
    }

    public Integer getRealRentDays() {
        return realRentDays;
    }

    public void setRealRentDays(Integer realRentDays) {
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

    public BigDecimal getDeductRentCarCashPledge() {
        if(this.rentCarPreAuthorizationAmount == null) return this.rentCarPreAuthorizationAmount;
        return this.rentCarPreAuthorizationAmount.subtract(this.returnRentCarPreAuthorizationAmount);
    }

    public void setDeductRentCarCashPledge(BigDecimal deductRentCarCashPledge) {
        this.deductRentCarCashPledge = deductRentCarCashPledge;
    }

    public String getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = DateUtil.formatDateAndTime(settleTime);
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

    public List<RentalPayWatercourseVO> getSettleBlotterList() {
        return settleBlotterList;
    }

    public void setSettleBlotterList(List<RentalPayWatercourseVO> settleBlotterList) {
        this.settleBlotterList = settleBlotterList;
    }

    public List<RentalOrderCarImg> getRociList() {
        return rociList;
    }

    public void setRociList(List<RentalOrderCarImg> rociList) {
        this.rociList = rociList;
    }
}
