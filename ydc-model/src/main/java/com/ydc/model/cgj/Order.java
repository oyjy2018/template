package com.ydc.model.cgj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -5209076105139576965L;
    /**
     * t_order.id
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer id;

    /**
     * t_order.member_id
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer memberId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * t_order.order_no (主订单号)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String orderNo;

    /**
     * t_order.supplier_code (供应商编码)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String supplierCode;

    /**
     * t_order.supplier_name (供应商名称)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String supplierName;

    /**
     * t_order.buyer_message (买家留言)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String buyerMessage;

    /**
     * t_order.logistics_status (物流状态（0：待发货；1：全部发货；2：部分发货）)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer logisticsStatus;

    /**
     * t_order.status (订单状态 0：创建订单  1：待付款 2：待发货 3：待收货 4：交易成功  8：订单关闭)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer status;

    /**
     * t_order.payment_time (支付时间)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Date paymentTime;

    /**
     * t_order.cancel_reason (取消原因)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String cancelReason;

    /**
     * t_order.cancel_time (取消时间)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Date cancelTime;

    /**
     * t_order.commodity_count (商品总数)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer commodityCount;

    /**
     * t_order.order_amount (订单总额)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private BigDecimal orderAmount;

    /**
     * t_order.payment_currency (支付币种  integral：积分)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String paymentCurrency;

    /**
     * t_order.pay_account (支付账号)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String payAccount;

    /**
     * t_order.pay_water (支付流水)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String payWater;

    /**
     * t_order.payment_method (支付方式)
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private String paymentMethod;

    /**
     * t_order.create_time
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Date createTime;

    /**
     * t_order.create_by
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Integer createBy;

    /**
     * t_order.update_time
     * @ibatorgenerated 2018-09-17 14:43:02
     */
    private Date updateTime;

    /**
     * t_order.update_by
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Integer getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Integer logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Integer getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(Integer commodityCount) {
        this.commodityCount = commodityCount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}