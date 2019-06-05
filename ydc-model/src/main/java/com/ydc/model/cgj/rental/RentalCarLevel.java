package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;

public class RentalCarLevel implements Serializable {
    private static final long serialVersionUID = 1476520689024388684L;
    /**
     * t_rental_car_level.id
     * @ibatorgenerated 2019-01-23 18:11:42
     */
    private Integer id;

    /**
     * t_rental_car_level.car_level (车等级)
     * @ibatorgenerated 2019-01-23 18:11:42
     */
    private String carLevel;

    /**
     * t_rental_car_level.pre_authorization (预授权金额)
     * @ibatorgenerated 2019-01-23 18:11:42
     */
    private BigDecimal preAuthorization;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public BigDecimal getPreAuthorization() {
        return preAuthorization;
    }

    public void setPreAuthorization(BigDecimal preAuthorization) {
        this.preAuthorization = preAuthorization;
    }


}