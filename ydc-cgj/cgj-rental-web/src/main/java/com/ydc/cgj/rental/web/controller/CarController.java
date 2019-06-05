package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.service.CarService;
import com.ydc.cgj.rental.web.service.RentalViolationService;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.enums.rental.RentalViolationEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.NumberUtil;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 车辆controller
 */
@RestController
@RequestMapping(value = "/car")
public class CarController {
    private final Logger logger = LogManager.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private RentalViolationService rentalViolationService;

    /**
     * 修改或保存车辆信息
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:car:view:add","rental:car:manage:car:view:update"},logical = Logical.OR)
    @PostMapping(value = "/saveOrUpdateCar")
    public String saveOrUpdateCar(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        //获取车辆信息
        if (req.get("car") == null) {
            return Result.failure("车辆信息为空").toJSON();
        }
        Map<String, Object> car = (Map<String, Object>) req.get("car");
        //车辆参数校验
        if (StringUtil.isEmpty(car.get("source"))) {
            return Result.failure("车辆来源为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("storeId"))) {
            return Result.failure("门店id为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("store"))) {
            return Result.failure("门店为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("brand"))) {
            return Result.failure("品牌为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("series"))) {
            return Result.failure("车系为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("model"))) {
            return Result.failure("车型为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("modelId"))) {
            return Result.failure("车型Id为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("carPlate"))) {
            return Result.failure("车牌号为空").toJSON();
        }
        if (!StringUtil.isCarPlate(car.get("carPlate"))) {
            return Result.failure("车牌号格式不对").toJSON();
        }
        if (StringUtil.isEmpty(car.get("upPlateDate"))) {
            return Result.failure("上牌日期为空").toJSON();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (sdf.parse(car.get("upPlateDate").toString()).getTime() > System.currentTimeMillis()) {
                return Result.failure("上牌日期不能大于当前时间").toJSON();
            }
        } catch (ParseException e) {
            logger.info("上牌日期转换异常");
            return Result.failure("上牌日期转换异常").toJSON();
        }
        if (StringUtil.isEmpty(car.get("vin"))) {
            return Result.failure("车架号为空").toJSON();
        }
        if (car.get("vin").toString().length() > 30) {
            return Result.failure("车架号不能超过30位").toJSON();
        }
        if (!StringUtil.isAllLetterAndNum(car.get("vin"))) {
            return Result.failure("车架号只能是字母和数字").toJSON();
        }
        if (StringUtil.isEmpty(car.get("engineNo"))) {
            return Result.failure("发动机号为空").toJSON();
        }
        if (car.get("engineNo").toString().length() > 20) {
            return Result.failure("发动机号不能超过20位").toJSON();
        }
        if (!StringUtil.isAllLetterAndNum(car.get("engineNo"))) {
            return Result.failure("发动机号只能是字母和数字").toJSON();
        }
        if (StringUtil.isEmpty(car.get("mileage"))) {
            return Result.failure("里程数为空").toJSON();
        }
        if (!NumberUtil.isNum(car.get("mileage"))) {
            return Result.failure("里程数必须为整数").toJSON();
        }
        if (car.get("mileage").toString().length() > 8) {
            return Result.failure("里程数不能超过8位").toJSON();
        }
        if (StringUtil.isEmpty(car.get("oilGaugeScale"))) {
            return Result.failure("油表刻度为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("oilMass"))) {
            return Result.failure("油量为空").toJSON();
        }
//        if (!NumberUtil.isNum(car.get("oilMass"))) {
//            return Result.failure("油量必须为整数").toJSON();
//        }
//        if (car.get("oilMass").toString().length() > 3) {
//            return Result.failure("油量不能超过3位").toJSON();
//        }
        //此版本不提供修改车型信息  不验证下列字段
        /*if (StringUtil.isEmpty(car.get("carLevel"))) {
            return Result.failure("车等级为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("carStructure"))) {
            return Result.failure("车结构为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("carSeat"))) {
            return Result.failure("座位数为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("gearBox"))) {
            return Result.failure("变速箱为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("engine"))) {
            return Result.failure("发动机为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("fuelForm"))) {
            return Result.failure("燃料种类为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("fuelLabeling"))) {
            return Result.failure("燃料标号为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("tankVolume"))) {
            return Result.failure("油箱容量为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("outputVolume"))) {
            return Result.failure("排放量为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("emissionsStandard"))) {
            return Result.failure("排放标准为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("drive"))) {
            return Result.failure("驱动为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("seatTexture"))) {
            return Result.failure("座椅材质为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("gasbagNumber"))) {
            return Result.failure("气囊数为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("hasGpsNavigation"))) {
            return Result.failure("是否有GPS为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("hasParkingSensors"))) {
            return Result.failure("是否有倒车雷达为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("soundBox"))) {
            return Result.failure("音响为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("mainImgUrl"))) {
            return Result.failure("主图地址为空").toJSON();
        }
        if (StringUtil.isEmpty(car.get("mainImgName"))) {
            return Result.failure("主图名称为空").toJSON();
        }*/

        //必传图片
        if (req.get("carImgs") == null || ((List<Map<String, Object>>) req.get("carImgs")).size() == 0) {
            return Result.failure("车辆图片为空").toJSON();
        }

        //车辆图片列表
        List<Map<String, Object>> carImgList = (List<Map<String, Object>>) req.get("carImgs");

        //开始判断列表里的每个图片对象是否有参数缺失  新增修改都需要判断
        List<Map<String, Object>> trueCarImgList = new ArrayList<>(); //最终添加的图片集合
        Map<String, Integer> describeTypeCountMap = new HashMap<>(); //统计每种图片的数量
        for (Map<String, Object> carImg : carImgList) {
            //空对象过滤  避免前端异常提前（有可能传空对象过来）
            if (carImg == null || carImg.size() == 0) {
                continue;
            }
            if (StringUtil.isEmpty(carImg.get("describeType"))) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片描述类型").toJSON();
            }
            //每种类型数量递增
            describeTypeCountMap.put(carImg.get("describeType").toString(), describeTypeCountMap.get(carImg.get("describeType").toString()) == null ? 1 : describeTypeCountMap.get(carImg.get("describeType").toString()) + 1);

            if (StringUtil.isEmpty(carImg.get("fileName"))) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片文件名").toJSON();
            }
            if (StringUtil.isEmpty(carImg.get("fileUrl"))) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片文件路径").toJSON();
            }
            if (StringUtil.isEmpty(carImg.get("littleFileName"))) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片缩略图文件名").toJSON();
            }
            if (StringUtil.isEmpty(carImg.get("littleFileUrl"))) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片缩略图文件路径").toJSON();
            }
            if (StringUtil.isEmpty(carImg.get("fileType"))) {
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车辆图片文件后缀").toJSON();
            }
            trueCarImgList.add(carImg); //添加图片
        }
        //判断各种图片是否满足数量要求

        //交强险做多3张，最少2张
        logger.info("img1");
        if (describeTypeCountMap.get("1") == null || !(describeTypeCountMap.get("1") >= 2 && describeTypeCountMap.get("1") <= 3)) {
            return Result.failure("交强险照片最多3张，最少2张").toJSON();
        }
        //商业险1张
        if (describeTypeCountMap.get("2") == null || describeTypeCountMap.get("2") != 1) {
            return Result.failure("商业险照片限定传1张").toJSON();
        }
        //行驶证正页1张
        if (describeTypeCountMap.get("3") == null || describeTypeCountMap.get("3") != 1) {
            return Result.failure("行驶证正页照片限定传1张").toJSON();
        }
        //行驶证负页1张
        if (describeTypeCountMap.get("4") == null || describeTypeCountMap.get("4") != 1) {
            return Result.failure("行驶证负页照片限定传1张").toJSON();
        }
        //左前1张
        if (describeTypeCountMap.get("6") == null || describeTypeCountMap.get("6") != 1) {
            return Result.failure("左前照片限定传1张").toJSON();
        }
        //右前1张
        if (describeTypeCountMap.get("7") == null || describeTypeCountMap.get("7") != 1) {
            return Result.failure("右前照片限定传1张").toJSON();
        }
        //左后1张
        if (describeTypeCountMap.get("8") == null || describeTypeCountMap.get("8") != 1) {
            return Result.failure("左后照片限定传1张").toJSON();
        }
        //右后1张
        if (describeTypeCountMap.get("9") == null || describeTypeCountMap.get("9") != 1) {
            return Result.failure("右后照片限定传1张").toJSON();
        }
        //仪表盘1张
        if (describeTypeCountMap.get("10") == null || describeTypeCountMap.get("10") != 1) {
            return Result.failure("仪表盘照片限定传1张").toJSON();
        }
        //其他最多10张
        if (describeTypeCountMap.get("11") != null && describeTypeCountMap.get("11") > 10) {
            return Result.failure("其他照片最多传10张").toJSON();
        }
        req.put("carImgs", trueCarImgList);  //覆盖原来的图片集合
        req.put("userId",WebShiroTokenManager.getUser().getId());
        return carService.saveOrUpdateCar(req);
    }

    /**
     * 获取车辆列表
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:car:view:query","rental:car:manage:carrun:view:query"},logical = Logical.OR)
    @PostMapping(value = "/getCarList")
    public String getCarList(@RequestParam("data") String data) {
        CommCarQueryDTO commCarQueryDTO = JSONObject.parseObject(data,CommCarQueryDTO.class);
        return carService.getCarList(commCarQueryDTO);
    }

    /**
     * 获取车辆详情
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:car:view:detail","rental:car:manage:carrun:view:detail"},logical = Logical.OR)
    @PostMapping(value = "/getCarInfo")
    public String getCarInfo(@RequestParam(value = "data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);

        if (StringUtil.isEmpty(req.get("id"))) {
            return Result.failure("id缺失").toJSON();
        }
        return carService.getCarInfo(Integer.valueOf(req.get("id").toString()));
    }

    /**
     * 获取车辆少量信息
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarInfoSimple")
    public String getCarInfoSimple(@RequestParam(value = "data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);

        if (StringUtil.isEmpty(req.get("carId"))) {
            return Result.failure("carId缺失").toJSON();
        }
        return carService.getCarInfoSimple(Integer.valueOf(req.get("carId").toString()));
    }

    /**
     * 启用禁用车辆
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:car:view:disable","rental:car:manage:car:view:enabled","rental:car:manage:carrun:view:disable"},logical = Logical.OR)
    @PostMapping(value = "/updateCarUseStatusById")
    public String updateCarUseStatusById(@RequestParam(value = "data") String data) {
        //验证参数并装换为目标对象
        Map vaildMap = ParamVaildateUtil.vaildateAndTransfer(data, CommCarUpdateUseStatusDTO.class);
        if("1".equals(vaildMap.get("code"))){
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        //从验证结果集中获取dto
        CommCarUpdateUseStatusDTO commCarUpdateUseStatusDTO = (CommCarUpdateUseStatusDTO) vaildMap.get("object");
        //获取用户信息
        User user = WebShiroTokenManager.getUser();
        commCarUpdateUseStatusDTO.setUseStatusUpdateBy(user.getId());
        commCarUpdateUseStatusDTO.setUpdateBy(user.getId());

        return carService.updateCarUseStatusById(commCarUpdateUseStatusDTO);
    }

    /**
     * 修改车辆运营状态
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = "rental:car:manage:carrun:view:updateOperationStatus")
    @PostMapping(value = "/updateCarOperationStatusById")
    public String updateCarOperationStatusById(@RequestParam(value = "data") String data) {
        Map vaildMap = ParamVaildateUtil.vaildateAndTransfer(data, CommCarUpdateOperationStatusDTO.class);
        if("1".equals(vaildMap.get("code"))){
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        //从验证结果集中获取dto
        CommCarUpdateOperationStatusDTO commCarUpdateOperationStatusDTO = (CommCarUpdateOperationStatusDTO) vaildMap.get("object");
        //获取用户信息
        User user = WebShiroTokenManager.getUser();
        commCarUpdateOperationStatusDTO.setUpdateBy(user.getId());

        return carService.updateCarOperationStatusById(commCarUpdateOperationStatusDTO);
    }

    /**
     * 保存或修改车系车型
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/saveOrUpdateCarSeries")
    public String saveOrUpdateCarSeries(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if (req.get("commCarSeries") == null) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车系车型信息").toJSON();
        }
        Map<String, String> commCarSeries = (Map<String, String>) req.get("commCarSeries");
        Map vaildMap = ParamVaildateUtil.vaildateMapAndTransfer(commCarSeries, CommCarSeriesDTO.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        //获取用户信息
        User user = WebShiroTokenManager.getUser();
        req.put("userId", user.getId());
        req.put("userName", user.getUserName());
        return carService.saveOrUpdateCarSeries(req);
    }

    /**
     * 查询车系车型详情
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarSeriesDetails")
    public String getCarSeriesDetails(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        return carService.getCarSeriesDetails(req);
    }

    /**
     * 查询车系车型列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCommCarSeriesList")
    public String getCommCarSeriesList(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        return carService.getCommCarSeriesList(req);
    }

    /**
     * 启用/禁用车系车型
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/updateHasEnabled")
    public String updateHasEnabled(@RequestParam("data") String data) {
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        return carService.updateHasEnabled(req);
    }

    /**
     * 通过条件获取违章列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalViolationList")
    public String getRentalViolationList(@RequestParam("data") String data) {
        RentalViolationDTO rentalViolationDTO = JSONObject.parseObject(data, RentalViolationDTO.class);
        return rentalViolationService.getRentalViolationList(rentalViolationDTO.changeEndTime(" 23:59:59")).toJSON();
    }

    /**
     * 通过违章id查询违章详情
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalViolationById")
    public String getRentalViolationById(@RequestParam("data") String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        int id = jsonObject.getInteger("id");
        return rentalViolationService.getRentalViolationById(id).toJSON();

    }

    /**
     * 新增违章单
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/insertRentalViolation")
    public String insertRentalViolation(@RequestParam("data") String data) {
        RentalViolationVO rentalViolationVO = JSONObject.parseObject(data, RentalViolationVO.class);
        return rentalViolationService.insertRentalViolation(rentalViolationVO);
    }

    /**
     * 更新违章单
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/updateRentalViolation")
    public String updateRentalViolation(@RequestParam("data") String data) {
        RentalViolationVO rentalViolationVO = JSONObject.parseObject(data, RentalViolationVO.class);
        return rentalViolationService.updateRentalViolation(rentalViolationVO);
    }

    /**
     * 通过违章id 软删除违章
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/updateRentalViolationStatus")
    public String updateRentalViolationStatus(@RequestParam("data") String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        int id = jsonObject.getInteger("id");
        return rentalViolationService.updateRentalViolationStatus(id);

    }

    /**
     * 获取违章类型
     *
     * @return
     */
    @PostMapping(value = "/getViolationType")
    public String getViolationType() {
        return rentalViolationService.getViolationType();
    }

    /**
     * 更新违章处理状态
     * @param data
     * @return
     */
    @PostMapping("/updateRentalViolationDealStatus")
    public String updateRentalViolationDealStatus(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "更新违章处理状态", data);
        Map validMap = ParamVaildateUtil.vaildateAndTransfer(data, RentalViolationUpdateDealStatusDTO.class);
        if ("1".equals(validMap.get("code"))) {
            return Result.failure(validMap.get("message").toString()).toJSON();
        }
        RentalViolationUpdateDealStatusDTO rentalViolationUpdateDealStatusDTO = (RentalViolationUpdateDealStatusDTO) validMap.get("object");
        //处理状态设为已处理
        rentalViolationUpdateDealStatusDTO.setDealStatus(RentalViolationEnum.DealStatusEnum.DEAL_STATUS_1.getCode());
        //获取登录人信息
        User user = WebShiroTokenManager.getUser();
        if (user == null) {
            return Result.failure("没有获取到登录人信息").toJSON();
        }
        //设置更新人和时间
        rentalViolationUpdateDealStatusDTO.setUpdateBy(user.getId());
        rentalViolationUpdateDealStatusDTO.setUpdateTime(new Date());
        rentalViolationUpdateDealStatusDTO.setDealCommitTime(new Date()); //提交时间
        rentalViolationUpdateDealStatusDTO.setDealCommitBy(user.getId());

        return rentalViolationService.updateRentalViolationDealStatus(rentalViolationUpdateDealStatusDTO);
    }

    /**
     * 通过车型id获取邮箱容量
     * @return
     */
    @PostMapping("/getTankVolumeByModelId")
    public String getTankVolumeByModelId(@RequestParam("data") String data){
        logger.info("subject:{},data:{}", "通过车型id获取邮箱容量", data);
        Map dataMap = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(dataMap.get("modelId"))) {
            return Result.failure("车型id为空").toJSON();
        }
        Integer modelId = (Integer) dataMap.get("modelId");
        return carService.getTankVolumeByModelId(modelId);
    }

}
