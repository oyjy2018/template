package com.ydc.service.car.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainDetailQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMainQueryVO;
import com.ydc.service.car.service.RentalCarMainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页-外部车辆服务
 */
@RestController
@RequestMapping(value = "/rentalCarMain")
public class RentalCarMainController {
    private static final Logger logger = LogManager.getLogger(RentalCarMainController.class);

    private final RentalCarMainService rentalCarMainService;

    @Autowired
    public RentalCarMainController(RentalCarMainService rentalCarMainService) {
        this.rentalCarMainService = rentalCarMainService;
    }

    /**
     * 获取主页内容
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getMainContent")
    public String getMainContent() throws Exception {
        JSONObject resultJSON = new JSONObject();
        List<String> realUrlList = rentalCarMainService.getMainContent();
        resultJSON.put("banners", realUrlList);
        return resultJSON.toJSONString();
    }

    /**
     * 查询品牌列表(已经存在车源的)
     */
    @PostMapping(value = "/getBrandList")
    public String getBrandList() {
        return Result.success(rentalCarMainService.getBrandList()).toJSON();
    }

    /**
     * 寻找车辆
     *
     * @param rentalCarMainQueryDTO
     * @return
     */
    @PostMapping(value = "/queryPublishCar")
    public String queryPublishCar(@RequestBody RentalCarMainQueryDTO rentalCarMainQueryDTO) {
        rentalCarMainQueryDTO.convertSQLForm();
        List<RentalCarMainQueryVO> carList = rentalCarMainService.queryPublishCar(rentalCarMainQueryDTO);
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("rows", carList);
        return Result.success(jMap).toJSON();
    }

    /**
     * 查询首页车辆详情
     *
     * @param rentalCarMainDetailQueryDTO
     * @return
     */
    @PostMapping(value = "/getCarDetail")
    public String getCarDetail(@RequestBody RentalCarMainDetailQueryDTO rentalCarMainDetailQueryDTO) throws Exception {
        return Result.success(rentalCarMainService.getCarDetail(rentalCarMainDetailQueryDTO)).toJSON();
    }

    /**
     * 获取所有城市(己发布+己出租)
     *
     * @return
     */
    @PostMapping("/getAllCities")
    public String getAllCities() {
        return Result.success(rentalCarMainService.getAllCities()).toJSON();
    }

    /**
     * 热门城市列表
     *
     * @return
     */
    @PostMapping("/getHotCitiesList")
    public String getHotCitiesList(@RequestParam("companyId") Integer companyId) {
        return Result.success(rentalCarMainService.getHotCitiesList(companyId)).toJSON();
    }
}
