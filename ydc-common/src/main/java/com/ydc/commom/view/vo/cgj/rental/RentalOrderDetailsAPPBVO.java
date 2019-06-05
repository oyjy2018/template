package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.MemberEnum;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.util.BigDecimalUtil;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;

public class RentalOrderDetailsAPPBVO {
    private Integer rentalOrderId;
    private String name;
    private String idCard;
    private String mobilePhone;
    private Integer whetherRealName;
    private String whetherRealNameCH;
    @JSONField(serialize = false)
    private Integer carId;
    @JSONField(serialize = false)
    private String carMainImgUrl;
    @JSONField(serialize = false)
    private String carSeriesMainImgUrl;
    private String mainImgUrl;
    @JSONField(serialize = false)
    private String carMainImgName;
    @JSONField(serialize = false)
    private String carSeriesMainImgName;
    private String mainImgName;
    private String viewMainImgUrl;
    private Integer status;
    @JSONField(serialize = false)
    private Integer flowOneStatus;
    @JSONField(serialize = false)
    private Integer flowTwoStatus;
    @JSONField(serialize = false)
    private Integer flowThreeStatus;
    private String statusCH;
    private String brand;
    @JSONField(serialize = false)
    private String carCarStructure;
    @JSONField(serialize = false)
    private String carGearBox;
    @JSONField(serialize = false)
    private String carCarSeat;
    @JSONField(serialize = false)
    private String carSeriesCarStructure;
    @JSONField(serialize = false)
    private String carSeriesGearBox;
    @JSONField(serialize = false)
    private String carSeriesCarSeat;
    private String useCarInfo;
    private String createTime;
    @JSONField(serialize = false)
    private Date appointmentFetchCarTime;
    @JSONField(serialize = false)
    private Date appointmentRepayCarTime;
    private String useCarTimeFrame;
    private String appointmentStoreName;
    @JSONField(serialize = false)
    private String storeRegisterProvince;
    @JSONField(serialize = false)
    private String storeRegisterCity;
    @JSONField(serialize = false)
    private String storeRegisterRegion;
    private String storeRegisterAddress;
    private String trafficPattern;
    private Integer rentalAccreditStatus;
    private String rentalAccreditStatusCH;
    private Integer violationAccreditStatus;
    private String violationAccreditStatusCH;
    private BigDecimal rentalAccreditMoney;
    private BigDecimal violationAccreditMoney;
    private String rentalAccreditStartTime;
    private String rentalAccreditEndTime;
    private String comeCarTime;
    private String repayCarTime;
    private String violationAccreditStartTime;
    private String violationAccreditEndTime;
    private String closeTime;
    private BigDecimal shouldChargeTotal;
    @JSONField(serialize = false)
    private BigDecimal actualAmount;
    private BigDecimal surplusWsMoney;

    public Integer getRentalOrderId() {
        return rentalOrderId;
    }

    public void setRentalOrderId(Integer rentalOrderId) {
        this.rentalOrderId = rentalOrderId;
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

    public Integer getWhetherRealName() {
        return whetherRealName;
    }

    public void setWhetherRealName(Integer whetherRealName) {
        this.whetherRealName = whetherRealName;
    }

    public String getWhetherRealNameCH() {
        if(this.whetherRealName == null) return "";
        MemberEnum.WhetherRealNameStatus enumObj = MemberEnum.WhetherRealNameStatus.getWhetherRealNameStatusByStatus(this.whetherRealName);
        if(enumObj == null){
            return "";
        }else{
            return enumObj.getStatusCH();
        }
    }

    public void setWhetherRealNameCH(String whetherRealNameCH) {
        this.whetherRealNameCH = whetherRealNameCH;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarMainImgUrl() {
        return carMainImgUrl;
    }

    public void setCarMainImgUrl(String carMainImgUrl) {
        this.carMainImgUrl = carMainImgUrl;
    }

    public String getCarSeriesMainImgUrl() {
        return carSeriesMainImgUrl;
    }

    public void setCarSeriesMainImgUrl(String carSeriesMainImgUrl) {
        this.carSeriesMainImgUrl = carSeriesMainImgUrl;
    }

    public String getMainImgUrl() {
        if(this.carId == null){
            return this.carSeriesMainImgUrl;
        }else{
            return this.carMainImgUrl;
        }
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public String getCarMainImgName() {
        return carMainImgName;
    }

    public void setCarMainImgName(String carMainImgName) {
        this.carMainImgName = carMainImgName;
    }

    public String getCarSeriesMainImgName() {
        return carSeriesMainImgName;
    }

    public void setCarSeriesMainImgName(String carSeriesMainImgName) {
        this.carSeriesMainImgName = carSeriesMainImgName;
    }

    public String getMainImgName() {
        if(this.carId == null){
            return this.carSeriesMainImgName;
        }else{
            return this.carMainImgName;
        }
    }

    public void setMainImgName(String mainImgName) {
        this.mainImgName = mainImgName;
    }

    public String getViewMainImgUrl() {
        return viewMainImgUrl;
    }

    public void setViewMainImgUrl(String viewMainImgUrl) {
        this.viewMainImgUrl = viewMainImgUrl;
    }

    public Integer getStatus() {
        if(this.status == null) return status;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarCarStructure() {
        return carCarStructure;
    }

    public void setCarCarStructure(String carCarStructure) {
        this.carCarStructure = carCarStructure;
    }

    public String getCarGearBox() {
        return carGearBox;
    }

    public void setCarGearBox(String carGearBox) {
        this.carGearBox = carGearBox;
    }

    public String getCarCarSeat() {
        return carCarSeat;
    }

    public void setCarCarSeat(String carCarSeat) {
        this.carCarSeat = carCarSeat;
    }

    public String getCarSeriesCarStructure() {
        return carSeriesCarStructure;
    }

    public void setCarSeriesCarStructure(String carSeriesCarStructure) {
        this.carSeriesCarStructure = carSeriesCarStructure;
    }

    public String getCarSeriesGearBox() {
        return carSeriesGearBox;
    }

    public void setCarSeriesGearBox(String carSeriesGearBox) {
        this.carSeriesGearBox = carSeriesGearBox;
    }

    public String getCarSeriesCarSeat() {
        return carSeriesCarSeat;
    }

    public void setCarSeriesCarSeat(String carSeriesCarSeat) {
        this.carSeriesCarSeat = carSeriesCarSeat;
    }

    public String getUseCarInfo() {
        if(this.carId == null){
            return this.carSeriesCarStructure + " " + this.carSeriesGearBox + " 乘坐" + this.carSeriesCarSeat + "人";
        }else{
            return this.carCarStructure + " " + this.carGearBox + " 乘坐" + this.carCarSeat + "人";
        }
    }

    public void setUseCarInfo(String useCarInfo) {
        this.useCarInfo = useCarInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = DateUtil.formatDateAndTime(createTime);
    }

    public Date getAppointmentFetchCarTime() {
        return appointmentFetchCarTime;
    }

    public void setAppointmentFetchCarTime(Date appointmentFetchCarTime) {
        this.appointmentFetchCarTime = appointmentFetchCarTime;
    }

    public Date getAppointmentRepayCarTime() {
        return appointmentRepayCarTime;
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

    public String getAppointmentStoreName() {
        return appointmentStoreName;
    }

    public void setAppointmentStoreName(String appointmentStoreName) {
        this.appointmentStoreName = appointmentStoreName;
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

    public String getStoreRegisterAddress() {
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.ifnull(this.storeRegisterProvince, ""))
                .append(StringUtil.ifnull(this.storeRegisterCity, ""))
                .append(StringUtil.ifnull(this.storeRegisterRegion, ""))
                .append(StringUtil.ifnull(this.storeRegisterAddress, ""));
        return sb.toString();
    }

    public void setStoreRegisterAddress(String storeRegisterAddress) {
        this.storeRegisterAddress = storeRegisterAddress;
    }

    public String getTrafficPattern() {
        return trafficPattern;
    }

    public void setTrafficPattern(String trafficPattern) {
        this.trafficPattern = trafficPattern;
    }

    public Integer getRentalAccreditStatus() {
        return rentalAccreditStatus;
    }

    public void setRentalAccreditStatus(Integer rentalAccreditStatus) {
        this.rentalAccreditStatus = rentalAccreditStatus;
    }

    public String getRentalAccreditStatusCH() {
        if(this.rentalAccreditStatus == null) return "";
        RentalDepositEnum.PaymentStatus enumObj =
                RentalDepositEnum.PaymentStatus.getPaymentStatusByStatus(this.rentalAccreditStatus);
        if(enumObj == null){
            return "";
        }else{
            return enumObj.getStatusCH();
        }
    }

    public void setRentalAccreditStatusCH(String rentalAccreditStatusCH) {
        this.rentalAccreditStatusCH = rentalAccreditStatusCH;
    }

    public Integer getViolationAccreditStatus() {
        return violationAccreditStatus;
    }

    public void setViolationAccreditStatus(Integer violationAccreditStatus) {
        this.violationAccreditStatus = violationAccreditStatus;
    }

    public String getViolationAccreditStatusCH() {
        if(this.violationAccreditStatus == null) return "";
        RentalDepositEnum.PaymentStatus enumObj =
                RentalDepositEnum.PaymentStatus.getPaymentStatusByStatus(this.violationAccreditStatus);
        if(enumObj == null){
            return "";
        }else{
            return enumObj.getStatusCH();
        }
    }

    public void setViolationAccreditStatusCH(String violationAccreditStatusCH) {
        this.violationAccreditStatusCH = violationAccreditStatusCH;
    }

    public BigDecimal getRentalAccreditMoney() {
        return rentalAccreditMoney;
    }

    public void setRentalAccreditMoney(BigDecimal rentalAccreditMoney) {
        this.rentalAccreditMoney = rentalAccreditMoney;
    }

    public BigDecimal getViolationAccreditMoney() {
        return violationAccreditMoney;
    }

    public void setViolationAccreditMoney(BigDecimal violationAccreditMoney) {
        this.violationAccreditMoney = violationAccreditMoney;
    }

    public String getRentalAccreditStartTime() {
        return rentalAccreditStartTime;
    }

    public void setRentalAccreditStartTime(Date rentalAccreditStartTime) {
        this.rentalAccreditStartTime = DateUtil.formatDateAndTime(rentalAccreditStartTime);
    }

    public String getRentalAccreditEndTime() {
        return rentalAccreditEndTime;
    }

    public void setRentalAccreditEndTime(Date rentalAccreditEndTime) {
        this.rentalAccreditEndTime = DateUtil.formatDateAndTime(rentalAccreditEndTime);
    }

    public String getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(Date comeCarTime) {
        this.comeCarTime = DateUtil.formatDateAndTime(comeCarTime);
    }

    public String getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(Date repayCarTime) {
        this.repayCarTime = DateUtil.formatDateAndTime(repayCarTime);
    }

    public String getViolationAccreditStartTime() {
        return violationAccreditStartTime;
    }

    public void setViolationAccreditStartTime(Date violationAccreditStartTime) {
        this.violationAccreditStartTime = DateUtil.formatDateAndTime(violationAccreditStartTime);
    }

    public String getViolationAccreditEndTime() {
        return violationAccreditEndTime;
    }

    public void setViolationAccreditEndTime(Date violationAccreditEndTime) {
        this.violationAccreditEndTime = DateUtil.formatDateAndTime(violationAccreditEndTime);
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = DateUtil.formatDateAndTime(closeTime);
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

    public BigDecimal getSurplusWsMoney() {
        return BigDecimalUtil.subtractAllowNull(this.shouldChargeTotal, this.actualAmount);
    }

    public void setSurplusWsMoney(BigDecimal surplusWsMoney) {
        this.surplusWsMoney = surplusWsMoney;
    }
}
