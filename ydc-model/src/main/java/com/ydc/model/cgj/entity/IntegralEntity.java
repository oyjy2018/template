package com.ydc.model.cgj.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 导入封装积分实体类
 *
 * @author gongjin
 * @create 2018-09-19 10:26
 **/
public class IntegralEntity implements Serializable {
    private static final long serialVersionUID = 7737254620099819431L;

    private Integer memberId;   //会员id
    private String mobilePhone; //会员电话
    private BigDecimal usableIntegral; //充值积分
    private Integer operatorId;//操作人
    private Integer integralTypeAcquire; //积分获取(0 车主贷款; 1 每日签到; 2 意见采纳)

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public BigDecimal getUsableIntegral() {
        return usableIntegral;
    }

    public void setUsableIntegral(BigDecimal usableIntegral) {
        this.usableIntegral = usableIntegral;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getIntegralTypeAcquire() {
        return integralTypeAcquire;
    }

    public void setIntegralTypeAcquire(Integer integralTypeAcquire) {
        this.integralTypeAcquire = integralTypeAcquire;
    }
}
