package com.ydc.commom.urlHttp;


import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


class RequestUtil {
    private Thread mThread;

    private static final Logger logger = LogManager.getLogger(RequestUtil.class);
    public RequestUtil() {
    }


    /**
     * 一般的get请求或post请求
     */
    RequestUtil(String method, String url, Map<String, Object> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        switch (method) {
            case "GET":
                urlHttpGet(url, paramsMap, headerMap, callBack);
                break;
            case "POST":
                urlHttpPost(url, paramsMap, null, headerMap, callBack);
                break;
        }
    }

    public static String handleHttp(String method, String url, Map<String, Object> paramsMap, final String jsonStr, Map<String, String> headerMap) throws Exception {
        switch (method) {
            case "GET":
                return doUrlHttpGet(url, paramsMap, headerMap);
            case "POST":
                return doUrlHttpPost(url, paramsMap, jsonStr, headerMap);
        }
        return null;

    }

    /**
     * post请求，传递json格式数据。
     */
    RequestUtil(String url, String jsonStr, Map<String, String> headerMap, CallBackUtil callBack) {
        urlHttpPost(url, null, jsonStr, headerMap, callBack);
    }

    /**
     * 上传文件
     */
    RequestUtil(String url, File file, List<File> fileList, Map<String, File> fileMap, String fileKey, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        urlHttpUploadFile(url, file, fileList, fileMap, fileKey, fileType, paramsMap, headerMap, callBack);
    }

    /**
     * get请求
     */
    private void urlHttpGet(final String url, final Map<String, Object> paramsMap, final Map<String, String> headerMap, final CallBackUtil callBack) {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                RealResponse response = new RealRequest().getData(getUrl(url, paramsMap), headerMap);
                if (response.code == HttpURLConnection.HTTP_OK) {
                    callBack.onSuccess(response);
                } else {
                    callBack.onError(response);
                }
            }

        });
    }

    /**
     * get请求
     */
    private static String doUrlHttpGet(final String url, final Map<String, Object> paramsMap, final Map<String, String> headerMap) throws Exception {
        logger.info("subject:{},url:{},paramsMap:{},headerMap:{}",
                "Get请求",url,JsonUtil.gsonStr(paramsMap),JsonUtil.gsonStr(headerMap));
        RealResponse response = new RealRequest().getData(getUrl(url, paramsMap), headerMap);
        return httpReturnHandle(response);
    }

    /**
     * Post请求
     */
    private static String doUrlHttpPost(final String url, final Map<String, Object> paramsMap, final String jsonStr, final Map<String, String> headerMap) throws Exception {
        logger.info("subject:{},url:{},paramsMap:{},jsonStr:{},headerMap:{}",
                "Post请求",url,JsonUtil.gsonStr(paramsMap),jsonStr,JsonUtil.gsonStr(headerMap));
        RealResponse response = new RealRequest().postData(url, getPostBody(paramsMap, jsonStr), getPostBodyType(paramsMap, jsonStr), headerMap);
        return httpReturnHandle(response);
    }

    private static String httpReturnHandle(RealResponse response) throws Exception {
        if (response.code == HttpURLConnection.HTTP_OK) {
            try (InputStreamReader isr = new InputStreamReader(response.inputStream);
                 BufferedReader reader = new BufferedReader(isr)
            ) {
                StringBuilder buffer = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                return buffer.toString();
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new ServiceRuntimeException(response.exception);
        }
    }

    /**
     * post请求
     */
    private void urlHttpPost(final String url, final Map<String, Object> paramsMap, final String jsonStr, final Map<String, String> headerMap, final CallBackUtil callBack) {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                RealResponse response = new RealRequest().postData(url, getPostBody(paramsMap, jsonStr), getPostBodyType(paramsMap, jsonStr), headerMap);
                if (response.code == HttpURLConnection.HTTP_OK) {
                    callBack.onSuccess(response);
                } else {
                    callBack.onError(response);
                }

            }

        });

    }

    /**
     * 上传文件
     */
    private void urlHttpUploadFile(final String url, final File file, final List<File> fileList, final Map<String, File> fileMap, final String fileKey, final String fileType, final Map<String, String> paramsMap, final Map<String, String> headerMap, final CallBackUtil callBack) {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                RealResponse response = null;
                response = new RealRequest().uploadFile(url, file, fileList, fileMap, fileKey, fileType, paramsMap, headerMap, callBack);
                if (response.code == HttpURLConnection.HTTP_OK) {
                    callBack.onSuccess(response);
                } else {
                    callBack.onError(response);
                }
            }

        });
    }


    /**
     * get请求，将键值对凭接到url上
     */
    private static String getUrl(String path, Map<String, Object> paramsMap) {
        if (paramsMap != null) {
            path = path + "?";
            for (String key : paramsMap.keySet()) {
                path = path + key + "=" + paramsMap.get(key) + "&";
            }
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    /**
     * 得到post请求的body
     */
    private static String getPostBody(Map<String, Object> params, String jsonStr) {//throws UnsupportedEncodingException {
        if (params != null) {
            return getPostBodyFormParameMap(params);
        } else if (StringUtil.isNotEmpty(jsonStr)) {
            return jsonStr;
        }
        return null;
    }


    /**
     * 根据键值对参数得到body
     */
    private static String getPostBodyFormParameMap(Map<String, Object> params) {//throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue()==null?"":entry.getValue().toString(), "UTF-8"));
            }
            return result.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }

    /**
     * 得到bodyType
     */
    private static String getPostBodyType(Map<String, Object> paramsMap, String jsonStr) {
        if (paramsMap != null) {
            //return "text/plain";
            return null;
        } else if (StringUtil.isNotEmpty(jsonStr)) {
            return "application/json;charset=utf-8";
        }
        return null;
    }


    /**
     * 开启子线程，调用run方法
     */
    void execute() {
        if (mThread != null) {
            mThread.start();
        }
    }
}
