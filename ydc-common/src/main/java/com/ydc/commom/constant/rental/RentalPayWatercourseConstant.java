package com.ydc.commom.constant.rental;
/**
 * 结算流水
 *
 * @author
 * @create 2018-11-27 20:06
 **/
public class RentalPayWatercourseConstant {


    /**
     * 支付方式 1：芝麻
     */
    public static final byte PAYMENT_MODE_1 = 1;

    /**
     * 支付方式 2：信用卡
     */
    public static final byte PAYMENT_MODE_2 = 2;

    /**
     * 支付方式 3：现金
     */
    public static final byte PAYMENT_MODE_3 = 3;


    /**
     * 流水类型 1:租车冻结
     */
    public static final byte DEPOSIT_TYPE_1 = 1;

    /**
     * 流水类型  2：违章冻结
     */
    public static final byte DEPOSIT_TYPE_2 = 2;

    /**
     * 流水类型  3:租车押金结算
     */
    public static final byte DEPOSIT_TYPE_3 = 3;


    /**
     * 流水类型  4：违章押金结算
     */
    public static final byte DEPOSIT_TYPE_4 = 4;

    /**
     * 流水类型  8：机务
     */
    public static final byte DEPOSIT_TYPE_8 = 8;


    /**
     * 未结算
     */
    public static final  Byte AUTH_STATUS_1=1;
    /**
     * 已结算未付清
     */
    public static final  Byte AUTH_STATUS_2=2;
    /**
     * 已结清
     */
    public static final  Byte AUTH_STATUS_10=10;
}

