package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 外部车辆查询条件
 */
public class RentalCarQueryDTO extends Pagination implements Serializable {

    /**
     * 车牌号
     */
    private String carPlate;
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
     * 状态
     */
    private Integer status;

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RentalCarQueryDTO{" +
                "carPlate='" + carPlate + '\'' +
                ", companyName='" + companyName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                ", status=" + status +
                '}';
    }
}
