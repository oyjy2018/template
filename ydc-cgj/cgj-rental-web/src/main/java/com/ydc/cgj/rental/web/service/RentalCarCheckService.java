package com.ydc.cgj.rental.web.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCarCheckQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;

public interface RentalCarCheckService {

    /**
     * 查询外部车辆列表
     *
     * @param rentalCarCheckQueryDTO
     * @return
     */
    String getList(RentalCarCheckQueryDTO rentalCarCheckQueryDTO);

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
     * 获取车辆详情
     *
     * @param carId
     * @return
     */
    String getDetail(Integer carId);

    /**
     * 审核
     * @param data
     * @return
     */
    String check(String data, Integer userId);

    /**
     * 通过企业名称查询名店名称
     * @param companyName
     * @return
     */
    String getStoreNameByCompanyName(String companyName);
}
