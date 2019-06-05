package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.commom.util.DateUtil;
import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.rental.RentalEnterpriseOrder;

import java.math.BigDecimal;
import java.util.Date;

public class AddRentalEnterpriseOrderDTO {
    private Integer id;

    @Attribute(name = "资源信息ID", required = true, isNum = true)
    private Integer resourceInfoId;

    private Integer demandSideId;

    private String demandSide;

    @Attribute(name = "资源方ID", required = true, isNum = true)
    private Integer resourceSideId;

    @Attribute(name = "资源方名称", required = true, maxLength = 15)
    private String resourceSide;

    @Attribute(name = "车等级", required = true, maxLength = 6)
    private String carLevel;

    @Attribute(name = "车品牌", required = true, maxLength = 20)
    private String carBrand;

    @Attribute(name = "车系", required = true, maxLength = 20)
    private String carSeries;

    @Attribute(name = "车型", required = true, maxLength = 50)
    private String carModel;

    @Attribute(name = "车结构", required = true, maxLength = 15)
    private String carStructure;

    @Attribute(name = "车座位数", required = true, maxLength = 15)
    private String carSeat;

    /**
     * 车型图片名称
     */
    @Attribute(name = "车型图片名称", required = true)
    private String carModelImgName;

    /**
     * 车型图片地址
     */
    @Attribute(name = "车型图片地址", required = true)
    private String carModelImgUrl;

    @Attribute(name = "保证金额度", required = true, isDigit = true,decimalLength = 2)
    private BigDecimal marginLimit;

    /**
     * 预约取车时间
     */
    @Attribute(name = "预约取车时间", required = true, dateFormat = "yyyy-MM-dd H")
    private String appointmentFetchCarTime;

    /**
     * 预约还车时间
     */
    @Attribute(name = "预约还车时间", required = true, dateFormat = "yyyy-MM-dd H")
    private String appointmentRepayCarTime;

    /**
     * 取车方式（1：到店取车；2：送车上门）
     */
    @Attribute(name = "取车方式", required = true, isNum = true)
    private Integer fetchCarMode;

    /**
     * 还车方式（1：到店还车；2：送车上门）
     */
    @Attribute(name = "还车方式", required = true, isNum = true)
    private Integer repayCarMode;

    /**
     * 车辆门店ID
     */
    @Attribute(name = "车辆门店ID", required = true, isNum = true)
    private Integer carStoreId;

    /**
     * 车辆门店
     */
    @Attribute(name = "车辆门店", required = true)
    private String carStoreName;

    /**
     * 预定数量
     */
    @Attribute(name = "预定数量", required = true, isNum = true, maxLength = 4)
    private Integer reserveCount;

    /**
     * 预约取车地址省编码
     */
    @Attribute(name = "预约取车地址省编码", required = true, isNum = true)
    private Integer appointmentFetchCarProvinceCode;

    /**
     * 预约取车地址省
     */
    @Attribute(name = "预约取车地址省", required = true)
    private String appointmentFetchCarProvince;

    /**
     * 预约取车地址市编码
     */
    @Attribute(name = "预约取车地址市编码", required = true, isNum = true)
    private Integer appointmentFetchCarCityCode;

    /**
     * 预约取车地址市
     */
    @Attribute(name = "预约取车地址市", required = true)
    private String appointmentFetchCarCity;

    /**
     * 预约取车地址区编码
     */
    @Attribute(name = "预约取车地址区编码")
    private Integer appointmentFetchCarRegionCode;

    /**
     * 预约取车地址区
     */
    @Attribute(name = "预约取车地址区")
    private String appointmentFetchCarRegion;

    /**
     * 预约取车详情地址
     */
    @Attribute(name = "预约取车详情地址", required = true, maxLength = 30)
    private String appointmentFetchCarDetailsAddress;

    /**
     * 预约还车地址省编码
     */
    @Attribute(name = "预约还车地址省编码", required = true, isNum = true)
    private Integer appointmentRepayCarProvinceCode;

    /**
     * 预约还车地址省
     */
    @Attribute(name = "预约还车地址省", required = true)
    private String appointmentRepayCarProvince;

    /**
     * 预约还车地址市编码
     */
    @Attribute(name = "预约还车地址市编码", required = true, isNum = true)
    private Integer appointmentRepayCarCityCode;

    /**
     * 预约还车地址市
     */
    @Attribute(name = "预约还车地址市", required = true)
    private String appointmentRepayCarCity;

    /**
     * 预约还车地址区编码
     */
    @Attribute(name = "预约还车地址区编码")
    private Integer appointmentRepayCarRegionCode;

    /**
     * 预约还车地址区
     */
    @Attribute(name = "预约还车地址区")
    private String appointmentRepayCarRegion;

    /**
     * 预约还车详情地址
     */
    @Attribute(name = "预约还车详情地址", required = true, maxLength = 30)
    private String appointmentRepayCarDetailsAddress;

    /**
     * 车辆租赁费
     */
    @Attribute(name = "车辆租赁费", required = true, isDigit = true, intLength = 9, decimalLength = 2)
    private BigDecimal carRentalFee;

    /**
     * 基础服务费
     */
    @Attribute(name = "基础服务费", required = true, isDigit = true, intLength = 9, decimalLength = 2)
    private BigDecimal baseServiceFee;

    /**
     * 押金
     */
    @Attribute(name = "押金", required = true, isDigit = true, intLength = 9, decimalLength = 2)
    private BigDecimal cashPledge;

    private Integer userId;

    private String userName;

    //车型id
    private Integer seriesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceInfoId() {
        return resourceInfoId;
    }

    public void setResourceInfoId(Integer resourceInfoId) {
        this.resourceInfoId = resourceInfoId;
    }

    public Integer getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(Integer demandSideId) {
        this.demandSideId = demandSideId;
    }

    public String getDemandSide() {
        return demandSide;
    }

    public void setDemandSide(String demandSide) {
        this.demandSide = demandSide;
    }

    public Integer getResourceSideId() {
        return resourceSideId;
    }

    public void setResourceSideId(Integer resourceSideId) {
        this.resourceSideId = resourceSideId;
    }

    public String getResourceSide() {
        return resourceSide;
    }

    public void setResourceSide(String resourceSide) {
        this.resourceSide = resourceSide;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }

    public String getCarModelImgName() {
        return carModelImgName;
    }

    public void setCarModelImgName(String carModelImgName) {
        this.carModelImgName = carModelImgName;
    }

    public String getCarModelImgUrl() {
        return carModelImgUrl;
    }

    public void setCarModelImgUrl(String carModelImgUrl) {
        this.carModelImgUrl = carModelImgUrl;
    }

    public BigDecimal getMarginLimit() {
        return marginLimit;
    }

    public void setMarginLimit(BigDecimal marginLimit) {
        this.marginLimit = marginLimit;
    }

    public String getAppointmentFetchCarTime() {
        return appointmentFetchCarTime;
    }

    public void setAppointmentFetchCarTime(String appointmentFetchCarTime) {
        this.appointmentFetchCarTime = appointmentFetchCarTime;
    }

    public String getAppointmentRepayCarTime() {
        return appointmentRepayCarTime;
    }

    public void setAppointmentRepayCarTime(String appointmentRepayCarTime) {
        this.appointmentRepayCarTime = appointmentRepayCarTime;
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

    public Integer getReserveCount() {
        return reserveCount;
    }

    public void setReserveCount(Integer reserveCount) {
        this.reserveCount = reserveCount;
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

    public BigDecimal getCarRentalFee() {
        return carRentalFee;
    }

    public void setCarRentalFee(BigDecimal carRentalFee) {
        this.carRentalFee = carRentalFee;
    }

    public BigDecimal getBaseServiceFee() {
        return baseServiceFee;
    }

    public void setBaseServiceFee(BigDecimal baseServiceFee) {
        this.baseServiceFee = baseServiceFee;
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public void initRentalEnterpriseOrder(RentalEnterpriseOrder reo){
        reo.setResourceInfoId(resourceInfoId);
        reo.setDemandSideId(demandSideId);
        reo.setDemandSide(demandSide);
        reo.setResourceSideId(resourceSideId);
        reo.setResourceSide(resourceSide);
        reo.setCarLevel(carLevel);
        reo.setCarBrand(carBrand);
        reo.setCarSeries(carSeries);
        reo.setCarModel(carModel);
        reo.setCarStructure(carStructure);
        reo.setCarSeat(carSeat);
        reo.setCarModelImgName(carModelImgName);
        reo.setCarModelImgUrl(carModelImgUrl);
        reo.setMarginLimit(marginLimit);
        reo.setAppointmentFetchCarTime(DateUtil.parseDate(appointmentFetchCarTime, "yyyy-MM-dd H"));
        reo.setAppointmentRepayCarTime(DateUtil.parseDate(appointmentRepayCarTime, "yyyy-MM-dd H"));
        reo.setFetchCarMode(fetchCarMode);
        reo.setRepayCarMode(repayCarMode);
        reo.setCarStoreId(carStoreId);
        reo.setCarStoreName(carStoreName);
        reo.setReserveCount(reserveCount);
        reo.setAppointmentFetchCarProvinceCode(appointmentFetchCarProvinceCode);
        reo.setAppointmentFetchCarProvince(appointmentFetchCarProvince);
        reo.setAppointmentFetchCarCityCode(appointmentFetchCarCityCode);
        reo.setAppointmentFetchCarCity(appointmentFetchCarCity);
        reo.setAppointmentFetchCarRegionCode(appointmentFetchCarRegionCode);
        reo.setAppointmentFetchCarRegion(appointmentFetchCarRegion);
        reo.setAppointmentFetchCarDetailsAddress(appointmentFetchCarDetailsAddress);
        reo.setAppointmentRepayCarProvinceCode(appointmentRepayCarProvinceCode);
        reo.setAppointmentRepayCarProvince(appointmentRepayCarProvince);
        reo.setAppointmentRepayCarCityCode(appointmentRepayCarCityCode);
        reo.setAppointmentRepayCarCity(appointmentRepayCarCity);
        reo.setAppointmentRepayCarRegionCode(appointmentRepayCarRegionCode);
        reo.setAppointmentRepayCarRegion(appointmentRepayCarRegion);
        reo.setAppointmentRepayCarDetailsAddress(appointmentRepayCarDetailsAddress);
        reo.setCarRentalFee(carRentalFee);
        reo.setBaseServiceFee(baseServiceFee);
        reo.setCashPledge(cashPledge);
    }
}
