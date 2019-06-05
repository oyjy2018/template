package com.ydc.dao.cgj.order;

import com.ydc.model.cgj.OrderLogistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 物流表
 */
public interface OrderLogisticsDao {
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
    int insert(OrderLogistics record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int insertSelective(OrderLogistics record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    OrderLogistics selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int updateByPrimaryKeySelective(OrderLogistics record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-08 10:10:26
     */
    int updateByPrimaryKey(OrderLogistics record);

    /**
     * 根据子订单ID，插入物流信息
     *
     * @param orderId
     * @param logisticsCompany
     * @param logisticsOrder
     * @param updateBy
     * @return
     */
    int insertBySonOrderId(@Param("orderId") Integer orderId, @Param("logisticsCompany") String logisticsCompany,
                           @Param("logisticsOrder") String logisticsOrder, @Param("updateBy") Integer updateBy);

    /**
     * 根据多个订单商品ID插入物流信息
     *
     * @param orderCommodityIds
     * @param logisticsCompany
     * @param logisticsOrder
     * @param updateBy
     * @return
     */
    int insertByOrderCommodityIds(@Param("orderCommodityIds") String orderCommodityIds,
                                  @Param("logisticsCompany") String logisticsCompany,
                                  @Param("logisticsOrder") String logisticsOrder, @Param("updateBy") Integer updateBy);

    /**
     * 查询订单物流
     *
     * @param orderId
     * @return
     */
    List<OrderLogistics> getOrderLogistics(@Param("orderId") Integer orderId);

    /**
     * 确认收货（X天后，系统自动确认收货）
     * @return
     */
    List<Map<String,Object>> getOrderLogisticsTask();

    /**
     * 批量更新订单状态
     * @param list
     */
    void batchUpdateOrderLogistics(List<Map<String,Object>> list);
}