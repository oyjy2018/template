package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.common.CommonEnum;
import com.ydc.commom.enums.rental.CommCarEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 外部车辆查询列表
 */
public class RentalCarQueryVO {

    private Integer id;
    /**
     * 所属企业
     */
    private String companyName;
    /**
     * 所属门店
     */
    private String storeName;
    /**
     * 车牌号
     */
    private String carPlate;
    /**
     * 车辆品牌
     */
    private String brand;
    /**
     * 车系
     */
    private String series;
    /**
     * 车型
     */
    private String model;
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 日均租金
     */
    private Integer avgRent;
    /**
     * 车辆状态 0待审核 1待发布 2己发布 3审核失败 4己出租
     */
    private Integer status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
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

    public Integer getAvgRent() {
        return avgRent;
    }

    public void setAvgRent(Integer avgRent) {
        this.avgRent = avgRent;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RentalCarQueryVO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", carPlate='" + carPlate + '\'' +
                ", brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                ", carLevel='" + carLevel + '\'' +
                ", avgRent=" + avgRent +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
