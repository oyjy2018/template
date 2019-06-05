package com.ydc.service.ydhc.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.model.ydhc.YdhcUser;
import com.ydc.service.ydhc.user.service.YdhcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ydhcUser")
public class YdhcUserController {

    @Autowired
    private YdhcUserService ydhcUserService;

    /**
     * 校验用户是否注册
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/checkUserIsRegister")
    Result checkUserIsRegister(String mobilePhone){
        return ydhcUserService.checkUserIsRegister(mobilePhone);
    }

    /**
     * 用户注册
     * @param ydhcUser
     * @return
     */
    @PostMapping(value = "/userRegister")
    Result userRegister(@RequestBody YdhcUser ydhcUser){
        return ydhcUserService.insert(ydhcUser) <= 0 ? Result.failure("注册失败，请稍后尝试") : Result.success();
    }

    /**
     * 获取用户
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/getUserByMobilePhone")
    YdhcUser getUserByMobilePhone(String mobilePhone){
        return ydhcUserService.getUserByMobilePhone(mobilePhone);
    }

    /**
     * 获取用户
     * @param ydhcUser
     * @return
     */
    @PostMapping(value = "/updateYdhcUser")
    String updateYdhcUser(@RequestBody YdhcUser ydhcUser){
        ydhcUserService.updateByPrimaryKeySelective(ydhcUser);
        return Result.success().toJSON();
    }
}
