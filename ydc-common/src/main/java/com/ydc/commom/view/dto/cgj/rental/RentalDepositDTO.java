package com.ydc.commom.view.dto.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @create 2019-01-07 15:48
 **/
public class RentalDepositDTO implements Serializable {
    private static final long serialVersionUID = -7622053918225743033L;

    private Integer orderId;//订单ID

    private Integer demandSideId;//需求方id

    private String rentCarOrderNo;//租车订单号

    private Integer paymentMode;//支付方式 1：芝麻 2：信用卡 3：现金

    private BigDecimal actualAmount;//实付金额

    private Date accountTime;//到账时间

    private Date actualRefundTime;//实际押金退还时间

    private BigDecimal actualRefundAmount;//实际退还金额

    /**
     * t (支付凭证名)
     * @ibatorgenerated 2019-01-07 16:30:47
     */
    private String payVoucherImgName;

    /**
     *  (支付凭证路径)
     * @ibatorgenerated 2019-01-07 16:30:47
     */
    private String payVoucherImgUrl;


    /**
     * (退还凭证名)
     * @ibatorgenerated 2019-01-07 16:30:47
     */
    private String refundVoucherImgName;

    /**
     * (退还凭证路径)
     * @ibatorgenerated 2019-01-07 16:30:47
     */
    private String refundVoucherImgUrl;



    private Integer userId;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getRentCarOrderNo() {
        return rentCarOrderNo;
    }

    public void setRentCarOrderNo(String rentCarOrderNo) {
        this.rentCarOrderNo = rentCarOrderNo;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }

    public Date getActualRefundTime() {
        return actualRefundTime;
    }

    public void setActualRefundTime(Date actualRefundTime) {
        this.actualRefundTime = actualRefundTime;
    }

    public BigDecimal getActualRefundAmount() {
        return actualRefundAmount;
    }

    public void setActualRefundAmount(BigDecimal actualRefundAmount) {
        this.actualRefundAmount = actualRefundAmount;
    }

    public Integer getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(Integer demandSideId) {
        this.demandSideId = demandSideId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getPayVoucherImgName() {
        return payVoucherImgName;
    }

    public void setPayVoucherImgName(String payVoucherImgName) {
        this.payVoucherImgName = payVoucherImgName;
    }

    public String getPayVoucherImgUrl() {
        return payVoucherImgUrl;
    }

    public void setPayVoucherImgUrl(String payVoucherImgUrl) {
        this.payVoucherImgUrl = payVoucherImgUrl;
    }

    public String getRefundVoucherImgName() {
        return refundVoucherImgName;
    }

    public void setRefundVoucherImgName(String refundVoucherImgName) {
        this.refundVoucherImgName = refundVoucherImgName;
    }

    public String getRefundVoucherImgUrl() {
        return refundVoucherImgUrl;
    }

    public void setRefundVoucherImgUrl(String refundVoucherImgUrl) {
        this.refundVoucherImgUrl = refundVoucherImgUrl;
    }
}
