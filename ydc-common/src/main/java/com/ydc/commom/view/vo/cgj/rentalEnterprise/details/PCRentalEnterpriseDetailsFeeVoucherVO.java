package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalFeeVoucherEnum;
import com.ydc.commom.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @create 2019-01-07 14:47
 **/
public class PCRentalEnterpriseDetailsFeeVoucherVO implements Serializable {
    private static final long serialVersionUID = -8285409075870895311L;


    private BigDecimal oneMoney;//租金总额
    private BigDecimal twoMoney;//押金总额
    private Date payTime;//支付方支付时间
    private String payVoucherImgName;//支付凭证文件名
    private String payVoucherImgUrl;//支付凭证文件路径
    private String payVoucherImgBrowse;//浏览支付凭证图片
    private Date accountTime;//接受方到账时间
    private String accountVoucherImgName;//到账凭证文件名
    private String accountVoucherImgUrl;//到账凭证文件路径
    private String accountVoucherImgBrowse;//浏览到账凭证图片
    private String userName;//操作人
    private Date createTime;//创建时间
    private Integer voucherType;//订单类型（1：平台收取租金；2：租金转移至资源方；3：押金退还至平台；4：押金退还至需求方）
    private String voucherTypeName;


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

    public String getPayVoucherImgBrowse() {
        return payVoucherImgBrowse;
    }

    public void setPayVoucherImgBrowse(String payVoucherImgBrowse) {
        this.payVoucherImgBrowse = payVoucherImgBrowse;
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

    @JSONField(format = DateUtil.DATATIMEF_STR)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Integer voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherTypeName() {
        return RentalFeeVoucherEnum.VoucherType.getVoucherCH(this.voucherType);
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName;
    }

}
