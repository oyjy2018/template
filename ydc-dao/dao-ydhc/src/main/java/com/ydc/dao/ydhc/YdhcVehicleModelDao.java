package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcVehicleModel;

import java.util.List;
import java.util.Map;

public interface YdhcVehicleModelDao {
    int deleteByPrimaryKey(Integer id);

    void deleteAll();

    int insert(YdhcVehicleModel record);

    void batchInsert(List<Map<String, Object>> list);

    int insertSelective(YdhcVehicleModel record);

    YdhcVehicleModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcVehicleModel record);

    int updateByPrimaryKey(YdhcVehicleModel record);

    List<Map<String, Object>> getVersionBySeries(String series);

    List<Map<String,Object>> getModelList();
}