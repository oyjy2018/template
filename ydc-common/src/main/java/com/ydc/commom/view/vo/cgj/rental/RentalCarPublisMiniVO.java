package com.ydc.commom.view.vo.cgj.rental;

/**
 * 车辆列表信息
 */
public class RentalCarPublisMiniVO extends RentalCarMiniVO {
    /**
     * 车等级
     */
    private String carLevel;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 门店id
     */
    private Integer storeId;
    /**
     * 车系id
     */
    private Integer seriesId;

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    @Override
    public String toString() {
        return "RentalCarPublisMiniVO{" +
                "carLevel='" + carLevel + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeId='" + storeId + '\'' +
                ", seriesId='" + seriesId + '\'' +
                '}';
    }
}