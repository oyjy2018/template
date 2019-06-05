package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.VehicleService;
import com.ydc.commom.view.dto.cgj.VehicleDTO;
import com.ydc.model.cgj.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 会员车辆
 *
 * @author
 * @create 2018-10-30 12:30
 **/
@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {


    private static final Logger logger = LogManager.getLogger(VehicleController.class);


    @Autowired
    VehicleService vehicleService;

    /**
     * 车辆列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getVehicleList")
    public String getVehicleList(@RequestParam("data") String data) {
        logger.info("subject:{},vehicleDTO:{}","车辆列表",data);
        VehicleDTO vehicleDTO = JSONObject.parseObject(data,VehicleDTO.class);
        vehicleDTO.setMemberId(SubjectUtil.getMemberId());
        return vehicleService.getVehicleList(vehicleDTO);
    }

    /**
     * 添加车辆
     * @param data
     * @return
     */
    @PostMapping(value = "/saveVehicle")
    public String saveVehicle(@RequestParam("data") String data){
        logger.info("subject:{},vehicle:{}","添加车辆",data);
        Vehicle vehicle = JSONObject.parseObject(data,Vehicle.class);
        vehicle.setMemberId(SubjectUtil.getMemberId());
        return vehicleService.saveVehicle(vehicle);
    }

    /**
     * 解绑车辆
     * @param data
     * @return
     */
    @PostMapping(value = "/unbindVehicle")
    public String unbindVehicle(@RequestParam("data") String data){
        logger.info("subject:{},vehicleDTO:{}","解绑车辆",data);
        VehicleDTO vehicleDTO = JSONObject.parseObject(data,VehicleDTO.class);
        return vehicleService.unbindVehicle(vehicleDTO);
    }

    /**
     * 获取车辆信息配置
     *
     * @return
     */
    @PostMapping(value = "/getVehicleConfig")
    public String getVehicleConfig() {
        return vehicleService.getVehicleBrandConfig();
    }

    /**
     * 根据车系获取车型
     * @param data
     * @return
     */
    @PostMapping(value = "/getVehicleModelBySeries")
    public String getVehicleModelBySeries(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","根据车系获取车型",data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        return vehicleService.getVehicleModelBySeries(jsonObject.getString("series"));
    }

    /**
     * 车辆详情
     * @param data
     * @return
     */
    @PostMapping(value = "/getVehicleDetails")
    public String getVehicleDetails(@RequestParam("data") String data){
        logger.info("subject:{},vehicleDTO:{}","车辆详情",data);
        VehicleDTO vehicleDTO = JSONObject.parseObject(data,VehicleDTO.class);
        return vehicleService.getVehicleDetails(vehicleDTO);
    }

    /**
     * 更新车辆信息
     * @param data
     * @return
     */
    @PostMapping(value = "/updateVehicleData")
    public String updateVehicleData(@RequestParam("data") String data){
        logger.info("subject:{},vehicle:{}","更新车辆信息",data);
        Vehicle vehicle = JSONObject.parseObject(data,Vehicle.class);
        vehicle.setMemberId(SubjectUtil.getMemberId());
        return vehicleService.updateVehicleData(vehicle);
    }
}
