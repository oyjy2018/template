package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.user.ServiceUserRoleDao;
import com.ydc.model.cgj.ServiceUserRole;
import com.ydc.service.user.service.ServiceUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-11-16 13:56
 **/
@Service
public class ServiceUserRoleServiceImpl implements ServiceUserRoleService {

    @Autowired
    ServiceUserRoleDao serviceUserRoleDao;

    @Override
    public ServiceUserRole getServiceUserRoleByUserId(Integer userId) {
        return serviceUserRoleDao.getServiceUserRoleByUserId(userId);
    }

    @Override
    public int updateServiceUserRole(ServiceUserRole record) {
        return serviceUserRoleDao.updateServiceUserRole(record);
    }

    @Override
    public int updateOrInsert(ServiceUserRole record) {
        return serviceUserRoleDao.updateOrInsert(record);
    }

    @Override
    public int insertServiceUserRole(ServiceUserRole record) {
        return serviceUserRoleDao.insertServiceUserRole(record);
    }
}
