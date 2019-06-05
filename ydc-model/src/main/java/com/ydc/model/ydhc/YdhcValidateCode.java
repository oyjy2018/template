package com.ydc.model.ydhc;

import java.io.Serializable;
import java.util.Date;

public class YdhcValidateCode implements Serializable {
    /**
     * t_ydhc_validate_code.id (主键ID)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Integer id;

    /**
     * t_ydhc_validate_code.mobile_phone (手机号)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String mobilePhone;

    /**
     * t_ydhc_validate_code.code (验证码)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String code;

    /**
     * 是否已使用
     */
    private Integer hasUsed;

    /**
     * t_ydhc_validate_code.time_out (失效时间)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Date timeOut;

    /**
     * t_ydhc_validate_code.status (状态（1：正常；0：禁用）)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Integer status;

    /**
     * t_ydhc_validate_code.create_time (创建时间)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Date createTime;

    /**
     * t_ydhc_validate_code.update_time (更新时间)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
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

    public Integer getHasUsed() {
        return hasUsed;
    }

    public void setHasUsed(Integer hasUsed) {
        this.hasUsed = hasUsed;
    }
}