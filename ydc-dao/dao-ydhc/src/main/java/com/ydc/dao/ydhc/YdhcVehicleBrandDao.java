package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcVehicleBrand;

import java.util.List;
import java.util.Map;

public interface YdhcVehicleBrandDao {
    int deleteByPrimaryKey(Integer id);

    void deleteAll();

    int insert(YdhcVehicleBrand record);

    void batchInsert(List<Map<String, Object>> list);

    int insertSelective(YdhcVehicleBrand record);

    YdhcVehicleBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcVehicleBrand record);

    int updateByPrimaryKey(YdhcVehicleBrand record);

    List<Map<String, Object>> getAllBrand();

    List<Map<String, Object>> getBrandList();
}