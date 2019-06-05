package com.ydc.service.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.integral.IntegralDTO;
import com.ydc.commom.view.vo.cgj.order.IntegralDetailVO;
import com.ydc.model.cgj.Integral;
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
 * H5积分控制器
 *
 * @author gongjin
 * @create 2018-09-07 14:31
 **/
@RestController
@RequestMapping(value = "/h5integral")
public class H5IntegralController {

    protected static final Logger logger = LogManager.getLogger(WebIntegralController.class);

    @Autowired
    IntegralService integralService;
    @Autowired
    IntegralDetailService integralDetailService;

    /**
     * 我的积分
     *
     * @param memberId
     * @return
     */
    @PostMapping(value = "/getMyIntegral")
    public String getMyIntegral(@RequestParam(value = "memberId") Integer memberId) {
        logger.info("subject:我的积分参数,memberId:"+memberId);
        try {
            Integral integral = integralService.getIntegralByMumberId(memberId);
            Map<String, Object> result = new HashMap<>();
            result.put("usableIntegral", integral == null ? 0.00 : integral.getUsableIntegral());
            logger.info("subject:{},result:{}","我的积分参数",JsonUtil.gsonStr(result));
            return Result.success(result).toJSON();
        } catch (Exception e) {
            logger.error("我的积分异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 我的积分-明细（收入和支出）
     * @return
     */
    @PostMapping(value = "/getIntegralDetail")
    public String getIntegralDetail(@RequestBody IntegralDTO integralDTO) {
        logger.info("我的积分-明细（收入和支出）:"+JsonUtil.gsonStr(integralDTO));
        try {
            List<IntegralDetailVO> mapList = integralDetailService.h5GetIntegralDetailList(integralDTO);
            return Result.success(mapList).toJSON();
        } catch (Exception e) {
            logger.error("我的积分异常",e);
            return Result.failure().toJSON();
        }
    }
}
