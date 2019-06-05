package com.ydc.beans.zhiMaCredit;

import com.alipay.api.response.AlipayTradePayResponse;
import com.ydc.commom.constant.rental.RentalSettlementConstant;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.model.util.DateUtil;
import com.ydc.model.util.MapUtil;

import java.util.Date;
import java.util.Map;

/**
 * 阿里对象转换工具
 */
public class AliPayEntityConvertUtil {


    public static AlipayTradePayResponseDTO convertAlipayTradePayResponseDTO (AlipayTradePayResponse alipayTradePayResponse){
        System.out.println("打印阿里的回传"+alipayTradePayResponse.toString());
        AlipayTradePayResponseDTO alipayTradePayResponseDTO=new AlipayTradePayResponseDTO();
        alipayTradePayResponseDTO.setBuyerLogonId(alipayTradePayResponse.getBuyerLogonId());
        alipayTradePayResponseDTO.setBuyerPayAmount(alipayTradePayResponse.getBuyerPayAmount());
        alipayTradePayResponseDTO.setBuyerUserId(alipayTradePayResponse.getBuyerUserId());
        alipayTradePayResponseDTO.setGmtPayment(alipayTradePayResponse.getGmtPayment());
        alipayTradePayResponseDTO.setOutTradeNo(alipayTradePayResponse.getOutTradeNo());
        alipayTradePayResponseDTO.setPayAmount(alipayTradePayResponse.getPayAmount());
        alipayTradePayResponseDTO.setReceiptAmount(alipayTradePayResponse.getReceiptAmount());
        alipayTradePayResponseDTO.setTotalAmount(alipayTradePayResponse.getTotalAmount());
        alipayTradePayResponseDTO.setTradeNo(alipayTradePayResponse.getTradeNo());
        return alipayTradePayResponseDTO;
    }

    public static AlipayTradePayResponseDTO convertAlipayTradePayResponseDTO (Map<String, String[]> map,String type){
        AlipayTradePayResponseDTO alipayTradePayResponseDTO=new AlipayTradePayResponseDTO();
        if (RentalSettlementConstant.SETTLEMENT_TYPE_PAY.equals(type)){
            alipayTradePayResponseDTO.setOutTradeNo( MapUtil.getMapValueByKey(map,"out_trade_no"));//订单号
            alipayTradePayResponseDTO.setTradeNo(MapUtil.getMapValueByKey(map,"trade_no"));//支付宝资金授权订单号
            alipayTradePayResponseDTO.setSettlementId(MapUtil.getMapValueByKey(map,"notify_id"));//支付宝流水号
            alipayTradePayResponseDTO.setBuyerLogonId(MapUtil.getMapValueByKey(map,"buyer_logon_id"));//买家登录账号
            alipayTradePayResponseDTO.setBuyerPayAmount(MapUtil.getMapValueByKey(map,"buyer_pay_amount"));//买家支付金额
            alipayTradePayResponseDTO.setBuyerUserId(MapUtil.getMapValueByKey(map,"buyer_id"));//买家ID
            Date date=DateUtil.parseDate(MapUtil.getMapValueByKey(map,"gmt_payment"), DateUtil.DATATIMEF_STR);
            alipayTradePayResponseDTO.setGmtPayment(date==null?new Date():date);
            alipayTradePayResponseDTO.setPayAmount(MapUtil.getMapValueByKey(map,"pay_amount"));//支付币种订单金额
            alipayTradePayResponseDTO.setReceiptAmount(MapUtil.getMapValueByKey(map,"receipt_amount"));//实际支付金额  必须
            alipayTradePayResponseDTO.setTotalAmount(MapUtil.getMapValueByKey(map,"total_amount"));//交易金额

        }else if (RentalSettlementConstant.SETTLEMENT_TYPE_UNFREEZE.equals(type)){
            alipayTradePayResponseDTO.setOutTradeNo( MapUtil.getMapValueByKey(map,"out_order_no"));//订单号
            alipayTradePayResponseDTO.setTradeNo(MapUtil.getMapValueByKey(map,"auth_no"));//支付宝资金授权订单号
            alipayTradePayResponseDTO.setSettlementId(MapUtil.getMapValueByKey(map,"operation_id"));//支付宝流水号
            alipayTradePayResponseDTO.setBuyerLogonId(MapUtil.getMapValueByKey(map,"payer_logon_id"));//买家登录账号
            alipayTradePayResponseDTO.setBuyerPayAmount(MapUtil.getMapValueByKey(map,"total_pay_amount"));//买家支付金额
            alipayTradePayResponseDTO.setBuyerUserId(MapUtil.getMapValueByKey(map,"payer_user_id"));//买家ID
            Date date=DateUtil.parseDate(MapUtil.getMapValueByKey(map,"gmt_trans"), DateUtil.DATATIMEF_STR);
            alipayTradePayResponseDTO.setGmtPayment(date==null?new Date():date);
            alipayTradePayResponseDTO.setPayAmount(MapUtil.getMapValueByKey(map,"total_pay_amount"));//支付币种订单金额
            alipayTradePayResponseDTO.setReceiptAmount(MapUtil.getMapValueByKey(map,"total_pay_amount"));//实际支付金额 必须
            alipayTradePayResponseDTO.setTotalAmount(MapUtil.getMapValueByKey(map,"total_freeze_amount"));//总共冻结金额
        }

        /*
        Key = operation_id value=20181222653945337402
        Key = notify_time value=2018-12-22 10:52:44
        Key = auth_no value=2018122210002001740275146706
        Key = status value=SUCCESS
        Key = sign_type value=RSA2
        Key = auth_app_id value=2018112662302802
        Key = charset value=utf-8
        Key = out_order_no value=201812051732333311
        Key = notify_type value=fund_auth_unfreeze
        Key = payer_user_id value=2088502988805742
        Key = operation_type value=UNFREEZE
        Key = version value=1.0
        Key = sign value=fEKimDDgrJLcUU6UGjlH5chP8/H92TahEkRNGK96MopKufvfViOiQYZ9sUsOqVWKBA5/AzYW1ICZ14qdCDnzRNEa35C/8aK2mCxfMOcszCnDO6t3Q
        +skrRNY3SJLlfjM6hrOb3h/QU5FEYy9l5E4quj8gt0ZBC/S8+yB+TrhLh7D+yFQ6HdY5AcSvnxlEGF3Bl/1QUx/iq2cQMSy8i0tmf2pwSMpVlNZJhoHq1wHHd
        +R62Vu0Phc9hDQp6jkIXyfZiQYV3hr5OMgsY9t1sIWcRh4kWr459OAp648DpI0hIXEPLtu1XVFf0jJrgKmyORWr2rVtxtF9+ZdG6EB4ySMgQ==
        Key = total_unfreeze_amount value=0.01  //总共解冻金额
        Key = amount value=0.01  //本次解冻金额
        Key = gmt_create value=2018-12-22 10:52:44
        Key = rest_amount value=0.04   //剩余冻结金额
        Key = total_freeze_amount value=0.05   //冻结总金额
        Key = payer_logon_id value=qin***@163.com
        Key = out_request_no value=201812051732333311
        Key = app_id value=2018112662302802
        Key = notify_id value=2018122200222105244043740254493930
        Key = total_pay_amount value=0.00  // 支付金额
        Key = gmt_trans value=2018-12-22 10:52:44
         */
        return alipayTradePayResponseDTO;
    }
}
