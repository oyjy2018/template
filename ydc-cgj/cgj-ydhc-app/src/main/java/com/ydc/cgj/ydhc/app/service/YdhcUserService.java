package com.ydc.cgj.ydhc.app.service;

import com.ydc.commom.result.Result;
import com.ydc.model.ydhc.YdhcUser;

public interface YdhcUserService {
    /**
     * 校验用户是否注册
     *
     * @param mobilePhone
     * @return
     */
    Result checkUserIsRegister(String mobilePhone);

    /**
     * 用户注册
     *
     * @param ydhcUser
     * @return
     */
    Result userRegister(YdhcUser ydhcUser);

    /**
     * 获取用户
     *
     * @param mobilePhone
     * @return
     */
    YdhcUser getUserByMobilePhone(String mobilePhone);

    /**
     * 获取用户
     * @param ydhcUser
     * @return
     */
    String updateYdhcUser(YdhcUser ydhcUser);
}
