package com.ydc.service.ydhc.user.service;

import com.ydc.commom.result.Result;

public interface YdhcValidateCodeService {
    /**
     * 发送短信
     *
     * @param mobilePhone
     * @return
     * @throws Exception
     */
    Result sendValidateCode(String mobilePhone) throws Exception;

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param code
     * @return
     * @throws Exception
     */
    Result checkValidateCode(String mobilePhone, String code) throws Exception;
}
