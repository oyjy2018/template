package com.ydc.service.user.controller;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.constant.ShiroConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.ValidateCode;
import com.ydc.service.user.service.DictionaryDetailService;
import com.ydc.service.user.service.MemberService;
import com.ydc.service.user.service.ValidateCodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    private final Logger logger = LogManager.getLogger(ValidateCodeController.class);

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    @Autowired
    private MemberService memberService;

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @param validateType
     * @return
     */
    @PostMapping("/sendValidateCode")
    public Result sendValidateCode(@RequestParam(value = "mobilePhone") String mobilePhone,
                                   @RequestParam(value = "validateType") Integer validateType,
                                   @RequestParam(value = "application") Integer application) throws Exception {
        logger.info("发送验证码, mobilePhone: " + mobilePhone + ";validateType: " + validateType);
        if (ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_1.getKey().equals(validateType)){
            //发送注册验证码时，需要检验用户是否存在
            Result result = memberService.checkMobileIsRegister(mobilePhone, application);
            if (result.getCode() != ResultConstant.RESULT_CODE_SUCCESS){
                return result;
            }
        }else if (ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_3.getKey().equals(validateType)){
            //发送登录验证码时，需要检验密码错误次数（暂时只车管家校验）
            if (MemberConstant.APPLICATION_CGJ == application){
                Object object = RedisUtil.redisGet(RedisConstant.CGJ_PASSWORD_FAULT_KEY.concat(mobilePhone));
                if (object != null && (int)object >= ShiroConstant.PASSWORD_FAULT_TIMES){
                    return Result.failure("密码输入错误连续超过3次，账户已被锁定，次日自动解锁");
                }
            }
        }

        //发送短信验证码是否打开
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW));
        //是否是体验账号
        DictionaryDetail testAccount = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.TEST_ACCOUNT, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.TEST_ACCOUNT, DictionaryConstant.DICT_CODE_DXFW));
        if ((dictionaryDetail == null || !("1").equals(dictionaryDetail.getDictValue())) ||
                (testAccount != null && testAccount.getDictValue() != null && testAccount.getDictValue().contains(mobilePhone))){
            return Result.getResult(ResultConstant.RESULT_CODE_SUCCESS, "短信发送成功");
        }
        //发送短信
        return validateCodeService.sendValidateCode(mobilePhone, validateType);
    }

    /**
     * 检验验证码
     *
     * @param mobilePhone
     * @param validateType
     * @param validateCode
     * @return
     * @throws Exception
     */
    @GetMapping("/checkValidateCode")
    public Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) throws Exception {
        logger.info("检验验证码, mobilePhone: " + mobilePhone + ";validateType: " + validateType + ";validateCode: " + validateCode);
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW));
        //是否是体验账号
        DictionaryDetail testAccount = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.TEST_ACCOUNT, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.TEST_ACCOUNT, DictionaryConstant.DICT_CODE_DXFW));
        if ((dictionaryDetail == null || !("1").equals(dictionaryDetail.getDictValue())) ||
                (testAccount != null && testAccount.getDictValue() != null && testAccount.getDictValue().contains(mobilePhone))){
            return Result.success("验证码正确");
        }
        return validateCodeService.checkValidateCode(mobilePhone, validateType, validateCode);
    }
}
