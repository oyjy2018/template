package com.ydc.commom.constant.rental;

/**
 * 租车结算
 *
 * @author
 * @create 2018-11-27 20:00
 **/
public class RentalSettlementConstant {

    /**
     * 租车预授权结算状态 1：未结算
     */
    public static final int RENTAL_AUTH_STATUS_1 = 1;

    /**
     * 租车预授权结算状态 2：已结算未付清
     */
    public static final int RENTAL_AUTH_STATUS_2 = 2;

    /**
     * 租车预授权结算状态 10已结清
     */
    public static final int RENTAL_AUTH_STATUS_10 = 10;


    /**
     * 违章预授权结算状态 1：未结算
     */
    public static final int VIOLATION_AUTH_STATUS_1 = 1;

    /**
     * 违章预授权结算状态 2：已结算未付清
     */
    public static final int VIOLATION_AUTH_STATUS_2 = 2;

    /**
     * 违章预授权结算状态 10已结清
     */
    public static final int VIOLATION_AUTH_STATUS_10 = 10;

    /**
     * 未结算
     */
    public static final int SETTLE_STATUS_1 = 1;

    /**
     * 已结算未付清
     */
    public static final int SETTLE_STATUS_2 = 2;

    /**
     * 已结清
     */
    public static final int SETTLE_STATUS_10 = 10;

    /**
     * 结算失败
     */
    public static final int SETTLE_STATUS_9 = 9;

    public static final  String  SETTLEMENT_TYPE_UNFREEZE="unfreeze";

    public static final  String  SETTLEMENT_TYPE_PAY="pay";


}
