package com.ydc.beans.zhiMaCredit;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * 当面资金授权
 */
public class AliPayFaceFundAuth {

    private static final Logger logger = LogManager.getLogger(AliPayFaceFundAuth.class);

    public static AlipayClient createAlipayClient(){
        AlipayClient alipayClient=new DefaultAlipayClient(SystemPropertiesConfig.ALIPAY_SERVER_URL,
                SystemPropertiesConfig.ALIPAY_APP_ID,
                SystemPropertiesConfig.ALIPAY_PRIVATE_KEY,
                "json","utf-8",
                SystemPropertiesConfig.ALIPAY_PUBLIC_KEY,
                "RSA2");

        return alipayClient;
    }



   /* private static String ALIPAY_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCtqe5aMAg7zIW2VA68osfl9qluofuXwQ7RyqAakAuTsTrAaOMmK56wH2Edqs3qG3EqCthLv7Np1mm1ttGTSAdiwLpls/QywLMkoRg9x9SMe1ETI1D58fZJZTb2BfpguV1DaIJesoA7l0Jd6X4OY9+drEyjF8j/QuqeyBAb8rCm4fagMTlnsAAbXpUAz4AoGIQ738podbkcQWQx eumZsWCk6u6q8i//JqF+ZHNhKP2GN5jlfsNuYUkSb2UAqoo7stjw2kVPdTfZWcltuIOjuEYYLy3BsGj7WwX4AbPreaeShzBnSR2vxwy8jvIgcqg0T3Z0guR8NfamtPVituqM2z1FAgMBAAECggEACi5GHN9n/Cl3RUMUQhbA5UhaknPIjhLxTXc1f5kmNJhVO2ZqupggC28rgFO9EpZdQfu0xmwMlo3NUKvRCXNVi4KE6uVr0XXeiq0vAKAwY7PvAtI1MxHuQEtAt5eOIBF92C/ORMLuJZAugdboXuuX9mRdcco5LE2992T/HMRTetT3pdtG5jSrN6TXiwMA2rcTZK1JpUnqhdOMx7WYapRpk6Ewu2pkc5so7vkbGrVmEfv+L8g89KXXvVWH5isKfseidaa6oA1t2qkVRXgyAc7kESOVQ2pGxtyfHCEqSbxTKL1WzMhSQmrPpc0HZF9sarGA8S+aWCPwlk3Dr5Av7mLdwQKBgQDaKgzs6ugAuvMuRlEBbpig0gC4H/xqO3EFQdrjkER0afFLeGU+Sg1izN+oFSvpEHzAVXdErK2tiSSYZnBjegz2KMv6NcoNGlPnwZQtOlDjrnlIC+6738qYCMW9cZRce6u6m/qIAcMq4L8QwP0JthDfFJlsJr3uz03fHUbUScHvCQKBgQDLyCu2ESH9OTxpzaXRGbiEreyCc/J1JF+48EQyRM5UCkhfU2ROucnqunHdkPxcmMYpeANytDBRGLuXc0Be31txJ6j+jgEg4PFdCbqPlOvbaiI2zRu/9I7VB96mdTDLWf004vQHcBv/q0JGSyYkJimPVTbtfW3aphJU8zjzCs7vXQKBgHzJPKdvHdD9HgfD9KWrCuZB+GNY+FoTMexd5MPxSxaDWp9eeBqO5OU0ZmGP6pPe+FxTBmtjkdwG68W4ImTj9PIAF9xAVBf2jTOsyAKuroHs1DZfjOgtc4ZOzjwz8TaNDoconL6as3WdI+yDVrjtSO0wrSjtaApmzRXnexIGNkVxAoGBAMajkZBA9RvMDgqobkj8RHP4wYGMGjx7v18bva4uid07xL4/IJjo210WbuXmbUiplwhXNy05XZu/eySvMIEKQi1z1nSD4a+19TCWyicpqBrSQxWeK72aWxxnNm6V74FZb8t8qxkQ/AnlW43/tOcp1/siuo8XY9ztYxMxn0KyN3L1AoGAVo9G0e2xu4Ot8ftGqoA7daDp0YrM+TwHL4hg8wN919J6KWPWI6HXxxXeoXdXm1j6r63rk3Vd3cKoKfkXqccO66e1pUc1k+a4gy/07eLfIbU0NqAGKOY6IIKPycopyrqJtziQ3iz78bOHa8LukHOQimDoMU79VxQqxiGbhIU5wKs=";

    
   // private static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjmodwaDzBZnqM1dlMe9cW2n7kc/vchHg5Msv6blpjPjRflkURdEq6pX/piPTZAJ7LnLF3qB/OmRoY4IgA2iF1QbJ8wuKU2+JFWGb2ydy68SO12o6SS4QJzWb/6nCm3PtNPWDAmLVhT7FxsaHcbIih7bPq5gpBjspJhTBLuvXfBzdPAnYUCfQgZhqf+8f2GzKFgRohK+yBlJZbU7xL9NL8KowYLy2yl3w8RDPhW9S25RlN20Qpuj8p+p55NlJV7BXImM5OpDh3lVdMUQSD+ygbvPQozj+MXNP4ZRKSLNlXh3stVS7kCx0ijF89r8DGXKKPFNdkR6Vf4UgZ65+5ANZAwIDAQAB";

    private static String ALIPAY_PUBLIC_KEY=  "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv/enKq6vGExYV8/XF7dhl6uqQVmNTthTSNmos+dX0S566k8TM7BZ9unju5zNzpIQ3OrcyAcRSPzvvqXRXRL7aKclkjwhQWXzP93jfszB1gIsarPEKo9xDsG2AqousAgiPc6ogEoyIYZl4YgJE2pFzSVPbVOy7ze3ilRnDWe54XBPQuGT0l8Ms9TPL9QWU64AAgPUMEWIkRKq5oxW7jRKJPOlsmTAVMnmQeGthw2vixFQrpU0qh2MHDhV0B+HaVQ96kB1qL0J9WRNSOMSYaGHzXBG5ZiLjHjEM5lcT8IKlE6ebe5G7CNTnPs+oVZFhWJc2R4CqLoMvYmv9NX2tAys4wIDAQAB";

    private static String ALIPAY_PAYEE_USER_ID="2088102176505357";
    private static String ALIPAY_SERVER_URL="https://openapi.alipaydev.com/gateway.do";
    private static String ALIPAY_APP_ID="2016092000556635";

*/
   /* private static String ALIPAY_PAYEE_USER_ID="2088331240095283";
    private static String ALIPAY_SERVER_URL="https://openapi.alipay.com/gateway.do";
    private static String ALIPAY_APP_ID="2018112662302802";*/


  /*  public static AlipayClient createAlipayClient(){
        AlipayClient alipayClient=new DefaultAlipayClient(ALIPAY_SERVER_URL,
                ALIPAY_APP_ID,
                ALIPAY_PRIVATE_KEY,
                "json","utf-8",
                ALIPAY_PUBLIC_KEY,
                "RSA2");
        return alipayClient;
    }*/

    public static Result createFundAuthOrderVoucher(String amount, String title, String orderNo, String serialNum, String urlType){
        AlipayFundAuthOrderVoucherCreateModel model=new AlipayFundAuthOrderVoucherCreateModel();
        model.setPayeeUserId(SystemPropertiesConfig.ALIPAY_PAYEE_USER_ID);
        model.setProductCode("PRE_AUTH");
        model.setAmount(amount);
        model.setOrderTitle(title);
        model.setOutOrderNo(orderNo);
        model.setOutRequestNo(serialNum);
        model.setPayTimeout("5d");
        try {
            logger.info("subject:{},model:{}","资金授权发码接口",JsonUtil.gsonStr(model));
            AlipayFundAuthOrderVoucherCreateResponse response= AliPayFaceFundAuth.fundAuthOrderVoucherCreate(model,urlType);
            if (response.isSuccess()){
                JSONObject jsonObject=JSON.parseObject(response.getBody()) ;
                String code_value=response.getCodeValue();
               logger.info(code_value);
                return Result.success(code_value);
            }
            logger.info("支付宝生产授权码错误:{},{} ,{}",response.getCode(),response.getMsg(),response.getSubMsg());
            return Result.failure("请使用其他方式授权");
        } catch (AlipayApiException e) {
            logger.error("支付宝生产授权码异常",e);
            return  Result.failure("请使用其他方式授权");
        }
    }


    /**
     * 资金授权发码接口
     */
    public static AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate(AlipayFundAuthOrderVoucherCreateModel model,String urlType) throws AlipayApiException {
        AlipayFundAuthOrderVoucherCreateRequest request = new AlipayFundAuthOrderVoucherCreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(SystemPropertiesConfig.ALIPAY_NOTIFY_URL+urlType);
        request.setReturnUrl(SystemPropertiesConfig.ALIPAY_RETURN_URL+urlType);
        logger.info("subject:{},request:{}","资金授权发码接口",JsonUtil.gsonStr(request));
        AlipayFundAuthOrderVoucherCreateResponse response =createAlipayClient().execute(request);
        return  response;
    }


    /**
     * 资金授权操作查询接口
     */
    public static AlipayFundAuthOperationDetailQueryResponse fundAuthOperationDetailQuery(AlipayFundAuthOperationDetailQueryModel model,String urlType) throws AlipayApiException {
        AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest ();
        request.setBizModel(model);
        request.setNotifyUrl(SystemPropertiesConfig.ALIPAY_NOTIFY_URL+urlType);
        request.setReturnUrl(SystemPropertiesConfig.ALIPAY_RETURN_URL+urlType);
        AlipayFundAuthOperationDetailQueryResponse  response =createAlipayClient().execute(request);
        return  response;
    }

    /**
     * 资金授权撤销
     */
    public static AlipayFundAuthOperationCancelResponse  fundAuthOperationCancel(AlipayFundAuthOperationCancelModel model,String urlType) throws AlipayApiException {
        AlipayFundAuthOperationCancelRequest  request = new AlipayFundAuthOperationCancelRequest();
        request.setBizModel(model);
        request.setNotifyUrl(SystemPropertiesConfig.ALIPAY_NOTIFY_URL+urlType);
        request.setReturnUrl(SystemPropertiesConfig.ALIPAY_RETURN_URL+urlType);
        AlipayFundAuthOperationCancelResponse response =createAlipayClient().execute(request);
        return  response;
    }

    /**
     *    资金授权解冻接口
     */
    public static AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnfreeze(AlipayFundAuthOrderUnfreezeModel model,String urlType) throws AlipayApiException {
        AlipayFundAuthOrderUnfreezeRequest request = new AlipayFundAuthOrderUnfreezeRequest ();
        request.setBizModel(model);
        request.setNotifyUrl(SystemPropertiesConfig.ALIPAY_NOTIFY_URL+urlType);
        request.setReturnUrl(SystemPropertiesConfig.ALIPAY_RETURN_URL+urlType);
        AlipayFundAuthOrderUnfreezeResponse  response =createAlipayClient().execute(request);
        return  response;
    }

    /**
     * 资金授权冻结接口
     *
     * 收银员使用扫码设备读取用户支付宝钱包“付款码”后，将条码信息和订单信息通过本接口上送至支付宝发起资金冻结。
     */
    public static AlipayFundAuthOrderFreezeResponse  fundAuthOrderFreeze(AlipayFundAuthOrderFreezeModel model,String urlType) throws AlipayApiException {
        AlipayFundAuthOrderFreezeRequest request = new AlipayFundAuthOrderFreezeRequest  ();
        request.setBizModel(model);
        request.setNotifyUrl(SystemPropertiesConfig.ALIPAY_NOTIFY_URL+urlType);
        request.setReturnUrl(SystemPropertiesConfig.ALIPAY_RETURN_URL+urlType);
        AlipayFundAuthOrderFreezeResponse response =createAlipayClient().execute(request);
        return  response;
    }

    /**
     *
     * @param
     */
    public static void createOrderRent(String orderNo) {
        ZhimaMerchantOrderRentCreateRequest request = new ZhimaMerchantOrderRentCreateRequest();
        request.setBizContent("{" +
                "\"invoke_type\":\"WINDOWS\"," +
                "\"invoke_return_url\":\"https://www.bing.com\"," +
                "\"notify_url\":\"废弃，使用蚂蚁开放平台应用中的网关地址\"," +
                "\"invoke_state\":\"{\\\\\\\"xxx\\\\\\\":\\\\\\\"xxx\\\\\\\"}\"," +
                "\"out_order_no\":\"2018100111110004646445\"," +
                "\"product_code\":\"w1010100000000002858\"," +
                "\"goods_name\":\"充电宝\"," +
                "\"rent_info\":\"2小时内免费，超过2元/小时\"," +
                "\"rent_unit\":\"DAY_YUAN\"," +
                "\"rent_amount\":\"100.00\"," +
                "\"deposit_amount\":\"200.00\"," +
                "\"deposit_state\":\"Y\"," +
                "\"borrow_cycle\":\"2\"," +
                "\"borrow_cycle_unit\":\"HOUR\"," +
                "\"borrow_shop_name\":\"肯德基文三路门店\"," +
                "\"name\":\"张三\"," +
                "\"cert_no\":\"310101198001012567\"," +
                "\"rent_settle_type\":\"alipay\"," +
                "\"borrow_time\":\"2018-11-27 10:01:01\"," +
                "\"expiry_time\":\"2018-12-26 12:06:31\"," +
                "\"mobile_no\":\"13088888888\"," +
                "\"address\":\"浙江省杭州市西湖区万塘路18号黄龙时代广场B座2楼101室\"," +
                "\"credit_biz\":\"ZMRB170925160744\"," +
                "\"extend_info\":\"{\\\\\\\"borrow_shop_code\\\\\\\":\\\\\\\"A123456\\\\\\\"}\"" +
                "  }");
        ZhimaMerchantOrderRentCreateResponse response = null;
        try {
            response = createAlipayClient().pageExecute(request,"GET");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            logger.info(response.getBody());
           logger.info("调用成功");
        } else {
           logger.info("调用失败");
        }

    }

    /**
     *
     *  冻结结算
     *
     */
    public static AlipayTradePayResponse  tradePay(AlipayTradePayModel model,String urlType) throws AlipayApiException {
        AlipayTradePayRequest  request = new AlipayTradePayRequest();
        request.setNotifyUrl(SystemPropertiesConfig.ALIPAY_NOTIFY_URL+urlType);
        request.setReturnUrl(SystemPropertiesConfig.ALIPAY_RETURN_URL+urlType);
        request.setBizModel(model);
        AlipayTradePayResponse  response =createAlipayClient().execute(request);
        return  response;
    }

    /**
     * 结算
     * @param outTradeNo 订单号
     * @param unfreezeAmount  解冻金额
     * @param authNo  支付宝订单号
     * @param depositType   1:租车  2：违章
     * @return
     */
    public static Result  createAlipayFundAuthOrderUnfreezeModel(String outTradeNo,String unfreezeAmount, String authNo, Byte depositType){
        logger.info("解冻调用 outTradeNo：{}， unfreezeAmount ：{}, authNo：{},   type：{}",outTradeNo, unfreezeAmount, authNo,  depositType);
        AlipayFundAuthOrderUnfreezeModel alipayFundAuthOrderUnfreezeModel=new AlipayFundAuthOrderUnfreezeModel();
        alipayFundAuthOrderUnfreezeModel.setAuthNo(authNo);
        alipayFundAuthOrderUnfreezeModel.setOutRequestNo(outTradeNo);
        alipayFundAuthOrderUnfreezeModel.setAmount(unfreezeAmount);
      /*  Byte paymentMode=1;
        Integer paymentStatus=3;*/
        String urlType=null;
        if (depositType.intValue()==1){
            //租车结算
            alipayFundAuthOrderUnfreezeModel.setRemark("租车解冻授权资金");
            urlType=AliPayConstant.NOTIFY_URL_RENTAL_RENTAL_UNFREEZE;
        }else if(depositType.intValue()==2){
            //违章结算
            alipayFundAuthOrderUnfreezeModel.setRemark("违章解冻授权资金");
            urlType=AliPayConstant.NOTIFY_URL_RENTAL_VIOLATION_UNFREEZE;
        }else {
            logger.info("depositType类型错误 depositType{}",depositType);
            return Result.failure("类型错误");
        }
        try {
            AlipayFundAuthOrderUnfreezeResponse response= AliPayFaceFundAuth.fundAuthOrderUnfreeze(alipayFundAuthOrderUnfreezeModel,urlType);
            if (response.isSuccess() && AliPayConstant.ALIPAY_RESPONSE_CODE_SUCCESS.equals(response.getCode())){
                logger.info("支付宝解冻调用成功");
                return Result.success("调用成功");
            }else {
                logger.info("支付宝解冻调用失败 {}，{},{}",response.getCode(),response.getMsg(),response.getSubMsg());
                return Result.failure("支付宝调用失败");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝解冻异常",e);
            return Result.failure("支付宝调用异常");
        }
    }

    /**
     * 结算
     * @param outTradeNo 订单号
     * @param authNo  支付宝订单号
     * @param buyerId 买家id
     * @param depositType   1:租车  2：违章
     * @return
     */
    public static Result  createAlipayTradePayModel(String outTradeNo, String amount, String authNo, String buyerId, Byte depositType){
        logger.info("请求结算 outTradeNo：{}， amount ：{}, authNo：{}, buyerId：{},  type：{}",outTradeNo, amount, authNo, buyerId,  depositType);
        AlipayTradePayModel alipayTradePayModel=new AlipayTradePayModel();
        alipayTradePayModel.setOutTradeNo(outTradeNo);
        alipayTradePayModel.setTotalAmount(amount);
        alipayTradePayModel.setProductCode("PRE_AUTH");
        alipayTradePayModel.setAuthNo(authNo);
        alipayTradePayModel.setBuyerId(buyerId);
        alipayTradePayModel.setSellerId(SystemPropertiesConfig.ALIPAY_PAYEE_USER_ID);
        alipayTradePayModel.setAuthConfirmMode("COMPLETE");
        alipayTradePayModel.setTransCurrency("CNY");
        alipayTradePayModel.setSettleCurrency("CNY");
        String urlType;
        if (depositType.intValue()==1){
            //租车结算
            alipayTradePayModel.setSubject("租车冻结转支付");
            urlType=AliPayConstant.NOTIFY_URL_RENTAL_RENTAL_PAY;
        }else if(depositType.intValue()==2){
            //违章结算
            alipayTradePayModel.setSubject("违章冻结转支付");
            urlType=AliPayConstant.NOTIFY_URL_RENTAL_VIOLATION_PAY;
        }else {
            logger.info("depositType类型错误 depositType{}",depositType);
            return Result.failure("类型错误");
        }
        try {
            AlipayTradePayResponse response= AliPayFaceFundAuth.tradePay(alipayTradePayModel,urlType);
            if (response.isSuccess() && AliPayConstant.ALIPAY_RESPONSE_CODE_SUCCESS.equals(response.getCode())){
                return Result.success("结算支付宝调用成功");
            }else {
                logger.info("支付宝结算调用失败 {}，{},{}",response.getCode(),response.getMsg(),response.getSubMsg());
                return Result.failure("支付宝调用失败");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝结算异常",e);
            return Result.failure("支付宝调用异常");
        }

    }


    /**
     * 解冻
     * @param outTradeNo 订单号
     * @param unfreezeAmount  解冻金额
     * @param authNo  支付宝订单号
     * @param depositType   1:租车  2：违章
     * @return
     */
    public static Result createAlipayFundAuthOrderUnfreezeModelComm(String outTradeNo,String unfreezeAmount,
                        String authNo, Byte depositType, String urlType, String remark){
        logger.info("解冻调用 outTradeNo：{}， unfreezeAmount ：{}, authNo：{},   type：{},   urlType：{}",outTradeNo, unfreezeAmount, authNo, depositType, urlType);
        AlipayFundAuthOrderUnfreezeModel alipayFundAuthOrderUnfreezeModel=new AlipayFundAuthOrderUnfreezeModel();
        alipayFundAuthOrderUnfreezeModel.setAuthNo(authNo);
        alipayFundAuthOrderUnfreezeModel.setOutRequestNo(outTradeNo);
        alipayFundAuthOrderUnfreezeModel.setAmount(unfreezeAmount);
        alipayFundAuthOrderUnfreezeModel.setRemark(remark);
        try {
            AlipayFundAuthOrderUnfreezeResponse response= AliPayFaceFundAuth.fundAuthOrderUnfreeze(alipayFundAuthOrderUnfreezeModel,urlType);
            if (response.isSuccess() && AliPayConstant.ALIPAY_RESPONSE_CODE_SUCCESS.equals(response.getCode())){
                logger.info("支付宝解冻调用成功");
                return Result.success("调用成功");
            }else {
                logger.info("支付宝解冻调用失败 {}，{},{}",response.getCode(),response.getMsg(),response.getSubMsg());
                return Result.failure("支付宝调用失败");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝解冻异常",e);
            return Result.failure("支付宝调用异常");
        }
    }




    public static void main(String[] args) {

    }

}
