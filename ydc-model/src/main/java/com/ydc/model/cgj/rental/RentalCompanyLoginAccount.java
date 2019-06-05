package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.util.Date;

/**
 * t_rental_company_login_account
 * @author 
 */
public class RentalCompanyLoginAccount implements Serializable {
    private Integer id;

    /**
     * 企业客户表id
     */
    private Integer companyCustomerId;

    /**
     * 登录手机号码
     */
    private String phone;

    /**
     * 状态（0：禁用；1：启用）
     */
    private Byte deleteStatus;

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

    public Integer getCompanyCustomerId() {
        return companyCustomerId;
    }

    public void setCompanyCustomerId(Integer companyCustomerId) {
        this.companyCustomerId = companyCustomerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Byte deleteStatus) {
        this.deleteStatus = deleteStatus;
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