package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.CarFeignService;
import com.ydc.cgj.rental.web.service.RentalViolationService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalViolationUpdateDealStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service("rentalViolationService")
public class RentalViolationServiceImpl implements RentalViolationService {

    @Autowired
    private CarFeignService carFeignService;

    @Override
    public Result getRentalViolationList(RentalViolationDTO rentalViolationDTO) {
        return carFeignService.getRentalViolationList(rentalViolationDTO);
    }

    @Override
    public Result getRentalViolationById(int id) {
        return carFeignService.getRentalViolationById(id);
    }

    @Override
    public String insertRentalViolation(RentalViolationVO rentalViolationVO) {
        return carFeignService.insertRentalViolation(rentalViolationVO);
    }

    @Override
    public String updateRentalViolation(RentalViolationVO rentalViolationVO) {
        return carFeignService.updateRentalViolation(rentalViolationVO);
    }

    @Override
    public String updateRentalViolationStatus(int id) {
        return carFeignService.updateRentalViolationStatus(id);
    }

    @Override
    public String getViolationType() {
        return carFeignService.getViolationType();
    }

    @Override
    public Result updateViolationSettlement(Map<String, Object> req) {
        return carFeignService.updateViolationSettlement(req);
    }

    /**
     * 更新违章处理状态
     * @param rentalViolationUpdateDealStatusDTO
     * @return
     */
    @Override
    public String updateRentalViolationDealStatus(RentalViolationUpdateDealStatusDTO rentalViolationUpdateDealStatusDTO) {
        return carFeignService.updateRentalViolationDealStatus(rentalViolationUpdateDealStatusDTO);
    }
}
