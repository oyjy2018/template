package com.ydc.cgj.rental.company.app.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreInsertDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreUpdateDTO;

public interface StoreService {
    //车商端新增门店
    String insert(RentalCompanyStoreInsertDTO rentalCompanyStoreInsertDTO);

    //车商端编辑门店
    String update(RentalCompanyStoreUpdateDTO rentalCompanyStoreUpdateDTO);

    //车商端我的门店列表
    String list(Integer theirEnterpriseId);

    //车商端门店详情
    String detail(Integer id);
}
