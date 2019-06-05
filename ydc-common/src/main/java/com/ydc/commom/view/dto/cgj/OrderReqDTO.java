package com.ydc.commom.view.dto.cgj;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public class OrderReqDTO implements Serializable {

    private static final long serialVersionUID = 3236985917839411413L;
    private String orderId;//商家订单ID

    private String orderCommodityIds;

    private String logisticsCompany;//物流公司

    private String logisticsOrder;//物流订单号

    private String updateBy;

    private String userId;//当前用户ID

    private String userName;//当前用户名称

    private String orderNo;//用户订单号

    private String startCreateTime;//下单时间-起

    private String endCreateTime;//下单时间-止

    private String supplierCode;//供应商code

    private Integer orderStatus;//订单状态

    private Integer page;
    private Integer rows;

    private String payWater;//支付流水

    private String mobilePhone;//手机号

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public OrderReqDTO() {
    }


    public OrderReqDTO(String orderId, String orderCommodityIds, String logisticsCompany,
                       String logisticsOrder, String updateBy, String userId, String userName,
                       String orderNo, String startCreateTime, String endCreateTime,
                       String supplierCode, Integer orderStatus, Integer page, Integer rows,String payWater,
                       String mobilePhone) {
        this.orderId = orderId;
        this.orderCommodityIds = orderCommodityIds;
        this.logisticsCompany = logisticsCompany;
        this.logisticsOrder = logisticsOrder;
        this.updateBy = updateBy;
        this.userId = userId;
        this.userName = userName;
        this.orderNo = orderNo;
        this.startCreateTime = startCreateTime;
        this.endCreateTime = endCreateTime;
        this.supplierCode = supplierCode;
        this.orderStatus = orderStatus;
        this.page = page;
        this.rows = rows;
        this.payWater = payWater;
        this.mobilePhone = mobilePhone;
    }

    public OrderReqDTO changeDTO(){
        return new OrderReqDTO(this.orderId,this.orderCommodityIds,this.logisticsCompany,this.logisticsOrder,this.updateBy,this.userId,this.userName,
                this.orderNo,(StringUtils.isEmpty(this.startCreateTime) ? null : this.startCreateTime+" 00:00:00"),
                (StringUtils.isEmpty(this.endCreateTime) ? null : this.endCreateTime +" 23:59:59"),this.supplierCode,this.orderStatus,
                (StringUtils.isEmpty(this.page) ? null : ((this.page - 1) * this.rows)),this.rows,this.payWater,this.mobilePhone);
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCommodityIds() {
        return orderCommodityIds;
    }

    public void setOrderCommodityIds(String orderCommodityIds) {
        this.orderCommodityIds = orderCommodityIds;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsOrder() {
        return logisticsOrder;
    }

    public void setLogisticsOrder(String logisticsOrder) {
        this.logisticsOrder = logisticsOrder;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater;
    }

}
