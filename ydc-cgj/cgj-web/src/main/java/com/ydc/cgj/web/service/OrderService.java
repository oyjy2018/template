package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.dto.cgj.order.MemberWaterDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 获取用户订单列表
     *
     * @param orderReqDTO
     * @return
     */
    String getOrderSonList(OrderReqDTO orderReqDTO);

    /**
     * 获取商品订单列表
     *
     * @param orderReqDTO
     * @return
     */
    public String getOrderCommodityList(OrderReqDTO orderReqDTO);

    /**
     * 全部发货
     *
     * @return
     */
    public String allShipments(OrderReqDTO ord);

    /**
     * 部分发货
     *
     * @param ord
     * @return
     */
    public String portionShipments(OrderReqDTO ord);
    /**
     * 查询交易流水
     * @author: hejiangping
     * @date: 2019/1/15
     */
    public String getMemberSaters(MemberWaterDTO memberWaterDTO);
}
