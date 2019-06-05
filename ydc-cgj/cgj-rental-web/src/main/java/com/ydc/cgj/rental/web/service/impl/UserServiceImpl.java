package com.ydc.cgj.rental.web.service.impl;

import com.ydc.beans.mq.cache.service.CacheSendMessageService;
import com.ydc.cgj.rental.web.feignService.UserFeignService;
import com.ydc.cgj.rental.web.service.UserService;
import com.ydc.commom.util.IPAddress;
import com.ydc.commom.util.IPUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.model.cgj.Dd;
import com.ydc.model.cgj.LogLogin;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Set;

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
    public Dd getDdConfigByServiceIdentifying(String serviceIdentifying) {
        return userFeignService.getDdConfigByServiceIdentifying(serviceIdentifying);
    }

    @Override
    public String saveLogLogin(Integer userId, String userName, String loginName, String roleName, HttpServletRequest request) {
        LogLogin record = new LogLogin();
        record.setUserId(userId);
        record.setUserName(userName);
        record.setLoginName(loginName);
        record.setRoleName(roleName);
        record.setLoginTime(new Date());
        record.setUserApplication(2);
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
    public Set<String> getFunInfoById(Integer roleId) {
        return userFeignService.getFunInfoById(roleId);
    }

    /**
     * 返回所有有效用户
     * @return
     */
    @Override
    public String getValidUser() {
        return userFeignService.getValidUser();
    }

    /**
     * 查询人员列表
     * @param userQueryDTO
     * @return
     */
    @Override
    public String getUserList(UserQueryDTO userQueryDTO) {
        return userFeignService.getUserList(userQueryDTO);
    }

    /**
     * 查询人员详情
     * @param id
     * @return
     */
    @Override
    public String getUserInfo(Integer id) {
        return userFeignService.getUserInfo(id);
    }

    /**
     * 修改人员
     * @param userUpdateDTO
     * @return
     */
    @Override
    public String updateUser(UserUpdateDTO userUpdateDTO) {
        return userFeignService.updateUser(userUpdateDTO);
    }

    /**
     * 增加人员
     * @param userInsertDTO
     * @return
     */
    @Override
    public String insertUser(UserInsertDTO userInsertDTO) {
        return userFeignService.insertUser(userInsertDTO);
    }

    /**
     * 获取机构权限树及已选机构
     * @param userId
     * @return
     */
    @Override
    public String getViewOrgTreeAndChecked(Integer userId) {
        return userFeignService.getViewOrgTreeAndChecked(userId);
    }
}
