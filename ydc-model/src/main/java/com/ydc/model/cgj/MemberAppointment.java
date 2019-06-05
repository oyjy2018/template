package com.ydc.model.cgj;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberAppointment implements Serializable {
    private static final long serialVersionUID = -1820589638651894655L;
    /**
     * t_appointment.id (主键)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer id;

    /**
     * t_appointment.member_id (用户id)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer memberId;

    /**
     * t_appointment.member_name (用户姓名)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String memberName;

    /**
     * t_appointment.items (预约项目)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String items;

    /**
     * t_appointment.appoint_amount (预约金额)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private BigDecimal appointAmount;

    /**
     * t_appointment.actual_amount (实际金额)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private BigDecimal actualAmount;

    /**
     * t_appointment.type (类型（1：预约订单；2：扫码订单）)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer type;

    /**
     * t_appointment.water (订单流水)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String orderNo;

    /**
     * t_appointment.appoint_status (预约状态（1：预约中；2：待服务；3：已完成；4：预约关闭）)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer appointStatus;

    /**
     * 过程状态 0：过程结束，1过程中
     */
    private Integer processStatus;

    /**
     * 预约关闭原因
     */
    private String reason;

    /**
     * t_appointment.plate_numbers (车牌号)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String plateNumbers;

    /**
     * t_appointment.store_id (门店id)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer storeId;

    /**
     * 门店code
     */
    private String storeCode;

    /**
     * t_appointment.store_name (门店名称)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String storeName;

    /**
     * t_appointment.store_address (门店详情)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String storeAddress;

    /**
     * t_appointment.store_phone (门店电话)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String storePhone;

    /**
     * t_appointment.appoint_time (预约时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date appointTime;

    /**
     * t_appointment.confirm_time (确认时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date confirmTime;

    /**
     * t_appointment.service_time (服务时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date serviceTime;

    /**
     * 关闭时间
     */
    private Date closeTime;

    /**
     * t_appointment.b_water (b端订单流水)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private String bOrderNo;

    /**
     * t_appointment.b_appoint_status (b端预约状态（1：预约中；2：待服务；3：已完成；4：预约关闭）)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer bAppointStatus;

    /**
     * b端预约关闭原因
     */
    private String bReason;

    /**
     * t_appointment.status (状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer status;

    /**
     * t_appointment.create_time (创建时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date createTime;

    /**
     * t_appointment.create_by (创建人)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer createBy;

    /**
     * t_appointment.update_time (更新时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date updateTime;

    /**
     * t_appointment.update_by (更新人)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public BigDecimal getAppointAmount() {
        return appointAmount;
    }

    public void setAppointAmount(BigDecimal appointAmount) {
        this.appointAmount = appointAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(Integer appointStatus) {
        this.appointStatus = appointStatus;
    }

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getbOrderNo() {
        return bOrderNo;
    }

    public void setbOrderNo(String bOrderNo) {
        this.bOrderNo = bOrderNo;
    }

    public Integer getbAppointStatus() {
        return bAppointStatus;
    }

    public void setbAppointStatus(Integer bAppointStatus) {
        this.bAppointStatus = bAppointStatus;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getbReason() {
        return bReason;
    }

    public void setbReason(String bReason) {
        this.bReason = bReason;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}