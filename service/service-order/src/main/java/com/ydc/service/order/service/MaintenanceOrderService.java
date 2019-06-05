package com.ydc.service.order.service;


import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalAccidentMaintenanceVO;
import com.ydc.commom.view.vo.cgj.rental.RentalOrderMaintenanceVO;
import com.ydc.model.cgj.rental.*;

import java.util.List;
import java.util.Map;

/**
 * 机务单
 *
 * @author
 * @create 2018-11-21 20:47
 **/
public interface MaintenanceOrderService {

    /**
     * 新增机务单（出车）
     *
     * @param rentalOrderMaintenanceInsertDTO
     * @return
     */
    String saveMaintenanceOrder(RentalOrderMaintenanceInsertDTO rentalOrderMaintenanceInsertDTO);

    /**
     * 机务单列表
     * @param rentalOrderMaintenanceDTO
     * @return
     */
    List<RentalOrderMaintenanceVO> getMaintenanceOrderList(RentalOrderMaintenanceDTO rentalOrderMaintenanceDTO);

    /**
     * 修改机务单（还车）
     *
     * @param param
     * @return
     */
    String updateMaintenanceOrder(Map<String, Object> param);

    /**
     * 根据id去获取机务单
     * @param maintenanceOrderId
     * @return
     */
    RentalOrderMaintenanceVO getMaintenanceOrderById(Integer maintenanceOrderId);

    /**
     * 机务单id获取洗车单
     * @param maintenanceId
     * @return
     */
    RentalCarWashInfo selectWashInfoByMaintenanceId(Integer maintenanceId);

    /**
     * 根据机务单id获取加油信息
     * @param maintenanceId
     * @return
     */
    RentalRefuelInfo selectRefuelByMaintenanceId(Integer maintenanceId);

    /**
     * 根据机务单id获取维修保养信息
     * @param maintenanceId
     * @return
     */
    Map<String, Object> selectMaintenanceInfoByMaintenanceId(Integer maintenanceId);

    /**
     * 根据机务单id获取事故维修信息
     * @param maintenanceId
     * @return
     */
    RentalAccidentMaintenanceVO selectAccidentMaintenanceByMaintenanceId(Integer maintenanceId);

    /**
     * 根据机务单id获取调用信息
     * @param maintenanceId
     * @return
     */
    RentalDispatch selectDispatchInfoByMaintenanceId(Integer maintenanceId);

    /**
     * 出车详情
     *
     * @param id
     * @return
     */
    String getMaintenanceOrderDrawOut(Integer id);

    /**
     * 根据车辆id获取机务单id
     *
     * @param carId
     * @return
     */
    String getMaintenanceOrderIdByCarId(Integer carId);

    /**
     * 删除机务单(软删)
     *
     * @param id
     * @param userId
     * @return
     */
    String deleteMaintenanceOrderById(Integer id, Integer userId);
}
