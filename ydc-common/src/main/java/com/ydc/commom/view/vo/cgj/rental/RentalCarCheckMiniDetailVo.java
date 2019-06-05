package com.ydc.commom.view.vo.cgj.rental;

import java.util.Date;

public class RentalCarCheckMiniDetailVo {

    /**
     * 车辆id
     */
    private Integer carId;
    /**
     * 车牌号
     */
    private String carPlate;
    /**
     * 车架号
     */
    private String vin;
    /**
     * 上牌日期
     */
    private String upPlateDate;
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 行驶证照片真实地址
     */
    private String drivingLicenseImg;
    /**
     * 行驶证照片名称
     */
    private String drivingLicenseImgName;
    /**
     * 行驶证照片地址
     */
    private String drivingLicenseImgUrl;
    /**
     * 审核结果
     */
    private Integer checkResult;
    /**
     * 退回原因
     */
    private String refuseReason;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getUpPlateDate() {
        return upPlateDate;
    }

    public void setUpPlateDate(String upPlateDate) {
        this.upPlateDate = upPlateDate;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public String getDrivingLicenseImg() {
        return drivingLicenseImg;
    }

    public void setDrivingLicenseImg(String drivingLicenseImg) {
        this.drivingLicenseImg = drivingLicenseImg;
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

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    @Override
    public String toString() {
        return "RentalCarCheckMiniDetailVo{" +
                "carId=" + carId +
                ", carPlate='" + carPlate + '\'' +
                ", vin='" + vin + '\'' +
                ", upPlateDate='" + upPlateDate + '\'' +
                ", carLevel='" + carLevel + '\'' +
                ", drivingLicenseImg='" + drivingLicenseImg + '\'' +
                ", drivingLicenseImgName='" + drivingLicenseImgName + '\'' +
                ", drivingLicenseImgUrl='" + drivingLicenseImgUrl + '\'' +
                ", checkResult=" + checkResult +
                ", refuseReason='" + refuseReason + '\'' +
                '}';
    }
}