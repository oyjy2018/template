package com.ydc.cgj.web.feignService;

import com.ydc.commom.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "service-user")
public interface LendingCustomerFeignService {

    /**
     * 用户派完券
     * @param loanIds
     * @return
     */
    @PostMapping(value = "/lendingCustomer/updateCustomerRollOver")
    Result updateCustomerRollOver(@RequestParam(value = "loanIds") List<Integer> loanIds);
}
