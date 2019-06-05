package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalSettlementEnum;
import com.ydc.commom.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 租金及押金信息
 *
 * @author
 * @create 2019-01-05 11:52
 **/
public class PCRentalEnterpriseDetailsDepositVO implements Serializable {
    private static final long serialVersionUID = 3144237037820096806L;

    private Integer paymentStatus;//押金状态 1：未支付，2：已支付，3：已退还，9：退还失败
    private String paymentStatusName;//押金状态
    private Integer paymentMode;//支付方式
    private String paymentModeName;
    private BigDecimal actualAmount;//实付金额
    private Date paymentTime;//支付时间
    private Date actualRefundTime;//实际押金退还时间
    private BigDecimal actualRefundAmount;//实际退还金额
    private String payVoucherImgName;//支付凭证文件名
    private String payVoucherImgUrl;//支付凭证文件路径
    private String payVoucherImgBrowse;//浏览支付凭证图片
    private String accountVoucherImgName;//到账凭证文件名
    private String accountVoucherImgUrl;//到账凭证文件路径
    private String accountVoucherImgBrowse;//浏览到账凭证图片
    private String userName;//操作人

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatusName() {
        return RentalDepositEnum.PaymentStatus.getPaymentStatusCHOfNull(this.paymentStatus);
    }

    public void setPaymentStatusName(String paymentStatusName) {
        this.paymentStatusName = paymentStatusName;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentModeName() {
        return RentalSettlementEnum.getRentalSettlementEnumOfNull(this.paymentMode);
    }

    public void setPaymentModeName(String paymentModeName) {
        this.paymentModeName = paymentModeName;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    @JSONField(format = DateUtil.DATATIMEF_STR)
    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    @JSONField(format = DateUtil.DATATIMEF_STR)
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

    public String getPayVoucherImgBrowse() {
        return payVoucherImgBrowse;
    }

    public void setPayVoucherImgBrowse(String payVoucherImgBrowse) {
        this.payVoucherImgBrowse = payVoucherImgBrowse;
    }

    public String getAccountVoucherImgName() {
        return accountVoucherImgName;
    }

    public void setAccountVoucherImgName(String accountVoucherImgName) {
        this.accountVoucherImgName = accountVoucherImgName;
    }

    public String getAccountVoucherImgUrl() {
        return accountVoucherImgUrl;
    }

    public void setAccountVoucherImgUrl(String accountVoucherImgUrl) {
        this.accountVoucherImgUrl = accountVoucherImgUrl;
    }

    public String getAccountVoucherImgBrowse() {
        return accountVoucherImgBrowse;
    }

    public void setAccountVoucherImgBrowse(String accountVoucherImgBrowse) {
        this.accountVoucherImgBrowse = accountVoucherImgBrowse;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
