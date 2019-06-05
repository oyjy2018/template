package com.ydc.commom.view.dto.cgj.order;

import java.io.Serializable;

/**
 * @author gongjin
 * @create 2018-10-18 17:22
 **/
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = -6989659610209898020L;

    private Integer orderId;

    private Integer memberId;

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
}
