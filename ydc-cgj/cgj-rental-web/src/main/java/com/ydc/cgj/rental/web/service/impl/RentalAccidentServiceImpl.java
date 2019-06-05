package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.OrderFeignService;
import com.ydc.cgj.rental.web.service.RentalAccidentService;
import com.ydc.commom.view.dto.cgj.rental.RentalAccidentQueryDTO;
import com.ydc.model.cgj.rental.RentalAccident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单管理->事故服务
 * @author  lshuang
 */
@Service
public class RentalAccidentServiceImpl  implements RentalAccidentService {

    @Autowired
    OrderFeignService orderFeignService;

    @Override
    public String insertRentalAccidentInfo(RentalAccident rentalAccident) {
        return orderFeignService.insertRentalAccidentInfo(rentalAccident);
    }

    @Override
    public String queryRentalAccidentListInfo(RentalAccidentQueryDTO rentalAccidentQueryDTO) {
        return orderFeignService.queryRentalAccidentListInfo(rentalAccidentQueryDTO);
    }

    @Override
    public String queryRentalAccidentInfoById(Integer id) {
        return orderFeignService.queryRentalAccidentInfoById(id);
    }

    @Override
    public String updateRentalAccidentInfo(RentalAccident rentalAccident) {
        return orderFeignService.updateRentalAccidentInfo(rentalAccident);
    }

    @Override
    public String deleteRentalAccidentInfo(RentalAccident rentalAccident) {
        return orderFeignService.deleteRentalAccidentInfo(rentalAccident);
    }
    @Override
    public String queryRentalOrderOrMaintenance(RentalAccident rentalAccident) {
        return orderFeignService.queryRentalOrderOrMaintenance(rentalAccident);
    }
}
