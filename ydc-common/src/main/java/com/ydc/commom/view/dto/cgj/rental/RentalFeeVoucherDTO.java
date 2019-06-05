package com.ydc.commom.view.dto.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 租车凭证
 *
 * @author
 * @create 2019-01-07 18:52
 **/
public class RentalFeeVoucherDTO implements Serializable {
    private static final long serialVersionUID = 7137112548651908592L;

    /**
     * 订单ID
     */
    private Integer orderId;


    private Integer voucherType;//订单类型（1：平台收取租金；2：租金转移至资源方；3：押金退还至平台；4：押金退还至需求方）

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
