package com.ydc.service.car.service;

import com.alibaba.fastjson.JSONArray;
import com.ydc.commom.view.dto.cgj.rental.RentalCarListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMyListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.*;

import java.util.List;

public interface RentalCarMyService {
    /**
     * 查询所有品牌
     * @return
     */
    List<String> getAddBrandList();

    /**
     * 新增车辆-通过车系查询车型
     * @param brand
     * @return
     */
    List<String> getAddSeriesByBrand(String brand);

    /**
     * 新增车辆-通过车系查询车型
     * @param series
     * @return
     */
    List<RentalCarSeriesVo> getAddModelBySeries(String series);

    /**
     * 我的车辆
     * @param rentalCarListDTO
     * @return
     */
    List<RentalCarMyQueryVO> getMy(RentalCarListDTO rentalCarListDTO);

    /**
     * 我的车辆列表
     * @param rentalCarMyListDTO
     * @return
     */
    List<RentalCarMiniVO> getMyList(RentalCarMyListDTO rentalCarMyListDTO);

    /**
     * 删除我的车辆
     * @param carId
     * @param companyId
     * @return
     */
    String deleteMyCar(Integer carId, Integer companyId);

    /**
     * 新增我的车辆
     * @param data
     * @return
     */
    String addMyCar(String data, Integer companyId);

}
