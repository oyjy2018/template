package com.ydc.model.cgj.common;

import java.io.Serializable;
import java.util.Date;

/**
 * t_cfg_holiday
 * @author
 */
public class Holiday implements Serializable {
    private Integer id;

    /**
     * 节假日期
     */
    private Date holidayDate;

    /**
     * 节假日状态（1：节假日；2：节假日调班(算工作日)）
     */
    private Integer holidayType;

    /**
     * 状态（1：正常；0：禁用）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public Integer getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(Integer holidayType) {
        this.holidayType = holidayType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}