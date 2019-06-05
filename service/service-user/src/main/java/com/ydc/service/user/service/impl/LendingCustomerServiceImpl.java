package com.ydc.service.user.service.impl;

import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.LendingCustomerDTO;
import com.ydc.dao.cgj.loan.LendingCustomerDao;
import com.ydc.service.user.service.LendingCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2018-10-29 18:33
 **/
@Service
public class LendingCustomerServiceImpl implements LendingCustomerService {

    @Autowired
    LendingCustomerDao lendingCustomerDao;

    @Override
    public List<Map<String, Object>> getLendingCustomerList(LendingCustomerDTO lendingCustomerDTO) {
        return PaginationUtil.paginationQuery(lendingCustomerDTO ,(tempLendingCustomerDTO) -> lendingCustomerDao.getLendingCustomerList(lendingCustomerDTO));
    }

    @Override
    public Integer updateCustomerRollOver(List<Integer> loanIds) {
        return lendingCustomerDao.updateCustomerRollOver(loanIds);
    }
}
