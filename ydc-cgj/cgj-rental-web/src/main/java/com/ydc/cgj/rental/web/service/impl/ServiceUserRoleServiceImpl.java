package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.UserFeignService;
import com.ydc.cgj.rental.web.service.ServiceUserRoleService;
import com.ydc.model.cgj.ServiceUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-11-16 13:56
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
    public int updateServiceUserRole(ServiceUserRole record) {
        return userFeignService.updateServiceUserRole(record);
    }

    @Override
    public int updateOrInsert(ServiceUserRole record) {
        return userFeignService.updateOrInsert(record);
    }

    @Override
    public int insertServiceUserRole(ServiceUserRole record) {
        return userFeignService.insertServiceUserRole(record);
    }
}
