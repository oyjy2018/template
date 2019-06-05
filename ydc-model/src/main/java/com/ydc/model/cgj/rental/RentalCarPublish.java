package com.ydc.model.cgj.rental;

import java.util.Date;

/**
 * 外部车辆发布表
 * t_rental_car_publish
 */
public class RentalCarPublish {
    /**
     * 主键id
     */
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
     * 工作日租金
     */
    private Integer workdayRent;
    /**
     * 周末租金
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
     * 发布时间
     */
    private Integer publishNum;
    /**
     * 版本号
     */
    private Integer version;

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

    public Integer getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(Integer publishNum) {
        this.publishNum = publishNum;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
        return "RentalCarPublish{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", storeId=" + storeId +
                ", workdayRent=" + workdayRent +
                ", weekendRent=" + weekendRent +
                ", holidayRent=" + holidayRent +
                ", dayServiceCharge=" + dayServiceCharge +
                ", publishNum=" + publishNum +
                ", version=" + version +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                '}';
    }
}
