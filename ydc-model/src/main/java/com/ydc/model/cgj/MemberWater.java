package com.ydc.model.cgj;

import java.math.BigDecimal;
import java.util.Date;

public class MemberWater {
    private Integer id;

    /**
	* 会员ID
	*/
    private Integer memberId;

    /**
	* 会员名
	*/
    private String memberName;

    /**
	* 手机号
	*/
    private String mobilePhone;

    /**
	* 交易积分数量
	*/
    private BigDecimal integralAmount;

    /**
	* 交易现金数量
	*/
    private BigDecimal cashAmount;

    /**
	* 交易时间
	*/
    private Date transactionTime;

    /**
	* 交易方式（1：余额；2：微信）
	*/
    private Integer transactionMethod;

    /**
	* 交易类型（1：积分；2：现金；3：混合）
	*/
    private Integer transactionType;

    /**
	* 流水类型(0：获取；1：消耗)
	*/
    private Integer waterType;

    /**
	* 流水状态（1：交易中；2：完成；3：取消）
	*/
    private Integer waterStatus;

    /**
	* 变动后积分余额
	*/
    private BigDecimal changeIntegralBalance;

    /**
	* 变动后现金余额
	*/
    private BigDecimal changeCashBalance;

    /**
	* 获取类型(0 车主贷款; 1 每日签到; 2 意见采纳)
	*/
    private Integer acquireType;

    /**
	* 消耗类型(0 商品兑换; 1 积分抽奖; 2 服务充值; 3 服务查询)
	*/
    private Integer consumeType;

    /**
	* 支付流水
	*/
    private String payWater;

    /**
	* 第三方支付流水
	*/
    private String thirdPayWater;

    /**
	* 状态（0：无效；1：有效）
	*/
    private Integer status;

    /**
	* 创建时间
	*/
    private Date createTime;

    /**
	* 创建人
	*/
    private Integer createBy;

    /**
	* 更新时间
	*/
    private Date updateTime;

    /**
	* 更新人
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public BigDecimal getIntegralAmount() {
        return integralAmount;
    }

    public void setIntegralAmount(BigDecimal integralAmount) {
        this.integralAmount = integralAmount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Integer getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(Integer transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getWaterType() {
        return waterType;
    }

    public void setWaterType(Integer waterType) {
        this.waterType = waterType;
    }

    public Integer getWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(Integer waterStatus) {
        this.waterStatus = waterStatus;
    }

    public BigDecimal getChangeIntegralBalance() {
        return changeIntegralBalance;
    }

    public void setChangeIntegralBalance(BigDecimal changeIntegralBalance) {
        this.changeIntegralBalance = changeIntegralBalance;
    }

    public BigDecimal getChangeCashBalance() {
        return changeCashBalance;
    }

    public void setChangeCashBalance(BigDecimal changeCashBalance) {
        this.changeCashBalance = changeCashBalance;
    }

    public Integer getAcquireType() {
        return acquireType;
    }

    public void setAcquireType(Integer acquireType) {
        this.acquireType = acquireType;
    }

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
        this.consumeType = consumeType;
    }

    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater;
    }

    public String getThirdPayWater() {
        return thirdPayWater;
    }

    public void setThirdPayWater(String thirdPayWater) {
        this.thirdPayWater = thirdPayWater;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}