package com.ydc.commom.view.vo.cgj.order;


import com.ydc.commom.enums.cgj.OrderEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 我的订单
 *
 * @author gongjin
 * @create 2018-09-15 17:06
 **/
public class MyOrderVO implements Serializable {
    private static final long serialVersionUID = -6386027623951544721L;

    private Integer orderId;    //订单id
    private String supplierCode;    //供应商编码
    private String supplierName;    // 供应商名称
    private BigDecimal orderAmount;  //订单总额
    private Integer commodityCount; //数量
    private String orderStatusName; //订单状态
    private Integer orderStatus;    //订单状态
    private String addressee;   //收件人
    private String phoneNumber; //手机号码
    private String consigneeAddress;//收件地址
    private String orderNo;//订单号
    private String payWater;//交易流水
    private String createTime;//创建时间
    private String paymentTime;//支付时间
    private Long countdown;// 倒计时（毫秒）
    private String orderNumber;//订单编号
    private String cancelReason;//取消原因
    private List<MyOrderCommodityVO> myOrderCommodityVOList;  //订单商品集合

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<MyOrderCommodityVO> getMyOrderCommodityVOList() {
        return myOrderCommodityVOList;
    }

    public void setMyOrderCommodityVOList(List<MyOrderCommodityVO> myOrderCommodityVOList) {
        this.myOrderCommodityVOList = myOrderCommodityVOList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(Integer commodityCount) {
        this.commodityCount = commodityCount;
    }

    public String getOrderStatusName() {
        return OrderEnum.OrderStatusEnum.getCodeName(this.orderStatus);
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Long getCountdown() {
        return countdown;
    }

    public void setCountdown(Long countdown) {
        this.countdown = countdown;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
