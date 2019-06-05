package com.ydc.cgj.ydhc.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.ydhc.YdhcUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-ydhc-user")
public interface IYdhcUserService {

    /**
     * 校验用户是否注册
     *
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/ydhcUser/checkUserIsRegister")
    Result checkUserIsRegister(@RequestParam("mobilePhone") String mobilePhone);

    /**
     * 用户注册
     *
     * @param ydhcUser
     * @return
     */
    @PostMapping(value = "/ydhcUser/userRegister")
    Result userRegister(@RequestBody YdhcUser ydhcUser);

    /**
     * 用户登录
     *
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/ydhcUser/getUserByMobilePhone")
    YdhcUser getUserByMobilePhone(@RequestParam("mobilePhone") String mobilePhone);

    /**
     * 获取用户
     * @param ydhcUser
     * @return
     */
    @PostMapping(value = "/ydhcUser/updateYdhcUser")
    String updateYdhcUser(@RequestBody YdhcUser ydhcUser);
}
