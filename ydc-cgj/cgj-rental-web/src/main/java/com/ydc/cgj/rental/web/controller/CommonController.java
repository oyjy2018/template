package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.rental.web.service.CarService;
import com.ydc.cgj.rental.web.service.DictionaryDetailService;
import com.ydc.cgj.rental.web.service.RentalStoreService;
import com.ydc.cgj.rental.web.util.CommonUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.CommonReqDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 公共控制器（可以用于获取公共数据，或缓存数据）
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    private static final Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    DictionaryDetailService dictionaryDetailService;

    @Autowired
    CarService carService;

    @Autowired
    private RentalStoreService rentalStoreService;

    /**
     * 获取车辆启用状态
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCarUseStatus", method = RequestMethod.POST)
    public String getCarUseStatus(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionList(DictionaryConstant.DICT_CODE_USE_STATUS_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取车辆运营状态
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCarOperationStatus", method = RequestMethod.POST)
    public String getCarOperationStatus(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionList(DictionaryConstant.DICT_CODE_OPERATION_STATUS_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取车辆来源
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCarSource", method = RequestMethod.POST)
    public String getCarSource(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionList(DictionaryConstant.DICT_CODE_SOURCE_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取颜色
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getColor", method = RequestMethod.POST)
    public String getColor(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_COLOR_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取车结构 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCarType", method = RequestMethod.POST)
    public String getCarType(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_CAR_TYPE_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取变速箱类型 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getGearbox", method = RequestMethod.POST)
    public String getGearbox(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_GEARBOX_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取发动机类型 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getEngine", method = RequestMethod.POST)
    public String getEngine(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_ENGINE_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取排放标准类型 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getEmissionsStandard", method = RequestMethod.POST)
    public String getEmissionsStandard(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_EMISSIONS_STANDARD_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取能源类型 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getFuelType", method = RequestMethod.POST)
    public String getFuelType(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_FUEL_TYPE_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取汽油标号 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getGasolineTab", method = RequestMethod.POST)
    public String getGasolineTab(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_GASOLINE_TAB_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取座位数 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getSeatings", method = RequestMethod.POST)
    public String getSeatings(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_SEATINGS_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取驱动 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getDrive", method = RequestMethod.POST)
    public String getDrive(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_DRIVE_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取车等级 选值范围
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCarLevel", method = RequestMethod.POST)
    public String getCarLevel(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        return Result.success(CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_CAR_LEVEL_CONFIG, nuulOption, dictionaryDetailService)).toJSON();
    }

    /**
     * 获取新增车系车型相关下拉框选值
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCarSeriesOptionList", method = RequestMethod.POST)
    public String getCarSeriesOptionList(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}","获取新增车系车型相关下拉框选值",data);
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        Map<String, Object> jMap = new HashMap<>();
        //车结构
        jMap.put("carTypeList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_CAR_TYPE_CONFIG, nuulOption, dictionaryDetailService));
        //变速箱
        jMap.put("gearboxList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_GEARBOX_CONFIG, nuulOption, dictionaryDetailService));
        //发动机
        jMap.put("engineList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_ENGINE_CONFIG, nuulOption, dictionaryDetailService));
        //排放标准
        jMap.put("emissionsStandardList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_EMISSIONS_STANDARD_CONFIG, nuulOption, dictionaryDetailService));
        //燃料类型
        jMap.put("fuelTypeList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_FUEL_TYPE_CONFIG, nuulOption, dictionaryDetailService));
        //汽油标号
        jMap.put("gasolineTabList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_GASOLINE_TAB_CONFIG, nuulOption, dictionaryDetailService));
        //座位数
        jMap.put("seatingsList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_SEATINGS_CONFIG, nuulOption, dictionaryDetailService));
        //驱动类型
        jMap.put("driveList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_DRIVE_CONFIG, nuulOption, dictionaryDetailService));
        //座位材质
        jMap.put("seatTextureList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_SEAT_TEXTURE_CONFIG, nuulOption, dictionaryDetailService));
        //气囊
        jMap.put("gasbagList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_GASBAG_CONFIG, nuulOption, dictionaryDetailService));
        //音响
        jMap.put("soundBoxList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_SOUND_BOX_CONFIG, nuulOption, dictionaryDetailService));
        //获取车等级
        jMap.put("carLevelList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_CAR_LEVEL_CONFIG, nuulOption, dictionaryDetailService));

        return Result.success(jMap).toJSON();
    }

    /**
     * 获取新增车辆相关下拉框选值
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCarOptionList", method = RequestMethod.POST)
    public String getCarOptionList(@RequestParam("data") String data) {
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        Map<String, Object> jMap = new HashMap<>();
        //国别
        jMap.put("countryList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_COUNTRY_CONFIG, nuulOption, dictionaryDetailService));
        //颜色
        jMap.put("colorList", CommonUtil.getOptionListCH(DictionaryConstant.DICT_CODE_COLOR_CONFIG, nuulOption, dictionaryDetailService));
        //过户次数
        List transferTimesList = new ArrayList();
        for (int i = 0; i <= 15; i++) {
            HashMap hm = new HashMap();
            hm.put("dictKey",i);
            hm.put("dictValue",i);
            transferTimesList.add(hm);
        }
        jMap.put("transferTimesList", transferTimesList);
        //油表刻度
        List oilGaugeScaleList = new ArrayList();
        for (int i = 0; i < 17; i++) {
            HashMap hm = new HashMap();
            hm.put("dictKey",i);
            hm.put("dictValue",i+"/16");
            oilGaugeScaleList.add(hm);
        }
        jMap.put("oilGaugeScaleList", oilGaugeScaleList);
        return Result.success(jMap).toJSON();
    }


    /**
     * 获取 全部品牌
     *
     * @return
     */
    @RequestMapping(value = "/getAllBrand", method = RequestMethod.POST)
    public String getAllBrand(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        return carService.getBrandList(req);
    }

    /**
     * 获取 全部启用品牌
     *
     * @return
     */
    @RequestMapping(value = "/getAllEnableBrand", method = RequestMethod.POST)
    public String getAllEnableBrand() {
        return carService.getAllEnableBrand();
    }

    /**
     * 通过品牌查询车系
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getSeriesByBrand", method = RequestMethod.POST)
    public String getSeriesByBrand(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(req.get("brand"))) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆品牌").toJSON();
        }
        return carService.getSeriesList(req);
    }

    /**
     * 通过品牌查询启用车系
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getEnableSeriesByBrand", method = RequestMethod.POST)
    public String getEnableSeriesByBrand(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(req.get("brand"))) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆品牌").toJSON();
        }
        return carService.getEnableSeriesByBrand(req);
    }

    /**
     * 通过车系查询车版本
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getVersionBySeries", method = RequestMethod.POST)
    public String getVersionBySeries(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(req.get("series"))) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆车系").toJSON();
        }
        return carService.getModelList(req);
    }

    /**
     * 通过车系查询启用车型
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getEnableModelBySeries", method = RequestMethod.POST)
    public String getEnableModelBySeries(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(req.get("series"))) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆车系").toJSON();
        }
        return carService.getEnableModelBySeries(req);
    }

    /**
     * 获取 全部门店
     *
     * @return
     */
    @RequestMapping(value = "/getAllRentalStore", method = RequestMethod.POST)
    public String getAllRentalStore() {
        return rentalStoreService.getAllRentalStore();
    }


    /**
     * 获取押金结算类型
     *
     * @return
     */
    @RequestMapping(value = "/getDeposit", method = RequestMethod.POST)
    public String getDeposit() {
        List<Map<String, Object>> depositTypeList=new ArrayList<>();
        Map<String,Object> depositTypeMap=new HashMap<>();
        depositTypeMap.put("dictValue","租车");
        depositTypeMap.put("parentDictCode","depositType");
        depositTypeMap.put("dictKey",RentalDepositConstant.DEPOSIT_TYPE_1);
        Map<String,Object> depositTypeMap1=new HashMap<>();
        depositTypeMap1.put("dictValue","违章");
        depositTypeMap1.put("parentDictCode","depositType");
        depositTypeMap1.put("dictKey",RentalDepositConstant.DEPOSIT_TYPE_2);
        depositTypeList.add(depositTypeMap);
        depositTypeList.add(depositTypeMap1);

        List<Map<String, Object>> paymentStatusList=new ArrayList<>();
        Map<String,Object> paymentStatusMap=new HashMap<>();
        paymentStatusMap.put("dictValue","未授权");
        paymentStatusMap.put("parentDictCode","paymentStatus");
        paymentStatusMap.put("dictKey",RentalDepositConstant.PAYMENT_STATUS_1);
        Map<String,Object> paymentStatusMap1=new HashMap<>();
        paymentStatusMap1.put("dictValue","已授权");
        paymentStatusMap1.put("parentDictCode","paymentStatus");
        paymentStatusMap1.put("dictKey",RentalDepositConstant.DEPOSIT_TYPE_2);
        paymentStatusList.add(paymentStatusMap);
        paymentStatusList.add(paymentStatusMap1);

        List<Map<String, Object>> paymentModeList=new ArrayList<>();
        Map<String,Object> paymentModeMap2=new HashMap<>();
        paymentModeMap2.put("dictValue","信用卡");
        paymentModeMap2.put("parentDictCode","paymentMode");
        paymentModeMap2.put("dictKey",RentalDepositConstant.PAYMENT_MODE_2);
        Map<String,Object> paymentModeMap3=new HashMap<>();
        paymentModeMap3.put("dictValue","现金/转账");
        paymentModeMap3.put("parentDictCode","paymentMode");
        paymentModeMap3.put("dictKey",RentalDepositConstant.PAYMENT_MODE_3);
        paymentModeList.add(paymentModeMap2);
        paymentModeList.add(paymentModeMap3);


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("depositTypeList",depositTypeList);
        jsonObject.put("paymentStatusList",paymentStatusList);
        jsonObject.put("paymentModeList",paymentModeList);

        return Result.success(jsonObject).toJSON();
    }

}
