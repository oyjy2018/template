package com.ydc.service.loan.service;

import com.ydc.commom.view.vo.cgj.LoanApplyVO;
import org.springframework.stereotype.Service;

@Service
public interface LoanApplyService {
    String getApplyNum(String data);

    Integer submitApplication(String data);

    Integer insertApplyAppr(String data);

    String getApplyApprList(String data);

    String getApplyList(LoanApplyVO loanApplyVO);
}
