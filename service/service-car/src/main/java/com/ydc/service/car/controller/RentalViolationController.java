package com.ydc.service.car.controller;

import com.github.pagehelper.PageInfo;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalViolationUpdateDealStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.service.car.service.RentalOrderService;
import com.ydc.service.car.service.RentalViolationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 违章管理
 * @author df
 */
@RestController
@RequestMapping(value = "/rentalViolation")
public class RentalViolationController {

    private static final Logger logger = LogManager.getLogger(RentalViolationController.class);

    @Autowired
    private RentalViolationService rentalViolationService;

    @Autowired
    private RentalOrderService rentalOrderService;

    /**
     * 通过条件获取违章列表
     * @param rentalViolationDTO
     * @return
     */
    @PostMapping(value = "/getRentalViolationList")
    public Result getRentalViolationList(@RequestBody RentalViolationDTO rentalViolationDTO){
        Result result = Result.success();
        List<RentalViolationVO> list = rentalViolationService.getRentalViolationList(rentalViolationDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("totalCount", list == null ? 0 : new PageInfo<>(list).getTotal());
        result.setData(data);
        return result;
    }

    /**
     * 通过违章id查询违章详情
     * @param id
     * @return
     * @author df
     */
    @GetMapping(value = "/getRentalViolationById/{id}")
    public Result getRentalViolationById(@PathVariable("id") int id){
        Result result = Result.success();
        result.setData(rentalViolationService.getRentalViolationById(id));
        return result;
    }

    /**
     * 新增违章单
     * @param rentalViolationVO
     * @return
     * @author df
     */
    @PostMapping(value = "/insertRentalViolation")
    public String insertRentalViolation(@RequestBody RentalViolationVO rentalViolationVO){
        logger.info("subject:{},rentalViolationVO:{}","新增违章单",JsonUtil.gsonStr(rentalViolationVO));
        try{
            if(rentalViolationService.insertRentalViolation(rentalViolationVO) > 0){
                //更新租车订单的违章次数
                if (("0").equals(rentalViolationVO.getOrderType())){
                    rentalOrderService.updateViolationNumber(rentalViolationVO.getOrderId());
                }
                return Result.success("成功").toJSON();
            }else{
                return Result.failure("新增违章单失败").toJSON();
            }
        }catch (Exception e){
            logger.error("新增违章单异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 编辑违章单
     * @param rentalViolationVO
     * @return
     * @author df
     */
    @PostMapping(value = "/updateRentalViolation")
    public String updateRentalViolation(@RequestBody RentalViolationVO rentalViolationVO){
        logger.info("subject:{},rentalViolationVO:{}","编辑违章单",JsonUtil.gsonStr(rentalViolationVO));
        try{
            if(rentalViolationService.updateRentalViolation(rentalViolationVO) > 0){
                return Result.success("成功").toJSON();
            }else{
                return Result.failure("编辑违章单失败").toJSON();
            }
        }catch (Exception e){
            logger.error("编辑违章单异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 删除违章记录
     * @param id
     * @return
     */
    @GetMapping(value = "/updateRentalViolationStatus/{id}")
    public String updateRentalViolationStatus(@PathVariable("id") int id){
        logger.info("subject:{},rentalViolationVO:{}","更新会员状态",JsonUtil.gsonStr(id));
        try{
            if(rentalViolationService.updateRentalViolationStatus(id) > 0){
                return Result.success("成功").toJSON();
            }else{
                return Result.failure("删除违章记录失败").toJSON();
            }
        }catch (Exception e){
            logger.error("删除违章记录异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 获取违章类型
     * @return
     */
    @GetMapping(value = "/getViolationType")
    public String getViolationType() {
        List<DictionaryDetail> violationTypes = new ArrayList<>();
        //获取  违章类型--机动车通行
        Object motorVehicle = RedisUtil.redisGet(RedisConstant.VIOLATION_TYPE_MOTOR_VEHICLE);
        if(motorVehicle != null){
            violationTypes.addAll((List<DictionaryDetail>)motorVehicle);
        }
        //获取  违章类型--高速公路特别规定
        Object specialProvisions = RedisUtil.redisGet(RedisConstant.VIOLATION_TYPE_SPECIAL_PROVISIONS);
        if(specialProvisions != null){
            violationTypes.addAll((List<DictionaryDetail>)specialProvisions);
        }
        //获取  违章类型--其他规定
        Object otherProvisions = RedisUtil.redisGet(RedisConstant.VIOLATION_TYPE_OTHER_PROVISIONS);
        if(otherProvisions != null){
            violationTypes.addAll((List<DictionaryDetail>)otherProvisions);
        }
        return Result.success(violationTypes).toJSON();
    }

    /**
     * 提交违章结算单
     * @param req
     * @return
     * @author df
     */
    @PostMapping(value = "/updateViolationSettlement")
    public Result updateViolationSettlement(@RequestBody Map<String, Object> req){
        try{
            return Result.success(rentalViolationService.updateViolationSettlement(req));
        }catch (Exception e){
            logger.error("提交违章结算单异常",e);
            return Result.failure();
        }
    }

    /**
     * 更新违章处理状态
     * @param rentalViolationUpdateDealStatusDTO
     * @return
     */
    @PostMapping(value = "/updateDealStatus")
    public String updateDealStatus(@RequestBody RentalViolationUpdateDealStatusDTO rentalViolationUpdateDealStatusDTO){
        logger.info("subject:{},rentalViolationUpdateDealStatusDTO:{}","更新违章处理状态",JsonUtil.jsonStr(rentalViolationUpdateDealStatusDTO));
        return rentalViolationService.updateDealStatus(rentalViolationUpdateDealStatusDTO);
    }

}
