package com.ydc.commom.view.vo.cgj.rental;

import java.io.Serializable;

/**
 *B端  我负责的门店vo
 * @author
 * @create 2018-11-16 18:50
 **/
public class RentalStoreBQueryVO implements Serializable {

    private String id;

    private String storeName;

    private String theirEnterpriseId;

    private String theirEnterpriseName;

    private String storeRegisterProvinceCode;

    private String storeRegisterProvince;

    private String storeRegisterCityCode;

    private String storeRegisterCity;

    private String storeRegisterRegionCode;

    private String storeRegisterRegion;

    private String detailsAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTheirEnterpriseId() {
        return theirEnterpriseId;
    }

    public void setTheirEnterpriseId(String theirEnterpriseId) {
        this.theirEnterpriseId = theirEnterpriseId;
    }

    public String getTheirEnterpriseName() {
        return theirEnterpriseName;
    }

    public void setTheirEnterpriseName(String theirEnterpriseName) {
        this.theirEnterpriseName = theirEnterpriseName;
    }

    public String getStoreRegisterProvinceCode() {
        return storeRegisterProvinceCode;
    }

    public void setStoreRegisterProvinceCode(String storeRegisterProvinceCode) {
        this.storeRegisterProvinceCode = storeRegisterProvinceCode;
    }

    public String getStoreRegisterProvince() {
        return storeRegisterProvince;
    }

    public void setStoreRegisterProvince(String storeRegisterProvince) {
        this.storeRegisterProvince = storeRegisterProvince;
    }

    public String getStoreRegisterCityCode() {
        return storeRegisterCityCode;
    }

    public void setStoreRegisterCityCode(String storeRegisterCityCode) {
        this.storeRegisterCityCode = storeRegisterCityCode;
    }

    public String getStoreRegisterCity() {
        return storeRegisterCity;
    }

    public void setStoreRegisterCity(String storeRegisterCity) {
        this.storeRegisterCity = storeRegisterCity;
    }

    public String getStoreRegisterRegionCode() {
        return storeRegisterRegionCode;
    }

    public void setStoreRegisterRegionCode(String storeRegisterRegionCode) {
        this.storeRegisterRegionCode = storeRegisterRegionCode;
    }

    public String getStoreRegisterRegion() {
        return storeRegisterRegion;
    }

    public void setStoreRegisterRegion(String storeRegisterRegion) {
        this.storeRegisterRegion = storeRegisterRegion;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }
}
