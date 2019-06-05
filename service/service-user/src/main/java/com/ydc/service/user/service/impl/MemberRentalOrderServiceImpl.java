package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.rental.RentalOrderDao;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.service.user.service.MemberRentalOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberRentalOrderServiceImpl implements MemberRentalOrderService {

    @Resource
    private RentalOrderDao rentalOrderDao;

    @Override
    public Integer updateOrderOneStatus(RentalOrder rentalOrder, Integer... oldStatus) {

        return rentalOrderDao.updateMemberOrderOneStatus(rentalOrder, StringUtils.join(oldStatus, ","));
    }
}
