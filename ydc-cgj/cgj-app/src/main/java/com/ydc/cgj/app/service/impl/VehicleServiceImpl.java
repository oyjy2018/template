package com.ydc.cgj.app.service.impl;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.app.feignService.IVehicleConfigFeignService;
import com.ydc.cgj.app.feignService.IVehicleFeignService;
import com.ydc.cgj.app.service.VehicleService;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.VehicleDTO;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import com.ydc.model.cgj.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author
 * @create 2018-10-30 12:28
 **/
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    IVehicleFeignService iVehicleFeignService;
    @Autowired
    IVehicleConfigFeignService iVehicleConfigFeignService;

    @Override
    public String getVehicleList(VehicleDTO vehicleDTO) {
        return iVehicleFeignService.getVehicleList(vehicleDTO);
    }

    @Override
    public String saveVehicle(Vehicle vehicle) {
        //数据添加
        if (vehicle == null) {
            return Result.failure("参数为空").toJSON();
        }
//        if (StringUtil.isEmpty(vehicle.getMemberId())) {
//            return Result.failure("会员id为空").toJSON();
//        }
        if (StringUtil.isEmpty(vehicle.getCarPlate())) {
            return Result.failure("车牌号为空").toJSON();
        }
        if (StringUtil.isEmpty(vehicle.getBrand())) {
            return Result.failure("车辆品牌为空").toJSON();
        }
        if (StringUtil.isEmpty(vehicle.getSeries())) {
            return Result.failure("车系为空").toJSON();
        }
        if (StringUtil.isEmpty(vehicle.getFrameNumber())) {
            return Result.failure("车架号为空").toJSON();
        }
        if (StringUtil.isEmpty(vehicle.getEngineNumber())) {
            return Result.failure("发动机号为空").toJSON();
        }
//        if (StringUtil.isEmpty(vehicle.getCarVersion())) {
//            return Result.failure("车版本为空").toJSON();
//        }
        vehicle.setStatus(1);
        vehicle.setCreateTime(new Date());
        vehicle.setCreateBy(vehicle.getMemberId());
        return iVehicleFeignService.saveVehicle(vehicle);
    }

    @Override
    public String unbindVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            return Result.failure("参数为空").toJSON();
        }
        if (StringUtil.isEmpty(vehicleDTO.getVehicleId())) {
            return Result.failure("车辆Id为空").toJSON();
        }
        return iVehicleFeignService.unbindVehicle(vehicleDTO);
    }

    @Override
    public String getVehicleDetails(VehicleDTO vehicleDTO) {
        if(vehicleDTO == null){
            return Result.failure("参数为空").toJSON();
        }
        if(StringUtil.isEmpty(vehicleDTO.getVehicleId())){
            return Result.failure("车辆Id为空").toJSON();
        }
        return iVehicleFeignService.getVehicleDetails(vehicleDTO);
    }

    @Override
    public String updateVehicleData(Vehicle vehicle) {
        if(vehicle == null){
            return Result.failure("参数为空").toJSON();
        }
        if(StringUtil.isEmpty(vehicle.getId())){
            return Result.failure("车辆id为空").toJSON();
        }
        if(StringUtil.isEmpty(vehicle.getCarPlate())){
            return Result.failure("车牌号为空").toJSON();
        }
        if(StringUtil.isEmpty(vehicle.getBrand())){
            return Result.failure("车辆品牌为空").toJSON();
        }
        if(StringUtil.isEmpty(vehicle.getSeries())){
            return Result.failure("车系为空").toJSON();
        }
//        if(StringUtil.isEmpty(vehicle.getCarVersion())){
//            return Result.failure("车版本为空").toJSON();
//        }
        return iVehicleFeignService.updateVehicleData(vehicle);
    }

    @Override
    public String getVehicleBrandConfig() {
        Optional<Object> brand = Optional.ofNullable(RedisUtil.getRedisTemplate().opsForList().range(RedisConstant.CAR_BRAND_KEY,0,-1));
        Optional<Map<String, List<SeriesVO>>> seriesMap = Optional.ofNullable(RedisUtil.getRedisTemplate().opsForHash().entries(RedisConstant.CAR_SERIES_KEY));
        Map<String,Object> data = new HashMap<>();
        data.put("brand",brand.orElseGet(() -> iVehicleConfigFeignService.getBrandVOList()));
        data.put("series",seriesMap.orElseGet(() ->iVehicleConfigFeignService.getSeriesVOList()));
        return Result.success(data).toJSON();
    }

    @Override
    public String getVehicleModelBySeries(String series) {
        Optional<Object> model = Optional.ofNullable(RedisUtil.getRedisTemplate().opsForHash().get(RedisConstant.CAR_MODEL_KEY, series));
        return Result.success(model.orElseGet(() -> iVehicleConfigFeignService.getModelVOList(series))).toJSON();
    }
}
