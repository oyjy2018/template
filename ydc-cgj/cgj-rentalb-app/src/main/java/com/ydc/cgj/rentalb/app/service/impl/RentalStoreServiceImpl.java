package com.ydc.cgj.rentalb.app.service.impl;

import com.ydc.cgj.rentalb.app.feignService.RentalStoreFeignService;
import com.ydc.cgj.rentalb.app.service.RentalStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalStoreServiceImpl implements RentalStoreService {

    @Autowired
    private RentalStoreFeignService rentalStoreFeignService;

    /**
     * 我负责的门店
     * @param userId
     * @return
     */
    @Override
    public String myResponsibleStoreList(Integer userId) {
        return rentalStoreFeignService.myResponsibleStoreList(userId);
    }
}
