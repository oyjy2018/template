package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 首页车辆查询条件
 */
public class RentalCarMainQueryDTO extends Pagination implements Serializable {
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 城市
     */
    private String city;
    /**
     * 最低价格
     */
    private Integer lowestPrice;
    /**
     * 最高价格
     */
    private Integer highestPrice;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 企业id
     */
    private Integer companyId;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Integer lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Integer getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Integer highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "RentalCarMainQueryDTO{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", city='" + city + '\'' +
                ", lowestPrice=" + lowestPrice +
                ", highestPrice=" + highestPrice +
                ", brand='" + brand + '\'' +
                ", carLevel='" + carLevel + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }
}
