package com.ydc.dao.cgj.car;

import com.ydc.model.ydhc.YdhcVehicleModel;

import java.util.List;
import java.util.Map;

public interface VehicleModelDao {
    int deleteByPrimaryKey(Integer id);

    void deleteAll();

    int insert(YdhcVehicleModel record);

    void batchInsert(List<Map<String, Object>> list);

    int insertSelective(YdhcVehicleModel record);

    YdhcVehicleModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcVehicleModel record);

    int updateByPrimaryKey(YdhcVehicleModel record);

    List<Map<String, Object>> getVersionBySeries(String series);

    /**
     * key与value都是中文
     * @param series
     * @return
     */
    List<Map<String, Object>> getVersionBySeriesCH(String series);

    List<Map<String,Object>> getModelList();
}