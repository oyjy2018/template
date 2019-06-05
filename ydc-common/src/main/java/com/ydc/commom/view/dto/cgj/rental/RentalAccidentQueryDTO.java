package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalAccidentQueryDTO extends Pagination implements Serializable {

    //事故id
    private Integer id;

    //车辆id
    private Integer carId;

    //车牌
    private String carNumber;

    //车所在门店
    private String carStoreName;

    //类型（1：租车单事故；2：机务单事故）
    private Integer orderType;

    //订单id（租车id或者机务单id）
    private Integer orderId;

    //事故起因（1：碰撞；2：停放被破坏；3：盗抢）
    private Integer accidentCause;

    //(具体分类（01车辆碰撞第三方无损；02车辆碰撞第三方有损;03车辆停放被撞/被砸; 04车辆被划;05车辆玻璃单独破碎;06车辆自燃;07车辆被盗;08车辆被盗追回有车损;09车辆被盗未遂有损;10车上人员伤/亡;11车胎漏气破损;12车辆故障;13双方车辆相撞,14双方相撞对方逃逸,15双方车辆相撞有人伤/亡;16车辆与非机动车相撞有人伤/亡;17多方车辆相撞;18多方车辆相撞有人伤/亡;19被水淹）)
    private Integer accidentType;

    //事故责任（1：全责；2：主责；3：次责；4：对等；5：无责）
    private Integer accidentDuty;

    //事故类别（1：单方；2：双方；3：多方）
    private Integer accidentCategory;

    //事故等级（1：小事故；2：中事故；3：大事故；4：特大事故）
    private Integer accidentClassification;

    //经手人
    private Integer userId;

    //驾驶人
    private String driver;

    //驾驶人类型（1：客户；2：员工；3：维修工；4：无
    private Integer driverType;

    // (事故状态（0：未结；1：已结）)
    private Integer accidentStatus;

    //事故发生时间（起始值）
    private Date accidentStartTime;

    //事故发生时间（结束值）
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

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getAccidentStatus() {
        return accidentStatus;
    }

    public void setAccidentStatus(Integer accidentStatus) {
        this.accidentStatus = accidentStatus;
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
}