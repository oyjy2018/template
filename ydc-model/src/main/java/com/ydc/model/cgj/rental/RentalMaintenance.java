package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalMaintenance implements Serializable {
    private static final long serialVersionUID = -3225958925268802548L;
    /**
     * t_rental_maintenance.id (id)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Integer id;

    /**
     * t_rental_maintenance.maintenance_id (机务id(来源t_rental_order_maintenance表id))
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Integer maintenanceId;

    /**
     * t_rental_maintenance.car_id (车辆id)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Integer carId;

    /**
     * t_rental_maintenance.delivery_type (送修种类(1：保养；2：维修))
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private String deliveryType;

    /**
     * t_rental_maintenance.factory_name (维修厂)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private String factoryName;

    /**
     * t_rental_maintenance.maintenance_costs (维修费用)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private BigDecimal maintenanceCosts;

    /**
     * t_rental_maintenance.maintenance_time (维修时间)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Date maintenanceTime;

    /**
     * t_rental_maintenance.status (状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Byte status;

    /**
     * t_rental_maintenance.create_time (创建时间)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Date createTime;

    /**
     * t_rental_maintenance.create_by (创建人)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Integer createBy;

    /**
     * t_rental_maintenance.update_time (更新时间)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Date updateTime;

    /**
     * t_rental_maintenance.update_by (更新人)
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    private Integer updateBy;

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

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public BigDecimal getMaintenanceCosts() {
        return maintenanceCosts;
    }

    public void setMaintenanceCosts(BigDecimal maintenanceCosts) {
        this.maintenanceCosts = maintenanceCosts;
    }

    public Date getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(Date maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
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