package com.ydc.cgj.rentalb.app.service;

import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RepayCarOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import com.ydc.model.cgj.rental.RentalOrder;
import java.util.Map;

/**
 * 租车订单信息
 *
 * @author
 * @create 2018-11-22 14:28
 **/
public interface RentalOrderService {


    /**
     * 获取出车信息
     * @param commCarDTO
     * @return
     */
    String getComeCarData( CommCarDTO commCarDTO);

    /**
     * 出车更新订单信息
     * @param
     * @return
     */
    String updateComeCarOrder( ComeCarOrderDTO comeCarOrderDTO);

    /**
     * 获取还车信息
     * @param commCarDTO
     * @return
     */
    String getRepayCarData( CommCarDTO commCarDTO);

    /**
     * 还车更新订单信息
     * @param
     * @return
     */
    String updateRepayCarOrder( RepayCarOrderDTO repayCarOrderDTO);


    /**
     * 还车的时候——更新出车订单信息
     * @param
     * @return
     */
    String updateComeCarOrderInRepayCar( ComeCarOrderDTO comeCarOrderDTO);

    /**
     * 根据门店ID查询订单列表(B端)
     * @param req
     * @return
     */
    String getRentalOrderListBByStoreId(Map<String, Object> req);

    /**
     * 查询订单详情(APP B端)
     * @param req
     * @return
     */
    String getRentalOrderDetailsAPPB(Map<String, Object> req);

    /**
     * 订单id查询详情
     * @param orderId
     * @return
     */
    RentalOrder getRentalOrderByOrderId(Integer orderId);


    /**
     * 取消用车
     * @param updateRentalOrderDTO
     * @return
     */
    String cancelUseCarOrder(UpdateRentalOrderDTO updateRentalOrderDTO);

    /**
     * 根据车辆id查询最后一次还车里程
     * @param carId
     * @return
     */
    String getCarOilDesc(Integer carId);
}
