package com.ydc.cgj.ydhc.app.service.impl;

import com.ydc.cgj.ydhc.app.feignService.IYdhcUserService;
import com.ydc.cgj.ydhc.app.service.YdhcUserService;
import com.ydc.commom.result.Result;
import com.ydc.model.ydhc.YdhcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdhcUserServiceImpl implements YdhcUserService {

    @Autowired
    private IYdhcUserService iYdhcUserService;

    @Override
    public Result checkUserIsRegister(String mobilePhone) {
        return iYdhcUserService.checkUserIsRegister(mobilePhone);
    }

    @Override
    public Result userRegister(YdhcUser ydhcUser) {
        return iYdhcUserService.userRegister(ydhcUser);
    }

    @Override
    public YdhcUser getUserByMobilePhone(String mobilePhone) {
        return iYdhcUserService.getUserByMobilePhone(mobilePhone);
    }

    @Override
    public String updateYdhcUser(YdhcUser ydhcUser) {
        return iYdhcUserService.updateYdhcUser(ydhcUser);
    }
}
