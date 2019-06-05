package com.ydc.service.order.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.rental.RentalOrder;

import java.util.List;
import java.util.Map;

/**
 * 租车-订单
 *
 * @author
 * @create 2018-11-21 20:47
 **/
public interface RentalOrderService {


    /**
     * 查询车辆等级
     * @return
     */
    List<Map<String,Object>> getCarLevelGroup();

    /**
     * 查询车辆品牌
     * @return
     */
    List<Map<String,Object>> getBrandByCarLevel(CommCarDTO commCarDTO);

    /**
     * 查询车辆车系
     * @return
     */
    List<Map<String,Object>> getSeriesByBrand(CommCarDTO commCarDTO);


    /**
     * 查询车辆车型
     * @return
     */
    List<Map<String,Object>> getModelBySeries(CommCarDTO commCarDTO);

    /**
     * 查询对应车辆
     * @param commCarDTO
     * @return
     */
    List<CarPlateVO> getCarPlateList(CommCarDTO commCarDTO);

    /**
     * 新增租车订单
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    Integer insertOrder(AddRentalOrderPCDTO addRentalOrderPCDTO);


    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    Integer updateByOrderId(RentalOrder record);



    /**
     * 查询所有门店
     * @param
     * @return
     */
    List<Map<String,Object>> getAllRentalStore();

    /**
     * 出车更新信息
     * @param comeCarOrderDTO
     * @return
     */
    Integer updateComeCarOrder(ComeCarOrderDTO comeCarOrderDTO) throws Exception;

    /**
     * 出车更新信息
     * @param repayCarOrderDTO
     * @return
     */
    Integer updateRepayCarOrder(RepayCarOrderDTO repayCarOrderDTO) throws Exception;

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
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-21 20:19:15
     */
    RentalOrder getRentalOrderByOrderId(Integer orderId);

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
    RentalOrder getRentalOrderByMemberId(Integer memberId);


    /**
     * 更新用户订单预授权
     * @param orderId
     * @param status
     */
    Result updateConsentAuthorization(Integer orderId, Integer status);

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
    ComeCarOrderVO getComeCarOrderData(Integer orderId);



    /**
     * 更新租车订单状态
     * @param updateRentalOrderDTO
     * @return
     */
    Integer updateRentalOrderStatus(UpdateRentalOrderDTO updateRentalOrderDTO);

    /**
     * 更新订单流程状态
     * @param status
     * @param flowOneStatus
     * @param flowTwoStatus
     * @param flowThreeStatus
     * @return
     */
    int updateRentalOrderFlowStatusById(String status, String flowOneStatus, String flowTwoStatus, String flowThreeStatus, Integer rentalOrderId);

    /**
     * 新增车辆操作日志
     * @param userId
     * @param content
     */
    void saveCommCarOperLogDao(Integer carId,Integer userId,String content);

    /**
     * 根据车辆id查询最后一次还车里程
     * @param carId
     * @return
     */
    RentalCarDataVO getCarOilDesc(Integer carId);

    /**
     * 车辆id查询车辆信息
     * @param carId
     * @return
     */
    CommCar getCarInfoByCarId(Integer carId);

    //验证是否能新增
    Result verifyEnableInsert(AddRentalOrderPCDTO addRentalOrderPCDTO);
}
