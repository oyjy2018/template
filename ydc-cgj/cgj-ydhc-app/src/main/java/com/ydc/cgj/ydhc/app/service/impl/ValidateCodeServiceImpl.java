package com.ydc.cgj.ydhc.app.service.impl;

import com.ydc.cgj.ydhc.app.feignService.IValidateCodeService;
import com.ydc.cgj.ydhc.app.service.ValidateCodeService;
import com.ydc.commom.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private IValidateCodeService validateCodeService;

    @Override
    public Result sendValidateCode(String mobilePhone) {
        return validateCodeService.sendValidateCode(mobilePhone);
    }

    @Override
    public Result checkValidateCode(String mobilePhone, String validateCode) {
        return validateCodeService.checkValidateCode(mobilePhone, validateCode);
    }
}
