package com.ydc.cgj.rental.web.service;

import com.ydc.commom.view.dto.cgj.rental.AddRentalOrderPCDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.model.cgj.rental.RentalOrder;
import java.util.Map;

/**
 * @author
 * @create 2018-11-22 13:00
 **/
public interface RentalOrderService {


    /**
     * 查询车辆等级
     * @param
     * @return
     */
    String getCarLevelGroup();


    /**
     * 查询车辆品牌
     * @param
     * @return
     */
    String getBrandByCarLevel(CommCarDTO commCarDTO);


    /**
     * 查询车辆车系
     * @param
     * @return
     */
    String getSeriesByBrand(CommCarDTO commCarDTO);


    /**
     * 查询车辆车型
     * @param
     * @return
     */
    String getModelBySeries(CommCarDTO commCarDTO);


    /** 新增租车订单
     * @param
     * @return
     */
    String insertOrder(AddRentalOrderPCDTO addRentalOrderPCDTO);

    /**
     * 查询门店集合
     * @param
     * @return
     */
    String getAllStore();

    /**
     * 获取订单列表
     * @param req
     * @return
     */
    String getRentalOrderList(Map<String, Object> req);


    /**
     * 会员id查询租车订单
     * @param memberId
     * @return
     */
    RentalOrder getRentalOrderByMemberId(Integer memberId);

    /**
     * 查询订单详情
     * @param req
     * @return
     */
    public String getRentalOrderDetailsPC(Map<String, Object> req);

    /**
     * 修改订单流程状态
     * @param req
     * @return
     */
    public String updateRentalOrderFlowStatus(Map<String, Object> req);

    /**
     * 订单id查询详情
     * @param orderId
     * @return
     */
    RentalOrder getRentalOrderByOrderId(Integer orderId);

}
