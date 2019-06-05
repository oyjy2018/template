package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

public class Function implements Serializable {
    private static final long serialVersionUID = 7733959289270566576L;
    /**
     * t_cfg_function.id
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private Integer id;

    /**
     * t_cfg_function.fun_name (功能名)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private String funName;

    /**
     * t_cfg_function.fun_code (功能编码)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private String funCode;

    /**
     * t_cfg_function.fun_short_name (功能简称)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private String funShortName;

    /**
     * t_cfg_function.menu_code (菜单编码)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private String menuCode;

    /**
     * t_cfg_function.service_identifying (服务标识（车管家：CGJ、租车：RENTAL))
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private String serviceIdentifying;

    /**
     * t_cfg_function.status (状态（1：正常；0：禁用）)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private String status;

    /**
     * t_cfg_function.create_time (创建时间)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private Date createTime;

    /**
     * t_cfg_function.create_by (创建人)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private Integer createBy;

    /**
     * t_cfg_function.update_time (修改时间)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private Date updateTime;

    /**
     * t_cfg_function.update_by (修改人)
     * @ibatorgenerated 2018-12-03 18:11:28
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getFunShortName() {
        return funShortName;
    }

    public void setFunShortName(String funShortName) {
        this.funShortName = funShortName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getServiceIdentifying() {
        return serviceIdentifying;
    }

    public void setServiceIdentifying(String serviceIdentifying) {
        this.serviceIdentifying = serviceIdentifying;
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