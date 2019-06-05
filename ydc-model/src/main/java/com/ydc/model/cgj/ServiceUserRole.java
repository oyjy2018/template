package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

public class ServiceUserRole implements Serializable {
    private static final long serialVersionUID = -85460883591740491L;
    /**
     * t_cfg_service_user_role.id
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    private Integer id;

    /**
     * t_cfg_service_user_role.user_id (用户ID)
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    private Integer userId;

    /**
     * t_cfg_service_user_role.cgj_role_id (车管家服务角色ID)
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    private Integer cgjRoleId;

    /**
     * t_cfg_service_user_role.rental_role_id (租车服务角色ID)
     * @ibatorgenerated 2018-11-16 16:30:52
     */
    private Integer rentalRoleId;

    /**
     * t_cfg_service_user_role.ydhc_role_id (一点好车服务角色ID)
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    private Integer ydhcRoleId;

    /**
     * t_cfg_service_user_role.create_time (创建时间)
     * @ibatorgenerated 2018-11-16 13:49:11
     */
    private Date createTime;

    /**
     * t_cfg_service_user_role.create_by (创建人)
     * @ibatorgenerated 2018-11-16 13:49:11
     */
    private Integer createBy;

    /**
     * t_cfg_service_user_role.update_time (修改时间)
     * @ibatorgenerated 2018-11-16 13:49:11
     */
    private Date updateTime;

    /**
     * t_cfg_service_user_role.update_by (修改人)
     * @ibatorgenerated 2018-11-16 13:49:11
     */
    private Integer updateBy;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCgjRoleId() {
        return cgjRoleId;
    }

    public void setCgjRoleId(Integer cgjRoleId) {
        this.cgjRoleId = cgjRoleId;
    }

    public Integer getRentalRoleId() {
        return rentalRoleId;
    }

    public void setRentalRoleId(Integer rentalRoleId) {
        this.rentalRoleId = rentalRoleId;
    }

    public Integer getYdhcRoleId() {
        return ydhcRoleId;
    }

    public void setYdhcRoleId(Integer ydhcRoleId) {
        this.ydhcRoleId = ydhcRoleId;
    }
}