package com.ydc.commom.view.dto.cgj;

import com.ydc.model.annotation.Attribute;

import java.io.Serializable;
import java.util.Date;

/**
 * 修改员工信息dto
 *
 * @author
 * @create 2018-10-31 12:08
 **/
public class UserUpdateDTO implements Serializable {

    @Attribute(name = "员工id",required = true,isNum = true)
    private Integer id; //id

    @Attribute(name = "用户名",required = true,emptyStringVerify = true)
    private String userName;//用户名

    @Attribute(name = "登录名")
    private String loginName;//登录名

    @Attribute(name = "工号")
    private String jobNumber;//工号

   // @Attribute(name = "岗位",required = true,emptyStringVerify = true)
    private String jobName;//岗位

    @Attribute(name = "手机号",required = true,emptyStringVerify = true)
    private String mobilePhone;//手机号

    @Attribute(name = "机构权限id",required = true,emptyStringVerify = true)
    private String viewOrgId;//机构权限

    @Attribute(name = "状态",required = true,emptyStringVerify = true)
    private String status;//状态

    @Attribute(name = "入职日期",dateFormat = "yyyy-MM-dd")
    private String entryDate;

    @Attribute(name = "离职日期",dateFormat = "yyyy-MM-dd")
    private String leaveDate;

    //修改时间
    private Date updateTime = new Date();

    //修改人id
    private Integer updateBy ;

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
