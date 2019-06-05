package com.ydc.service.car.controller.ydhc;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.SystemUtil;
import com.ydc.commom.view.dto.ydhc.VehicleDto;
import com.ydc.commom.view.vo.cgj.car.BrandVO;
import com.ydc.commom.view.vo.cgj.car.ModelVO;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import com.ydc.commom.view.vo.ydhc.VehicleVo;
import com.ydc.commom.view.vo.ydhc.YdhcVehicleVO;
import com.ydc.model.ydhc.YdhcVehicle;
import com.ydc.model.ydhc.YdhcVehicleImg;
import com.ydc.service.car.service.VehicleBrandService;
import com.ydc.service.car.service.ydhc.VehicleImgService;
import com.ydc.service.car.service.ydhc.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private static Logger logger = LogManager.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleImgService vehicleImgService;
    @Autowired
    VehicleBrandService vehicleBrandService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(String name){
       logger.info(name);
        return Result.success("一点好车").toJSON();
    }

    /**
     * 修改或保存车辆信息
     * @param req
     * @return
     */
    @PostMapping(value = "/saveOrUpdateVehicle")
    public String saveOrUpdateVehicle(@RequestBody Map<String, Object> req){
        Map<String, String> ydhcVehicle = (Map<String, String>)req.get("ydhcVehicle");
        YdhcVehicle existYdhcVehicle = vehicleService.selectByTitle(ydhcVehicle.get("title"));
        if(existYdhcVehicle != null){
            Integer vehicleId = null;
            if(ydhcVehicle.get("id") == null || "".equals(ydhcVehicle.get("id"))){
                vehicleId = 0;
            }else{
                vehicleId = Integer.valueOf(ydhcVehicle.get("id"));
            }
            if(vehicleId.intValue() != existYdhcVehicle.getId().intValue())
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "车辆标题已存在").toJSON();
        }
        vehicleService.saveOrUpdateVehicle(req);
        return Result.success("保存成功").toJSON();
    }

    /**
     * 更新发布状态
     * @param req
     * @return
     */
    @PostMapping(value = "/updateReleaseStatus")
    public String updateReleaseStatus(@RequestBody Map<String, String> req){
        vehicleService.updateReleaseStatusByIds(req);
        return Result.success("更新发布状态成功").toJSON();
    }

    /**
     * 获取车辆信息
     * @param req
     * @return
     */
    @PostMapping(value = "/getVehicleInfo")
    public String getVehicleInfo(@RequestBody Map<String, String> req){
        YdhcVehicle vehicle = vehicleService.selectByPrimaryKey(Integer.valueOf(req.get("vehicleId")));
        if(vehicle == null){
            return Result.failure("车辆信息不存在或丢失").toJSON();
        }
        vehicle.setPrice(vehicle.getPrice().divide(BigDecimal.valueOf(10000), BigDecimal.ROUND_HALF_UP, 2));
        List<YdhcVehicleImg> vehicleImgList = vehicleImgService.selectByVehicleId(vehicle.getId());
        for(YdhcVehicleImg vehicleImg:vehicleImgList){
            try{
                vehicleImg.setViewFileUrl(FileUtil.getBrowseFile(vehicleImg.getFileUrl(),vehicleImg.getFileName()));
                vehicleImg.setViewLittleFileUrl(FileUtil.getBrowseFile(vehicleImg.getLittleFileUrl(),vehicleImg.getLittleFileName()));
            }catch (Exception e){
                logger.info("加密图片路径异常",e);
            }
        }
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("vehicle",vehicle);
        jMap.put("vehicleImgList",vehicleImgList);
        return Result.success(jMap).toJSON();
    }

    /**
     * 查询车辆列表信息
     * @param req
     * @return
     */
    @PostMapping(value = "/getYdhcVehicleList")
    public String getYdhcVehicleList(@RequestBody Map<String, Object> req){
        SystemUtil.getRequestQueryLimit(req);
        List<YdhcVehicleVO> ret = vehicleService.getYdhcVehicleList(req);
        Map<String, Object> retCount = vehicleService.getYdhcVehicleListCount(req);
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("totalCount", retCount.get("cnt"));
        jMap.put("rows",ret);
        return Result.success(jMap).toJSON();
    }

    @PostMapping(value = "/deleteVehicleImg")
    public String deleteVehicleImg(@RequestBody Map<String, String> req){
        int count = vehicleImgService.deleteById(Integer.valueOf(req.get("vehicleImgId")));
        return Result.success("图片删除成功").toJSON();
    }

    /**
     * 获取车辆列表
     * @param vehicleVo
     * @return
     */
    @PostMapping(value = "/getVehicleList")
    public Result getVehicleList(@RequestBody VehicleVo vehicleVo){
        List<VehicleDto> list = vehicleService.getVehicleList(vehicleVo);
        Result result = Result.success();
        result.setData(list);
        return result;
    }

    /**
     * 获取车辆详细信息
     * @param vehicleId
     * @return
     */
    @PostMapping(value = "/getVehicleDetail")
    public Result getVehicleDetail(Integer vehicleId){
        VehicleDto vehicleDto = vehicleService.getVehicleDetail(vehicleId);
        Result result = Result.success();
        result.setData(vehicleDto);
        return result;
    }

    /**
     * 车品牌
     * @return
     */
    @PostMapping(value = "/getBrandVOList")
    public List<BrandVO> getBrandVOList(){
        logger.info("subject:{}","车品牌");
        return vehicleBrandService.getBrandVOList();
    }

    /**
     * 车系
     * @return
     */
    @PostMapping(value = "/getSeriesVOList")
    public Map<String, List<SeriesVO>> getSeriesVOList(){
        logger.info("subject:{}","车系");
        return vehicleBrandService.getSeriesVOList();
    }

    /**
     * 车型
     * @return
     */
    @PostMapping(value = "/getModelVOList")
    public List<ModelVO> getModelVOList(@RequestParam("series") String series) {
        logger.info("subject:{},series:{}","车型",series);
        return vehicleBrandService.getModelVOList(series);
    }
}
