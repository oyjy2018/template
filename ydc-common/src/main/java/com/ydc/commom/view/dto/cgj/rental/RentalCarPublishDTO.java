package com.ydc.commom.view.dto.cgj.rental;


import java.io.Serializable;

public class RentalCarPublishDTO implements Serializable {
    private static final long serialVersionUID = -2012160144259859378L;
    private Integer publishId;

    private String brand;//品牌
    private String series;//车系
    private String model;//车型
    private String carLevel;//车等级
    private Integer seriesId;//车型id

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
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

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }
}
