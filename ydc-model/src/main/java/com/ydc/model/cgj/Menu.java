package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 5983478876485224180L;
    /**
     * t_cfg_menu.id
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private Integer id;

    /**
     * t_cfg_menu.menu_name (菜单名)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private String menuName;

    /**
     * t_cfg_menu.menu_code (菜单编码)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private String menuCode;

    /**
     * t_cfg_menu.menu_url (菜单路径)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private String menuUrl;

    /**
     * t_cfg_menu.parent_menu_code (父菜单ID)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private String parentMenuCode;

    /**
     * t_cfg_menu.sort (排序)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private Integer sort;

    /**
     * t_cfg_menu.hierarchy (层级)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private Byte hierarchy;

    /**
     * t_cfg_menu.menu_type (1 系统内部链接；2 其它系统链接 )
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private Integer menuType;

    /**
     * t_cfg_menu.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private String status;

    /**
     * t_cfg_menu.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private Date createTime;

    /**
     * t_cfg_menu.create_by (创建人)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private Integer createBy;

    /**
     * t_cfg_menu.update_time (修改时间)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private Date updateTime;

    /**
     * t_cfg_menu.update_by (修改人)
     *
     * @ibatorgenerated 2018-09-04 11:19:28
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Byte hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
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