package com.ydc.cgj.rental.company.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalPayWatercourse;

import java.util.List;

public interface DepositService {

    /**
     * 新增租赁押金
     * @param rentalDeposit
     * @return
     */
    public String addRentalDeposit(RentalDeposit rentalDeposit);

}
