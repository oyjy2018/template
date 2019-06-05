package com.ydc.commom.view.dto.cgj.order;

import java.io.Serializable;

/**
 * 取消订单
 *
 * @author gongjin
 * @create 2018-10-17 11:45
 **/
public class CancelOrderDTO implements Serializable {
    private static final long serialVersionUID = -4998001398256885509L;

    private Integer orderId;

    private Integer memberId;

    private String cancelReason;//取消原因


    public CancelOrderDTO(Integer orderId, Integer memberId, String cancelReason) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.cancelReason = cancelReason;
    }

    public CancelOrderDTO() {}

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
