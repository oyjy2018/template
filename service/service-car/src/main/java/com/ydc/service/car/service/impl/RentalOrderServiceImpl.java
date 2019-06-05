package com.ydc.service.car.service.impl;

import com.ydc.dao.cgj.rental.RentalOrderDao;
import com.ydc.service.car.service.RentalOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RentalOrderServiceImpl implements RentalOrderService {

    @Resource
    private RentalOrderDao rentalOrderDao;

    @Override
    public Integer updateViolationNumber(Integer orderId) {
        return rentalOrderDao.updateViolationNumber(orderId);
    }
}
