package com.ydc.cgj.rental.company.app.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundAuthOrderVoucherCreateModel;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.zhiMaCredit.AliPayConstant;
import com.ydc.beans.zhiMaCredit.AliPayFaceFundAuth;
import com.ydc.cgj.rental.company.app.feignService.DepositFeignService;
import com.ydc.cgj.rental.company.app.service.DepositService;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {
    private static final Logger logger = LogManager.getLogger(DepositServiceImpl.class);
    @Resource
    DepositFeignService depositFeignService;

    @Override
    public String addRentalDeposit(RentalDeposit rentalDeposit) {
        return depositFeignService.addRentalDeposit(rentalDeposit);
    }
}
