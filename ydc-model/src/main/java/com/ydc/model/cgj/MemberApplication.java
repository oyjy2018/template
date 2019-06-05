package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

public class MemberApplication implements Serializable {
    private static final long serialVersionUID = -5774210985647962880L;
    /**
     * t_member_application.id (id)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Integer id;

    /**
     * t_member_application.member_id (客户id)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Integer memberId;

    /**
     * 客户姓名
     */
    private String memberName;

    /**
     * t_member_application.member_login_name (客户登录名)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private String memberLoginName;

    /**
     * t_member_application.member_phone (客户手机号码)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private String memberPhone;

    /**
     * t_member_application.member_password (客户密码)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private String memberPassword;

    /**
     * t_member_application.member_status (客户状态（1：正常；2：注销；3：锁定）)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Integer memberStatus;

    /**
     * t_member_application.application (应用系统（1：车管家；2：租车）)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Integer application;

    /**
     * t_member_application.status (状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Integer status;

    /**
     * t_member_application.create_time (创建时间)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Date createTime;

    /**
     * t_member_application.create_by (创建人)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Integer createBy;

    /**
     * t_member_application.update_time (更新时间)
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    private Date updateTime;

    /**
     * t_member_application.update_by (更新人)
     * @ibatorgenerated 2018-11-16 09:37:42
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

    public String getMemberLoginName() {
        return memberLoginName;
    }

    public void setMemberLoginName(String memberLoginName) {
        this.memberLoginName = memberLoginName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Integer getApplication() {
        return application;
    }

    public void setApplication(Integer application) {
        this.application = application;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}