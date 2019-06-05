package com.ydc.cgj.bridge.controller;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.bridge.interfaces.CheThreeHundred;
import com.ydc.cgj.bridge.service.DictionaryDetailService;
import com.ydc.commom.constant.LoanApplyConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.NumberUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 车辆估值
 * @author hejiangping
 * @date 2019/1/7
 */
@RestController
@RequestMapping(value = "/carValuation")
public class CarValuationController {
    private final Logger logger = LogManager.getLogger(CarValuationController.class);
    @Autowired
    DictionaryDetailService dictionaryDetailService;

    /**
     * 获取城市列表
     * @author: hejiangping
     * @date: 2019/1/7
     */
    @PostMapping(value = "/getCityList")
    public String getCityList() {
        List<Map<String, Object>> cityList = new ArrayList<>();
        try {
            Object obj = RedisUtil.redisGet(RedisConstant.CHE300_CITY_LIST);
            if (obj == null) {
                cityList = CheThreeHundred.getAllCity();
                RedisUtil.redisSet(RedisConstant.CHE300_CITY_LIST,cityList,null);
            }else{
                cityList = (List<Map<String, Object>>) obj;
            }
            List<Map<String, Object>> citys = new ArrayList<>();
            Map<String, Object> map = null;
            if (!cityList.isEmpty() && cityList.size() > 0) {
                for (Map<String, Object> m : cityList) {
                    map = new HashMap<>();
                    map.put("city_id", m.get("city_id"));
                    map.put("city_name", m.get("city_name"));
                    citys.add(map);
                }
                map = new HashMap<>();
                map.put("city_id", "-1");
                map.put("city_name","请选择");
                citys.add(0, map);
            }
            //String json = JsonUtil.jsonStr(citys);
            return Result.success(citys).toJSON();
        } catch (Exception e) {
            logger.error("展示城市列表异常");
            return Result.failure("加载城市列表失败！").toJSON();
        }
    }
    /**
     * 根据车架号查询车型信息
     * @author: hejiangping
     * @date: 2019/1/7
     */
    @PostMapping(value = "/identifyModelByVIN")
    public String identifyModelByVIN(@RequestBody String data) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(data);
            if (StringUtil.isEmpty(dataMap.get("vehicleFrameNumber"))) {
                return Result.failure("车架号不能为空").toJSON();
            }
            Map<String, Object> resMap = CheThreeHundred.identifyModelByVIN(dataMap.get("vehicleFrameNumber").toString());
            if (resMap != null && !resMap.isEmpty()) {
                if (resMap.containsKey("error_msg")) {
                    return Result.failure(resMap.get("error_msg").toString()).toJSON();
                }
                List<Map<String, Object>> modelInfo = JsonUtil.jsonToListMap(resMap.get("modelInfo").toString());
                return Result.success(modelInfo).toJSON();
            }
            return Result.failure("查询车型信息失败").toJSON();
        } catch (Exception e) {
            logger.error("查询车型信息异常");
            return Result.failure("查询车型信息异常").toJSON();
        }
    }
    /**
     * 查询车辆估值
     * @author: hejiangping
     * @date: 2019/1/7
     */
    @PostMapping(value = "/getUsedCarPriceAnalysis")
    public String getUsedCarPriceAnalysis(@RequestBody String data) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(data);
            Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode("che300")
                    .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode("che300")));
            Map<String, Object> resMap = CheThreeHundred.getUsedCarPriceAnalysis(dataMap,optional.get());
            if (resMap != null && !resMap.isEmpty()) {
                if (resMap.containsKey("error_msg")) {
                    return Result.failure(resMap.get("error_msg").toString()).toJSON();
                }
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("b2bPrice", NumberUtil.numberFormat(BigDecimal.valueOf(Double.valueOf(resMap.get("b2b_price").toString())).multiply(BigDecimal.valueOf(10000)).doubleValue(), NumberUtil.moneyFormat3));
                // 车辆估价使用数量存到缓存
                Integer applyNum = (Integer) RedisUtil.redisGet(RedisConstant.CGJ_APPLY_NUM + LoanApplyConstant.VALUATION_CAR);
                if(applyNum == null){
                    applyNum = 0;
                }
                applyNum = applyNum + 1;
                RedisUtil.redisSet(RedisConstant.CGJ_APPLY_NUM + LoanApplyConstant.VALUATION_CAR,applyNum,null);
                Integer num = (Integer) RedisUtil.redisGet(RedisConstant.CGJ_APPLY_SHOW_NUM + LoanApplyConstant.VALUATION_CAR);
                if(num == null){
                    num = 1000;
                }
                num = num + 1;
                // 虚拟值
                RedisUtil.redisSet(RedisConstant.CGJ_APPLY_SHOW_NUM + LoanApplyConstant.VALUATION_CAR,num,null);
                return Result.success(resultMap).toJSON();
            }
            return Result.failure("查询车辆估值失败").toJSON();
        } catch (Exception e) {
            logger.error("查询车辆估值异常");
            return Result.failure("查询车辆估值异常").toJSON();
        }
    }
}
