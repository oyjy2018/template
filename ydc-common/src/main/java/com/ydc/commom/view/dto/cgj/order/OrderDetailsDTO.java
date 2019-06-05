package com.ydc.commom.view.dto.cgj.order;

import java.io.Serializable;

/**
 * H5订单详情
 *
 * @author gongjin
 * @create 2018-10-17 11:28
 **/
public class OrderDetailsDTO implements Serializable {
    private static final long serialVersionUID = -7860263331041734128L;

    private Integer memberId;

    private Integer orderId;

    public OrderDetailsDTO(){}

    public OrderDetailsDTO(Integer memberId, Integer orderId) {
        this.memberId = memberId;
        this.orderId = orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
