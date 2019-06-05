package com.ydc.cgj.app.feignService;

import com.ydc.commom.view.dto.cgj.VehicleDTO;
import com.ydc.model.cgj.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author
 * @create 2018-10-31 11:53
 **/

@Service
@FeignClient(value = "service-user")
public interface IVehicleFeignService {

    /**
     * 车辆列表
     * @param vehicleDTO
     * @return
     */
    @PostMapping(value = "/vehicle/getVehicleList")
    String getVehicleList(@RequestBody VehicleDTO vehicleDTO);

    /**
     * 添加车辆
     * @param vehicle
     * @return
     */
    @PostMapping(value = "/vehicle/saveVehicle")
    String saveVehicle(@RequestBody Vehicle vehicle);

    /**
     * 解绑车辆
     * @param vehicleDTO
     * @return
     */
    @PostMapping(value = "/vehicle/unbindVehicle")
    String unbindVehicle(@RequestBody VehicleDTO vehicleDTO);

    /**
     * 车辆详情
     * @param vehicleDTO
     * @return
     */
    @PostMapping(value = "/vehicle/getVehicleDetails")
    String getVehicleDetails(@RequestBody VehicleDTO vehicleDTO);

    /**
     * 更新车辆信息
     * @param vehicle
     * @return
     */
    @PostMapping(value = "/vehicle/updateVehicleData")
    String updateVehicleData(@RequestBody Vehicle vehicle);
}
