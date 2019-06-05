package com.ydc.commom.dingtalk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.dingtalk.demo.OApiException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;


public class HttpHelper {

    private static Logger logger = LogManager.getLogger(HttpHelper.class);

    public static JSONObject httpGet(String url) throws OApiException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpGet.setConfig(requestConfig);

        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {
                logger.info("获取AccessToken失败,code:" + response.getStatusLine().getStatusCode() + ";url:" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                JSONObject result = JSON.parseObject(resultStr);
                logger.info("获取AccessToken,code:" + response.getStatusLine().getStatusCode() + ";url:" + url + ";resultStr:" + resultStr);
                return result;
            }
        } catch (IOException e) {
            logger.error("request url=" + url + ", exception, msg=" + e.getMessage(), e);
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                logger.error("request url=" + url + ", exception, msg=" + e.getMessage(), e);
            }
        }
        return null;
    }


    public static JSONObject httpPost(String url, Object data) throws OApiException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");

        try {
            StringEntity requestEntity = new StringEntity(JSON.toJSONString(data), "utf-8");
            httpPost.setEntity(requestEntity);

            response = httpClient.execute(httpPost, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {
                logger.info("获取用户授权的持久授权码,url:" + url + ";code:" + response.getStatusLine().getStatusCode());
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");

                JSONObject result = JSON.parseObject(resultStr);
                logger.info("获取用户授权的持久授权码,url:" + url + ";resultStr:" + resultStr);
                return result;
            }
        } catch (IOException e) {
            logger.error("request url=" + url + ", exception, msg=" + e.getMessage(), e);
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                logger.error("request url=" + url + ", exception, msg=" + e.getMessage(), e);
            }
        }
        return null;
    }

    public static JSONObject uploadMedia(String url, File file) throws OApiException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);

        HttpEntity requestEntity = MultipartEntityBuilder.create().addPart("media",
                new FileBody(file, ContentType.APPLICATION_OCTET_STREAM, file.getName())).build();
        httpPost.setEntity(requestEntity);

        try {
            response = httpClient.execute(httpPost, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {
                logger.info("获取用户授权的持久授权码,code:" + response.getStatusLine().getStatusCode() + ";url:" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                JSONObject result = JSON.parseObject(resultStr);
                logger.info("获取用户授权的持久授权码,url:" + url + ";resultStr:" + resultStr);
                return result;
            }
        } catch (IOException e) {
            logger.error("request url=" + url + ", exception, msg=" + e.getMessage(), e);
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                logger.error("request url=" + url + ", exception, msg=" + e.getMessage(), e);
            }
        }
        return null;
    }
}
