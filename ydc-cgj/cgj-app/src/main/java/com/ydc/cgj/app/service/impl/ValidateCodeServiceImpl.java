package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IValidateCodeService;
import com.ydc.cgj.app.service.ValidateCodeService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private IValidateCodeService validateCodeService;

    @Override
    public Result sendValidateCode(String mobilePhone, Integer validateType, Integer application) {
        return validateCodeService.sendValidateCode(mobilePhone, validateType, application);
    }

    @Override
    public Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) {
        if (validateCode == null) return Result.failure("请输入验证码");
        return validateCodeService.checkValidateCode(mobilePhone, validateType, validateCode);
    }
}
