package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.commom.enums.common.CommonEnum;
import com.ydc.commom.enums.rental.CommCarEnum;

/**
 * 车辆详情查询实体类
 */
public class CommCarSimpleVO {

    /*-----数据库直接返回字段  start-----*/
    private Integer carId;

    //门店id
    private Integer storeId;

    //门店名
    private String store;

    //车牌
    private String carPlate;

    //油量
    private Integer oilMass;

    //里程数
    private Integer mileage;

    //品牌
    private String brand;

    //车系
    private String series;

    //车型
    private String model;
    /*-----数据库直接返回字段  end-----*/

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getOilMass() {
        return oilMass;
    }

    public void setOilMass(Integer oilMass) {
        this.oilMass = oilMass;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
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
}
