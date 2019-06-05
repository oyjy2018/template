package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @create 2019-01-08 14:35
 **/
public class InsertRentPaymentDTO implements Serializable {
    private static final long serialVersionUID = -7170604295601752749L;

    /**
     * 订单ID
     */
    @Attribute(name = "订单ID",required = true,emptyStringVerify = true)
    private Integer orderId;

    /**
     * 金额一
     */
    @Attribute(name = "租金总额",required = true,emptyStringVerify = true,isDigit = true,decimalLength = 2 ,maxLength = 10)
    private BigDecimal oneMoney;

    /**
     * 金额二
     */
    @Attribute(name = "押金总额",required = true,emptyStringVerify = true,isDigit = true,decimalLength = 2,maxLength = 10)
    private BigDecimal twoMoney;

    /**
     * 支付方支付时间
     */
    @Attribute(name = "需求方支付日期",required = true,emptyStringVerify = true)
    private Date payTime;

    /**
     * 支付凭证文件名
     */
    private String payVoucherImgName;

    /**
     * 支付凭证文件路径
     */
    private String payVoucherImgUrl;

    /**
     * 接受方到账时间
     */
    @Attribute(name = "平台到账日期",required = true,emptyStringVerify = true)
    private Date accountTime;

    /**
     * 到账凭证文件名
     */
    @Attribute(name = "到账凭证文件名",required = true,emptyStringVerify = true)
    private String accountVoucherImgName;

    /**
     * 到账凭证文件路径
     */
    @Attribute(name = "到账凭证文件路径",required = true,emptyStringVerify = true)
    private String accountVoucherImgUrl;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOneMoney() {
        return oneMoney;
    }

    public void setOneMoney(BigDecimal oneMoney) {
        this.oneMoney = oneMoney;
    }

    public BigDecimal getTwoMoney() {
        return twoMoney;
    }

    public void setTwoMoney(BigDecimal twoMoney) {
        this.twoMoney = twoMoney;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
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
}
