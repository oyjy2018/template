package com.ydc.commom.view.vo.cgj;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 门店
 *
 * @author
 * @create 2018-10-31 12:08
 **/
public class CarStoreVO implements Serializable {
    private static final long serialVersionUID = -7335820580713279376L;

    private Integer storeId;//门店id
    private String storeName;//门店名
    private String storeLogo;//门店logo
    private String storeCode;//门店编码
    private String storeIdentifying;//商家标识
    private String servicePhone;//客服电话
    private String region;//省市区
    private String detailsAddress;//详细地址
    private String businessHours; //预约时间
    private Integer salesVolume;//销量
    private String serveGather;//服务集合
    private BigDecimal longitude; //经度
    private BigDecimal latitude;//纬度
    private Double distance;//距离
    private String category;//业务开展

    public CarStoreVO() {
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreIdentifying() {
        return storeIdentifying;
    }

    public void setStoreIdentifying(String storeIdentifying) {
        this.storeIdentifying = storeIdentifying;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getServeGather() {
        return serveGather;
    }

    public void setServeGather(String serveGather) {
        this.serveGather = serveGather;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
