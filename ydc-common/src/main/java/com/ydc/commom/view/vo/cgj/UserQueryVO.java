package com.ydc.commom.view.vo.cgj;

import com.ydc.commom.enums.common.CommonEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 门店
 *
 * @author
 * @create 2018-10-31 12:08
 **/
public class UserQueryVO implements Serializable {
    private Integer id;//员工id
    private String userName;//用户名
    private String jobNumber;//工号
    private String jobName;//岗位
    private String mobilePhone;//手机号
    private String viewOrgId;//机构权限
    private String viewOrgName;//机构权限
    private String status;//状态

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
        return CommonEnum.DeleteStatusEnum.getCodeName(Integer.parseInt(status));
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
