package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单收货地址
 */
public class OrderDeliveryAddress implements Serializable {
    private static final long serialVersionUID = 8927324777243501037L;
    /**
     * t_order_delivery_address.id
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    private Integer id;

    /**
     * t_order_delivery_address.order_id
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    private Integer orderId;

    /**
     * t_order_delivery_address.addressee (收件人)
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    private String addressee;

    /**
     * t_order_delivery_address.phone_number (手机号码)
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    private String phoneNumber;

    /**
     * t_order_delivery_address.consignee_address (收件地址)
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    private String consigneeAddress;

    /**
     * t_order_delivery_address.create_time
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    private Date createTime;

    /**
     * t_order_delivery_address.update_time
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    private Date updateTime;

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

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
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
}