package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalRefuelInfo implements Serializable {
    private static final long serialVersionUID = 2452982936295222459L;
    /**
     * t_rental_refuel_info.id (id)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Integer id;

    /**
     * t_rental_refuel_info.maintenance_id (机务id(来源t_rental_order_maintenance表id))
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Integer maintenanceId;

    /**
     * t_rental_refuel_info.car_id (车辆id)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Integer carId;

    /**
     * t_rental_refuel_info.refuel_amount (加油量)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private BigDecimal refuelAmount;

    /**
     * t_rental_refuel_info.refuel_unit_price (加油单价)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private BigDecimal refuelUnitPrice;

    /**
     * t_rental_refuel_info.refuel_ogarage_oil_mass (出库油量)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private BigDecimal refuelOgarageOilMass;

    /**
     * t_rental_refuel_info.refuel_money (加油金额)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private BigDecimal refuelMoney;

    /**
     * t_rental_refuel_info.refuel_driver (驾车人)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private String refuelDriver;

    /**
     * t_rental_refuel_info.refuel_payment_way (加油付费方式（1：现金；2：油卡）)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Byte refuelPaymentWay;

    /**
     * t_rental_refuel_info.refuel_oil_card_balance (加油后油卡余额)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private BigDecimal refuelOilCardBalance;

    /**
     * t_rental_refuel_info.status (状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Byte status;

    /**
     * t_rental_refuel_info.create_time (创建时间)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Date createTime;

    /**
     * t_rental_refuel_info.create_by (创建人)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Integer createBy;

    /**
     * t_rental_refuel_info.update_time (更新时间)
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    private Date updateTime;

    /**
     * t_rental_refuel_info.update_by (更新人)
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

    public BigDecimal getRefuelAmount() {
        return refuelAmount;
    }

    public void setRefuelAmount(BigDecimal refuelAmount) {
        this.refuelAmount = refuelAmount;
    }

    public BigDecimal getRefuelUnitPrice() {
        return refuelUnitPrice;
    }

    public void setRefuelUnitPrice(BigDecimal refuelUnitPrice) {
        this.refuelUnitPrice = refuelUnitPrice;
    }

    public BigDecimal getRefuelOgarageOilMass() {
        return refuelOgarageOilMass;
    }

    public void setRefuelOgarageOilMass(BigDecimal refuelOgarageOilMass) {
        this.refuelOgarageOilMass = refuelOgarageOilMass;
    }

    public BigDecimal getRefuelMoney() {
        return refuelMoney;
    }

    public void setRefuelMoney(BigDecimal refuelMoney) {
        this.refuelMoney = refuelMoney;
    }

    public String getRefuelDriver() {
        return refuelDriver;
    }

    public void setRefuelDriver(String refuelDriver) {
        this.refuelDriver = refuelDriver;
    }

    public Byte getRefuelPaymentWay() {
        return refuelPaymentWay;
    }

    public void setRefuelPaymentWay(Byte refuelPaymentWay) {
        this.refuelPaymentWay = refuelPaymentWay;
    }

    public BigDecimal getRefuelOilCardBalance() {
        return refuelOilCardBalance;
    }

    public void setRefuelOilCardBalance(BigDecimal refuelOilCardBalance) {
        this.refuelOilCardBalance = refuelOilCardBalance;
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