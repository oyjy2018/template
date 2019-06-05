package com.ydc.model.cgj.rental;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 外部车辆审核表
 * t_rental_car_check
 */
public class RentalCarCheck {

    private Integer id;

    /**
     * 企业id
     */
    private Integer companyId;
    /**
     * 门店id
     */
    private Integer storeId;
    /**
     * 车型id
     */
    private Integer carSeriesId;
    /**
     * 车结构
     */
    private String carStructure;
    /**
     * 座位数
     */
    private String carSeat;
    /**
     * 车辆数
     */
    private Integer carNum;
    /**
     * 每审核时间
     */
    private Date checkTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核状态 0未审核 1己审核
     */
    private Integer checkStatus;

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

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
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

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
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
        return "RentalCarCheck{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", storeId=" + storeId +
                ", carSeriesId=" + carSeriesId +
                ", carStructure='" + carStructure + '\'' +
                ", carSeat=" + carSeat +
                ", carNum=" + carNum +
                ", checkTime=" + checkTime +
                ", remark='" + remark + '\'' +
                ", checkStatus=" + checkStatus +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                '}';
    }
}
