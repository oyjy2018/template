package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 外部车辆列表查询
 */
public class RentalCarCheckQueryVO {

    private Integer id;
    /**
     * 所属企业
     */
    private String companyName;
    /**
     * 所属门店
     */
    private String storeName;
    /**
     * 车辆品牌
     */
    private String brand;
    /**
     * 车系
     */
    private String series;
    /**
     * 车型
     */
    private String model;
    /**
     * 车台数
     */
    private Integer carNum;
    /**
     * 提交审核时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "RentalCarCheckQueryVO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                ", carNum=" + carNum +
                ", submitTime='" + submitTime + '\'' +
                '}';
    }
}
