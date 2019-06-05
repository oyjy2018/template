package com.ydc.commom.view.vo.cgj.order;

import java.io.Serializable;

/**
 * 获取订单状态数量
 *
 * @author
 * @create 2018-12-13 17:20
 **/
public class OrderStatusNumVO implements Serializable {
    private static final long serialVersionUID = 2118571917767694078L;

    private Integer status;
    private Integer num;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
