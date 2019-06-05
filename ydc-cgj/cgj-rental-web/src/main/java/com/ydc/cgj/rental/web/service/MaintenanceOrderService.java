package com.ydc.cgj.rental.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalOrderMaintenanceVO;

import java.util.Map;

/**
 * @author
 * @create 2018-11-22 13:00
 **/
public interface MaintenanceOrderService {

    /**
     * 机务单列表
     *
     * @param rentalOrderMaintenanceDTO
     * @return
     */
    Result getMaintenanceOrderList(RentalOrderMaintenanceDTO rentalOrderMaintenanceDTO);

    /**
     * 新增机务单
     *
     * @param rentalOrderMaintenanceInsertDTO
     * @return
     */
    String saveMaintenanceOrder(RentalOrderMaintenanceInsertDTO rentalOrderMaintenanceInsertDTO);

    /**
     * 修改机务单
     *
     * @param param
     * @return
     */
    String updateMaintenanceOrder(Map<String, Object> param);

    /**
     * 机务单详情
     *
     * @param maintenanceOrderId
     * @return
     */
    RentalOrderMaintenanceVO getMaintenanceOrderDetail(Integer maintenanceOrderId);

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
