package com.ydc.commom.constant;

import java.util.HashMap;
import java.util.Map;

public class PayConstant {

    //微信支付
    public static final String PAY_WECHATPAY = "wechatPay";

    //积分支付
    public static final String PAY_INTEGRAL = "integral";

    //微信内支付
    public static final int IN_WEIXIN_PAY = 1;

    //H5内支付
    public static final int IN_H5_PAY = 2;

    //app支付
    public static final int IN_APP_PAY = 3;

    //币种---积分
    public static final String PAYMENT_CURRENCY_INTEGRAL = "integral";

    //币种---人民币
    public static final String PAYMENT_CURRENCY_CNY = "cny";

    //交易方式----余额（先阶段就是积分）
    public static final int TRANSACTION_METHOD_ACCOUNT = 1;

    //交易方式----微信
    public static final int TRANSACTION_METHOD_WEIXIN = 2;

    //交易类型----积分
    public static final int transaction_type_integral = 1;

    //交易类型----现金
    public static final int TRANSACTION_TYPE_CASH = 2;

    //交易类型----混合
    public static final int TRANSACTION_TYPE_MESS = 3;

    //流水状态----交易中
    public static final int WATER_STATUS_ING = 1;

    //流水状态----完成
    public static final int WATER_STATUS_DONE = 2;

    //流水状态----取消
    public static final int WATER_STATUS_CANCEL = 3;

    //流水类型----获取
    public static final int WATER_TYPE_GAIN = 0;

    //流水类型----消耗
    public static final int WATER_TYPE_CONSUME = 1;

    //微信支付交易环境类型
    public static final String TRADE_TYPE_H5 = "MWEB";
    public static final String TRADE_TYPE_JS = "JSAPI";
    public static final String TRADE_TYPE_APP = "APP";

    //支付方式-----币种的enum
    public enum PaymentCurrencyEnum{
        PAYMENT_METHOD_CURRENCY_INTEGRAL(PAY_INTEGRAL, PAYMENT_CURRENCY_INTEGRAL),
        payment_Method_Currency_CNY(PAY_WECHATPAY, PAYMENT_CURRENCY_CNY);

        private String paymentMethod;
        private String paymentCurrency;
        PaymentCurrencyEnum(String paymentMethod, String paymentCurrency){
            this.paymentMethod = paymentMethod;
            this.paymentCurrency = paymentCurrency;
        }

        private static final Map<String, PaymentCurrencyEnum> mapToEnum = new HashMap<>();
        static {
            for (PaymentCurrencyEnum paymentCurrencyEnum : PaymentCurrencyEnum.values()){
                mapToEnum.put(paymentCurrencyEnum.paymentMethod, paymentCurrencyEnum);
            }
        }

        public static PaymentCurrencyEnum getPaymentCurrencyEnumByMethod(String paymentMethod){
            return mapToEnum.get(paymentMethod);
        }

        public static String getPaymentCurrencyByMethod(String paymentMethod){
            PaymentCurrencyEnum paymentCurrencyEnum = getPaymentCurrencyEnumByMethod(paymentMethod);
            return paymentCurrencyEnum == null ? null : paymentCurrencyEnum.paymentCurrency;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public String getPaymentCurrency() {
            return paymentCurrency;
        }
    }
}
