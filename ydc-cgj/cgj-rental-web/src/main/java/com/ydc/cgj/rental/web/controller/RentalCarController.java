package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.service.RentalCarService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 外部车辆
 */
@RestController
@RequestMapping(value = "/rentalCar")
public class RentalCarController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private RentalCarService rentalCarService;

    /**
     * 获取车辆列表查询条件
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getCondition")
    public String getCondition() {
        return rentalCarService.getCondition(WebShiroTokenManager.getUser().getId());
    }

    /**
     * 通过企业名称查询门店列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getStoreNameByCompanyName")
    public String getStoreNameByCompanyName(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String companyName = dataJSON.getString("companyName");
        if(StringUtil.isEmpty(companyName)) {
            return Result.failure("公司名称不能为空").toJSON();
        }
        return rentalCarService.getStoreNameByCompanyName(companyName);
    }

    /**
     * 根据车品牌查询车系
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getSeriesByBrand")
    public String getSeriesByBrand(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String brand = dataJSON.getString("brand");
        if (StringUtil.isEmpty(brand)) {
            return Result.failure("品牌不能为空").toJSON();
        }
        return rentalCarService.getSeriesByBrand(brand);
    }

    /**
     * 根据车系查询车型
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getModelBySeries")
    public String getCarModelBySeries(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String series = dataJSON.getString("series");
        if (StringUtil.isEmpty(series)) {
            return Result.failure("车系不能为空").toJSON();
        }
        return rentalCarService.getModelBySeries(series);
    }

    /**
     * 获取车辆列表
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:rentalCar:manage:car:view:query"})
    @PostMapping(value = "/getList")
    public String getList(@RequestParam("data") String data) {
        RentalCarQueryDTO rentalCarQueryDTO = JSONObject.parseObject(data, RentalCarQueryDTO.class);
        return rentalCarService.getList(rentalCarQueryDTO);
    }

    /**
     * 获取车辆详情
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:rentalCar:manage:car:view:detail"})
    @PostMapping(value = "/getDetail")
    public String getCarDetail(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer carId = dataJSON.getInteger("carId");
        return rentalCarService.getDetail(carId);
    }

    /**
     * 下架车辆
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:rentalCar:manage:car:view:remove"})
    @PostMapping(value = "/removeCar")
    public String removeCar(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer carId = dataJSON.getInteger("carId");
        if (carId == null) {
            return Result.failure("id缺失").toJSON();
        }
        return rentalCarService.removeCar(carId, WebShiroTokenManager.getUser().getId());
    }

}
