package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.LendingCustomerFeignService;
import com.ydc.cgj.web.feignService.UserFeignService;
import com.ydc.cgj.web.service.LendingCustomerService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.LendingCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author
 * @create 2018-10-31 15:07
 **/
@Service
public class LendingCustomerServiceImpl implements LendingCustomerService {

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private LendingCustomerFeignService lendingCustomerFeignService;

    @Override
    public String getLendingCustomerList(LendingCustomerDTO lendingCustomerDTO) {
        return userFeignService.getLendingCustomerList(lendingCustomerDTO);
    }

    @Override
    public Result updateCustomerRollOver(List<Integer> loanIds) {
        return lendingCustomerFeignService.updateCustomerRollOver(loanIds);
    }
}
