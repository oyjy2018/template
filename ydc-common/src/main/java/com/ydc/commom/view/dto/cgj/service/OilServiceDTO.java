package com.ydc.commom.view.dto.cgj.service;

import java.io.Serializable;

public class OilServiceDTO implements Serializable {
    private String province;

    private String city;

    public String getProvince() {

        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
