package com.ydc.model.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


    public static final String DATATIMEF_1 = "yyyy-MM-dd HH:mm";
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
     *
     * @return
     */
    public static Date getDateByDatatimefStr(String dateStr){
        return parseDate(dateStr, DATATIMEF_STR);

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


    public static String getdatatimef_1(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATATIMEF_1);
            return sdf.format(date);
        }catch (Exception e){
            return  null;
        }
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

    public static void main(String[] args) throws ParseException {
//    	System.out.println(getTimeOut("2017-11-28","2017-12-1"));

//       logger.info(getLastMonday());
//       logger.info(getApartMinute(DateUtil.parseDateAndTime("2018-09-01 10:10:10"),DateUtil.parseDateAndTime("2018-09-01 10:19:15")));

//        String startTime = DateUtil.formatDate(new Date(),DateUtil.DATATIMEF_STR);
//       logger.info(startTime);
//        String endTime = DateUtil.addDateMinut(DateUtil.parseDateAndTime(startTime),1);
//       logger.info(endTime);

//        Date date = new Date();
//        String startTime = DateUtil.formatDate(DateUtil.getAddDate(date,3,-1))+" 00:00:00";
//       logger.info(startTime);
//        String endTime = DateUtil.formatDate(date)+" 23:59:59";
//       logger.info(endTime);
        String strDate = "1541952000";
       logger.info(timeStamp2Date(strDate,DATATIMEF_STR));
    }
}
