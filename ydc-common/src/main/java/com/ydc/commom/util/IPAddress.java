package com.ydc.commom.util;

import java.io.Serializable;

/**
 * 根据IP获取请求地址
 *
 * @author gongjin
 * @create 2017-05-08 12:44
 **/
public class IPAddress implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1472110837772809872L;
    private static final String None = "None";

    /**
     * ip
     */
    private String ip;
    /**
     * 国家
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 运营商
     */
    private String carrier;

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
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
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getCarrier() {
        return carrier;
    }
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }


    public String getAddress() {
        String address = "";
        if(isNotEmpty(country)) {
            address += country;
            if(isNotEmpty(province)) {
                address += "," + province;
                if(isNotEmpty(city)) {
                    address += "," + city;
                    if(isNotEmpty(district)) {
                        address += "," + district;
                    }
                }
            }
        }
        return address;
    }

    private boolean isNotEmpty(String str) {
        if(str != null && !str.trim().equals("") && !str.trim().equals(None)) {
            return true;
        }
        return false;
    }

}
