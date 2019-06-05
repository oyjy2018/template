package com.ydc.commom.result;

public class ResultConstant {


    /********  系统级code  start   ****************/
    /**
     * 结果为空
     */
    public static int RESULT_CODE_IS_NUll = 10010;

    public static int RESULT_CODE_SUCCESS = 200;


    //异常或者错误之类的,定义请小于 10000
    public static int RESULT_CODE_FAILURE = 9999;
    public static int RESULT_CODE_EXCEPTION = 9000;
    public static int RESULT_CODE_SERVICERUNTIMEEXCEPTION = 9001;

    public static int RESULT_CODE_PUBLICKEY_INVALID = 9009;

    //短信超过最高次数
    public static int MAX_SEND_FAILURE = 9997;
    //请求参数错误
    public static int RESULT_CODE_PARAM_FAILURE = 9996;
    //公共数据版本还未生成
    public static int RESULT_CODE_COMMON_VERSION_FAILURE = 9010;



    /********  系统级code  end   ****************/


    /************  data 中常量请定义在此处  *****************/

    /************  data 登录常量请定义在此处  *****************/
    //未登录错误
    public static int NOT_LOGIN_FAILURE = 403;
    //获取临时授权码异常，请稍后再试
    public static int LOGIN_FAILURE_404 = 404;
    //获取钉钉开放应用的ACCESS_TOKEN，请稍后再试
    public static int LOGIN_FAILURE_405 = 405;
    //钉钉服务器繁忙，请稍后再试
    public static int LOGIN_FAILURE_406 = 406;
    //获取持久授权码异常，请稍后再试
    public static int LOGIN_FAILURE_407 = 407;
    //获取SNS_TOKEN异常，请稍后再试
    public static int LOGIN_FAILURE_408 = 408;
    //获取成员的userid异常，请稍后再试
    public static int LOGIN_FAILURE_409 = 409;
    //获取成员详情异常，请稍后再试
    public static int LOGIN_FAILURE_410 = 410;
    //您已被禁用，请联系管理员
    public static int LOGIN_FAILURE_411 = 411;
    //登录账号被锁，请联系管理员
    public static int LOGIN_FAILURE_412 = 412;
    //登录失败，请联系管理员
    public static int LOGIN_FAILURE_413 = 413;
    //登录失效,请重新登陆
    public static int LOGIN_FAILURE_414 = 414;
    //正在登录中
    public static int LOGIN_FAILURE_415 = 415;
    //暂无登录权限
    public static int LOGIN_FAILURE_416 = 416;
    /************  data 登录量请定义在此处  *****************/

    /************  函数返回结果状态在此定义  *****************/
    //函数逻辑执行成功
    public static int FUNCTION_CODE_SUCCESS = 1;
    //函数逻辑执行失败
    public static int FUNCTION_CODE_FAILURE = 0;
    /************  函数返回结果状态在此定义  *****************/
}
