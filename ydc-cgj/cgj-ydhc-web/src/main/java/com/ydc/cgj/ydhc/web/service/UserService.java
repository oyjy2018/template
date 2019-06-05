package com.ydc.cgj.ydhc.web.service;


import com.ydc.model.cgj.Dd;
import com.ydc.model.cgj.User;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    String updateUser(User user);

    /**
     * 获取用户信息
     *
     * @param loginName
     * @return
     */
    User getUserByLoginName(@RequestParam(value = "loginName") String loginName);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    String insert(User user);


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
     * @param userId
     * @param userName
     * @param loginName
     * @param roleName
     * @param request
     * @return
     */
    String saveLogLogin(Integer userId, String userName, String loginName, String roleName, HttpServletRequest request);


    /**
     * 获取钉钉配置
     * @return
     */
    Dd getDdConfigByServiceIdentifying(String serviceIdentifying);

}
