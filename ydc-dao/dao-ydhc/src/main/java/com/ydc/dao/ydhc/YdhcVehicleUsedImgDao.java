package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcVehicleUsedImg;
import com.ydc.model.ydhc.YdhcVehicleUsedImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YdhcVehicleUsedImgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(YdhcVehicleUsedImg record);

    int insertSelective(YdhcVehicleUsedImg record);

    YdhcVehicleUsedImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcVehicleUsedImg record);

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

    /**
     * 批量删除图片，软删
     * @param vehicleImgIds
     * @return
     */
    int deleteByVehicleImgIds(@Param("vehicleImgIds") String vehicleImgIds);

    /**
     * 批量更新文字性描述
     * @param vehicleUsedImgList
     * @return
     */
    int batchUpdateImgDescribe(List<YdhcVehicleUsedImg> vehicleUsedImgList);
}