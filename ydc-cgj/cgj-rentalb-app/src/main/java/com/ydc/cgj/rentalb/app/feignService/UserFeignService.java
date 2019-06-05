package com.ydc.cgj.rentalb.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.LogLogin;
import com.ydc.model.cgj.ServiceUserRole;
import com.ydc.model.cgj.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author
 * @create 2018-11-19 13:56
 **/
@Service
@FeignClient(value = "service-user")
public interface UserFeignService {

    /**
     * 获取用户信息根据手机号码
     *
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/user/getUserByMobilePhoneNoStatus")
    User getUserByMobilePhoneNoStatus(@RequestParam(value = "mobilePhone") String mobilePhone);

    /**
     * 保存登录日志
     *
     * @param record
     * @return
     */
    @PostMapping(value = "/user/saveLogLogin")
    String saveLogLogin(LogLogin record);

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @param validateType
     * @return
     */
    @PostMapping(value = "/validateCode/sendValidateCode")
    Result sendValidateCode(@RequestParam("mobilePhone") String mobilePhone,
                            @RequestParam("validateType") Integer validateType,
                            @RequestParam(value = "application") Integer application);

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateType
     * @param validateCode
     * @return
     */
    @GetMapping(value = "/validateCode/checkValidateCode")
    Result checkValidateCode(
            @RequestParam("mobilePhone") String mobilePhone,
            @RequestParam("validateType") Integer validateType,
            @RequestParam("validateCode") String validateCode);


    /**
     * 获取用户服务角色
     * @param userId
     * @return
     */
    @PostMapping(value = "/user/getServiceUserRoleByUserId")
    ServiceUserRole getServiceUserRoleByUserId(@RequestParam("userId") Integer userId);

    /**
     * 新增用户服务角色
     * @param record
     * @return
     */
    @PostMapping(value = "/user/insertServiceUserRole")
    int insertServiceUserRole(@RequestBody ServiceUserRole record);
}
