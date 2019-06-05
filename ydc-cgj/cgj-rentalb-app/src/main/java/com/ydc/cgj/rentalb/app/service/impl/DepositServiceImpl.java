package com.ydc.cgj.rentalb.app.service.impl;


import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundAuthOrderVoucherCreateModel;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.zhiMaCredit.AliPayConstant;
import com.ydc.beans.zhiMaCredit.AliPayFaceFundAuth;
import com.ydc.cgj.rentalb.app.feignService.DepositFeignService;
import com.ydc.cgj.rentalb.app.service.DepositService;
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

    /**
     *
     * @param orderId
     * @param type 1:租车  2：违章
     * @return
     */
    @Override
    public Result getOrderDepositQrCode(Integer orderId, Integer type) {
        Result<HashMap> result= depositFeignService.getOrderDeposit(orderId, type);
        if ( null== result){
            return Result.failure("查看错误，请联系系统管理员");
        }
        if (result.getCode()!=ResultConstant.RESULT_CODE_SUCCESS){
            return  result;
        }
        HashMap<String,Object> resultMap= result.getData();
        //RentalOrder rentalOrder= JsonUtil.jsonToBean( JsonUtil.jsonStr(resultMap.get("rentalOrder")),RentalOrder.class);
        RentalDeposit rentalDeposit=JsonUtil.jsonToBean( JsonUtil.jsonStr(resultMap.get("rentalDeposit")),RentalDeposit.class);

        String amount,serialNum,title,orderNo,urlType;
        if (RentalDepositConstant.DEPOSIT_TYPE_1==type.intValue()){
            amount=StringUtil.bigDecimalToString(rentalDeposit.getPayableAmount());
            serialNum=rentalDeposit.getSerialNum();
            title="租车预授权";
            orderNo=rentalDeposit.getRentCarOrderNo();
            urlType= AliPayConstant.NOTIFY_URL_RENTAL_RENTAL_FREEZE;
        }else if (RentalDepositConstant.DEPOSIT_TYPE_2==type.intValue()){
            amount=StringUtil.bigDecimalToString(rentalDeposit.getPayableAmount());
            serialNum=rentalDeposit.getSerialNum();
            title="租车违章预授权";
            orderNo=rentalDeposit.getViolationOrderNo();
            urlType= AliPayConstant.NOTIFY_URL_RENTAL_VIOLATION_FREEZE;

        }else {
            return Result.failure("参数错误");
        }
        return createFundAuthOrderVoucher(amount,serialNum,title,orderNo,urlType);
    }

    @Override
    public Result<String> freezeDeposit(PayRentalDepositRequestDTO payRentalDepositRequestDTO) {
        return depositFeignService.freezeDeposit(payRentalDepositRequestDTO);
    }

    @Override
    public Result<String> payDeposit(AlipayTradePayResponseDTO alipayTradePayResponseDTO) {
        return depositFeignService.payDeposit(alipayTradePayResponseDTO);
    }


    @Override
    public Result manualOrderDeposit(ManualFreezeRentalDepositDTO manualFreezeRentalDepositDTO) {
        return depositFeignService.manualOrderDeposit(manualFreezeRentalDepositDTO);
    }

    @Override
    public List<RentalPayWatercourse> getRentalPayWatercourse(String orderNo, String thirdParthOrderNo) {
        return depositFeignService.getRentalPayWatercourse(orderNo,thirdParthOrderNo);
    }



    /**
     *
     * @param amount 冻结金额
     * @param serialNum 流水号
     * @param title  名称
     * @param orderNo 订单号
     * @param urlType 回调
     * @return
     */
    private Result createFundAuthOrderVoucher(String amount,String serialNum,String title,String orderNo,String urlType){
        AlipayFundAuthOrderVoucherCreateModel model=new AlipayFundAuthOrderVoucherCreateModel();
        model.setPayeeUserId(SystemPropertiesConfig.ALIPAY_PAYEE_USER_ID);
        model.setProductCode("PRE_AUTH");
        //model.setEnablePayChannels("[{\"payChannelType\":\"PCREDIT_PAY\"},{\"payChannelType\":\"MONEY_FUND\"}]");
        model.setAmount(amount);
        model.setOrderTitle(title);
        model.setOutOrderNo(orderNo);
        model.setOutRequestNo(serialNum);
        model.setPayTimeout("5d");
        try {
            AlipayFundAuthOrderVoucherCreateResponse response= AliPayFaceFundAuth.fundAuthOrderVoucherCreate(model,urlType);
            if (response.isSuccess()){
                logger.info("支付二维码{}",response.getCodeValue());
                Result result=Result.success();
                result.setData(response.getCodeValue());
              return result;
            }
            logger.info("支付宝生产授权码错误:{},{} ,{}",response.getCode(),response.getMsg(),response.getSubMsg());
            return Result.failure("请使用其他方式授权");
        } catch (AlipayApiException e) {
            logger.error("支付宝生产授权码异常",e);
            return  Result.failure("请使用其他方式授权");
        }
    }

    @Override
    public String addRentalDeposit(RentalDeposit rentalDeposit) {
        return depositFeignService.addRentalDeposit(rentalDeposit);
    }

    @Override
    public Result freezeEnterpriseDeposit(PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO) {
        return depositFeignService.freezeEnterpriseDeposit(aliPayFundAuthNotifyDTO);
    }

    @Override
    public Result<String> unfreezeDeposit(AlipayTradePayResponseDTO alipayTradePayResponseDTO) {
        return depositFeignService.unfreezeDeposit(alipayTradePayResponseDTO);
    }
}
