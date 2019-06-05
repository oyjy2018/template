package com.ydc.cgj.ydhc.web.service.impl;

import com.ydc.cgj.ydhc.web.feignService.VehicleFeignService;
import com.ydc.cgj.ydhc.web.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleFeignService vehicleFeignService;

    @Override
    public String saveOrUpdateVehicle(Map<String, Object> req) {
        return vehicleFeignService.saveOrUpdateVehicle(req);
    }

    @Override
    public String updateReleaseStatus(Map<String, String> req) {
        return vehicleFeignService.updateReleaseStatus(req);
    }

    @Override
    public String getVehicleInfo(Map<String, String> req) {
        return vehicleFeignService.getVehicleInfo(req);
    }

    @Override
    public String getYdhcVehicleList(Map<String, Object> req) {
        return vehicleFeignService.getYdhcVehicleList(req);
    }
}
