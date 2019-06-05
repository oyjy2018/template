package com.ydc.commom.view.vo.cgj.rental;

import java.util.List;

/**
 * 城市列表
 */
public class RentalCarMainCityVo {
    /**
     * 城市列表
     */
    private List<String> cities;
    /**
     * 热门城市
     */
    private List<String> hotcities;

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getHotcities() {
        return hotcities;
    }

    public void setHotcities(List<String> hotcities) {
        this.hotcities = hotcities;
    }

    @Override
    public String toString() {
        return "RentalCarMainCityVo{" +
                ", cities=" + cities +
                ", hotcities=" + hotcities +
                '}';
    }
}