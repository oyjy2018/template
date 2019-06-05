package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.Pagination;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @create 2018-11-16 18:50
 **/
public class RentalCompanyStoreUpdateDTO extends Pagination implements Serializable {

    @Attribute(name = "门店id",required = true,emptyStringVerify = true,isNum = true)
    private String id;

    @Attribute(name = "门店名称",required = true,emptyStringVerify = true)
    private String storeName;

    @Attribute(name = "门店地址省code",required = true,emptyStringVerify = true)
    private String storeRegisterProvinceCode;

    @Attribute(name = "门店地址省",required = true,emptyStringVerify = true)
    private String storeRegisterProvince;

    @Attribute(name = "门店地址市code",required = true,emptyStringVerify = true)
    private String storeRegisterCityCode;

    @Attribute(name = "门店地址市",required = true,emptyStringVerify = true)
    private String storeRegisterCity;

    @Attribute(name = "门店地址区code",required = true,emptyStringVerify = true)
    private String storeRegisterRegionCode;

    @Attribute(name = "门店地址区",required = true,emptyStringVerify = true)
    private String storeRegisterRegion;

    @Attribute(name = "门店详细地址",required = true,emptyStringVerify = true)
    private String detailsAddress;

    //更新时间
    private Date updateTime = new Date();

    //
    private Integer updateBy;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}
