package com.ydc.service.car.service.ydhc;

import com.ydc.model.ydhc.YdhcVehicleUsed;

import java.util.List;
import java.util.Map;

public interface VehicleUsedService {

    /**
     * 更新或保存二手车信息
     *
     * @param req
     * @return
     */
    Integer saveOrUpdateVehicleUsed(Map<String, Object> req);

    /**
     * 根据标题查询二手车信息
     *
     * @return
     * @param title
     */
    YdhcVehicleUsed selectByTitle(String title);

    /***
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-08 15:37:08
     ***/
    int updateByPrimaryKeySelective(YdhcVehicleUsed record);

    /**
     * 查询二手车信息
     *
     * @param req
     * @return
     */
    String getVehicleUsedInfo(Map<String, String> req);

    /**
     * 根据主键获取
     *
     * @param id
     * @return
     */
    YdhcVehicleUsed selectByPrimaryKey(Integer id);

    /**
     * 查询二手车信息列表(包括模板，在售，待售)
     *
     * @param param
     * @return
     */
    List<Map<String, Object>> getYdhcVehicleUsedList(Map<String, Object> param);

    /**
     * 查询二手车信息列表(包括模板，在售，待售)的总数
     *
     * @param param
     * @return
     */
    Map<String, Object> getYdhcVehicleUsedListCount(Map<String, Object> param);

    /**
     * 更新二手车发布状态
     * @param req
     * @return
     */
    int updateReleaseStatusByIds(Map<String, String> req);

    /**
     * 查询二手车列表
     * @param param
     * @return
     */
    List<Map<String, Object>> getVehicleUsedListApp(Map<String, Object> param);

    /**
     * 获取二手车详情
     * @param vehicleUsedId
     * @return
     */
    Map<String, Object> getVehicleUsedDetailApp(Integer vehicleUsedId);
}
