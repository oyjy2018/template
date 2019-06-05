package com.ydc.commom.view.vo.cgj.order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 创建预约订单VO
 */
public class CreateAppointmentParamVO implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 预约时间
     */
    private String appointTime;

    /**
     * 门店id
     */
    private Integer storeId;

    /**
     * 优惠券code
     */
    private List<String> rollCodes;
    /**
     * 优惠券状态
     */
    private Integer rollStatus;

    /**
     * 券码使用时间
     */
    private Date rollUsedTime;

    /**
     *
     * B端订单号
     */
    private String bOrderNo;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * B端订单状态
     */
    private Integer bOrderStatus;

    private String storeNo;//门店码


    private String telno;//用户手机号码

    private Integer useType; //订单类型 扫码或预约

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public List<String> getRollCodes() {
        return rollCodes;
    }

    public void setRollCodes(List<String> rollCodes) {
        this.rollCodes = rollCodes;
    }

    public Integer getRollStatus() {
        return rollStatus;
    }

    public void setRollStatus(Integer rollStatus) {
        this.rollStatus = rollStatus;
    }

    public String getbOrderNo() {
        return bOrderNo;
    }

    public void setbOrderNo(String bOrderNo) {
        this.bOrderNo = bOrderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public Date getRollUsedTime() {
        return rollUsedTime;
    }

    public void setRollUsedTime(Date rollUsedTime) {
        this.rollUsedTime = rollUsedTime;
    }

    public Integer getbOrderStatus() {
        return bOrderStatus;
    }

    public void setbOrderStatus(Integer bOrderStatus) {
        this.bOrderStatus = bOrderStatus;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
