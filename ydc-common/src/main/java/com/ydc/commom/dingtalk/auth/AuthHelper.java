package com.ydc.commom.dingtalk.auth;


import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.service.corp.CorpConnectionService;
import com.dingtalk.open.client.common.SdkInitException;
import com.dingtalk.open.client.common.ServiceException;
import com.dingtalk.open.client.common.ServiceNotExistException;
import com.ydc.commom.dingtalk.demo.OApiException;
import com.ydc.commom.dingtalk.utils.DingTalkFileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Timer;

/**
 * 获取AccessToken
 *
 * @author gongjin
 * @create 2017-05-18 19:39
 **/
public class AuthHelper {

    private static Logger logger = LogManager.getLogger(AuthHelper.class);
    // public static String jsapiTicket = null;
    // public static String accessToken = null;
    public static Timer timer = null;
    // 调整到1小时50分钟6600000
    public static final long cacheTime = 1000 * 60 * 55 * 2;
    public static long currentTime = 0 + cacheTime + 1;
    public static long lastTime = 0;
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /*
     * 在此方法中，为了避免频繁获取access_token，
     * 在距离上一次获取access_token时间在两个小时之内的情况，
     * 将直接从持久化存储中读取access_token
     */
    public static String getAccessToken(String corpId, String corpSecret) throws OApiException {
        long curTime = System.currentTimeMillis();
        JSONObject accessTokenValue = (JSONObject) DingTalkFileUtil.getValue("accesstoken", corpId);
        String accToken = "";
        JSONObject jsontemp = new JSONObject();
        if (accessTokenValue == null || curTime - accessTokenValue.getLong("begin_time") >= cacheTime) {
            try {
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                CorpConnectionService corpConnectionService = serviceFactory.getOpenService(CorpConnectionService.class);
                accToken = corpConnectionService.getCorpToken(corpId, corpSecret);
                // save accessToken
                JSONObject jsonAccess = new JSONObject();
                jsontemp.clear();
                jsontemp.put("access_token", accToken);
                jsontemp.put("begin_time", curTime);
                jsonAccess.put(corpId, jsontemp);
                DingTalkFileUtil.write2File(jsonAccess, "accesstoken");
            } catch (SdkInitException e) {
                logger.error("subject:{};e:{}","获取access_token和jsapi_ticket异常",e);
            } catch (ServiceException e) {
                logger.error("subject:{};e:{}","获取access_token和jsapi_ticket异常",e);
            } catch (ServiceNotExistException e) {
                logger.error("subject:{};e:{}","获取access_token和jsapi_ticket异常",e);
            }
        } else {
            return accessTokenValue.getString("access_token");
        }
        return accToken;
    }
}

