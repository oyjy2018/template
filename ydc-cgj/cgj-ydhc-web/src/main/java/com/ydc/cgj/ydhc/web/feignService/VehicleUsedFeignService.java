package com.ydc.cgj.ydhc.web.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@FeignClient(value = "service-car")
public interface VehicleUsedFeignService {


    @PostMapping(value = "/vehicleUsed/getVehicleUsedInfo")
    String getVehicleUsedInfo(@RequestBody Map<String, String> req);

    @PostMapping(value = "/vehicleUsed/saveOrUpdateVehicleUsed")
    String saveOrUpdateVehicleUsed(@RequestBody Map<String, Object> req);

    @PostMapping(value = "/vehicleUsed/updateReleaseStatus")
    String updateReleaseStatus(@RequestBody Map<String, String> req);

    @PostMapping(value = "/vehicleUsed/getYdhcVehicleUsedList")
    String getYdhcVehicleUsedList(@RequestBody Map<String, Object> req);

    @PostMapping(value = "/vehicleUsed/getAllBrand")
    String getAllBrand(@RequestBody Map<String, Object> req);

    @PostMapping(value = "/vehicleUsed/getSeriesByBrand")
    String getSeriesByBrand(Map<String, Object> req);

    @PostMapping(value = "/vehicleUsed/getVersionBySeries")
    String getVersionBySeries(Map<String, Object> req);
}
