package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.IntegralService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 积分管理
 *
 * @author gongjin
 * @create 2018-09-04 19:13
 **/
@RestController
@RequestMapping(value = "/webintegral")
public class IntegralController {

    private static final Logger logger = LogManager.getLogger(IntegralController.class);

    @Autowired
    IntegralService integralService;

    /**
     * 积分列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getIntegralList")
    public String getIntegralList(@RequestParam("data") String data) {
        logger.info("subject:{},integralManageDTO:{}", "积分列表", data);
        IntegralManageDTO integralManageDTO = JSONObject.parseObject(data,IntegralManageDTO.class);
        return integralService.getIntegralList(integralManageDTO.changeDTO());
    }

    /**
     * 积分充值
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/saveOrUpdateIntegral")
    public String saveOrUpdateIntegral(@RequestParam("data") String data ) {
        logger.info("subject:{},integralManageDTO:{}", "积分充值", data);
        IntegralManageDTO integralManageDTO = JSONObject.parseObject(data,IntegralManageDTO.class);
        if(StringUtil.isEmpty(integralManageDTO.getIntegralTypeAcquire().trim())){
            return Result.failure("积分充值类型不能为空").toJSON();
        }
        if(StringUtil.isEmpty(integralManageDTO.getUsableIntegral())){
            return Result.failure("充值积分不能为空").toJSON();
        }
        if(StringUtil.isEmpty(integralManageDTO.getMemberId())){
            return Result.failure("会员id不能为空").toJSON();
        }
        User user = WebShiroTokenManager.getUser();
        integralManageDTO.setOperatorId(user.getId());
        return integralService.saveOrUpdateIntegral(integralManageDTO.changeDTO());
    }

    /**
     * 积分明细列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getIntegralDetailList")
    public String getIntegralDetailList(@RequestParam("data") String data) {
        logger.info("subject:{},integralManageDTO:{}", "积分明细列表", data);
        IntegralManageDTO integralManageDTO = JSONObject.parseObject(data,IntegralManageDTO.class);
        return integralService.getIntegralDetailList(integralManageDTO.changeDTO());
    }
}
