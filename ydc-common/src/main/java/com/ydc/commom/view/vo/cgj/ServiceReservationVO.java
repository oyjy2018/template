package com.ydc.commom.view.vo.cgj;

import com.ydc.commom.enums.cgj.MemberAppointmentEnum;
import com.ydc.commom.enums.common.CommonEnum;

import java.io.Serializable;

/**
 * 服务预约
 *
 * @author
 * @create 2018-12-14 17:07
 **/
public class ServiceReservationVO implements Serializable {
    private static final long serialVersionUID = 8168777886430109662L;

    private Integer memberAppointmentId;
    private Integer memberId;
    private String memberName;
    private String mobilePhone;
    private String items;
    private String appointAmount;
    private String actualAmount;
    private String orderNo;
    private Integer type;
    private String typeName;
    private String plateNumbers;
    private String storeName;
    private String storeAddress;
    private String storePhone;
    private String appointTime;
    private String confirmTime;
    private String serviceTime;
    private Integer bAppointStatus;
    private String bAppointStatusName;
    private Integer status;
    private String statusName;

    public Integer getMemberAppointmentId() {
        return memberAppointmentId;
    }

    public void setMemberAppointmentId(Integer memberAppointmentId) {
        this.memberAppointmentId = memberAppointmentId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getAppointAmount() {
        return appointAmount;
    }

    public void setAppointAmount(String appointAmount) {
        this.appointAmount = appointAmount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return MemberAppointmentEnum.AppointmentType.getCodeName(this.type);
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Integer getbAppointStatus() {
        return bAppointStatus;
    }

    public void setbAppointStatus(Integer bAppointStatus) {
        this.bAppointStatus = bAppointStatus;
    }

    public String getbAppointStatusName() {
        return MemberAppointmentEnum.AppointmentBAppointStatus.getCodeName(this.bAppointStatus);
    }

    public void setbAppointStatusName(String bAppointStatusName) {
        this.bAppointStatusName = bAppointStatusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return CommonEnum.ValidEnum.getCodeName(this.status);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}


