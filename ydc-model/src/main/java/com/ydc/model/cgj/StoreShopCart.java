package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车主表
 */
public class StoreShopCart implements Serializable {
    private static final long serialVersionUID = 3431857489887511361L;
    /**
     * t_store_shop_cart.id
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer id;

    /**
     * t_store_shop_cart.memberId (用户id)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer memberId;

    /**
     * t_store_shop_cart.commodity_count (购物车商品总数)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer commodityCount;

    /**
     * t_store_shop_cart.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Date createTime;

    /**
     * t_store_shop_cart.create_by (创建用户)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer createBy;

    /**
     * t_store_shop_cart.update_time (修改时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Date updateTime;

    /**
     * t_store_shop_cart.update_by (修改人员)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
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

    public Integer getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(Integer commodityCount) {
        this.commodityCount = commodityCount;
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