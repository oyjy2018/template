package com.ydc.service.user.service;

import com.ydc.model.cgj.ServiceUserRole;

/**
 * @author
 * @create 2018-11-16 13:54
 **/
public interface ServiceUserRoleService {


    /**
     * 获取用户不同服务角色
     * @param userId
     * @return
     */
    ServiceUserRole getServiceUserRoleByUserId(Integer userId);


    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-16 13:45:53
    */
    int updateServiceUserRole(ServiceUserRole record);


    /**
     * 新增或者更新
     * @param record
     * @return
     */
    int updateOrInsert(ServiceUserRole record);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    int insertServiceUserRole(ServiceUserRole record);
}
