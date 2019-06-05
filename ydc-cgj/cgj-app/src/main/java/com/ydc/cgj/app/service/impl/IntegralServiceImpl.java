package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.service.IntegralService;
import com.ydc.cgj.app.feignService.UserFeignService;
import com.ydc.commom.view.dto.cgj.integral.IntegralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 积分
 *
 * @author gongjin
 * @create 2018-09-07 13:57
 **/
@Service
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    UserFeignService userFeignService;

    @Override
    public String getMyIntegral(Integer memberId) {
        return userFeignService.getMyIntegral(memberId);
    }

    @Override
    public String getIntegralDetail(IntegralDTO integralDTO) {
        return userFeignService.getIntegralDetail(integralDTO);
    }
}
