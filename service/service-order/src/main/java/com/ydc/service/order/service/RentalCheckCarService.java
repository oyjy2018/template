package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;
import com.ydc.model.cgj.rental.RentalCheckCar;

import java.util.List;

/**
 * @author
 * @create 2019-01-04 10:39
 **/
public interface RentalCheckCarService {

    /**
     * 企业租车订单查询出车信息
     * @param dto
     * @return
     */
    List<RentalCheckCar> getRentalCheckCar(RentalCheckCarDTO dto);

    /**
     * 录入出车信息
     * @param list
     */
    void insertRentalCheckComeCar(List<RentalCheckCarDTO> list) throws Exception;

    /**
     * 录入还车信息
     * @param list
     */
    void insertRentalCheckRepayCar(List<RentalCheckCarDTO> list) throws Exception;
}
