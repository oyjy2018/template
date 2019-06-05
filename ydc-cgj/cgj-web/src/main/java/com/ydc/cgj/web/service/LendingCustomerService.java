package com.ydc.cgj.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.LendingCustomerDTO;

import java.util.List;

/**
 * 放款客户
 *
 * @author
 * @create 2018-10-31 15:06
 **/
public interface LendingCustomerService {


    /**
     * 放款派卷
     *
     * @param lendingCustomerDTO
     * @return
     */
    String getLendingCustomerList(LendingCustomerDTO lendingCustomerDTO);

    /**
     * 用户派完券
     * @param loanIds
     * @return
     */
    Result updateCustomerRollOver(List<Integer> loanIds);
}
