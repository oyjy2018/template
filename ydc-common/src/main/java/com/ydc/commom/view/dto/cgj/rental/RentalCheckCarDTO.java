package com.ydc.commom.view.dto.cgj.rental;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @create 2019-01-04 10:42
 **/
public class RentalCheckCarDTO implements Serializable {
    private static final long serialVersionUID = -7770655161405852862L;

    private Integer id;

    private Integer orderId;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 资源方出车检验图片文件名
     */
    private String resourceSideComeCarImgName;

    /**
     * 资源方出车检验图片路径
     */
    private String resourceSideComeCarImgUrl;

    /**
     * 需求方出车检验图片文件名
     */
    private String demandSideComeCarImgName;

    /**
     * 需求方出车检验图片路径
     */
    private String demandSideComeCarImgUrl;

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

    /**
     * 需求方还车检验图片文件名
     */
    private String demandSideRepayCarImgName;

    /**
     * 需求方还车检验图片路径
     */
    private String demandSideRepayCarImgUrl;

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

    private String carLevel;//车等级

    private Integer carSeriesId;//车型id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RentalCheckCarDTO() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public RentalCheckCarDTO(Integer orderId){
        this.orderId = orderId;
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
}
