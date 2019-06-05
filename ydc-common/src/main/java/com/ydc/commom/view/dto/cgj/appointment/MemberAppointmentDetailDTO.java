package com.ydc.commom.view.dto.cgj.appointment;

import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class MemberAppointmentDetailDTO implements Serializable {

    private static final long serialVersionUID = 3818326432886260201L;

    private Integer orderId;

    private String storePhone;//门店电话

    private String storeLogo;//门店logo

    private String storeName;//门店名称

    private String province;//门店省

    private String city;//门店地址市

    private String region;//门店地址区

    private String detailsAddress;//详细地址

    private BigDecimal longitude;//经度

    private BigDecimal latitude;//纬度

    private String businessStartTime;//开始营业时间

    private String businessEndTime;//结束营业时间

    private List<AppointmentCouponDTO> appointmentCouponDTOList;

    private String orderNo;//订单号

    private String items;//服务项目

    private BigDecimal appointAmount;//预约总金额

    private BigDecimal discountAmount;//优惠金额

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public String getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(String businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public String getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(String businessEndTime) {
        this.businessEndTime = businessEndTime;
    }

    public List<AppointmentCouponDTO> getAppointmentCouponDTOList() {
        return appointmentCouponDTOList;
    }

    public void setAppointmentCouponDTOList(List<AppointmentCouponDTO> appointmentCouponDTOList) {
        this.appointmentCouponDTOList = appointmentCouponDTOList;
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

    public BigDecimal getDiscountAmount()
    {

        return this.appointAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
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

    public void setAppointStatusStr(String appointStatusStr) {
        this.appointStatusStr = appointStatusStr;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getAppointTimeStr() {
        return DateUtil.getdatatimef_1(this.getAppointTime());
    }

    public void setAppointTimeStr(String appointTimeStr) {
        this.appointTimeStr = appointTimeStr;
    }


}
