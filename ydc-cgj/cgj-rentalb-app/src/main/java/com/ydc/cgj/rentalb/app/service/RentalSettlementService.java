package com.ydc.cgj.rentalb.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalPayWatercourseDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.model.cgj.rental.RentalSettlement;

/**
 * 租车结算
 *
 * @author
 * @create 2018-11-26 16:40
 **/
public interface RentalSettlementService {


    /**
     * 返回结算需要订单信息
     * @param
     * @return
     */
    String getRentalSettlement(Integer orderId);


    /**
     * 首次结算信息入库
     * @param record
     * @return
     */
    Result saveRentalSettlement(RentalSettlementDTO record);


    /**
     * 单独保存租车支付流水
     * @param rentalPayWatercourseDTO
     * @return
     */
    String saveRentalPayWatercourse(RentalPayWatercourseDTO rentalPayWatercourseDTO);

    /**
     * 结算信息查询
     * @param orderId
     * @return
     */
    RentalSettlement getRentalSettlementByOrderId(Integer orderId);
    /**
     * 调用支付宝结算
     */
    /**
     * 调用支付宝结算
     * @param orderId
     * @param type 1:租车扣除 2：违章扣除
     * @return
     */
     Result updateSettleAliPayDeposit(Integer orderId,Integer type);
}
