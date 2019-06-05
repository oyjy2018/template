package com.ydc.model.cgj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 放款客户表
 */
public class LendingCustomer implements Serializable {
    private static final long serialVersionUID = 4762078820747040561L;
    /**
     * t_lending_customer.id
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private Integer id;

    /**
     * t_lending_customer.customer_id (风控用户ID)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private Integer customerId;

    /**
     * t_lending_customer.customer_name (客户名)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private String customerName;

    /**
     * t_lending_customer.id_card (身份证)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private String idCard;

    /**
     * t_lending_customer.mobile_phone (手机号)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private String mobilePhone;

    /**
     * t_lending_customer.gender (性别)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private String gender;

    /**
     * t_lending_customer.age (年龄)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private String age;

    /**
     * t_lending_customer.email (邮箱)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private String email;

    /**
     * t_lending_customer.loan_id (借款ID)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private Integer loanId;

    /**
     * t_lending_customer.bidding_money (发标金额)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private BigDecimal biddingMoney;

    /**
     * t_lending_customer.full_scale_time (满标时间)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private Date fullScaleTime;

    /**
     * 是否派完（1：是；0：否）
     */
    private Integer whetherIssue;

    /**
     * t_lending_customer.create_time (创建时间)
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    private Date createTime;

    /**
     * 派卷数量
     */
    private Integer rollNumber;

    /**
     * t_lending_customer.first_send_roll_time (首次发劵时间)
     * @ibatorgenerated  2018-10-29 18:15:45
     */
    private Date firstSendRollTime;
    /**
     * t_lending_customer.recently_send_roll_time (最近发劵时间)
     * @ibatorgenerated  2018-10-29 18:15:45
     */
    private Date recentlySendRollTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getBiddingMoney() {
        return biddingMoney;
    }

    public void setBiddingMoney(BigDecimal biddingMoney) {
        this.biddingMoney = biddingMoney;
    }

    public Date getFullScaleTime() {
        return fullScaleTime;
    }

    public void setFullScaleTime(Date fullScaleTime) {
        this.fullScaleTime = fullScaleTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getWhetherIssue() {
        return whetherIssue;
    }

    public void setWhetherIssue(Integer whetherIssue) {
        this.whetherIssue = whetherIssue;
    }

    public Integer getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(Integer rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Date getFirstSendRollTime() {
        return firstSendRollTime;
    }

    public void setFirstSendRollTime(Date firstSendRollTime) {
        this.firstSendRollTime = firstSendRollTime;
    }

    public Date getRecentlySendRollTime() {
        return recentlySendRollTime;
    }

    public void setRecentlySendRollTime(Date recentlySendRollTime) {
        this.recentlySendRollTime = recentlySendRollTime;
    }
}