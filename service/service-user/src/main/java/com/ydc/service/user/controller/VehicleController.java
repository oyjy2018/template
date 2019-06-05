package com.ydc.service.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.VehicleDTO;
import com.ydc.commom.view.vo.cgj.VehicleVO;
import com.ydc.model.cgj.Vehicle;
import com.ydc.service.user.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 会员车辆
 *
 * @author
 * @create 2018-10-30 12:09
 **/

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private static final Logger logger = LogManager.getLogger(VehicleController.class);


    @Autowired
    VehicleService vehicleService;

    /**
     * 车辆列表
     * @param vehicleDTO
     * @return
     */
    @PostMapping(value = "/getVehicleList")
    public String getVehicleList(@RequestBody VehicleDTO vehicleDTO) {
        logger.info("subject:{},vehicleDTO:{}","车辆列表",JsonUtil.gsonStr(vehicleDTO));
        try {
            List<Map<String, Object>> mapList = vehicleService.getVehicleList(vehicleDTO);
            return Result.success(mapList).toJSON();
        }catch (Exception e){
            logger.error("车辆列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 添加车辆
     * @param vehicle
     * @return
     */
    @PostMapping(value = "/saveVehicle")
    public String saveVehicle(@RequestBody Vehicle vehicle){
        logger.info("subject:{},vehicle:{}","添加车辆",JsonUtil.gsonStr(vehicle));
        try{
            if(vehicle == null)return Result.failure("车辆信息为空").toJSON();
            Vehicle cVehicle = vehicleService.selectByCarPlate(vehicle.getCarPlate());
            if(cVehicle != null && cVehicle.getMemberId().intValue() != vehicle.getMemberId().intValue()){
                return Result.failure("车牌号已被其他用户绑定,有问题,请联系客户!").toJSON();
            }
            Vehicle oldVehicle = vehicleService.selectByCarPlateAndMemberId(vehicle.getCarPlate(),vehicle.getMemberId());
            if(oldVehicle != null){
                if(oldVehicle.getStatus() == 1){
                    return Result.failure("车牌号已存在").toJSON();
                }else{
                    vehicle.setId(oldVehicle.getId());
                    vehicle.setUpdateBy(oldVehicle.getCreateBy());
                    return vehicleService.updateByPrimaryKey(vehicle) <= 0 ? Result.failure("添加车辆失败").toJSON() : Result.success("成功").toJSON();
                }
            }
            return vehicleService.insert(vehicle) <= 0 ? Result.failure("添加车辆失败").toJSON() : Result.success("成功").toJSON();
        }catch (Exception e){
            logger.error("添加车辆异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 解绑车辆
     * @param vehicleDTO
     * @return
     */
    @PostMapping(value = "/unbindVehicle")
    public String unbindVehicle(@RequestBody VehicleDTO vehicleDTO){
        logger.info("subject:{},vehicleDTO:{}","解绑车辆",JsonUtil.gsonStr(vehicleDTO));
        try {
            Vehicle vehicle = vehicleService.selectByPrimaryKey(vehicleDTO.getVehicleId());
            if(vehicle.getStatus() == 0){
                return Result.failure("车辆已处于解绑状态").toJSON();
            }
            vehicle.setStatus(0);
            vehicle.setUpdateBy(vehicleDTO.getMemberId());
            return vehicleService.updateByPrimaryKey(vehicle) <= 0 ? Result.failure("解绑失败").toJSON() : Result.success("成功").toJSON();
        }catch (Exception e){
            logger.error("解绑车辆异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 车辆详情
     * @param vehicleDTO
     * @return
     */
    @PostMapping(value = "/getVehicleDetails")
    public String getVehicleDetails(@RequestBody VehicleDTO vehicleDTO){
        logger.info("subject:{},vehicleDTO:{}","车辆详情",JsonUtil.gsonStr(vehicleDTO));
        try{
            VehicleVO vehicleVO = vehicleService.getVehicleVOById(vehicleDTO.getVehicleId());
            return Result.success(vehicleVO).toJSON();
        }catch (Exception e){
            logger.error("查看车辆详情异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 更新车辆信息
     * @param vehicle
     * @return
     */
    @PostMapping(value = "/updateVehicleData")
    public String updateVehicleData(@RequestBody Vehicle vehicle){
        logger.info("subject:{},vehicle:{}","更新车辆信息",JsonUtil.gsonStr(vehicle));
        try{
//            Vehicle oldVehicle = vehicleService.selectByPrimaryKey(vehicle.getId());
//            vehicle.setCreateTime(oldVehicle.getCreateTime());
//            vehicle.setCreateBy(oldVehicle.getCreateBy());
            vehicle.setStatus(1);
            vehicle.setUpdateBy(vehicle.getMemberId());
            return vehicleService.updateByPrimaryKey(vehicle) <= 0 ? Result.failure("保存失败").toJSON() : Result.success("成功").toJSON();
        }catch (DuplicateKeyException ms){
            logger.error("车牌号已存在异常",ms);
            return Result.failure("车牌号已存在").toJSON();
        }catch (Exception e){
            logger.error("更新车辆信息异常",e);
            return Result.failure().toJSON();
        }
    }
}
