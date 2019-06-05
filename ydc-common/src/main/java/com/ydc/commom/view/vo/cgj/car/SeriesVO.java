package com.ydc.commom.view.vo.cgj.car;

import java.io.Serializable;

/**
 * 车系
 *
 * @author
 * @create 2018-12-13 13:36
 **/
public class SeriesVO implements Serializable {
    private static final long serialVersionUID = 1390959739357190395L;

    private Integer id;
    private String brand;
    private String series;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
