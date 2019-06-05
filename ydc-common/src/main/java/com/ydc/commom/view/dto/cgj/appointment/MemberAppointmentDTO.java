package com.ydc.commom.view.dto.cgj.appointment;

import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberAppointmentDTO implements Serializable {

    private static final long serialVersionUID = 1611803473805902684L;

    private Integer orderId;

    private String storeLogo;//门店logo

    private String storeName;//门店名称

    private String orderNo;//订单号

    private String items;//服务项目

    private BigDecimal appointAmount;//预约金额

    private Date appointTime;//预约时间

    private String appointTimeStr;//预约时间

    private Integer  appointStatus;//预约状态

    private Integer processStatus;//过程状态

    private String  appointStatusStr;//预约状态中文


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public BigDecimal getAppointAmount() {
        if (appointAmount==null){
            return  appointAmount;
        }
        return appointAmount.setScale(2,BigDecimal.ROUND_DOWN);
    }

    public void setAppointAmount(BigDecimal appointAmount) {
        this.appointAmount = appointAmount;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public Integer getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(Integer appointStatus) {
        this.appointStatus = appointStatus;
    }

    public String getAppointStatusStr() {
        switch (this.appointStatus){
            case 1:
                if (this.processStatus.intValue()==1){
                    this.appointStatusStr="预约取消处理中";
                }else {
                    this.appointStatusStr="预约中";
                }
                break;
            case 2:
                this.appointStatusStr="待服务";
                break;
            case 3:
                this.appointStatusStr="已完成";
                break;
            case 4:
                this.appointStatusStr="预约关闭";
                break;
             default:
                this.appointStatusStr="";

        }
        return  this.appointStatusStr;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public void setAppointStatusStr(String appointStatusStr) {
        this.appointStatusStr = appointStatusStr;
    }

    public String getAppointTimeStr() {
        return DateUtil.getdatatimef_1(this.getAppointTime());
    }

    public void setAppointTimeStr(String appointTimeStr) {
        this.appointTimeStr = appointTimeStr;
    }
}
