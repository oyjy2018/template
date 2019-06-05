package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.VehicleDTO;
import com.ydc.commom.view.vo.cgj.VehicleVO;
import com.ydc.model.cgj.Vehicle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VehicleDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    Integer insert(Vehicle record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    int insertSelective(Vehicle record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    Vehicle selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    int updateByPrimaryKeySelective(Vehicle record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 11:52:20
     */
    Integer updateByPrimaryKey(Vehicle record);

    /**
     * 车辆列表
     * @return
     */
    List<Map<String,Object>> getVehicleList(@Param("vehicleDTO") VehicleDTO vehicleDTO);

    /**
     * 查询车辆信息
     * @param carPlate
     * @param memberId
     * @return
     */
    Vehicle selectByCarPlateAndMemberId(@Param("carPlate") String carPlate,@Param("memberId") Integer memberId);


    Vehicle selectByCarPlate(@Param("carPlate") String carPlate);

    /**
     * 车品牌
     * @return
     */
    List<Map<String, Object>> getBrandList();

    /**
     * 车系
     * @return
     */
    List<Map<String, Object>> getSeriesList();

    /**
     * 车版本
     * @return
     */
    List<Map<String,Object>> getModelList();

    /**
     * 获取会员车辆信息
     * @param memberId
     * @return
     */
    Vehicle getVehicleByMemberId(Integer memberId);

    /**
     * 查询车辆详情
     * @param vehicleId
     * @return
     */
    VehicleVO getVehicleVOById(Integer vehicleId);
}