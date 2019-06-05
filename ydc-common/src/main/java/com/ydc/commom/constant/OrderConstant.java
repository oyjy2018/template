package com.ydc.commom.constant;

/**
 * 订单状态
 *
 * @author gongjin
 * @create 2018-09-10 12:33
 **/
public class OrderConstant {

    /**
     * 0：创建订单
     */
    public static final int ORDER_STATUS_CREATE = 0;


    /**
     * 1：待付款
     */
    public static final int ORDER_STATUS_UNPAID = 1;

    /**
     * 2：待发货
     */
    public static final int ORDER_STATUS_PAID_UNDELIVERED = 2;

    /**
     * 3：待收货
     */
    public static final int ORDER_STATUS_SHIPPED = 3;

    /**
     * 4：交易成功
     */
    public static final int ORDER_STATUS_RECEIVED = 4;

    /**
     * 8：订单关闭
     */
    public static final int ORDER_STATUS_CLOSED= 8;
}
