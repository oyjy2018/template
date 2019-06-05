package com.ydc.commom.view.vo.cgj.rentalEnterprise;

import java.io.Serializable;

/**
 * @author
 * @create 2019-01-04 17:19
 **/
public class RentalEnterpriseCarVO implements Serializable {
    private static final long serialVersionUID = 8293891549168380659L;

    private String brandName;
    private String brand;//车辆品牌
    private String seriesName;
    private String series;//车辆车型
    private String modelName;
    private String model;//车辆车系

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
