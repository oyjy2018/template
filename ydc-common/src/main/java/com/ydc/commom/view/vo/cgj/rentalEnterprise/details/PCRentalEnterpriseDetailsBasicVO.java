package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情：基本信息
 *
 * @author
 * @create 2019-01-05 11:31
 **/
public class PCRentalEnterpriseDetailsBasicVO implements Serializable {
    private static final long serialVersionUID = -5626032544482637698L;

    private Integer resourceInfoId;//资源信息记录ID
    private Integer orderId;//订单id
    private String orderNo;//订单编号
    private Integer status;//订单状态
    private String statusCH;//订单名
    private Integer flowOneStatus;
    private Integer flowTwoStatus;
    private Integer flowThreeStatus;
    private String carLevel;//车牌级别
    private String carBrand;//车品牌
    private String carSeries;//车系
    private String carModel;//车型
    private String carStructure;//车结构
    private String carSeat;//车座位数
    private Integer reserveCount;//预约数量
    private String demandSide;//需求方
    private Integer demandSideId;//需求方id
    private String resourceSide;//资源方

    private Integer fetchCarMode;//取车方式（1：到店取车；2：送车上门）
    private String fetchCarModeName;

    private Integer repayCarMode;//还车方式（1：到店还车；2：送车上门）
    private String repayCarModeName;

    private Date appointmentFetchCarTime;//预约取车时间
    private Date appointmentRepayCarTime;//预约还车时间
    private String carStoreName;//车辆门店名称
    private String carStoreAddress;//门店地址

    private String appointmentFetchCarProvince;//预约取车地址省
    private String appointmentFetchCarCity;//预约取车地址市
    private String appointmentFetchCarRegion;//预约取车地址区
    private String appointmentFetchCarDetailsAddress;//预约取车详情地址

    private String appointmentRepayCarProvince;//预约还车地址省
    private String appointmentRepayCarCity;//预约还车地址市
    private String appointmentRepayCarRegion;//预约还车地址区
    private String appointmentRepayCarDetailsAddress;//预约还车详情地址

    private BigDecimal carRentalFee;//车辆租赁费
    private String carRentalFeeFormat;
    private BigDecimal baseServiceFee;//基础服务费
    private String baseServiceFeeFormat;
    private BigDecimal cashPledge;//押金
    private String cashPledgeFormat;
    private BigDecimal totalFee;//总额

    private Date comeCarTime;//出车时间
    private String comeCarPersonName;//出车操作人
    private Date repayCarTime;//还车时间
    private String repayCarPersonName;//还车操作人

    public Integer getResourceInfoId() {
        return resourceInfoId;
    }

    public void setResourceInfoId(Integer resourceInfoId) {
        this.resourceInfoId = resourceInfoId;
    }

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
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusCH() {
        return RentalEnterpriseOrderEnum.OrderStatus.getDescribeStore(this.status,this.flowOneStatus,this.flowTwoStatus,this.flowThreeStatus);
    }

    public void setStatusCH(String statusCH) {
        this.statusCH = statusCH;
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

    public Integer getReserveCount() {
        return reserveCount;
    }

    public void setReserveCount(Integer reserveCount) {
        this.reserveCount = reserveCount;
    }

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }

    public String getDemandSide() {
        return demandSide;
    }

    public void setDemandSide(String demandSide) {
        this.demandSide = demandSide;
    }

    public String getResourceSide() {
        return resourceSide;
    }

    public void setResourceSide(String resourceSide) {
        this.resourceSide = resourceSide;
    }

    public Integer getFetchCarMode() {
        return fetchCarMode;
    }

    public void setFetchCarMode(Integer fetchCarMode) {
        this.fetchCarMode = fetchCarMode;
    }

    public String getFetchCarModeName() {
        return RentalOrderEnum.FetchCarMode.getFetchCarModeByStatus(this.fetchCarMode).getStatusCH();
    }

    public void setFetchCarModeName(String fetchCarModeName) {
        this.fetchCarModeName = fetchCarModeName;
    }

    public Integer getRepayCarMode() {
        return repayCarMode;
    }

    public void setRepayCarMode(Integer repayCarMode) {
        this.repayCarMode = repayCarMode;
    }

    public String getRepayCarModeName() {
        return RentalOrderEnum.RepayCarMode.getRepayCarModeByStatus(this.repayCarMode).getStatusCH();
    }

    public void setRepayCarModeName(String repayCarModeName) {
        this.repayCarModeName = repayCarModeName;
    }

    @JSONField(format = DateUtil.DATAFORMAT_STR+" HH时")
    public Date getAppointmentFetchCarTime() {
        return appointmentFetchCarTime;
    }

    public void setAppointmentFetchCarTime(Date appointmentFetchCarTime) {
        this.appointmentFetchCarTime = appointmentFetchCarTime;
    }
    @JSONField(format = DateUtil.DATAFORMAT_STR+" HH时")
    public Date getAppointmentRepayCarTime() {
        return appointmentRepayCarTime;
    }

    public void setAppointmentRepayCarTime(Date appointmentRepayCarTime) {
        this.appointmentRepayCarTime = appointmentRepayCarTime;
    }

    public String getCarStoreName() {
        return carStoreName;
    }

    public void setCarStoreName(String carStoreName) {
        this.carStoreName = carStoreName;
    }

    public String getCarStoreAddress() {
        return carStoreAddress;
    }

    public void setCarStoreAddress(String carStoreAddress) {
        this.carStoreAddress = carStoreAddress;
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
        return appointmentFetchCarDetailsAddress;
    }

    public void setAppointmentFetchCarDetailsAddress(String appointmentFetchCarDetailsAddress) {
        this.appointmentFetchCarDetailsAddress = appointmentFetchCarDetailsAddress;
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
        return appointmentRepayCarDetailsAddress;
    }

    public void setAppointmentRepayCarDetailsAddress(String appointmentRepayCarDetailsAddress) {
        this.appointmentRepayCarDetailsAddress = appointmentRepayCarDetailsAddress;
    }

    @JSONField(format = DateUtil.DATATIMEF_STR)
    public Date getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(Date comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public String getComeCarPersonName() {
        return comeCarPersonName;
    }

    public void setComeCarPersonName(String comeCarPersonName) {
        this.comeCarPersonName = comeCarPersonName;
    }

    @JSONField(format = DateUtil.DATATIMEF_STR)
    public Date getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(Date repayCarTime) {
        this.repayCarTime = repayCarTime;
    }

    public String getRepayCarPersonName() {
        return repayCarPersonName;
    }

    public void setRepayCarPersonName(String repayCarPersonName) {
        this.repayCarPersonName = repayCarPersonName;
    }

    public Integer getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(Integer demandSideId) {
        this.demandSideId = demandSideId;
    }

    public String getCarRentalFeeFormat() {
        return carRentalFeeFormat;
    }

    public void setCarRentalFeeFormat(String carRentalFeeFormat) {
        this.carRentalFeeFormat = carRentalFeeFormat;
    }

    public String getBaseServiceFeeFormat() {
        return baseServiceFeeFormat;
    }

    public void setBaseServiceFeeFormat(String baseServiceFeeFormat) {
        this.baseServiceFeeFormat = baseServiceFeeFormat;
    }

    public String getCashPledgeFormat() {
        return cashPledgeFormat;
    }

    public void setCashPledgeFormat(String cashPledgeFormat) {
        this.cashPledgeFormat = cashPledgeFormat;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }
}
