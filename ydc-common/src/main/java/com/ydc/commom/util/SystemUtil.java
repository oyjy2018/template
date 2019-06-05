package com.ydc.commom.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

/**
 * @author gongjin
 * @create 2018-09-04 14:46
 **/
public class SystemUtil {

    private static Logger logger = LogManager.getLogger(SystemUtil.class);

    public static final String REDIS_DICTIONARY_KEY = "CGJ_REDIS_DICTIONARY_KEY";

    public static void getRequestQueryParameter(Map<String, Object> seachMap, HttpServletRequest request) {
        try {
            @SuppressWarnings("unchecked")
            Enumeration eum = request.getParameterNames();
            while (eum.hasMoreElements()) {
                String paraName = (String) eum.nextElement();
                String paraValue = request.getParameter(paraName);
                seachMap.put(paraName, paraValue);
            }
        } catch (Exception e) {
            logger.error("subject:{},e:{}","请求查询参数异常",e);
        }
    }

    /**
     * 查询行数处理
     * @param seachMap
     */
    public static void getRequestQueryLimit(Map<String, Object> seachMap){
        if(seachMap.get("page") != null){
            seachMap.put("page", (Integer.valueOf(seachMap.get("page").toString()) - 1)
                    * Integer.valueOf(seachMap.get("rows").toString())); // 分页开始点
        }else{
            seachMap.put("page", 0); // 分页开始点
        }
    }

    /**
     * 获取唯一字段
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }


    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request)
            throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        logger.info("subject:{},ip:{}","X-Forwarded-For",ip);

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                logger.info("subject:{},ip:{}","Proxy-Client-IP",ip);
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                logger.info("subject:{},ip:{}","WL-Proxy-Client-IP",ip);
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                logger.info("subject:{},ip:{}","HTTP_CLIENT_IP",ip);
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                logger.info("subject:{},ip:{}","HTTP_X_FORWARDED_FOR",ip);
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                //针对本机测试时无法获取IP的情况，对线上无影响
                if("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)){
                    ip = InetAddress.getLocalHost().getHostAddress();
                }
                logger.info("subject:{},ip:{}","getRemoteAddr",ip);
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}
