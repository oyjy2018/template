package com.ydc.commom.view.dto.cgj.rental;

import java.io.Serializable;

/**
 * @author
 * @create 2018-11-21 20:59
 **/
public class CommCarDTO implements Serializable {
    private static final long serialVersionUID = -8221136146208928151L;


    private String carLevel;//车辆等级
    private String brand;//车辆品牌
    private String series;//车辆车系
    private String model;//车辆车型
    private Integer orderId;//租车订单
    private Integer storeId;//门店id

    public CommCarDTO() {
    }

    public CommCarDTO(String carLevel, String brand, String series, String model, Integer orderId) {
        this.carLevel = carLevel;
        this.brand = brand;
        this.series = series;
        this.model = model;
        this.orderId = orderId;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
