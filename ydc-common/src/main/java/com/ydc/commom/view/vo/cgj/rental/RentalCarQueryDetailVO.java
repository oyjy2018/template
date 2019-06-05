package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 外部详情车辆查询
 */
public class RentalCarQueryDetailVO extends RentalCarQueryVO {

    /**
     * 门店地址
     */
    private String storeAddress;
    /**
     * 车型照片真实地址
     */
    private String modelImg;
    /**
     * 车型照片名称
     */
    private String modelImgName;
    /**
     * 车型照片url
     */
    private String modelImgUrl;
    /**
     * 行驶证照片名称
     */
    private String drivingLicenseImgName;
    /**
     * 行驶证照片地址
     */
    private String drivingLicenseImgUrl;
    /**
     * 行驶证照片真实地址
     */
    private String drivingLicenseImg;
    /**
     * 车架号
     */
    private String vin;
    /**
     * 车座位数
     */
    private Integer carSeat;
    /**
     * 车结构
     */
    private String carStructure;
    /**
     * 上牌日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date upPlateDate;
    /**
     * 工作日租金
     */
    private Integer workdayRent;
    /**
     * 节假日租金
     */
    private Integer weekendRent;
    /**
     * 节假日租金
     */
    private Integer holidayRent;
    /**
     * 每日服务费
     */
    private Integer dayServiceCharge;
    /**
     * 审核失败原因
     */
    private String refuseReason;

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getModelImg() {
        return modelImg;
    }

    public void setModelImg(String modelImg) {
        this.modelImg = modelImg;
    }

    public String getModelImgName() {
        return modelImgName;
    }

    public void setModelImgName(String modelImgName) {
        this.modelImgName = modelImgName;
    }

    public String getModelImgUrl() {
        return modelImgUrl;
    }

    public void setModelImgUrl(String modelImgUrl) {
        this.modelImgUrl = modelImgUrl;
    }

    public String getDrivingLicenseImgName() {
        return drivingLicenseImgName;
    }

    public void setDrivingLicenseImgName(String drivingLicenseImgName) {
        this.drivingLicenseImgName = drivingLicenseImgName;
    }

    public String getDrivingLicenseImgUrl() {
        return drivingLicenseImgUrl;
    }

    public void setDrivingLicenseImgUrl(String drivingLicenseImgUrl) {
        this.drivingLicenseImgUrl = drivingLicenseImgUrl;
    }

    public String getDrivingLicenseImg() {
        return drivingLicenseImg;
    }

    public void setDrivingLicenseImg(String drivingLicenseImg) {
        this.drivingLicenseImg = drivingLicenseImg;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(Integer carSeat) {
        this.carSeat = carSeat;
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public Date getUpPlateDate() {
        return upPlateDate;
    }

    public void setUpPlateDate(Date upPlateDate) {
        this.upPlateDate = upPlateDate;
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

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    @Override
    public String toString() {
        return "RentalCarQueryDetailVO{" +
                "storeAddress='" + storeAddress + '\'' +
                ", modelImg='" + modelImg + '\'' +
                ", modelImgName='" + modelImgName + '\'' +
                ", modelImgUrl='" + modelImgUrl + '\'' +
                ", drivingLicenseImgName='" + drivingLicenseImgName + '\'' +
                ", drivingLicenseImgUrl='" + drivingLicenseImgUrl + '\'' +
                ", drivingLicenseImg='" + drivingLicenseImg + '\'' +
                ", vin='" + vin + '\'' +
                ", carSeat=" + carSeat +
                ", carStructure='" + carStructure + '\'' +
                ", upPlateDate=" + upPlateDate +
                ", workdayRent=" + workdayRent +
                ", weekendRent=" + weekendRent +
                ", holidayRent=" + holidayRent +
                ", dayServiceCharge=" + dayServiceCharge +
                ", refuseReason='" + refuseReason + '\'' +
                '}';
    }
}
