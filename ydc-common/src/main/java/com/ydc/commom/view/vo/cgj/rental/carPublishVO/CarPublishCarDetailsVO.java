package com.ydc.commom.view.vo.cgj.rental.carPublishVO;

import java.io.Serializable;

/**
 * 发布信息车辆
 *
 * @author
 * @create 2019-01-25 17:16
 **/
public class CarPublishCarDetailsVO implements Serializable {
    private static final long serialVersionUID = -1328001502525082681L;

    private String carLevel;
    private String carStructure;
    private String carSeat;
    private Integer carSeriesId;
    private Integer storeId;

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

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
