package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.util.Date;

/**
 * t_rental_check_car
 * @author 
 */
public class RentalCheckCar implements Serializable {
    private Integer id;

    /**
     * 订单号
     */
    private Integer orderId;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * t_rental_check_car.car_level (车等级)
     * @ibatorgenerated 2019-01-25 14:13:34
     */
    private String carLevel;

    /**
     * t_rental_check_car.car_series_id (车型id)
     * @ibatorgenerated 2019-01-25 14:13:34
     */
    private Integer carSeriesId;


    /**
     * 资源方出车检验图片文件名
     */
    private String resourceSideComeCarImgName;

    /**
     * 资源方出车检验图片路径
     */
    private String resourceSideComeCarImgUrl;

    private String resourceSideComeCarImgBrowse;

    /**
     * 需求方出车检验图片文件名
     */
    private String demandSideComeCarImgName;

    /**
     * 需求方出车检验图片路径
     */
    private String demandSideComeCarImgUrl;

    private String demandSideComeCarImgBrowse;

    /**
     * 出车操作人ID
     */
    private Integer comeCarPersonId;

    /**
     * 出车操作人
     */
    private String comeCarPerson;

    /**
     * 出车时间
     */
    private Date comeCarTime;

    /**
     * 资源方还车检验图片文件名
     */
    private String resourceSideRepayCarImgName;

    /**
     * 资源方还车检验图片路径
     */
    private String resourceSideRepayCarImgUrl;

    private String resourceSideRepayCarImgBrowse;

    /**
     * 需求方还车检验图片文件名
     */
    private String demandSideRepayCarImgName;

    /**
     * 需求方还车检验图片路径
     */
    private String demandSideRepayCarImgUrl;

    private String demandSideRepayCarImgBrowse;

    /**
     * 还车操作人ID
     */
    private Integer repayCarPersonId;

    /**
     * 还车操作人
     */
    private String repayCarPerson;

    /**
     * 还车时间
     */
    private Date repayCarTime;

    private Date createTime;

    private Integer createBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getResourceSideComeCarImgName() {
        return resourceSideComeCarImgName;
    }

    public void setResourceSideComeCarImgName(String resourceSideComeCarImgName) {
        this.resourceSideComeCarImgName = resourceSideComeCarImgName;
    }

    public String getResourceSideComeCarImgUrl() {
        return resourceSideComeCarImgUrl;
    }

    public void setResourceSideComeCarImgUrl(String resourceSideComeCarImgUrl) {
        this.resourceSideComeCarImgUrl = resourceSideComeCarImgUrl;
    }

    public String getDemandSideComeCarImgName() {
        return demandSideComeCarImgName;
    }

    public void setDemandSideComeCarImgName(String demandSideComeCarImgName) {
        this.demandSideComeCarImgName = demandSideComeCarImgName;
    }

    public String getDemandSideComeCarImgUrl() {
        return demandSideComeCarImgUrl;
    }

    public void setDemandSideComeCarImgUrl(String demandSideComeCarImgUrl) {
        this.demandSideComeCarImgUrl = demandSideComeCarImgUrl;
    }

    public Integer getComeCarPersonId() {
        return comeCarPersonId;
    }

    public void setComeCarPersonId(Integer comeCarPersonId) {
        this.comeCarPersonId = comeCarPersonId;
    }

    public String getComeCarPerson() {
        return comeCarPerson;
    }

    public void setComeCarPerson(String comeCarPerson) {
        this.comeCarPerson = comeCarPerson;
    }

    public Date getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(Date comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public String getResourceSideRepayCarImgName() {
        return resourceSideRepayCarImgName;
    }

    public void setResourceSideRepayCarImgName(String resourceSideRepayCarImgName) {
        this.resourceSideRepayCarImgName = resourceSideRepayCarImgName;
    }

    public String getResourceSideRepayCarImgUrl() {
        return resourceSideRepayCarImgUrl;
    }

    public void setResourceSideRepayCarImgUrl(String resourceSideRepayCarImgUrl) {
        this.resourceSideRepayCarImgUrl = resourceSideRepayCarImgUrl;
    }

    public String getDemandSideRepayCarImgName() {
        return demandSideRepayCarImgName;
    }

    public void setDemandSideRepayCarImgName(String demandSideRepayCarImgName) {
        this.demandSideRepayCarImgName = demandSideRepayCarImgName;
    }

    public String getDemandSideRepayCarImgUrl() {
        return demandSideRepayCarImgUrl;
    }

    public void setDemandSideRepayCarImgUrl(String demandSideRepayCarImgUrl) {
        this.demandSideRepayCarImgUrl = demandSideRepayCarImgUrl;
    }

    public Integer getRepayCarPersonId() {
        return repayCarPersonId;
    }

    public void setRepayCarPersonId(Integer repayCarPersonId) {
        this.repayCarPersonId = repayCarPersonId;
    }

    public String getRepayCarPerson() {
        return repayCarPerson;
    }

    public void setRepayCarPerson(String repayCarPerson) {
        this.repayCarPerson = repayCarPerson;
    }

    public Date getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(Date repayCarTime) {
        this.repayCarTime = repayCarTime;
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

    public String getResourceSideComeCarImgBrowse() {
        return resourceSideComeCarImgBrowse;
    }

    public void setResourceSideComeCarImgBrowse(String resourceSideComeCarImgBrowse) {
        this.resourceSideComeCarImgBrowse = resourceSideComeCarImgBrowse;
    }

    public String getDemandSideComeCarImgBrowse() {
        return demandSideComeCarImgBrowse;
    }

    public void setDemandSideComeCarImgBrowse(String demandSideComeCarImgBrowse) {
        this.demandSideComeCarImgBrowse = demandSideComeCarImgBrowse;
    }

    public String getResourceSideRepayCarImgBrowse() {
        return resourceSideRepayCarImgBrowse;
    }

    public void setResourceSideRepayCarImgBrowse(String resourceSideRepayCarImgBrowse) {
        this.resourceSideRepayCarImgBrowse = resourceSideRepayCarImgBrowse;
    }

    public String getDemandSideRepayCarImgBrowse() {
        return demandSideRepayCarImgBrowse;
    }

    public void setDemandSideRepayCarImgBrowse(String demandSideRepayCarImgBrowse) {
        this.demandSideRepayCarImgBrowse = demandSideRepayCarImgBrowse;
    }
}