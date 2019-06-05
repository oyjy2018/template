package com.ydc.cgj.bridge.service;

import java.util.List;

public interface OrderService {

    /**
     * 获取订单中的用户id
     * @param payWater
     * @return
     */
    Integer getOrderMemberId(String payWater);

    /**
     * 配发券之后更新订单状态
     * @param orderList
     * @return
     */
    void updateSendRollOrderStatus(List<String> orderList);
}
