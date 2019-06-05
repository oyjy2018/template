package com.ydc.cgj.rental.web.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundAuthOrderVoucherCreateModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.zhiMaCredit.AliPayConstant;
import com.ydc.beans.zhiMaCredit.AliPayEntityConvertUtil;
import com.ydc.beans.zhiMaCredit.AliPayFaceFundAuth;
import com.ydc.cgj.rental.web.feignService.DepositFeignService;
import com.ydc.cgj.rental.web.service.DepositService;
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
import java.util.Map;

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
    public Result freezeDeposit(PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO) {
        return depositFeignService.freezeDeposit(aliPayFundAuthNotifyDTO);
    }

    @Override
    public Result payDepositCallBack(AlipayTradePayResponseDTO alipayTradePayResponseDTO) {
        return depositFeignService.payDeposit(alipayTradePayResponseDTO);
    }

    /**
     * 调用支付宝结算
     * @param orderId
     * @param type 1:租车扣除 2：违章扣除
     * @return
     */
    @Override
    public void updateSettleAliPayDeposit(Integer orderId,Integer type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result result= depositFeignService.getOrderDeposit(orderId, type);
                if ( null== result || result.getCode()!=ResultConstant.RESULT_CODE_SUCCESS){
                    logger.info("查看错误，请联系系统管理员 订单ID{} 类型{}",orderId,type);
                }
                Map<String,Object> resultMap= (Map<String, Object>) result.getData();
                // RentalOrder rentalOrder= JsonUtil.jsonToBean( JsonUtil.jsonStr(resultMap.get("rentalOrder")),RentalOrder.class);
                RentalDeposit rentalDeposit=JsonUtil.jsonToBean( JsonUtil.jsonStr(resultMap.get("rentalDeposit")),RentalDeposit.class);
                RentalPayWatercourse rentalPayWatercourse=JsonUtil.jsonToBean( JsonUtil.jsonStr(resultMap.get("rentalPayWatercourse")),RentalPayWatercourse.class);
                if (RentalDepositConstant.PAYMENT_MODE_1.compareTo(rentalDeposit.getPaymentMode())!=0){
                    logger.info("不是支付宝的授权 订单ID{} 类型{}",orderId,type);
                }
                 createAlipayTradePayModel(rentalDeposit,rentalPayWatercourse,type.byteValue());
            }
        }).start();
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
     * @param rentalDeposit
     * @param rentalPayWatercourse
     * @param type
     */
    private boolean createAlipayTradePayModel( RentalDeposit rentalDeposit,RentalPayWatercourse rentalPayWatercourse,Byte type){
        AlipayTradePayModel alipayTradePayModel=new AlipayTradePayModel();
        alipayTradePayModel.setOutTradeNo(rentalDeposit.getRentCarOrderNo());
        alipayTradePayModel.setTotalAmount(StringUtil.bigDecimalToString(rentalDeposit.getActualAmount()));
        alipayTradePayModel.setProductCode("PRE_AUTH");
        alipayTradePayModel.setAuthNo(rentalPayWatercourse.getThirdParthOrderNo());
        alipayTradePayModel.setSubject("冻结转支付");
        alipayTradePayModel.setBuyerId(rentalPayWatercourse.getPaymentAccount());
        alipayTradePayModel.setSellerId(SystemPropertiesConfig.ALIPAY_PAYEE_USER_ID);
        alipayTradePayModel.setAuthConfirmMode("COMPLETE");
        alipayTradePayModel.setTransCurrency("CNY");
        alipayTradePayModel.setSettleCurrency("CNY");

         String payeeAccount=SystemPropertiesConfig.ALIPAY_PAYEE_USER_ID;
         Byte depositType;//流水类型 1:租车冻结 2：违章冻结 3:租车押金结算 4：违章押金结算  8：机务
         Byte paymentMode=1;
         Integer paymentStatus=3;
        if (type.intValue()==1){
            //租车结算
            depositType=3;
            alipayTradePayModel.setSubject("租车冻结转支付");
        }else if(type.intValue()==2){
            //违章结算
            depositType=4;
            alipayTradePayModel.setSubject("违章冻结转支付");
        }else {
            return false;
        }

        try {
            AlipayTradePayResponse response= AliPayFaceFundAuth.tradePay(alipayTradePayModel,AliPayConstant.NOTIFY_URL_RENTAL_RENTAL_PAY);
            if (response.isSuccess()){
                if ("10000".equals(response.getCode())){
                    //扣款成功
                    AlipayTradePayResponseDTO alipayTradePayResponseDTO= AliPayEntityConvertUtil.convertAlipayTradePayResponseDTO(response);
                    alipayTradePayResponseDTO.setType(type);
                    alipayTradePayResponseDTO.setPaymentMode(paymentMode);
                    alipayTradePayResponseDTO.setDepositType(depositType);
                    alipayTradePayResponseDTO.setPaymentStatus(paymentStatus);
                    alipayTradePayResponseDTO.setPayeeAccount(payeeAccount);
                    //更新
                 Result result=depositFeignService.payDeposit(alipayTradePayResponseDTO);
                 if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS){
                     return true;
                 }
                 logger.info("更新失败 {}，{},{}",result.getCode(),result.getMessage());
                }else {
                    logger.info("扣款失败 {}，{},{}",response.getCode(),response.getMsg(),response.getSubMsg());
                    return false;
                }
            }
            logger.info("支付宝结算错误{},{}",response.getCode(),response.getMsg());
        } catch (AlipayApiException e) {
            logger.error("支付宝结算异常",e);
        }
        return false;
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
}
