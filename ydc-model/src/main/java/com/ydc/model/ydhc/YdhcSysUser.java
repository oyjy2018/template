package com.ydc.model.ydhc;

import java.io.Serializable;
import java.util.Date;

/**
 * t_ydhc_sys_user
 * @author 
 */
public class YdhcSysUser implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 机构ID
     */
    private Integer orgId;

    /**
     * 可查看机构数据
     */
    private String viewOrgId;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 岗位
     */
    private String jobName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 头像路径
     */
    private String avatarUrl;

    /**
     * 钉钉用户ID
     */
    private String ddUId;

    /**
     * 钉钉ID
     */
    private String ddId;

    /**
     * 银行卡号
     */
    private String cardNumber;

    /**
     * 开户银行
     */
    private String bankName;

    /**
     * 开户行支行
     */
    private String bankSubbranch;

    /**
     * 收款户名
     */
    private String collectionAccountName;

    /**
     * 收款账号
     */
    private String collectionCardNumber;

    /**
     * 收款开户银行
     */
    private String collectionBankName;

    /**
     * 收款开户行支行
     */
    private String collectionBankSubbranch;

    /**
     * 离职日期
     */
    private Date leaveDate;

    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 状态（1：正常；0：禁用）
     */
    private Integer status;

    /**
     * 企业code
     */
    private String enterpriseCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getViewOrgId() {
        return viewOrgId;
    }

    public void setViewOrgId(String viewOrgId) {
        this.viewOrgId = viewOrgId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDdUId() {
        return ddUId;
    }

    public void setDdUId(String ddUId) {
        this.ddUId = ddUId;
    }

    public String getDdId() {
        return ddId;
    }

    public void setDdId(String ddId) {
        this.ddId = ddId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankSubbranch() {
        return bankSubbranch;
    }

    public void setBankSubbranch(String bankSubbranch) {
        this.bankSubbranch = bankSubbranch;
    }

    public String getCollectionAccountName() {
        return collectionAccountName;
    }

    public void setCollectionAccountName(String collectionAccountName) {
        this.collectionAccountName = collectionAccountName;
    }

    public String getCollectionCardNumber() {
        return collectionCardNumber;
    }

    public void setCollectionCardNumber(String collectionCardNumber) {
        this.collectionCardNumber = collectionCardNumber;
    }

    public String getCollectionBankName() {
        return collectionBankName;
    }

    public void setCollectionBankName(String collectionBankName) {
        this.collectionBankName = collectionBankName;
    }

    public String getCollectionBankSubbranch() {
        return collectionBankSubbranch;
    }

    public void setCollectionBankSubbranch(String collectionBankSubbranch) {
        this.collectionBankSubbranch = collectionBankSubbranch;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
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