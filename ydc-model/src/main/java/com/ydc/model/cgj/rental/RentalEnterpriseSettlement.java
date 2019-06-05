package com.ydc.model.cgj.rental;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * t_rental_enterprise_settlement
 * @author 
 */
public class RentalEnterpriseSettlement implements Serializable {
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 需求方ID
     */
    private Integer demandSideId;

    /**
     * 结算金额
     */
    private BigDecimal settleMoney;

    /**
     * 结算方式（1：押金；2：信用卡；3：转账）
     */
    private Integer settleWay;

    private String settleWayCH;

    /**
     * 结算时间
     */
    private Date settleDate;

    /**
     * 结算凭证文件名
     */
    private String voucherImgName;

    /**
     * 结算凭证文件地址
     */
    private String voucherImgUrl;

    private String voucherImgBrowse;

    /**
     * 结算状态（1：已结清）
     */
    private Integer settleStatus;

    /**
     * 结算人员ID
     */
    private Integer settleUserId;

    /**
     * 结算人员姓名
     */
    private String settleUserName;

    private Date createTime;

    private Integer createBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(Integer demandSideId) {
        this.demandSideId = demandSideId;
    }

    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }

    public Integer getSettleWay() {
        return settleWay;
    }

    public void setSettleWay(Integer settleWay) {
        this.settleWay = settleWay;
    }


    @JSONField(format = DateUtil.DATAFORMAT_STR)
    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getVoucherImgName() {
        return voucherImgName;
    }

    public void setVoucherImgName(String voucherImgName) {
        this.voucherImgName = voucherImgName;
    }

    public String getVoucherImgUrl() {
        return voucherImgUrl;
    }

    public void setVoucherImgUrl(String voucherImgUrl) {
        this.voucherImgUrl = voucherImgUrl;
    }

    public Integer getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
    }

    public Integer getSettleUserId() {
        return settleUserId;
    }

    public void setSettleUserId(Integer settleUserId) {
        this.settleUserId = settleUserId;
    }

    public String getSettleUserName() {
        return settleUserName;
    }

    public void setSettleUserName(String settleUserName) {
        this.settleUserName = settleUserName;
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

    public String getVoucherImgBrowse() {
        return voucherImgBrowse;
    }

    public void setVoucherImgBrowse(String voucherImgBrowse) {
        this.voucherImgBrowse = voucherImgBrowse;
    }

    public String getSettleWayCH() {
        return settleWayCH;
    }

    public void setSettleWayCH(String settleWayCH) {
        this.settleWayCH = settleWayCH;
    }
}