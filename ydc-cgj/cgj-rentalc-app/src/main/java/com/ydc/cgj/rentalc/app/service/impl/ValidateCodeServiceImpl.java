package com.ydc.cgj.rentalc.app.service.impl;

import com.ydc.cgj.rentalc.app.feignService.ValidateCodeFeignService;
import com.ydc.cgj.rentalc.app.service.ValidateCodeService;
import com.ydc.commom.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private ValidateCodeFeignService validateCodeFeignService;

    @Override
    public Result sendValidateCode(String mobilePhone, Integer validateType, Integer application) {
        return validateCodeFeignService.sendValidateCode(mobilePhone, validateType, application);
    }

    @Override
    public Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) {
        return validateCodeFeignService.checkValidateCode(mobilePhone, validateType, validateCode);
    }
}
