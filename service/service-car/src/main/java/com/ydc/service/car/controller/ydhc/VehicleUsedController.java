package com.ydc.service.car.controller.ydhc;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.model.ydhc.YdhcVehicleUsed;
import com.ydc.model.ydhc.YdhcVehicleUsedImg;
import com.ydc.service.car.service.ydhc.VehicleUsedImgService;
import com.ydc.service.car.service.ydhc.VehicleUsedService;
import com.ydc.service.car.service.ydhc.YdhcVehicleBrandService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/vehicleUsed")
public class VehicleUsedController {

    private static Logger logger = LogManager.getLogger(VehicleUsedController.class);

    @Autowired
    private VehicleUsedService vehicleUsedService;
    @Autowired
    private VehicleUsedImgService vehicleUsedImgService;
    @Autowired
    private YdhcVehicleBrandService ydhcVehicleBrandService;


    /**
     * 修改或保存二手车信息
     * @param req
     * @return
     */
    @PostMapping(value = "/saveOrUpdateVehicleUsed")
    public String saveOrUpdateVehicleUsed(@RequestBody Map<String, Object> req){
        Map<String, Object> ydhcVehicleUsedMap = (Map<String, Object>)req.get("ydhcVehicleUsed");
        YdhcVehicleUsed existYdhcVehicle = vehicleUsedService.selectByTitle(ydhcVehicleUsedMap.get("title").toString());
        logger.info("existYdhcVehicle is null = "+ (existYdhcVehicle == null));
        if(existYdhcVehicle != null){
            Integer vehicleId = null;
            if(ydhcVehicleUsedMap.get("id") == null || "".equals(ydhcVehicleUsedMap.get("id"))){
                vehicleId = 0;
            }else{
                vehicleId = Integer.valueOf(ydhcVehicleUsedMap.get("id").toString());
            }
            if(vehicleId.intValue() != existYdhcVehicle.getId().intValue())
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "车辆标题已存在").toJSON();
        }
        vehicleUsedService.saveOrUpdateVehicleUsed(req);
        return Result.success("保存成功").toJSON();
    }

    /**
     * 更新二手车发布状态
     * @param req
     * @return
     */
    @PostMapping(value = "/updateReleaseStatus")
    public String updateReleaseStatus(@RequestBody Map<String, String> req){
        vehicleUsedService.updateReleaseStatusByIds(req);
        return Result.success("更新发布状态成功").toJSON();
    }

    /**
     * 获取二手车信息
     * @param req
     * @return
     */
    @PostMapping(value = "/getVehicleUsedInfo")
    public String getVehicleUsedInfo(@RequestBody Map<String, String> req){
        YdhcVehicleUsed vehicle = vehicleUsedService.selectByPrimaryKey(Integer.valueOf(req.get("id")));
        if(vehicle == null){
            return Result.failure("车辆信息不存在或丢失").toJSON();
        }
        vehicle.setPrice(vehicle.getPrice().divide(BigDecimal.valueOf(10000), BigDecimal.ROUND_HALF_UP, 2));
        vehicle.setNewCarPrice(vehicle.getNewCarPrice().divide(BigDecimal.valueOf(10000), BigDecimal.ROUND_HALF_UP, 2));

        List<YdhcVehicleUsedImg> vehicleUsedImgList = vehicleUsedImgService.selectByVehicleId(vehicle.getId());
        for(YdhcVehicleUsedImg vehicleImg:vehicleUsedImgList){
            try{
                vehicleImg.setViewFileUrl(FileUtil.getBrowseFile(vehicleImg.getFileUrl(),vehicleImg.getFileName()));
                vehicleImg.setViewLittleFileUrl(FileUtil.getBrowseFile(vehicleImg.getLittleFileUrl(),vehicleImg.getLittleFileName()));
            }catch (Exception e){
                logger.info("加密图片路径异常",e);
            }
        }
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("vehicleUsed",vehicle);
        jMap.put("vehicleUsedImgList",vehicleUsedImgList);
        return Result.success(jMap).toJSON();
    }

    /**
     * 查询二手车列表信息
     * @param req
     * @return
     */
    @PostMapping(value = "/getYdhcVehicleUsedList")
    public String getYdhcVehicleUsedList(@RequestBody Map<String, Object> req){
        SystemUtil.getRequestQueryLimit(req);
        List<Map<String, Object>> ret = vehicleUsedService.getYdhcVehicleUsedList(req);
        Map<String, Object> retCount = vehicleUsedService.getYdhcVehicleUsedListCount(req);
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("totalCount", retCount.get("total_count"));
        jMap.put("rows",ret);
        return Result.success(jMap).toJSON();
    }

    /**
     * 查询全部汽车品牌 不分页
     * @return
     */
    @PostMapping(value = "/getAllBrand")
    public String getAllBrand(){
        return Result.success(ydhcVehicleBrandService.getAllBrand()).toJSON();
    }

    /**
     * 通过品牌查询车系
     * @return
     */
    @PostMapping(value = "/getSeriesByBrand")
    public String getSeriesByBrand(@RequestBody Map<String,Object> req){
        return Result.success(ydhcVehicleBrandService.getSeriesByBrand(req)).toJSON();
    }

    /**
     * 通过车系查询车版本
     * @return
     */
    @PostMapping(value = "/getVersionBySeries")
    public String getVersionBySeries(@RequestBody Map<String,Object> req){
        return Result.success(ydhcVehicleBrandService.getVersionBySeries(req)).toJSON();
    }

/*    @PostMapping(value = "/deleteVehicleImg")
    public String deleteVehicleImg(@RequestBody Map<String, String> req){
        int count = vehicleImgService.deleteById(Integer.valueOf(req.get("vehicleImgId")));
        return Result.success("图片删除成功").toJSON();
    }*/

    /**
     * 获取二手车列表
     * @param req
     * @return
     */
    @PostMapping(value = "/getVehicleUsedListApp")
    public String getVehicleUsedListApp(@RequestBody Map<String, Object> req){
        SystemUtil.getRequestQueryLimit(req);
        logger.info("获取二手车列表, param: {}", req);
        List<Map<String, Object>> vehicleUsedList = vehicleUsedService.getVehicleUsedListApp(req);
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("vehicleUsedList",vehicleUsedList);
        return Result.success(jMap).toJSON();
    }

    /**
     * 获取二手车详情
     * @param req
     * @return
     */
    @PostMapping(value = "/getVehicleUsedDetailApp")
    public String getVehicleUsedDetailApp(@RequestBody Map<String, Object> req){
        logger.info("获取二手车详情, param: {}", req);
        Map<String, Object> vehicleUsed = vehicleUsedService.getVehicleUsedDetailApp(Integer.valueOf(req.get("vehicleUsedId").toString()));
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("vehicleUsed",vehicleUsed);
        return Result.success(jMap).toJSON();
    }

}
