package com.ydc.cgj.ydhc.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.SystemUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FeignClient(value = "service-car")
public interface IVehicleUsedService {
    /**
     * 获取二手车列表
     * @param req
     * @return
     */
    @PostMapping(value = "/vehicleUsed/getVehicleUsedListApp")
    public String getVehicleUsedListApp(@RequestBody Map<String, Object> req);

    /**
     * 获取二手车详情
     * @param req
     * @return
     */
    @PostMapping(value = "/vehicleUsed/getVehicleUsedDetailApp")
    public String getVehicleUsedDetailApp(@RequestBody Map<String, Object> req);
}
