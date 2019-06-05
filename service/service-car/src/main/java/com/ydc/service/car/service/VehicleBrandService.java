package com.ydc.service.car.service;

import com.ydc.commom.view.vo.cgj.car.BrandVO;
import com.ydc.commom.view.vo.cgj.car.ModelVO;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import com.ydc.model.ydhc.YdhcVehicleBrand;

import java.util.List;
import java.util.Map;

public interface VehicleBrandService {
    int deleteByPrimaryKey(Integer id);

    int insert(YdhcVehicleBrand record);

    void batchInsert(List<Map<String, Object>> list);

    int insertSelective(YdhcVehicleBrand record);

    YdhcVehicleBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcVehicleBrand record);

    int updateByPrimaryKey(YdhcVehicleBrand record);

    List<Map<String, Object>> getAllBrand();

    /**
     * key与value都是中文
     * @return
     */
    List<Map<String, Object>> getAllBrandCH();

    List<Map<String, Object>> getBrandList();


    /**
     * 车品牌
     * @return
     */
    List<BrandVO> getBrandVOList();

    /**
     * 车系
     * @return
     */
    Map<String, List<SeriesVO>> getSeriesVOList();

    /**
     * 车型
     * @return
     */
    List<ModelVO> getModelVOList(String series);

}
