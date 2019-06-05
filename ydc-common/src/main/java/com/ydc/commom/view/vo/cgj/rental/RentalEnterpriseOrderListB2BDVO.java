package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;

import java.util.Date;
import java.util.Optional;

public class RentalEnterpriseOrderListB2BDVO {
    private Integer orderId;
    private String orderNo;
    private Integer status;
    @JSONField(serialize = false)
    private Integer flowOneStatus;
    @JSONField(serialize = false)
    private Integer flowTwoStatus;
    @JSONField(serialize = false)
    private Integer flowThreeStatus;
    private String statusCH;
    @JSONField(serialize = false)
    private Date appointmentFetchCarTime;
    @JSONField(serialize = false)
    private Date appointmentRepayCarTime;
    private String useCarTimeFrame;
    @JSONField(serialize = false)
    private String carBrand;
    @JSONField(serialize = false)
    private String carSeries;
    @JSONField(serialize = false)
    private String carStructure;
    @JSONField(serialize = false)
    private String carSeat;
    private String useCarInfo;
    @JSONField(serialize = false)
    private String carModelImgName;
    @JSONField(serialize = false)
    private String carModelImgUrl;
    private String viewCarModelImgUrl;
    private Integer carStoreId;
    private String carStoreName;
    @JSONField(serialize = false)
    private String storeRegisterProvince;
    @JSONField(serialize = false)
    private String storeRegisterCity;
    @JSONField(serialize = false)
    private String storeRegisterRegion;
    private String storeDetailsAddress;
    private Integer fetchCarMode;
    @JSONField(serialize = false)
    private String appointmentFetchCarProvince;
    @JSONField(serialize = false)
    private String appointmentFetchCarCity;
    @JSONField(serialize = false)
    private String appointmentFetchCarRegion;
    private String appointmentFetchCarDetailsAddress;
    private Integer repayCarMode;
    @JSONField(serialize = false)
    private String appointmentRepayCarProvince;
    @JSONField(serialize = false)
    private String appointmentRepayCarCity;
    @JSONField(serialize = false)
    private String appointmentRepayCarRegion;
    private String appointmentRepayCarDetailsAddress;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        Optional<RentalEnterpriseOrderEnum.OrderStatus> optional =
                RentalEnterpriseOrderEnum.OrderStatus.getOrderStatus(status,flowOneStatus,flowTwoStatus,flowThreeStatus);
        return optional.isPresent() ? optional.get().getStatus():status;
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

    public String getStatusCH() {
        Optional<RentalEnterpriseOrderEnum.OrderStatus> optional =
                RentalEnterpriseOrderEnum.OrderStatus.getOrderStatus(status,flowOneStatus,flowTwoStatus,flowThreeStatus);
        return optional.isPresent() ? optional.get().getDescribeDemand():"";
    }

    public void setStatusCH(String statusCH) {
        this.statusCH = statusCH;
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

    public String getUseCarTimeFrame() {
        return DateUtil.format(appointmentFetchCarTime,"MM月dd日HH时") + " 至 " + DateUtil.format(appointmentRepayCarTime,"MM月dd日HH时");
    }

    public void setUseCarTimeFrame(String useCarTimeFrame) {
        this.useCarTimeFrame = useCarTimeFrame;
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

    public String getUseCarInfo() {
        if(carStructure == null){
            return "";
        }else{
            this.carSeat = StringUtil.isNotEmpty(this.carSeat) ? (this.carSeat.indexOf("座") != -1 ? this.carSeat : this.carSeat+"座") : "";
            return carBrand + "" + carSeries + " " + carStructure + this.carSeat;
        }
    }

    public static void main(String[] args) {
        RentalEnterpriseOrderListB2BDVO rentalEnterpriseOrderListB2BDVO = new RentalEnterpriseOrderListB2BDVO();
        rentalEnterpriseOrderListB2BDVO.setCarStructure("两厢轿车");
        rentalEnterpriseOrderListB2BDVO.setCarBrand("宝马");
        rentalEnterpriseOrderListB2BDVO.setCarSeries("宝马1系");
        rentalEnterpriseOrderListB2BDVO.setCarSeat("4座");
        System.out.println(rentalEnterpriseOrderListB2BDVO.getUseCarInfo());
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

    public String getViewCarModelImgUrl() {
        return viewCarModelImgUrl;
    }

    public void setViewCarModelImgUrl(String viewCarModelImgUrl) {
        this.viewCarModelImgUrl = viewCarModelImgUrl;
    }

    public void setUseCarInfo(String useCarInfo) {
        this.useCarInfo = useCarInfo;
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

    public String getStoreRegisterProvince() {
        return storeRegisterProvince;
    }

    public void setStoreRegisterProvince(String storeRegisterProvince) {
        this.storeRegisterProvince = storeRegisterProvince;
    }

    public String getStoreRegisterCity() {
        return storeRegisterCity;
    }

    public void setStoreRegisterCity(String storeRegisterCity) {
        this.storeRegisterCity = storeRegisterCity;
    }

    public String getStoreRegisterRegion() {
        return storeRegisterRegion;
    }

    public void setStoreRegisterRegion(String storeRegisterRegion) {
        this.storeRegisterRegion = storeRegisterRegion;
    }

    public String getStoreDetailsAddress() {
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.ifnull(storeRegisterProvince, ""))
                .append(StringUtil.ifnull(storeRegisterCity, ""))
                .append(StringUtil.ifnull(storeRegisterRegion, ""))
                .append(StringUtil.ifnull(storeDetailsAddress, ""));
        return sb.toString();
    }

    public void setStoreDetailsAddress(String storeDetailsAddress) {
        this.storeDetailsAddress = storeDetailsAddress;
    }

    public Integer getFetchCarMode() {
        return fetchCarMode;
    }

    public void setFetchCarMode(Integer fetchCarMode) {
        this.fetchCarMode = fetchCarMode;
    }

    public String getAppointmentFetchCarProvince() {
        return appointmentFetchCarProvince;
    }

    public void setAppointmentFetchCarProvince(String appointmentFetchCarProvince) {
        this.appointmentFetchCarProvince = appointmentFetchCarProvince;
    }

    public String getAppointmentFetchCarCity() {
        return appointmentFetchCarCity;
    }

    public void setAppointmentFetchCarCity(String appointmentFetchCarCity) {
        this.appointmentFetchCarCity = appointmentFetchCarCity;
    }

    public String getAppointmentFetchCarRegion() {
        return appointmentFetchCarRegion;
    }

    public void setAppointmentFetchCarRegion(String appointmentFetchCarRegion) {
        this.appointmentFetchCarRegion = appointmentFetchCarRegion;
    }

    public String getAppointmentFetchCarDetailsAddress() {
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.ifnull(appointmentFetchCarProvince, ""))
                .append(StringUtil.ifnull(appointmentFetchCarCity, ""))
                .append(StringUtil.ifnull(appointmentFetchCarRegion, ""))
                .append(StringUtil.ifnull(appointmentFetchCarDetailsAddress, ""));
        return sb.toString();
    }

    public void setAppointmentFetchCarDetailsAddress(String appointmentFetchCarDetailsAddress) {
        this.appointmentFetchCarDetailsAddress = appointmentFetchCarDetailsAddress;
    }

    public Integer getRepayCarMode() {
        return repayCarMode;
    }

    public void setRepayCarMode(Integer repayCarMode) {
        this.repayCarMode = repayCarMode;
    }

    public String getAppointmentRepayCarProvince() {
        return appointmentRepayCarProvince;
    }

    public void setAppointmentRepayCarProvince(String appointmentRepayCarProvince) {
        this.appointmentRepayCarProvince = appointmentRepayCarProvince;
    }

    public String getAppointmentRepayCarCity() {
        return appointmentRepayCarCity;
    }

    public void setAppointmentRepayCarCity(String appointmentRepayCarCity) {
        this.appointmentRepayCarCity = appointmentRepayCarCity;
    }

    public String getAppointmentRepayCarRegion() {
        return appointmentRepayCarRegion;
    }

    public void setAppointmentRepayCarRegion(String appointmentRepayCarRegion) {
        this.appointmentRepayCarRegion = appointmentRepayCarRegion;
    }

    public String getAppointmentRepayCarDetailsAddress() {
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.ifnull(appointmentRepayCarProvince, ""))
                .append(StringUtil.ifnull(appointmentRepayCarCity, ""))
                .append(StringUtil.ifnull(appointmentRepayCarRegion, ""))
                .append(StringUtil.ifnull(appointmentRepayCarDetailsAddress, ""));
        return sb.toString();
    }

    public void setAppointmentRepayCarDetailsAddress(String appointmentRepayCarDetailsAddress) {
        this.appointmentRepayCarDetailsAddress = appointmentRepayCarDetailsAddress;
    }
}
