package com.ydc.service.loan.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.LoanApplyVO;
import com.ydc.service.loan.service.LoanApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/loanApply")
public class LoanApplyController {

    @Autowired
    private LoanApplyService loanApplyService;

    @PostMapping(value = "/getApplyNum")
    public String getApplyNum(@RequestParam("data") String data) {
        String applyNum = loanApplyService.getApplyNum(data);
        Object object = applyNum;

        return Result.success(object).toJSON();
    }


    @PostMapping(value = "/submitApplication")
    public String submitApplication(@RequestParam("data") String data) {

        return loanApplyService.submitApplication(data)  <= 0 ? Result.failure("更新失败").toJSON() : Result.success("成功").toJSON();
    }

    @PostMapping(value = "/insertApplyAppr")
    public String insertApplyAppr(@RequestBody String data) {

        return loanApplyService.insertApplyAppr(data)  <= 0 ? Result.failure("更新失败").toJSON() : Result.success("成功").toJSON();
    }

    @PostMapping(value = "/getApplyApprList")
    public String getApplyApprList(@RequestParam("data") String data) {

        return loanApplyService.getApplyApprList(data);
    }

    @PostMapping(value = "/getApplyList")
    public String getApplyList(@RequestBody LoanApplyVO loanApplyVO) {

        return loanApplyService.getApplyList(loanApplyVO);
    }
}
