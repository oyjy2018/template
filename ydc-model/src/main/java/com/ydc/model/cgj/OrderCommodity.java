package com.ydc.model.cgj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品表
 */
public class OrderCommodity implements Serializable {
    private static final long serialVersionUID = -6124082137156775857L;
    /**
     * t_order_commodity.id
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer id;

    /**
     * t_order_commodity.member_id (用户id)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer memberId;

    /**
     * t_order_commodity.order_id (主订单id)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer orderId;

    /**
     * t_order_commodity.order_no (主订单编号)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String orderNo;

    /**
     * t_order_commodity.commodity_id (商品id)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer commodityId;

    /**
     * t_order_commodity.commodity_name (商品名称)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String commodityName;

    /**
     * t_order_commodity.commodity_model_id (商品模型id)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer commodityModelId;

    /**
     * t_order_commodity.commodity_model_name (商品型号)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String commodityModelName;

    /**
     * t_order_commodity.commodity_model_number (商品数量)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer commodityModelNumber;

    /**
     * t_order_commodity.supplier_code (供应商编码)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String supplierCode;

    /**
     * t_order_commodity.supplier_name (供应商名称)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String supplierName;

    /**
     * t_order_commodity.single_price (商品单价)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private BigDecimal singlePrice;

    /**
     * t_order_commodity.sell_price (所需价格（积分）)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private BigDecimal sellPrice;

    /**
     * t_order_commodity.home_little_file_url (首页缩率图url)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String homeLittleFileUrl;

    /**
     * t_order_commodity.home_little_file_name (首页缩略图名称)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String homeLittleFileName;

    /**
     * t_order_commodity.home_file_url (首页图片url)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String homeFileUrl;

    /**
     * t_order_commodity.home_file_name (首页图片名称)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String homeFileName;

    /**
     * t_order_commodity.home_file_type (首页图片类型)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String homeFileType;

    /**
     * t_order_commodity.home_page_img_id (首页图片)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer homePageImgId;

    /**
     * t_order_commodity.logistics_status (发货状态（0：未发货；1：已发货）)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer logisticsStatus;

    /**
     * t_order_commodity.create_time
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Date createTime;

    /**
     * t_order_commodity.create_by
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer createBy;

    /**
     * t_order_commodity.update_time
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Date updateTime;

    /**
     * t_order_commodity.update_by
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommodityModelId() {
        return commodityModelId;
    }

    public void setCommodityModelId(Integer commodityModelId) {
        this.commodityModelId = commodityModelId;
    }

    public String getCommodityModelName() {
        return commodityModelName;
    }

    public void setCommodityModelName(String commodityModelName) {
        this.commodityModelName = commodityModelName;
    }

    public Integer getCommodityModelNumber() {
        return commodityModelNumber;
    }

    public void setCommodityModelNumber(Integer commodityModelNumber) {
        this.commodityModelNumber = commodityModelNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getHomeLittleFileUrl() {
        return homeLittleFileUrl;
    }

    public void setHomeLittleFileUrl(String homeLittleFileUrl) {
        this.homeLittleFileUrl = homeLittleFileUrl;
    }

    public String getHomeLittleFileName() {
        return homeLittleFileName;
    }

    public void setHomeLittleFileName(String homeLittleFileName) {
        this.homeLittleFileName = homeLittleFileName;
    }

    public String getHomeFileUrl() {
        return homeFileUrl;
    }

    public void setHomeFileUrl(String homeFileUrl) {
        this.homeFileUrl = homeFileUrl;
    }

    public String getHomeFileName() {
        return homeFileName;
    }

    public void setHomeFileName(String homeFileName) {
        this.homeFileName = homeFileName;
    }

    public String getHomeFileType() {
        return homeFileType;
    }

    public void setHomeFileType(String homeFileType) {
        this.homeFileType = homeFileType;
    }

    public Integer getHomePageImgId() {
        return homePageImgId;
    }

    public void setHomePageImgId(Integer homePageImgId) {
        this.homePageImgId = homePageImgId;
    }

    public Integer getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Integer logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
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
}