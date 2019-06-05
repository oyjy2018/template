package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.IntegralService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.integral.IntegralDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 积分
 *
 * @author gongjin
 * @create 2018-09-07 13:59
 **/
@RestController
@RequestMapping(value = "/h5integral")
public class IntegralController {

    protected static final Logger logger = LogManager.getLogger(IntegralController.class);

    @Autowired
    IntegralService integralService;

    /**
     * 我的积分
     *
     * @return
     */
    @PostMapping(value = "/getMyIntegral")
    public String getMyIntegral() {
        try {
            return integralService.getMyIntegral(SubjectUtil.getMemberId());
        } catch (Exception e) {
            logger.error("我的积分异常", e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 我的积分-明细（收入和支出）
     *
     * @return
     */
    @PostMapping(value = "/getIntegralDetail")
    public String getIntegralDetail(@RequestParam("data") String data) {
        logger.info("subject:{},integralDTO:{}","H5 我的积分-明细（收入和支出）",data);
        try {
            IntegralDTO integralDTO = JSONObject.parseObject(data,IntegralDTO.class);
            integralDTO.setMemberId(SubjectUtil.getMemberId());
            return integralService.getIntegralDetail(integralDTO);
        } catch (Exception e) {
            logger.error("我的积分-明细（收入和支出）异常", e);
            return Result.failure().toJSON();
        }
    }
}
