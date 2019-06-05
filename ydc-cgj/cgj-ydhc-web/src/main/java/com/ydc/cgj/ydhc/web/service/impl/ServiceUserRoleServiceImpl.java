package com.ydc.cgj.ydhc.web.service.impl;

import com.ydc.cgj.ydhc.web.feignService.UserFeignService;
import com.ydc.cgj.ydhc.web.service.ServiceUserRoleService;
import com.ydc.model.cgj.ServiceUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-12-05 17:16
 **/
@Service
public class ServiceUserRoleServiceImpl implements ServiceUserRoleService {

    @Autowired
    UserFeignService userFeignService;

    @Override
    public ServiceUserRole getServiceUserRoleByUserId(Integer userId) {
        return userFeignService.getServiceUserRoleByUserId(userId);
    }

    @Override
    public int insertServiceUserRole(ServiceUserRole record) {
        return userFeignService.insertServiceUserRole(record);
    }
}
