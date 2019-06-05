package com.ydc.commom.view.dto.cgj.rental;

/**
 * 新增外部车辆查
 */
public class RentalCarInfoAddDTO {

    /**
     * 车牌号
     */
    private String carPlate;
    /**
     * 车架号
     */
    private String vin;
    /**
     * 门店id
     */
    private String upPlateDate;
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 行驶证照片
     */
    private String drivingLicenseImg;

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

    @Override
    public String toString() {
        return "RentalCarInfoAddDTO{" +
                "carPlate='" + carPlate + '\'' +
                ", vin='" + vin + '\'' +
                ", upPlateDate='" + upPlateDate + '\'' +
                ", carLevel='" + carLevel + '\'' +
                ", drivingLicenseImg='" + drivingLicenseImg + '\'' +
                '}';
    }
}
