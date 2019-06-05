package com.ydc.commom.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.enums.common.CommonEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.view.vo.cgj.rental.RentalOrderListVO;
import com.ydc.model.cgj.common.Holiday;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日期工具类
 *
 * @author gongjin
 * @create 2017-05-08 9:28
 **/
public class DateUtil {

    private static Logger logger = LogManager.getLogger(DateUtil.class);

    /**
     * 格式化对象
     */
    public final static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    public final  static long DATE_LONG_30=1000*60*30;

    /**
     * 默认显示日期的格式
     */
    public static final String DATAFORMAT_STR = "yyyy-MM-dd";
    /**
     * 默认显示日期的格式
     */
    public static final String YYYY_MM_DATAFORMAT_STR = "yyyy-MM";
    /**
     * 默认显示日期时间的格式
     */
    public static final String DATATIMEF_STR = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认显示日期时间的格式
     */
    public static final String WECHATMSGDATEFORMAT = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * 默认显示简体中文日期的格式
     */
    public static final String ZHCN_DATAFORMAT_STR = "yyyy年MM月dd日";
    /**
     * 默认显示简体中文日期的格式
     */
    public static final String ZHCN_DATAFORMAT_MD = "MM月dd日";
    /**
     * 默认显示简体中文日期时间的格式
     */
    public static final String ZHCN_DATATIMEF_STR = "yyyy年MM月dd日HH时mm分ss秒";
    /**
     * 合同号日期格式YYMMDD
     */
    public static final String CONTRACT_YEAR_MONTH_DAY = "yyMMdd";

    /**
     * 数据库日期格式化，年-月-日
     */
    public static final String DATABASE_YEAR_MONTH_DAY = "%Y-%m-%d";

    /**
     * 数据库日期格式化，年-月
     */
    public static final String DATABASE_YEAR_MONTH = "%Y-%m";

    /**
     * 日期格式yyyyMM
     */
    public static final String NUM_YEAR_MONTH = "yyyyMM";

    /**
     * 斜杠日期
     */
    public static final String SPRIT_DATAFORMAT_STR = "yyyy/MM/dd";

    /**
     * 日期格式yyyyMMddHHmmss
     */
    public static final String NUM_YEAR_MONTH_TIME = "yyyyMMddHHmmss";

    /**
     * 日期格式yyyy-MM-dd'T'HH:mm:ss
     */
    public static final String NUM_YEAR_MONTH_T_TIME = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * 日期格式yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    public static final String NUM_YEAR_MONTH_T_TIME_MS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    /**
     * 默认显示简体中文日期的格式
     */
    public static final String ZHCN_DATAFORMAT_STR_LOGOGRAM = "yyyy年M月d日";


    /**
     * 日期格式yyMMddHHmmss
     */
    public static final String NUM_TWO_YEAR_MONTH_TIME = "yyMMddHHmmss";


    /**
     * /**
     * 格式化日期（字符串）
     *
     * @param dateStr 字符型日期
     * @param format  格式
     * @return 返回日期
     */
    public static Date parseDate(String dateStr, String format) {
        if (null == dateStr || ("").equals(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            logger.error("格式化日期异常",e);
            return null;
        }
    }

    /**
     * 格式化日期（日期）
     *
     * @param date   字符型日期
     * @param format 格式
     * @return 返回日期
     */
    public static Date parseDate(Date date, String format) {
        return parseDate(format(date, format), format);
    }

    /**
     * 格式化输出日期
     *
     * @param date   日期
     * @param format 格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        try {
            if (date == null) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            logger.error("格式化输出日期",e);
            return null;
        }
    }

    /**
     * 判断时间一是否小于时间二（该方法参数有先后顺序）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean date1LessThanOrEqualToDate2(Date date1, Date date2) {
        try {
            date1 = FORMATTER.parse(FORMATTER.format(date1));
            date2 = FORMATTER.parse(FORMATTER.format(date2));
            if (date1.getTime() <= date2.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            logger.error("日期对比异常",e);
        }
        return false;
    }

    /**
     * 判断两个时间是否为同一天（年月日的比较）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean sameDate(Date date1, Date date2) {
        try {
            date1 = FORMATTER.parse(FORMATTER.format(date1));
            date2 = FORMATTER.parse(FORMATTER.format(date2));
            if (date1.getTime() == date2.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            logger.error("日期对比异常",e);
        }
        return false;
    }

    /**
     * 日期加或减
     *
     * @param date
     * @param field  1、年；2、月；5、天
     * @param amount
     * @return Date
     */
    public static Date dateAdd(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 日期加或减
     *
     * @param date
     * @param field  1、年；2、月;5、天
     * @param amount
     * @return String
     */
    public static String dateToStringAdd(String date, int field, int amount) {
        Date date2 = parseDate(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date2);
        c.add(field, amount);
        return formatDate(c.getTime());
    }

    /**
     * 格式化日期，格式yyyy-MM-dd
     *
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * parse时间，格式yyyy-MM-dd
     *
     * @param dateStr
     * @return java.util.Date
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * parse时间，格式HH:mm:ss
     *
     * @param dateStr
     * @return java.util.Date
     */
    public static Date parseTime(String dateStr) {
        return parseDate(dateStr, "HH:mm:ss");
    }

    /**
     * parse时间，格式yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @return java.util.Date
     */
    public static Date parseDateAndTime(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间，格式HH:mm:ss
     *
     * @param date
     * @return String
     */
    public static String formatTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 格式化时间，格式yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return String
     */
    public static String formatDateAndTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间，格式yyyy-MM-dd HH:mm
     *
     * @param date
     * @return String
     */
    public static String formatDateWithoutS(Date date) {
        return format(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 返回年份
     *
     * @param date 日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 返回月份
     *
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日份
     *
     * @param date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 返回星期
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        return day == 1 ? 7 : (day - 1);
    }

    /**
     * 返回字符型日期
     *
     * @param date 日期
     * @return 返回字符型日期
     */
    public static String getStrDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * 返回字符型时间
     *
     * @param date 日期
     * @return 返回字符型时间
     */
    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 取得指定月份的第一天
     * 2017-06  yyyy-MM
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthBegin(String strdate, String format) {
        Date date = parseDate(strdate, format);
        return format(date, "yyyy-MM") + "-01";
    }

    /**
     * 取得指定月份的最后一天
     * 2017-06  yyyy-MM
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthEnd(String strdate, String format) {
        Date date = parseDate(strdate, format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }

    /**
     * 往指定日期中的年月日相加
     *
     * @param date   Date 日期
     * @param field  int 需要加的字段1 年; 2 月;3 日
     * @param amount int 加多少
     * @return String
     */
    public static String addDate(Date date, int field, int amount) {
        int temp = 0;
        if (field == 1) {
            temp = Calendar.YEAR;
        }
        if (field == 2) {
            temp = Calendar.MONTH;
        }
        if (field == 3) {
            temp = Calendar.DATE;
        }
        String Time = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(temp, amount);
            Time = sdf.format(cal.getTime());
            return Time;
        } catch (Exception e) {
            logger.error("往指定日期中的年月日相加异常",e);
            return null;
        }
    }

    /**
     * 往指定日期中的年月日相加
     *
     * @param date   Date 日期
     * @param field  int 需要加的字段 1 年; 2 月;3 日
     * @param amount int 加多少
     * @return Date
     */
    public static Date getAddDate(Date date, int field, int amount) {
        int temp = 0;
        if (field == 1) {
            temp = Calendar.YEAR;
        }
        if (field == 2) {
            temp = Calendar.MONTH;
        }
        if (field == 3) {
            temp = Calendar.DATE;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(temp, amount);
        return cal.getTime();
    }

    /**
     * 两个日期相隔天数2014-03-26、2014-03-26=0；2014-03-25、2014-03-26=1
     *
     * @param startDate 格式yyyy-MM-dd
     * @param endDate   格式yyyy-MM-dd
     * @return
     */
    public static long diffDays(Date startDate, Date endDate) {
        startDate = DateUtil.getDate(startDate);
        endDate = DateUtil.getDate(endDate);
        Calendar startCalendar1 = Calendar.getInstance(), endCalendar2 = Calendar
                .getInstance();
        startCalendar1.setTime(startDate);
        endCalendar2.setTime(endDate);
        long startTime1 = startCalendar1.getTimeInMillis();
        long endTime2 = endCalendar2.getTimeInMillis();
        long result = (endTime2 - startTime1) / (1000 * 60 * 60 * 24);
        return result;
    }

    public static long diffTimeInMillis(Date startDate, Date endDate){
        if (endDate == null){
            endDate = new Date();
        }
        if (startDate == null){
            return 0L;
        }
        return endDate.getTime() - startDate.getTime();
    }

    public static long diffTimeInMinutes(Date startDate, Date endDate){
        long diffTime = diffTimeInMillis(startDate, endDate);
        return (diffTime / (60 * 1000)) + (diffTime % (60 * 1000) == 0 ? 0 : 1);
    }

    public static String diffTimeInMinutesAndHours(Date startDate, Date endDate){
        long diffMinutes = diffTimeInMinutes(startDate, endDate);
        return (diffMinutes / 60) + "H" + (diffMinutes % 60) + "分";
    }

    /**
     * 返回字符型日期
     *
     * @param date 日期
     * @return 返回字符型日期
     */
    public static Date getDate(Date date) {
        return DateUtil.parseDate(format(date, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    /**
     * 获得当前时间（Timestamp类型）
     */
    public static Timestamp getNewTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获取当前系统时间
     *
     * @return Date
     */
    public static Date getNewDate() {
        return new Date();
    }

    /**
     * 计算未来多少天之后的日期
     *
     * @param days 累加的天数
     * @return 累加后的日期
     * @throws ParseException
     */
    public static String getAddDaysTime(String startTime, int days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        if (StringUtil.isEmpty(startTime)) {
            d = new Date();
        } else {
            try {
                d = format.parse(startTime);
            } catch (ParseException e) {
                d = new Date();
            }
        }
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, days);// days为增加的天数，可以改变的
        d = ca.getTime();
        String backTime = format.format(d);
        return backTime;
    }

    /**
     * 获取2个日前相差的天数
     * time2 - time1 = 相差天数
     *
     * @param time1 时间1
     * @param time2 时间2
     * @return
     */
    public static long getTimeOut(String time1, String time2) {
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
        long t = 0;
        try {
            Date start = smdf.parse(time1);
            Date end = smdf.parse(time2);
            t = (end.getTime() - start.getTime()) / (3600 * 24 * 1000);
        } catch (ParseException e) {
            logger.error("获取2个日前相差的天数异常",e);
        }
        return t;
    }

    /**
     * 将一个"yyyy-MM-dd HH:mm:ss"字符串，转换成"yyyy年MM月dd日HH时mm分ss秒"的字符串
     *
     * @param dateStr
     * @return
     */
    public static String getZhCNDateTime(String dateStr) {
        Date d = getDate(dateStr);
        return dateToDateString(d, ZHCN_DATATIMEF_STR);
    }

    /**
     * 按照默认显示日期时间的格式"yyyy-MM-dd HH:mm:ss"，转化dateTimeStr为Date类型
     * dateTimeStr必须是"yyyy-MM-dd HH:mm:ss"的形式
     *
     * @param dateTimeStr
     * @return
     */
    public static Date getDate(String dateTimeStr) {
        return getDate(dateTimeStr, DATATIMEF_STR);
    }

    /**
     * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
     *
     * @param dateTimeStr
     * @param formatStr
     * @return
     */
    public static Date getDate(String dateTimeStr, String formatStr) {
        if (dateTimeStr == null || dateTimeStr.equals("")) {
            return null;
        }
        DateFormat sdf = getDateFormat(formatStr);
        Date d = null;
        try {
            d = sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            throw new ServiceRuntimeException(e);
        }
        return d;

    }

    public static DateFormat getDateFormat(String formatStr) {
        if (formatStr.equalsIgnoreCase(DATAFORMAT_STR)) {
            return new SimpleDateFormat(formatStr);
        } else if (formatStr.equalsIgnoreCase(DATATIMEF_STR)) {
            return new SimpleDateFormat(formatStr);
        } else if (formatStr.equalsIgnoreCase(ZHCN_DATAFORMAT_STR)) {
            return new SimpleDateFormat(formatStr);
        } else if (formatStr.equalsIgnoreCase(ZHCN_DATATIMEF_STR)) {
            return new SimpleDateFormat(formatStr);
        } else {
            return new SimpleDateFormat(formatStr);
        }
    }

    /**
     * 将Date转换成formatStr格式的字符串
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String dateToDateString(Date date, String formatStr) {
        DateFormat df = getDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 格式化timestamp为中文日期格式
     *
     * @param timestamp
     * @return
     */
    public static String formatTimeStampStringToClient(Timestamp timestamp) {
        Date date;
        if (timestamp == null) {
            return "--";
        } else {
            date = new Date(timestamp.getTime());
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            return df.format(date);
        } catch (Exception e) {
            logger.error("格式化timestamp为中文日期格式异常",e);
            return null;
        }
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date getCurrentMonthFirstDay() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static Date getCurrentMonthLastDay() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static String getCurrentMonthFirstDayStr() {
        return FORMATTER.format(getCurrentMonthFirstDay());
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static String getCurrentMonthLastDayStr() {
        return FORMATTER.format(getCurrentMonthLastDay());
    }

    /**
     * 格式化timestamp为中文日期格式
     *
     * @param timestamp
     * @return
     */
    public static String formatTimeStampToYYYYMMDDHHMMSS(Timestamp timestamp) {
        Date date;
        if (timestamp == null) {
            return null;
        } else {
            date = new Date(timestamp.getTime());
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(date);
        } catch (Exception ex) {
            logger.error("formatTimeStampToYYYYMMDDHHMMSS exception", ex);
            return null;
        }
    }

    /**
     * 获取当前日期yyyy-MM-dd的形式
     *
     * @return
     */
    public static String getCurDateStr() {
        return dateToDateString(Calendar.getInstance().getTime(), DATAFORMAT_STR);
    }

    /**
     * 根据指定日期获取月份的第一天
     *
     * @param strDate
     * @return
     */
    public static String getMonthFirstDayByAssignDate(String strDate) {
        try {
            if (StringUtil.isEmpty(strDate)) {
                return null;
            }
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat mf = new SimpleDateFormat("yyyy-MM");
            Date date = mf.parse(strDate);
            calendar.setTime(date);
            calendar.add(calendar.DATE, 0);//因为格式化时默认了DATE为本月第一天所以此处为0
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            logger.error("根据指定日期获取月份的第一天异常",e);
        }
        return null;
    }

    /**
     * 根据指定日期获取月份的最后一天
     *
     * @param strDate
     * @return
     */
    public static String getMonthLastDayByAssignDate(String strDate) {
        try {
            if (StringUtil.isEmpty(strDate)) {
                return null;
            }
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat mf = new SimpleDateFormat("yyyy-MM");
            Date date = mf.parse(strDate);
            calendar.setTime(date);
            calendar.roll(calendar.DATE, -1);//api解释roll()：向指定日历字段添加指定（有符号的）时间量，不更改更大的字段
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            logger.error("根据指定日期获取月份的最后一天异常",e);
        }
        return null;
    }

    /**
     * 获得当前周- 周日  的日期
     *
     * @author:gongjin
     * @param:
     * @date: 2017年10月18日 下午3:25:23
     * @return:String
     */
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        String preMonday = formatDate(monday);
        return preMonday;
    }

    /**
     * 当前周- 周一的日期
     *
     * @author:gongjin
     * @param:
     * @date: 2017年10月18日 下午3:25:30
     * @return:String
     */
    public static String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        String preMonday = formatDate(monday);
        return preMonday;
    }

    /**
     * 获得本周一与当前日期相差的天数
     *
     * @author:gongjin
     * @param:
     * @date: 2017年10月18日 下午3:25:36
     * @return:int
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 获取指定月份的第一个星期一
     *
     * @author:gongjin
     * @param:
     * @date: 2017年10月18日 下午4:08:31
     * @return:String
     */
    public static String getWhenMonthFirstMonday(String strDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDate(strDate));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int i = 1;
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            cal.set(Calendar.DAY_OF_MONTH, i++);
        }
        Date firstMonday = cal.getTime();
        return formatDate(firstMonday);
    }

    /**
     * 返回两个日期之间的所有值
     *
     * @author:gongjin
     * @param:
     * @date: 2018年1月2日 下午8:48:20
     * @return:List<String>
     */
    public static List<String> getDateLists(Date date, int day) throws Exception {
        Date end = DateUtil.parseDate(DateUtil.dateAdd(date, 5, -1), DateUtil.DATAFORMAT_STR);
        Date start = DateUtil.parseDate(DateUtil.dateAdd(date, 5, day), DateUtil.DATAFORMAT_STR);
        List<Date> lists = dateSplit(start, end);
        List<String> strList = new ArrayList<>();
        if (!lists.isEmpty()) {
            for (Date d : lists) {
                strList.add(DateUtil.formatDate(d));
            }
        }
        return strList;
    }

    /**
     * 返回两个日期之间的值
     *
     * @author:gongjin
     * @param:
     * @date: 2018年1月2日 下午8:44:22
     * @return:List<Date>
     */
    public static List<Date> getDateLists(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) return null;
        Date date1 = null;
        Date date2 = null;
        if (startDate.before(endDate)){
            date1 = endDate;
            date2 = startDate;
        }else{
            date1 = startDate;
            date2 = endDate;
        }
        Long spi = date1.getTime() - date2.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数
        //若【还车时间-出车时间】%24>4h，则用车时长=【还车时间-出车时间】/24+1；
        if ((spi % (24 * 60 * 60 * 1000)) > 4 * 60 * 60 * 1000 ){
            step++;
        }
        System.out.println(step);
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(date2); //从开始日期开始取值
        //此处修改为 向dateList添加step-1次 原为for(int i = 1; i <= step; i++)
        for (int i = 1; i < step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime()
                    + (24 * 60 * 60 * 1000)));// 比上一天加一
        }
        return dateList;
    }

    /**
     * 返回两个日期之间的值
     *
     * @author:gongjin
     * @param:
     * @date: 2018年1月2日 下午8:44:22
     * @return:List<Date>
     */
    private static List<Date> dateSplit(Date startDate, Date endDate)
            throws Exception {
        if (!startDate.before(endDate))
            throw new Exception("开始时间应该在结束时间之后");
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(endDate);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime()
                    - (24 * 60 * 60 * 1000)));// 比上一天减一
        }
        return dateList;
    }

    /**
     * 获取两个日期相差几个月
     *
     * @param start
     * @param end
     * @return
     * @author 石冬冬-Heil Hilter(dd.shi02@zuche.com)
     * @date 2016-11-30 下午7:57:32
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 日期相隔的分钟
     * @param oleDate
     * @param nowDate
     * @return
     */
    public static Long getApartMinute(Date oleDate,Date nowDate){
        long i =  nowDate.getTime() - oleDate.getTime();//得到的毫秒数
        long result = i / (1000 * 60);//分
        return result;
    }



    public enum DateCode {

        A("A", "1"),
        B("B", "2"),
        C("C", "3"),
        D("D", "4"),
        E("E", "5"),
        F("F", "6"),
        G("G", "7"),
        H("H", "8"),
        I("I", "9"),
        J("J", "10"),
        K("K", "11"),
        L("L", "12");

        private String key;
        private String value;

        private DateCode(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static DateCode getEnumByValue(String value) {
            for (DateCode dc : DateCode.values()) {
                if (dc.value.equals(value))
                    return dc;
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * 获取上个月一号的日期
     *
     * @return
     */
    public static String getLastMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getNewDate());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return FORMATTER.format(calendar.getTime());
    }

    /**
     * 获取上周周一的日期
     *
     * @return
     */
    public static String getLastMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus - 7);
        Date monday = currentDate.getTime();
        String preMonday = formatDate(monday);
        return preMonday;
    }


    public static int getYear(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        int cutMonth = 0;
        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            cutMonth = year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            cutMonth = year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            cutMonth = year * 12 + month;
        } else {
            cutMonth = (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
        return cutMonth / 12 + 1;
    }

    /**
     * 给时间加上几个小时
     * @param date 当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @param hour 需要加的时间
     * @return
     */
    public static String addDateMinut(Date date, int hour){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        return format.format(date);

    }
    /**
     * 获取当前时间 年 月 日
     *
     * @return
     */
    public static String getCurrentDate() {
        try {
            Date d = new Date();
            return formatDate(d, ZHCN_DATAFORMAT_STR_LOGOGRAM);
        } catch (Exception e) {
            return "";
        }

    }

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

    public static DateFormat getThreadLocalDateFormat(final String format) {
        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(format);
            threadLocal.set(df);
        }
        return df;
    }

    public static String formatDate(Date date, String fromat) throws ParseException {
        return getThreadLocalDateFormat(fromat).format(date);
    }

  /*  public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }*/

    public static double getCurrentDateDouble() {
        Double dd = new Double("0");
        try {
            Date d = new Date();
            String date = formatDate(d, NUM_TWO_YEAR_MONTH_TIME);

        } catch (Exception e) {

        }
        return dd;
    }

    /**
     * 获取明天零点的日期对象
     * @return
     */
    public static Date getTomorrow(){
        Date date = dateAdd(new Date(), Calendar.DAY_OF_MONTH, 1);
        return parseDate(formatDate(date) + " 00:00:00", DATATIMEF_STR);
    }


    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2StrDate(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    /**
     * 时间戳转换成日期
     * @param seconds
     * @param format
     * @return
     */
    public static Date timeStamp2Date(String seconds,String format){
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return null;
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return parseDate(sdf.format(new Date(Long.valueOf(seconds+"000"))),format);
    }

    /**
     * 时间戳转换成日期
     * @param seconds
     * @return
     */
    public static Date timeStamp2Date(String seconds){
        return timeStamp2Date(seconds,DATATIMEF_STR);
    }


    /**
     * 获取精度为秒的时间戳
     * @param date
     * @return
     */
    public static long getSecondTimeStamp(Date date){
        if (date == null){
            return 0L;
        }
        return date.getTime() / 1000L;
    }

    /**
     * 比较时间差 第一个减去第二个 差值与 diffValue比较大小
     * @param first
     * @param second
     * @param diffValue
     * @return
     */
    public static int compareSubDate(Date first,Date second ,long diffValue){
        long temp;
        if ( null==first ){
            return -1;
        }
        if (null == second){
            temp=first.getTime()-diffValue;
        }else {
            temp=first.getTime()-second.getTime()-diffValue;
        }
        return (temp < 0) ? -1 : ((temp == 0) ? 0 : 1);
    }

    public static void main(String[] args) throws ParseException {
        Result<List<Holiday>> result = DateUtil.getCurrentYearHoliday();
        if(result.getCode() != 200) System.out.println(result.getMessage());
        List<Holiday> holidayList = result.getData();
        holidayList.forEach(o -> System.out.println(DateUtil.formatDate(o.getHolidayDate()) + "\t"
                + o.getHolidayType() + "\t" + o.getStatus() + "\t" + DateUtil.formatDateAndTime(o.getCreateTime()) + "\t" + DateUtil.formatDateAndTime(o.getUpdateTime())));
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.getActualMaximum(Calendar.MONTH));
        System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        // logger.info(DateUtil.compareSubDate(new Date(),new Date(),DateUtil.DATE_LONG_5));
    }


    /**
     * 获取两个时间差
     * @param startTime yyyy-MM-dd HH:mm:ss
     * @param endTime yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String longTimeToDay(String startTime,String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //转换成date类型
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);
        //获取毫秒数
        Long startLong = start.getTime();
        Long endLong = end.getTime();
        //计算时间差,单位毫秒
        Long ms = endLong-startLong;

        //时间差转换为 \天\时\分\秒
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;


        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        if(day < 0 || hour < 0 || minute < 0 || second < 0){
            return String.valueOf(0);
        }
        double totalDay = hour > 0 || minute > 0 || second > 0 ? (hour >= 4 ? Double.valueOf(++day) :  Double.valueOf(day)+0.5) : Double.valueOf(day);
        return String.valueOf(totalDay);
    }

    /**
     * 给日期格式加上最小时分秒 00:00:00
     * @param date
     * @return
     */
    public static String jointMinSuffix(String date){
        if(date == null || "".equals(date)) return null;
        return date + " 00:00:00";
    }

    /**
     * 给日期格式加上最大时分秒 23:59:59
     * @param date
     * @return
     */
    public static String jointMaxSuffix(String date){
        if(date == null || "".equals(date)) return null;
        return date + " 23:59:59";
    }

    public static Result<List<Holiday>> getCurrentYearHoliday(){
        List<Holiday> holidayList = new ArrayList<>();
        String result = null;
        try {
            result = HttpUtil.get("http://timor.tech/api/holiday/year/2019");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取本年度节假日期异常",e);
            return Result.failure("获取本年度节假日期异常");
        }
        if(StringUtil.isEmpty(result) || !StringUtil.isJson(result)) return Result.failure("本年度节假日期获取失败");

        TreeMap<String, Object> jsonMap = JsonUtil.jsonToTreeMap(result);System.out.println(jsonMap.get("holiday").toString());
        if(!( jsonMap.containsKey("code") && "0".equals(jsonMap.get("code").toString()) )){
            return Result.failure(jsonMap.containsKey("message") ? jsonMap.get("message").toString():"本年度节假日期获取失败");
        }

        Date sysDate = new Date();
        TreeMap<String, Object> holidayMapList = JsonUtil.jsonToTreeMap(jsonMap.get("holiday").toString());
        holidayMapList.values().forEach(obj -> {
            Map<String, Object> holidayMap = (Map<String, Object>)obj;
            Holiday holiday = new Holiday();
            holiday.setHolidayDate(DateUtil.parseDate(holidayMap.get("date").toString()));
            holiday.setHolidayType(((boolean)holidayMap.get("holiday")) ? 1:2);
            holiday.setStatus(CommonEnum.DeleteStatusEnum.STATUS_1.getCode()+"");
            holiday.setCreateTime(sysDate);
            holiday.setUpdateTime(sysDate);
            holidayList.add(holiday);
        });
        return Result.success(holidayList);
    }

    /**
     * 判断是否是周末
     * @param date
     * @return
     */
    public static boolean hasWeekend(Date date){
        if(date == null) return false;

        if(getWeek(date) > 5) return true;

        return false;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }


    /**
     * 获取两个时间相差的小时数
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int getHoursDiff(Date fromDate,Date toDate) {
        int hours = 0;
        try {
            SimpleDateFormat simpleFormat = new SimpleDateFormat(DATATIMEF_STR);
            String fromDateStr = simpleFormat.format(fromDate);
            String toDateStr = simpleFormat.format(toDate);
            long fromTime = simpleFormat.parse(fromDateStr).getTime();
            long toTime = simpleFormat.parse(toDateStr).getTime();
            hours = Math.abs((int) ((toTime - fromTime) / (1000 * 60 * 60)));
        } catch (ParseException e) {
            logger.error("获取小时差异常", e);
            return -1;
        }
        return hours;
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    /**
     * 获取当前时间 格式为 yyyyMMddHHmmss
     * **/
    public static String getTimeForyyyymmddhhmmss() {
        DateFormat df = new SimpleDateFormat(NUM_YEAR_MONTH_TIME);
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        return dateName;
    }
}
