package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalSettlement2VO;
import com.ydc.commom.view.vo.cgj.rental.RentalSettlementVO;
import com.ydc.model.cgj.rental.RentalSettlement;

/**
 * @author
 * @create 2018-11-26 14:54
 **/
public interface RentalSettlementService {

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:25:19
     */
    int insert(RentalSettlementDTO record) throws Exception;

    /**
     * 返回结算需要订单信息
     * @param orderId
     * @return
     */
    RentalSettlementVO getRentalSettlement(Integer orderId);

    /**
     * 结算信息
     * @param orderId
     * @return
     */
    RentalSettlement getRentalSettlementByOrderId(Integer orderId);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:25:19
     */
    int updateByPrimaryKey(RentalSettlement record);


    /**
     * 结算明细
     * @param orderId
     * @return
     */
    RentalSettlement2VO getRentalSettlement2VOByOrderId(Integer orderId);
}
