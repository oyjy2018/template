package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.IsEmpty;
import com.ydc.model.annotation.MaxSize;
import com.ydc.model.annotation.MinSize;
import com.ydc.model.cgj.rental.RentalDeposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 后台新增订单信息
 *
 * @author
 * @create 2018-11-22 11:49
 **/
public class AddRentalOrderPCDTO implements Serializable {
    private static final long serialVersionUID = 2674068404500075367L;


    private Integer id; //新增数据返回主键id

    /**
     * t_rental_order.order_no (订单号)
     * @ibatorgenerated 2018-11-23 11:42:42
     */
    private String orderNo;

    /**
     * t_rental_order.member_id (会员id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer memberId;

    /**
     * t_rental_order.car_level (车等级)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "车等级不能为空")
    private String carLevel;

    /**
     * t_rental_order.brand (车辆品牌)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "车辆品牌不能为空")
    private String brand;

    /**
     * t_rental_order.series (车系)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "车系不能为空")
    private String series;

    /**
     * t_rental_order.model (车型)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "车型不能为空")
    private String model;

    /**
     * t_rental_order.mobile_phone (手机号)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "手机号不能为空")
    @MinSize(minLen = 11,message = "手机号格式不正确")
    @MaxSize(max = 12,message = "手机号格式不正确")
    private String mobilePhone;

    /**
     * t_rental_order.name (姓名)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "姓名不能为空")
    @MaxSize(max = 15,message = "姓名长度太长")
    private String name;

    /**
     * t_rental_order.id_card (身份证)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "身份证不能为空")
    @MaxSize(max = 18 , message = "身份证长度太长")
    private String idCard;

    /**
     * t_rental_order.fetch_car_mode (取车方式（1：到店取车；2：送车上门）)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "取车方式不能为空")
    private Integer fetchCarMode;

    /**
     * t_rental_order.repay_car_mode (还车方式（1：到店还车；2：送车上门）)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "还车方式不能为空")
    private Integer repayCarMode;

    /**
     * t_rental_order.appointment_fetch_car_time (预约取车时间)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约取车时间不能为空")
    private Date appointmentFetchCarTime;

    /**
     * t_rental_order.appointment_repay_car_time (预约还车时间)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约还车时间不能为空")
    private Date appointmentRepayCarTime;

    /**
     * t_rental_order.appointment_store_id (预约取车门店id)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String appointmentStoreId;

    /**
     * t_rental_order.appointment_store_name (预约取车门店名)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String appointmentStoreName;

    /**
     *预约还车门店id
     */
    private Integer appointmentRepayStoreId;

    /**
     * 预约还车门店名
     */
    private String appointmentRepayStoreName;

    /**
     * t_rental_order.appointment_fetch_car_province_code (预约取车地址省编码)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约取车地址省编码不能为空")
    private Integer appointmentFetchCarProvinceCode;

    /**
     * t_rental_order.appointment_fetch_car_province (预约取车地址省)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约取车地址省不能为空")
    private String appointmentFetchCarProvince;

    /**
     * t_rental_order.appointment_fetch_car_city_code (预约取车地址市编码)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约取车地址市编码不能为空")
    private Integer appointmentFetchCarCityCode;

    /**
     * t_rental_order.appointment_fetch_car_city (预约取车地址市)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约取车地址市不能为空")
    private String appointmentFetchCarCity;

    /**
     * t_rental_order.appointment_fetch_car_region_code (预约取车地址区编码)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer appointmentFetchCarRegionCode;

    /**
     * t_rental_order.appointment_fetch_car_region (预约取车地址区)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String appointmentFetchCarRegion;

    /**
     * t_rental_order.appointment_fetch_car_details_address (预约取车详情地址)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约取车详情地址不能为空")
    @MaxSize(max = 50,message = "预约取车详情地址长度太长")
    private String appointmentFetchCarDetailsAddress;

    /**
     * t_rental_order.appointment_repay_car_province_code (预约还车地址省编码)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约还车地址省编码不能为空")
    private Integer appointmentRepayCarProvinceCode;

    /**
     * t_rental_order.appointment_repay_car_province (预约还车地址省)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约还车地址省不能为空")
    private String appointmentRepayCarProvince;

    /**
     * t_rental_order.appointment_repay_car_city_code (预约还车地址市编码)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约还车地址市编码不能为空")
    private Integer appointmentRepayCarCityCode;

    /**
     * t_rental_order.appointment_repay_car_city (预约还车地址市)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约还车地址市不能为空")
    private String appointmentRepayCarCity;

    /**
     * t_rental_order.appointment_repay_car_region_code (预约还车地址区编码)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer appointmentRepayCarRegionCode;

    /**
     * t_rental_order.appointment_repay_car_region (预约还车地址区)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private String appointmentRepayCarRegion;

    /**
     * t_rental_order.appointment_repay_car_details_address (预约还车详情地址)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    @IsEmpty(message = "预约还车详情地址不能为空")
    @MaxSize(max = 50,message = "预约取车详情地址长度太长")
    private String appointmentRepayCarDetailsAddress;

    /**
     * t_rental_order.rental_accredit_money (租车预授权金额)
     * @ibatorgenerated 2018-11-23 10:59:07
     */
    private BigDecimal rentalAccreditMoney;

    /**
     * t_rental_order.status (状态（1：未确认-待风控；2：已确认-待风控；3：风控通过-待租车预授权；4：已租车预授权-待出车；5：出车成功-待还车；6：已还车-待结算；7：已还车-已结算；98：已取消；99：已拒绝）)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer status;

    /**
     * t_rental_order.create_time (创建时间)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Date createTime;

    /**
     * t_rental_order.create_by (创建人)
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    private Integer createBy;

    /**
     * 关闭原因
     */
    private String closeCause;

    /**
     * 关闭时间
     */
    private Date closeTime;

    /**
     * 关闭之前状态
     */
    private Integer closeBeforeStatus;

    /**
     * 流程状态一（100等于成功；0等于初始状态）
     */
    private Integer flowOneStatus;

    /**
     * 租赁押金
     */
    private RentalDeposit rentalDeposit;

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

    public Integer getFetchCarMode() {
        return fetchCarMode;
    }

    public void setFetchCarMode(Integer fetchCarMode) {
        this.fetchCarMode = fetchCarMode;
    }

    public Integer getRepayCarMode() {
        return repayCarMode;
    }

    public void setRepayCarMode(Integer repayCarMode) {
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

    public String getAppointmentStoreId() {
        return appointmentStoreId;
    }

    public void setAppointmentStoreId(String appointmentStoreId) {
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

    public BigDecimal getRentalAccreditMoney() {
        return rentalAccreditMoney;
    }

    public void setRentalAccreditMoney(BigDecimal rentalAccreditMoney) {
        this.rentalAccreditMoney = rentalAccreditMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getCloseBeforeStatus() {
        return closeBeforeStatus;
    }

    public void setCloseBeforeStatus(Integer closeBeforeStatus) {
        this.closeBeforeStatus = closeBeforeStatus;
    }

    public Integer getFlowOneStatus() {
        return flowOneStatus;
    }

    public void setFlowOneStatus(Integer flowOneStatus) {
        this.flowOneStatus = flowOneStatus;
    }

    public RentalDeposit getRentalDeposit() {
        return rentalDeposit;
    }

    public void setRentalDeposit(RentalDeposit rentalDeposit) {
        this.rentalDeposit = rentalDeposit;
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
