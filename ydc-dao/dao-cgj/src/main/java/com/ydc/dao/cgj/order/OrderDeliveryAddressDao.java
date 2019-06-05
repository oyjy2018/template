package com.ydc.dao.cgj.order;

import com.ydc.model.cgj.OrderDeliveryAddress;
import org.apache.ibatis.annotations.Param;

/**
 * 订单收货地址
 */
public interface OrderDeliveryAddressDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    int insert(OrderDeliveryAddress record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    int insertSelective(OrderDeliveryAddress record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    OrderDeliveryAddress selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    int updateByPrimaryKeySelective(OrderDeliveryAddress record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-08 10:10:25
     */
    int updateByPrimaryKey(OrderDeliveryAddress record);

    /**
     * 订单查询收货地址
     * @param orderId
     * @return
     */
    OrderDeliveryAddress getOrderDeliveryAddress(@Param("orderId") Integer orderId);
}