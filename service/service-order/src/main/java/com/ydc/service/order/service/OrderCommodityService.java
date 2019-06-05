package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.model.cgj.OrderCommodity;

import java.util.List;
import java.util.Map;

/**
 * 订单商品表
 *
 * @author gongjin
 * @create 2018-09-08 15:12
 **/
public interface OrderCommodityService {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int insert(OrderCommodity record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int insertSelective(OrderCommodity record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    OrderCommodity selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int updateByPrimaryKeySelective(OrderCommodity record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int updateByPrimaryKey(OrderCommodity record);

    /**
     * 获取商品订单记录
     *
     * @param orderReqDTO
     * @return
     */
    List<Map<String, Object>> getOrderCommodityList(OrderReqDTO orderReqDTO);

    /**
     * 获取商品订单记录总数
     *
     * @param orderReqDTO
     * @return
     */
    Map<String, Object> getOrderCommodityListCount(OrderReqDTO orderReqDTO);

    /**
     * 通过商品订单ID集合获取已发货的记录
     * @param orderCommodityIds
     * @return
     */
    List<OrderCommodity> getOrderCommodityByOrderCommodityIds(String orderCommodityIds);
}
