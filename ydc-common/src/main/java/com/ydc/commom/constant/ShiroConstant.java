package com.ydc.commom.constant;

/**
 * shiro常量
 */
public class ShiroConstant {

    //shiro认证key
    public static final String AUTHORIZATION = "authorization";

    //登录失败，用户不存在
    public static final String NO_MEMBER_FAILURE_LOGIN = "no_member_failure_login";

    //登录失败，用户名或密码错误
    public static final String PASSWORD_FAILURE_LOGIN = "password_failure_login";

    //登录失败，用户已注销
    public static final String MEMBER_LOGOFF_FAILURE_LOGIN = "member_logoff_failure_login";

    //登录失败，用户已锁定
    public static final String MEMBER_LOCKED_FAILURE_LOGIN = "member_locked_failure_login";

    //shiro redis的key
    public static final String CGJ_SHIRO_REDIS_KEY = "CGJ:APP_SHIRO:SESSION:";
    public static final String CGJ_SHIRO_USER_REDIS_KEY = "CGJ:APP_SHIRO:USER:";

    //shiro session的有效时长（秒）
    public static final long SHIRO_SESSION_TIMEOUT = 30*24*60*60L;

    //不用获取sessionId的请求url（重新生成sessionId）
    public static final String NO_SESSIONID_URL = "/login/doRegister, /login/doPasswordLogin, /login/doValidateLogin, /login/doWeixinLogin, /login/bindMemberPhone";

    //密码错误最高次数
    public static final int PASSWORD_FAULT_TIMES = 3;

    //一点好车 shiro redis的key
    public static final String YDHC_SHIRO_REDIS_KEY = "ydhc:shiro:appSession:";

    //一点好车 shiro session的有效时长
    public static final long YDHC_SHIRO_SESSION_TIMEOUT = 7*24*60*60*1000L;

    //租车shiro redis的key
    public static final String RENTAL_APPC_SHIRO_REDIS_KEY = "RENTAL:APPC_SHIRO:SESSION:";
    public static final String RENTAL_SHIRO_USER_REDIS_KEY = "RENTAL:APPC_SHIRO:USER:";
    //租车shiro session的有效时长（秒）
    public static final long RENTAL_SHIRO_SESSION_TIMEOUT = 30*24*60*60L;


    //shiro redis的key
    public static final String RENTAL_B_SHIRO_REDIS_KEY = "RENTAL:B:APP_SHIRO:SESSION:";
    public static final String RENTAL_B_SHIRO_USER_REDIS_KEY = "RENTAL:B:APP_SHIRO:USER:";
    //shiro session的有效时长（秒）
    public static final long RENTAL_B_SHIRO_SESSION_TIMEOUT = 30*24*60*60L;
}
