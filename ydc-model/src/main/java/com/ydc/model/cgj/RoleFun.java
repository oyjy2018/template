package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleFun implements Serializable {
    /**
     * t_cfg_role_fun.id
     * @ibatorgenerated 2018-12-03 18:53:08
     */
    private Integer id;

    /**
     * t_cfg_role_fun.role_id (角色ID)
     * @ibatorgenerated 2018-12-03 18:53:08
     */
    private Integer roleId;

    /**
     * t_cfg_role_fun.fun_id (功能ID)
     * @ibatorgenerated 2018-12-03 18:53:08
     */
    private Integer funId;

    /**
     * t_cfg_role_fun.create_time (创建时间)
     * @ibatorgenerated 2018-12-03 18:53:08
     */
    private Date createTime;

    /**
     * 功能集合
     */
    private List<Function> functions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }
}