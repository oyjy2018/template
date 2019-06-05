package com.ydc.cgj.rental.web.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;

import java.util.List;

public interface RentalCarService {

    /**
     * 查询外部车辆列表
     *
     * @param rentalCarQueryDTO
     * @return
     */
    String getList(RentalCarQueryDTO rentalCarQueryDTO);

    /**
     * 查询外部车辆列表查询条件
     *
     * @param
     * @return
     */
    String getCondition(Integer companyId);

    /**
     * 根据车辆品牌查询车系
     *
     * @param
     * @return
     */
    String getSeriesByBrand(String brand);

    /**
     * 根据车系查询车型
     *
     * @param
     * @return
     */
    String getModelBySeries(String series);

    /**
     * 下架车辆
     * @param carId
     * @param userId
     * @return
     */
    String removeCar(int carId, int userId);

    /**
     * 获取车辆详情
     *
     * @param carId
     * @return
     */
    String getDetail(Integer carId);

    /**
     * 通过企业名称查询名店名称
     * @param companyName
     */
    String getStoreNameByCompanyName(String companyName);
}
