package com.ydc.service.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.service.user.service.LendingCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lendingCustomer")
public class LendingCustomerController {

    @Autowired
    private LendingCustomerService lendingCustomerService;

    @PostMapping(value = "/updateCustomerRollOver")
    public Result updateCustomerRollOver(@RequestParam(value = "loanIds") List<Integer> loanIds){
        Integer result = lendingCustomerService.updateCustomerRollOver(loanIds);
        return (result == null || result <= 0) ? Result.failure() : Result.success();
    }
}
