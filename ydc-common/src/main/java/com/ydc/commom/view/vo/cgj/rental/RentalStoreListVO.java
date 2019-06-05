package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.commom.enums.common.CommonEnum;

import java.io.Serializable;

/**
 * 租车门店集合
 *
 * @author
 * @create 2018-12-07 14:41
 **/
public class RentalStoreListVO implements Serializable {
    private static final long serialVersionUID = -4908077223024162647L;

    private Integer storeId;
    private String storeName;
    private String organizationName;
    private Integer superiorOrganizationId;
    private String superiorOrganizationName;
    private Integer symbiosisId;
    private String symbiosisName;
    private Integer organizationTypeId;
    private String organizationTypeName;
    private Integer theirEnterpriseId;
    private String theirEnterpriseName;
    private String storeRegisterProvinceCode;
    private String storeRegisterProvince;
    private String storeRegisterCityCode;
    private String storeRegisterCity;
    private String storeRegisterRegionCode;
    private String storeRegisterRegion;
    private String detailsAddress;
    private String storeLongitude;
    private String storeLatitude;
    private String trafficPattern;
    private String mobilePhone;
    private Integer status;
    private String statusName;
    private String createTime;


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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getSuperiorOrganizationId() {
        return superiorOrganizationId;
    }

    public void setSuperiorOrganizationId(Integer superiorOrganizationId) {
        this.superiorOrganizationId = superiorOrganizationId;
    }

    public String getSuperiorOrganizationName() {
        return superiorOrganizationName;
    }

    public void setSuperiorOrganizationName(String superiorOrganizationName) {
        this.superiorOrganizationName = superiorOrganizationName;
    }

    public Integer getSymbiosisId() {
        return symbiosisId;
    }

    public void setSymbiosisId(Integer symbiosisId) {
        this.symbiosisId = symbiosisId;
    }

    public String getSymbiosisName() {
        return symbiosisName;
    }

    public void setSymbiosisName(String symbiosisName) {
        this.symbiosisName = symbiosisName;
    }

    public Integer getOrganizationTypeId() {
        return organizationTypeId;
    }

    public void setOrganizationTypeId(Integer organizationTypeId) {
        this.organizationTypeId = organizationTypeId;
    }

    public String getOrganizationTypeName() {
        return organizationTypeName;
    }

    public void setOrganizationTypeName(String organizationTypeName) {
        this.organizationTypeName = organizationTypeName;
    }

    public Integer getTheirEnterpriseId() {
        return theirEnterpriseId;
    }

    public void setTheirEnterpriseId(Integer theirEnterpriseId) {
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

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public String getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(String storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public String getTrafficPattern() {
        return trafficPattern;
    }

    public void setTrafficPattern(String trafficPattern) {
        this.trafficPattern = trafficPattern;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return CommonEnum.DeleteStatusEnum.getCodeName(this.status);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

