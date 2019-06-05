package com.ydc.commom.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class NumberUtil {

    /**
     * 格式化金额，保留两位小数点
     */
    public static final String moneyFormat = "######0.00";

    /**
     * 格式化金额，保留四位小数点
     */
    public static final String moneyFormat4 = "######0.0000";

    /**
     * 金额格式化
     */
    public static final String moneyFormat2 = "#,###,##0.##";

    /**
     * 金额格式化
     */
    public static final String moneyFormat3 = "#,###,##0.00";

    /**
     * 生成x位数的随机数
     *
     * @param x
     * @return
     */
    public static String getNumberCheckCode(int x) {
        char[] codeSerial = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String ranNumber = "";
        // 生成一个x位数的随机码
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < x; i++) {
            // 取索引为0-26以内的随机数，从codeSeria中取
            int a = random.nextInt(10);
            ranNumber += codeSerial[a];
        }
        return ranNumber;
    }

    //判断是否小于0
    public static boolean hasPositiveNumber(Integer number){
        try {
            int tempNumber=number.intValue();
            if (tempNumber<0){
                return false;
            }
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    public static void main(String[] args) {
        System.out.println(hasPositiveNumber(1));
    }


    //相加
    public static Integer addTogether(Integer number1,Integer number2){
        try {
            int count=0;
            if (null!=number1){
                count=count+number1.intValue();
            }
            if (null!=number2){
                count=count+number2.intValue();
            }
            return  count;
        }catch (Exception e){
            return  null;
        }
    }

    /**
     * 判断是否为纯数字（不包含小数点）
     * @param:
     * @date: 2018年7月11日 下午2:07:47
     * @return:boolean
     */
    public static boolean isNum(Object str){
        return (Pattern.compile("[0-9]*")).matcher(String.valueOf(str)).matches();

    }

    public static boolean hasNumber(String str,int before,int after){
        Pattern patternNum = Pattern
                .compile("^([1-9]\\d{0,"+(before-1)+"}"+ (after > 0 ? "(\\.\\d{1,"+after+"})?":"")
                        +"|"
                        + "[0]"+(after > 0 ? "(\\.\\d{1,"+after+"})?":"")+")$");
        Matcher matcherBatchId = patternNum.matcher(str);
        return matcherBatchId.matches();
    }

    /**
     * @description 数值格式化
     *
     * @param amount
     *            字符型日期
     * @param format
     *            格式
     * @return 返回日期
     */
    public static String numberFormat(double amount, String format) {
        DecimalFormat numberFormat = new DecimalFormat(format);
        return numberFormat.format(amount);
    }
    /**
     * 得到微信支付金额
     * @param price
     * @return
     */
    public static String getWeiXinPayAmount(BigDecimal price){
        if (price == null)
            return String.valueOf(BigDecimal.ZERO.longValue());
        return String.valueOf((BigDecimal.valueOf(100L).multiply(price)).longValue());
    }
}
