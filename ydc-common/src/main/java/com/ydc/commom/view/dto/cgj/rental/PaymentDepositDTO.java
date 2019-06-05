package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业租车后台列表：已交保证金
 *
 * @author
 * @create 2019-01-08 14:14
 **/
public class PaymentDepositDTO implements Serializable {
    private static final long serialVersionUID = 3432264739338088449L;

    @Attribute(name = "订单ID",required = true,emptyStringVerify = true)
    private Integer orderId;//订单ID

    @Attribute(name = "需求方id",required = true,emptyStringVerify = true)
    private Integer demandSideId;//需求方id

    @Attribute(name = "租车订单号",required = true,emptyStringVerify = true)
    private String rentCarOrderNo;//租车订单号

    @Attribute(name = "支付方式",required = true,emptyStringVerify = true)
    private Integer paymentMode;//支付方式 1：芝麻 2：信用卡 3：现金

    @Attribute(name = "实付金额",required = true,emptyStringVerify = true,isNum = true,maxLength = 10)
    private BigDecimal actualAmount;//实付金额

    @Attribute(name = "到账时间",required = true,emptyStringVerify = true)
    private Date accountTime;//到账时间

    /**
     * t (支付凭证名)
     */
    @Attribute(name = "支付凭证名",required = true,emptyStringVerify = true)
    private String payVoucherImgName;

    /**
     *  (支付凭证路径)
     */
    @Attribute(name = "支付凭证名",required = true,emptyStringVerify = true)
    private String payVoucherImgUrl;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(Integer demandSideId) {
        this.demandSideId = demandSideId;
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
}
