package com.ydc.dao.cgj.rental;

import com.ydc.model.cgj.rental.RentalCarWashInfo;

public interface RentalCarWashInfoDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    int insert(RentalCarWashInfo record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    int insertSelective(RentalCarWashInfo record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    RentalCarWashInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    int updateByPrimaryKeySelective(RentalCarWashInfo record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-22 10:29:04
     */
    int updateByPrimaryKey(RentalCarWashInfo record);

    /**
     * 根据机务单id获取洗车信息
     * @param maintenanceId
     * @return
     */
    RentalCarWashInfo selectWashInfoByMaintenanceId(Integer maintenanceId);
}