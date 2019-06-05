package com.ydc.model.cgj.sys;

import java.io.Serializable;
import java.util.Date;

public class CgjAppVersion implements Serializable {
    /**
     * t_cgj_app_version.id
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private Integer id;

    /**
     * t_cgj_app_version.platform (客户端平台)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private String platform;

    /**
     * t_cgj_app_version.version (app版本号)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private String version;

    /**
     * t_cgj_app_version.forced_update (是否强制更新（0否，1是）)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private Integer forcedUpdate;

    /**
     * t_cgj_app_version.update_log (更新日志)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private String updateLog;

    /**
     * t_cgj_app_version.update_url (应用商店更新地址)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private String updateUrl;

    /**
     * t_cgj_app_version.part_update_url (服务器更新地址)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private String partUpdateUrl;

    /**
     * t_cgj_app_version.has_part_update (是否从服务器更新)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private Integer hasPartUpdate;

    /**
     * t_cgj_app_version.status (有效状态（0：无效；1：有效）)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private Integer status;

    /**
     * t_cgj_app_version.create_time
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private Date createTime;

    /**
     * t_cgj_app_version.create_by (创建人)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private Integer createBy;

    /**
     * t_cgj_app_version.update_time (修改时间)
     * @ibatorgenerated 2019-01-12 14:06:24
     */
    private Date updateTime;

    /**
     * t_cgj_app_version.update_by (修改人)
     * @ibatorgenerated 2019-01-12 14:06:24
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getForcedUpdate() {
        return forcedUpdate;
    }

    public void setForcedUpdate(Integer forcedUpdate) {
        this.forcedUpdate = forcedUpdate;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getPartUpdateUrl() {
        return partUpdateUrl;
    }

    public void setPartUpdateUrl(String partUpdateUrl) {
        this.partUpdateUrl = partUpdateUrl;
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