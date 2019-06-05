package com.ydc.model.ydhc;

import java.io.Serializable;
import java.util.Date;

/**
 * t_ydhc_vehicle_brand
 * @author
 */
public class YdhcVehicleBrand implements Serializable {
    private Integer id;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 标签
     */
    private String label;

    /**
     * 图标路径
     */
    private String icon;

    /**
     * 是否热门品牌
     */
    private Integer hasHot;

    /**
     * 状态（1：启用 0：禁用）
     */
    private Integer status;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getHasHot() {
        return hasHot;
    }

    public void setHasHot(Integer hasHot) {
        this.hasHot = hasHot;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}