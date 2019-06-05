package com.ydc.commom.util.site;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author
 * @create 2018-11-01 19:32
 **/
public class GetLocation {

    private static final Logger logger = LogManager.getLogger(GetLocation.class);


    public static void main(String[] args) {
        // lat 31.2990170   纬度
        //log 121.3466440    经度

        String add = position("深圳市银田路18号西乡体育中心");
        JSONObject jo = JSONObject.parseObject(add);

        add = getAdd("114.044426", "22.565685");
        JSONObject jsonObject = JSONObject.parseObject(add);
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("addrList"));
        Iterator<Object> iterator= jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject jObject= (JSONObject) iterator.next();
           logger.info(jObject.get("admName"));
           logger.info(jObject.get("name"));
        }
    }

    /**
     * 经度，纬度获取地理位置
     * @author:gongjin
     * @param:
     * @date: 2018年11月1日 下午7:30:34
     * @return:String
     */
    public static String getAdd(String log, String lat ){
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=100";
        final ExecutorService exec = Executors.newFixedThreadPool(1);
        Callable<String> call = new Callable<String>() {
            public String call() throws Exception {
                URL url = new URL(urlString);
                java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
                String line;
                String res = "";
                while ((line = in.readLine()) != null) {
                    res += line+"\n";
                }
                in.close();
                return res;
            }
        };
        Object obj = null;
        try {
            Future<String> future = exec.submit(call);
            obj = future.get(1000 * 5, TimeUnit.MILLISECONDS); //任务处理超时时间设为 5 秒
        } catch (TimeoutException ex) {
            logger.error("subject:{},e:{}","经度，纬度获取地理位置超时",ex);
        }  catch (Exception e) {
            logger.error("subject:{},e:{}","经度，纬度获取地理位置",e);
        }
        logger.info("subject:{},res:{}","经度，纬度获取地理位置",obj);
        // 关闭线程池
        exec.shutdown();
        return obj == null ? null : obj.toString();
    }

    /**
     * 地址获取经度，纬度
     * @author:gongjin
     * @param:
     * @date: 2018年11月1日 下午7:30:55
     * @return:String
     */
    public static String position(String city){
        String urlString = "http://gc.ditu.aliyun.com/geocoding?a="+city;
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            logger.error("subject:{},e:{}","地址获取经度，纬度",e);
        }
        logger.info("subject:{},res:{}","地址获取经度，纬度",res);
        return res;
    }
}
