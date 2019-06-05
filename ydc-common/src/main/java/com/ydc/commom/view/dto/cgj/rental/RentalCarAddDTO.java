package com.ydc.commom.view.dto.cgj.rental;

import java.util.List;

/**
 * 新增外部车辆查
 */
public class RentalCarAddDTO {

    /**
     * 车型id
     */
    private Integer carSeriesId;
    /**
     * 车座位数
     */
    private Integer carSeat;
    /**
     * 门店id
     */
    private Integer storeId;

    private List<RentalCarInfoAddDTO> carList;

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
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

    public List<RentalCarInfoAddDTO> getCarList() {
        return carList;
    }

    public void setCarList(List<RentalCarInfoAddDTO> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "RentalCarAddDTO{" +
                "carSeriesId=" + carSeriesId +
                ", carSeat=" + carSeat +
                ", storeId=" + storeId +
                ", carList=" + carList +
                '}';
    }
}
