package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * 物流表
 */
public class OrderLogistics implements Serializable {
    private static final long serialVersionUID = -4100557260181622921L;
    /**
     * t_order_logistics.id
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    private Integer id;

    /**
     * t_order_logistics.order_commodity_id
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    private Integer orderCommodityId;

    /**
     * t_order_logistics.logistics_company (物流公司)
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    private String logisticsCompany;

    /**
     * t_order_logistics.logistics_order (物流单号)
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    private String logisticsOrder;

    /**
     * t_order_logistics.delivery_time (发货时间)
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    private String deliveryTime;

    /**
     * t_order_logistics.create_time
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    private Date createTime;

    /**
     * t_order_logistics.update_time
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    private Date updateTime;

    /**
     * t_order_logistics.update_by
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderCommodityId() {
        return orderCommodityId;
    }

    public void setOrderCommodityId(Integer orderCommodityId) {
        this.orderCommodityId = orderCommodityId;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsOrder() {
        return logisticsOrder;
    }

    public void setLogisticsOrder(String logisticsOrder) {
        this.logisticsOrder = logisticsOrder;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}