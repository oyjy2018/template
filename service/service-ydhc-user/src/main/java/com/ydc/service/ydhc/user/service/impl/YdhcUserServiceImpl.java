package com.ydc.service.ydhc.user.service.impl;

import com.ydc.commom.result.Result;
import com.ydc.dao.ydhc.YdhcUserDao;
import com.ydc.model.ydhc.YdhcUser;
import com.ydc.service.ydhc.user.service.YdhcUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class YdhcUserServiceImpl implements YdhcUserService {

    @Resource
    YdhcUserDao ydhcUserDao;

    @Override
    public Result checkUserIsRegister(String mobilePhone) {
        YdhcUser ydhcUser = this.getUserByMobilePhone(mobilePhone);
        return ydhcUser == null ? Result.failure("未注册") : Result.success("已注册");
    }

    @Override
    public Integer insert(YdhcUser ydhcUser) {
        return ydhcUserDao.insert(ydhcUser);
    }

    @Override
    public YdhcUser getUserByMobilePhone(String mobilePhone) {
        return ydhcUserDao.getUserByMobilePhone(mobilePhone);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcUser ydhcUser) {
        return ydhcUserDao.updateByPrimaryKeySelective(ydhcUser);
    }
}
