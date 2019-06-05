package com.ydc.cgj.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.app.feignService.ILoanApplyFeignService;
import com.ydc.cgj.app.service.LoanApplyService;
import com.ydc.commom.constant.LoanApplyConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplyServiceImpl implements LoanApplyService {

    @Autowired
    ILoanApplyFeignService iLoanApplyFeignService;

    @Override
    public String getApplyNum(String data) {
        JSONObject json = JSONObject.parseObject(data);
        Integer appLyProuctType = 0;
        Integer applyNum = 0;
        if(json.containsKey("appLyProuctType") && StringUtil.isNotEmpty(json.get("appLyProuctType"))){
            appLyProuctType = Integer.valueOf(json.get("appLyProuctType").toString());
            applyNum = (Integer) RedisUtil.redisGet(RedisConstant.CGJ_APPLY_SHOW_NUM + appLyProuctType);
            if(applyNum == null){
                return iLoanApplyFeignService.getApplyNum(data);
            }
        }
        Object object = applyNum.toString();
        return Result.success(object).toJSON();
    }

    @Override
    public String submitApplication(String data) {
        return iLoanApplyFeignService.submitApplication(data);
    }
}
