package com.ydc.cgj.ydhc.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.ydhc.VehicleVo;

public interface VehicleService {

    /**
     * 获取车辆列表
     *
     * @param vehicleVo
     * @return
     */
    Result getVehicleList(VehicleVo vehicleVo);

    /**
     * 获取车辆详情
     *
     * @param vehicleId
     * @return
     */
    Result getVehicleDetail(Integer vehicleId);

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
