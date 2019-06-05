package com.ydc.model.cgj.rental;

public class RentalCarPublishNum {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 发布id
     */
    private Integer publishId;
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 车系id
     */
    private Integer carSeriesId;
    /**
     * 车品牌
     */
    private String carBrand;
    /**
     * 车系
     */
    private String carSeries;
    /**
     * 车型
     */
    private String carModel;
    /**
     * 车数量
     */
    private Integer carNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel == null ? null : carLevel.trim();
    }

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand == null ? null : carBrand.trim();
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries == null ? null : carSeries.trim();
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel == null ? null : carModel.trim();
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }
}