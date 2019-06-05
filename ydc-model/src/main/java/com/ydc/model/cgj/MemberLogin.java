package com.ydc.model.cgj;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录表
 */
public class MemberLogin implements Serializable {
    private static final long serialVersionUID = 3523984703473209079L;
    /**
     * t_user_login.id (登录id)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer id;

    /**
     * t_user_login.user_id (用户id)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer userId;

    /**
     * t_user_login.login_type (登录类型（1：快捷登录；2：密码登录）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer loginType;

    /**
     * t_user_login.login_source (登录来源（1：H5；2：APP）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer loginSource;

    /**
     * t_user_login.login_token (登录token)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String loginToken;

    /**
     * 客户应用
     */
    private Integer memberApplication;

    /**
     * t_user_login.invaild_time (失效时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date invaildTime;

    /**
     * t_user_login.last_login_time (最后登录时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date lastLoginTime;

    /**
     * t_user_login.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer status;

    /**
     * t_user_login.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date createTime;

    /**
     * t_user_login.update_time (更新时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(Integer loginSource) {
        this.loginSource = loginSource;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public Date getInvaildTime() {
        return invaildTime;
    }

    public void setInvaildTime(Date invaildTime) {
        this.invaildTime = invaildTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMemberApplication() {
        return memberApplication;
    }

    public void setMemberApplication(Integer memberApplication) {
        this.memberApplication = memberApplication;
    }

    public static enum LoginTypeEnum {
        //短信快捷登录
        LOGIN_TYPE_ENUM_1(1),
        //密码登录
        LOGIN_TYPE_ENUM_2(2);

        private Integer key;

        private LoginTypeEnum(Integer key) {
            this.key = key;
        }

        public Integer getKey() {
            return key;
        }
    }

    public static enum LoginSourceEnum {
        //H5
        LOGIN_SOURCE_ENUM_1(1),
        //APP
        LOGIN_SOURCE_ENUM_2(2);

        private Integer key;

        private LoginSourceEnum(Integer key) {
            this.key = key;
        }

        public Integer getKey() {
            return key;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}