package com.ydc.model.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * t_rental_fee_voucher
 * @author 
 */
public class RentalFeeVoucher implements Serializable {
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单类型
     */
    private Integer voucherType;

    /**
     * 金额一
     */
    private BigDecimal oneMoney;

    /**
     * 金额二
     */
    private BigDecimal twoMoney;

    /**
     * 支付方支付时间
     */
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
    private Date accountTime;

    /**
     * 到账凭证文件名
     */
    private String accountVoucherImgName;

    /**
     * 到账凭证文件路径
     */
    private String accountVoucherImgUrl;

    private Date createTime;

    private Integer createBy;

    private static final long serialVersionUID = 1L;

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

    public Integer getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Integer voucherType) {
        this.voucherType = voucherType;
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

    @JSONField(format = DateUtil.DATAFORMAT_STR)
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

    @JSONField(format = DateUtil.DATAFORMAT_STR)
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
}