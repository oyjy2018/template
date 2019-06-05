package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新车辆启用状态类
 */
public class CommCarUpdateUseStatusDTO implements Serializable {

    //车辆id
    @Attribute(name = "车辆id",required = true, emptyStringVerify = true)
    private Integer id;

    //状态
    @Attribute(name = "车辆启用状态",required = true, emptyStringVerify = true)
    private String useStatus;

    //修改启用状态时间
    private Date useStatusUpdateTime = new Date();

    //修改启用状态人员
    private Integer useStatusUpdateBy;

    //更新时间
    private Date updateTime = new Date();

    //更新人
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public Date getUseStatusUpdateTime() {
        return useStatusUpdateTime;
    }

    public void setUseStatusUpdateTime(Date useStatusUpdateTime) {
        this.useStatusUpdateTime = useStatusUpdateTime;
    }

    public Integer getUseStatusUpdateBy() {
        return useStatusUpdateBy;
    }

    public void setUseStatusUpdateBy(Integer useStatusUpdateBy) {
        this.useStatusUpdateBy = useStatusUpdateBy;
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
