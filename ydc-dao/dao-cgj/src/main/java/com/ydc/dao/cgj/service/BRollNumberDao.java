package com.ydc.dao.cgj.service;

import com.ydc.model.cgj.BRollNumber;

public interface BRollNumberDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    Integer insert(BRollNumber record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    int insertSelective(BRollNumber record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    BRollNumber selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    int updateByPrimaryKeySelective(BRollNumber record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    int updateByPrimaryKey(BRollNumber record);
}