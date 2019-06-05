package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.Pagination;
import com.ydc.model.cgj.car.CommCarImg;

import java.io.Serializable;
import java.util.List;

/**
 * 车辆查询实体类
 */
public class CommCarQueryDTO extends Pagination implements Serializable {
    //车牌
    private String carPlate;

    //门店id
    private Integer storeId;

    //品牌
    private String brand;

    //车系
    private String series;

    //车型
    private String model;

    //车等级
    private String carLevel;

    //启用状态
    private String useStatus;

    //运营状态
    private String operationStatus;

    //车辆来源
    private String source;

    //门店省份
    private String storeRegisterProvinceCode;

    //门店市
    private String  storeRegisterCityCode;

    //排序依据
    private String orderBy;


    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStoreRegisterProvinceCode() {
        return storeRegisterProvinceCode;
    }

    public void setStoreRegisterProvinceCode(String storeRegisterProvinceCode) {
        this.storeRegisterProvinceCode = storeRegisterProvinceCode;
    }

    public String getStoreRegisterCityCode() {
        return storeRegisterCityCode;
    }

    public void setStoreRegisterCityCode(String storeRegisterCityCode) {
        this.storeRegisterCityCode = storeRegisterCityCode;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
