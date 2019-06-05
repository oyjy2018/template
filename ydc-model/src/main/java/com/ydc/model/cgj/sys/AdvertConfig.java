package com.ydc.model.cgj.sys;

import java.io.Serializable;
import java.util.Date;

public class AdvertConfig implements Serializable {
    /**
     * t_cgj_advert_config.id (主键ID)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private Integer id;

    /**
     * t_cgj_advert_config.client_dict_key (客户端key)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private String clientDictKey;

    /**
     * t_cgj_advert_config.module_dict_key (功能模块key)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private String moduleDictKey;

    /**
     * t_cgj_advert_config.sort (排序)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private Integer sort;

    /**
     * t_cgj_advert_config.title (活动标题)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private String title;

    /**
     * t_cgj_advert_config.remark (活动描述)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private String remark;

    /**
     * t_cgj_advert_config.jump_url (广告投放链接)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private String jumpUrl;

    /**
     * t_cgj_advert_config.switch_status (是否启用（0关，1开）)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private Integer switchStatus;

    /**
     * t_cgj_advert_config.status (有效状态（0：无效；1：有效）)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private Integer status;

    /**
     * t_cgj_advert_config.create_time (创建时间)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private Date createTime;

    /**
     * t_cgj_advert_config.create_by (创建人)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private Integer createBy;

    /**
     * t_cgj_advert_config.update_time (修改时间)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private Date updateTime;

    /**
     * t_cgj_advert_config.update_by (修改人)
     * @ibatorgenerated 2019-01-02 16:34:36
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientDictKey() {
        return clientDictKey;
    }

    public void setClientDictKey(String clientDictKey) {
        this.clientDictKey = clientDictKey;
    }

    public String getModuleDictKey() {
        return moduleDictKey;
    }

    public void setModuleDictKey(String moduleDictKey) {
        this.moduleDictKey = moduleDictKey;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
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