package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.commom.util.DateUtil;
import com.ydc.model.cgj.Pagination;

import java.io.Serializable;
import java.util.Set;

public class RentalEnterpriseOrderDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 8778714638966444262L;

    private String queryContent;//租车B端搜索内容

    private Integer orderId;

    private Integer userId;

    private String userName;

    private Integer demandSideId;

    private Integer resourceSideId;

    private String demandSide;//需求方名称

    private String resourceSide;//资源方名称

    private String carBrand;//车品牌
    private String carSeries;//车系
    private String carModel;//车型
    private String startAppointmentFetchCarTime;//预约取车时间-开始
    private String endAppointmentFetchCarTime;//预约取车时间-结束
    private String startAppointmentRepayCarTime;//预约还车时间-开始
    private String endAppointmentRepayCarTime;//预约还车时间-结束
    private String startComeCarTime;//实际取车时间-开始
    private String endComeCarTime;//实际取车时间-结束
    private String startRepayCarTime;//实际还车时间-开始
    private String endRepayCarTime;//实际还车时间-结束
    private Integer status;//接收前端参数：订单状态
    private Integer realStatus;
    private Set<Integer> flowOneStatus;
    private Set<Integer> flowTwoStatus;
    private Set<Integer> flowThreeStatus;
    private String viewOrgId;

    public String getQueryContent() {
        return queryContent;
    }

    public void setQueryContent(String queryContent) {
        this.queryContent = queryContent;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(Integer demandSideId) {
        this.demandSideId = demandSideId;
    }

    public Integer getResourceSideId() {
        return resourceSideId;
    }

    public void setResourceSideId(Integer resourceSideId) {
        this.resourceSideId = resourceSideId;
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

    public String getStartAppointmentFetchCarTime() {
        return DateUtil.jointMinSuffix(this.startAppointmentFetchCarTime);
    }

    public void setStartAppointmentFetchCarTime(String startAppointmentFetchCarTime) {
        this.startAppointmentFetchCarTime = startAppointmentFetchCarTime;
    }

    public String getEndAppointmentFetchCarTime() {
        return DateUtil.jointMaxSuffix(this.endAppointmentFetchCarTime);
    }

    public void setEndAppointmentFetchCarTime(String endAppointmentFetchCarTime) {
        this.endAppointmentFetchCarTime = endAppointmentFetchCarTime;
    }

    public String getStartAppointmentRepayCarTime() {
        return DateUtil.jointMinSuffix(this.startAppointmentRepayCarTime);
    }

    public void setStartAppointmentRepayCarTime(String startAppointmentRepayCarTime) {
        this.startAppointmentRepayCarTime = startAppointmentRepayCarTime;
    }

    public String getEndAppointmentRepayCarTime() {
        return DateUtil.jointMaxSuffix(this.endAppointmentRepayCarTime);
    }

    public void setEndAppointmentRepayCarTime(String endAppointmentRepayCarTime) {
        this.endAppointmentRepayCarTime = endAppointmentRepayCarTime;
    }

    public String getStartComeCarTime() {
        return DateUtil.jointMinSuffix(this.startComeCarTime);
    }

    public void setStartComeCarTime(String startComeCarTime) {
        this.startComeCarTime = startComeCarTime;
    }

    public String getEndComeCarTime() {
        return DateUtil.jointMaxSuffix(this.endComeCarTime);
    }

    public void setEndComeCarTime(String endComeCarTime) {
        this.endComeCarTime = endComeCarTime;
    }

    public String getStartRepayCarTime() {
        return DateUtil.jointMinSuffix(this.startRepayCarTime);
    }

    public void setStartRepayCarTime(String startRepayCarTime) {
        this.startRepayCarTime = startRepayCarTime;
    }

    public String getEndRepayCarTime() {
        return DateUtil.jointMaxSuffix(this.endRepayCarTime);
    }

    public void setEndRepayCarTime(String endRepayCarTime) {
        this.endRepayCarTime = endRepayCarTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Integer> getFlowOneStatus() {
        return flowOneStatus;
    }

    public void setFlowOneStatus(Set<Integer> flowOneStatus) {
        this.flowOneStatus = flowOneStatus;
    }

    public Set<Integer> getFlowTwoStatus() {
        return flowTwoStatus;
    }

    public void setFlowTwoStatus(Set<Integer> flowTwoStatus) {
        this.flowTwoStatus = flowTwoStatus;
    }

    public Set<Integer> getFlowThreeStatus() {
        return flowThreeStatus;
    }

    public void setFlowThreeStatus(Set<Integer> flowThreeStatus) {
        this.flowThreeStatus = flowThreeStatus;
    }

    public Integer getRealStatus() {
        return realStatus;
    }

    public void setRealStatus(Integer realStatus) {
        this.realStatus = realStatus;
    }

    public RentalEnterpriseOrderDTO(){

    }

    public RentalEnterpriseOrderDTO(Integer orderId){
        this.orderId = orderId;
    }

    public String getViewOrgId() {
        return viewOrgId;
    }

    public void setViewOrgId(String viewOrgId) {
        this.viewOrgId = viewOrgId;
    }
}
