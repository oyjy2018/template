package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 用户表
 */
public class User implements Serializable {
    private static final long serialVersionUID = 6176564055145644821L;
    /**
     * t_user.id
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer id;

    /**
     * t_user.user_name (用户名)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String userName;

    /**
     * t_user.login_name (登陆名)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String loginName;

    /**
     * t_user.password (密码)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String password;

    /**
     * t_user.service_user_role_id (服务用户角色id)
     * @ibatorgenerated 2018-11-16 16:57:49
     */
    private Integer serviceUserRoleId;

    /**
     * t_user.org_id (机构ID)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer orgId;

    /**
     * t_user.view_org_id (可查看机构数据)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String viewOrgId;

    /**
     * t_user.mobile_phone (手机号)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String mobilePhone;

    /**
     * t_user.job_name (岗位)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String jobName;

    /**
     * t_user.email (邮箱)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String email;

    /**
     * t_user.job_number (工号)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String jobNumber;

    /**
     * t_user.avatar_url (头像路径)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String avatarUrl;

    /**
     * t_user.dd_u_id (钉钉用户ID)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String ddUId;

    /**
     * t_user.dd_id (钉钉ID)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String ddId;

    /**
     * t_user.card_number (银行卡号)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String cardNumber;

    /**
     * t_user.bank_name (开户银行)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String bankName;

    /**
     * t_user.bank_subbranch (开户行支行)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String bankSubbranch;

    /**
     * t_user.collection_account_name (收款户名)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String collectionAccountName;

    /**
     * t_user.collection_card_number (收款账号)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String collectionCardNumber;

    /**
     * t_user.collection_bank_name (收款开户银行)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String collectionBankName;

    /**
     * t_user.collection_bank_subbranch (收款开户行支行)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private String collectionBankSubbranch;

    /**
     * t_user.leave_date (离职日期)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date leaveDate;

    /**
     * t_user.entry_date (入职日期)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date entryDate;

    /**
     * t_user.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer status;

    /**
     * 企业code
     */
    private String enterpriseCode;

    /**
     * t_user.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date createTime;

    /**
     * t_user.create_by (创建人)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer createBy;

    /**
     * t_user.update_time (修改时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date updateTime;

    /**
     * t_user.update_by (修改人)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer updateBy;

    /**
     * t_user表没有该字段
     */
    private String publicKey;

    /**
     * 钉钉标识
     */
    private String serviceIdentifying;

    /**
     * 对应服务角色id
     */
    private Integer roleId;

    private Double version;

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

    public Integer getServiceUserRoleId() {
        return serviceUserRoleId;
    }

    public void setServiceUserRoleId(Integer serviceUserRoleId) {
        this.serviceUserRoleId = serviceUserRoleId;
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

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }


    public String getServiceIdentifying() {
        return serviceIdentifying;
    }

    public void setServiceIdentifying(String serviceIdentifying) {
        this.serviceIdentifying = serviceIdentifying;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }
}