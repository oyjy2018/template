package com.ydc.commom.view.vo.cgj.order;

import com.ydc.commom.enums.cgj.IntegralDetailEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 车管家积分明细
 *
 * @author
 * @create 2018-12-07 16:37
 **/
public class IntegralDetailVO implements Serializable {
    private static final long serialVersionUID = -1018042120928332156L;

    private Integer integralDetailId;
    private String mobilePhone;
    private String memberName;
    private String payTime;
    private String integralPay;
    private Integer pay_type;
    private String payType;
    private BigDecimal changeIntegralBalance;
    private String integralTypeAcquire;
    private Integer integralTypeAcquireCode;
    private String integralTypeConsume;
    private Integer integralTypeConsumeCode;
    private String payWater;
    private Integer memberId;

    public Integer getIntegralDetailId() {
        return integralDetailId;
    }

    public void setIntegralDetailId(Integer integralDetailId) {
        this.integralDetailId = integralDetailId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getIntegralPay() {
        return integralPay;
    }

    public void setIntegralPay(String integralPay) {
        this.integralPay = integralPay;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public String getPayType() {
        return IntegralDetailEnum.PayTypeEnum.getCodeName(this.pay_type);
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getChangeIntegralBalance() {
        return changeIntegralBalance;
    }

    public void setChangeIntegralBalance(BigDecimal changeIntegralBalance) {
        this.changeIntegralBalance = changeIntegralBalance;
    }

    public String getIntegralTypeAcquire() {
        return IntegralDetailEnum.IntegralTypeAcquireEnum.getCodeName(this.integralTypeAcquireCode);
    }

    public void setIntegralTypeAcquire(String integralTypeAcquire) {
        this.integralTypeAcquire = integralTypeAcquire;
    }

    public Integer getIntegralTypeAcquireCode() {
        return integralTypeAcquireCode;
    }

    public void setIntegralTypeAcquireCode(Integer integralTypeAcquireCode) {
        this.integralTypeAcquireCode = integralTypeAcquireCode;
    }

    public String getIntegralTypeConsume() {
        return IntegralDetailEnum.IntegralTypeConsumeEnum.getCodeName(this.integralTypeConsumeCode);
    }

    public void setIntegralTypeConsume(String integralTypeConsume) {
        this.integralTypeConsume = integralTypeConsume;
    }

    public Integer getIntegralTypeConsumeCode() {
        return integralTypeConsumeCode;
    }

    public void setIntegralTypeConsumeCode(Integer integralTypeConsumeCode) {
        this.integralTypeConsumeCode = integralTypeConsumeCode;
    }

    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
