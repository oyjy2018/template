package com.ydc.cgj.ydhc.web.service;

import com.ydc.model.cgj.ServiceUserRole;

/**
 * @author
 * @create 2018-12-05 17:15
 **/
public interface ServiceUserRoleService {

    /**
     * 获取用户不同服务角色
     * @param userId
     * @return
     */
    ServiceUserRole getServiceUserRoleByUserId(Integer userId);


    /**
     * 新增用户服务角色
     * @param record
     * @return
     */
    int insertServiceUserRole(ServiceUserRole record);
}
