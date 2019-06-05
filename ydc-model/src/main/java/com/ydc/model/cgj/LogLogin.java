package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * web登录日志
 */
public class LogLogin implements Serializable {
    private static final long serialVersionUID = -1589904183750895078L;
    /**
     * t_log_login.id
     * @ibatorgenerated 2018-09-26 16:09:33
     */
    private Integer id;

    /**
     * t_log_login.user_id (用户ID)
     * @ibatorgenerated 2018-09-26 16:09:33
     */
    private Integer userId;

    /**
     * t_log_login.user_name (用户名)
     * @ibatorgenerated 2018-09-26 16:09:33
     */
    private String userName;

    /**
     * t_log_login.login_name (登陆名)
     * @ibatorgenerated 2018-09-26 16:09:33
     */
    private String loginName;

    /**
     * t_log_login.login_time (登陆时间)
     * @ibatorgenerated 2018-09-26 16:09:33
     */
    private Date loginTime;

    /**
     * t_log_login.role_name (角色名)
     * @ibatorgenerated 2018-09-26 16:09:33
     */
    private String roleName;

    /**
     * t_log_login.ip_address (登陆IP地址)
     * @ibatorgenerated 2018-09-26 16:09:33
     */
    private String ipAddress;

    /**
     * t_log_login.user_application (客户应用（1：车管家；2：租车；3：一点好车）)
     * @ibatorgenerated 2018-11-19 14:12:04
     */
    private Integer userApplication;

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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getUserApplication() {
        return userApplication;
    }

    public void setUserApplication(Integer userApplication) {
        this.userApplication = userApplication;
    }
}