package com.ydc.cgj.web.service;

import com.ydc.commom.view.vo.cgj.LoanApplyVO;

public interface ApplyApprService {
    String insertApplyAppr(String data);

    String getApplyApprList(String data);

    String getApplyList(LoanApplyVO loanApplyVO);

}
