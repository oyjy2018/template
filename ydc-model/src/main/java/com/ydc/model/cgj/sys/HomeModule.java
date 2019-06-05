package com.ydc.model.cgj.sys;

import java.io.Serializable;
import java.util.Date;

public class HomeModule implements Serializable {
    /**
     * t_cgj_home_module.id
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Integer id;

    /**
     * t_cgj_home_module.platform (客户端)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private String platform;

    /**
     * t_cgj_home_module.module (模块)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private String module;

    /**
     * t_cgj_home_module.son_module_id (子模块ID)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Integer sonModuleId;

    /**
     * t_cgj_home_module.son_module (子模块)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private String sonModule;

    /**
     * t_cgj_home_module.son_module_remark (子模块描述)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private String sonModuleRemark;

    /**
     * t_cgj_home_module.function_name (功能名称)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private String functionName;

    /**
     * t_cgj_home_module.remark (备注)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private String remark;

    /**
     * t_cgj_home_module.jump_url
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private String jumpUrl;

    /**
     * t_cgj_home_module.show_status (是否显示（0关，1开）)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Integer showStatus;

    /**
     * t_cgj_home_module.sort (排序)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Integer sort;

    /**
     * t_cgj_home_module.status (有效状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Integer status;

    /**
     * t_cgj_home_module.create_time (创建时间)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Date createTime;

    /**
     * t_cgj_home_module.create_by (创建人)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Integer createBy;

    /**
     * t_cgj_home_module.update_time (修改时间)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Date updateTime;

    /**
     * t_cgj_home_module.update_by (修改人)
     * @ibatorgenerated 2018-12-26 19:23:23
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getSonModuleId() {
        return sonModuleId;
    }

    public void setSonModuleId(Integer sonModuleId) {
        this.sonModuleId = sonModuleId;
    }

    public String getSonModule() {
        return sonModule;
    }

    public void setSonModule(String sonModule) {
        this.sonModule = sonModule;
    }

    public String getSonModuleRemark() {
        return sonModuleRemark;
    }

    public void setSonModuleRemark(String sonModuleRemark) {
        this.sonModuleRemark = sonModuleRemark;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
}