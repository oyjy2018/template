package com.ydc.commom.view.vo.cgj.rental;

/**
 * 我的车辆查询列表
 */
public class RentalCarMyQueryVO {
    /**
     * 门店名称
     */
    private String storeName;
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
     * 车数量
     */
    private Integer carNum;
    /**
     * 主图真实地址
     */
    private String mainImg;
    /**
     * 主图名称
     */
    private String mainImgName;
    /**
     * 主图地址
     */
    private String mainImgUrl;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    @Override
    public String toString() {
        return "RentalCarMyQueryVO{" +
                "storeName='" + storeName + '\'' +
                ", model='" + model + '\'' +
                ", carLevel='" + carLevel + '\'' +
                ", carStructure='" + carStructure + '\'' +
                ", carSeat=" + carSeat +
                ", carNum=" + carNum +
                ", mainImg='" + mainImg + '\'' +
                ", mainImgName='" + mainImgName + '\'' +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                '}';
    }
}
