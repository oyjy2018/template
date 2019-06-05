package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;

import java.util.Date;

public class RentalOrderListVO {

    private Integer rentalOrderId;
    private Integer carId;
    private Integer status;
    @JSONField(serialize = false)
    private Integer flowOneStatus;
    @JSONField(serialize = false)
    private Integer flowTwoStatus;
    @JSONField(serialize = false)
    private Integer flowThreeStatus;
    private String statusCH;
    private Integer appointmentStoreId;
    private String appointmentStoreName;
    private String carNumber;
    private String name;
    private String mobilePhone;
    private String rentalAccreditStatus;
    private Integer rentalAccreditStatusCode;
    private String rentalAccreditEndTime;
    private String violationAccreditStatus;
    private Integer violationAccreditStatusCode;
    private String violationAccreditEndTime;
    private Integer violationNumber;
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
    private String comeCarTime;
    private String repayCarTime;
    private String brand;
    private String series;
    private String model;
    private String carLevel;

    public Integer getRentalOrderId() {
        return rentalOrderId;
    }

    public void setRentalOrderId(Integer rentalOrderId) {
        this.rentalOrderId = rentalOrderId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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
            return enumObj.getDescribe();
        }
    }

    public void setStatusCH(String statusCH) {
        this.statusCH = statusCH;
    }

    public Integer getAppointmentStoreId() {
        return appointmentStoreId;
    }

    public void setAppointmentStoreId(Integer appointmentStoreId) {
        this.appointmentStoreId = appointmentStoreId;
    }

    public String getAppointmentStoreName() {
        return appointmentStoreName;
    }

    public void setAppointmentStoreName(String appointmentStoreName) {
        this.appointmentStoreName = appointmentStoreName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRentalAccreditStatus() {
        RentalDepositEnum.PaymentStatus enumObj =
                RentalDepositEnum.PaymentStatus.getPaymentStatusByStatus(this.rentalAccreditStatusCode);
        if(enumObj == null){
            return "";
        }else{
            return enumObj.getStatusCH();
        }
    }

    public void setRentalAccreditStatus(String rentalAccreditStatus) {
        this.rentalAccreditStatus = rentalAccreditStatus;
    }

    public Integer getRentalAccreditStatusCode() {
        return rentalAccreditStatusCode;
    }

    public void setRentalAccreditStatusCode(Integer rentalAccreditStatusCode) {
        this.rentalAccreditStatusCode = rentalAccreditStatusCode;
    }

    public String getRentalAccreditEndTime() {
        return rentalAccreditEndTime;
    }

    public void setRentalAccreditEndTime(Date rentalAccreditEndTime) {
        this.rentalAccreditEndTime = DateUtil.formatDateAndTime(rentalAccreditEndTime);
    }

    public String getViolationAccreditStatus() {
        RentalDepositEnum.PaymentStatus enumObj =
                RentalDepositEnum.PaymentStatus.getPaymentStatusByStatus(this.violationAccreditStatusCode);
        if(enumObj == null){
            return "";
        }else{
            return enumObj.getStatusCH();
        }
    }

    public void setViolationAccreditStatus(String violationAccreditStatus) {
        this.violationAccreditStatus = violationAccreditStatus;
    }

    public Integer getViolationAccreditStatusCode() {
        return violationAccreditStatusCode;
    }

    public void setViolationAccreditStatusCode(Integer violationAccreditStatusCode) {
        this.violationAccreditStatusCode = violationAccreditStatusCode;
    }

    public String getViolationAccreditEndTime() {
        return violationAccreditEndTime;
    }

    public void setViolationAccreditEndTime(Date violationAccreditEndTime) {
        this.violationAccreditEndTime = DateUtil.formatDateAndTime(violationAccreditEndTime);
    }

    public Integer getViolationNumber() {
        return violationNumber;
    }

    public void setViolationNumber(Integer violationNumber) {
        this.violationNumber = violationNumber;
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
        this.appointmentFetchCarTime = DateUtil.format(appointmentFetchCarTime, "yyyy-MM-dd HH:mm");
    }

    public String getAppointmentRepayCarTime() {
        return appointmentRepayCarTime;
    }

    public void setAppointmentRepayCarTime(Date appointmentRepayCarTime) {
        this.appointmentRepayCarTime = DateUtil.format(appointmentRepayCarTime, "yyyy-MM-dd HH:mm");
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

    public String getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(Date comeCarTime) {
        this.comeCarTime = DateUtil.format(comeCarTime, "yyyy-MM-dd HH:mm");
    }

    public String getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(Date repayCarTime) {
        this.repayCarTime = DateUtil.format(repayCarTime, "yyyy-MM-dd HH:mm");
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

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }
}
