package com.ydc.cgj.ydhc.web.service.impl;

import com.ydc.cgj.ydhc.web.feignService.UserFeignService;
import com.ydc.cgj.ydhc.web.service.UserService;
import com.ydc.commom.util.IPAddress;
import com.ydc.commom.util.IPUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.model.cgj.Dd;
import com.ydc.model.cgj.LogLogin;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户
 *
 * @author gongjin
 * @create 2018-09-06 11:49
 **/
@Service("userService")
public class UserServiceImpl implements UserService {


    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    UserFeignService userFeignService;


    @Override
    public String updateUser(User user) {
        return userFeignService.updateUser(user);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return userFeignService.getUserByLoginName(loginName);
    }

    @Override
    public String insert(User user) {
        return userFeignService.insert(user);
    }

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
        record.setUserApplication(4);
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
    public Dd getDdConfigByServiceIdentifying(String serviceIdentifying) {
        return userFeignService.getDdConfigByServiceIdentifying(serviceIdentifying);
    }
}
