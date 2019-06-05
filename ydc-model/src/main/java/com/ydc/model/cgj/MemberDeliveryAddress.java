package com.ydc.model.cgj;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收货地址
 */
public class MemberDeliveryAddress implements Serializable {
    private static final long serialVersionUID = -1762446137945966709L;
    /**
     * t_user_delivery_address.id (收货地址id)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer id;

    /**
     * t_user_delivery_address.user_id (用户id)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer userId;

    /**
     * 联系人姓名
     */
    private String linkName;

    /**
     * t_user_delivery_address.link_phone (联系方式)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String linkPhone;

    /**
     * t_user_delivery_address.default_address (是否是默认地址（0：否；1：是）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Byte defaultAddress;

    /**
     * t_user_delivery_address.address_province_code (地址省编码)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer addressProvinceCode;

    /**
     * t_user_delivery_address.address_city_code (地址市编码)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer addressCityCode;

    /**
     * t_user_delivery_address.address_region_code (地址区编码)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer addressRegionCode;

    /**
     * t_user_delivery_address.address_province (地址省地址)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String addressProvince;

    /**
     * t_user_delivery_address.address_city (地址市地址)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String addressCity;

    /**
     * t_user_delivery_address.address_region (地址区地址)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String addressRegion;

    /**
     * t_user_delivery_address.address_detail (详细地址)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String addressDetail;

    /**
     * t_user_delivery_address.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer status;

    /**
     * t_user_delivery_address.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date createTime;

    /**
     * t_user_delivery_address.update_time (更新时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Byte getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Byte defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Integer getAddressProvinceCode() {
        return addressProvinceCode;
    }

    public void setAddressProvinceCode(Integer addressProvinceCode) {
        this.addressProvinceCode = addressProvinceCode;
    }

    public Integer getAddressCityCode() {
        return addressCityCode;
    }

    public void setAddressCityCode(Integer addressCityCode) {
        this.addressCityCode = addressCityCode;
    }

    public Integer getAddressRegionCode() {
        return addressRegionCode;
    }

    public void setAddressRegionCode(Integer addressRegionCode) {
        this.addressRegionCode = addressRegionCode;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(String addressRegion) {
        this.addressRegion = addressRegion;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}