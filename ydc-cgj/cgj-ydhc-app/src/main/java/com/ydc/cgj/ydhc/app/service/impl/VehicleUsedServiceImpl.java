package com.ydc.cgj.ydhc.app.service.impl;

import com.ydc.cgj.ydhc.app.feignService.IVehicleUsedService;
import com.ydc.cgj.ydhc.app.service.VehicleUsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VehicleUsedServiceImpl implements VehicleUsedService {

    @Autowired
    private IVehicleUsedService iVehicleUsedService;

    @Override
    public String getVehicleUsedListApp(Map<String, Object> req) {
        return iVehicleUsedService.getVehicleUsedListApp(req);
    }

    @Override
    public String getVehicleUsedDetailApp(Map<String, Object> req) {
        return iVehicleUsedService.getVehicleUsedDetailApp(req);
    }
}
