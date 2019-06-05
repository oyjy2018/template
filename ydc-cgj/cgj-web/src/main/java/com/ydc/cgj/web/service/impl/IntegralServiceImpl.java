package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.service.IntegralService;
import com.ydc.cgj.web.feignService.UserFeignService;
import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 积分
 *
 * @author gongjin
 * @create 2018-09-07 8:51
 **/
@Service
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    UserFeignService userFeignService;


    @Override
    public String getIntegralList(IntegralManageDTO integralManageDTO) {
        return userFeignService.getIntegralList(integralManageDTO);
    }

    @Override
    public String saveOrUpdateIntegral(IntegralManageDTO integralManageDTO) {
        return userFeignService.saveOrUpdateIntegral(integralManageDTO);
    }

    @Override
    public String getIntegralDetailList(IntegralManageDTO integralManageDTO) {
        return userFeignService.getIntegralDetailList(integralManageDTO);
    }
}
