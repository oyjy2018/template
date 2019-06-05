package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.IApplyApprFeignService;
import com.ydc.cgj.web.service.ApplyApprService;
import com.ydc.commom.view.vo.cgj.LoanApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyApprServiceImpl implements ApplyApprService{

    @Autowired
    IApplyApprFeignService iApplyApprFeignService;
    @Override
    public String insertApplyAppr(String data) {
        return iApplyApprFeignService.insertApplyAppr(data);
    }

    @Override
    public String getApplyApprList(String data) {
        return iApplyApprFeignService.getApplyApprList(data);
    }

    @Override
    public String getApplyList(LoanApplyVO loanApplyVO) {
        return iApplyApprFeignService.getApplyList(loanApplyVO);
    }

}
