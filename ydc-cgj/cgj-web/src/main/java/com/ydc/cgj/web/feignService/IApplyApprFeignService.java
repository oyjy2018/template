package com.ydc.cgj.web.feignService;


import com.ydc.commom.view.dto.cgj.BlankRollDto;
import com.ydc.commom.view.vo.cgj.LoanApplyVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-loan")
public interface IApplyApprFeignService {

    @PostMapping("/loanApply/insertApplyAppr")
    String insertApplyAppr(@RequestBody String data);

    @PostMapping("/loanApply/getApplyApprList")
    String getApplyApprList(@RequestParam("data") String data);

    @PostMapping("/loanApply/getApplyList")
    String getApplyList(@RequestBody LoanApplyVO loanApplyVO);

}
