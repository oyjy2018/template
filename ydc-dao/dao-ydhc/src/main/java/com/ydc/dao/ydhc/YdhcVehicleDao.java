package com.ydc.dao.ydhc;

import com.ydc.commom.view.vo.ydhc.VehicleVo;
import com.ydc.commom.view.vo.ydhc.YdhcVehicleVO;
import com.ydc.model.cgj.Page;
import com.ydc.model.ydhc.YdhcVehicle;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface YdhcVehicleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(YdhcVehicle record);

    int insertSelective(YdhcVehicle record);

    YdhcVehicle selectByPrimaryKey(Integer id);

    YdhcVehicle selectByTitle(String title);

    int updateByPrimaryKeySelective(YdhcVehicle record);

    int updateByPrimaryKey(YdhcVehicle record);

    /**
     * 更新发布状态
     * @param releaseDate
     * @param shelveTime
     * @param updatePerson
     * @param updateBy
     * @param vehicleIds
     * @return
     */
    int updateReleaseStatusByIds(@Param("releaseStatus")Integer releaseStatus,
                                 @Param("releaseDate") Date releaseDate,
                                 @Param("shelveDate") Date shelveTime,
                                 @Param("updatePerson")String updatePerson,
                                 @Param("updateBy")Integer updateBy,
                                 @Param("vehicleIds") String vehicleIds);

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
     * 获取车辆id列表
     * @param vehicleVo
     * @return
     */
    List<Integer> getVehicleIdList(VehicleVo vehicleVo);
}