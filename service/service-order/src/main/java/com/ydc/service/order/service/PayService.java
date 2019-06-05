package com.ydc.service.order.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.order.BuyRollParamVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import com.ydc.model.cgj.Order;

import java.util.List;

public interface PayService {

    /**
     * 支付订单
     * @param payOrderParamVO
     * @param memberId
     * @return
     * @throws Exception
     */
    Result updateOrderPay(PayOrderParamVO payOrderParamVO, Integer memberId, List<Order> orderList) throws Exception;

    /**
     * 购买券
     * @param buyRollParamVO
     * @param memberId
     * @return
     * @throws Exception
     */
    default Result buyRoll(BuyRollParamVO buyRollParamVO, Integer memberId) throws Exception{
        return null;
    }
}
