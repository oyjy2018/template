package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.order.BuyRollParamVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;

public interface PayService {
    Result payOrder(PayOrderParamVO payOrderParamVO, Integer memberId);

    /**
     * 购买券
     * @param buyRollParamVO
     * @param memberId
     * @return
     */
    Result buyRoll(BuyRollParamVO buyRollParamVO, Integer memberId);
}
