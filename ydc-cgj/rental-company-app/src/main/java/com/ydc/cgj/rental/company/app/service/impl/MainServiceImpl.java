package com.ydc.cgj.rental.company.app.service.impl;

import com.ydc.cgj.rental.company.app.feignService.EnterpriseOrderFeignService;
import com.ydc.cgj.rental.company.app.feignService.MainFeignService;
import com.ydc.cgj.rental.company.app.service.MainService;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainDetailQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainFeignService mainFeignService;
    @Autowired
    private EnterpriseOrderFeignService enterpriseOrderFeignService;


    @Override
    public String getMainContent() {
        return mainFeignService.getMainContent();
    }

    @Override
    public String queryPublishCar(RentalCarMainQueryDTO rentalCarMainQueryDTO) {
        return mainFeignService.queryPublishCar(rentalCarMainQueryDTO);
    }

    @Override
    public String getCarDetail(RentalCarMainDetailQueryDTO rentalCarMainDetailQueryDTO) {
        //查询节假日接口
        enterpriseOrderFeignService.getHoliday();
        return mainFeignService.getCarDetail(rentalCarMainDetailQueryDTO);
    }

    @Override
    public String getBrandList() {
        return mainFeignService.getBrandList();
    }

    @Override
    public String getAllCities() {
        return mainFeignService.getAllCities();
    }

    @Override
    public String getHotCitiesList(Integer companyId) {
        return mainFeignService.getHotCitiesList(companyId);
    }

}
