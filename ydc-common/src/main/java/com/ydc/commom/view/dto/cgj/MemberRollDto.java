package com.ydc.commom.view.dto.cgj;


import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 用户券查询类
 */
public class MemberRollDto extends Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer memberId;
    /**
     * 手机号码
     */
    private String mobilePhone;
    /**
     * 借款单id
     */
    private Integer loanId;
    /**
     * 预约单id
     */
    private Integer appointId;
    /**
     * 失效开始时间
     */
    private String startInvalidTime;
    /**
     * 失效结束时间
     */
    private String endInvalidTime;
    /**
     * 开始使用时间
     */
    private String startUsedTime;
    /**
     * 结束使用时间
     */
    private String endUsedTime;
    /**
     * 券类型
     */
    private Integer rollType;
    /**
     * 券状态
     */
    private Integer rollStatus;
    /**
     * 券码
     */
    private String rollCode;
    /**
     * 购买订单
     */
    private String buyOrderNo;
    /**
     * 服务订单
     */
    private String orderNo;

    public String getRollCode() {
        return rollCode;
    }

    public void setRollCode(String rollCode) {
        this.rollCode = rollCode;
    }

    public String getBuyOrderNo() {
        return buyOrderNo;
    }

    public void setBuyOrderNo(String buyOrderNo) {
        this.buyOrderNo = buyOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

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


    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getAppointId() {
        return appointId;
    }

    public void setAppointId(Integer appointId) {
        this.appointId = appointId;
    }

    public String getStartInvalidTime() {
        return startInvalidTime;
    }

    public void setStartInvalidTime(String startInvalidTime) {
        this.startInvalidTime = startInvalidTime;
    }

    public String getEndInvalidTime() {
        return endInvalidTime;
    }

    public void setEndInvalidTime(String endInvalidTime) {
        this.endInvalidTime = endInvalidTime;
    }

    public String getStartUsedTime() {
        return startUsedTime;
    }

    public void setStartUsedTime(String startUsedTime) {
        this.startUsedTime = startUsedTime;
    }

    public String getEndUsedTime() {
        return endUsedTime;
    }

    public void setEndUsedTime(String endUsedTime) {
        this.endUsedTime = endUsedTime;
    }

    public Integer getRollType() {
        return rollType;
    }

    public void setRollType(Integer rollType) {
        this.rollType = rollType;
    }

    public Integer getRollStatus() {
        return rollStatus;
    }

    public void setRollStatus(Integer rollStatus) {
        this.rollStatus = rollStatus;
    }

    public void changeEndTime(String time){
        if (this.endInvalidTime != null && !("").equals(this.endInvalidTime)){
            this.endInvalidTime = this.endInvalidTime + time;
        }
        if (this.endUsedTime != null && !("").equals(this.endUsedTime)){
            this.endUsedTime = this.endUsedTime + time;
        }
    }
}
