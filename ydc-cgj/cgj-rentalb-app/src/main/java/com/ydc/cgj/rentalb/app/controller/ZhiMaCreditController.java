package com.ydc.cgj.rentalb.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundAuthOrderUnfreezeModel;
import com.alipay.api.domain.AlipayFundAuthOrderVoucherCreateModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.response.AlipayFundAuthOrderUnfreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.zhiMaCredit.AliPayConstant;
import com.ydc.beans.zhiMaCredit.AliPayEntityConvertUtil;
import com.ydc.beans.zhiMaCredit.AliPayFaceFundAuth;
import com.ydc.cgj.rentalb.app.service.DepositService;
import com.ydc.cgj.rentalb.app.service.RentalSettlementService;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.constant.rental.RentalSettlementConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalDepositDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * 芝麻信用授权处理
 */
@RestController
@RequestMapping(value = "/credit")
public class ZhiMaCreditController {

    private static final Logger logger = LogManager.getLogger(ZhiMaCreditController.class);

    @Autowired
    private DepositService depositService;
    @Autowired
    private RentalSettlementService rentalSettlementService;

    /**
     * 测试调用生产授权码 不用
     * @return
     */
    @PostMapping(value = "/consent/update")
    public String updateConsentAuthorization(@RequestParam(value = "orderNo") String orderNo){
        RentalOrder rentalOrder=new RentalOrder();
        rentalOrder.setOrderNo(orderNo);
        RentalDeposit rentalDeposit=new RentalDeposit();
        rentalDeposit.setPayableAmount(new BigDecimal(0.01));
        rentalDeposit.setSerialNum(orderNo+"11");
        return createFundAuthOrderVoucher(rentalOrder,rentalDeposit).toJSON();
    }

    private Result createFundAuthOrderVoucher(RentalOrder rentalOrder,RentalDeposit rentalDeposit){
        AlipayFundAuthOrderVoucherCreateModel model=new AlipayFundAuthOrderVoucherCreateModel();
        model.setPayeeUserId(SystemPropertiesConfig.ALIPAY_PAYEE_USER_ID);
        model.setProductCode("PRE_AUTH");
        //model.setEnablePayChannels("[{\"payChannelType\":\"PCREDIT_PAY\"},{\"payChannelType\":\"MONEY_FUND\"}]");
        model.setAmount("0.01");
        model.setOrderTitle("租车预授权");
        model.setOutOrderNo(rentalOrder.getOrderNo());
        model.setOutRequestNo(rentalDeposit.getSerialNum());
        model.setPayTimeout("5d");
        try {
            AlipayFundAuthOrderVoucherCreateResponse response= AliPayFaceFundAuth.fundAuthOrderVoucherCreate(model,"/rental/freeze");
            if (response.isSuccess()){
                JSONObject jsonObject=JSON.parseObject(response.getBody()) ;
                String code_value=response.getCodeValue();
                return Result.success(code_value);
            }
            logger.info("支付宝生产授权码错误{},{} ,{}",response.getCode(),response.getMsg(),response.getSubMsg());
            return Result.failure("请使用其他方式授权");
        } catch (AlipayApiException e) {
            logger.error("支付宝生产授权码异常",e);
            return  Result.failure("请使用其他方式授权");
        }
    }

    @PostMapping(value = "/consent/update1")
    public String update1(@RequestParam(value = "orderNo") String orderNo){
        AlipayFundAuthOrderVoucherCreateModel model=new AlipayFundAuthOrderVoucherCreateModel();
        model.setPayeeUserId(SystemPropertiesConfig.ALIPAY_PAYEE_USER_ID);
        model.setProductCode("PRE_AUTH");
        model.setAmount("0.05");
        model.setOrderTitle("租车预授权");
        model.setOutOrderNo(orderNo);
        model.setOutRequestNo(orderNo+"11");
        model.setPayTimeout("5d");
        try {
            AlipayFundAuthOrderVoucherCreateResponse response= AliPayFaceFundAuth.fundAuthOrderVoucherCreate(model,"/rental/freeze");
            if (response.isSuccess()){
                String code_value=response.getCodeValue();
               logger.info(code_value);
                return Result.success(code_value).toJSON();
            }
            logger.info("支付宝生产授权码错误{},{}",response.getCode(),response.getMsg());
            return Result.failure("请使用其他方式授权").toJSON();
        } catch (AlipayApiException e) {
            logger.error("支付宝生产授权码异常",e);
            return  Result.failure("请使用其他方式授权").toJSON();
        }

    }


    /**
     * 冻结押金成功回调
     * @return
     */
    @RequestMapping(value = "/callBack/create/rental/freeze")
    public String freezeRentalCallBack(HttpServletRequest request, HttpServletResponse response){
        logger.info("subject:{},req:{}","冻结押金成功回调",request);
        Map<String, String[]>  map=request.getParameterMap();
        String notify_type=request.getParameter("notify_type");
        for (String key : map.keySet()) {
           logger.info("Key = " + key+" value="+map.get(key)[0]);
        }
        if (AliPayConstant.ALIPAY_CALLBACK_AUTH_FREEZE.equals(notify_type.toUpperCase())){
            freezeSuccess(map,RentalDepositConstant.PAYMENT_MODE_1,RentalDepositConstant.DEPOSIT_TYPE_1,RentalDepositConstant.PAYMENT_STATUS_2);
        }
        return "SUCCESS";
    }

    /**
     * 违章冻结押金成功回调
     * @return
     */
    @RequestMapping(value = "/callBack/create/violation/freeze")
    public String freezeViolationCallBack(HttpServletRequest request, HttpServletResponse response){
        logger.info("subject:{},req:{}","违章冻结押金成功回调",request);
        Map<String, String[]>  map=request.getParameterMap();
        String notify_type=request.getParameter("notify_type");
        for (String key : map.keySet()) {
           logger.info("Key = " + key+" value="+map.get(key)[0]);
        }
        if (AliPayConstant.ALIPAY_CALLBACK_AUTH_FREEZE.equals(notify_type.toUpperCase())){
            freezeSuccess(map,RentalDepositConstant.PAYMENT_MODE_1,RentalDepositConstant.DEPOSIT_TYPE_2,RentalDepositConstant.PAYMENT_STATUS_2);
        }
        return "SUCCESS";
    }

    /**
     * 租车结算回调
     * @return
     */
    @RequestMapping(value = "/callBack/create/rental/pay")
    public String payRentalCallBack(HttpServletRequest request, HttpServletResponse response){
        logger.info("subject:{},req:{}","租车结算成功回调",request);
        Map<String, String[]>  map=request.getParameterMap();
        String trade_status=request.getParameter("trade_status");
        for (String key : map.keySet()) {
           logger.info("Key = " + key+" value="+map.get(key)[0]);
        }
        if (AliPayConstant.ALIPAY_CALLBACK_PAY.equals(trade_status.toUpperCase())){
            settleSuccess(map,RentalDepositConstant.PAYMENT_MODE_1,RentalDepositConstant.DEPOSIT_TYPE_1,
                    RentalDepositConstant.PAYMENT_STATUS_3,
                    RentalSettlementConstant.SETTLEMENT_TYPE_PAY);
        }
        return "SUCCESS";
    }

    /**
     * 违章结算回调
     * @return
     */
    @RequestMapping(value = "/callBack/create/violation/pay")
    public String payViolationCallBack(HttpServletRequest request, HttpServletResponse response){
        logger.info("subject:{},req:{}","违章押金结算回调",request);
        Map<String, String[]>  map=request.getParameterMap();
        String trade_status=request.getParameter("trade_status");
        for (String key : map.keySet()) {
           logger.info("Key = " + key+" value="+map.get(key)[0]);
        }
        if (AliPayConstant.ALIPAY_CALLBACK_PAY.equals(trade_status.toUpperCase())){
            settleSuccess(map,RentalDepositConstant.PAYMENT_MODE_1,RentalDepositConstant.DEPOSIT_TYPE_2,
                    RentalDepositConstant.PAYMENT_STATUS_3,
                    RentalSettlementConstant.SETTLEMENT_TYPE_PAY);
        }
        return "SUCCESS";
    }

    /**
     *  租车解冻回调
     */
    @RequestMapping(value = "/callBack/create/rental/unfreeze")
    public String unfreezeRentalCallBack(HttpServletRequest request, HttpServletResponse response){
            logger.info("subject:{},req:{}","租车解冻成功回调",request);
            Map<String, String[]>  map=request.getParameterMap();
            String notify_type=request.getParameter("notify_type");
            for (String key : map.keySet()) {
               logger.info("Key = " + key+" value="+map.get(key)[0]);
            }
            if (AliPayConstant.ALIPAY_CALLBACK_AUTH_UNFREEZE.equals(notify_type.toUpperCase())){
                settleSuccess(map,RentalDepositConstant.PAYMENT_MODE_1,
                        RentalDepositConstant.DEPOSIT_TYPE_1,
                        RentalDepositConstant.PAYMENT_STATUS_3,
                        RentalSettlementConstant.SETTLEMENT_TYPE_UNFREEZE);
            }
            return "SUCCESS";
        }

    /**
     *  违章解冻回调
     */
    @RequestMapping(value = "/callBack/create/violation/unfreeze")
    public String unfreezeViolationCallBack(HttpServletRequest request, HttpServletResponse response){
        logger.info("subject:{},req:{}","违章解冻成功回调",request);
        Map<String, String[]>  map=request.getParameterMap();
        String notify_type=request.getParameter("notify_type");
        for (String key : map.keySet()) {
           logger.info("Key = " + key+" value="+map.get(key)[0]);
        }
        if (AliPayConstant.ALIPAY_CALLBACK_AUTH_UNFREEZE.equals(notify_type.toUpperCase())){
            settleSuccess(map,RentalDepositConstant.PAYMENT_MODE_1,
                    RentalDepositConstant.DEPOSIT_TYPE_2,
                    RentalDepositConstant.PAYMENT_STATUS_3,RentalSettlementConstant.SETTLEMENT_TYPE_UNFREEZE);
        }
        return "SUCCESS";
    }

    /**
     *  预授权冻结成功
     * @param map
     * @param paymentMode  押金支付方式 1：芝麻
     * @param depositType  押金类型：1租车 2：违章
     * @param paymentStatus 押金状态： 1：未支付，2：已支付，3：已退还，9：退还失败
     */
    private void freezeSuccess(Map<String, String[]>  map,Byte paymentMode,Byte depositType,Byte paymentStatus){
        //更新
        PayRentalDepositRequestDTO payRentalDepositRequestDTO=new PayRentalDepositRequestDTO(map);
        payRentalDepositRequestDTO.setPayment_mode(paymentMode);//押金支付方式  芝麻
        payRentalDepositRequestDTO.setDepositType(depositType);//押金类型
        payRentalDepositRequestDTO.setPayment_status(paymentStatus);//押金状态 支付
        logger.info("预授权冻结成功 {}",payRentalDepositRequestDTO.toString());
        depositService.freezeDeposit(payRentalDepositRequestDTO);

    }
    /**
     *  结算成功回调
     * @param map
     * @param paymentMode  押金支付方式 1：芝麻
     * @param depositType  押金类型：1租车 2：违章
     * @param paymentStatus 押金状态： 1：未支付，2：已支付，3：已退还，9：退还失败
     * @param type  数据类型 ：unfreeze 解冻  pay:支付
     */
    private void settleSuccess(Map<String, String[]>  map,Byte paymentMode,Byte depositType,Byte paymentStatus,String type){
        //更新
        AlipayTradePayResponseDTO alipayTradePayResponseDTO=AliPayEntityConvertUtil.convertAlipayTradePayResponseDTO(map,type);
        alipayTradePayResponseDTO.setPaymentMode(paymentMode);//押金支付方式  芝麻
        alipayTradePayResponseDTO.setPaymentStatus(Integer.valueOf(paymentStatus));//押金状态 支付
        alipayTradePayResponseDTO.setDepositType(depositType);//押金类型
        logger.info("结算成功回调 {}",alipayTradePayResponseDTO.toString());
        depositService.payDeposit(alipayTradePayResponseDTO);
    }

    @PostMapping(value = "/consent/update2")
    public String  createOrderRent(@RequestParam("orderNo") String orderNo){
        logger.info("subject:{},req:{}","根据会员ID查询订单列表(C端)参数",orderNo);
        try {
            AliPayFaceFundAuth.createOrderRent(orderNo);
            return Result.success("成功").toJSON();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
    }


    /**
     * 手动解冻押金
     * @param auth_no
     * @param out_request_no
     * @param amount
     * @param remark
     * @return
     */
    @PostMapping(value = "/manual/unfreeze")
    public String  manualUnfreeze(@RequestParam("auth_no") String auth_no,
                                  @RequestParam("out_request_no") String out_request_no,
                                  @RequestParam("amount") String amount,
                                  @RequestParam("remark") String remark
                                  ){
        try {
            AlipayFundAuthOrderUnfreezeModel model=new AlipayFundAuthOrderUnfreezeModel();
            model.setAmount(amount);
            model.setAuthNo(auth_no);
            model.setOutRequestNo(out_request_no);
            model.setRemark(remark);
            AlipayFundAuthOrderUnfreezeResponse response= AliPayFaceFundAuth.fundAuthOrderUnfreeze(model,"/rental/freeze");
            if (response.isSuccess()){
                return Result.success(response.getMsg()).toJSON();
            }
            return Result.failure(response.getMsg()).toJSON();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
    }

    /**
     * 手动结算
     */
    @PostMapping(value = "/manual/settle")
    public String  manualSettle(@RequestParam("auth_no") String auth_no,
                                  @RequestParam("out_trade_no") String out_trade_no){
        try {
            List<RentalPayWatercourse> rentalPayWatercourses= depositService.getRentalPayWatercourse(out_trade_no,auth_no);
            if (rentalPayWatercourses==null || rentalPayWatercourses.isEmpty()){
                return Result.failure("订单号错误").toJSON();
            }
            RentalPayWatercourse rentalPayWatercourse=rentalPayWatercourses.get(0);

            AlipayTradePayModel model=new AlipayTradePayModel();
            model.setAuthNo(auth_no);
            model.setOutTradeNo(out_trade_no);
            model.setTotalAmount(StringUtil.bigDecimalToString(rentalPayWatercourse.getActualAmount()));
            model.setSubject("押金转支付");
            model.setProductCode("PRE_AUTH");
            model.setBuyerId(rentalPayWatercourse.getPaymentAccount());
            model.setSellerId(rentalPayWatercourse.getPayeeAccount());
            model.setAuthConfirmMode("COMPLETE");
            AlipayTradePayResponse response= AliPayFaceFundAuth.tradePay(model,AliPayConstant.NOTIFY_URL_RENTAL_RENTAL_PAY);
            if (response.isSuccess()){
                logger.info(response.toString());
                return Result.success(response.getMsg()).toJSON();
            }
            return Result.failure(response.getMsg()).toJSON();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
    }

    /**
     * 手动结算  1:租车  2：违章
     */
    @PostMapping(value = "/manual/settle/type")
    public String  manualSettleOrder(@RequestParam("orderId") Integer orderId,
                                @RequestParam("type")  Integer type){
        try {
            rentalSettlementService.updateSettleAliPayDeposit(orderId,type);
            return Result.success().toJSON();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
    }


    /**
     * 企业租车授权二维码
     * @param dto
     * @return
     */
    @PostMapping(value = "/getAuthorizationQuickMark")
    public String getAuthorizationQuickMark(@RequestBody RentalDepositDTO dto){
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

    /**
     * 冻结保证金成功回调
     * @return
     */
    @RequestMapping(value = "/callBack/create/enterprise/freeze")
    public String freezeEnterpriseCallBack(HttpServletRequest request, HttpServletResponse response){
        logger.info("subject:{},req:{}","冻结押金成功回调",request);
        Map<String, String[]>  map=request.getParameterMap();
        String notify_type=request.getParameter("notify_type");
        for (String key : map.keySet()) {
            logger.info("Key = " + key+" value="+map.get(key)[0]);
        }
        if (AliPayConstant.ALIPAY_CALLBACK_AUTH_FREEZE.equals(notify_type.toUpperCase())){
            freezeEnterpriseSuccess(map,RentalDepositConstant.PAYMENT_MODE_1,RentalDepositConstant.DEPOSIT_TYPE_1,RentalDepositConstant.PAYMENT_STATUS_2);
        }
        return "SUCCESS";
    }

    /**
     *  预授权冻结成功（企业租车）
     * @param map
     * @param paymentMode  押金支付方式 1：芝麻
     * @param depositType  押金类型：1租车 2：违章
     * @param paymentStatus 押金状态： 1：未支付，2：已支付，3：已退还，9：退还失败
     */
    private void freezeEnterpriseSuccess(Map<String, String[]>  map,Byte paymentMode,Byte depositType,Byte paymentStatus){
        //更新
        PayRentalDepositRequestDTO payRentalDepositRequestDTO=new PayRentalDepositRequestDTO(map);
        payRentalDepositRequestDTO.setPayment_mode(paymentMode);//保证金支付方式  芝麻
        payRentalDepositRequestDTO.setDepositType(depositType);//保证金类型
        payRentalDepositRequestDTO.setPayment_status(paymentStatus);//保证金状态 支付
        logger.info("预授权冻结成功 {}",payRentalDepositRequestDTO.toString());
        Result result = depositService.freezeEnterpriseDeposit(payRentalDepositRequestDTO);
        logger.info("预授权冻结成功后台处理结果 {}",result.toJSON());
    }

    /**
     *  保证金解冻回调（企业租车）
     */
    @RequestMapping(value = "/callBack/create/enterprise/unfreeze")
    public String unfreezeEnterpriseCallBack(HttpServletRequest request, HttpServletResponse response){
        logger.info("subject:{},req:{}","租车解冻成功回调",request);
        Map<String, String[]>  map=request.getParameterMap();
        String notify_type=request.getParameter("notify_type");
        for (String key : map.keySet()) {
            logger.info("Key = " + key+" value="+map.get(key)[0]);
        }
        if (AliPayConstant.ALIPAY_CALLBACK_AUTH_UNFREEZE.equals(notify_type.toUpperCase())){
            this.unfreezeSuccess(map,RentalDepositConstant.PAYMENT_MODE_1,
                    RentalDepositConstant.DEPOSIT_TYPE_1,
                    RentalDepositConstant.PAYMENT_STATUS_3,
                    RentalSettlementConstant.SETTLEMENT_TYPE_UNFREEZE);
        }
        return "SUCCESS";
    }

    /**
     *  保证金解冻回调
     * @param map
     * @param paymentMode  押金支付方式 1：芝麻
     * @param depositType  押金类型：1租车 2：违章
     * @param paymentStatus 押金状态： 1：未支付，2：已支付，3：已退还，9：退还失败
     * @param type  数据类型 ：unfreeze 解冻  pay:支付
     */
    private void unfreezeSuccess(Map<String, String[]>  map,Byte paymentMode,Byte depositType,Byte paymentStatus,String type){
        //更新
        AlipayTradePayResponseDTO alipayTradePayResponseDTO=AliPayEntityConvertUtil.convertAlipayTradePayResponseDTO(map,type);
        alipayTradePayResponseDTO.setPaymentMode(paymentMode);//押金支付方式  芝麻
        alipayTradePayResponseDTO.setPaymentStatus(Integer.valueOf(paymentStatus));//押金状态 支付
        alipayTradePayResponseDTO.setDepositType(depositType);//押金类型
        logger.info("结算成功回调 {}",alipayTradePayResponseDTO.toString());
        depositService.unfreezeDeposit(alipayTradePayResponseDTO);
    }

}
