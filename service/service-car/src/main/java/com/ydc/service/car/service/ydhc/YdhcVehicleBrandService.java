package com.ydc.service.car.service.ydhc;

import com.ydc.model.ydhc.YdhcVehicleBrand;

import java.util.List;
import java.util.Map;

public interface YdhcVehicleBrandService {
    int deleteByPrimaryKey(Integer id);

    int insert(YdhcVehicleBrand record);

    int insertSelective(YdhcVehicleBrand record);

    YdhcVehicleBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcVehicleBrand record);

    int updateByPrimaryKey(YdhcVehicleBrand record);

    /**
     * »ñÈ¡ËùÓÐÆ·ÅÆ
     * @return
     */
    List<Map<String, Object>> getAllBrand();

    List<Map<String, Object>> getSeriesByBrand(Map<String, Object> req);

    List<Map<String, Object>> getVersionBySeries(Map<String, Object> req);
}
