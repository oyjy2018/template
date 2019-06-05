package com.ydc.model.cgj;


import java.io.Serializable;
import java.util.Date;

/**
 * 购物车明细
 */
public class StoreShopCartCommodity implements Serializable {
    private static final long serialVersionUID = -2009382067715473164L;
    /**
     * t_store_shop_cart_commodity.id
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer id;

    /**
     * t_store_shop_cart_commodity.member_id (用户id)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer memberId;

    /**
     * t_store_shop_cart_commodity.commodity_id (商品id)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer commodityId;

    /**
     * t_store_shop_cart_commodity.commodity_model_id (商品模型id)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer commodityModelId;

    /**
     * t_store_shop_cart_commodity.supplier_code (供应商编码)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String supplierCode;

    /**
     * t_store_shop_cart_commodity.supplier_name (供应商名称)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String supplierName;

    /**
     * t_store_shop_cart_commodity.commodity_number (商品数量)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer commodityNumber;

    /**
     * t_store_shop_cart_commodity.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date createTime;

    /**
     * t_store_shop_cart_commodity.create_by (创建用户)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer createBy;

    /**
     * t_store_shop_cart_commodity.update_time (修改时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date updateTime;

    /**
     * t_store_shop_cart_commodity.update_by (修改人员)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer updateBy;

    private Integer deleteStatus;

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCommodityModelId() {
        return commodityModelId;
    }

    public void setCommodityModelId(Integer commodityModelId) {
        this.commodityModelId = commodityModelId;
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

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
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