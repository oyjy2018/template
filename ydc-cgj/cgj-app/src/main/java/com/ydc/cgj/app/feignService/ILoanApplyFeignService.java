package com.ydc.cgj.app.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;

@Service
@FeignClient(value = "service-loan")
public interface ILoanApplyFeignService {

    @PostMapping(value = "/loanApply/getApplyNum")
    String getApplyNum(@RequestParam("data") String data);

    @PostMapping(value = "/loanApply/submitApplication")
    String submitApplication(@RequestParam("data") String data);
}
