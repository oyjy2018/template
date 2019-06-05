package com.ydc.cgj.app.service;

import com.ydc.commom.view.dto.cgj.VehicleDTO;
import com.ydc.model.cgj.Vehicle;

/**
 * @author
 * @create 2018-10-30 12:27
 **/
public interface VehicleService {

    /**
     * 车辆列表
     *
     * @param vehicleDTO
     * @return
     */
    String getVehicleList(VehicleDTO vehicleDTO);

    /**
     * 添加车辆
     *
     * @param vehicle
     * @return
     */
    String saveVehicle(Vehicle vehicle);


    /**
     * 解约车辆
     *
     * @param vehicleDTO
     * @return
     */
    String unbindVehicle(VehicleDTO vehicleDTO);


    /**
     * 车辆详情
     * @param vehicleDTO
     * @return
     */
    String getVehicleDetails(VehicleDTO vehicleDTO);

    /**
     * 更新车辆信息
     * @param vehicle
     * @return
     */
    String updateVehicleData(Vehicle vehicle);

    /**
     * 获取车辆配置
     * @return
     */
    String getVehicleBrandConfig();

    /**
     * 根据车系获取车型
     * @param series
     * @return
     */
    String getVehicleModelBySeries(String series);

}
