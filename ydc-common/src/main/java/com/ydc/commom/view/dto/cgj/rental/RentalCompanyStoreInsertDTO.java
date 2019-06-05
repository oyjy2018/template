package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.Pagination;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @create 2018-11-16 18:50
 **/
public class RentalCompanyStoreInsertDTO extends Pagination implements Serializable {

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

    @Attribute(name = "门店名称",required = true,emptyStringVerify = true)
    private String storeName;

    //合作关系
    private String symbiosisName;

    //门店类型
    private String organizationTypeName;

    //所属企业id
    private String theirEnterpriseId;

    //所属企业名
    private String theirEnterpriseName;

    private Integer status;//状态（0：禁用；1：启用）

    //创建时间
    private Date createTime = new Date();


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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public String getSymbiosisName() {
        return symbiosisName;
    }

    public void setSymbiosisName(String symbiosisName) {
        this.symbiosisName = symbiosisName;
    }

    public String getOrganizationTypeName() {
        return organizationTypeName;
    }

    public void setOrganizationTypeName(String organizationTypeName) {
        this.organizationTypeName = organizationTypeName;
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
}
