package com.ydc.cgj.rental.company.app.service.impl;

import com.ydc.cgj.rental.company.app.feignService.StoreFeignService;
import com.ydc.cgj.rental.company.app.service.StoreService;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreInsertDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreFeignService storeFeignService;

    //车商端新增门店
    @Override
    public String insert(RentalCompanyStoreInsertDTO rentalCompanyStoreInsertDTO) {
        return storeFeignService.insert(rentalCompanyStoreInsertDTO);
    }

    //车商端编辑门店
    @Override
    public String update(RentalCompanyStoreUpdateDTO rentalCompanyStoreUpdateDTO) {
        return storeFeignService.update(rentalCompanyStoreUpdateDTO);
    }

    //车商端我的门店列表
    @Override
    public String list(Integer theirEnterpriseId) {
        return storeFeignService.list(theirEnterpriseId);
    }

    //车商端门店详情
    @Override
    public String detail(Integer id) {
        return storeFeignService.detail(id);
    }
}
