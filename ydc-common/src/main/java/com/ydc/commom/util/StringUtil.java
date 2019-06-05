package com.ydc.commom.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.constant.LoanApplyConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 *
 * @author gongjin
 * @create 2017-05-03 14:14
 **/
public class StringUtil {

    private static Logger logger = LogManager.getLogger(StringUtil.class);

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > -1) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * str空判断
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null == str || str.equalsIgnoreCase("null") || str.equals("")) {
            return true;
        } else
            return false;
    }

    /**
     * Object 判断是否为空
     *
     * @author:gongjin
     * @param:
     * @date: 2017年11月27日 下午3:06:46
     * @return:boolean
     */
    public static boolean isEmpty(Object str) {
        if (str == null || "".equals(str) || "null".equalsIgnoreCase(str.toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * str不为空判断
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (null != str && !str.equalsIgnoreCase("null") && !str.equals("")) {
            return true;
        } else
            return false;
    }

    /**
     * 判断是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        if (null != obj && !"".equals(obj) && !"null".equals(obj)) {
            return true;
        }
        return false;
    }

    /**
     * 把字串转化为整数,若转化失败，则返回0
     *
     * @param str 字串
     * @return
     */
    public static int strToInt(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            logger.error("subject:{},str:{},e:{}", "strToInt异常", str, ex);
        }
        return 0;
    }

    /**
     * 把字串转化为长整型数,若转化失败，则返回0
     *
     * @param str
     * @return
     */
    public static long strToLong(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception ex) {
            LoggerUtil.error(logger, ex, "subject, str", "strToLong异常", str);
        }
        return 0;
    }

    /**
     * 把字串转化为Float型数据,若转化失败，则返回0
     *
     * @param str
     * @return
     */
    public static float strToFloat(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception ex) {
            logger.error("subject:{},str:{},e:{}", "strToFloat异常", str, ex);
        }
        return 0;
    }

    /**
     * 把字串转化为Double型数据，若转化失败，则返回0
     *
     * @param str
     * @return
     */
    public static double strToDouble(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception ex) {
            LoggerUtil.error(logger, ex, "subject, str", "strToDouble异常", str);
        }
        return 0;
    }

    public static Boolean objToBoolean(Object o){
        if(o == null) return null;
        try {
            return Boolean.parseBoolean(o.toString());
        } catch (Exception ex) {
            LoggerUtil.error(logger, ex, "subject, o", "objToBoolean异常", o);
        }
        return null;
    }

    /**
     * 把字串去空
     *
     * @param str
     * @return
     */
    public static String trimStr(String str) {
        if (str == null) {
            return "";
        }
        return str.trim();
    }

    /**
     * 判断是否手机号码格式
     *
     * @param str
     * @return
     */
    public static boolean isMobilePhone(String str) {
        return str
                .matches("^\\d{11}$");
    }


    /**
     * 把字符串，按指字的分隔符分隔为字符串数组
     *
     * @param list       字符串
     * @param seperators 分隔符
     * @return 字符串数组
     */
    public static String[] split(String list, String seperators) {
        return split(list, seperators, false);
    }

    /**
     * 把字符串，按指字的分隔符分隔为字符串数组
     *
     * @param seperators 分隔符
     * @param list       字符串
     * @param include    是否需要把分隔符也返回
     * @return 字符串数组
     */
    public static String[] split(String list, String seperators, boolean include) {
        StringTokenizer tokens = new StringTokenizer(list, seperators, include);
        String[] result = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            result[i++] = tokens.nextToken();
        }
        return result;
    }

    /**
     * 是否为空格串(判断是否为null 、"" 或 " ") isBlank(null) = true; isBlank("") = true; isBlank(" ") = true;
     *
     * @param str 输入的字符串
     * @return 是否为空格串
     */
    public static boolean isBlank(final String str) {
        boolean bResult = true;
        // 是否为空串，如果为空串直接返回
        if (StringUtil.isEmpty(str)) {
            bResult = true;
        } else {
            // 检查字符串是否全部为空格
            for (int i = 0; i < str.length(); i++) {
                if (!(Character.isWhitespace(str.charAt(i)))) {
                    bResult = false;
                    break;
                }
            }
        }
        // 全部为空格，则返回true
        return bResult;
    }

    /**
     * 判断字符是否是日期格式
     *
     * @param date 字符串
     * @return boolean
     * @author chenyanling
     */
    public static boolean isDateStringValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        // 输入对象不为空
        try {
            sdf.parse(date);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }

    /**
     * 获取4位随机码
     *
     * @return
     */
    public static String getPwdCode() {
        char[] codeSerial = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String ranNumber = "";
        // 生成一个4位数的随机码
        for (int i = 0; i < 4; i++) {
            // 取索引为0-35以内的随机数，从codeSeria中取
            int a = (new Random()).nextInt(10);
            ranNumber += codeSerial[a];
        }

        return "ydkj" + ranNumber;
    }

    /**
     * 验证邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email) {
            return false;
        }
        email = email.trim();
        if (isEmpty(email)) {
            return false;
        } else if (email.length() < 5 || email.length() > 32) {
            return false;
        }
        String email_matcher = "([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        Pattern pat = Pattern.compile(email_matcher);
        Matcher mat = pat.matcher(email);
        if (!mat.find()) {
            return false;
        }
        return true;
    }

    /**
     * 检查字符串里面是否包含中文
     * 包含 返回 true
     * 不包含 返回 false
     *
     * @author:gongjin
     * @param:
     * @date: 2017年7月12日 下午3:18:53
     * @return:boolean
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 将对象做String输出，如果是NULL值，则输出NULL
     *
     * @param o
     * @return
     */
    public static String objToStr(Object o) {
        if (o == null) return null;
        return o.toString();
    }

    /**
     * 把字串转化为整数,若转化失败，则返回null
     *
     * @param str 字串
     * @return
     */
    public static Integer strToInteger(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            logger.error("subject:{},str:{},e:{}", "strToInt异常", str, ex);
        }
        return null;
    }

    /**
     * 字符串转BigDecimal
     *
     * @param o
     * @return
     */
    public static BigDecimal objToBigDecimal(Object o) {
        if (isEmpty(o)) return new BigDecimal(0);
        try {
            return new BigDecimal(String.valueOf(o).trim());
        } catch (Exception e) {
            logger.error("字符串转BigDecimal异常", e);
            return new BigDecimal(0);
        }
    }

    /**
     * 银行卡号格式化，每四位加空格
     *
     * @author:gongjin
     * @param:
     * @date: 2017年9月12日 上午9:50:11
     * @return:String
     */
    public static String splitBankCard(String bankCard) {
        if (isEmpty(bankCard)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bankCard);

        int length = bankCard.length() / 4 + bankCard.length();
        for (int i = 0; i < length; i++) {
            if (i % 5 == 0) {
                sb.insert(i, " ");
            }
        }
        return sb.deleteCharAt(0).toString();
    }

    /**
     * 截取emoji表情
     *
     * @param str
     * @return
     */
    public static String removeNonBmpUnicode(String str) {
        try {
            if (isEmpty(str)) {
                return null;
            }
            str = str.replaceAll("[^\\u0000-\\uFFFF]", "");
            return str;
        }catch (Exception e){
            return  null;
        }

    }

    /**
     * String属性验证返回
     *
     * @author:gongjin
     * @param:
     * @date: 2017年11月27日 下午3:41:33
     * @return:String
     */
    public static String retObj(Object obj) {
        if (isEmpty(obj)) {
            return null;
        } else {
            return obj.toString();
        }
    }

    /**
     * Integer属性验证返回
     *
     * @author:gongjin
     * @param:
     * @date: 2018年1月24日 下午2:46:45
     * @return:Integer
     */
    public static Integer retInt(Object obj) {
        if (isEmpty(obj))
            return null;
        return Integer.valueOf(obj.toString());
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        boolean result = false;
        if (str.matches("\\d+(\\.\\d+)?")) {
            result = true;
        }
        return result;
    }

    /**
     * 首字母转小写
     *
     * @param str
     * @return
     */
    public static String toLowerCaseFirstOne(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            StringBuilder newStr = new StringBuilder();
            newStr.append(Character.toLowerCase(str.charAt(0))).append(str.substring(1));
            return newStr.toString();
        }
    }

    /**
     * 替换特殊字符
     *
     * @param str
     * @return
     * @author huyueming
     * @date 2017年9月7日
     */
    public static String replaceSpecStr(String str) {
        if (!StringUtils.isEmpty(str)) {
            String regEx = "[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。》>、/？?]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("_");
        }
        return null;
    }

    /**
     * 检查对象里面的所有属性是否为null
     *
     * @author:gongjin
     * @param: filterList 需要过滤属性名
     * @date: 2018年1月11日 下午12:38:20
     * @return:boolean
     */
    public static boolean isAllFieldNull(Object obj, List<String> filterList) throws Exception {
        Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            if (filterList.contains(f.getName())) {
                continue;
            }
            Object val = f.get(obj);// 得到此属性的值
            if (val != null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 去除字符串里的html标签
     *
     * @param str
     * @return
     */
    public static String delHtmlTag(String str) {
        str = str.replaceAll("<[.[^>]]*>", "");
        str = str.replaceAll(" ", "");
        return str;
    }

    /**
     * 判断是否是整数
     *
     * @param str
     * @return
     */
    public static boolean hasInteger(String str) {
        boolean result = false;
        if (str.matches("^[1-9]+\\d*$|^[0]$")) {
            result = true;
        }
        return result;
    }

    /**
     * 防止sql注入处理
     *
     * @param str
     * @return
     */
    public static String preventInjection(String str) {
        if (str == null) {
            return null;
        }
        String reg = "(?i)(?:')|(?:--)|(?:;)|(?:=)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                + "(\\b(select|update|union|and|or|where|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        return str.replaceAll(reg, "");
    }


    /**
     * BigDecimal 转String
     * @param o
     * @return
     */
    public static String bigDecimalToString(BigDecimal o){
        if (o == null) return "0.00";
        try {
            return new String(String.valueOf(o.doubleValue()));
        }catch (Exception e){
            logger.error("BigDecimal 转String异常",e);
            return "0.00";
        }
    }


    /**
     * 只含数字和字母
     *
     * @param obj
     */
    public static boolean isAllLetterAndNum(Object obj) {
        if (isEmpty(obj)) {
            return false;
        }
        String regex = "^[a-z0-9A-Z]*$";
        return obj.toString().matches(regex);
    }

    /**
     * 是否是车牌号
     *
     * 1.常规车牌号：仅允许以汉字开头，后面可录入六个字符，由大写英文字母和阿拉伯数字组成。如：粤B12345；
     * 2.最后一个为汉字的车牌：允许以汉字开头，后面可录入六个字符，前五位字符，由大写英文字母和阿拉伯数字组成，而最后一个字符为汉字，汉字包括“挂”、“学”、“警”、“港”、“澳”。如：粤Z1234港。
     * 3.新军车牌：以两位为大写英文字母开头，后面以5位阿拉伯数字组成。如：BA12345。
     * 4.绿牌车：多一位数
     * @param obj
     * @return
     */
    public static boolean isCarPlate(Object obj) {
        if (isEmpty(obj)) {
            return false;
        }
        String regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[·]?[A-Z0-9]{4,5}[A-Z0-9挂学警港澳]{1}$";
        return obj.toString().matches(regex);
    }

    /**
     * 字符串判断是否存在，存在返回true，否则false
     * @param targetStr
     * @param sourceStr
     * @return
     */
    public static boolean contain(String targetStr, String sourceStr) {
        String[] sourceArray  = sourceStr.split(",");
        for (int i = 0; i < sourceArray.length; i++) {
            if (sourceArray[i].equals(targetStr)) {
                return true;
            }
        }
        return  false;
    }

    public static String ifnull(String s, String str){
        if(s == null) return str;
        return s;
    }

    public static boolean isJson(String content) {
        try {
            JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
//     logger.info(isCarPlate("京A·52DD11"));
        JSONObject json = JSON.parseObject("{}");
        String aa = json.getString("aa");
       logger.info(aa);
//       logger.info(objToBigDecimal(114.0548798656));
    }

    /**
     * 字符串去掉特殊符号
     * @param str
     * @return
     */
    public static String filterSymbols(String str) {
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].\\\\<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
