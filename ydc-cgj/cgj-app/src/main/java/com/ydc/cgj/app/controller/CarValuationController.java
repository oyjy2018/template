package com.ydc.cgj.app.controller;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 车辆评估
 * @author hejiangping
 * @date 2019/1/7
 */
@RestController
@RequestMapping(value = "/carValuation")
public class CarValuationController {
    private final Logger logger = LogManager.getLogger(CarValuationController.class);

    /**
     * 获取城市列表
     * @author: hejiangping
     * @date: 2019/1/7
     */
    @PostMapping(value = "/getCityList")
    public String getCityList() {
        logger.info("subject:{}","获取城市列表");
        String result = null;
        try {
            result = UrlHttpUtil.doPost("http://"+SystemPropertiesConfig.BRIDGE_URL+"/carValuation/getCityList");
        } catch (Exception e) {
            logger.error("展示城市列表异常");
            return Result.failure("加载城市列表失败！").toJSON();
        }
        return result;
    }
    /**
     * 查询车型信息
     * @author: hejiangping
     * @date: 2019/1/7
     */
    @PostMapping(value = "/identifyModelByVIN")
    public String identifyModelByVIN(@RequestParam("data") String data) {
        logger.info("subject:{},param:{}","查询车型信息",data);
        String result = null;
        try {
            result = UrlHttpUtil.doPost("http://"+SystemPropertiesConfig.BRIDGE_URL+"/carValuation/identifyModelByVIN",data);
        } catch (Exception e) {
            logger.error("查询车型信息异常");
            return Result.failure("查询车型信息失败！").toJSON();
        }
        return result;
    }
    /**
     * 查询车辆估值
     * @author: hejiangping
     * @date: 2019/1/7
     */
    @PostMapping(value = "/getUsedCarPriceAnalysis")
    public String getUsedCarPriceAnalysis(@RequestParam("data") String data) {
        logger.info("subject:{},param:{}","查询车辆估值",data);
        String result = null;
        try {
            result = UrlHttpUtil.doPost("http://"+SystemPropertiesConfig.BRIDGE_URL+"/carValuation/getUsedCarPriceAnalysis",data);
        } catch (Exception e) {
            logger.error("查询车辆估值异常");
            return Result.failure("查询车辆估值失败！").toJSON();
        }
        return result;
    }
    /**
     * 行驶证识别
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @PostMapping(value = "/vehicleOcr")
    public String vehicleOcr(@RequestParam("data") String data) {
        logger.info("subject:{},param:{}","行驶证识别",data);
        String result = null;
        try {
            result = UrlHttpUtil.doPost("http://"+SystemPropertiesConfig.BRIDGE_URL+"/ocr/vehicle",data);
        } catch (Exception e) {
            logger.error("行驶证识别异常");
            return Result.failure("行驶证识别失败！").toJSON();
        }
        return result;
    }
}
