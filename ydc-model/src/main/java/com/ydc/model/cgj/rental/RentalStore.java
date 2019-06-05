package com.ydc.model.cgj.rental;

import com.ydc.model.annotation.IsEmpty;
import com.ydc.model.annotation.MaxSize;
import com.ydc.model.annotation.MinSize;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalStore implements Serializable {
    private static final long serialVersionUID = 686719438165005859L;
    /**
     *
     * t_rental_store.id
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer id;

    /**
     * t_rental_store.store_name (门店名称)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="门店名称不能为空")
    @MaxSize(max = 10,message="门店名称长度太长")
    private String storeName;

    /**
     * t_rental_store.organization_name (机构全称)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
//    @IsEmpty(message="机构全称不能为空")
    @MaxSize(max = 30,message="机构全称长度太长")
    private String organizationName;

    /**
     * t_rental_store.superior_organization_id (上级机构id)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="上级机构id不能为空")
    private Integer superiorOrganizationId;

    /**
     * t_rental_store.superior_organization_name (上级机构名)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="上级机构名不能为空")
    private String superiorOrganizationName;

    /**
     * t_rental_store.symbiosis_id (合作关系id)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="合作关系id不能为空")
    private Integer symbiosisId;

    /**
     * t_rental_store.symbiosis_name (合作关系名)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="合作关系名不能为空")
    private String symbiosisName;

    /**
     * t_rental_store.organization_type_id (机构类型id)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="机构类型id不能为空")
    private Integer organizationTypeId;

    /**
     * t_rental_store.organization_type_name (机构类型名)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="机构类型名不能为空")
    private String organizationTypeName;

    /**
     * t_rental_store.their_enterprise_id (所属企业id)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="所属企业id不能为空")
    private Integer theirEnterpriseId;

    /**
     * t_rental_store.their_enterprise_name (所属企业名)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="所属企业名不能为空")
    private String theirEnterpriseName;

    /**
     * t_rental_store.store_register_province_code (门店地址省编码)
     * @ibatorgenerated 2018-11-19 15:18:41
     */
    @IsEmpty(message="门店地址省编码不能为空")
    private Integer storeRegisterProvinceCode;

    /**
     * t_rental_store.store_register_province (门店地址省)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="门店地址省不能为空")
    private String storeRegisterProvince;

    /**
     * t_rental_store.store_register_city_code (门店地址市编码)
     * @ibatorgenerated 2018-11-19 15:18:41
     */
    @IsEmpty(message="门店地址市编码不能为空")
    private Integer storeRegisterCityCode;

    /**
     * t_rental_store.store_register_city (门店地址市)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="门店地址市不能为空")
    private String storeRegisterCity;

    /**
     * t_rental_store.store_register_region_code (门店地址区编码)
     * @ibatorgenerated 2018-11-19 15:18:41
     */
    private Integer storeRegisterRegionCode;

    /**
     * t_rental_store.store_register_region (门店地址区)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String storeRegisterRegion;

    /**
     * t_rental_store.details_address (详情地址)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="详情地址不能为空")
    @MaxSize(max = 90,message = "详情地址长度太长")
    private String detailsAddress;

    /**
     * t_rental_store.store_longitude (门店经度)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
//    @IsEmpty(message="门店经度不能为空")
    private BigDecimal storeLongitude;

    /**
     * t_rental_store.store_latitude (门店纬度)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
//    @IsEmpty(message="门店纬度不能为空")
    private BigDecimal storeLatitude;

    /**
     * t_rental_store.traffic_pattern (交通方式)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
//    @IsEmpty(message="交通方式不能为空")
    @MaxSize(max = 100,message = "详情地址长度太长")
    private String trafficPattern;

    /**
     * t_rental_store.mobile_phone (联系电话)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    @IsEmpty(message="联系电话不能为空")
    @MinSize(minLen = 11,message="联系电话长度太短")
    @MaxSize(max = 12,message="联系电话长度太长")
    private String mobilePhone;

    /**
     * t_rental_store.status (状态（0：禁用；1：启用）)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer status;

    /**
     * t_rental_store.create_time (创建时间)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Date createTime;

    /**
     * t_rental_store.create_by (创建人)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer createBy;

    /**
     * t_rental_store.update_time (修改时间)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Date updateTime;

    /**
     * t_rental_store.update_by (修改人)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStoreRegisterProvince() {
        return storeRegisterProvince;
    }

    public void setStoreRegisterProvince(String storeRegisterProvince) {
        this.storeRegisterProvince = storeRegisterProvince;
    }

    public String getStoreRegisterCity() {
        return storeRegisterCity;
    }

    public void setStoreRegisterCity(String storeRegisterCity) {
        this.storeRegisterCity = storeRegisterCity;
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

    public BigDecimal getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(BigDecimal storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public BigDecimal getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(BigDecimal storeLatitude) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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

    public Integer getStoreRegisterProvinceCode() {
        return storeRegisterProvinceCode;
    }

    public void setStoreRegisterProvinceCode(Integer storeRegisterProvinceCode) {
        this.storeRegisterProvinceCode = storeRegisterProvinceCode;
    }

    public Integer getStoreRegisterCityCode() {
        return storeRegisterCityCode;
    }

    public void setStoreRegisterCityCode(Integer storeRegisterCityCode) {
        this.storeRegisterCityCode = storeRegisterCityCode;
    }

    public Integer getStoreRegisterRegionCode() {
        return storeRegisterRegionCode;
    }

    public void setStoreRegisterRegionCode(Integer storeRegisterRegionCode) {
        this.storeRegisterRegionCode = storeRegisterRegionCode;
    }
}