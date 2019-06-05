package com.ydc.commom.view.vo.cgj.rental;

import java.io.Serializable;

/**
 * @author
 * @create 2018-11-22 11:15
 **/
public class CarPlateVO implements Serializable {
    private static final long serialVersionUID = -6950620628333130762L;


    private Integer carPlateId;//车辆id
    private String carPlate;//车牌号

    public Integer getCarPlateId() {
        return carPlateId;
    }

    public void setCarPlateId(Integer carPlateId) {
        this.carPlateId = carPlateId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

}
