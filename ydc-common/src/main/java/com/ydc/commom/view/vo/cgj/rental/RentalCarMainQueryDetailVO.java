package com.ydc.commom.view.vo.cgj.rental;

import java.math.BigDecimal;
import java.util.List;

/**
 * 首页车辆查询列表
 */
public class RentalCarMainQueryDetailVO {
    /**
     * 车结构
     */
    private String carStructure;
    /**
     * 车座位数
     */
    private Integer carSeat;
    /**
     * 门店id
     */
    private Integer storeId;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 车数量
     */
    private Integer carNum;
    /**
     * 主图真实地址
     */
    private String mainImg;
    /**
     * 主图url
     */
    private String mainImgUrl;
    /**
     * 主图名称
     */
    private String mainImgName;
    /**
     * 发布id
     */
    private Integer publishId;
    /**
     * 企业id
     */
    private Integer companyId;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 工作日租金
     */
    private Integer workdayRent;
    /**
     * 节假日租金
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
     * 预授权
     */
    private BigDecimal preAuthorization;
    /**
     * 品牌
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
     * 每天价格
     */
    List<RentalCarDayPriceVO> priceList;

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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public String getMainImgName() {
        return mainImgName;
    }

    public void setMainImgName(String mainImgName) {
        this.mainImgName = mainImgName;
    }

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public BigDecimal getPreAuthorization() {
        return preAuthorization;
    }

    public void setPreAuthorization(BigDecimal preAuthorization) {
        this.preAuthorization = preAuthorization;
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

    public List<RentalCarDayPriceVO> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<RentalCarDayPriceVO> priceList) {
        this.priceList = priceList;
    }

    @Override
    public String toString() {
        return "RentalCarMainQueryDetailVO{" +
                "carStructure='" + carStructure + '\'' +
                ", carSeat=" + carSeat +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", carLevel='" + carLevel + '\'' +
                ", carNum=" + carNum +
                ", mainImg='" + mainImg + '\'' +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                ", mainImgName='" + mainImgName + '\'' +
                ", publishId=" + publishId +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", workdayRent=" + workdayRent +
                ", weekendRent=" + weekendRent +
                ", holidayRent=" + holidayRent +
                ", dayServiceCharge=" + dayServiceCharge +
                ", preAuthorization=" + preAuthorization +
                ", brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                ", priceList=" + priceList +
                '}';
    }
}
