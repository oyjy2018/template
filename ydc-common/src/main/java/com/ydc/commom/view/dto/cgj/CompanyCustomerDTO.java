package com.ydc.commom.view.dto.cgj;

import com.ydc.model.cgj.Pagination;

public class CompanyCustomerDTO extends Pagination {

    /**
     * 注册手机
     */
    private String registeredPhone;

    /**
     * 企业认证状态（0：未认证；1：待审核；2：已认证；3：审核退回）
     */
    private Integer authenticationStatus;

    /**
     * 注册公司名
     */
    private String registeredCompanyName;

    /**
     * 认证日期
     */
    private String authenticationStartDate;

    /**
     * 认证日期
     */
    private String authenticationEndDate;

    /**
     * 注册日期
     */
    private String createStartTime;

    /**
     * 注册日期
     */
    private String createEndTime;

    /**
     * 状态（0：禁用；1：启用）
     */
    private Integer deleteStatus;

    public CompanyCustomerDTO changeEndTime(String time) {
        if (this.authenticationEndDate != null && !("").equals(this.authenticationEndDate)) {
            this.authenticationEndDate = this.authenticationEndDate + time;
        }
        if (this.createEndTime != null && !("").equals(this.createEndTime)) {
            this.createEndTime = this.createEndTime + time;
        }
        return this;
    }

    public String getRegisteredPhone() {
        return registeredPhone;
    }

    public void setRegisteredPhone(String registeredPhone) {
        this.registeredPhone = registeredPhone;
    }

    public Integer getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(Integer authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public String getRegisteredCompanyName() {
        return registeredCompanyName;
    }

    public void setRegisteredCompanyName(String registeredCompanyName) {
        this.registeredCompanyName = registeredCompanyName;
    }

    public String getAuthenticationStartDate() {
        return authenticationStartDate;
    }

    public void setAuthenticationStartDate(String authenticationStartDate) {
        this.authenticationStartDate = authenticationStartDate;
    }

    public String getAuthenticationEndDate() {
        return authenticationEndDate;
    }

    public void setAuthenticationEndDate(String authenticationEndDate) {
        this.authenticationEndDate = authenticationEndDate;
    }

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
