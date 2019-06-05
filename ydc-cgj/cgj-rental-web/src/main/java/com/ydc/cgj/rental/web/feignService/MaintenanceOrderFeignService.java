package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalOrderMaintenanceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 机务单
 * @author
 * @create 2018-11-22 9:38
 **/
@Service
@FeignClient(value = "service-order")
public interface MaintenanceOrderFeignService {

    /**
     * 机务单列表
     * @param rentalOrderMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/maintenanceOrder/getMaintenanceOrderList")
    Result getMaintenanceOrderList(@RequestBody RentalOrderMaintenanceDTO rentalOrderMaintenanceDTO);

    /**
     * 新增机务单
     *
     * @param rentalOrderMaintenanceInsertDTO
     * @return
     */
    @PostMapping(value = "/maintenanceOrder/saveMaintenanceOrder")
    String saveMaintenanceOrder(@RequestBody RentalOrderMaintenanceInsertDTO rentalOrderMaintenanceInsertDTO);

    /**
     * 修改机务单
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/maintenanceOrder/updateMaintenanceOrder")
    String updateMaintenanceOrder(@RequestBody Map<String, Object> param);

    /**
     * 机务单详情
     * @param maintenanceOrderId
     * @return
     */
    @GetMapping(value = "/maintenanceOrder/getMaintenanceOrderDetail")
    RentalOrderMaintenanceVO getMaintenanceOrderDetail(@RequestParam(value = "maintenanceOrderId") Integer maintenanceOrderId);

    /**
     * 出车详情
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/maintenanceOrder/getMaintenanceOrderDrawOut")
    String getMaintenanceOrderDrawOut(@RequestParam("id") Integer id);

    /**
     * 根据车辆id获取机务单id
     *
     * @param carId
     * @return
     */
    @PostMapping(value = "/maintenanceOrder/getMaintenanceOrderIdByCarId")
    String getMaintenanceOrderIdByCarId(@RequestParam("carId") Integer carId);

    /**
     * 删除机务单(软删)
     *
     * @param id
     * @param userId
     * @return
     */
    @PostMapping(value = "/maintenanceOrder/deleteMaintenanceOrderById")
    String deleteMaintenanceOrderById(@RequestParam("id") Integer id, @RequestParam("userId") Integer userId);
}
