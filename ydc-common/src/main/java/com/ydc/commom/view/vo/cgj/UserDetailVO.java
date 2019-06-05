package com.ydc.commom.view.vo.cgj;

import java.io.Serializable;

/**
 * 员工查询详情vo
 *
 * @author
 * @create 2018-10-31 12:08
 **/
public class UserDetailVO implements Serializable {
    private Integer id;//员工id
    private String userName;//用户名
    private String loginName;//登录名
    private String jobNumber;//工号
    private String jobName;//岗位
    private String mobilePhone;//手机号
    private String viewOrgId;//机构权限id
    private String viewOrgName;//机构名称
    private String status;//状态
    private String entryDate;
    private String leaveDate;

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

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getViewOrgId() {
        return viewOrgId;
    }

    public void setViewOrgId(String viewOrgId) {
        this.viewOrgId = viewOrgId;
    }

    public String getViewOrgName() {
        return viewOrgName;
    }

    public void setViewOrgName(String viewOrgName) {
        this.viewOrgName = viewOrgName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }
}
