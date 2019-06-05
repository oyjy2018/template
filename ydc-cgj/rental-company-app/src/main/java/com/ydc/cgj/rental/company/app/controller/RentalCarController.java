package com.ydc.cgj.rental.company.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.rental.company.app.service.RentalCarService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMyListDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的外部车辆
 */
@RestController
@RequestMapping(value = "/rentalCar")
public class RentalCarController extends BaseController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private RentalCarService rentalCarService;

    /**
     * 获取我的车辆
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getMy")
    public String getMy(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer status = dataJSON.getInteger("status");
        RentalCarListDTO rentalCarListDTO = JSONObject.parseObject(data, RentalCarListDTO.class);
        rentalCarListDTO.setUserId(super.getUser().getId());
        return rentalCarService.getMy(rentalCarListDTO);
    }

    /**
     * 获取我的车辆列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getMyList")
    public String getMyList(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String storeName = dataJSON.getString("storeName");
        String model = dataJSON.getString("model");
        if (StringUtils.isEmpty(storeName) || StringUtil.isEmpty(model)) {
            return Result.failure("门店名称和车型不能为空").toJSON();
        }
        if (StringUtil.isEmpty(dataJSON.getString("carLevel"))) {
            return Result.failure("车等级不能为空").toJSON();
        }
        RentalCarMyListDTO rentalCarMyListDTO = JsonUtil.jsonToBean(data, RentalCarMyListDTO.class);
        rentalCarMyListDTO.setCompanyId(super.getUser().getId());
        return rentalCarService.getMyList(rentalCarMyListDTO);
    }

    /**
     * 获取我的车辆详情
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getMyCarDetail")
    public String getMyCarDetail(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer carId = dataJSON.getInteger("carId");
        if (carId == null) {
            return Result.failure("车辆id不能为空").toJSON();
        }
        return rentalCarService.getMyCarDetail(carId);
    }

    /**
     * 获取我的车辆详情
     *
     * @param
     * @return
     */
    @PostMapping(value = "/deleteMyCar")
    public String deleteMyCar(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer carId = dataJSON.getInteger("carId");
        if (carId == null) {
            return Result.failure("车辆id不能为空").toJSON();
        }
        return rentalCarService.deleteMyCar(carId, super.getUser().getId());
    }

    /**
     * 新增车辆
     *
     * @param
     * @return
     */
    @PostMapping(value = "/addMyCar")
    public String addMyCar(@RequestParam("data") String data) {
        String checkMsg = rentalCarService.checkData(data);
        if (StringUtil.isEmpty(checkMsg)) {
            return rentalCarService.addMyCar(data, super.getUser().getId());
        } else {
            return Result.failure(checkMsg).toJSON();
        }
    }

    /**
     * 根据状态查询车辆列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getCarListByStatus")
    public String getCarListByStatus(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer status = dataJSON.getInteger("status");
        if (status == null) {
            return Result.failure("车辆状态不能为空").toJSON();
        }
        return rentalCarService.getCarListByStatus(super.getUser().getId(), status);
    }

    /**
     * 发布车辆
     *
     * @param
     * @return
     */
    @PostMapping(value = "/publish")
    public String publish(@RequestParam("data") String data) {
        String checkMsg = rentalCarService.checkPublishData(data);
        if (StringUtil.isEmpty(checkMsg)) {
            return rentalCarService.publish(data, super.getUser().getId());
        } else {
            return Result.failure(checkMsg).toJSON();
        }
    }

    @PostMapping(value = "/removeCar")
    public String removeCar(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        dataJSON.put("userId", super.getUser().getId());
        JSONArray carIdArray = dataJSON.getJSONArray("carIds");
        if (carIdArray == null || carIdArray.size() == 0) {
            return Result.failure("车辆id不能为空").toJSON();
        }
        return rentalCarService.removeCar(dataJSON.toJSONString());
    }

    /**
     * 新增车辆-获取品牌列表
     *
     * @return
     */
    @PostMapping("/getAddBrandList")
    public String getAddBrandList() {
        return rentalCarService.getAddBrandList();
    }

    /**
     * 新增车辆-通过品牌查询车系
     *
     * @return
     */
    @PostMapping("/getAddSeriesByBrand")
    public String getSeriesByBrand(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String brand = dataJSON.getString("brand");
        if (StringUtil.isEmpty(brand)) {
            return Result.failure("品牌不能为空").toJSON();
        }
        return rentalCarService.getAddSeriesByBrand(brand);
    }

    /**
     * 新增车辆-通过车系查询车型
     *
     * @return
     */
    @PostMapping("/getAddModelBySeries")
    public String getAddModelBySeries(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String series = dataJSON.getString("series");
        if (StringUtil.isEmpty(series)) {
            return Result.failure("车系不能为空").toJSON();
        }
        return rentalCarService.getAddModelBySeries(series);
    }

}
