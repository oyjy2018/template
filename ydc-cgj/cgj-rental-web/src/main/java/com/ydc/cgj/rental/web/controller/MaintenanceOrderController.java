package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.service.MaintenanceOrderService;
import com.ydc.cgj.rental.web.service.UserService;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.*;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceUpdateDTO;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 租车机务单
 *
 * @author
 * @create 2018-11-22 13:55
 **/
@RestController
@RequestMapping(value = "/maintenanceOrder")
public class MaintenanceOrderController {

    private static final Logger logger = LogManager.getLogger(MaintenanceOrderController.class);

    @Autowired
    private MaintenanceOrderService maintenanceOrderService;

    @Autowired
    private UserService userService;

    /**
     * 新增机务单 出车
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = "rental:car:manage:carrun:view:addMaintenance")
    @PostMapping("/saveMaintenanceOrder")
    public String saveMaintenanceOrder(@RequestParam(value = "data", required = false) String data) {
        //参数验证 并转为目标对象
        Map vaildMap = ParamVaildateUtil.vaildateAndTransfer(data, RentalOrderMaintenanceInsertDTO.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        RentalOrderMaintenanceInsertDTO rentalOrderMaintenanceInsertDTO = (RentalOrderMaintenanceInsertDTO) vaildMap.get("object");
        Integer userId = WebShiroTokenManager.getUser().getId();
        //设置创建&更新人员id
        rentalOrderMaintenanceInsertDTO.setCreateBy(userId);
        rentalOrderMaintenanceInsertDTO.setUpdateBy(userId);
        return maintenanceOrderService.saveMaintenanceOrder(rentalOrderMaintenanceInsertDTO);
    }

    /**
     * 机务单列表
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = "rental:maintenance:manage:maintenance:view:query")
    @PostMapping(value = "/getMaintenanceOrderList")
    public String getMaintenanceOrderList(@RequestParam(value = "data") String data) {
        return maintenanceOrderService.getMaintenanceOrderList(
                JSON.parseObject(data, RentalOrderMaintenanceDTO.class)).toJSON();
    }

    /**
     * 修改机务单 还车
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:carrun:view:repayCar","rental:maintenance:manage:maintenance:view:repayCar"},logical = Logical.OR)
    @PostMapping("/updateMaintenanceOrder")
    public String updateMaintenanceOrder(@RequestParam("data") String data) {
        Map<String, Object> req = JSONObject.parseObject(data, Map.class);

        Map<String, Object> param = new HashMap<>();//获取传递参数

        User user = WebShiroTokenManager.getUser();
        Integer userId = user.getId();
        String userName = user.getUserName();

        //机务单主表
        Map<String, Object> rentalOrderMaintenanceMap = (Map<String, Object>) req.get("rentalOrderMaintenance");
        if (rentalOrderMaintenanceMap == null || rentalOrderMaintenanceMap.size() == 0) {
            return Result.failure("机务信息为空").toJSON();
        }
        //参数验证
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("id"))) {
            return Result.failure("机务单id为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("carId"))) {
            return Result.failure("车辆id为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("carNumber"))) {
            return Result.failure("车牌号为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("carStoreId"))) {
            return Result.failure("所属门店id为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("carStoreName"))) {
            return Result.failure("所属门店名为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("comeCarTime"))) {
            return Result.failure("出车时间为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("comeWarehouseMileage"))) {
            return Result.failure("出库里程数为空").toJSON();
        }
        if (rentalOrderMaintenanceMap.get("comeWarehouseMileage").toString().length() > 8) {
            return Result.failure("出库里程数不能超过8位数").toJSON();
        }
        if (!BigDecimalUtil.isTwoDecimal(rentalOrderMaintenanceMap.get("comeWarehouseMileage").toString())) {
            return Result.failure("出库里程数必须为数字").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("comeWarehouseOilAmount"))) {
            return Result.failure("出库油量为空").toJSON();
        }
        if (rentalOrderMaintenanceMap.get("comeWarehouseOilAmount").toString().length() > 3) {
            return Result.failure("出库油量不能超过3位数").toJSON();
        }
        if (!BigDecimalUtil.isTwoDecimal(rentalOrderMaintenanceMap.get("comeWarehouseOilAmount").toString())) {
            return Result.failure("出库油量必须为数字").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("comeWarehouseStoreId"))) {
            return Result.failure("出库所在门店id为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("comeWarehouseStoreName"))) {
            return Result.failure("出库所在门店名为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("comeCarUserId"))) {
            return Result.failure("驾车人员Id为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("comeCarUserName"))) {
            return Result.failure("驾车人员名为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("maintenanceType"))) {
            return Result.failure("机务类型为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("repayCarTime"))) {
            return Result.failure("还车时间为空").toJSON();
        }
        //还车时间必须大于出车时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (sdf.parse(rentalOrderMaintenanceMap.get("repayCarTime").toString()).getTime() < sdf.parse(rentalOrderMaintenanceMap.get("comeCarTime").toString()).getTime()){
                return Result.failure("还车时间必须大于出车时间").toJSON();
            }
        } catch (ParseException e) {
            return Result.failure("时间格式化异常").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("repayCarMileage"))) {
            return Result.failure("还车里程数为空").toJSON();
        }
        if (rentalOrderMaintenanceMap.get("repayCarMileage").toString().length() > 8) {
            return Result.failure("还车里程数不能超过8位数").toJSON();
        }
        if (!BigDecimalUtil.isTwoDecimal(rentalOrderMaintenanceMap.get("repayCarMileage").toString())) {
            return Result.failure("还车里程数必须为数字").toJSON();
        }
        //判断还车里程数是否大于出车里程数
        if (BigDecimalUtil.retBigDecimal(rentalOrderMaintenanceMap.get("repayCarMileage")).compareTo(BigDecimalUtil.retBigDecimal(rentalOrderMaintenanceMap.get("comeWarehouseMileage"))) < 0) {
            return Result.failure("还车里程数必须大于出车里程数").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("repayCarOilAmount"))) {
            return Result.failure("还车油量为空").toJSON();
        }
        if (rentalOrderMaintenanceMap.get("repayCarOilAmount").toString().length() > 3) {
            return Result.failure("还车油量不能超过3位数").toJSON();
        }
        if (!BigDecimalUtil.isTwoDecimal(rentalOrderMaintenanceMap.get("repayCarOilAmount").toString())) {
            return Result.failure("还车油量必须为数字").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("repayCarStoreId"))) {
            return Result.failure("还车所在门店id为空").toJSON();
        }
        if (StringUtil.isEmpty(rentalOrderMaintenanceMap.get("repayCarStoreName"))) {
            return Result.failure("还车所在门店名为空").toJSON();
        }

        RentalOrderMaintenance rentalOrderMaintenance = JSONObject.parseObject(JSONObject.toJSONString(rentalOrderMaintenanceMap), RentalOrderMaintenance.class);
        rentalOrderMaintenance.setRepayCarUserId(userId);
        rentalOrderMaintenance.setRepayCarUserName(userName);
        rentalOrderMaintenance.setRepayCar((byte) 1); //设为已还
        rentalOrderMaintenance.setUpdateTime(new Date());
        rentalOrderMaintenance.setUpdateBy(userId);
        param.put("rentalOrderMaintenance", rentalOrderMaintenance); //放到参数中

        //获取机务类型
        String maintenanceType = rentalOrderMaintenance.getMaintenanceType();

        //判断是否有洗车信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_1.getCode())) {
            Map<String, Object> carWashInfoMap = (Map<String, Object>) req.get("rentalCarWashInfo");
            if (carWashInfoMap == null || carWashInfoMap.size() == 0) {
                return Result.failure("洗车信息为空").toJSON();
            }
            if (StringUtil.isEmpty(carWashInfoMap.get("washCarStore"))) {
                return Result.failure("洗车门店为空").toJSON();
            }
            if (StringUtil.isEmpty(carWashInfoMap.get("washCarCost"))) {
                return Result.failure("洗车费用为空").toJSON();
            }
            if (!BigDecimalUtil.isTwoDecimal(carWashInfoMap.get("washCarCost").toString())) {
                return Result.failure("洗车费用必须为数字").toJSON();
            }
            RentalCarWashInfo rentalCarWashInfo = JSONObject.parseObject(JSONObject.toJSONString(carWashInfoMap), RentalCarWashInfo.class);
            rentalCarWashInfo.setCarId(rentalOrderMaintenance.getCarId());  //设置车id
            rentalCarWashInfo.setMaintenanceId(rentalOrderMaintenance.getId()); //设置机务单id
            rentalCarWashInfo.setStatus((byte) 1); //设置默认状态
            rentalCarWashInfo.setCreateTime(new Date()); //设置创建时间
            rentalCarWashInfo.setUpdateTime(new Date()); //设置更新时间
            rentalCarWashInfo.setCreateBy(userId); //设置创建人员
            rentalCarWashInfo.setUpdateBy(userId); //设置更新人员

            param.put("rentalCarWashInfo", rentalCarWashInfo); //加入参数map
        }
        //判断是否有加油信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_2.getCode())) {
            Map<String, Object> rentalRefuelInfoMap = (Map<String, Object>) req.get("rentalRefuelInfo");
            if (rentalRefuelInfoMap == null || rentalRefuelInfoMap.size() == 0) {
                return Result.failure("加油信息为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalRefuelInfoMap.get("refuelAmount"))) {
                return Result.failure("加油量为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalRefuelInfoMap.get("refuelUnitPrice"))) {
                return Result.failure("加油单价为空").toJSON();
            }
            if (!BigDecimalUtil.isTwoDecimal(rentalRefuelInfoMap.get("refuelUnitPrice").toString())) {
                return Result.failure("加油单价必须为数字").toJSON();
            }
            if (StringUtil.isEmpty(rentalRefuelInfoMap.get("refuelMoney"))) {
                return Result.failure("加油金额为空").toJSON();
            }
            if (!BigDecimalUtil.isTwoDecimal(rentalRefuelInfoMap.get("refuelMoney").toString())) {
                return Result.failure("加油金额必须为数字").toJSON();
            }
            if (StringUtil.isEmpty(rentalRefuelInfoMap.get("refuelPaymentWay"))) {
                return Result.failure("加油付费方式为空").toJSON();
            }
            if (!NumberUtil.isNum(rentalRefuelInfoMap.get("refuelPaymentWay"))) {
                return Result.failure("加油付费方式值类型不正确").toJSON();
            }
            RentalRefuelInfo rentalRefuelInfo = JSONObject.parseObject(JSONObject.toJSONString(rentalRefuelInfoMap), RentalRefuelInfo.class);

            rentalRefuelInfo.setCarId(rentalOrderMaintenance.getCarId());  //设置车id
            rentalRefuelInfo.setMaintenanceId(rentalOrderMaintenance.getId()); //设置机务单id
            rentalRefuelInfo.setStatus((byte) 1); //设置默认状态
            rentalRefuelInfo.setCreateTime(new Date()); //设置创建时间
            rentalRefuelInfo.setUpdateTime(new Date()); //设置更新时间
            rentalRefuelInfo.setCreateBy(userId); //设置创建人员
            rentalRefuelInfo.setUpdateBy(userId); //设置更新人员

            param.put("rentalRefuelInfo", rentalRefuelInfo); //加入参数map
        }
        //判断是否有维修保养信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_3.getCode())) {
            Map<String, Object> rentalMaintenanceMap = (Map<String, Object>) req.get("rentalMaintenance");
            if (rentalMaintenanceMap == null || rentalMaintenanceMap.size() == 0) {
                return Result.failure("维修保养信息为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalMaintenanceMap.get("deliveryType"))) {
                return Result.failure("送修种类为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalMaintenanceMap.get("factoryName"))) {
                return Result.failure("维修厂为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalMaintenanceMap.get("maintenanceCosts"))) {
                return Result.failure("维修费用为空").toJSON();
            }
            if (!BigDecimalUtil.isTwoDecimal(rentalMaintenanceMap.get("maintenanceCosts").toString())) {
                return Result.failure("维修费用必须为数字").toJSON();
            }
            if (StringUtil.isEmpty(rentalMaintenanceMap.get("maintenanceTime"))) {
                return Result.failure("维修时间为空").toJSON();
            }

            RentalMaintenance rentalMaintenance = JSONObject.parseObject(JSONObject.toJSONString(rentalMaintenanceMap), RentalMaintenance.class);

            rentalMaintenance.setCarId(rentalOrderMaintenance.getCarId());  //设置车id
            rentalMaintenance.setMaintenanceId(rentalOrderMaintenance.getId()); //设置机务单id
            rentalMaintenance.setStatus((byte) 1); //设置默认状态
            rentalMaintenance.setCreateTime(new Date()); //设置创建时间
            rentalMaintenance.setUpdateTime(new Date()); //设置更新时间
            rentalMaintenance.setCreateBy(userId); //设置创建人员
            rentalMaintenance.setUpdateBy(userId); //设置更新人员

            param.put("rentalMaintenance", rentalMaintenance); //加入参数map
        }
        //判断是否有事故维修信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_4.getCode())) {
            Map<String, Object> rentalAccidentMaintenanceMap = (Map<String, Object>) req.get("rentalAccidentMaintenance");
            if (rentalAccidentMaintenanceMap == null || rentalAccidentMaintenanceMap.size() == 0) {
                return Result.failure("事故维修维修保养信息为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalAccidentMaintenanceMap.get("factoryName"))) {
                return Result.failure("维修厂为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalAccidentMaintenanceMap.get("maintenanceCosts"))) {
                return Result.failure("维修费用为空").toJSON();
            }
            if (!BigDecimalUtil.isTwoDecimal(rentalAccidentMaintenanceMap.get("maintenanceCosts").toString())) {
                return Result.failure("维修费用必须为数字").toJSON();
            }
            if (StringUtil.isEmpty(rentalAccidentMaintenanceMap.get("maintenanceTime"))) {
                return Result.failure("维修时间为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalAccidentMaintenanceMap.get("accidentId"))) {
                return Result.failure("事故id为空").toJSON();
            }

            RentalAccidentMaintenance rentalAccidentMaintenance = JSONObject.parseObject(JSONObject.toJSONString(rentalAccidentMaintenanceMap), RentalAccidentMaintenance.class);

            rentalAccidentMaintenance.setCarId(rentalOrderMaintenance.getCarId());  //设置车id
            rentalAccidentMaintenance.setMaintenanceId(rentalOrderMaintenance.getId()); //设置机务单id
            rentalAccidentMaintenance.setStatus((byte) 1); //设置默认状态
            rentalAccidentMaintenance.setCreateTime(new Date()); //设置创建时间
            rentalAccidentMaintenance.setUpdateTime(new Date()); //设置更新时间
            rentalAccidentMaintenance.setCreateBy(userId); //设置创建人员
            rentalAccidentMaintenance.setUpdateBy(userId); //设置更新人员

            param.put("rentalAccidentMaintenance", rentalAccidentMaintenance); //加入参数map
            param.put("accidentId", rentalAccidentMaintenanceMap.get("accidentId")); //放入是事故id
        }
        //判断是否有调度信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_5.getCode())) {
            Map<String, Object> rentalDispatchMap = (Map<String, Object>) req.get("rentalDispatch");
            if (rentalDispatchMap == null || rentalDispatchMap.size() == 0) {
                return Result.failure("调度信息为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalDispatchMap.get("dispatchType"))) {
                return Result.failure("调度类型为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalDispatchMap.get("transportMode"))) {
                return Result.failure("运输方式为空").toJSON();
            }
            if (StringUtil.isEmpty(rentalDispatchMap.get("dispatchCosts"))) {
                return Result.failure("调度费用为空").toJSON();
            }
            if (!BigDecimalUtil.isTwoDecimal(rentalDispatchMap.get("dispatchCosts").toString())) {
                return Result.failure("调度费用必须为数字").toJSON();
            }

            RentalDispatch rentalDispatch = JSONObject.parseObject(JSONObject.toJSONString(rentalDispatchMap), RentalDispatch.class);

            rentalDispatch.setCarId(rentalOrderMaintenance.getCarId());  //设置车id
            rentalDispatch.setMaintenanceId(rentalOrderMaintenance.getId()); //设置机务单id
            rentalDispatch.setStatus((byte) 1); //设置默认状态
            rentalDispatch.setCreateTime(new Date()); //设置创建时间
            rentalDispatch.setUpdateTime(new Date()); //设置更新时间
            rentalDispatch.setCreateBy(userId); //设置创建人员
            rentalDispatch.setUpdateBy(userId); //设置更新人员

            param.put("rentalDispatch", rentalDispatch); //加入参数map
        }
        return maintenanceOrderService.updateMaintenanceOrder(param);
    }

    /**
     * 修改机务单 还车
     *
     * @param data
     * @return
     */
    @PostMapping("/updateMaintenanceOrderCopy")
    public String updateMaintenanceOrderCopy(@RequestParam("data") String data) {
        Map vaildMap = ParamVaildateUtil.vaildate(data, RentalOrderMaintenanceUpdateDTO.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        Map<String, Object> req = JSONObject.parseObject(data, Map.class);
        //机务单主表
        Map<String, Object> rentalOrderMaintenanceMap = (Map<String, Object>) req.get("rentalOrderMaintenance");
        vaildMap = ParamVaildateUtil.vaildateMap(rentalOrderMaintenanceMap, RentalOrderMaintenance.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }

        //获取机务类型
        String maintenanceType = (String) rentalOrderMaintenanceMap.get("maintenanceType");

        //判断是否有洗车信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_1.getCode())) {
            Map<String, Object> carWashInfoMap = (Map<String, Object>) req.get("rentalCarWashInfo");
            vaildMap = ParamVaildateUtil.vaildateMap(carWashInfoMap, RentalCarWashInfo.class);
            if ("1".equals(vaildMap.get("code"))) {
                return Result.failure("洗车信息验证：" + vaildMap.get("message").toString()).toJSON();
            }
        }
        //判断是否有加油信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_2.getCode())) {
            Map<String, Object> rentalRefuelInfoMap = (Map<String, Object>) req.get("rentalRefuelInfo");
            vaildMap = ParamVaildateUtil.vaildateMap(rentalRefuelInfoMap, RentalRefuelInfo.class);
            if ("1".equals(vaildMap.get("code"))) {
                return Result.failure("加油信息验证：" + vaildMap.get("message").toString()).toJSON();
            }
        }
        //判断是否有维修保养信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_3.getCode())) {
            Map<String, Object> rentalMaintenanceMap = (Map<String, Object>) req.get("rentalMaintenance");
            vaildMap = ParamVaildateUtil.vaildateMap(rentalMaintenanceMap, RentalMaintenance.class);
            if ("1".equals(vaildMap.get("code"))) {
                return Result.failure("维修保养信息验证：" + vaildMap.get("message").toString()).toJSON();
            }
        }
        //判断是否有事故维修信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_4.getCode())) {
            Map<String, Object> rentalAccidentMaintenanceMap = (Map<String, Object>) req.get("rentalAccidentMaintenance");
            vaildMap = ParamVaildateUtil.vaildateMap(rentalAccidentMaintenanceMap, RentalAccidentMaintenance.class);
            if ("1".equals(vaildMap.get("code"))) {
                return Result.failure("事故维修信息验证：" + vaildMap.get("message").toString()).toJSON();
            }
        }
        //判断是否有调度信息
        if (maintenanceType.contains(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_5.getCode())) {
            Map<String, Object> rentalDispatchMap = (Map<String, Object>) req.get("rentalDispatch");
            vaildMap = ParamVaildateUtil.vaildateMap(rentalDispatchMap, RentalDispatch.class);
            if ("1".equals(vaildMap.get("code"))) {
                return Result.failure("调度信息验证：" + vaildMap.get("message").toString()).toJSON();
            }
        }
        RentalOrderMaintenanceUpdateDTO rentalOrderMaintenanceUpdateDTO = JSONObject.parseObject(data, RentalOrderMaintenanceUpdateDTO.class);
        //return maintenanceOrderService.updateMaintenanceOrder(rentalOrderMaintenanceUpdateDTO);
        return Result.success(rentalOrderMaintenanceUpdateDTO).toJSON();
    }

    /**
     * 机务单详情
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:maintenance:manage:maintenance:view:detail","rental:car:manage:violation:view:maintenanceDetail","rental:car:manage:incident:view:maintenanceDetail"},logical = Logical.OR)
    @PostMapping(value = "/getMaintenanceOrderDetail")
    public String getMaintenanceOrderDetail(@RequestParam(value = "data") String data) {
        Integer maintenanceOrderId = JSONObject.parseObject(data).getInteger("maintenanceOrderId");
        Result result = Result.success();
        result.setData(maintenanceOrderService.getMaintenanceOrderDetail(maintenanceOrderId));
        return result.toJSON();
    }

    /**
     * 出车详情
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getMaintenanceOrderDrawOut")
    public String getMaintenanceOrderDrawOut(@RequestParam(value = "data") String data) {
        Map req = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(req.get("id"))) {
            return Result.failure("机务单id为空").toJSON();
        }
        return maintenanceOrderService.getMaintenanceOrderDrawOut(Integer.valueOf(req.get("id").toString()));
    }

    /**
     * 返回所有有效用户
     *
     * @return
     */
    @PostMapping("/getValidUser")
    public String getValidUser() {
        return userService.getValidUser();
    }

    /**
     * 根据车辆id获取机务单id
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getMaintenanceOrderIdByCarId")
    public String getMaintenanceOrderIdByCarId(@RequestParam(value = "data") String data) {
        Map req = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(req.get("carId"))) {
            return Result.failure("车辆id为空").toJSON();
        }
        return maintenanceOrderService.getMaintenanceOrderIdByCarId(Integer.valueOf(req.get("carId").toString()));
    }

    /**
     * 删除机务单(软删)
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = "rental:maintenance:manage:maintenance:view:disable")
    @PostMapping(value = "/deleteMaintenanceOrderById")
    public String deleteMaintenanceOrderById(@RequestParam(value = "data") String data) {
        Map req = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(req.get("id"))) {
            return Result.failure("机务单id为空").toJSON();
        }
        return maintenanceOrderService.deleteMaintenanceOrderById(Integer.valueOf(req.get("id").toString()), WebShiroTokenManager.getUser().getId());
    }
}
