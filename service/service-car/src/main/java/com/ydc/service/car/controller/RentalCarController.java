package com.ydc.service.car.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.service.car.service.RentalCarMainService;
import com.ydc.service.car.service.RentalCarPublishService;
import com.ydc.service.car.service.RentalCarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外部车辆服务
 */
@RestController
@RequestMapping(value = "/rentalCar")
public class RentalCarController {
    private static final Logger logger = LogManager.getLogger(RentalCarController.class);

    private final RentalCarService rentalCarService;
    private final RentalCarPublishService rentalCarPublishService;
    private final RentalCarMainService rentalCarMainService;

    @Autowired
    public RentalCarController(RentalCarService rentalCarService, RentalCarPublishService rentalCarPublishService, RentalCarMainService rentalCarMainService) {
        this.rentalCarService = rentalCarService;
        this.rentalCarPublishService = rentalCarPublishService;
        this.rentalCarMainService = rentalCarMainService;
    }

    /**
     * 查询外部车辆列表查询条件
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getCondition")
    public String getCondition() {
        return Result.success(rentalCarService.getCondition()).toJSON();
    }

    /**
     * 通过企业名称查询门店列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getStoreNameByCompanyName")
    public String getStoreNameByCompanyName(@RequestParam("companyName") String companyName) {
        return Result.success(rentalCarService.getStoreNameByCompanyName(companyName)).toJSON();
    }

    /**
     * 根据品牌查询车系
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getSeriesByBrand")
    public String getSeriesByBrand(@RequestParam("brand") String brand) {
        logger.info("brand: " + brand);
        return Result.success(rentalCarService.getSeriesByBrand(brand)).toJSON();
    }

    /**
     * 根据车系查询车型
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getModelBySeries")
    public String getModelBySeries(@RequestParam("series") String series) {
        return Result.success(rentalCarService.getModelBySeries(series)).toJSON();
    }

    /**
     * 查询外部车辆列表
     *
     * @param rentalCarQueryDTO
     * @return
     */
    @PostMapping(value = "/getList")
    public String getList(@RequestBody RentalCarQueryDTO rentalCarQueryDTO) {
        logger.info("getList: {}", rentalCarQueryDTO);
        List<RentalCarQueryVO> carList = rentalCarService.getList(rentalCarQueryDTO);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("totalCount", PaginationUtil.pageTotalQuery(carList));
        retMap.put("rows", carList);
        return Result.success(retMap).toJSON();
    }

    /**
     * 查询车辆详情
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getDetail")
    public String getDetail(@RequestParam("carId") Integer id) throws Exception {
        RentalCarQueryDetailVO rentalCarQueryDetailVO = rentalCarService.getDetail(id);
        return Result.success(rentalCarQueryDetailVO).toJSON();
    }

    /**
     * 下架车辆
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/removeCar")
    public String removeRentalCar(@RequestParam("data") String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        Integer userId = jsonObject.getInteger("userId");
        JSONArray carIds = jsonObject.getJSONArray("carIds");
        return Result.success(rentalCarService.removeRentalCar(carIds, userId)).toJSON();
    }

}
