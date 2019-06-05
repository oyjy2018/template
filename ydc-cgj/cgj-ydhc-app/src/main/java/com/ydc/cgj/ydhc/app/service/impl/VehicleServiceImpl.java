package com.ydc.cgj.ydhc.app.service.impl;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.ydhc.app.feignService.IVehicleService;
import com.ydc.cgj.ydhc.app.service.VehicleService;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import com.ydc.commom.view.vo.ydhc.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private IVehicleService iVehicleService;

    @Override
    public Result getVehicleList(VehicleVo vehicleVo) {
        return iVehicleService.getVehicleList(vehicleVo);
    }

    @Override
    public Result getVehicleDetail(Integer vehicleId) {
        return iVehicleService.getVehicleDetail(vehicleId);
    }

    @Override
    public String getVehicleBrandConfig() {
        Optional<Object> brand = Optional.ofNullable(RedisUtil.getRedisTemplate().opsForList().range(RedisConstant.CAR_BRAND_KEY,0,-1));
        Optional<Map<String, List<SeriesVO>>> seriesMap = Optional.ofNullable(RedisUtil.getRedisTemplate().opsForHash().entries(RedisConstant.CAR_SERIES_KEY));
        Map<String,Object> data = new HashMap<>();
        data.put("brand",brand.orElseGet(() -> iVehicleService.getBrandVOList()));
        data.put("series",seriesMap.orElseGet(() ->iVehicleService.getSeriesVOList()));
        return Result.success(data).toJSON();
    }

    @Override
    public String getVehicleModelBySeries(String series) {
        Optional<Object> model = Optional.ofNullable(RedisUtil.getRedisTemplate().opsForHash().get(RedisConstant.CAR_MODEL_KEY, series));
        return Result.success(model.orElseGet(() -> iVehicleService.getModelVOList(series))).toJSON();
    }
}
