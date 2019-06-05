package com.ydc.cgj.ydhc.web.service;

import java.util.Map;

public interface VehicleService {
    /**
     * 修改或保存车辆信息
     *
     * @param req
     * @return
     */
    public String saveOrUpdateVehicle(Map<String, Object> req);

    /**
     * 更新发布状态
     *
     * @param req
     * @return
     */
    public String updateReleaseStatus(Map<String, String> req);

    /**
     * 获取车辆信息
     *
     * @param req
     * @return
     */
    public String getVehicleInfo(Map<String, String> req);

    /**
     * 查询车辆列表信息
     *
     * @param req
     * @return
     */
    public String getYdhcVehicleList(Map<String, Object> req);
}
