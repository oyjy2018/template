package com.ydc.cgj.ydhc.app.controller;

import com.ydc.cgj.ydhc.app.service.VehicleUsedService;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.ydhc.PublicReqDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping(value = "/vehicleUsed")
public class VehicleUsedController {

    private final Logger logger = LogManager.getLogger(VehicleUsedController.class);
    @Autowired
    private VehicleUsedService vehicleUsedService;

    /**
     * 获取二手车列表
     * @param publicReqDto
     * @return
     */
    @PostMapping(value = "/getVehicleUsedListApp")
    public String getVehicleUsedListApp(@RequestBody PublicReqDto publicReqDto){
        Map<String, Object> req = JsonUtil.jsonToMap(publicReqDto.getData());
        return vehicleUsedService.getVehicleUsedListApp(req);
    }

    /**
     * 获取二手车详情
     * @param publicReqDto
     * @return
     */
    @PostMapping(value = "/getVehicleUsedDetailApp")
    public String getVehicleUsedDetailApp(@RequestBody PublicReqDto publicReqDto){
        Map<String, Object> req = JsonUtil.jsonToMap(publicReqDto.getData());
        return vehicleUsedService.getVehicleUsedDetailApp(req);
    }

}
