package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 外部车辆查询条件
 */
public class RentalCarCheckQueryDTO extends Pagination implements Serializable {

    /**
     * 所属企业id
     */
    private String companyName;
    /**
     * 所属门店id
     */
    private String storeName;
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
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "RentalCarCheckQueryDTO{" +
                ", companyName='" + companyName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
