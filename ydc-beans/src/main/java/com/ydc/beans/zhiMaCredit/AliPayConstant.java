package com.ydc.beans.zhiMaCredit;

public class AliPayConstant {

    //租车冻结
    public static  final String NOTIFY_URL_RENTAL_RENTAL_FREEZE="rental/freeze";

    //违章冻结
    public static  final String NOTIFY_URL_RENTAL_VIOLATION_FREEZE="violation/freeze";

    //租车扣除
    public static  final String NOTIFY_URL_RENTAL_RENTAL_PAY="rental/pay";

    //违章扣除
    public static  final String NOTIFY_URL_RENTAL_VIOLATION_PAY="violation/pay";

    //租车解冻
    public static  final String NOTIFY_URL_RENTAL_RENTAL_UNFREEZE="rental/unfreeze";

    //违章解冻
    public static  final String NOTIFY_URL_RENTAL_VIOLATION_UNFREEZE="violation/unfreeze";




    public static  final  String ALIPAY_RESPONSE_CODE_SUCCESS="10000";//接口调用成功


    //阿里回调 类型
    //授权回调
    public final static  String ALIPAY_CALLBACK_AUTH_FREEZE="FUND_AUTH_FREEZE";

    //支付回调
    public final static  String ALIPAY_CALLBACK_PAY="TRADE_SUCCESS";


    //解冻回调
    public final static  String ALIPAY_CALLBACK_AUTH_UNFREEZE="FUND_AUTH_UNFREEZE";

}
