package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;

public interface ValidateCodeService {

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @param validateType
     * @return
     */
    Result sendValidateCode(String mobilePhone, Integer validateType, Integer application);

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateType
     * @param validateCode
     * @return
     */
    Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode);
}
