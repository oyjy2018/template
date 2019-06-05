package com.ydc.cgj.ydhc.web.controller;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.cgj.ydhc.web.feignService.VehicleUsedFeignService;
import com.ydc.cgj.ydhc.web.service.DictionaryDetailService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.MapUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.CommonReqDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    DictionaryDetailService dictionaryDetailService;
    @Autowired
    private VehicleUsedFeignService vehicleUsedFeignService;

    /**
     * 获取指定字典表数据
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getDictionaryDetail", method = RequestMethod.POST)
    public String getDictionaryDetail(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(crd.getParentDictCode())
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(crd.getParentDictCode())));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            if (nuulOption != null && nuulOption) {
                DictionaryDetail dd = new DictionaryDetail();
                dd.setDictKey("全部");
                dd.setDictValue("");
                dd.setParentDictCode(crd.getParentDictCode());
                dd.setSort(0);
                dictionaryDetailList.add(0, dd);
            }
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取颜色
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getColor", method = RequestMethod.POST)
    public String getColor(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_COLOR_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_COLOR_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_COLOR_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取车源类型
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getCarSource", method = RequestMethod.POST)
    public String getCarSource(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_CAR_SOURCE_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_CAR_SOURCE_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_CAR_SOURCE_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取供车商家
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getMerchant", method = RequestMethod.POST)
    public String getMerchant(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_MERCHANT_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_MERCHANT_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_MERCHANT_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取车辆品牌车系
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getCarBrand", method = RequestMethod.POST)
    public String getCarBrand(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_CAR_BRAND_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_CAR_BRAND_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_CAR_BRAND_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList, "remark1", "remark2", "remark3"));
            return Result.success(ddList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取车型 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getCarType", method = RequestMethod.POST)
    public String getCarType(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_CAR_TYPE_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_CAR_TYPE_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_CAR_TYPE_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取变速箱类型 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getGearbox", method = RequestMethod.POST)
    public String getGearbox(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_GEARBOX_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_GEARBOX_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_GEARBOX_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取发动机类型 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getEngine", method = RequestMethod.POST)
    public String getEngine(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_ENGINE_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_ENGINE_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_ENGINE_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取排放标准类型 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getEmissionsStandard", method = RequestMethod.POST)
    public String getEmissionsStandard(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_EMISSIONS_STANDARD_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_EMISSIONS_STANDARD_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_EMISSIONS_STANDARD_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取燃料类型 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getFuelType", method = RequestMethod.POST)
    public String getFuelType(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_FUEL_TYPE_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_FUEL_TYPE_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_FUEL_TYPE_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取座位数 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getSeatings", method = RequestMethod.POST)
    public String getSeatings(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_SEATINGS_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_SEATINGS_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_SEATINGS_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取厂商类型 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getManufacturerType", method = RequestMethod.POST)
    public String getManufacturerType(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_MANUFACTURER_TYPE_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_MANUFACTURER_TYPE_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_MANUFACTURER_TYPE_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取国别 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getCountry", method = RequestMethod.POST)
    public String getCountry(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_COUNTRY_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_COUNTRY_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_COUNTRY_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取驱动 选值范围
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getDrive", method = RequestMethod.POST)
    public String getDrive(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_DRIVE_CONFIG)
                .orElseGet(() ->dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_DRIVE_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nuulOption = Boolean.valueOf(crd.getNullOption());
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(DictionaryConstant.DICT_CODE_DRIVE_CONFIG));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }


    /**
     * 获取 全部品牌
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/getAllBrand", method = RequestMethod.POST)
    public String getAllBrand(@RequestBody Map<String,Object> req) {

        return vehicleUsedFeignService.getAllBrand(req);
    }

    /**
     * 通过品牌查询车系
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/getSeriesByBrand", method = RequestMethod.POST)
    public String getSeriesByBrand(@RequestBody Map<String,Object> req) {
        if (StringUtil.isEmpty(req.get("brand"))) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆品牌").toJSON();
        }

        return vehicleUsedFeignService.getSeriesByBrand(req);
    }

    /**
     * 通过车系查询车版本
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/getVersionBySeries", method = RequestMethod.POST)
    public String getVersionBySeries(@RequestBody Map<String,Object> req) {
        if (StringUtil.isEmpty(req.get("series"))) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆车系").toJSON();
        }
        return vehicleUsedFeignService.getVersionBySeries(req);
    }

}
