package com.ydc.cgj.bridge.controller;

import com.ydc.cgj.bridge.service.CarService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 车服务
 */
@RestController
@RequestMapping(value = "/car")
public class CarController {
    private final Logger logger = LogManager.getLogger(CarController.class);

    @Autowired
    private CarService carService;


    /**
     * 获取车辆列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarList")
    public String getCarList(@RequestParam("data") String data) {
        CommCarQueryDTO commCarQueryDTO = JsonUtil.jsonToBean(data,CommCarQueryDTO.class);
        return carService.getCarList(commCarQueryDTO);
    }

    /**
     * 获取车辆详情
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarInfo")
    public String getCarInfo(@RequestParam(value = "data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);

        if (StringUtil.isEmpty(req.get("id"))) {
            return Result.failure("id缺失").toJSON();
        }
        return carService.getCarInfo(Integer.valueOf(req.get("id").toString()));
    }

    /**
     * 获取车辆少量信息
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarInfoSimple")
    public String getCarInfoSimple(@RequestParam(value = "data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);

        if (StringUtil.isEmpty(req.get("carId"))) {
            return Result.failure("carId缺失").toJSON();
        }
        return carService.getCarInfoSimple(Integer.valueOf(req.get("carId").toString()));
    }

    /**
     * 查询车系车型详情
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarSeriesDetails")
    public String getCarSeriesDetails(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        return carService.getCarSeriesDetails(req);
    }

    /**
     * 查询车系车型列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCommCarSeriesList")
    public String getCommCarSeriesList(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        return carService.getCommCarSeriesList(req);
    }

}
