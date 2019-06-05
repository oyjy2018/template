package com.ydc.cgj.rental.web.controller;


import com.alibaba.fastjson.JSON;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.service.DictionaryDetailService;
import com.ydc.cgj.rental.web.service.RentalAccidentService;
import com.ydc.cgj.rental.web.service.UserService;
import com.ydc.cgj.rental.web.util.CommonUtil;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.CommonReqDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalAccidentQueryDTO;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalAccident;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 事故controller
 */
@RestController
@RequestMapping(value = "/accident")
public class RentalAccidentController {

    private final Logger logger = LogManager.getLogger(RentalAccidentController.class);

    @Autowired
    DictionaryDetailService dictionaryDetailService;

    @Autowired
    RentalAccidentService rentalAccidentService;

    @Autowired
    UserService userService;

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
     * 获取新增车系车型相关下拉框选值
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getAccidentOptionList", method = RequestMethod.POST)
    public String getAccidentOptionList(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}","获取事故列表相关下拉框选值",data);
        CommonReqDTO crd = JsonUtil.jsonToBean(data, CommonReqDTO.class);
        Boolean nuulOption = crd.getNullOption() != null ? Boolean.valueOf(crd.getNullOption()) : null;
        Map<String, Object> jMap = new HashMap<>();
        // 事故起因
        jMap.put("accidentCause", CommonUtil.getOptionList(DictionaryConstant.ACCIDENT_CAUSE, nuulOption, dictionaryDetailService));
        // 事故具体分类
        jMap.put("accidentType", CommonUtil.getOptionList(DictionaryConstant.ACCIDENT_TYPE, nuulOption, dictionaryDetailService));
        // 事故责任
        jMap.put("accidentDuty", CommonUtil.getOptionList(DictionaryConstant.ACCIDENT_DUTY, nuulOption, dictionaryDetailService));
        // 事故类别
        jMap.put("accidentCategory", CommonUtil.getOptionList(DictionaryConstant.ACCIDENT_CATEGORY, nuulOption, dictionaryDetailService));
        //事故等级
        jMap.put("accidentClassification", CommonUtil.getOptionList(DictionaryConstant.ACCIDENT_CLASSIFICATION, nuulOption, dictionaryDetailService));
        //驾驶人类型
        jMap.put("driverType", CommonUtil.getOptionList(DictionaryConstant.DRIVER_TYPE, nuulOption, dictionaryDetailService));
        return Result.success(jMap).toJSON();
    }
    /**
     * 新增事故单
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:order:manage:order:view:addAccident","rental:maintenance:manage:maintenance:view:addAccident"},logical = Logical.OR)
    @PostMapping( value = "/insertRentalAccidentInfo")
    public String insertRentalAccidentInfo(@RequestParam("data") String data) {
        RentalAccident rentalAccident = JSON.parseObject(data, RentalAccident.class);
        Integer accidentCause = rentalAccident.getAccidentCause();  // 事故起因
        Integer accidentType = rentalAccident.getAccidentType(); // 具体分类
        Integer accidentDuty = rentalAccident.getAccidentDuty(); // 事故责任类型
        Integer accidentCategory = rentalAccident.getAccidentCategory(); // 事故类别
        Integer accidentClassification = rentalAccident.getAccidentClassification(); // 事故等级
        Integer accidentStatus = rentalAccident.getAccidentStatus(); // 事故状态
        Integer userId = rentalAccident.getUserId(); // 事故经手人
        BigDecimal accidentAmount = rentalAccident.getAccidentAmount(); // 事故总金额
        Integer orderType = rentalAccident.getOrderType();
        if (accidentCause == null) {
            return Result.failure("事故起因不能为空").toJSON();
        } else {
            if (!StringUtil.contain(Integer.toString(accidentCause),"1,2,3") ){
                return Result.failure("事故起因类型不正确").toJSON();
            }
        }
        if (accidentType == null) {
            return Result.failure("具体分类不能为空").toJSON();
        } else {
            if (!StringUtil.contain(Integer.toString(accidentType),"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19") ){
                return Result.failure("具体分类类型不正确").toJSON();
            }
        }
        if (accidentDuty == null) {
            return Result.failure("事故责任不能为空").toJSON();
        } else {
            if (!StringUtil.contain(Integer.toString(accidentDuty),"1,2,3,4,5") ){
                return Result.failure("事故责任类型不正确").toJSON();
            }
        }
        if (accidentCategory == null) {
            return Result.failure("事故类别不能为空").toJSON();
        } else {
            if (!StringUtil.contain(Integer.toString(accidentCategory),"1,2,3") ){
                return Result.failure("事故类别类型不正确").toJSON();
            }
        }
        if (accidentClassification == null) {
            return Result.failure("事故等级不能为空").toJSON();
        } else {
            if (!StringUtil.contain(Integer.toString(accidentClassification),"1,2,3,4") ){
                return Result.failure("事故等级类型不正确").toJSON();
            }
        }
        if (accidentStatus == null) {
            return Result.failure("事故状态不能为空").toJSON();
        } else {
            if (!StringUtil.contain(Integer.toString(accidentStatus),"0,1") ){
                return Result.failure("事故状态类型不正确").toJSON();
            }
        }
        if (userId == null) {
            return Result.failure("事故经手人不能为空").toJSON();
        }
        if (accidentAmount == null) {
            return Result.failure("事故总金额不能为空").toJSON();
        } else {
            if (accidentAmount.compareTo(new BigDecimal("0.00")) <= 0) {
                return Result.failure("事故总金额不能小于等于0元").toJSON();
            }
        }
        if (orderType != null) {
            if (!StringUtil.contain(Integer.toString(orderType),"0,1") ){
                return Result.failure("订单类型不正确").toJSON();
            }
        }
        rentalAccident.setStatus(CodeConstant.NORMAL_STATUS);// 正常
        rentalAccident.setCreateTime(new Date());
        //获取用户信息
        User user = WebShiroTokenManager.getUser();
        rentalAccident.setCreateBy(user.getId());
        return rentalAccidentService.insertRentalAccidentInfo(rentalAccident);
    }

    /**
     * 根据id查询事故单详情
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:incident:view:accidentDetail"})
    @PostMapping( value = "/queryRentalAccidentInfoById")
    public String queryRentalAccidentInfoById(@RequestParam("data") String data) {
        RentalAccident rentalAccident = JSON.parseObject(data, RentalAccident.class);
        Integer id = rentalAccident.getId();
        if (id == null) {
            return Result.failure("id不能为空").toJSON();
        }
        return rentalAccidentService.queryRentalAccidentInfoById(id);
    }


    /**
     * 查询事故单列表
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:incident:view:query"})
    @PostMapping( value = "/queryRentalAccidentListInfo")
    public String queryRentalAccidentListInfo(@RequestParam("data") String data) {
        RentalAccidentQueryDTO rentalAccidentQueryDTO = JSON.parseObject(data, RentalAccidentQueryDTO.class);
        return rentalAccidentService.queryRentalAccidentListInfo(rentalAccidentQueryDTO);
    }

    /**
     * 编辑事故单
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:incident:view:update"})
    @PostMapping( value = "/updateRentalAccidentInfo")
    public String updateRentalAccidentInfo(@RequestParam("data") String data) {
        RentalAccident rentalAccident = JSON.parseObject(data, RentalAccident.class);
        User user = WebShiroTokenManager.getUser();
        rentalAccident.setUpdateBy(user.getId());
        return rentalAccidentService.updateRentalAccidentInfo(rentalAccident);
    }

    /**
     * 事故单信息软删除
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:car:manage:incident:view:disable"})
    @PostMapping( value = "/deleteRentalAccidentInfo")
    public String deleteRentalAccidentInfo(@RequestParam("data") String data) {
        RentalAccident rentalAccident = JSON.parseObject(data, RentalAccident.class);
        return rentalAccidentService.deleteRentalAccidentInfo(rentalAccident);
    }

    /**
     * 查询租车订单或机务单信息
     * @param data
     * @return
     */
    @PostMapping( value = "/queryRentalOrderOrMaintenance")
    public String queryRentalOrderOrMaintenance(@RequestParam("data") String data) {
        RentalAccident rentalAccident = JSON.parseObject(data, RentalAccident.class);
        return  rentalAccidentService.queryRentalOrderOrMaintenance(rentalAccident);
    }
}
