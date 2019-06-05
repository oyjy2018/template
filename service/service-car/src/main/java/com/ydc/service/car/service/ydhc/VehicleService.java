package com.ydc.service.car.service.ydhc;

import com.ydc.commom.view.dto.ydhc.VehicleDto;
import com.ydc.commom.view.vo.ydhc.VehicleVo;
import com.ydc.commom.view.vo.ydhc.YdhcVehicleVO;
import com.ydc.model.ydhc.YdhcVehicle;

import java.util.List;
import java.util.Map;

public interface VehicleService {

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int insert(YdhcVehicle record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int insertSelective(YdhcVehicle record);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int updateByPrimaryKeySelective(YdhcVehicle record);

    /**
     * 更新或保存车辆信息
     * @param req
     * @return
     */
    Integer saveOrUpdateVehicle(Map<String, Object> req);

    /**
     * 更新发布状态
     * @param req
     * @return
     */
    int updateReleaseStatusByIds(Map<String, String> req);

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    YdhcVehicle selectByPrimaryKey(Integer id);

    YdhcVehicle selectByTitle(String title);

    /**
     * 查询车辆信息列表
     * @param param
     * @return
     */
    List<YdhcVehicleVO> getYdhcVehicleList(Map<String, Object> param);

    /**
     * 查询车辆信息列表总数
     * @param param
     * @return
     */
    Map<String, Object> getYdhcVehicleListCount(Map<String, Object> param);

    /**
     * 获取车辆列表
     * @param vehicleVo
     * @return
     */
    List<VehicleDto> getVehicleList(VehicleVo vehicleVo);

    /**
     * 获取车辆详情
     * @param vehicleId
     * @return
     */
    VehicleDto getVehicleDetail(Integer vehicleId);
}
