package com.ydc.cgj.rental.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.rental.web.feignService.RentalCarCheckFeignService;
import com.ydc.cgj.rental.web.service.RentalCarCheckService;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarCheckQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalCarCheckServiceImpl implements RentalCarCheckService {

    @Autowired
    private RentalCarCheckFeignService rentalCarCheckFeignService;

    @Override
    public String getList(RentalCarCheckQueryDTO rentalCarCheckQueryDTO) {
        return rentalCarCheckFeignService.getList(rentalCarCheckQueryDTO);
    }

    @Override
    public String getCondition(Integer companyId) {
        return rentalCarCheckFeignService.getCondition(companyId);
    }

    @Override
    public String getSeriesByBrand(String brand) {
        return rentalCarCheckFeignService.getSeriesByBrand(brand);
    }

    @Override
    public String getModelBySeries(String series) {
        return rentalCarCheckFeignService.getModelBySeries(series);
    }

    @Override
    public String getDetail(Integer carId) {
        return rentalCarCheckFeignService.getDetail(carId);
    }

    @Override
    public String check(String data, Integer userId) {
        JSONObject resultJSON = JSONObject.parseObject(data);
        Boolean allDeny = resultJSON.getBoolean("allDeny");
        if (allDeny == null) {
            throw new ServiceRuntimeException("allDeny不能为空");
        }
        if (allDeny && StringUtil.isEmpty(resultJSON.getString("remark"))) {
            throw new ServiceRuntimeException("审批备注不能为空");
        }
        JSONArray carCheckResults = resultJSON.getJSONArray("carCheckResults");
        if (carCheckResults == null || carCheckResults.size() == 0) {
            throw new ServiceRuntimeException("车辆审核信息不能为空");
        }
        return rentalCarCheckFeignService.check(data, userId);
    }

    @Override
    public String getStoreNameByCompanyName(String companyName) {
        return rentalCarCheckFeignService.getStoreNameByCompanyName(companyName);
    }
}
