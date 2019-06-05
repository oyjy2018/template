package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * t_rental_dispatch
 * @author 
 */
public class RentalDispatch implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 机务id(来源t_rental_order_maintenance表id)
     */
    private Integer maintenanceId;

    /**
     * 车辆id
     */
    private Integer carId;

    /**
     * 调度类型(0：同城调度；1：城际调度)
     */
    private Byte dispatchType;

    /**
     * 运输方式(0：开车；1：托运)
     */
    private Byte transportMode;

    /**
     * 调度费用
     */
    private BigDecimal dispatchCosts;

    /**
     * 状态（0：失效；1：有效）
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private Integer updateBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Byte getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(Byte dispatchType) {
        this.dispatchType = dispatchType;
    }

    public Byte getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(Byte transportMode) {
        this.transportMode = transportMode;
    }

    public BigDecimal getDispatchCosts() {
        return dispatchCosts;
    }

    public void setDispatchCosts(BigDecimal dispatchCosts) {
        this.dispatchCosts = dispatchCosts;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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