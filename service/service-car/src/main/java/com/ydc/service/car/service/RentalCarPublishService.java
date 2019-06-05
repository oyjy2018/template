package com.ydc.service.car.service;

import com.ydc.commom.view.vo.cgj.rental.RentalCarListVO;

import java.util.List;

public interface RentalCarPublishService {
    /**
     * 根据状态查询车辆列表
     * @param companyId
     * @param status
     * @return
     */
    List<RentalCarListVO> getCarListByStatus(Integer companyId, Integer status);

    /**
     * 发布车辆
     * @param data
     * @return
     */
    String publish(String data, Integer companyId);
}
