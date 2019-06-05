package com.ydc.cgj.rentalb.app.service;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.LogLogin;
import com.ydc.model.cgj.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @create 2018-11-19 14:00
 **/
public interface UserService {

    /**
     * 获取用户信息根据手机号码
     *
     * @param mobilePhone
     * @return
     */
    User getUserByMobilePhoneNoStatus(String mobilePhone);

    /**
     * 保存登录日志
     *
     * @param record
     * @return
     */
    String saveLogLogin(Integer userId, String userName, String loginName, String roleName, HttpServletRequest request);

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @param validateType
     * @return
     */
    Result sendValidateCode(String mobilePhone,Integer validateType,Integer application);

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateType
     * @param validateCode
     * @return
     */
    Result checkValidateCode(String mobilePhone, Integer validateType,String validateCode);
}
