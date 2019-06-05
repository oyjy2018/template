package com.ydc.commom.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Map;
import java.util.concurrent.*;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 *
 * @author gongjin
 * @create 2017-05-08 12:07
 **/
public class IPUtil {

    /**淘宝api*/
    private static final String taobao_url = "http://ip.taobao.com/service/getIpInfo.php";

    private static final Logger logger = LogManager.getLogger(IPUtil.class);
    /**
     * 根据ip查询地址
     * @author gongjin
     * @param ip
     * @return
     */
    public static IPAddress getAddressByIP(String ip) {
//        final ExecutorService exec = Executors.newFixedThreadPool(1);
//        Callable<IPAddress> call = new Callable<IPAddress>() {
//            public IPAddress call() throws Exception {
//                IPAddress ipAddress = null;
//                HttpClient client = new HttpClient();
//                HttpMethod method = new GetMethod(taobao_url);
//                method.setQueryString("ip="+ip);
//                client.executeMethod(method);
//                if (method.getStatusCode() == HttpStatus.SC_OK) {
//                    String response = method.getResponseBodyAsString();
//                    if(!StringUtil.isEmpty(response)) {
//                        Map<String,Object> jo = JsonUtil.jsonToMap(response);
//                        if(jo != null && jo.containsKey("code") && "0".equals(jo.get("code").toString())) {
//                            Map<String,Object> retData = (Map<String,Object>)jo.get("data");
//                            ipAddress = new IPAddress();
//                            logger.info("获取请求地址:"+retData);
//                            ipAddress.setIp(retData.get("ip").toString());
//                            ipAddress.setCountry(retData.get("country").toString());//国家
//                            ipAddress.setProvince(retData.get("region").toString());//省份
//                            ipAddress.setCity(retData.get("city").toString());//城市
//                            ipAddress.setDistrict(retData.get("county").toString());//区
//                            ipAddress.setCarrier(retData.get("isp").toString());//运营商
//                        }
//                    }
//                }
//                return ipAddress;
//            }
//        };
//        IPAddress ipAddress = null;
//        try {
//            Future<IPAddress> future = exec.submit(call);
//            ipAddress = future.get(1000 * 5, TimeUnit.MILLISECONDS); //任务处理超时时间设为 5 秒
//        }catch (TimeoutException ex) {
//            logger.error("subject:{},e:{]","根据ip查询地址超时",ex);
//        }catch (Exception e){
//            logger.error("subject:{},e:{]","根据ip查询地址异常",e);
//        }
//        // 关闭线程池
//        exec.shutdown();
//        return ipAddress;
        IPAddress ipAddress = null;
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(taobao_url);
        try {
            logger.info("subject:{},ip:{}","根据ip查询地址",ip);
//			method.addRequestHeader("apikey", apikey);
            method.setQueryString("ip="+ip);
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                String response = method.getResponseBodyAsString();
                if(!StringUtil.isEmpty(response)) {
                    Map<String,Object> jo = JsonUtil.jsonToMap(response);
                    if(jo != null && jo.containsKey("code") && "0".equals(jo.get("code").toString())) {
                        Map<String,Object> retData = (Map<String,Object>)jo.get("data");
                        ipAddress = new IPAddress();
                        logger.info("subject:{},retData:{}","获取请求地址",retData);
                        ipAddress.setIp(retData.get("ip").toString());
                        ipAddress.setCountry(retData.get("country").toString());//国家
                        ipAddress.setProvince(retData.get("region").toString());//省份
                        ipAddress.setCity(retData.get("city").toString());//城市
                        ipAddress.setDistrict(retData.get("county").toString());//区
                        ipAddress.setCarrier(retData.get("isp").toString());//运营商
                    }
                }
                logger.info("subject:{},response:{}","根据ip查询地址",response);
            }
        } catch (Exception e) {
            logger.error("subject:{},e:{}","根据ip查询地址出现异常",e);
        }
        return ipAddress;
    }

    /**
     * 获取访问者IP
     *
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     *
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    public static void main(String [] args){
        IPAddress ipAddress = getAddressByIP("116.25.99.195");
        System.out.print(ipAddress.getAddress());
    }
}
