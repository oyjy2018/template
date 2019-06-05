package com.ydc.model.ydhc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * t_ydhc_cfg_region
 * @author 
 */
public class YdhcRegion implements Serializable {
    /**
     * 地区编码
     */
    private Integer regionCode;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 父地区编码
     */
    private Integer parentRegionCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    private List<YdhcRegion> sonRegionList;

    private static final long serialVersionUID = 1L;

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getParentRegionCode() {
        return parentRegionCode;
    }

    public void setParentRegionCode(Integer parentRegionCode) {
        this.parentRegionCode = parentRegionCode;
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

    public List<YdhcRegion> getSonRegionList() {
        return sonRegionList;
    }

    public void setSonRegionList(List<YdhcRegion> sonRegionList) {
        this.sonRegionList = sonRegionList;
    }
}