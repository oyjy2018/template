package com.ydc.service.order.service;

import com.ydc.model.cgj.rental.RentalCar;

import java.util.List;

/**
 * @author
 * @create 2019-01-10 15:05
 **/
public interface RentalCarService {


    /**
     * 外部车辆信息表
     * @param rentalCar
     * @return
     */
    List<RentalCar> getRentalCar(RentalCar rentalCar);
}
