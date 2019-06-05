package com.ydc.service.car.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.*;
import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateOperationStatusDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateUseStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.CommCarQueryVO;
import com.ydc.commom.view.vo.cgj.rental.CommCarSeriesVO;
import com.ydc.commom.view.vo.cgj.rental.CommCarSimpleVO;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.car.CommCarSeries;
import com.ydc.service.car.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆服务
 */
@RestController
@RequestMapping(value = "/car")
public class CarController {
    private static final Logger logger = LogManager.getLogger(CarController.class);

    @Autowired
    private CarService carService;
    @Autowired
    private VehicleBrandService vehicleBrandService;
    @Autowired
    private VehicleSeriesService vehicleSeriesService;
    @Autowired
    private VehicleModelService vehicleModelService;
    @Autowired
    private CommCarSeriesService commCarSeriesService;

    /**
     * 修改或保存车辆信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/saveOrUpdateCar")
    public String saveOrUpdateCar(@RequestBody Map<String, Object> req) {
        logger.info("subject:{},req:{}","修改或保存车辆信息",JsonUtil.gsonStr(req));
        Map<String, Object> car = (Map<String, Object>) req.get("car");
        //车辆唯一属性验证
        //车牌
        Integer id = StringUtil.isEmpty(car.get("id"))?null:Integer.valueOf(car.get("id").toString());
        CommCar carByPlate = carService.selectByPlateOrVinOrEngineNo(car.get("carPlate").toString());
        if (carByPlate != null && car.get("carPlate").toString().equals(carByPlate.getCarPlate()) && carByPlate.getId() != id ) {
               return Result.failure("车牌号已存在").toJSON();
        }
        // 车架号
        CommCar carByVin = carService.selectByPlateOrVinOrEngineNo(car.get("vin").toString());
        if (carByVin != null && car.get("vin").toString().equals(carByVin.getVin()) && carByVin.getId() != id ) {
               return Result.failure("车架号已存在").toJSON();
        }
        // 发动机号
        CommCar carByEngineNo = carService.selectByPlateOrVinOrEngineNo(car.get("engineNo").toString());
        if (carByEngineNo != null && car.get("engineNo").toString().equals(carByEngineNo.getEngineNo()) && carByEngineNo.getId() != id ) {
               return Result.failure("发动机号已存在").toJSON();
        }
        CommCarSeries commCarSeries = commCarSeriesService.selectByPrimaryKey(Integer.valueOf(car.get("modelId").toString()));
        //判断油量是否大于油箱容量
        if( commCarSeries.getTankVolume() < Double.parseDouble(car.get("oilMass").toString())){
            return Result.failure("油量不能大于车型油箱容量").toJSON();
        }
        Map<String,Object> commCarSeriesMap = JsonUtil.jsonToMap(JsonUtil.jsonStr(commCarSeries));
        commCarSeriesMap.remove("id");//create_time, create_by, update_time, update_by
        commCarSeriesMap.remove("createTime");
        commCarSeriesMap.remove("createBy");
        commCarSeriesMap.remove("updateTime");
        commCarSeriesMap.remove("updateBy");
        car.putAll(commCarSeriesMap);
        req.put("car", car);
        carService.saveOrUpdateCar(req);
        return Result.success("保存成功").toJSON();
    }

    public static void main(String[] args) {

    }

    /**
     * 查询车辆列表
     *
     * @param commCarQueryDTO
     * @return
     */
    @PostMapping(value = "/getCarList")
    public String getCarList(@RequestBody CommCarQueryDTO commCarQueryDTO) {
        //SystemUtil.getRequestQueryLimit(req);
        List<CommCarQueryVO> carList = carService.getCarList(commCarQueryDTO);
        //Map<String, Object> retCount = carService.getCarListCount(req);
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
    @PostMapping(value = "/getCarInfo")
    public String getCarInfo(@RequestParam("id") Integer id) {
        Map<String, Object> car = carService.getCarInfo(id);
        return Result.success(car).toJSON();
    }

    /**
     * 查询车辆少量信息
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getCarInfoSimple")
    public String getCarInfoSimple(@RequestParam("id") Integer id) {
        Map<String,Object> retMap = new HashMap<>();
        CommCarSimpleVO commCarSimpleVO = carService.getCarInfoSimple(id);
        retMap.put("car",commCarSimpleVO);
        Result result = Result.success();
        result.setData(retMap);
        return result.toJSON();
    }

    /**
     * 启用禁用车辆
     *
     * @param commCarUpdateUseStatusDTO
     * @return
     */
    @PostMapping(value = "/updateCarUseStatusById")
    public String updateCarUseStatusById(@RequestBody CommCarUpdateUseStatusDTO commCarUpdateUseStatusDTO) {
        return carService.updateCarUseStatusById(commCarUpdateUseStatusDTO);
    }


    /**
     * 查询车辆品牌
     *
     * @return
     */
    @PostMapping(value = "/getBrandList")
    public String getBrandList(@RequestBody Map<String, Object> req) {
        List<Map<String, Object>> brandList = vehicleBrandService.getAllBrandCH();
        if(req.containsKey("nullOption") && req.get("nullOption") != null && !"".equals(req.get("nullOption").toString())){
            Boolean nullOption = Boolean.valueOf(req.get("nullOption").toString());
            if(nullOption){
                brandList.add(0,MapUtil.getAllOption(null));
            }
        }
        return Result.success(brandList).toJSON();
    }

    /**
     * 获取 全部启用品牌
     *
     * @return
     */
    @PostMapping(value = "/getAllEnableBrand")
    public String getAllEnableBrand() {
        return commCarSeriesService.getAllEnableBrand();
    }

    /**
     * 根据车品牌查车系
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getSeriesList")
    public String getSeriesList(@RequestBody Map<String, Object> req) {
        List<Map<String, Object>> seriesList = vehicleSeriesService.getSeriesByBrandCH(req.get("brand").toString());
        if(req.containsKey("nullOption") && req.get("nullOption") != null && !"".equals(req.get("nullOption").toString())){
            Boolean nullOption = Boolean.valueOf(req.get("nullOption").toString());
            if(nullOption){
                seriesList.add(0,MapUtil.getAllOption(null));
            }
        }
        return Result.success(seriesList).toJSON();
    }

    /**
     * 通过品牌查询启用车系
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getEnableSeriesByBrand")
    public String getEnableSeriesByBrand(@RequestBody Map<String, Object> req) {
        return commCarSeriesService.getEnableSeriesByBrand(req);
    }

    /**
     * 根据车系查询车型
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getModelList")
    public String getModelList(@RequestBody Map<String, Object> req) {
        List<Map<String, Object>> modelList = vehicleModelService.getVersionBySeriesCH(req.get("series").toString());
        Boolean notHaveOption = StringUtil.objToBoolean(req.get("notHaveOption"));
        if(notHaveOption != null && notHaveOption){
            modelList.add(0, MapUtil.getNotHaveOption(null));
        }
        Boolean nullOption = StringUtil.objToBoolean(req.get("nullOption"));
        if(nullOption != null && nullOption){
            modelList.add(0, MapUtil.getAllOption(null));
        }
        return Result.success(modelList).toJSON();
    }

    /**
     * 通过车系查询启用车型
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getEnableModelBySeries")
    public String getEnableModelBySeries(@RequestBody Map<String, Object> req) {
        return commCarSeriesService.getEnableModelBySeries(req);
    }

    /**
     * 查询车系车型详情
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getCarSeriesDetails")
    public String getCarSeriesDetails(@RequestBody Map<String, Object> req) {
        Integer commCarSeriesId = Integer.valueOf(req.get("commCarSeriesId").toString());
        CommCarSeries ccs = commCarSeriesService.selectByPrimaryKey(commCarSeriesId);
        return Result.success(ccs).toJSON();
    }

    /**
     * 保存或修改车系车型
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/saveOrUpdateCarSeries")
    public String saveOrUpdateCarSeries(@RequestBody Map<String, Object> req) {
        Map<String, String> commCarSeries = (Map<String, String>)req.get("commCarSeries");
        Map<String, Object> param = new HashMap<>();
        param.put("brand", commCarSeries.get("brand"));
        param.put("series", commCarSeries.get("series"));
        param.put("model", commCarSeries.get("model"));
        param.put("carLevel", commCarSeries.get("carLevel"));
        List<CommCarSeries> ccsList = commCarSeriesService.getCarSeriesByParam(param);
        if(ccsList != null && ccsList.size() > 0){
            Integer id = StringUtil.retInt(commCarSeries.get("id"));
            if(id == null || id == 0) return Result.failure("已存在同品牌、车系、车型、车等级的数据，请勿重复提交").toJSON();
            for(CommCarSeries ccs: ccsList){
                if(ccs.getId().intValue() != id.intValue()) return Result.failure("已存在同品牌、车系、车型、车等级的数据，请勿重复提交").toJSON();
            }
        }

        commCarSeriesService.saveOrUpdate(req);
        return Result.success("保存成功").toJSON();
    }

    /**
     * 查询车系车型列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getCommCarSeriesList")
    public String getCommCarSeriesList(@RequestBody Map<String, Object> req) {
        SystemUtil.getRequestQueryLimit(req);
        List<CommCarSeriesVO> ret = commCarSeriesService.getCommCarSeriesList(req);
        Map<String, Object> retCount = commCarSeriesService.getCommCarSeriesCount(req);
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("totalCount", retCount.get("cnt"));
        jMap.put("rows", ret);
        return Result.success(jMap).toJSON();
    }

    /**
     * 启用/禁用车系车型
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/updateHasEnabled")
    public String updateHasEnabled(@RequestBody Map<String, Object> req) {
        Integer commCarSeriesId = Integer.valueOf(req.get("commCarSeriesId").toString());
        Integer hasEnabled = Integer.valueOf(req.get("hasEnabled").toString());
        Integer userId = Integer.valueOf(req.get("userId").toString());
        int i = commCarSeriesService.updateHasEnabledById(commCarSeriesId, hasEnabled, userId);
        if (i > 0) {
            return Result.success(hasEnabled == 1 ? "启用成功" : "禁用成功").toJSON();
        }
        return Result.failure(hasEnabled == 1 ? "启用失败" : "禁用失败").toJSON();
    }

    /**
     * 修改运营状态
     *
     * @param commCarUpdateOperationStatusDTO
     * @return
     */
    @PostMapping(value = "/updateCarOperationStatusById")
    public String updateCarOperationStatusById(@RequestBody CommCarUpdateOperationStatusDTO commCarUpdateOperationStatusDTO) {
        return carService.updateCarOperationStatusById(commCarUpdateOperationStatusDTO);
    }

    /**
     * 通过车型id获取邮箱容量
     * @param modelId
     * @return
     */
    @PostMapping(value = "/getTankVolumeByModelId")
    String getTankVolumeByModelId(@RequestParam("modelId") Integer modelId){
        logger.info("subject:{},modelId:{}","通过车型id获取邮箱容量",modelId);
        return commCarSeriesService.getTankVolumeByModelId(modelId);
    }
}
