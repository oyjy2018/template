package com.ydc.service.user.service;


import java.util.Set;

/**
 * @author
 * @create 2018-11-14 17:53
 **/
public interface UserRoleFunctionService {

    /**
     * 角色id和服务标识查询角色
     * @param roleId
     * @return
     */
    Set<String> getFunInfoById(Integer roleId);

}
