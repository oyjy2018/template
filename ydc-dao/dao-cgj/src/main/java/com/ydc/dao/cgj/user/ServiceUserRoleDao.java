package com.ydc.dao.cgj.user;

import com.ydc.model.cgj.ServiceUserRole;
import org.apache.ibatis.annotations.Param;

public interface ServiceUserRoleDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    int insertServiceUserRole(ServiceUserRole record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    int insertSelective(ServiceUserRole record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    ServiceUserRole selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    int updateByPrimaryKeySelective(ServiceUserRole record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-16 13:45:53
     */
    int updateServiceUserRole(ServiceUserRole record);

    /**
     * 获取用户不同服务角色
     * @param userId
     * @return
     */
    ServiceUserRole getServiceUserRoleByUserId(@Param("userId") Integer userId);

    /**
     * 新增或者更新
     * @param record
     * @return
     */
    int updateOrInsert(ServiceUserRole record);
}