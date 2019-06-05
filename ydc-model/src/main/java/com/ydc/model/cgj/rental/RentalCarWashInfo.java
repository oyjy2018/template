package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalCarWashInfo implements Serializable {
    private static final long serialVersionUID = 8644450932591122340L;
    /**
     * t_rental_car_wash_info.id (id)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Integer id;

    /**
     * t_rental_car_wash_info.maintenance_id (机务id(来源t_rental_order_maintenance表id))
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Integer maintenanceId;

    /**
     * t_rental_car_wash_info.car_id (车辆id)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Integer carId;

    /**
     * t_rental_car_wash_info.wash_car_store (洗车门店)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private String washCarStore;

    /**
     * t_rental_car_wash_info.wash_car_cost (洗车费用)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private BigDecimal washCarCost;

    /**
     * t_rental_car_wash_info.status (状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Byte status;

    /**
     * t_rental_car_wash_info.create_time (创建时间)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Date createTime;

    /**
     * t_rental_car_wash_info.create_by (创建人)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Integer createBy;

    /**
     * t_rental_car_wash_info.update_time (更新时间)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Date updateTime;

    /**
     * t_rental_car_wash_info.update_by (更新人)
     * @ibatorgenerated 2018-11-22 10:29:04
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

    public String getWashCarStore() {
        return washCarStore;
    }

    public void setWashCarStore(String washCarStore) {
        this.washCarStore = washCarStore;
    }

    public BigDecimal getWashCarCost() {
        return washCarCost;
    }

    public void setWashCarCost(BigDecimal washCarCost) {
        this.washCarCost = washCarCost;
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