package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcVehicle;
import com.ydc.model.ydhc.YdhcVehicleUsed;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface YdhcVehicleUsedDao {

    int insert(YdhcVehicleUsed record);

    int updateByPrimaryKeySelective(YdhcVehicleUsed record);

    YdhcVehicleUsed selectByPrimaryKey(Integer id);

    List<Map<String, Object>> getYdhcVehicleUsedList(Map<String, Object> param);

    Map<String, Object> getYdhcVehicleUsedListCount(Map<String, Object> param);

    YdhcVehicleUsed selectByTitle(String title);

    /* 更新发布状态
     * @param releaseDate
     * @param shelveTime
     * @param updatePerson
     * @param updateBy
     * @param vehicleIds
     * @return
     */
    int updateReleaseStatusByIds(@Param("releaseStatus") Integer releaseStatus,
                                 @Param("releaseDate") Date releaseDate,
                                 @Param("shelveDate") Date shelveTime,
                                 @Param("updatePerson") String updatePerson,
                                 @Param("updateBy") Integer updateBy,
                                 @Param("ids") String ids);

    /**
     * 查询二手车列表
     * @param param
     * @return
     */
    List<Integer> getVehicleUsedListApp(Map<String, Object> param);

    /**
     * 获取二手车详情
     * @param vehicleUsedId
     * @return
     */
    Map<String, Object> getVehicleUsedDetailApp(@Param("vehicleUsedId") Integer vehicleUsedId);
}