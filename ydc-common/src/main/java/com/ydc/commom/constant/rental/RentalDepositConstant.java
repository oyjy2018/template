package com.ydc.commom.constant.rental;

/**
 * 租赁押金
 *
 * @author
 * @create 2018-11-24 9:28
 **/
public class RentalDepositConstant {


    /**
     * 押金类型 1：租车冻结
     */
    public static final Byte DEPOSIT_TYPE_1 = 1;

    /**
     * 押金类型 2:违章冻结
     */
    public static final Byte DEPOSIT_TYPE_2 = 2;




    /**
     * 押金状态 1：未支付
     */
    public static final Byte PAYMENT_STATUS_1 = 1;

    /**
     * 押金状态 2：已支付
     */
    public static final Byte PAYMENT_STATUS_2 = 2;


    /**
     * 押金状态 3：已退还
     */
    public static final Byte PAYMENT_STATUS_3 = 3;

    /**
     * 押金状态 9：退还失败
     */
    public static final Byte PAYMENT_STATUS_9 = 9;

    /**
     * 押金状态 3：退还中
     */
    public static final Byte PAYMENT_STATUS_4 = 4;



    public static final Byte PAYMENT_MODE_1=1;//芝麻
    public static final Byte PAYMENT_MODE_2=2;//信用卡
    public static final Byte PAYMENT_MODE_3=3;//现金/转账



}
