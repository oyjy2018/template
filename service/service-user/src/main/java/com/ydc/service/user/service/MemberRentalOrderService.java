package com.ydc.service.user.service;

import com.ydc.model.cgj.rental.RentalOrder;

public interface MemberRentalOrderService {

    /**
     * 更新订单状态
     * @param rentalOrder
     * @param oldStatus
     * @return
     */
    Integer updateOrderOneStatus(RentalOrder rentalOrder, Integer... oldStatus);
}
