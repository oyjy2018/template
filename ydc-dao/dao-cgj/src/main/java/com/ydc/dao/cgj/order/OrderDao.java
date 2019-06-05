package com.ydc.dao.cgj.order;

import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.dto.cgj.order.MyOrderDTO;
import com.ydc.commom.view.dto.cgj.order.OrderDetailsDTO;
import com.ydc.commom.view.vo.cgj.order.MyOrderVO;
import com.ydc.commom.view.vo.cgj.order.OrderStatusNumVO;
import com.ydc.model.cgj.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int insert(Order record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int insertSelective(Order record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * 获取用户订单列表
     *
     * @param orderReqDTO
     * @return
     */
    List<Map<String, Object>> getOrderList(@Param("orderReqDTO") OrderReqDTO orderReqDTO);

    /**
     * 获取用户订单列表总数
     *
     * @param orderReqDTO
     * @return
     */
    Map<String, Object> getOrderListCount(@Param("orderReqDTO") OrderReqDTO orderReqDTO);

    Order selectByOrderNo(String orderNo);


    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int updateByPrimaryKey(Order record);

    int updateByOrderNoAndStatus(Map<String,Object> map );

    int updateBatchByStatus(Map<String,Object> map );

    /**
     * 取消订单
     * @param status
     * @param memberId
     * @param orderId
     * @return
     */
    Integer cancelOrder(@Param("status") Integer status, @Param("memberId") Integer memberId,
                              @Param("orderId") Integer orderId, @Param("cancelReason") String cancelReason,
                              @Param("cancelTime") String cancelTime, @Param("oldStatus") Integer oldStatus);

    /**
     * 确认收货
     * @param status
     * @param memberId
     * @param orderId
     * @return
     */
    Integer notarizeReceiving(@Param("status") Integer status, @Param("memberId") Integer memberId,@Param("orderId") Integer orderId);


    /**
     * 我的订单
     * @param myOrderDTO
     * @return
     */
    List<MyOrderVO> getOrdersByMemberId(@Param("myOrderDTO") MyOrderDTO myOrderDTO);

    /**
     * 我的订单
     * @param orderDetailsDTO
     * @return
     */
    MyOrderVO getOrderByMemberId(@Param("orderDetailsDTO") OrderDetailsDTO orderDetailsDTO);

    /**
     * 根据订单状态查询订单
     * @param status
     * @return
     */
    List<Order> getOrderByStatus(@Param("status") Integer status);

    /**
     * 批量关闭订单
     * @param list
     */
    void updateBatch(List<Integer> list,@Param("status") Integer status);

    /**
     * 查询 订单环节数据
     * @return
     */
    List<OrderStatusNumVO> getOrderStatusNum(@Param("memberId") Integer memberId);

    /**
     * 系统自动确认收货
     * @param orderId
     * @return
     */
    Integer updateAutoConfirmReceipt(@Param("orderId") Integer orderId);

    /**
     * 根据订单商品id查询订单
     * @param orderCommodityIds
     * @return
     */
    Order getOrderByOrderCommodityIds(@Param("orderCommodityIds") String orderCommodityIds);

    /**
     * 根据流水更新订单状态
     * @param payWater
     * @param newStatus
     * @param oldStatus
     * @return
     */
    int updateOrderStatusByWater(@Param(value = "payWater") String payWater,
                                 @Param(value = "newStatus") int newStatus,
                                 @Param(value = "oldStatus") Integer oldStatus,
                                 @Param(value = "paymentTime") Date paymentTime);

    /**
     * 获取订单中券的信息
     * @param payWater
     * @return
     */
    List<Map<String, Object>> getRollNumInOrder(@Param(value = "payWater") String payWater);

    /**
     * 获取订单中的用户id
     * @param payWater
     * @return
     */
    Integer getOrderMemberId(String payWater);

    /**
     * 配发券之后更新订单状态
     * @param orderNoList
     * @return
     */
    int updateSendRollOrderStatus(@Param(value = "orderNoList") List<String> orderNoList);
}