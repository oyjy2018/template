package com.ydc.cgj.ydhc.app.service;

import java.util.Map;

public interface VehicleUsedService {
    /**
     * 获取二手车列表
     * @param req
     * @return
     */
    public String getVehicleUsedListApp(Map<String, Object> req);

    /**
     * 获取二手车详情
     * @param req
     * @return
     */
    public String getVehicleUsedDetailApp(Map<String, Object> req);
}
