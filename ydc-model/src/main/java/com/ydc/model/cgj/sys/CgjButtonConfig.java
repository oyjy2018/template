package com.ydc.model.cgj.sys;

import java.io.Serializable;
import java.util.Date;

public class CgjButtonConfig implements Serializable {
    /**
     * t_cgj_button_config.id
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private Integer id;

    /**
     * t_cgj_button_config.client_dict_key (客户端key，对应字典表cgj_client)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private String clientDictKey;

    /**
     * t_cgj_button_config.module_dict_key (模块key，对应字典表cgj_advert_module)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private String moduleDictKey;

    /**
     * t_cgj_button_config.function_name (功能名称)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private String functionName;

    /**
     * t_cgj_button_config.button_code (按钮代码)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private String buttonCode;

    /**
     * t_cgj_button_config.button_name (按钮名称)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private String buttonName;

    /**
     * t_cgj_button_config.remark (备注)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private String remark;

    /**
     * t_cgj_button_config.switch_status (是否启用（0关，1开）)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private Integer switchStatus;

    /**
     * t_cgj_button_config.status (有效状态（0：无效；1：有效）)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private Integer status;

    /**
     * t_cgj_button_config.create_time (创建时间)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private Date createTime;

    /**
     * t_cgj_button_config.create_by (创建人)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private Integer createBy;

    /**
     * t_cgj_button_config.update_time (修改时间)
     * @ibatorgenerated 2019-01-08 14:55:56
     */
    private Date updateTime;

    /**
     * t_cgj_button_config.update_by (修改人)
     * @ibatorgenerated 2019-01-08 14:55:56
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

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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