package com.ydc.cgj.rental.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.rental.web.feignService.RentalCarFeignService;
import com.ydc.cgj.rental.web.service.RentalCarService;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalCarServiceImpl implements RentalCarService {

    @Autowired
    private RentalCarFeignService rentalCarFeignService;

    @Override
    public String getList(RentalCarQueryDTO rentalCarQueryDTO) {
        return rentalCarFeignService.getList(rentalCarQueryDTO);
    }

    @Override
    public String getCondition(Integer companyId) {
        return rentalCarFeignService.getCondition(companyId);
    }

    @Override
    public String getSeriesByBrand(String brand) {
        return rentalCarFeignService.getSeriesByBrand(brand);
    }

    @Override
    public String getModelBySeries(String series) {
        return rentalCarFeignService.getModelBySeries(series);
    }

    @Override
    public String removeCar(int carId, int userId) {
        JSONObject dataJSON = new JSONObject();
        dataJSON.put("carIds", "[" + carId + "]");
        dataJSON.put("userId", userId);
        return rentalCarFeignService.removeCar(dataJSON.toJSONString());
    }

    @Override
    public String getDetail(Integer carId) {
        return rentalCarFeignService.getDetail(carId);
    }

    @Override
    public String getStoreNameByCompanyName(String companyName) {
        return rentalCarFeignService.getStoreNameByCompanyName(companyName);
    }
}
