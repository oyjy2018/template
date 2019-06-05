package com.ydc.commom.view.dto.cgj.order;

import com.ydc.model.cgj.Order;
import com.ydc.model.cgj.OrderCommodity;

import java.io.Serializable;
import java.util.List;

public class OrderAndOrderCommodityListDTO  implements Serializable {
    private Order order;

    private List<OrderCommodity> orderCommodityList;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderCommodity> getOrderCommodityList() {
        return orderCommodityList;
    }

    public void setOrderCommodityList(List<OrderCommodity> orderCommodityList) {
        this.orderCommodityList = orderCommodityList;
    }
}
