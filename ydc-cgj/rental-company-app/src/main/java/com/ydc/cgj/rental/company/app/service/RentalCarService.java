package com.ydc.cgj.rental.company.app.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCarListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMyListDTO;

public interface RentalCarService {

    /**
     * 查询我的车辆
     *
     * @param rentalCarListDTO
     * @return
     */
    String getMy(RentalCarListDTO rentalCarListDTO);

    /**
     * 查询我的车辆列表
     * @param rentalCarMyListDTO
     * @return
     */
    String getMyList(RentalCarMyListDTO rentalCarMyListDTO);

    /**
     * 获取我的车辆详情
     * @param carId
     * @return
     */
    String getMyCarDetail(Integer carId);

    /**
     * 删除我的车辆
     * @param carId
     * @param companyId
     * @return
     */
    String deleteMyCar(Integer carId, Integer companyId);

    /**
     * 新增车辆
     * @param data
     * @param companyId
     * @return
     */
    String addMyCar(String data, Integer companyId);

    /**
     * 新增字段检测
     * @param data
     * @return
     */
    String checkData(String data);

    /**
     * 根据状态查询车辆列表
     * @param companyId
     * @return
     */
    String getCarListByStatus(Integer companyId, Integer status);

    /**
     * 检测发布数据
     * @param data
     * @return
     */
    String checkPublishData(String data);

    /**
     * 发布车辆
     * @param data
     * @param companyId
     * @return
     */
    String publish(String data, Integer companyId);

    /**
     * 获取品牌列表
     * @return
     */
    String getAddBrandList();

    /**
     * 通过品牌查询车系
     * @param brand
     * @return
     */
    String getAddSeriesByBrand(String brand);

    /**
     * 通过品牌查询车系
     * @param series
     * @return
     */
    String getAddModelBySeries(String series);

    /**
     * 下架车辆
     * @param data
     * @return
     */
    String removeCar(String data);

}
