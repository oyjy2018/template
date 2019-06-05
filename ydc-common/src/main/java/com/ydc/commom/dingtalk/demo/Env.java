package com.ydc.commom.dingtalk.demo;


/**
 * 钉钉工具类
 */
public class Env {

    /**
     * 获取钉钉开放应用的ACCESS_TOKEN
     */
    public static final String AUTHORIZATION = "https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=%s&response_type=code&scope=snsapi_login&state=STATE&redirect_uri=%s";


    /**
     * 钉钉统一接口
     */
    public static final String OAPI_HOST = "https://oapi.dingtalk.com";

    /**
     * 获取钉钉开放应用的ACCESS_TOKEN
     * 使用appid及appSecret访问如下接口，获取accesstoken，此处获取的token有效期为2小时，
     * 有效期内重复获取，返回相同值，并自动续期，如果在有效期外获取会获得新的token值，
     * 建议定时获取本token，不需要用户登录时再获取。
     */
    public static final String GET_TOKEN = "/sns/gettoken";

    /**
     * 获取用户授权的持久授权码
     * 使用第3步获取的AccessToken及第1步中获取到的临时授权码code(tmp_auth_code)，
     * 调用下面的接口来获取当前钉钉用户授权给你的持久授权码。此授权码目前无过期时间，
     * 可反复使用，参数临时授权码code只能使用一次。
     * Https请求方式: POST
     * https://oapi.dingtalk.com/sns/get_persistent_code?access_token=ACCESS_TOKEN
     */
    public static final String GET_PERSISTENT_CODE = "/sns/get_persistent_code";

    /**
     * 获取用户授权的SNS_TOKEN
     * 在获得钉钉用户的持久授权码后，通过以下接口获取该用户授权的SNS_TOKEN，
     * 此token的有效时间为2小时，重复获取不会续期。
     * Https请求方式: POST
     * https://oapi.dingtalk.com/sns/get_sns_token?access_token=ACCESS_TOKEN
     */
    public static final String GET_SNS_TOKEN = "/sns/get_sns_token";

    /**
     * 获取用户授权的个人信息
     * 在获得钉钉用户的SNS_TOKEN后，通过以下接口获取该用户的个人信息
     */
    public static final String GETUSERINFO = "/sns/getuserinfo";

    /**
     * 根据unionid获取成员的userid
     * Https请求方式: GET
     * https://oapi.dingtalk.com/user/getUseridByUnionid?access_token=ACCESS_TOKEN&unionid=xxxxxx
     */
    public static final String GETUSERIDBYUNIONID = "/user/getUseridByUnionid";

    /**
     * 获取成员详情
     * Https请求方式: GET
     * https://oapi.dingtalk.com/user/get?access_token=ACCESS_TOKEN&userid=zhangsan
     */
    public static final String GET = "/user/get";

    /**
     * 创建会话
     * Https请求方式: POST
     * https://oapi.dingtalk.com/chat/create?access_token=ACCESS_TOKEN
     */
    public static final String CHAT_CREATE = "/chat/create";

    /**
     * 获取会话
     * Https请求方式: GET
     * https://oapi.dingtalk.com/chat/get?access_token=ACCESS_TOKEN
     */
    public static final String CHAT_GET = "/chat/get";

    /**
     * 发送群消息
     * Https请求方式: POST
     * https://oapi.dingtalk.com/chat/send?access_token=ACCESS_TOKEN
     */
    public static final String CHAT_SEND = "/chat/send";

    /**
     * 发送普通消息
     * Https请求方式: POST
     * https://oapi.dingtalk.com/message/send_to_conversation?access_token=ACCESS_TOKEN
     */
    public static final String MESSAGE_SEND_TO_CONVERSATION = "/message/send_to_conversation";

    /**
     * 获取部门列表
     * Https请求方式: GET
     * https://oapi.dingtalk.com/department/list?access_token=ACCESS_TOKEN
     */
    public static final String DEPARTMENT_LIST = "/department/list";

    /**
     * 获取部门成员
     * Https请求方式: GET
     * https://oapi.dingtalk.com/user/simplelist?access_token=ACCESS_TOKEN&department_id=1
     */
    public static final String USER_SIMPLELIST = "/user/simplelist";

    /**
     * 发送企业通知消息
     * Https请求方式: POST
     * https://oapi.dingtalk.com/message/send?access_token=ACCESS_TOKEN
     */
    public static final String MESSAGE_SEND = "/message/send";

    /**
     * 获取企业通知消息已读未读状态
     */
    public static final String MESSAGE_LIST_MESSAGE_STATUS = "/message/list_message_status";

    /**
     * 上传媒体文件
     * Https请求方式: POST
     * https://oapi.dingtalk.com/media/upload?access_token=ACCESS_TOKEN&type=TYPE
     */
    public static final String MEDIA_UPLOAD = "/media/upload";

}
