package com.ydc.model.cgj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BCarServiceShop implements Serializable {
    private static final long serialVersionUID = 3263897967342562251L;
    /**
     * t_b_store.id
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private Integer id;

    /**
     * t_b_store.store_name (商家名称)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String storeName;

    /**
     * t_b_store.store_logo (商家Logo)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String storeLogo;

    /**
     * t_b_store.store_code (门店码)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String storeCode;

    /**
     * t_b_store.store_identifying (商家标识)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String storeIdentifying;

    /**
     * t_b_store.service_phone (客服电话)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String servicePhone;

    /**
     * t_b_store.store_register_province (门店地址省)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String storeRegisterProvince;

    /**
     * t_b_store.store_register_city (门店地址市)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String storeRegisterCity;

    /**
     * t_b_store.store_register_region (门店地址区)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String storeRegisterRegion;

    /**
     * t_b_store.details_address (详情地址)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String detailsAddress;

    /**
     * t_b_store.business_hours_start_time (营业时间开始时间)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String businessHoursStartTime;

    /**
     * t_b_store.business_hours_end_time (营业时间结束时间)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private String businessHoursEndTime;

    /**
     * t_b_store.status (状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private Integer status;

    /**
     * t_b_store.serve_gather (门店提供服务集合)
     * @ibatorgenerated 2018-10-30 16:43:52
     */
    private String serveGather;

    /**
     * t_b_store.whether_putaway (是否上架（0：否；1：是）)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private Integer whetherPutaway;

    /**
     * t_b_car_service_shop.category (业务开展)
     * @ibatorgenerated 2019-01-21 14:24:22
     */
    private String category;

    /**
     * t_b_store.longitude (经度)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private BigDecimal longitude;

    /**
     * t_b_store.latitude (纬度)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private BigDecimal latitude;

    /**
     * t_b_store.create_time (创建时间)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private Date createTime;

    /**
     * t_b_store.create_by (创建人)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private Integer createBy;

    /**
     * t_b_store.update_time (修改时间)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private Date updateTime;

    /**
     * t_b_store.update_by (修改人)
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    private Integer updateBy;

    private Integer priority;
    private Date priorityTime;

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

    public String getBusinessHoursStartTime() {
        return businessHoursStartTime;
    }

    public void setBusinessHoursStartTime(String businessHoursStartTime) {
        this.businessHoursStartTime = businessHoursStartTime;
    }

    public String getBusinessHoursEndTime() {
        return businessHoursEndTime;
    }

    public void setBusinessHoursEndTime(String businessHoursEndTime) {
        this.businessHoursEndTime = businessHoursEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWhetherPutaway() {
        return whetherPutaway;
    }

    public void setWhetherPutaway(Integer whetherPutaway) {
        this.whetherPutaway = whetherPutaway;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getServeGather() {
        return serveGather;
    }

    public void setServeGather(String serveGather) {
        this.serveGather = serveGather;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getPriorityTime() {
        return priorityTime;
    }

    public void setPriorityTime(Date priorityTime) {
        this.priorityTime = priorityTime;
    }
}