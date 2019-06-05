package com.ydc.cgj.ydhc.web.service.impl;

import com.ydc.cgj.ydhc.web.feignService.VehicleUsedFeignService;
import com.ydc.cgj.ydhc.web.service.VehicleUsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VehicleUsedServiceImpl implements VehicleUsedService {

    @Autowired
    private VehicleUsedFeignService vehicleUsedFeignService;

    @Override
    public String saveOrUpdateVehicleUsed(Map<String, Object> req) {
        return vehicleUsedFeignService.saveOrUpdateVehicleUsed(req);
    }

    @Override
    public String updateReleaseStatus(Map<String, String> req) {
        return vehicleUsedFeignService.updateReleaseStatus(req);
    }

    @Override
    public String getVehicleUsedInfo(Map<String, String> req) {
        return vehicleUsedFeignService.getVehicleUsedInfo(req);
    }

    @Override
    public String getYdhcVehicleUsedList(Map<String, Object> req) {
        return vehicleUsedFeignService.getYdhcVehicleUsedList(req);
    }

}
