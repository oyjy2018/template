package com.ydc.cgj.rental.company.app.service.impl;

import com.ydc.cgj.rental.company.app.feignService.EnterpriseOrderFeignService;
import com.ydc.cgj.rental.company.app.service.EnterpriseOrderService;
import com.ydc.commom.view.dto.cgj.rental.AddRentalEnterpriseOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarSeriesDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarPublishDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseOrderServiceImpl implements EnterpriseOrderService {

    @Autowired
    EnterpriseOrderFeignService enterpriseOrderFeignService;

    @Override
    public String addRentalOrder(AddRentalEnterpriseOrderDTO dto) {
        return enterpriseOrderFeignService.addRentalOrder(dto);
    }

    @Override
    public String cancelOrder(RentalEnterpriseOrderDTO dto) {
        return enterpriseOrderFeignService.cancelOrder(dto);
    }

    @Override
    public String refuseOrder(RentalEnterpriseOrderDTO dto) {
        return enterpriseOrderFeignService.refuseOrder(dto);
    }

    @Override
    public String getEnterpriseOrderListB2BD(RentalEnterpriseOrderDTO dto) {
        return enterpriseOrderFeignService.getEnterpriseOrderListB2BD(dto);
    }

    @Override
    public String getEnterpriseOrderListB2BR(RentalEnterpriseOrderDTO dto) {
        return enterpriseOrderFeignService.getEnterpriseOrderListB2BR(dto);
    }

    @Override
    public String getEnterpriseOrderDetailB2BD(RentalEnterpriseOrderDTO dto) {
        return enterpriseOrderFeignService.getEnterpriseOrderDetailB2BD(dto);
    }

    @Override
    public String getEnterpriseOrderDetailB2BR(RentalEnterpriseOrderDTO dto) {
        return enterpriseOrderFeignService.getEnterpriseOrderDetailB2BR(dto);
    }

    @Override
    public String getHoliday() {
        return enterpriseOrderFeignService.getHoliday();
    }

    @Override
    public String confirmOrder(RentalEnterpriseOrderDTO dto) {
        return enterpriseOrderFeignService.confirmOrder(dto);
    }

    @Override
    public String getPublishInfo(RentalCarPublishDTO dto) {
        return enterpriseOrderFeignService.getPublishInfo(dto);
    }
}
