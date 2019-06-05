package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.commom.enums.common.CommonEnum;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2018-11-16 18:37
 **/
public class RentalStoreVO implements Serializable {
    private static final long serialVersionUID = -7034175642574225048L;

    /**
     * 门店id
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer storeId;

    /**
     * t_rental_store.store_name (门店名称)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String storeName;

    /**
     * t_rental_store.organization_name (机构全称)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String organizationName;

    /**
     * t_rental_store.superior_organization_id (上级机构id)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer superiorOrganizationId;

    /**
     * t_rental_store.superior_organization_name (上级机构名)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String superiorOrganizationName;

    /**
     * t_rental_store.symbiosis_id (合作关系id)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer symbiosisId;

    /**
     * t_rental_store.symbiosis_name (合作关系名)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String symbiosisName;

    /**
     * t_rental_store.organization_type_id (机构类型id)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer organizationTypeId;

    /**
     * t_rental_store.organization_type_name (机构类型名)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String organizationTypeName;

    /**
     * t_rental_store.their_enterprise_id (所属企业id)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer theirEnterpriseId;

    /**
     * t_rental_store.their_enterprise_name (所属企业名)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String theirEnterpriseName;

    /**
     * t_rental_store.store_register_province_code (门店地址省编码)
     * @ibatorgenerated 2018-11-19 15:18:41
     */
    private Integer storeRegisterProvinceCode;

    /**
     * t_rental_store.store_register_province (门店地址省)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String storeRegisterProvince;

    /**
     * t_rental_store.store_register_city_code (门店地址市编码)
     * @ibatorgenerated 2018-11-19 15:18:41
     */
    private Integer storeRegisterCityCode;

    /**
     * t_rental_store.store_register_city (门店地址市)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
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
    private String detailsAddress;

    /**
     * t_rental_store.store_longitude (门店经度)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private BigDecimal storeLongitude;

    /**
     * t_rental_store.store_latitude (门店纬度)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private BigDecimal storeLatitude;

    /**
     * t_rental_store.traffic_pattern (交通方式)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String trafficPattern;

    /**
     * t_rental_store.mobile_phone (联系电话)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String mobilePhone;

    /**
     * t_rental_store.status (状态（0：禁用；1：启用）)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private Integer statusCode;

    /**
     * 状态名
     */
    private String statusName;

    /**
     * t_rental_store.create_time (创建时间)
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    private String createTime;


    public static List<RentalStoreVO> getRentalStoreVos(List<Map<String,Object>> mapList){
        List<RentalStoreVO> rentalStoreVOS = Lists.newArrayList();
        if(mapList == null || mapList.isEmpty())return rentalStoreVOS;
        rentalStoreVOS = mapList.stream().map(item ->{
            RentalStoreVO rentalStoreVO = new RentalStoreVO();
            rentalStoreVO.setStoreId(Integer.valueOf(item.get("dictKey").toString()));
            rentalStoreVO.setStoreName(item.get("dictValue").toString());
            return rentalStoreVO;
        }).collect(Collectors.toList());
        return rentalStoreVOS;
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

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return  CommonEnum.DeleteStatusEnum.getCodeName(this.statusCode);
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
