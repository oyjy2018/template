package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * @author
 * @create 2018-11-16 18:50
 **/
public class RentalStoreDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = -8578440837038537649L;

    private String storeRegisterProvinceCode;//门店地址省

    private String storeRegisterCityCode;//门店地址市

    private String storeRegisterRegionCode;//门店地址区

    private String storeName;//门店名称

    private Integer storeId;//门店id

    private Integer status;//状态（0：禁用；1：启用）

    private Integer userId;//操作人id

    public RentalStoreDTO() {
    }

    public String getStoreRegisterProvinceCode() {
        return storeRegisterProvinceCode;
    }

    public void setStoreRegisterProvinceCode(String storeRegisterProvinceCode) {
        this.storeRegisterProvinceCode = storeRegisterProvinceCode;
    }

    public String getStoreRegisterCityCode() {
        return storeRegisterCityCode;
    }

    public void setStoreRegisterCityCode(String storeRegisterCityCode) {
        this.storeRegisterCityCode = storeRegisterCityCode;
    }

    public String getStoreRegisterRegionCode() {
        return storeRegisterRegionCode;
    }

    public void setStoreRegisterRegionCode(String storeRegisterRegionCode) {
        this.storeRegisterRegionCode = storeRegisterRegionCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
