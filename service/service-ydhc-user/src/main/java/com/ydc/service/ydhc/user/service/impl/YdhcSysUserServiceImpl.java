package com.ydc.service.ydhc.user.service.impl;

import com.ydc.dao.ydhc.YdhcSysUserDao;
import com.ydc.model.ydhc.YdhcSysUser;
import com.ydc.service.ydhc.user.service.YdhcSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdhcSysUserServiceImpl implements YdhcSysUserService {

    @Autowired
    private YdhcSysUserDao ydhcSysUserDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ydhcSysUserDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YdhcSysUser record) {
        return ydhcSysUserDao.insert(record);
    }

    @Override
    public int insertSelective(YdhcSysUser record) {
        return ydhcSysUserDao.insertSelective(record);
    }

    @Override
    public YdhcSysUser selectByPrimaryKey(Integer id) {
        return ydhcSysUserDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcSysUser record) {
        return ydhcSysUserDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcSysUser record) {
        return ydhcSysUserDao.updateByPrimaryKey(record);
    }

    @Override
    public YdhcSysUser getUserByLoginName(String loginName) {
        return ydhcSysUserDao.getUserByLoginName(loginName);
    }

    @Override
    public YdhcSysUser getUserByMobilePhoneNoStatus(String mobilePhone) {
        return ydhcSysUserDao.getUserByMobilePhoneNoStatus(mobilePhone);
    }
}
