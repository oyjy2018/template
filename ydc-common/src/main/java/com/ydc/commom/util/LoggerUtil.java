package com.ydc.commom.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;


/**
 * @author gongjin
 * @Description: 日志工具(用于获取JSON格式的日志)
 * @create 2017-05-03 14:17
 **/
public class LoggerUtil {

    //final static Marker FQCN =MarkerManager.getMarker( LoggerUtil.class.getCanonicalName());

    final static String FQCN = LoggerUtil.class.getCanonicalName();

    private static Map<String, String> logMap = null;

    static {
        logMap = new HashMap<>();
        logMap.put("orgId", "机构ID");
    }

    /**
     * key分隔符
     */
    public static final String KEY_SEPARATOR = ",";

    /**
     * 默认key前缀
     */
    public static final String DEFAULT_KEY_PREFIX = "default_key_";

    /**
     * subject key
     */
    public static final String SUBJECT_KEY = "subject";

    /**
     * 返回json日志消息
     *
     * @param jsonObject json对象
     * @return
     */
    public static String getLoggerMessage(JSONObject jsonObject) {
        if (jsonObject == null)
            return "";
        return jsonObject.toString();
    }

    /**
     * 返回json日志消息
     *
     * @param keys   key列表, 多个用逗号“,”隔开
     * @param values value列表
     * @return
     */
    public static String getLoggerMessage(String keys, Object... values) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (StringUtil.isNotEmpty(keys)) {
            String[] keyArray = keys.replaceAll("\\s*", "").split(KEY_SEPARATOR);
            for (int i = 0; i < keyArray.length && i < values.length; ++i) {
                map.put(keyArray[i], values[i] == null ? "" : values[i]);
            }
        }
        return getLoggerMessage(JSONObject.fromObject(map));
    }

    /**
     * 返回json日志消息
     *
     * @param values value列表
     * @return
     */
    public static String getLoggerMessage(Object... values) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < values.length; ++i) {
            map.put(DEFAULT_KEY_PREFIX + "_" + i, values[i] == null ? "" : values[i]);
        }
        return getLoggerMessage(map);
    }

    /**
     * 返回json日志消息
     *
     * @param object object
     * @return
     */
    public static String getLoggerMessage(Object object) {
        return getLoggerMessage(JSONObject.fromObject(object));
    }

    /**
     * 返回json日志消息
     *
     * @param subject 主题
     * @return
     */
    public static String getLoggerMessage(String subject) {
        return getLoggerMessage(SUBJECT_KEY, subject);
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param jsonObject
     */
    public static void logInfoMessage(Logger logger, JSONObject jsonObject) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(jsonObject));
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param keys
     * @param values
     */
    public static void logInfoMessage(Logger logger, String keys, Object... values) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(keys, values));

    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param values
     */
    public static void logInfoMessage(Logger logger, Object... values) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(values));
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param object
     */
    public static void logInfoMessage(Logger logger, Object object) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(object), null);
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param subject
     */
    public static void logInfoMessage(Logger logger, String subject) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(subject), null);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param jsonObject
     */
    public static void logErrorMessage(Logger logger, Throwable t, JSONObject jsonObject) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(jsonObject), t);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param keys
     * @param values
     */
    public static void logErrorMessage(Logger logger, Throwable t, String keys, Object... values) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(keys, values), t);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param values
     */
    public static void logErrorMessage(Logger logger, Throwable t, Object... values) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(values), t);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param object
     */
    public static void logErrorMessage(Logger logger, Throwable t, Object object) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(object), t);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param subject
     */
    public static void logErrorMessage(Logger logger, Throwable t, String subject) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(subject), t);
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param jsonObject
     */
    public static void info(Logger logger, JSONObject jsonObject) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(jsonObject), null);
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param keys
     * @param values
     */
    public static void info(Logger logger, String keys, Object... values) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(keys, values), null);
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param values
     */
    public static void info(Logger logger, Object... values) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(values), null);
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param object
     */
    public static void info(Logger logger, Object object) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(object), null);
    }

    /**
     * 记录info日志
     *
     * @param logger
     * @param subject
     */
    public static void info(Logger logger, String subject) {
        logger.log(Level.INFO, FQCN, getLoggerMessage(subject), null);
    }

    /**
     * 记录warn日志
     *
     * @param logger
     * @param jsonObject
     */
    public static void warn(Logger logger, JSONObject jsonObject) {
        logger.log(Level.WARN, FQCN, getLoggerMessage(jsonObject), null);
    }

    /**
     * 记录warn日志
     *
     * @param logger
     * @param keys
     * @param values
     */
    public static void warn(Logger logger, String keys, Object... values) {
        logger.log(Level.WARN, FQCN, getLoggerMessage(keys, values), null);
    }

    /**
     * 记录warn日志
     *
     * @param logger
     * @param values
     */
    public static void warn(Logger logger, Object... values) {
        logger.log(Level.WARN, FQCN, getLoggerMessage(values), null);
    }

    /**
     * 记录warn日志
     *
     * @param logger
     * @param object
     */
    public static void warn(Logger logger, Object object) {
        logger.log(Level.WARN, FQCN, getLoggerMessage(object), null);
    }

    /**
     * 记录warn日志
     *
     * @param logger
     * @param subject
     */
    public static void warn(Logger logger, String subject) {
        logger.log(Level.WARN, FQCN, getLoggerMessage(subject), null);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param jsonObject
     */
    public static void error(Logger logger, Throwable t, JSONObject jsonObject) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(jsonObject), t);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param keys
     * @param values
     */
    public static void error(Logger logger, Throwable t, String keys, Object... values) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(keys, values), t);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param values
     */
    public static void error(Logger logger, Throwable t, Object... values) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(values), t);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param object
     */
    public static void error(Logger logger, Throwable t, Object object) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(object), t);
    }

    /**
     * 记录error日志
     *
     * @param logger
     * @param t
     * @param subject
     */
    public static void error(Logger logger, Throwable t, String subject) {
        logger.log(Level.ERROR, FQCN, getLoggerMessage(subject), t);
    }

    public static void debug(Logger logger,String message){
        if (logger.isDebugEnabled()){
            logger.debug(message);
        }
    }

    /**
     * 对象修改记录日志
     *
     * @param newObj 新对象
     * @param oldObj 旧对象
     * @return
     * @author huyueming
     * @date 2017年6月14日
     */
    @SuppressWarnings("rawtypes")
    public static StringBuffer contrastObjLog(Object newObj, Object oldObj) {
        StringBuffer sb = new StringBuffer();
        try {
            Class clazz = newObj.getClass();
            //获取对象的所有字段
            Field[] fields = newObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                //跳过序列化ID、最后修改时间、最后修改人
                if ("serialVersionUID".equals(field.getName())
                        || "updateTime".equals(field.getName())
                        || "updateBy".equals(field.getName())
//    					|| field.getName().indexOf("Id") > 0
                        || "id".equals(field.getName())
                        || "isConsignorCustomer".equals(field.getName())
                        || "createTime".equals(field.getName())
                        || "createBy".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(newObj);
                Object o2 = getMethod.invoke(oldObj);
                String s1 = o1 == null ? "" : o1.toString(); //避免空指针异常 
                String s2 = o2 == null ? "" : o2.toString(); //避免空指针异常
                if (!s1.equals(s2)) {
                    //如果是费率表，特殊处理，展示名直接取feeName
                    if (clazz.getName().indexOf("LoanFeeHistory") > 0) {
                        PropertyDescriptor p = new PropertyDescriptor("feeName", clazz);
                        Method pmethod = p.getReadMethod();
                        Object obj = pmethod.invoke(newObj);
                        String ss = obj == null ? "" : obj.toString();
                        sb.append("【").append(ss).append("】").append(":").append("由【").append(s2).append("】修改为【").append(s1).append("】").append("<br/>");
                    } else {
                        sb.append("【").append(logMap.get(field.getName()) == null ? field.getName() : logMap.get(field.getName())).append("】").append(":").append("由【").append(s2).append("】修改为【").append(s1).append("】").append("<br/>");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    /**
     * 截取日志尾部<br/>
     *
     * @author:gongjin
     * @param:
     * @date: 2017年6月16日 下午5:54:01
     * @return:StringBuffer
     */
    public static StringBuffer subLog(StringBuffer sb) {
        if (StringUtil.isNotEmpty(sb) && sb.toString().contains("<br/>")) {
            sb.delete(sb.toString().lastIndexOf("<br/>"), sb.length());
        }
        return sb;
    }
}
