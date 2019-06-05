package com.ydc.commom.view.vo.cgj.rentalEnterprise;


import java.io.Serializable;

/**
 * @author
 * @create 2019-01-26 13:17
 **/
public class RentalCarByCarPlateVO implements Serializable {
    private static final long serialVersionUID = -2516345070115026645L;

    private String carPlate;
    private String carLevel;
    private String brand;
    private String series;
    private String model;
    private Integer carSeriesId;

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
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

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
    }
}
