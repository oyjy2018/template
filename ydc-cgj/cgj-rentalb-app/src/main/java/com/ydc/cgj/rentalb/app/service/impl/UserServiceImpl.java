package com.ydc.cgj.rentalb.app.service.impl;

import com.ydc.cgj.rentalb.app.feignService.UserFeignService;
import com.ydc.cgj.rentalb.app.service.UserService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.IPAddress;
import com.ydc.commom.util.IPUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.model.cgj.LogLogin;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author
 * @create 2018-11-19 14:01
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    UserFeignService userFeignService;

    @Override
    public User getUserByMobilePhoneNoStatus(String mobilePhone) {
        return userFeignService.getUserByMobilePhoneNoStatus(mobilePhone);
    }

    @Override
    public String saveLogLogin(Integer userId, String userName, String loginName, String roleName, HttpServletRequest request) {
        LogLogin record = new LogLogin();
        record.setUserId(userId);
        record.setUserName(userName);
        record.setLoginName(loginName);
        record.setRoleName(roleName);
        record.setLoginTime(new Date());
        record.setUserApplication(3);
        String ip = null;
        String address = null;
        try {
            ip = SystemUtil.getIpAddress(request);
            IPAddress ipaddress = IPUtil.getAddressByIP(ip);
            if (ipaddress != null) {
                address = ipaddress.getAddress();
            }
            record.setIpAddress(ip + ":" + address);
        } catch (Exception e) {
            logger.error("获取请求ip异常", e);
        }
        return userFeignService.saveLogLogin(record);
    }



    @Override
    public Result sendValidateCode(String mobilePhone, Integer validateType, Integer application) {
        return userFeignService.sendValidateCode(mobilePhone,validateType,application);
    }

    @Override
    public Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) {
        return userFeignService.checkValidateCode(mobilePhone,validateType,validateCode);
    }
}
