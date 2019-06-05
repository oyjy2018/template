package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcVehicleImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YdhcVehicleImgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(YdhcVehicleImg record);

    int insertSelective(YdhcVehicleImg record);

    YdhcVehicleImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcVehicleImg record);

    int updateByPrimaryKey(YdhcVehicleImg record);

    /**
     * 批量插入数据
     * @param list
     */
    void batchInsert(List<YdhcVehicleImg> list);

    /**
     * 根据车辆ID查询
     * @param vehicleId
     * @return
     */
    List<YdhcVehicleImg> selectByVehicleId(Integer vehicleId);

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
}