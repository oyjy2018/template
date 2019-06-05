package com.ydc.cgj.rental.company.app.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCarMainDetailQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainQueryDTO;

public interface MainService {

    /**
     * 获取主页内容
     * @param
     * @return
     */
    String getMainContent();

    /**
     * 寻找车辆
     * @param rentalCarMainQueryDTO
     * @return
     */
    String queryPublishCar(RentalCarMainQueryDTO rentalCarMainQueryDTO);

    /**
     * 查询首页的车辆详情
     * @param rentalCarMainDetailQueryDTO
     * @return
     */
    String getCarDetail(RentalCarMainDetailQueryDTO rentalCarMainDetailQueryDTO);

    /**
     * 品牌列表
     * @return
     */
    String getBrandList();

    /**
     * 获取所有己城市（己发布+己出租）
     * @return
     */
    String getAllCities();

    /**
     * 获取城市列表
     * @param companyId
     * @return
     */
    String getHotCitiesList(Integer companyId);
}
