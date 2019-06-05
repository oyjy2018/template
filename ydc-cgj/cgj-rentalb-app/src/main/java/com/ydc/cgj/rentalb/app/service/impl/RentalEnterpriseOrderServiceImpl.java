package com.ydc.cgj.rentalb.app.service.impl;

import com.ydc.cgj.rentalb.app.feignService.RentalEnterpriseOrderFeignService;
import com.ydc.cgj.rentalb.app.service.RentalEnterpriseOrderService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2019-01-04 10:04
 **/
@Service
public class RentalEnterpriseOrderServiceImpl implements RentalEnterpriseOrderService {


    @Autowired
    RentalEnterpriseOrderFeignService rentalEnterpriseOrderFeignService;

    @Override
    public String getStoreRentalEnterpriseOrderList(RentalEnterpriseOrderDTO dto) {
        return rentalEnterpriseOrderFeignService.getStoreRentalEnterpriseOrderList(dto);
    }

    @Override
    public String getStoreRentalEnterpriseOrderId(RentalEnterpriseOrderDTO dto) {
        if(StringUtil.isEmpty(dto.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        return rentalEnterpriseOrderFeignService.getStoreRentalEnterpriseOrderId(dto);
    }

    @Override
    public String cancelRentalEnterpriseOrder(RentalEnterpriseOrderDTO dto) {
        if(StringUtil.isEmpty(dto.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        return rentalEnterpriseOrderFeignService.cancelRentalEnterpriseOrder(dto);
    }

    @Override
    public String rejectRentalEnterpriseOrder(RentalEnterpriseOrderDTO dto) {
        if(StringUtil.isEmpty(dto.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        return rentalEnterpriseOrderFeignService.rejectRentalEnterpriseOrder(dto);
    }

    @Override
    public String notarizeRentalEnterpriseOrder(RentalEnterpriseOrderDTO dto) {
        if(StringUtil.isEmpty(dto.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        return rentalEnterpriseOrderFeignService.notarizeRentalEnterpriseOrder(dto);
    }

    @Override
    public String getRentalCheckCarByOrderId(RentalCheckCarDTO dto) {
        if(StringUtil.isEmpty(dto.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        return rentalEnterpriseOrderFeignService.getRentalCheckCarByOrderId(dto);
    }
}
