package com.ydc.cgj.ydhc.web.feignService;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@FeignClient(value = "service-car")
public interface VehicleFeignService {

    @PostMapping(value = "/vehicle/saveOrUpdateVehicle")
    String saveOrUpdateVehicle(@RequestBody Map<String, Object> req);

    @PostMapping(value = "/vehicle/updateReleaseStatus")
    String updateReleaseStatus(@RequestBody Map<String, String> req);

    @PostMapping(value = "/vehicle/getVehicleInfo")
    String getVehicleInfo(@RequestBody Map<String, String> req);

    @PostMapping(value = "/vehicle/getYdhcVehicleList")
    String getYdhcVehicleList(@RequestBody Map<String, Object> req);
}
