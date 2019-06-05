package com.ydc.commom.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BigBecimal 计算工具类 为简化计算代码和保证计算精度
 * @author gongjin
 */
public final class BigDecimalUtil {

	/**
	 * 创建一个 BigBecimal MathContext.DECIMAL128 精度设置与 IEEE 754R Decimal128 格式（即 34
	 * 个数字）匹配，舍入模式为 HALF_EVEN
	 * 
	 * @param num
	 *            要创建的数值
	 * @return
	 */
	public static BigDecimal getBigBecimal(double num) {
		return new BigDecimal(num, MathContext.DECIMAL128);
	}

	/**
	 * 返回两数相加之和
	 * 
	 * @param num1
	 *            加数1
	 * @param num2
	 *            加数2
	 * @return
	 */
	public static BigDecimal add(double num1, double num2) {
		return add(getBigBecimal(num1), getBigBecimal(num2));
	}

	/**
	 * 返回两数相加之和
	 * 
	 * @param num1
	 *            加数1
	 * @param num2
	 *            加数2
	 * @return
	 */
	public static BigDecimal add(BigDecimal num1, double num2) {
		return add(num1, getBigBecimal(num2));
	}

	/**
	 * 返回两数相加之和
	 * 
	 * @param num1
	 *            加数1
	 * @param num2
	 *            加数2
	 * @return
	 */
	public static BigDecimal add(double num1, BigDecimal num2) {
		return add(getBigBecimal(num1), num2);
	}

	/**
	 * 返回两数相加之和
	 * 
	 * @param num1
	 *            加数1
	 * @param num2
	 *            加数2
	 * @return
	 */
	public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
		return num1.add(num2, MathContext.DECIMAL128);
	}

	/**
	 * 返回两数相加之和
	 *
	 * @param num1
	 *            加数1
	 * @param num2
	 *            加数2
	 * @return
	 */
	public static BigDecimal addAllowNull(BigDecimal num1, BigDecimal num2) {
		BigDecimal new1 = num1 == null ? BigDecimal.ZERO : num1;
		BigDecimal new2 = num2 == null ? BigDecimal.ZERO : num2;
		return new1.add(new2, MathContext.DECIMAL128);
	}

	/**
	 * 返回两数相减之差
	 * 
	 * @param num1
	 *            减数
	 * @param num2
	 *            被减数
	 * @return
	 */
	public static BigDecimal subtract(double num1, double num2) {
		return subtract(getBigBecimal(num1), getBigBecimal(num2));
	}

	/**
	 * 返回两数相减之差
	 * 
	 * @param num1
	 *            减数
	 * @param num2
	 *            被减数
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal num1, double num2) {
		return subtract(num1, getBigBecimal(num2));
	}

	/**
	 * 返回两数相减之差
	 * 
	 * @param num1
	 *            减数
	 * @param num2
	 *            被减数
	 * @return
	 */
	public static BigDecimal subtract(double num1, BigDecimal num2) {
		return subtract(getBigBecimal(num1), num2);
	}

	/**
	 * 返回两数相减之差
	 * 
	 * @param num1
	 *            减数
	 * @param num2
	 *            被减数
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
		return num1.subtract(num2, MathContext.DECIMAL128);
	}

	/**
	 * 返回两数相减之差(NULL值默认为0)
	 *
	 * @param num1
	 *            减数
	 * @param num2
	 *            被减数
	 * @return
	 */
	public static BigDecimal subtractAllowNull(BigDecimal num1, BigDecimal num2) {
		BigDecimal newNum1 = num1 == null ? BigDecimal.ZERO : num1;
		BigDecimal newNum2 = num2 == null ? BigDecimal.ZERO : num2;
		return newNum1.subtract(newNum2, MathContext.DECIMAL128);
	}

	/**
	 * 返回两数相乘之积
	 * 
	 * @param num1
	 *            乘数1
	 * @param num2
	 *            乘数2
	 * @return
	 */
	public static BigDecimal multiply(double num1, double num2) {
		return multiply(getBigBecimal(num1), getBigBecimal(num2));
	}

	/**
	 * 返回两数相乘之积
	 * 
	 * @param num1
	 *            乘数1
	 * @param num2
	 *            乘数2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal num1, double num2) {
		return multiply(num1, getBigBecimal(num2));
	}

	/**
	 * 返回两数相乘之积
	 * 
	 * @param num1
	 *            乘数1
	 * @param num2
	 *            乘数2
	 * @return
	 */
	public static BigDecimal multiply(double num1, BigDecimal num2) {
		return multiply(getBigBecimal(num1), num2);
	}

	/**
	 * 返回两数相乘之积
	 * 
	 * @param num1
	 *            乘数1
	 * @param num2
	 *            乘数2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal num1, BigDecimal num2) {
		return num1.multiply(num2, MathContext.DECIMAL128);
	}

	/**
	 * 返回两数相除之商
	 * 
	 * @param num1
	 *            除数
	 * @param num2
	 *            被除数
	 * @return
	 */
	public static BigDecimal divide(double num1, double num2) {
		return divide(getBigBecimal(num1), getBigBecimal(num2));
	}

	/**
	 * 返回两数相除之商
	 * 
	 * @param num1
	 *            除数
	 * @param num2
	 *            被除数
	 * @return
	 */
	public static BigDecimal divide(BigDecimal num1, double num2) {
		return divide(num1, getBigBecimal(num2));
	}

	/**
	 * 返回两数相除之商
	 * 
	 * @param num1
	 *            除数
	 * @param num2
	 *            被除数
	 * @return
	 */
	public static BigDecimal divide(double num1, BigDecimal num2) {
		return divide(getBigBecimal(num1), num2);
	}

	/**
	 * 返回两数相除之商
	 * 
	 * @param num1
	 *            除数
	 * @param num2
	 *            被除数
	 * @return
	 */
	public static BigDecimal divide(BigDecimal num1, BigDecimal num2) {
	    //特殊处理，判断除数是否为0，如果为0，则返回0
	    //if(num2.intValue() == 0){
	    //    return num2;
	    //}
		BigDecimal zero = new  BigDecimal(0);
        int result = num2.compareTo(zero);
        if(result==0){
        	return num2;
        }
		return num1.divide(num2, MathContext.DECIMAL128);
	}

	/**
	 * 返回num1的num2次方 num1^num2
	 * 
	 * @param num1
	 *            原数
	 * @param num2
	 *            次方数
	 * @return
	 */
	public static BigDecimal pow(double num1, int num2) {
		return pow(getBigBecimal(num1), num2);
	}

	/**
	 * 返回num1的num2次方 num1^num2
	 * 
	 * @param num1
	 *            原数
	 * @param num2
	 *            次方数
	 * @return
	 */
	public static BigDecimal pow(BigDecimal num1, int num2) {
		return num1.pow(num2, MathContext.DECIMAL128);
	}

	/**
	 * 获取 Double 保留 2 位小数，四舍五入
	 * 
	 * @param num
	 *            操作数
	 * @return
	 */
	public static double getP2Double(BigDecimal num) {
		return getPDouble(num, 2);
	}
	
	/**
	 * 获取 Double 保留 1 位小数，四舍五入
	 * 
	 * @param num
	 *            操作数
	 * @return
	 */
	public static double getP1Double(BigDecimal num) {
		return getPDouble(num, 1);
	}

	/**
	 * 获取 Double 保留 2 位小数，四舍五入
	 * 
	 * @param num
	 *            操作数
	 * @return
	 */
	public static double getP3Double(BigDecimal num) {
		return getPDouble(num, 3);
	}

	/**
	 * 获取 Double
	 * 
	 * @param num
	 *            操作数
	 * @param round
	 *            要保留的小数位数
	 * @return
	 */
	public static double getPDouble(BigDecimal num, int round) {
		return num.setScale(round, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * 获取 BigDecimal 保留 2 位小数，四舍五入
	 * 
	 * @param num
	 *            操作数
	 * @return
	 */
	public static BigDecimal getP2BigDecimal(BigDecimal num) {
		return getPDecimal(num, 2);
	}

	/**
	 * 获取 BigDecimal 保留 2 位小数，四舍五入
	 * 
	 * @param num
	 *            操作数
	 * @return
	 */
	public static BigDecimal getP3Decimal(BigDecimal num) {
		return getPDecimal(num, 3);
	}

	/**
	 * 获取 BigDecimal
	 * 
	 * @param num
	 *            操作数
	 * @param round
	 *            要保留的小数位数
	 * @return
	 */
	public static BigDecimal getPDecimal(BigDecimal num, int round) {
		return num.setScale(round, RoundingMode.HALF_UP);
	}

	/**
	 * 
	 * 验证小数位后面2位，小数点前面9位
	 * @author:gongjin
	 * @param:
	 * @date: 2017年6月16日 下午4:31:58
	 * @return:boolean
	 */
	public static boolean isTwoDecimal(String target) {
		Pattern patternNum = Pattern
				.compile("^((([1-9]{1}\\d{0,9}))((\\.(\\d){1,2}))?|([0]{1}))((\\.(\\d){1,2}))?$");
		Matcher matcherBatchId = patternNum.matcher(target);
		return matcherBatchId.matches();
	}

	/**
	 * 返回 BigDecimal
	 * @param obj
	 * @return
	 */
	public static BigDecimal retBigDecimal(Object obj){
		if(StringUtil.isEmpty(obj)){
			return BigDecimal.valueOf(0);
		}
		return BigDecimal.valueOf(Double.valueOf(obj.toString()));
	}


	public static  BigDecimal convertString(String str){
		try {
			BigDecimal bigDecimal=new BigDecimal(str);
			return bigDecimal;
		}catch (Exception e){
			return  null;
		}
	}

	/**
	 *  判断取值范围
	 * @param
	 */
	public static boolean between(BigDecimal value,BigDecimal minValue,BigDecimal maxValue){
		try {
			if (value ==null ){
				return false;
			}
			if (minValue == null || value.compareTo(minValue)<=0 ){
				return false;
			}
			if (maxValue == null || value.compareTo(maxValue)>=0 ){
				return false;
			}
			return true;
		}catch (Exception e){
			return  false;
		}
	}

	public static void main(String[] ags){

		BigDecimal a = new BigDecimal(100.55);
		BigDecimal b = new BigDecimal(3);
		System.out.println(a.divide(b));
	}
}
