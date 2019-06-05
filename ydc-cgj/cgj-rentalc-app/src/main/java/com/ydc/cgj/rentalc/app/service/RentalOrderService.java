package com.ydc.cgj.rentalc.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import com.ydc.model.cgj.rental.RentalOrder;
import java.util.Map;

/**
 * @author
 * @create 2018-11-22 13:00
 **/
public interface RentalOrderService {

    /**
     * 根据会员ID查询订单列表(C端)
     * @param req
     * @return
     */
    String getRentalOrderListCByMemberId(Map<String, Object> req);

    /**
     * 查询订单详情(APP C端)
     * @param req
     * @return
     */
    public String getRentalOrderDetailsAPPC(Map<String, Object> req);

    /**
     * 更新用户订单预授权
     * @param memberId
     * @param orderId
     * @param status
     */
    Result updateConsentAuthorization(Integer memberId, Integer orderId, String status);

    /**
     * 取消用车
     * @param updateRentalOrderDTO
     * @return
     */
    String cancelUseCarOrder(UpdateRentalOrderDTO updateRentalOrderDTO);

    /**
     * 订单id查询详情
     * @param orderId
     * @return
     */
    RentalOrder getRentalOrderByOrderId(Integer orderId);
}
