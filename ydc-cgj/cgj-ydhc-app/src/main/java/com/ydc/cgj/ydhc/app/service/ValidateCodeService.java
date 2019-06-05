package com.ydc.cgj.ydhc.app.service;

import com.ydc.commom.result.Result;

public interface ValidateCodeService {

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @return
     */
    Result sendValidateCode(String mobilePhone);

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateCode
     * @return
     */
    Result checkValidateCode(String mobilePhone, String validateCode);
}
