package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.util.DateUtil;

import java.util.Date;

public class RentalOrderListBVO {
    private Integer rentalOrderId;
    private String name;
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
    private String brand;
    @JSONField(serialize = false)
    private String carStructure;
    @JSONField(serialize = false)
    private String gearBox;
    @JSONField(serialize = false)
    private String carSeat;
    private String useCarInfo;

    public Integer getRentalOrderId() {
        return rentalOrderId;
    }

    public void setRentalOrderId(Integer rentalOrderId) {
        this.rentalOrderId = rentalOrderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        RentalOrderEnum.StatusStoreEnum enumObj =
                RentalOrderEnum.StatusStoreEnum.getStatusStoreEnum(this.status, this.flowOneStatus, this.flowTwoStatus, this.flowThreeStatus);
        if(enumObj == null){
            return status;
        }else{
            return enumObj.getStatus();
        }
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
        RentalOrderEnum.StatusStoreEnum enumObj =
                RentalOrderEnum.StatusStoreEnum.getStatusStoreEnum(this.status, this.flowOneStatus, this.flowTwoStatus, this.flowThreeStatus);
        if(enumObj == null){
            return "";
        }else{
            return enumObj.getDescribe();
        }
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }

    public String getUseCarInfo() {
        if(this.carStructure == null){
            return "";
        }else{
            return this.brand + " " + this.carStructure + "" + this.gearBox + " 乘坐" + this.carSeat + "人";
        }
    }

    public void setUseCarInfo(String useCarInfo) {
        this.useCarInfo = useCarInfo;
    }
}
