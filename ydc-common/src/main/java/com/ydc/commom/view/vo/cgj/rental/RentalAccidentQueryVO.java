package com.ydc.commom.view.vo.cgj.rental;

import com.ydc.commom.enums.rental.RentalAccidentEnum;
import com.ydc.model.cgj.Pagination;

import java.io.Serializable;
import java.util.Date;

public class RentalAccidentQueryVO extends Pagination implements Serializable {

    //事故id
    private Integer accidentId;

    //车辆id
    private Integer carId;

    //车牌
    private String carNumber;

    //车所在门店
    private String store;

    //类型（1：租车单事故；2：机务单事故）
    private String orderType;

    //订单id（租车id或者机务单id）
    private String orderId;

    //事故起因（1：碰撞；2：停放被破坏；3：盗抢）
    private String accidentCause;

    //(具体分类（01车辆碰撞第三方无损；02车辆碰撞第三方有损;03车辆停放被撞/被砸; 04车辆被划;05车辆玻璃单独破碎;06车辆自燃;07车辆被盗;08车辆被盗追回有车损;09车辆被盗未遂有损;10车上人员伤/亡;11车胎漏气破损;12车辆故障;13双方车辆相撞,14双方相撞对方逃逸,15双方车辆相撞有人伤/亡;16车辆与非机动车相撞有人伤/亡;17多方车辆相撞;18多方车辆相撞有人伤/亡;19被水淹）)
    private String accidentType;

    //事故责任（1：全责；2：主责；3：次责；4：对等；5：无责）
    private String accidentDuty;

    //事故类别（1：单方；2：双方；3：多方）
    private String accidentCategory;

    //事故等级（1：小事故；2：中事故；3：大事故；4：特大事故）
    private String accidentClassification;

    //经手人
    private String userName;

    //驾驶人
    private String driver;

    //驾驶人类型（1：客户；2：员工；3：维修工；4：无
    private String driverType;

    // (事故状态（0：未结；1：已结）)
    private String accidentStatus;

    //违章金额
    private String accidentAmount;

    //事故发生时间
    private String accidentTime;

    //创建时间
    private String createTime;

    public Integer getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Integer accidentId) {
        this.accidentId = accidentId;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = RentalAccidentEnum.OrderTypeEnum.getCodeName(Integer.valueOf(orderType));
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAccidentCause() {
        return accidentCause;
    }

    public void setAccidentCause(String accidentCause) {
        this.accidentCause = RentalAccidentEnum.AccidentCauseEnum.getCodeName(Integer.valueOf(accidentCause));
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = RentalAccidentEnum.AccidentTypeEnum.getCodeName(Integer.valueOf(accidentType));
    }

    public String getAccidentDuty() {
        return accidentDuty;
    }

    public void setAccidentDuty(String accidentDuty) {
        this.accidentDuty = RentalAccidentEnum.AccidentDutyEnum.getCodeName(Integer.valueOf(accidentDuty));
    }

    public String getAccidentCategory() {
        return accidentCategory;
    }

    public void setAccidentCategory(String accidentCategory) {
        this.accidentCategory = RentalAccidentEnum.AccidentCategoryEnum.getCodeName(Integer.valueOf(accidentCategory));
    }

    public String getAccidentClassification() {
        return accidentClassification;
    }

    public void setAccidentClassification(String accidentClassification) {
        this.accidentClassification = RentalAccidentEnum.AccidentClassificationEnum.getCodeName(Integer.valueOf(accidentClassification));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = RentalAccidentEnum.DriverTypeEnum.getCodeName(Integer.valueOf(driverType));
    }

    public String getAccidentStatus() {
        return accidentStatus;
    }

    public void setAccidentStatus(String accidentStatus) {
        this.accidentStatus = RentalAccidentEnum.AccidentStatusEnum.getCodeName(Integer.valueOf(accidentStatus));
    }

    public String getAccidentAmount() {
        return accidentAmount;
    }

    public void setAccidentAmount(String accidentAmount) {
        this.accidentAmount = accidentAmount;
    }

    public String getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(String accidentTime) {
        this.accidentTime = accidentTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}