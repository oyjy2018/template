package com.ydc.service.user.service;


import com.ydc.commom.view.dto.cgj.LendingCustomerDTO;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2018-10-29 18:32
 **/
public interface LendingCustomerService {


    /**
     * 放款客户数据
     * @param lendingCustomerDTO
     * @return
     */
    List<Map<String,Object>> getLendingCustomerList(LendingCustomerDTO lendingCustomerDTO);

    /**
     * 用户派完券
     * @param loanIds
     * @return
     */
    Integer updateCustomerRollOver(List<Integer> loanIds);
}
