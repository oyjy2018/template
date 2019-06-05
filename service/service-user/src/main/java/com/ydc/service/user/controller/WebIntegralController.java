package com.ydc.service.user.controller;

import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.commom.view.vo.cgj.order.IntegralDetailVO;
import com.ydc.model.cgj.entity.IntegralEntity;
import com.ydc.service.user.service.IntegralDetailService;
import com.ydc.service.user.service.IntegralService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Web积分控制器
 *
 * @author gongjin
 * @create 2018-09-04 19:50
 **/
@RestController
@RequestMapping(value = "/webintegral")
public class WebIntegralController {


    protected static final Logger logger = LogManager.getLogger(WebIntegralController.class);


    @Autowired
    IntegralService integralService;
    @Autowired
    IntegralDetailService integralDetailService;


    /**
     * 积分列表
     * @param integralManageDTO
     * @return
     */
    @PostMapping(value = "/getIntegralList")
    public String getIntegralList(@RequestBody IntegralManageDTO integralManageDTO) {
        logger.info("查询积分列表,integralManageDTO:"+JsonUtil.gsonStr(integralManageDTO));
        try {
            List<Map<String, Object>> ret = integralService.getIntegralList(integralManageDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("查询积分列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 积分充值
     * @param integralManageDTO
     * @return
     */
    @PostMapping(value = "/saveOrUpdateIntegral")
    public String saveOrUpdateIntegral(@RequestBody IntegralManageDTO integralManageDTO) {
        logger.info("充值积分,integralManageDTO:"+JsonUtil.gsonStr(integralManageDTO));
        try {
            integralService.logicIntergralRecharge(integralManageDTO);
            return Result.success("积分充值成功").toJSON();
        } catch (ServiceRuntimeException se) {
            logger.error(se.getMessage(),se);
            return Result.failure(se.getMessage()).toJSON();
        } catch (Exception e) {
            logger.error("积分充值异常",e);
            return Result.failure().toJSON();
        }
    }


    /**
     * 积分明细列表
     *
     * @param integralManageDTO
     * @return
     */
    @PostMapping(value = "/getIntegralDetailList")
    public String getIntegralDetailList(@RequestBody IntegralManageDTO integralManageDTO) {
        logger.info("积分明细列表,integralManageDTO："+JsonUtil.gsonStr(integralManageDTO));
        try {
            List<IntegralDetailVO> ret = integralDetailService.getIntegralDetailList(integralManageDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("积分明细列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 积分批量充值
     * @param list
     * @return
     */
    @PostMapping(value = "/batchRecharge")
    public String batchRecharge(@RequestBody List<IntegralEntity> list){
        try{
            integralService.batchRecharge(list);
            return Result.success("积分充值成功").toJSON();
        }catch (ServiceRuntimeException se){
            logger.error(se.getMessage(),se);
            return Result.failure(se.getMessage()).toJSON();
        }catch (Exception e){
            logger.error("积分批量充值",e);
            return Result.failure("积分充值失败").toJSON();
        }
    }
}
