package com.ydc.dao.cgj.rental;

import com.ydc.model.cgj.rental.RentalRefuelInfo;

public interface RentalRefuelInfoDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    int insert(RentalRefuelInfo record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    int insertSelective(RentalRefuelInfo record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    RentalRefuelInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    int updateByPrimaryKeySelective(RentalRefuelInfo record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-22 10:29:05
     */
    int updateByPrimaryKey(RentalRefuelInfo record);

    /**
     * 根据机务单id获取加油信息
     * @param maintenanceId
     * @return
     */
    RentalRefuelInfo selectRefuelByMaintenanceId(Integer maintenanceId);
}