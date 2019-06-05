package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.MaintenanceOrderFeignService;
import com.ydc.cgj.rental.web.service.MaintenanceOrderService;
import com.ydc.cgj.rental.web.service.RentalOrderService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalOrderMaintenanceVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 机务单service
 * @author
 * @create 2018-11-22 13:01
 **/
@Service
public class MaintenanceOrderServiceImpl implements MaintenanceOrderService {


    private static final Logger logger = LogManager.getLogger(RentalOrderService.class);


    @Autowired
    MaintenanceOrderFeignService maintenanceOrderFeignService;

    @Override
    public Result getMaintenanceOrderList(RentalOrderMaintenanceDTO rentalOrderMaintenanceDTO) {
        return maintenanceOrderFeignService.getMaintenanceOrderList(rentalOrderMaintenanceDTO);
    }

    /**
     * 新增机务单
     *
     * @param rentalOrderMaintenanceInsertDTO
     * @return
     */
    @Override
    public String saveMaintenanceOrder(RentalOrderMaintenanceInsertDTO rentalOrderMaintenanceInsertDTO) {
        return maintenanceOrderFeignService.saveMaintenanceOrder(rentalOrderMaintenanceInsertDTO);
    }

    /**
     * 修改机务单
     *
     * @param param
     * @return
     */
    @Override
    public String updateMaintenanceOrder(Map<String, Object> param) {
        return maintenanceOrderFeignService.updateMaintenanceOrder(param);
    }

    @Override
    public RentalOrderMaintenanceVO getMaintenanceOrderDetail(Integer maintenanceOrderId) {
        return maintenanceOrderFeignService.getMaintenanceOrderDetail(maintenanceOrderId);
    }

    /**
     * 出车详情
     *
     * @param id
     * @return
     */
    @Override
    public String getMaintenanceOrderDrawOut(Integer id) {
        return maintenanceOrderFeignService.getMaintenanceOrderDrawOut(id);
    }

    /**
     * 根据车辆id获取机务单id
     *
     * @param carId
     * @return
     */
    @Override
    public String getMaintenanceOrderIdByCarId(Integer carId) {
        return maintenanceOrderFeignService.getMaintenanceOrderIdByCarId(carId);
    }

    /**
     * 删除机务单(软删)
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    public String deleteMaintenanceOrderById(Integer id, Integer userId) {
        return maintenanceOrderFeignService.deleteMaintenanceOrderById(id,userId);
    }
}
