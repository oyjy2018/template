package com.ydc.cgj.rental.company.app.controller;

import com.ydc.beans.zhiMaCredit.AliPayFaceFundAuth;
import com.ydc.cgj.rental.company.app.common.CompanyUtil;
import com.ydc.cgj.rental.company.app.service.DepositService;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalDepositDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalDeposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/deposit")
public class DepositController {


    private static final Logger logger = LogManager.getLogger(DepositController.class);

    @Autowired
    private DepositService depositService;
    /**
     *   企业租车授权二维码
     * @param data  type:1 租车  2：违章
     * @return
     */
    @PostMapping(value = "/qrcode/create")
    public String getAuthorizationQuickMark(@RequestParam String data){
        logger.info("subject:{},data:{}","企业租车授权二维码", JsonUtil.gsonStr(data));
        RentalDepositDTO dto = JsonUtil.jsonToBean(data, RentalDepositDTO.class);
        RentalDeposit rentalDeposit = new RentalDeposit();
        rentalDeposit.setOrderId(dto.getOrderId());
        rentalDeposit.setMemberId(dto.getDemandSideId());
        rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS2.getStatus());
        rentalDeposit.setRentCarOrderNo(dto.getRentCarOrderNo());
        rentalDeposit.setSerialNum(UUID.randomUUID().toString());
        rentalDeposit.setActualAmount(dto.getActualAmount());
        rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_1);
        rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_1);
        rentalDeposit.setPaymentMode(RentalDepositConstant.PAYMENT_MODE_1);
        rentalDeposit.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        rentalDeposit.setCreateTime(new Date());
        RentalCompanyCustomerVO rcc = CompanyUtil.getCompanyCustomer();
        rentalDeposit.setCreateBy(rcc.getId());

        String ret = depositService.addRentalDeposit(rentalDeposit);
        if(StringUtil.isEmpty(ret)) return Result.failure("获取授权二维码失败！").toJSON();
        Result<Map<String, Object>> res = JsonUtil.jsonToBean(ret, Result.class);
        if(res.getCode() != ResultConstant.RESULT_CODE_SUCCESS) return Result.failure("获取授权二维码失败！").toJSON();
        Map<String, Object> deposit = res.getData();
        //
        Result result = AliPayFaceFundAuth.createFundAuthOrderVoucher(deposit.get("actualAmount").toString(), "租车授权",
                deposit.get("rentCarOrderNo").toString(), deposit.get("serialNum").toString(), "enterprise/freeze");
        if(result.getCode() != ResultConstant.RESULT_CODE_SUCCESS) return Result.failure("获取授权二维码失败！").toJSON();

        return result.toJSON();
    }

}
