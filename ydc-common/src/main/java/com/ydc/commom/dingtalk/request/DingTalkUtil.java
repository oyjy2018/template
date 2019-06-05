package com.ydc.commom.dingtalk.request;

import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.dingtalk.auth.AuthHelper;
import com.ydc.commom.dingtalk.demo.Env;
import com.ydc.commom.dingtalk.demo.OApiException;
import com.ydc.commom.dingtalk.utils.HttpHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一存放调用钉钉接口工具类
 *
 * @author:gongjin
 * @date：2017年5月24日 下午4:03:12
 */
public class DingTalkUtil {

    private static Logger logger = LogManager.getLogger(DingTalkUtil.class);

    /**
     * 获取钉钉开放应用的ACCESS_TOKEN
     *
     * @return accesstoken
     */
    public static JSONObject gettoken(String appId, String appSecret) {
        String url = Env.OAPI_HOST + Env.GET_TOKEN + "?appid=" + appId + "&appsecret=" + appSecret;
        logger.info("获取钉钉开放应用的ACCESS_TOKEN" + url);
        try {
            return HttpHelper.httpGet(url);
        } catch (Exception e) {
            logger.error("获取钉钉开放应用的ACCESS_TOKEN异常", e);
            return null;
        }
    }

    /**
     * 获取用户授权的持久授权码
     * 注意access_token 分为两种，扫二维码获取的token和直接http请求的token意义不一样，注意区分
     * 这里使用的扫二码获取的access_token
     *
     * @param accessToken
     * @param tmpAuthCode
     * @return openid   用户在当前开放应用内的唯一标识
     * unionid 用户在当前钉钉开放平台账号范围内的唯一标识，
     * 同一个钉钉开放平台账号可以包含多个开放应用，
     * 同时也包含ISV的套件应用及企业应用
     * persistent_code 用户给开放应用授权的持久授权码，此码目前无过期时间
     */
    public static JSONObject getPersistentCode(String accessToken, String tmpAuthCode) {
        String url = Env.OAPI_HOST + Env.GET_PERSISTENT_CODE + "?access_token=" + accessToken;
        Map<String, String> map = new HashMap<>();
        map.put("tmp_auth_code", tmpAuthCode);
        logger.info("获取用户授权的持久授权码,url:" + url + ",accessToken:" + accessToken + ",tmpAuthCode:" + tmpAuthCode);
        try {
            return HttpHelper.httpPost(url, map);
        } catch (Exception e) {
            logger.error("获取用户授权的持久授权码异常", e);
            return null;
        }
    }

    /**
     * 获取用户授权的SNS_TOKEN
     *
     * @param accessToken
     * @param openid
     * @param persistentCode
     * @return expires_in  sns_token的过期时间
     * sns_token   用户授权的token
     */
    public static JSONObject getSnsToken(String accessToken, String openid, String persistentCode) {
        Map<String, String> map = new HashMap<>();
        map.put("openid", openid);
        map.put("persistent_code", persistentCode);
        String url = Env.OAPI_HOST + Env.GET_SNS_TOKEN + "?access_token=" + accessToken;
        logger.info("获取用户授权的SNS_TOKEN,url:" + url + ",accessToken:" + accessToken + ",openid:" + openid + ",persistentCode:" + persistentCode);
        try {
            return HttpHelper.httpPost(url, map);
        } catch (Exception e) {
            logger.error("获取用户授权的SNS_TOKEN异常", e);
            return null;
        }
    }

    /**
     * 获取用户授权的个人信息
     *
     * @param snsToken
     * @return corp_info    企业信息（默认不返回）
     * is_auth     企业是否经过钉钉认证（默认不返回）
     * is_manager  当前用户是否为该企业的管理人员（默认不返回）
     * rights_level该企业的权益等级（默认不返回）
     * corp_name   企业名称（默认不返回）
     * maskedMobile经过处理的手机号（默认不返回）
     * nick        用户在钉钉上面的昵称
     * openid      用户在当前开放应用内的唯一标识
     * unionid     用户在当前开放应用所属的钉钉开放平台账号内的唯一标识
     * dingId      钉钉Id
     */
    public static JSONObject getUserInfo(String snsToken) {
        String url = Env.OAPI_HOST + Env.GETUSERINFO + "?sns_token=" + snsToken;
        logger.info("获取用户授权的个人信息,url:" + url + ",snsToken:" + snsToken);
        try {
            return HttpHelper.httpGet(url);
        } catch (Exception e) {
            logger.error("获取用户授权的个人信息异常", e);
            return null;
        }
    }

    /**
     * 根据unionid获取成员的userid
     *
     * @param unionid AuthHelper.getAccessToken() 获取用户信息
     * @return userid 员工唯一标识ID（不可修改）
     */
    public static JSONObject getUseridByUnionid(String unionid, String corpId, String corpSecret) {
        try {
            String url = Env.OAPI_HOST + Env.GETUSERIDBYUNIONID + "?access_token=" + getAccessToken(corpId, corpSecret) + "&unionid=" + unionid;
            logger.info("根据unionid获取成员的userid,url:" + url);
            return HttpHelper.httpGet(url);
        } catch (Exception e) {
            logger.error("根据unionid获取成员的userid异常", e);
            return null;
        }
    }

    /**
     * 获取成员详情
     *
     * @param userid 员工在企业内的UserID，企业用来唯一标识用户的字段
     * @return 参数太多就不列举，详情请查看
     * https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.dC7hvN&treeId=172&articleId=104979&docType=1
     */
    public static JSONObject get(String userid, String corpId, String corpSecret) {
        try {
            String url = Env.OAPI_HOST + Env.GET + "?access_token=" + getAccessToken(corpId, corpSecret) + "&userid=" + userid;
            logger.info("获取成员详情,url:" + url);
            return HttpHelper.httpGet(url);
        } catch (Exception e) {
            logger.error("根据unionid获取成员的userid异常", e);
            return null;
        }
    }

    /**
     * 获取部门列表
     *
     * @author:gongjin
     * @param:
     * @date: 2017年10月19日 下午2:51:51
     * @return:JSONObject
     */
    public static JSONObject getDepartmentList(String corpId, String corpSecret) {
        try {
            String url = Env.OAPI_HOST + Env.DEPARTMENT_LIST + "?access_token=" + getAccessToken(corpId, corpSecret);
            logger.info("获取部门列表,url:" + url);
            return HttpHelper.httpGet(url);
        } catch (Exception e) {
            logger.error("获取部门列表异常", e);
            return null;
        }
    }

    /**
     * 获取部门成员
     *
     * @author:gongjin
     * @param: departmentId 部门id
     * @date: 2017年10月19日 下午3:23:35
     * @return:JSONObject
     */
    public static JSONObject getSimplelist(Integer departmentId, String corpId, String corpSecret) {
        try {
            String url = Env.OAPI_HOST + Env.USER_SIMPLELIST + "?access_token=" + getAccessToken(corpId, corpSecret) + "&department_id=" + departmentId;
            logger.info("获取部门成员,url:" + url);
            return HttpHelper.httpGet(url);
        } catch (Exception e) {
            logger.error("获取部门成员异常", e);
            return null;
        }
    }

    /**
     * 获取access_token
     *
     * @author:gongjin
     * @param:
     * @date: 2017年10月31日 下午4:09:59
     * @return:String
     */
    public static String getAccessToken(String corpId, String corpSecret) throws OApiException {
        return AuthHelper.getAccessToken(corpId, corpSecret);
    }
}
