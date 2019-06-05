package com.ydc.model.rcs.dto;

import java.io.Serializable;

/**
 * 放款数据
 *
 * @author
 * @create 2018-10-26 14:36
 **/
public class LendingDataDTO implements Serializable {
    private static final long serialVersionUID = 3156739851219984973L;

    private Integer customerId; //风控客户id
    private String customerName;//风控客户名
    private String idCard;  //身份证id
    private String gender;  //客户性别
    private String age; //客户年龄
    private String mobilePhone;//客户电话
    private String email;   //客户邮件
    private String loanId;  //借款单id
    private String fullScaleTime;//满标时间
    private String biddingMoney;//发标金额
    private Integer source; //客户来源
    private Integer whetherRealName;//是否实名
    private String createTime;//创建时间
    private Integer whetherIssue;//是否派完（1：是；0：否）
    private Integer rollNumber;//派卷数量
    private Integer whetherLoan;//是否借款

    public LendingDataDTO(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getFullScaleTime() {
        return fullScaleTime;
    }

    public void setFullScaleTime(String fullScaleTime) {
        this.fullScaleTime = fullScaleTime;
    }

    public String getBiddingMoney() {
        return biddingMoney;
    }

    public void setBiddingMoney(String biddingMoney) {
        this.biddingMoney = biddingMoney;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getWhetherRealName() {
        return whetherRealName;
    }

    public void setWhetherRealName(Integer whetherRealName) {
        this.whetherRealName = whetherRealName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    public Integer getWhetherLoan() {
        return whetherLoan;
    }

    public void setWhetherLoan(Integer whetherLoan) {
        this.whetherLoan = whetherLoan;
    }
}
