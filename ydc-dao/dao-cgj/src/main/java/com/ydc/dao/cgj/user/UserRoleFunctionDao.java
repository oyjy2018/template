package com.ydc.dao.cgj.user;

import com.ydc.model.cgj.Menus;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户角色功能
 *
 * @author
 * @create 2018-11-14 17:48
 **/
public interface UserRoleFunctionDao {

    /**
     * 角色id和服务标识查询角色
     * @param roleId
     * @return
     */
    Set<String> getFunInfoById(@Param("roleId") Integer roleId);

    /**
     * 查询有效功能
     * @return
     */
    List<Map<String,Object>> getAllFunctions();

    /**
     * 查询有效角色
     * @return
     */
    List<Map<String,Object>> getAllRoles();

    /**
     * 角色查询所有功能
     * @return
     */
    List<Map<String,Object>> getAllRoleFun();

    /**
     * 获取版本号
     * @return
     */
    Double getVersion();

    /**
     * 获取所有菜单
     * @return
     */
    List<Map<String,Object>> getAllMenus();

    /**
     * 功能菜单
     * @param list
     * @return
     */
    List<Menus> getAllFunMenusList(List<String> list);

    /**
     * 角色功能菜单
     * @return
     */
    List<Map<String,Object>> getRoleFunMenusList();
}
