package com.ydc.commom.util.rental;

import com.ydc.commom.util.DateUtil;

import java.util.Date;

/**
 * 订单号
 *
 * @author
 * @create 2018-11-23 13:52
 **/
public class RentalOrderNoUtil {


    /**
     * 返回订单号
     * @return
     */
    public static String getOrderNo(){
        return DateUtil.format(new Date(),DateUtil.NUM_YEAR_MONTH_TIME)+String.valueOf((int)((Math.random()*9+1)*100000));
    }
}
