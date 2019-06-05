package com.ydc.model.cgj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分明细表
 */
public class IntegralDetail implements Serializable {
    private static final long serialVersionUID = -1836860128628829999L;
    /**
     * t_integral_detail.id
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer id;

    /**
     * t_integral_detail.member_id (会员ID)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer memberId;

    /**
     * t_integral_detail.member_name (会员名)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private String memberName;

    /**
     * 手机号码会员
     */
    private String mobilePhone;

    /**
     * t_integral_detail.pay_time (收支时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Date payTime;

    /**
     * t_integral_detail.integral_pay (积分收支)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private String integralPay;

    /**
     * t_integral_detail.pay_type (收支类型(0：获取；1：消耗))
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer payType;

    /**
     * t_integral_detail.change_integral_balance (变动后积分余额)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private BigDecimal changeIntegralBalance;

    /**
     * t_integral_detail.integral_type_acquire (积分获取(0 车主贷款; 1 每日签到; 2 意见采纳))
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer integralTypeAcquire;

    /**
     * t_integral_detail.integral_type_consume (积分消耗(0 商品兑换; 1 积分抽奖; 2 服务充值; 3 服务查询))
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer integralTypeConsume;

    /**
     * t_integral_detail.payWater (订单ID(注：收支类型等于1时，改字段为必填))
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private String payWater;

    /**
     * t_integral_detail.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Date createTime;

    /**
     * t_integral_detail.create_by (创建人)
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    private Integer createBy;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getIntegralPay() {
        return integralPay;
    }

    public void setIntegralPay(String integralPay) {
        this.integralPay = integralPay;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getChangeIntegralBalance() {
        return changeIntegralBalance;
    }

    public void setChangeIntegralBalance(BigDecimal changeIntegralBalance) {
        this.changeIntegralBalance = changeIntegralBalance;
    }

    public Integer getIntegralTypeAcquire() {
        return integralTypeAcquire;
    }

    public void setIntegralTypeAcquire(Integer integralTypeAcquire) {
        this.integralTypeAcquire = integralTypeAcquire;
    }

    public Integer getIntegralTypeConsume() {
        return integralTypeConsume;
    }

    public void setIntegralTypeConsume(Integer integralTypeConsume) {
        this.integralTypeConsume = integralTypeConsume;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater;
    }
}