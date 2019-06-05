package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新车辆启用状态类
 */
public class CommCarUpdateOperationStatusDTO implements Serializable {

    //车辆id
    @Attribute(name = "车辆id",required = true, emptyStringVerify = true)
    private Integer id;

    //状态
    @Attribute(name = "车辆运营状态",required = true, emptyStringVerify = true)
    private String operationStatus;

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

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
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
