package com.ydc.commom.view.vo.cgj.rental;

/**
 * 首页车辆查询列表
 */
public class RentalCarMainQueryVO {
    /**
     * 发布表id
     */
    private Integer publishId;
    /**
     * 门店地址
     */
    private String storeAddress;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 车系id
     */
    private Integer seriesId;
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
     * 车结构
     */
    private String carStructure;
    /**
     * 车座位数
     */
    private Integer carSeat;
    /**
     * 主图真实地址
     */
    private String mainImg;
    /**
     * 主图名称
     */
    private String mainImgName;
    /**
     * 主图url
     */
    private String mainImgUrl;
    /**
     * 工作日租金
     */
    private Integer workdayRent;

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
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

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public Integer getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(Integer carSeat) {
        this.carSeat = carSeat;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getMainImgName() {
        return mainImgName;
    }

    public void setMainImgName(String mainImgName) {
        this.mainImgName = mainImgName;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public Integer getWorkdayRent() {
        return workdayRent;
    }

    public void setWorkdayRent(Integer workdayRent) {
        this.workdayRent = workdayRent;
    }

    @Override
    public String toString() {
        return "RentalCarMainQueryVO{" +
                "publishId=" + publishId +
                ", storeAddress='" + storeAddress + '\'' +
                ", brand='" + brand + '\'' +
                ", seriesId=" + seriesId +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                ", carLevel='" + carLevel + '\'' +
                ", carStructure='" + carStructure + '\'' +
                ", carSeat=" + carSeat +
                ", mainImg='" + mainImg + '\'' +
                ", mainImgName='" + mainImgName + '\'' +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                ", workdayRent=" + workdayRent +
                '}';
    }
}
