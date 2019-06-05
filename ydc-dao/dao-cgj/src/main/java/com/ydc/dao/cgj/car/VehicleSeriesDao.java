package com.ydc.dao.cgj.car;

import com.ydc.model.ydhc.YdhcVehicleSeries;

import java.util.List;
import java.util.Map;

public interface VehicleSeriesDao {
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

    /**
     * key与value都是中文
     * @return
     */
    List<Map<String, Object>> getSeriesByBrandCH(String brand);
}