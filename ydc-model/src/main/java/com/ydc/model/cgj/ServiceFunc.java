package com.ydc.model.cgj;

import java.util.Date;

/**
 * 服务功能表
 * 
 * @author wangcan
 * 
 * @date 2018-12-29
 */
public class ServiceFunc {
    /**
     * id,唯一标识,自增，主键
     */
    private Integer id;

    /**
     * 服务id
     */
    private Integer serviceId;

    /**
     * 功能名称
     */
    private String funcName;

    /**
     * 跳转链接
     */
    private String url;

    /**
     * 是否显示(1、显示；2、不显示)
     */
    private Integer showType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 有效状态（0：无效；1：有效）
     */
    private Integer deleteStatus;

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

    public ServiceFunc(Integer id, Integer serviceId, String funcName, String url, Integer showType, String remark, Integer deleteStatus, Date createTime, Integer createBy, Date updateTime, Integer updateBy) {
        this.id = id;
        this.serviceId = serviceId;
        this.funcName = funcName;
        this.url = url;
        this.showType = showType;
        this.remark = remark;
        this.deleteStatus = deleteStatus;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public ServiceFunc() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
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