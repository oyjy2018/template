package com.ydc.commom.view.vo.cgj.rental;

import java.util.List;
import java.util.Objects;

/**
 * 我的车辆待发布列表
 */
public class RentalCarListVO {
    /**
     * 车型
     */
    private String model;
    /**
     * 车结构
     */
    private String carStructure;
    /**
     * 车座位数
     */
    private Integer carSeat;

    public RentalCarListVO(String model, String carStructure, Integer carSeat) {
        this.model = model;
        this.carStructure = carStructure;
        this.carSeat = carSeat;
    }

    public RentalCarListVO() {
    }

    /**
     * 车辆列表
     */
    List<RentalCarPublisMiniVO> carList;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public List<RentalCarPublisMiniVO> getCarList() {
        return carList;
    }

    public void setCarList(List<RentalCarPublisMiniVO> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "RentalCarPublishVO{" +
                "model='" + model + '\'' +
                ", carStructure='" + carStructure + '\'' +
                ", carSeat=" + carSeat +
                ", carList=" + carList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalCarListVO that = (RentalCarListVO) o;
        return Objects.equals(model, that.model) &&
                Objects.equals(carStructure, that.carStructure) &&
                Objects.equals(carSeat, that.carSeat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, carStructure, carSeat);
    }
}
