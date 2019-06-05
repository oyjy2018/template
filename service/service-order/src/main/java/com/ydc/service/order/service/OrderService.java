package com.ydc.service.order.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.dto.cgj.order.CancelOrderDTO;
import com.ydc.commom.view.dto.cgj.order.MyOrderDTO;
import com.ydc.commom.view.dto.cgj.order.OrderAndOrderCommodityListDTO;
import com.ydc.commom.view.dto.cgj.order.OrderDetailsDTO;
import com.ydc.commom.view.vo.cgj.order.AddOrderResponseVO;
import com.ydc.commom.view.vo.cgj.order.MyOrderVO;
import com.ydc.commom.view.vo.cgj.order.OrderStatusNumVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import com.ydc.commom.view.vo.cgj.shopCart.CreateOrderParamVO;
import com.ydc.model.cgj.Order;
import com.ydc.model.cgj.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderService {

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    Order selectByPrimaryKey(Integer id);

    List<User> queryUserList();

    /**
     * 获取用户订单列表
     *
     * @param orderReqDTO
     * @return
     */
    List<Map<String, Object>> getOrderList(OrderReqDTO orderReqDTO);

    /**
     * 获取用户订单列表总数
     *
     * @param orderReqDTO
     * @return
     */
    Map<String, Object> getOrderListCount(OrderReqDTO orderReqDTO);

    /**
     * H5我的订单
     * @param myOrderDTO
     * @return
     */
    List<MyOrderVO> logicalGetOrderList(MyOrderDTO myOrderDTO);

    /**
     * 订单详情
     * @param orderDetailsDTO
     * @return
     */
    MyOrderVO logicalGetOrderCommodity(OrderDetailsDTO orderDetailsDTO,MyOrderVO myOrderVO);

    /**
     * 取消订单
     *
     * @param status
     * @param updateBy
     * @param orderId
     * @return
     */
    Integer cancelOrder(Integer status, Integer updateBy, Integer orderId,String cancelReason,String cancelTime,Integer oldStatus);

    /**
     * 确认收货
     * @param status
     * @param memberId
     * @param orderId
     * @return
     */
    Integer notarizeReceiving(Integer status, Integer memberId,Integer orderId);

    /**
     *  创建订单
     * @param createOrderParamVO
     * @param memberId
     */
    Result<List<OrderAndOrderCommodityListDTO>> createOrder(CreateOrderParamVO createOrderParamVO, Integer memberId);

    /**
     *  更新订单状态，扣除库存
     */
    AddOrderResponseVO updateCommodityNumAndOrderStatus(List<OrderAndOrderCommodityListDTO> orderAndOrderCommodityListDTOList);

    void updateOrderShopCartStatus(Set<Integer> ids,Integer memberId);

    Result updateOrderPay(PayOrderParamVO payOrderParamVO, Integer memberId) throws Exception;


    /**
     * 我的订单
     * @Param myOrderDTO
     * @return
     */
    List<MyOrderVO> getOrdersByMemberId(MyOrderDTO myOrderDTO);



    /**
     * 我的订单
     * @param orderDetailsDTO
     * @return
     */
    MyOrderVO getOrderByMemberId(OrderDetailsDTO orderDetailsDTO);

    /**
     *  取消订单
     * @param cancelOrderDTO
     * @throws Exception
     */
    void cancelOrder(CancelOrderDTO cancelOrderDTO) throws Exception;

    /**
     * 确认收货
     * @param orderId
     * @param memberId
     * @throws Exception
     */
    void notarizeReceiving(Integer orderId, Integer memberId);

    /**
     * 查询 订单环节数据
     * @return
     */
    List<Map<String, Object>> getOrderStatusNum(Integer memberId);

    /**
     * 系统自动确认收货
     * @param orderId
     * @return
     */
    Integer updateAutoConfirmReceipt(Integer orderId);


    /**
     * 根据订单商品id查询订单
     * @param orderCommodityIds
     * @return
     */
    Order getOrderByOrderCommodityIds(String orderCommodityIds);

    Boolean updateOrderStatus(List<String> orderNos);

    /**
     * 根据流水更新订单状态
     * @param payWater
     * @param newStatus
     * @param oldStatus
     * @return
     */
    int updateOrderStatusByWater(String payWater, int newStatus, Integer oldStatus, Date paymentTime);

    /**
     * 获取一个流水里未发货的券订单数量
     * @param payWater
     * @return
     */
    List<Map<String, Object>> getRollNumInOrder(String payWater);

    /**
     * 获取订单中的用户id
     * @param payWater
     * @return
     */
    Integer getOrderMemberId(String payWater);

    //扣除用户积分
    boolean updateMemberIntegral(Integer memberId, BigDecimal number, Integer integralTypeConsume, String payWater);

    /**
     * 配发券之后更新订单状态
     * @param orderNoList
     * @return
     */
    int updateSendRollOrderStatus(List<String> orderNoList);
}
