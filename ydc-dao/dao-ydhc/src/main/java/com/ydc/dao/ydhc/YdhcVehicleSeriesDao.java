package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcVehicleSeries;

import java.util.List;
import java.util.Map;

public interface YdhcVehicleSeriesDao {
    int deleteByPrimaryKey(Integer id);

    void deleteAll();

    int insert(YdhcVehicleSeries record);

    int batchInsert(List<Map<String, Object>> list);

    int insertSelective(YdhcVehicleSeries record);

    YdhcVehicleSeries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcVehicleSeries record);

    int updateByPrimaryKey(YdhcVehicleSeries record);

    List<Map<String, Object>> getSeriesList();

    List<Map<String, Object>> getSeriesByBrand(String brand);
}