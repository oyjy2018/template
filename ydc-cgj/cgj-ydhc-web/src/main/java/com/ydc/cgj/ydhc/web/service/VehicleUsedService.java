package com.ydc.cgj.ydhc.web.service;

import java.util.Map;

public interface VehicleUsedService {
    /**
     * 修改或保存二手车信息
     * @param req
     * @return
     */
    public String saveOrUpdateVehicleUsed(Map<String, Object> req);

    /**
     * 获取车辆信息
     * @param req
     * @return
     */
    public String getVehicleUsedInfo(Map<String, String> req);

    /**
     * 查询二手车列表信息（包括模板，在售，待售）
     * @param req
     * @return
     */
    public String getYdhcVehicleUsedList(Map<String, Object> req);

    /**
     * 更新发布状态
     * @param req
     * @return
     */
    public String updateReleaseStatus(Map<String, String> req);
}
