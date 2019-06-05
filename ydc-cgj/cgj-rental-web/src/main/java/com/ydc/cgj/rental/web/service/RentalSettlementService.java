package com.ydc.cgj.rental.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalPayWatercourseDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.model.cgj.rental.RentalSettlement;

/**
 * @author
 * @create 2018-11-28 16:29
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
     * @param rentalSettlementDTO
     * @return
     */
    Result saveRentalSettlement(RentalSettlementDTO rentalSettlementDTO);


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
     * 更新支付宝结算参数
     */
    Result updateAliPaySettleDTO(Integer orderId,Integer type);
}
