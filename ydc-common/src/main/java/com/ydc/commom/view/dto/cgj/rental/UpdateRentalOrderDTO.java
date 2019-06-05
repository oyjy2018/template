package com.ydc.commom.view.dto.cgj.rental;


import java.io.Serializable;

/**
 * 更新租车订单状态
 *
 * @author
 * @create 2018-11-24 15:44
 **/
public class UpdateRentalOrderDTO implements Serializable {
    private static final long serialVersionUID = 2817391993936093076L;

    private Integer orderId;//订单id
    private Integer status;//状态
    private Integer userId;//操作人
    private Integer closeBeforeStatus;//关闭之前的状态
    private String closeCause;//关闭原因
    private String closeTime;//关闭时间
    private Integer flowOneStatus;//流程状态一

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCloseBeforeStatus() {
        return closeBeforeStatus;
    }

    public void setCloseBeforeStatus(Integer closeBeforeStatus) {
        this.closeBeforeStatus = closeBeforeStatus;
    }

    public String getCloseCause() {
        return closeCause;
    }

    public void setCloseCause(String closeCause) {
        this.closeCause = closeCause;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getFlowOneStatus() {
        return flowOneStatus;
    }

    public void setFlowOneStatus(Integer flowOneStatus) {
        this.flowOneStatus = flowOneStatus;
    }
}
