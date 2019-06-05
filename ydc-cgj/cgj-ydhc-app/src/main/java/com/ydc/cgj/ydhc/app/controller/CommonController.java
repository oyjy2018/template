package com.ydc.cgj.ydhc.app.controller;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.ydhc.app.service.VehicleService;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.car.BrandVO;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 公共控制器（可以用于获取公共数据，或缓存数据）
 */
@RestController
@RequestMapping("/ydhcCommon")
public class CommonController {

    @Autowired
    VehicleService vehicleService;

    /**
     * 获取车辆品牌（包含车系，车型）
     * @return
     */
    @RequestMapping(value = "/getCarBrand", method = RequestMethod.POST)
    public String getCarBrand(){
        return vehicleService.getVehicleBrandConfig();
    }

}
