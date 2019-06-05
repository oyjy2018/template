package com.ydc.model.cgj.rental;

import com.ydc.model.annotation.Attribute;

import java.io.Serializable;
import java.util.Date;

public class RentalOrderMaintenance implements Serializable {
    private static final long serialVersionUID = -4047296943415471970L;
    /**
     * t_rental_order_maintenance.id (id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "机务单id",required = true)
    private Integer id;

    /**
     * t_rental_order_maintenance.car_id (车辆id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "车辆id",required = true)
    private Integer carId;

    /**
     * t_rental_order_maintenance.car_number (车牌号)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "车牌号",required = true,maxLength = 9)
    private String carNumber;

    /**
     * t_rental_order_maintenance.brand (车辆门店id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "所属门店id",required = true)
    private Integer carStoreId;

    /**
     * t_rental_order_maintenance.series (车辆门店名称)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "所属门店名",required = true)
    private String carStoreName;

    /**
     * t_rental_order_maintenance.come_car_time (出车时间)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "出车时间",required = true)
    private Date comeCarTime;

    /**
     * t_rental_order_maintenance.come_warehouse_mileage (出库已行驶里程)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "出库里程数",required = true,isNum = true,maxLength = 8)
    private String comeWarehouseMileage;

    /**
     * t_rental_order_maintenance.come_warehouse_oil_amount (出库油量)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "出库油量",required = true,isNum = true,maxLength = 3)
    private String comeWarehouseOilAmount;

    /**
     * t_rental_order_maintenance.come_warehouse_store_id (出库所在门店id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "出库所在门店id",required = true,isNum = true)
    private String comeWarehouseStoreId;

    /**
     * t_rental_order_maintenance.come_warehouse_store_name (出库所在门店)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "出库所在门店",required = true)
    private String comeWarehouseStoreName;

    /**
     * t_rental_order_maintenance.come_car_user_id (驾车人员)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "驾车人员Id",required = true,isNum = true)
    private Integer comeCarUserId;

    /**
     * 驾驶人姓名
     */
    @Attribute(name = "驾车人员名",required = true)
    private String comeCarUserName;

    /**
     * t_rental_order_maintenance.maintenance_type (机务类型（多个，以,分割）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "机务类型",required = true)
    private String maintenanceType;

    /**
     * t_rental_order_maintenance.transport_mode (运输方式)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Byte transportMode;

    /**
     * t_rental_order_maintenance.come_car_remark (出车备注)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private String comeCarRemark;

    /**
     * t_rental_order_maintenance.repay_car (是否还车（0：未还；1：已还）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Byte repayCar;

    /**
     * t_rental_order_maintenance.repay_car_time (还车时间)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "repayCarTime",required = true)
    private Date repayCarTime;

    /**
     * t_rental_order_maintenance.repay_car_mileage (还车已行驶里程)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "还车里程数",required = true,isNum = true)
    private String repayCarMileage;

    /**
     * t_rental_order_maintenance.repay_car_oil_amount (还车油量)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "还车油量",required = true,isNum = true)
    private String repayCarOilAmount;

    /**
     * t_rental_order_maintenance.repay_car_store_id (还车所在门店id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "还车所在门店id",required = true)
    private String repayCarStoreId;

    /**
     * t_rental_order_maintenance.repay_car_store_name (还车所在门店)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    @Attribute(name = "还车所在门店名",required = true)
    private String repayCarStoreName;

    /**
     * t_rental_order_maintenance.repay_car_user_id (还车操作人)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer repayCarUserId;

    /**
     * 还车操作人姓名
     */
    private String repayCarUserName;

    /**
     * 还车备注
     */
    private String repayCarRemark;

    /**
     * t_rental_order_maintenance.status (状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Byte status;

    /**
     * t_rental_order_maintenance.create_time (创建时间)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Date createTime;

    /**
     * t_rental_order_maintenance.create_by (创建人)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer createBy;

    /**
     * t_rental_order_maintenance.update_time (更新时间)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Date updateTime;

    /**
     * t_rental_order_maintenance.update_by (更新人)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(Date comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public String getComeWarehouseMileage() {
        return comeWarehouseMileage;
    }

    public void setComeWarehouseMileage(String comeWarehouseMileage) {
        this.comeWarehouseMileage = comeWarehouseMileage;
    }

    public String getComeWarehouseOilAmount() {
        return comeWarehouseOilAmount;
    }

    public void setComeWarehouseOilAmount(String comeWarehouseOilAmount) {
        this.comeWarehouseOilAmount = comeWarehouseOilAmount;
    }

    public String getComeWarehouseStoreId() {
        return comeWarehouseStoreId;
    }

    public void setComeWarehouseStoreId(String comeWarehouseStoreId) {
        this.comeWarehouseStoreId = comeWarehouseStoreId;
    }

    public String getComeWarehouseStoreName() {
        return comeWarehouseStoreName;
    }

    public void setComeWarehouseStoreName(String comeWarehouseStoreName) {
        this.comeWarehouseStoreName = comeWarehouseStoreName;
    }

    public Integer getComeCarUserId() {
        return comeCarUserId;
    }

    public void setComeCarUserId(Integer comeCarUserId) {
        this.comeCarUserId = comeCarUserId;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public Byte getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(Byte transportMode) {
        this.transportMode = transportMode;
    }

    public String getComeCarRemark() {
        return comeCarRemark;
    }

    public void setComeCarRemark(String comeCarRemark) {
        this.comeCarRemark = comeCarRemark;
    }

    public Byte getRepayCar() {
        return repayCar;
    }

    public void setRepayCar(Byte repayCar) {
        this.repayCar = repayCar;
    }

    public Date getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(Date repayCarTime) {
        this.repayCarTime = repayCarTime;
    }

    public String getRepayCarMileage() {
        return repayCarMileage;
    }

    public void setRepayCarMileage(String repayCarMileage) {
        this.repayCarMileage = repayCarMileage;
    }

    public String getRepayCarOilAmount() {
        return repayCarOilAmount;
    }

    public void setRepayCarOilAmount(String repayCarOilAmount) {
        this.repayCarOilAmount = repayCarOilAmount;
    }

    public String getRepayCarStoreId() {
        return repayCarStoreId;
    }

    public void setRepayCarStoreId(String repayCarStoreId) {
        this.repayCarStoreId = repayCarStoreId;
    }

    public String getRepayCarStoreName() {
        return repayCarStoreName;
    }

    public void setRepayCarStoreName(String repayCarStoreName) {
        this.repayCarStoreName = repayCarStoreName;
    }

    public Integer getRepayCarUserId() {
        return repayCarUserId;
    }

    public void setRepayCarUserId(Integer repayCarUserId) {
        this.repayCarUserId = repayCarUserId;
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

    public Integer getCarStoreId() {
        return carStoreId;
    }

    public void setCarStoreId(Integer carStoreId) {
        this.carStoreId = carStoreId;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public String getComeCarUserName() {
        return comeCarUserName;
    }

    public void setComeCarUserName(String comeCarUserName) {
        this.comeCarUserName = comeCarUserName;
    }

    public String getRepayCarUserName() {
        return repayCarUserName;
    }

    public void setRepayCarUserName(String repayCarUserName) {
        this.repayCarUserName = repayCarUserName;
    }

    public String getRepayCarRemark() {
        return repayCarRemark;
    }

    public void setRepayCarRemark(String repayCarRemark) {
        this.repayCarRemark = repayCarRemark;
    }
}