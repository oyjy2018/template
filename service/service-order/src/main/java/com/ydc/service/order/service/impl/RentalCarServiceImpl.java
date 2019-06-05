package com.ydc.service.order.service.impl;

import com.ydc.dao.cgj.rental.RentalCarDao;
import com.ydc.model.cgj.rental.RentalCar;
import com.ydc.service.order.service.RentalCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2019-01-10 15:05
 **/
@Service
public class RentalCarServiceImpl implements RentalCarService {

    @Autowired
    RentalCarDao rentalCarDao;

    @Override
    public List<RentalCar> getRentalCar(RentalCar rentalCar) {
        return rentalCarDao.getRentalCar(rentalCar);
    }
}
