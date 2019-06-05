package com.ydc.dao.cgj.rental;

import com.ydc.model.cgj.rental.Organization;

import java.util.List;

public interface OrganizationDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    int insert(Organization record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    int insertSelective(Organization record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    Organization selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    int updateByPrimaryKeySelective(Organization record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-19 10:31:03
     */
    int updateByPrimaryKey(Organization record);

    /**
     * 查询上级机构
     * @return
     */
    List<Organization> getOrganization();
}