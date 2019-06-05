package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 7108575964341895018L;
    /**
     * t_vehicle.id
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private Integer id;

    /**
     * t_vehicle.member_id (会员ID)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private Integer memberId;

    /**
     * t_vehicle.car_plate (车牌号)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private String carPlate;

    /**
     * t_vehicle.brand_code (车辆品牌code)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private String brandCode;

    /**
     * t_vehicle.brand (车辆品牌)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private String brand;

    /**
     * t_vehicle.series_code (车系code)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private String seriesCode;

    /**
     * t_vehicle.series (车系)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private String series;

    /**
     * t_vehicle.car_version_code (车版本code)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private String carVersionCode;

    /**
     * t_vehicle.car_version (车版本)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private String carVersion;

    /**
     * t_vehicle.status (状态（1：正常；0：解绑）)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private Integer status;

    /**
     * t_vehicle.create_time (创建时间)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private Date createTime;

    /**
     * t_vehicle.create_by (创建人)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private Integer createBy;

    /**
     * t_vehicle.update_time (修改时间)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private Date updateTime;

    /**
     * t_vehicle.update_by (修改人)
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    private Integer updateBy;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 发动机号
     */
    private String engineNumber;

    /**
     * 车架号
     */
    private String frameNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCarVersionCode() {
        return carVersionCode;
    }

    public void setCarVersionCode(String carVersionCode) {
        this.carVersionCode = carVersionCode;
    }

    public String getCarVersion() {
        return carVersion;
    }

    public void setCarVersion(String carVersion) {
        this.carVersion = carVersion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }
}