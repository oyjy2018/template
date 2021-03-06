package com.ydc.service.car.service.ydhc;

import com.ydc.model.ydhc.YdhcVehicleUsedImg;

import java.util.List;

public interface VehicleUsedImgService {

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int insert(YdhcVehicleUsedImg record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int insertSelective(YdhcVehicleUsedImg record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    YdhcVehicleUsedImg selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int updateByPrimaryKeySelective(YdhcVehicleUsedImg record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int updateByPrimaryKey(YdhcVehicleUsedImg record);

    /**
     * 批量插入数据
     * @param list
     */
    void batchInsert(List<YdhcVehicleUsedImg> list);

    /**
     * 根据车辆ID查询
     * @param vehicleId
     * @return
     */
    List<YdhcVehicleUsedImg> selectByVehicleId(Integer vehicleId);

    /**
     * 根据ID删除（软删）
     * @param id
     * @return
     */
    int deleteById(Integer id);
}
