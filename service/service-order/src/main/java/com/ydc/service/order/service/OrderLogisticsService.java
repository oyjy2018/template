package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.vo.cgj.order.OrderLogisticsVO;
import com.ydc.model.cgj.OrderLogistics;

import java.util.List;

/**
 * 订单物流
 *
 * @author gongjin
 * @create 2018-09-10 12:07
 **/
public interface OrderLogisticsService {

    /**
     * 查询订单物流
     *
     * @param orderId
     * @return
     */
    List<OrderLogisticsVO> getOrderLogistics(Integer orderId);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int insert(OrderLogistics record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int insertSelective(OrderLogistics record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    OrderLogistics selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int updateByPrimaryKeySelective(OrderLogistics record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int updateByPrimaryKey(OrderLogistics record);

    /**
     * 根据子订单ID，插入物流信息
     * @param ord
     * @return
     */
    int insertBySonOrderId(OrderReqDTO ord);

    /**
     * 根据多个订单商品ID插入物流信息
     * @param ord
     * @return
     */
    int insertByOrderCommodityIds(OrderReqDTO ord);
}
