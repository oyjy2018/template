package com.ydc.cgj.rental.company.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.rental.company.app.service.MainService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainDetailQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainQueryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 首页
 */
@RestController
@RequestMapping(value = "/main")
public class MainController extends BaseController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private MainService mainService;

    /**
     * 获取主页内容
     *
     * @return
     */
    @PostMapping(value = "/getMainContent")
    public String getMainContent() {
        return mainService.getMainContent();
    }

    /**
     * 寻找车辆
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/queryPublishCar")
    public String queryPublishCar(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        String startTime = dataJSON.getString("startTime");
        if (StringUtil.isEmpty(startTime)) {
            return Result.failure("开始时间不能为空").toJSON();
        }
        String endTime = dataJSON.getString("endTime");
        if (StringUtil.isEmpty(endTime)) {
            return Result.failure("结束时间不能为空").toJSON();
        }
        String city = dataJSON.getString("city");
        if (StringUtil.isEmpty(city)) {
            return Result.failure("城市不能为空").toJSON();
        }
        RentalCarMainQueryDTO rentalCarMainQueryDTO = JSONObject.parseObject(data, RentalCarMainQueryDTO.class);
        Integer companyId = super.getUser() != null ? super.getUser().getId() : 0;
        rentalCarMainQueryDTO.setCompanyId(companyId);
        return mainService.queryPublishCar(rentalCarMainQueryDTO);
    }

    /**
     * 查询车辆详情
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarDetail")
    public String getCarDetail(@RequestParam("data") String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer publishId = dataJSON.getInteger("publishId");
        String startTime = dataJSON.getString("startTime");
        String endTime = dataJSON.getString("endTime");
        String carLevel = dataJSON.getString("carLevel");
        Integer seriesId = dataJSON.getInteger("seriesId");
        if (startTime == null) {
            return Result.failure("发布id不能为空").toJSON();
        }
        if (StringUtil.isEmpty(startTime)) {
            return Result.failure("开始时间不能为空").toJSON();
        }
        if (StringUtil.isEmpty(endTime)) {
            return Result.failure("结束时间不能为空").toJSON();
        }
        if (StringUtil.isEmpty(carLevel)) {
            return Result.failure("车等级不能为空").toJSON();
        }
        if (seriesId == null) {
            return Result.failure("车系id不能为空").toJSON();
        }
        RentalCarMainDetailQueryDTO  rentalCarMainDetailQueryDTO = JSONObject.parseObject(data, RentalCarMainDetailQueryDTO.class);
        return mainService.getCarDetail(rentalCarMainDetailQueryDTO);
    }

    /**
     * 筛选-品牌类型
     *
     * @return
     */
    @PostMapping(value = "/getBrandList")
    public String getBrandList() {
        return mainService.getBrandList();
    }

    /**
     * 获取所有己城市(己发布+己出租)
     * @return
     */
    @PostMapping(value = "/getAllCities")
    public String getAllCities() {
        return mainService.getAllCities();
    }

    /**
     * 热门城市列表
     * @return
     */
    @PostMapping(value = "/getHotCitiesList")
    public String getHotCitiesList() {
        Integer companyId = super.getUser() == null ? 0 : super.getUser().getId();
        return mainService.getHotCitiesList(companyId);
    }
}
