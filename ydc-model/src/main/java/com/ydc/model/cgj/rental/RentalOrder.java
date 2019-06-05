package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentalOrder implements Serializable {
    private static final long serialVersionUID = 3579913004553553849L;
    /**
     * t_rental_order.id
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer id;

    /**
     * t_rental_order.order_no (订单号)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String orderNo;

    /**
     * t_rental_order.member_id (会员id)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer memberId;

    /**
     * t_rental_order.car_id (车辆id)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer carId;

    /**
     * t_rental_order.car_number (车牌号)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String carNumber;

    /**
     * t_rental_order.car_level (车等级)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String carLevel;

    /**
     * t_rental_order.brand (车辆品牌)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String brand;

    /**
     * t_rental_order.series (车系)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String series;

    /**
     * t_rental_order.model (车型)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String model;

    /**
     * t_rental_order.mobile_phone (手机号)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String mobilePhone;

    /**
     * t_rental_order.name (姓名)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String name;

    /**
     * t_rental_order.id_card (身份证)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String idCard;

    /**
     * t_rental_order.fetch_car_mode (取车方式（1：到店取车；2：送车上门）)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Byte fetchCarMode;

    /**
     * t_rental_order.repay_car_mode (还车方式（1：到店还车；2：送车上门）)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Byte repayCarMode;

    /**
     * t_rental_order.appointment_fetch_car_time (预约取车时间)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Date appointmentFetchCarTime;

    /**
     * t_rental_order.appointment_repay_car_time (预约还车时间)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Date appointmentRepayCarTime;

    /**
     * t_rental_order.appointment_store_id (预约取车门店id)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer appointmentStoreId;

    /**
     * t_rental_order.appointment_store_name (预约取车门店名)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentStoreName;

    /**
     * t_rental_order.appointment_repay_store_id (预约还车门店id)
     * @ibatorgenerated 2018-11-30 21:16:46
     */
    private Integer appointmentRepayStoreId;

    /**
     * t_rental_order.appointment_repay_store_name (预约还车门店名)
     * @ibatorgenerated 2018-11-30 21:16:46
     */
    private String appointmentRepayStoreName;

    /**
     * t_rental_order.appointment_fetch_car_province_code (预约取车地址省编码)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer appointmentFetchCarProvinceCode;

    /**
     * t_rental_order.appointment_fetch_car_province (预约取车地址省)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentFetchCarProvince;

    /**
     * t_rental_order.appointment_fetch_car_city_code (预约取车地址市编码)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer appointmentFetchCarCityCode;

    /**
     * t_rental_order.appointment_fetch_car_city (预约取车地址市)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentFetchCarCity;

    /**
     * t_rental_order.appointment_fetch_car_region_code (预约取车地址区编码)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer appointmentFetchCarRegionCode;

    /**
     * t_rental_order.appointment_fetch_car_region (预约取车地址区)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentFetchCarRegion;

    /**
     * t_rental_order.appointment_fetch_car_details_address (预约取车详情地址)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentFetchCarDetailsAddress;

    /**
     * t_rental_order.appointment_repay_car_province_code (预约还车地址省编码)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer appointmentRepayCarProvinceCode;

    /**
     * t_rental_order.appointment_repay_car_province (预约还车地址省)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentRepayCarProvince;

    /**
     * t_rental_order.appointment_repay_car_city_code (预约还车地址市编码)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer appointmentRepayCarCityCode;

    /**
     * t_rental_order.appointment_repay_car_city (预约还车地址市)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentRepayCarCity;

    /**
     * t_rental_order.appointment_repay_car_region_code (预约还车地址区编码)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer appointmentRepayCarRegionCode;

    /**
     * t_rental_order.appointment_repay_car_region (预约还车地址区)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentRepayCarRegion;

    /**
     * t_rental_order.appointment_repay_car_details_address (预约还车详情地址)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String appointmentRepayCarDetailsAddress;

    /**
     * t_rental_order.come_car_time (出车时间)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Date comeCarTime;

    /**
     * t_rental_order.come_warehouse_mileage (出库已行驶里程)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String comeWarehouseMileage;

    /**
     * t_rental_order.come_warehouse_oil_amount (出库油量)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer comeWarehouseOilAmount;

    /**
     * t_rental_order.come_warehouse_store_id (出库所在门店id)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer comeWarehouseStoreId;

    /**
     * t_rental_order.come_warehouse_store_name (出库所在门店)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String comeWarehouseStoreName;

    /**
     * t_rental_order.coachman_id (驾驶人id)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer coachmanId;

    /**
     * t_rental_order.coachman_name (驾驶人姓名)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String coachmanName;

    /**
     * t_rental_order.come_car_remark (出车备注)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String comeCarRemark;

    /**
     * t_rental_order.order_receivable_money (订单应收金额)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private BigDecimal orderReceivableMoney;

    /**
     * t_rental_order.order_practical_money (订单实收金额)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private BigDecimal orderPracticalMoney;

    /**
     * t_rental_order.repay_car_time (还车时间)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Date repayCarTime;

    /**
     * t_rental_order.repay_car_mileage (还车已行驶里程)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String repayCarMileage;

    /**
     * t_rental_order.repay_car_oil_amount (还车油量)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer repayCarOilAmount;

    /**
     * t_rental_order.repay_car_warehouse_store_id (还车所在门店id)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer repayCarWarehouseStoreId;

    /**
     * t_rental_order.repay_car_warehouse_store_name (还车所在门店)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String repayCarWarehouseStoreName;

    /**
     * t_rental_order.repay_car_coachman_id (还车操作人id)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer repayCarCoachmanId;

    /**
     * t_rental_order.repay_car_coachman_name (还车操作人姓名)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String repayCarCoachmanName;

    /**
     * t_rental_order.repay_car_remark (还车备注)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String repayCarRemark;

    /**
     * t_rental_order.violation_number (违章次数)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer violationNumber;

    /**
     * t_rental_order.status (状态（1：备车；2：用车；3：还车；4：结算；98：已取消；99：已拒绝）)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer status;

    /**
     * t_rental_order.flow_one_status (流程状态一)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer flowOneStatus;

    /**
     * t_rental_order.flow_two_status (流程状态二)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer flowTwoStatus;

    /**
     * t_rental_order.flow_three_status (流程状态三)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer flowThreeStatus;

    /**
     * t_rental_order.close_before_status (关闭之前状态)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer closeBeforeStatus;

    /**
     * t_rental_order.close_cause (关闭原因)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private String closeCause;

    /**
     * t_rental_order.close_time (关闭时间)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Date closeTime;

    /**
     * t_rental_order.create_time (创建时间)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Date createTime;

    /**
     * t_rental_order.create_by (创建人)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer createBy;

    /**
     * t_rental_order.update_time (修改时间)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Date updateTime;

    /**
     * t_rental_order.update_by (修改人)
     * @ibatorgenerated 2018-11-24 09:35:30
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Byte getFetchCarMode() {
        return fetchCarMode;
    }

    public void setFetchCarMode(Byte fetchCarMode) {
        this.fetchCarMode = fetchCarMode;
    }

    public Byte getRepayCarMode() {
        return repayCarMode;
    }

    public void setRepayCarMode(Byte repayCarMode) {
        this.repayCarMode = repayCarMode;
    }

    public Date getAppointmentFetchCarTime() {
        return appointmentFetchCarTime;
    }

    public void setAppointmentFetchCarTime(Date appointmentFetchCarTime) {
        this.appointmentFetchCarTime = appointmentFetchCarTime;
    }

    public Date getAppointmentRepayCarTime() {
        return appointmentRepayCarTime;
    }

    public void setAppointmentRepayCarTime(Date appointmentRepayCarTime) {
        this.appointmentRepayCarTime = appointmentRepayCarTime;
    }

    public Integer getAppointmentStoreId() {
        return appointmentStoreId;
    }

    public void setAppointmentStoreId(Integer appointmentStoreId) {
        this.appointmentStoreId = appointmentStoreId;
    }

    public String getAppointmentStoreName() {
        return appointmentStoreName;
    }

    public void setAppointmentStoreName(String appointmentStoreName) {
        this.appointmentStoreName = appointmentStoreName;
    }

    public Integer getAppointmentFetchCarProvinceCode() {
        return appointmentFetchCarProvinceCode;
    }

    public void setAppointmentFetchCarProvinceCode(Integer appointmentFetchCarProvinceCode) {
        this.appointmentFetchCarProvinceCode = appointmentFetchCarProvinceCode;
    }

    public String getAppointmentFetchCarProvince() {
        return appointmentFetchCarProvince;
    }

    public void setAppointmentFetchCarProvince(String appointmentFetchCarProvince) {
        this.appointmentFetchCarProvince = appointmentFetchCarProvince;
    }

    public Integer getAppointmentFetchCarCityCode() {
        return appointmentFetchCarCityCode;
    }

    public void setAppointmentFetchCarCityCode(Integer appointmentFetchCarCityCode) {
        this.appointmentFetchCarCityCode = appointmentFetchCarCityCode;
    }

    public String getAppointmentFetchCarCity() {
        return appointmentFetchCarCity;
    }

    public void setAppointmentFetchCarCity(String appointmentFetchCarCity) {
        this.appointmentFetchCarCity = appointmentFetchCarCity;
    }

    public Integer getAppointmentFetchCarRegionCode() {
        return appointmentFetchCarRegionCode;
    }

    public void setAppointmentFetchCarRegionCode(Integer appointmentFetchCarRegionCode) {
        this.appointmentFetchCarRegionCode = appointmentFetchCarRegionCode;
    }

    public String getAppointmentFetchCarRegion() {
        return appointmentFetchCarRegion;
    }

    public void setAppointmentFetchCarRegion(String appointmentFetchCarRegion) {
        this.appointmentFetchCarRegion = appointmentFetchCarRegion;
    }

    public String getAppointmentFetchCarDetailsAddress() {
        return appointmentFetchCarDetailsAddress;
    }

    public void setAppointmentFetchCarDetailsAddress(String appointmentFetchCarDetailsAddress) {
        this.appointmentFetchCarDetailsAddress = appointmentFetchCarDetailsAddress;
    }

    public Integer getAppointmentRepayCarProvinceCode() {
        return appointmentRepayCarProvinceCode;
    }

    public void setAppointmentRepayCarProvinceCode(Integer appointmentRepayCarProvinceCode) {
        this.appointmentRepayCarProvinceCode = appointmentRepayCarProvinceCode;
    }

    public String getAppointmentRepayCarProvince() {
        return appointmentRepayCarProvince;
    }

    public void setAppointmentRepayCarProvince(String appointmentRepayCarProvince) {
        this.appointmentRepayCarProvince = appointmentRepayCarProvince;
    }

    public Integer getAppointmentRepayCarCityCode() {
        return appointmentRepayCarCityCode;
    }

    public void setAppointmentRepayCarCityCode(Integer appointmentRepayCarCityCode) {
        this.appointmentRepayCarCityCode = appointmentRepayCarCityCode;
    }

    public String getAppointmentRepayCarCity() {
        return appointmentRepayCarCity;
    }

    public void setAppointmentRepayCarCity(String appointmentRepayCarCity) {
        this.appointmentRepayCarCity = appointmentRepayCarCity;
    }

    public Integer getAppointmentRepayCarRegionCode() {
        return appointmentRepayCarRegionCode;
    }

    public void setAppointmentRepayCarRegionCode(Integer appointmentRepayCarRegionCode) {
        this.appointmentRepayCarRegionCode = appointmentRepayCarRegionCode;
    }

    public String getAppointmentRepayCarRegion() {
        return appointmentRepayCarRegion;
    }

    public void setAppointmentRepayCarRegion(String appointmentRepayCarRegion) {
        this.appointmentRepayCarRegion = appointmentRepayCarRegion;
    }

    public String getAppointmentRepayCarDetailsAddress() {
        return appointmentRepayCarDetailsAddress;
    }

    public void setAppointmentRepayCarDetailsAddress(String appointmentRepayCarDetailsAddress) {
        this.appointmentRepayCarDetailsAddress = appointmentRepayCarDetailsAddress;
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

    public Integer getComeWarehouseOilAmount() {
        return comeWarehouseOilAmount;
    }

    public void setComeWarehouseOilAmount(Integer comeWarehouseOilAmount) {
        this.comeWarehouseOilAmount = comeWarehouseOilAmount;
    }

    public Integer getComeWarehouseStoreId() {
        return comeWarehouseStoreId;
    }

    public void setComeWarehouseStoreId(Integer comeWarehouseStoreId) {
        this.comeWarehouseStoreId = comeWarehouseStoreId;
    }

    public String getComeWarehouseStoreName() {
        return comeWarehouseStoreName;
    }

    public void setComeWarehouseStoreName(String comeWarehouseStoreName) {
        this.comeWarehouseStoreName = comeWarehouseStoreName;
    }

    public Integer getCoachmanId() {
        return coachmanId;
    }

    public void setCoachmanId(Integer coachmanId) {
        this.coachmanId = coachmanId;
    }

    public String getCoachmanName() {
        return coachmanName;
    }

    public void setCoachmanName(String coachmanName) {
        this.coachmanName = coachmanName;
    }

    public String getComeCarRemark() {
        return comeCarRemark;
    }

    public void setComeCarRemark(String comeCarRemark) {
        this.comeCarRemark = comeCarRemark;
    }

    public BigDecimal getOrderReceivableMoney() {
        return orderReceivableMoney;
    }

    public void setOrderReceivableMoney(BigDecimal orderReceivableMoney) {
        this.orderReceivableMoney = orderReceivableMoney;
    }

    public BigDecimal getOrderPracticalMoney() {
        return orderPracticalMoney;
    }

    public void setOrderPracticalMoney(BigDecimal orderPracticalMoney) {
        this.orderPracticalMoney = orderPracticalMoney;
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

    public Integer getRepayCarOilAmount() {
        return repayCarOilAmount;
    }

    public void setRepayCarOilAmount(Integer repayCarOilAmount) {
        this.repayCarOilAmount = repayCarOilAmount;
    }

    public Integer getRepayCarWarehouseStoreId() {
        return repayCarWarehouseStoreId;
    }

    public void setRepayCarWarehouseStoreId(Integer repayCarWarehouseStoreId) {
        this.repayCarWarehouseStoreId = repayCarWarehouseStoreId;
    }

    public String getRepayCarWarehouseStoreName() {
        return repayCarWarehouseStoreName;
    }

    public void setRepayCarWarehouseStoreName(String repayCarWarehouseStoreName) {
        this.repayCarWarehouseStoreName = repayCarWarehouseStoreName;
    }

    public Integer getRepayCarCoachmanId() {
        return repayCarCoachmanId;
    }

    public void setRepayCarCoachmanId(Integer repayCarCoachmanId) {
        this.repayCarCoachmanId = repayCarCoachmanId;
    }

    public String getRepayCarCoachmanName() {
        return repayCarCoachmanName;
    }

    public void setRepayCarCoachmanName(String repayCarCoachmanName) {
        this.repayCarCoachmanName = repayCarCoachmanName;
    }

    public String getRepayCarRemark() {
        return repayCarRemark;
    }

    public void setRepayCarRemark(String repayCarRemark) {
        this.repayCarRemark = repayCarRemark;
    }

    public Integer getViolationNumber() {
        return violationNumber;
    }

    public void setViolationNumber(Integer violationNumber) {
        this.violationNumber = violationNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlowOneStatus() {
        return flowOneStatus;
    }

    public void setFlowOneStatus(Integer flowOneStatus) {
        this.flowOneStatus = flowOneStatus;
    }

    public Integer getFlowTwoStatus() {
        return flowTwoStatus;
    }

    public void setFlowTwoStatus(Integer flowTwoStatus) {
        this.flowTwoStatus = flowTwoStatus;
    }

    public Integer getFlowThreeStatus() {
        return flowThreeStatus;
    }

    public void setFlowThreeStatus(Integer flowThreeStatus) {
        this.flowThreeStatus = flowThreeStatus;
    }

    public Integer getCloseBeforeStatus() {
        return closeBeforeStatus;
    }

    public void setCloseBeforeStatus(Integer closeBeforeStatus) {
        this.closeBeforeStatus = closeBeforeStatus;
    }

    public String getCloseCause() {
        return closeCause;
    }

    public void setCloseCause(String closeCause) {
        this.closeCause = closeCause;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
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

    public Integer getAppointmentRepayStoreId() {
        return appointmentRepayStoreId;
    }

    public void setAppointmentRepayStoreId(Integer appointmentRepayStoreId) {
        this.appointmentRepayStoreId = appointmentRepayStoreId;
    }

    public String getAppointmentRepayStoreName() {
        return appointmentRepayStoreName;
    }

    public void setAppointmentRepayStoreName(String appointmentRepayStoreName) {
        this.appointmentRepayStoreName = appointmentRepayStoreName;
    }
}