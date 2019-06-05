package com.ydc.commom.urlHttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.constant.HttpConstant;
import com.ydc.commom.exception.ServiceRuntimeException;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * http工具类
 *  使用的是 HttpURLConnection
 */

public class UrlHttpUtil {
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    public static final String FILE_TYPE_FILE = "file/*";
    public static final String FILE_TYPE_IMAGE = "image/*";
    public static final String FILE_TYPE_AUDIO = "audio/*";
    public static final String FILE_TYPE_VIDEO = "video/*";



    /**
     * get请求
     * @param url：url
     */
    public static String doGet(String url) throws Exception {
        return doGet(url, null, null);
    }

    /**
     * get请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static String doGet(String url, Map<String, Object> paramsMap) throws Exception {
        return  doGet(url, paramsMap, null);
    }

    /**
     * get请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String doGet(String url, Map<String, Object> paramsMap, Map<String, String> headerMap) throws Exception {
          return  RequestUtil.handleHttp(METHOD_GET, url, paramsMap,null, headerMap);
    }

    /**
     * post请求
     * @param url：url
     */
    public static String doPost(String url)throws  Exception{
        return doPost(url, null, null,null);
    }

    /**
     *
     * @param url
     * @param paramsMap：封装键值对参数
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, Object> paramsMap)throws  Exception{
        return doPost(url, paramsMap, null, null);
    }

    /**
     *
     * @param url
     * @param jsonStr：json格式的键值对参数
     * @return
     * @throws Exception
     */
    public static String doPost(String url,String jsonStr)throws  Exception{
        return doPost(url, null, jsonStr, null);
    }

    /**
     *
     * @param url
     * @param jsonStr：json格式的键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @return
     * @throws Exception
     */
    public static String doPost(String url,String jsonStr, Map<String, String> headerMap)throws  Exception{
        return doPost(url, null, jsonStr,headerMap);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String doPost(String url, Map<String, Object> paramsMap, Map<String, String> headerMap) throws  Exception{
       return doPost(url, paramsMap, null,headerMap);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param jsonStr：json格式的键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String doPost(String url, Map<String, Object> paramsMap,String jsonStr, Map<String, String> headerMap) throws  Exception{
        return  RequestUtil.handleHttp(METHOD_POST, url, paramsMap,jsonStr, headerMap);

    }




    /**
     * get请求
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void get(String url, CallBackUtil callBack) {
        get(url, null, null, callBack);
    }

    /**
     * get请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void get(String url, Map<String, Object> paramsMap, CallBackUtil callBack) {
        get(url, paramsMap, null, callBack);
    }

    /**
     * get请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void get(String url, Map<String, Object> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_GET, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * post请求
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void post(String url, CallBackUtil callBack) {
        post(url, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void post(String url, Map<String, Object> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST,url,paramsMap,headerMap,callBack).execute();
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void post(String url, Map<String, Object> paramsMap, CallBackUtil callBack) {
        post(url, paramsMap, null, callBack);
    }
    /**
     * post请求，可以传递参数
     * @param url：url
     * @param jsonStr：json格式的键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void postJson(String url, String jsonStr, CallBackUtil callBack) {
        postJson(url, jsonStr, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param jsonStr：json格式的键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用
     */
    public static void postJson(String url, String jsonStr, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(url,jsonStr,headerMap,callBack).execute();
    }
    
    /**
     * post请求，上传单个文件
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用,还可以重写onProgress方法，得到上传进度
     */
    public static void uploadFile(String url, File file, String fileKey, String fileType, CallBackUtil callBack) {
        uploadFile(url, file, fileKey,fileType, null, callBack);
    }

    /**
     * post请求，上传单个文件
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，还可以重写onProgress方法，得到上传进度
     */
    public static void uploadFile(String url, File file, String fileKey, String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        uploadFile(url, file,fileKey, fileType, paramsMap, null, callBack);
    }

    /**
     * post请求，上传单个文件
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，还可以重写onProgress方法，得到上传进度
     */
    public static void uploadFile(String url, File file, String fileKey, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(url,file,null,null,fileKey,fileType,paramsMap,headerMap,callBack).execute();
    }


    /**
     * post请求，上传多个文件，以list集合的形式
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，
     */
    public static void uploadListFile(String url, List<File> fileList, String fileKey, String fileType, CallBackUtil callBack) {
        uploadListFile(url, fileList, fileKey, fileType,null, callBack);
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void uploadListFile(String url, List<File> fileList, String fileKey, String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        uploadListFile(url, fileList, fileKey, fileType,paramsMap, null, callBack);
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void uploadListFile(String url, List<File> fileList, String fileKey, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(url,null,fileList,null,fileKey,fileType,paramsMap,headerMap,callBack).execute();
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void uploadMapFile(String url, Map<String, File> fileMap, String fileType, CallBackUtil callBack) {
        uploadMapFile(url, fileMap, fileType, null, callBack);
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void uploadMapFile(String url, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        uploadMapFile(url, fileMap, fileType, paramsMap, null, callBack);
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用。
     */
    public static void uploadMapFile(String url, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(url,null,null,fileMap,null,fileType,paramsMap,headerMap,callBack).execute();
    }


    /**
     * 下载文件,不带参数
     */
    public static void downloadFile(String url, CallBackUtil.CallBackFile callBack) {
        downloadFile(url,null,callBack);
    }

    /**
     * 下载文件,带参数
     */
    public static void downloadFile(String url, Map<String, Object> paramsMap, CallBackUtil.CallBackFile callBack) {
        downloadFile(url, paramsMap, null, callBack);
    }
    /**
     * 下载文件,带参数,带请求头
     */
    public static void downloadFile(String url, Map<String, Object> paramsMap,Map<String, String> headerMap, CallBackUtil.CallBackFile callBack) {
        get(url, paramsMap, headerMap, callBack);
    }

    /**
     * 处理响应数据
     * @param response
     */
    public static void doResponse(String response, Consumer<String> successResponse) throws Exception{
        doResponse(response, null, successResponse);
    }

    public static void doResponse(String response, Consumer<String> failureResponse, Consumer<String> successResponse) throws Exception{
        JSONObject jsonObject = JSON.parseObject(response);
        //请求失败
        Object status = jsonObject.get(HttpConstant.RESPONSE_STATUS);
        if (status == null || !HttpConstant.RESPONSE_STATUS_SUCCESS.equals(status)){
            if (failureResponse != null){
                failureResponse.accept(jsonObject.getString(HttpConstant.RESPONSE_DATA));
            }
            throw new ServiceRuntimeException(jsonObject.getString(HttpConstant.RESPONSE_MESSAGE));
        }
        //请求成功
        successResponse.accept(jsonObject.getString(HttpConstant.RESPONSE_DATA));
    }

}
