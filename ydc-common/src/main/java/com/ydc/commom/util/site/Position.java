package com.ydc.commom.util.site;

import java.io.Serializable;

/**
 * @author
 * @create 2018-10-31 11:15
 **/
public class Position implements Serializable {

    private static final long serialVersionUID = 7269130831220804359L;
    private double minLat;//最小纬度
    private double maxLat;//最大纬度
    private double minLng;//最小经度
    private double maxLng;//最大经度

    public Position(){}

    public Position(double minLat, double maxLat, double minLng, double maxLng) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLng = minLng;
        this.maxLng = maxLng;
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMinLng() {
        return minLng;
    }

    public void setMinLng(double minLng) {
        this.minLng = minLng;
    }

    public double getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(double maxLng) {
        this.maxLng = maxLng;
    }
}
