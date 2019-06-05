package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.LoanApplyService;
import com.ydc.cgj.app.service.ValidateCodeService;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.ValidateCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

/**
 * 借款申请
 */
@RestController
@RequestMapping(value = "/loanApply")
public class LoanApplyController {
    protected static final Logger logger = LogManager.getLogger(LoanApplyController.class);

    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private LoanApplyService loanApplyService;

    @PostMapping(value = "/submitApplication")
    public String submitApplication(@RequestParam("data") String data) {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        Integer validateType = jsonObject.getInteger("validateType");
        String validateCode = jsonObject.getString("validateCode");
        Member member = SubjectUtil.getMember();
        if(member == null){
            return Result.failure("请先登录").toJSON();
        }
        if(!mobilePhone.equals(member.getMobilePhone()) || StringUtil.isNotEmpty(validateCode)){
            Result result = this.checkValidateCode(mobilePhone, validateType, validateCode);
            if (ResultConstant.RESULT_CODE_SUCCESS != result.getCode()) {
                return Result.failure("验证码错误").toJSON();
            }
        }
        logger.info("申请人登录信息 id :"+ member.getId()+ "  username:" + member.getMemberName());
        jsonObject.put("memberId",member.getId());
        jsonObject.put("username",member.getMemberName());
//        jsonObject.put("memberId","967");
//        jsonObject.put("username","测试一");
        logger.info("申请人登录信息 id :"+ jsonObject.get("memberId")+ "  username:" + jsonObject.get("username"));
        data = jsonObject.toString();
        return loanApplyService.submitApplication(data);
    }

    //校验验证码
    private Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) {
        logger.info("subject:{}, mobilePhone:{}, validateType:{}, validateCode:{}","校验验证码", mobilePhone, validateType, validateCode);
        Result result = validateCodeService.checkValidateCode(mobilePhone, validateType, validateCode);
        if (ResultConstant.RESULT_CODE_SUCCESS == result.getCode()) {
            String sign = SystemUtil.getUUID();
            RedisUtil.redisSet(RedisConstant.SIGNVALIDATECODE_SIGN.concat(mobilePhone), sign, 600L);
            result.setData(sign);
        }
        return result;
    }

    @PostMapping(value = "/getApplyNum")
    public String getApplyNum(@RequestParam("data") String data) {
        String applyNum = loanApplyService.getApplyNum(data);
        return applyNum;
    }
}
