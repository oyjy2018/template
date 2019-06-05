package com.ydc.service.ydhc.user.service;

import com.ydc.commom.result.Result;
import com.ydc.model.ydhc.YdhcUser;

public interface YdhcUserService {

    /**
     * 校验用户是否注册
     * @param mobilePhone
     * @return
     */
    Result checkUserIsRegister(String mobilePhone);

    /**
     * 用户注册
     * @param ydhcUser
     * @return
     */
    Integer insert(YdhcUser ydhcUser);

    /**
     * 获取用户
     * @param mobilePhone
     * @return
     */
    YdhcUser getUserByMobilePhone(String mobilePhone);

    /**
     * 更新用户
     * @param ydhcUser
     * @return
     */
    int updateByPrimaryKeySelective(YdhcUser ydhcUser);
}
