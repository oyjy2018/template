package com.ydc.service.user.service;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.ValidateCode;

/**
 * 短信service
 */
public interface ValidateCodeService {

    /**
     * 发送短信
     *
     * @param mobilePhone
     * @param validateType
     * @return
     * @throws Exception
     */
    Result sendValidateCode(String mobilePhone, Integer validateType) throws Exception;

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateType
     * @param code
     * @return
     * @throws Exception
     */
    Result checkValidateCode(String mobilePhone, Integer validateType, String code) throws Exception;
}
