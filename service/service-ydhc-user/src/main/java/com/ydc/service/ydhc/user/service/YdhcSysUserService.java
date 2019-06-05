package com.ydc.service.ydhc.user.service;

import com.ydc.model.ydhc.YdhcSysUser;

public interface YdhcSysUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(YdhcSysUser record);

    int insertSelective(YdhcSysUser record);

    YdhcSysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcSysUser record);

    int updateByPrimaryKey(YdhcSysUser record);

    /**
     * 根据loginName查询用户
     *
     * @param loginName
     * @return
     */
    YdhcSysUser getUserByLoginName(String loginName);

    /**
     * 查询有效用户
     * @param mobilePhone
     * @return
     */
    YdhcSysUser getUserByMobilePhoneNoStatus(String mobilePhone);
}
