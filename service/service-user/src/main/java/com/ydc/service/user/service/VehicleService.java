package com.ydc.service.user.service;

import com.ydc.commom.view.dto.cgj.VehicleDTO;
import com.ydc.commom.view.vo.cgj.VehicleVO;
import com.ydc.model.cgj.Vehicle;


import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2018-10-30 12:06
 **/
public interface VehicleService {

    /**
     * 车辆列表
     * @return
     */
    List<Map<String,Object>> getVehicleList(VehicleDTO vehicleDTO);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     */
    Vehicle selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    Integer updateByPrimaryKey(Vehicle record);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     */
    Integer insert(Vehicle record);

    /**
     * 查询车辆信息
     * @param carPlate
     * @param memberId
     * @return
     */
    Vehicle selectByCarPlateAndMemberId(String carPlate,Integer memberId);


    /**
     * 查询车辆信息
     * @param carPlate
     * @return
     */
    Vehicle selectByCarPlate(String carPlate);

    /**
     * 查询车辆详情
     * @param vehicleId
     * @return
     */
    VehicleVO getVehicleVOById(Integer vehicleId);
}
