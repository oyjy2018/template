package com.ydc.cgj.ydhc.web.controller;

import com.ydc.cgj.ydhc.web.service.VehicleUsedService;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.BigDecimalUtil;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.ydhc.YdhcVehicleUsed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/vehicleUsed")
public class VehicleUsedController {

    private static Logger logger = LogManager.getLogger(VehicleUsedController.class);

    @Autowired
    private VehicleUsedService vehicleUsedService;

    /**
     * 修改或保存二手车辆信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/saveOrUpdateVehicleUsed")
    public String saveOrUpdateVehicleUsed(@RequestBody Map<String, Object> req) {
        if (req.get("ydhcVehicleUsed") == null) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆信息").toJSON();
        }
        Map<String, String> ydhcVehicleUsedMap = (Map<String, String>) req.get("ydhcVehicleUsed");
        Map vaildMap = ParamVaildateUtil.vaildateMapAndTransfer(ydhcVehicleUsedMap, YdhcVehicleUsed.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        YdhcVehicleUsed ydhcVehicleUsed = (YdhcVehicleUsed) vaildMap.get("object");
        //燃料类型为电动时  默认排放标准为无排放
        if ("电动".equals(ydhcVehicleUsed.getFuelType())) {
            ydhcVehicleUsed.setEmissionsStandardCode("0");
            ydhcVehicleUsed.setEmissionsStandard("无排放");
        }
        if (ydhcVehicleUsed.getPrice().compareTo(new BigDecimal(0)) <= 0) {
            return Result.failure("售价必须大于0").toJSON();
        }
        if (ydhcVehicleUsed.getNewCarPrice().compareTo(new BigDecimal(0)) <= 0) {
            return Result.failure("新车含税必须大于0").toJSON();
        }
        req.put("ydhcVehicleUsed",ydhcVehicleUsed);
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("title"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆标题").toJSON();
//        }
//        if (ydhcVehicleUsed.get("title").length() > 60) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "车辆标题不得大于60个字符").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("carOwnerName"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车主姓名").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("vin"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车架号").toJSON();
//        }
//        if (String.valueOf(ydhcVehicleUsed.get("vin")).length() > 30) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "车架号不得大于30个字符").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("carPlate"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆车牌号").toJSON();
//        }
//        if (String.valueOf(ydhcVehicleUsed.get("carPlate")).length() > 8) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "车牌号不得大于8个字符").toJSON();
//        }
//        if (StringUtil.isEmpty(String.valueOf(ydhcVehicleUsed.get("brandCode")))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆品牌code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("brand"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆品牌").toJSON();
//        }
//        if (StringUtil.isEmpty(String.valueOf(ydhcVehicleUsed.get("seriesCode")))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车系code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("series"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车系").toJSON();
//        }
//        if (StringUtil.isEmpty(String.valueOf(ydhcVehicleUsed.get("carVersionCode")))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车版本code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("carVersion"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车版本").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("carTypeCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车类型code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("carType"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车类型").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("gearboxCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少变速箱code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("gearbox"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少变速箱").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("upPlateDate"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少上牌日期").toJSON();
//        }
//        if (StringUtil.isEmpty(String.valueOf(ydhcVehicleUsed.get("mileage")))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少表显里程").toJSON();
//        }
//        if (!BigDecimalUtil.isTwoDecimal(String.valueOf(ydhcVehicleUsed.get("mileage")))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "错误的表显里程").toJSON();
//        }
//        if (String.valueOf(ydhcVehicleUsed.get("mileage")).length() > 8) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "表显里程不能超过8位数").toJSON();
//        }
//        if (StringUtil.isEmpty(String.valueOf(ydhcVehicleUsed.get("emissions")))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少排放量").toJSON();
//        }
//        if (!BigDecimalUtil.isTwoDecimal(String.valueOf(ydhcVehicleUsed.get("emissions")))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "错误的排放量").toJSON();
//        }
//        if (String.valueOf(ydhcVehicleUsed.get("emissions")).length() > 4) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "排放量数值不正确").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("fuelTypeCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少燃料类型code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("fuelType"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少燃料类型").toJSON();
//        }
////        if("电动".equals(ydhcVehicleUsed.get("fuelType")) && !"无排放".equals(ydhcVehicleUsed.get("emissionsStandard"))){
////            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "燃料类型为电动时排放标准必须为无排放").toJSON();
////        }
//        //燃料类型为电动时  默认排放标准为无排放
//        if ("电动".equals(ydhcVehicleUsed.get("fuelType"))) {
//            ydhcVehicleUsed.put("emissionsStandardCode","0");
//            ydhcVehicleUsed.put("emissionsStandard","无排放");
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("emissionsStandardCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少排放标准code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("emissionsStandard"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少排放标准").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("seatingsCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少座位数code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("seatings"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少座位数").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("driveCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少驱动code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("drive"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少驱动").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("provinceCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车牌所在省code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("province"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车牌所在省").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("cityCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车牌所在市code").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("city"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车牌所在市").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("isTransfer"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少是否可以过户").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("asDeadline"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少年检到期时间").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("saliDeadline"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少交强险到期时间").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("lookCarProvinceCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少看车地点省编码").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("lookCarProvince"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少看车地点省").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("lookCarCityCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少看车地点市编码").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("lookCarCity"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少看车地点省").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("lookCarRegionCode"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少看车地点县/区编码").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("lookCarRegion"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少看车地点县/区").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("lookCarAddress"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少看车详细地址").toJSON();
//        }
//        if (String.valueOf(ydhcVehicleUsed.get("lookCarAddress")).length() > 50) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "看车详细地址不得大于50个字符").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("config"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少配置信息").toJSON();
//        }
//        if (ydhcVehicleUsed.get("config").length() > 600) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "配置信息不得大于600字符").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("price"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少售价").toJSON();
//        }
//        if (!BigDecimalUtil.isTwoDecimal(ydhcVehicleUsed.get("price"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "错误的售价").toJSON();
//        }
//        if (String.valueOf(ydhcVehicleUsed.get("price")).length() > 13) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "售价数值过大").toJSON();
//        }
//        if(BigDecimalUtil.retBigDecimal(ydhcVehicleUsed.get("price")).compareTo(new BigDecimal(0)) <= 0){
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "售价必须大于0").toJSON();
//        }
//        if (StringUtil.isEmpty(ydhcVehicleUsed.get("newCarPrice"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少新车含税价").toJSON();
//        }
//        if (!BigDecimalUtil.isTwoDecimal(ydhcVehicleUsed.get("newCarPrice"))) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "错误的新车含税价").toJSON();
//        }
//        if (String.valueOf(ydhcVehicleUsed.get("newCarPrice")).length() > 13) {
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "新车含税价数值过大").toJSON();
//        }
//        if(BigDecimalUtil.retBigDecimal(ydhcVehicleUsed.get("newCarPrice")).compareTo(new BigDecimal(0)) <= 0){
//            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "新车含税价必须大于0").toJSON();
//        }
        List<Map<String, Object>> ydhcVehicleUsedImgs = null;
        if (ydhcVehicleUsedMap.get("id") == null || "0".equals(ydhcVehicleUsedMap.get("id"))) {
            if (req.get("ydhcVehicleUsedImgs") == null) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片").toJSON();
            }
            ydhcVehicleUsedImgs = (List<Map<String, Object>>) req.get("ydhcVehicleUsedImgs");
            if (ydhcVehicleUsedImgs.size() == 0) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片").toJSON();
            }
        }

        if (ydhcVehicleUsedImgs != null) {
            StringBuffer describeTypeSb = new StringBuffer(",");
            List<Map<String, Object>> trueYdhcVehicleUsedImgs = new ArrayList<>(); //存储不为空的图片
            for (Map<String, Object> ydhcVehicleUsedImg : ydhcVehicleUsedImgs) {
                if (ydhcVehicleUsedImg == null || ydhcVehicleUsedImg.size() == 0) {
                    continue;
                }
                if (StringUtil.isEmpty(ydhcVehicleUsedImg.get("imgType"))) {
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片类型").toJSON();
                }
                if (StringUtil.isEmpty(ydhcVehicleUsedImg.get("describeType"))) {
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片描述类型").toJSON();
                }
                //记录出现的图片描述类型
                describeTypeSb.append(ydhcVehicleUsedImg.get("describeType")).append(",");

                if (StringUtil.isEmpty(ydhcVehicleUsedImg.get("fileName"))) {
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片文件名").toJSON();
                }
                if (StringUtil.isEmpty(ydhcVehicleUsedImg.get("fileUrl"))) {
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片文件路径").toJSON();
                }
                if (StringUtil.isEmpty(ydhcVehicleUsedImg.get("littleFileName"))) {
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片缩略图文件名").toJSON();
                }
                if (StringUtil.isEmpty(ydhcVehicleUsedImg.get("littleFileUrl"))) {
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片所旅途文件路径").toJSON();
                }
                if (StringUtil.isEmpty(ydhcVehicleUsedImg.get("fileType"))) {
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片文件后缀").toJSON();
                }
                if (!StringUtil.isEmpty(ydhcVehicleUsedImg.get("imgDescribe")) && String.valueOf(ydhcVehicleUsedImg.get("imgDescribe")).length() > 40) {
                    return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "图片描述文字不得大于40个字符").toJSON();
                }

                trueYdhcVehicleUsedImgs.add(ydhcVehicleUsedImg); //添加图片
            }
            String describeTypesStr = describeTypeSb.toString(); //出现的图片描述类型串
            if (describeTypesStr.indexOf(",11,") == -1 || describeTypesStr.indexOf(",21,") == -1 || describeTypesStr.indexOf(",22,") == -1 ||
                    describeTypesStr.indexOf(",23,") == -1 || describeTypesStr.indexOf(",31,") == -1 || describeTypesStr.indexOf(",32,") == -1) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少必传的图片").toJSON();
            }
            req.put("ydhcVehicleUsedImgs", trueYdhcVehicleUsedImgs); //用非空图片集合替换图片集合
        }
        return vehicleUsedService.saveOrUpdateVehicleUsed(req);
    }

    /**
     * 获取二手车信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getVehicleUsedInfo")
    public String getVehicleUsedInfo(@RequestBody Map<String, String> req) {
        if (req == null || StringUtil.isEmpty(req.get("id"))) {
            return Result.failure("参数缺失").toJSON();
        }
        return vehicleUsedService.getVehicleUsedInfo(req);
    }

    /**
     * 查询二手车列表信息（包括模板，在售，待售）
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/getVehicleUsedList")
    public String getVehicleUsedList(@RequestBody Map<String, Object> req) {
        return vehicleUsedService.getYdhcVehicleUsedList(req);
    }

    /**
     * 更新发布状态
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/updateReleaseStatus")
    public String updateReleaseStatus(@RequestBody Map<String, String> req) {
        return vehicleUsedService.updateReleaseStatus(req);
    }
}
