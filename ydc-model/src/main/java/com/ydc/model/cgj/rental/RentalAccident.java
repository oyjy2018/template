package com.ydc.model.cgj.rental;

import com.ydc.model.cgj.Pagination;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class RentalAccident extends Pagination implements Serializable {

    private static final long serialVersionUID = 5208253036815910469L;

    /**
     * t_rental_accident.id (id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer id;

    /**
     * t_rental_accident.car_id (车辆id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer carId;

    /**
     * t_rental_accident.car_number (车牌)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private String carNumber;

    /**
     * t_rental_accident.car_store_id (车所在门店id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private String carStoreId;

    /**
     * t_rental_accident.car_store_name (车所在门店)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private String carStoreName;

    /**
     * t_rental_accident.accident_time (事故发生时间)
     * @ibatorgenerated 2018-11-23 10:02:53
     */
    private Date accidentTime;

    /**
     * t_rental_accident.accident_cause (事故起因（1：碰撞；2：停放被破坏；3：盗抢）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer accidentCause;

    /**
     * t_rental_accident.accident_type (具体分类（01车辆碰撞第三方无损；02车辆碰撞第三方有损;03车辆停放被撞/被砸; 04车辆被划;05车辆玻璃单独破碎;06车辆自燃;07车辆被盗;08车辆被盗追回有车损;09车辆被盗未遂有损;10车上人员伤/亡;11车胎漏气破损;12车辆故障;13双方车辆相撞,14双方相撞对方逃逸,15双方车辆相撞有人伤/亡;16车辆与非机动车相撞有人伤/亡;17多方车辆相撞;18多方车辆相撞有人伤/亡;19被水淹）)
     * @ibatorgenerated 2018-11-23 10:02:53
     */
    private Integer accidentType;

    /**
     * t_rental_accident.accident_duty (事故责任（1：全责；2：主责；3：次责；4：对等；5：无责）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer accidentDuty;

    /**
     * t_rental_accident.accident_category (事故类别（1：单方；2：双方；3：多方）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer accidentCategory;

    /**
     * t_rental_accident.accident_classification (事故等级（1：小事故；2：中事故；3：大事故；4：特大事故）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer accidentClassification;

    /**
     * t_rental_accident.accident_amount (事故总金额)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private BigDecimal accidentAmount;

    /**
     * t_rental_accident.user_id (经手人)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer userId;

    /**
     * t_rental_accident.user_name (用户名)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private String userName;

    /**
     * t_rental_accident.driver (驾驶人)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private String driver;

    /**
     * t_rental_accident.driver_type (驾驶人类型（1：客户；2：员工；3：维修工；4：无）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer driverType;

    /**
     * t_rental_accident.accident_status (事故状态（0：未结；1：已结）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer accidentStatus;

    /**
     * t_rental_accident.order_id (订单id（租车id或者机务单id）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer orderId;

    /**
     * t_rental_accident.type (类型（1：租车单事故；2：机务单事故）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer orderType;

    /**
     * t_rental_accident.maintenance_order_id (处理事故机务id)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private String maintenanceOrderId;

    /**
     * t_rental_accident.status (状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer status;

    /**
     * t_rental_accident.create_time (创建时间)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Date createTime;

    /**
     * t_rental_accident.create_by (创建人)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer createBy;

    /**
     * t_rental_accident.update_time (更新时间)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Date updateTime;

    /**
     * t_rental_accident.update_by (更新人)
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    private Integer updateBy;

    /**
     * 所属门店
     */
    private String store;

    /**
     * 车牌号
     */
    private String carPlate;

    /**
     * 车辆品牌
     */
    private String brand;

    /**
     * 车系
     */
    private String series;

    /**
     * 车型
     */
    private String model;

    /**
     * 出车时间
     */
    private String comeCarTime;

    /**
     * 还车时间
     */
    private String repayCarTime;

    /**
     * 驾车人姓名
     */
    private String coachmanName;

    /**
     * 出库所在门店
     */
    private String comeWarehouseStoreName;

    /**
     * 还车所在门店
     */
    private String repayCarWarehouseStoreName;

    /**
     * 还车操作人姓名
     */
    private String repayCarCoachmanName;

    /**
     * 事故开始时间
     */
    private Date accidentStartTime;

    /**
     * 事故结束时间
     */
    private Date accidentEndTime;
    
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

    public String getCarStoreId() {
        return carStoreId;
    }

    public void setCarStoreId(String carStoreId) {
        this.carStoreId = carStoreId;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public BigDecimal getAccidentAmount() {
        return accidentAmount;
    }

    public void setAccidentAmount(BigDecimal accidentAmount) {
        this.accidentAmount = accidentAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Integer getDriverType() {
        return driverType;
    }

    public void setDriverType(Integer driverType) {
        this.driverType = driverType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getAccidentStatus() {
        return accidentStatus;
    }

    public void setAccidentStatus(Integer accidentStatus) {
        this.accidentStatus = accidentStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMaintenanceOrderId() {
        return maintenanceOrderId;
    }

    public void setMaintenanceOrderId(String maintenanceOrderId) {
        this.maintenanceOrderId = maintenanceOrderId;
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

    public Integer getAccidentCause() {
        return accidentCause;
    }

    public void setAccidentCause(Integer accidentCause) {
        this.accidentCause = accidentCause;
    }

    public Integer getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(Integer accidentType) {
        this.accidentType = accidentType;
    }

    public Integer getAccidentDuty() {
        return accidentDuty;
    }

    public void setAccidentDuty(Integer accidentDuty) {
        this.accidentDuty = accidentDuty;
    }

    public Integer getAccidentCategory() {
        return accidentCategory;
    }

    public void setAccidentCategory(Integer accidentCategory) {
        this.accidentCategory = accidentCategory;
    }

    public Integer getAccidentClassification() {
        return accidentClassification;
    }

    public void setAccidentClassification(Integer accidentClassification) {
        this.accidentClassification = accidentClassification;
    }
    public Date getAccidentTime() {
        return accidentTime;
    }
    public void setAccidentTime(Date accidentTime) {
        this.accidentTime = accidentTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(String comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public String getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(String repayCarTime) {
        this.repayCarTime = repayCarTime;
    }

    public String getCoachmanName() {
        return coachmanName;
    }

    public void setCoachmanName(String coachmanName) {
        this.coachmanName = coachmanName;
    }

    public String getComeWarehouseStoreName() {
        return comeWarehouseStoreName;
    }

    public void setComeWarehouseStoreName(String comeWarehouseStoreName) {
        this.comeWarehouseStoreName = comeWarehouseStoreName;
    }

    public String getRepayCarWarehouseStoreName() {
        return repayCarWarehouseStoreName;
    }

    public void setRepayCarWarehouseStoreName(String repayCarWarehouseStoreName) {
        this.repayCarWarehouseStoreName = repayCarWarehouseStoreName;
    }

    public String getRepayCarCoachmanName() {
        return repayCarCoachmanName;
    }

    public void setRepayCarCoachmanName(String repayCarCoachmanName) {
        this.repayCarCoachmanName = repayCarCoachmanName;
    }

    public Date getAccidentStartTime() {
        return accidentStartTime;
    }

    public void setAccidentStartTime(Date accidentStartTime) {
        this.accidentStartTime = accidentStartTime;
    }

    public Date getAccidentEndTime() {
        return accidentEndTime;
    }

    public void setAccidentEndTime(Date accidentEndTime) {
        this.accidentEndTime = accidentEndTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}