package com.ydc.model.cgj.rental;

import java.util.Date;

/**
 * 外部车辆
 * t_rental_car
 */
public class RentalCar {

    private Integer id;
    /**
     * 车辆状态 0待审核 1待发布 2己发布 3审核失败 4己出租
     */
    private Integer status;
    /**
     * 企业id
     */
    private Integer companyId;
    /**
     * 所属门店id
     */
    private Integer storeId;
    /**
     * 车牌号
     */
    private String carPlate;
    /**
     * 车型id
     */
    private Integer carSeriesId;
    /**
     * 行驶证照片文件名
     */
    private String drivingLicenseImgName;
    /**
     * 行驶证照片地址
     */
    private String drivingLicenseImgUrl;
    /**
     * 车结构
     */
    private String carStructure;
    /**
     * 车座位数
     */
    private String carSeat;
    /**
     * 车架号
     */
    private String vin;
    /**
     * 上牌日期
     */
    private Date upPlateDate;
    /**
     * 车等级
     */
    private String carLevel;

    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getUpPlateDate() {
        return upPlateDate;
    }

    public void setUpPlateDate(Date upPlateDate) {
        this.upPlateDate = upPlateDate;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "RentalCar{" +
                "id=" + id +
                ", status=" + status +
                ", companyId=" + companyId +
                ", storeId=" + storeId +
                ", carPlate='" + carPlate + '\'' +
                ", carSeriesId=" + carSeriesId +
                ", drivingLicenseImgName='" + drivingLicenseImgName + '\'' +
                ", drivingLicenseImgUrl='" + drivingLicenseImgUrl + '\'' +
                ", carStructure='" + carStructure + '\'' +
                ", carSeat=" + carSeat +
                ", vin='" + vin + '\'' +
                ", upPlateDate=" + upPlateDate +
                ", carLevel='" + carLevel + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                '}';
    }
}
