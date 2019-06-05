package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.AddRentalOrderPCDTO;
import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.RepayCarOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.model.cgj.rental.RentalOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RentalOrderDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    Integer insertOrder(AddRentalOrderPCDTO addRentalOrderPCDTO);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    int insertSelective(RentalOrder record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    RentalOrder getRentalOrderByOrderId(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    Integer updateByOrderId(RentalOrder record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    int updateByPrimaryKey(RentalOrder record);

    /**
     * 出车更新信息
     * @param comeCarOrderDTO
     * @return
     */
    Integer updateComeCarOrder(ComeCarOrderDTO comeCarOrderDTO);

    /**
     * 出车更新信息
     * @param repayCarOrderDTO
     * @return
     */
    Integer updateRepayCarOrder(RepayCarOrderDTO repayCarOrderDTO);

    /**
     * 查询订单列表
     * @param param
     * @return
     */
    List<RentalOrderListVO> getRentalOrderList(Map<String, Object> param);

    /**
     * 查询订单列表总数
     * @param param
     * @return
     */
    Map<String, Object> getRentalOrderListCount(Map<String, Object> param);

    /**
     * 根据会员ID查询订单列表
     * @param param
     * @return
     */
    List<RentalOrderListCVO> getRentalOrderListCByMemberId(Map<String, Object> param);

    /**
     * 根据门店ID查询订单列表
     * @param param
     * @return
     */
    List<RentalOrderListBVO> getRentalOrderListBByStoreId(Map<String, Object> param);

    /**
     * 会员id订单
     * @param memberId
     * @return
     */
    RentalOrder getRentalOrderByMemberId(@Param("memberId") Integer memberId);

    /**
     *  获取订单详情（PC端）
     * @param rentalOrderId
     * @return
     */
    RentalOrderDetailsVO getRentalOrderDetailsPC(Integer rentalOrderId);

    /**
     * 获取订单详情（APP C端）
     * @param rentalOrderId
     * @return
     */
    RentalOrderDetailsAPPCVO getRentalOrderDetailsAPPC(Integer rentalOrderId);

    /**
     * 获取订单详情（APP B端）
     * @param rentalOrderId
     * @return
     */
    RentalOrderDetailsAPPBVO getRentalOrderDetailsAPPB(Integer rentalOrderId);

    /**
     * 获取出车信息
     * @param orderId
     * @return
     */
    ComeCarOrderVO getComeCarOrderData(@Param("orderId") Integer orderId);

    /**
     * 更新租车订单状态
     * @param updateRentalOrderDTO
     * @return
     */
    Integer updateRentalOrderStatus(@Param("updateRentalOrderDTO") UpdateRentalOrderDTO updateRentalOrderDTO);


    List<RentalOrder>  selectRentalOrder(RentalOrder rentalOrder);

    /**
     * 更新用户订单状态为oldStatus的订单状态
     * @param rentalOrder
     * @param oldStatus
     * @return
     */
    Integer updateMemberOrderOneStatus(@Param("rentalOrder") RentalOrder rentalOrder, @Param("oldStatus") String oldStatus);

    /**
     * 更新订单流程状态
     * @param status
     * @param flowOneStatus
     * @param flowThreeStatus
     * @return
     */
    int updateRentalOrderFlowStatusById(@Param("status") String status, @Param("flowOneStatus") String flowOneStatus,
                              @Param("flowTwoStatus")String flowTwoStatus,@Param("flowThreeStatus")String flowThreeStatus,
                                        @Param("rentalOrderId") Integer rentalOrderId);

    /**
     * 根据订单id查询租车订单信息
     * @param orderId
     * @return
     */
    Map getCarInfo(@Param("orderId") Integer orderId);

    /**
     * 根据车辆id查询最后一次还车里程
     * @param carId
     * @return
     */
    RentalCarDataVO getCarOilDesc(@Param("carId") Integer carId);

    /**
     * 更新违章次数
     * @param orderId
     * @return
     */
    Integer updateViolationNumber(Integer orderId);

    //获取同时段有效的租车车辆
    List<Map> getExistEffectiveOrderList(Map paramMap);
}