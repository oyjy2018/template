package com.ydc.cgj.rentalb.app.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;

/**
 * @author
 * @create 2019-01-04 10:03
 **/
public interface RentalEnterpriseOrderService {


    /**
     * 查询外部订单列表
     * @param dto
     * @return
     */
    String getStoreRentalEnterpriseOrderList(RentalEnterpriseOrderDTO dto);

    /**
     * 外部订单:查看详情
     * @param dto
     * @return
     */
    String getStoreRentalEnterpriseOrderId(RentalEnterpriseOrderDTO dto);


    /**
     * 外部订单:取消订单
     * @param dto
     * @return
     */
    String cancelRentalEnterpriseOrder(RentalEnterpriseOrderDTO dto);


    /**
     * 外部订单:拒绝订单
     * @param dto
     * @return
     */
    String rejectRentalEnterpriseOrder(RentalEnterpriseOrderDTO dto);


    /**
     * 外部订单:确认订单
     * @param dto
     * @return
     */
    String notarizeRentalEnterpriseOrder(RentalEnterpriseOrderDTO dto);


    /**
     * 外部订单:订单id查询车辆信息
     * @param dto
     * @return
     */
    String getRentalCheckCarByOrderId(RentalCheckCarDTO dto);
}
