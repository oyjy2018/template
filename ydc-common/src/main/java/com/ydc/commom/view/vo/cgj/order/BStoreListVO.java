package com.ydc.commom.view.vo.cgj.order;

import com.ydc.commom.enums.cgj.BCarServiceShopEnum;
import com.ydc.commom.enums.common.CommonEnum;

import java.io.Serializable;

/**
 * B端门店集合
 *
 * @author
 * @create 2018-12-07 15:04
 **/
public class BStoreListVO implements Serializable {
    private static final long serialVersionUID = 1086867227257008198L;

    private Integer storeId;
    private String storeName;
    private String storeLogo;
    private String storeCode;
    private String storeIdentifying;
    private String servicePhone;
    private String region;
    private String detailsAddress;
    private String businessHours;
    private String statusName;
    private Integer status;
    private String whetherPutawayName;
    private Integer whetherPutaway;
    private String serveGather;
    private String longitude;
    private String latitude;
    private String category;

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

    public String getStatusName() {
        return CommonEnum.ValidEnum.getCodeName(this.status);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWhetherPutawayName() {
        return BCarServiceShopEnum.getCodeName(this.whetherPutaway);
    }

    public void setWhetherPutawayName(String whetherPutawayName) {
        this.whetherPutawayName = whetherPutawayName;
    }

    public Integer getWhetherPutaway() {
        return whetherPutaway;
    }

    public void setWhetherPutaway(Integer whetherPutaway) {
        this.whetherPutaway = whetherPutaway;
    }

    public String getServeGather() {
        return serveGather;
    }

    public void setServeGather(String serveGather) {
        this.serveGather = serveGather;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
