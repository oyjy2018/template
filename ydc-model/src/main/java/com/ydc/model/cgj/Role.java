package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private static final long serialVersionUID = 3931733377542455853L;
    /**
     * t_cfg_role.id
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private Integer id;

    /**
     * t_cfg_role.role_name (角色名)
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private String roleName;

    /**
     * t_cfg_role.view_type (数据查看类型（1：个人；2：机构）)
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private String viewType;

    /**
     * t_cfg_role.service_identifying (服务标识（车管家：CGJ、租车：RENTAL))
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private String serviceIdentifying;

    /**
     * t_cfg_role.status (状态（1：正常；0：禁用）)
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private String status;

    /**
     * t_cfg_role.create_time (创建时间)
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private Date createTime;

    /**
     * t_cfg_role.create_by (创建人)
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private Integer createBy;

    /**
     * t_cfg_role.update_time (修改时间)
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private Date updateTime;

    /**
     * t_cfg_role.update_by (修改人)
     * @ibatorgenerated 2018-12-03 18:14:41
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
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