package com.ydc.commom.view.dto.cgj.order;


import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * H5我们的订单
 *
 * @author gongjin
 * @create 2018-10-17 10:56
 **/
public class MyOrderDTO extends Pagination implements Serializable {

    private static final long serialVersionUID = -6386027623951544721L;


    private Integer status;//订单状态

    private Integer memberId;

    public MyOrderDTO(){}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
