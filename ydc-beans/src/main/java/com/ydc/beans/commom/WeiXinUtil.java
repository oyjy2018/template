package com.ydc.beans.commom;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.HttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


public class WeiXinUtil {

    private static final Logger logger = LogManager.getLogger(WeiXinUtil.class);
    private static final String CODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";

    private static final String LOGIN_GRANT_TYPE = "authorization_code";

    //微信登录url
    private static final String WEIXIN_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //微信获取用户信息url
    private static final String WEIXIN_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    private static final String WEIXIN_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_base&state={2}#wechat_redirect";

    private static final String WEIXIN_OPEN_ID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";

    public static Map<String, Object> getSessionKeyAndOpenid(String code){
        String path = MessageFormat.format(CODE_2_SESSION,SystemPropertiesConfig.WEIXIN_APPID,SystemPropertiesConfig.WEIXIN_SECRET,code);
        String res = HttpUtil.get(path);
        if(StringUtil.isEmpty(res)){
            return null;
        }
        return JsonUtil.jsonToMap(res);
    }

    /**
     * 获取openId的code
     * @param mobilePhone
     * @return
     * @throws Exception
     */
    public static void getMemberOpenIdCode(String mobilePhone) throws Exception{
        String notifyUrl = URLEncoder.encode(SystemPropertiesConfig.WEIXIN_PAY_OPENIDNOTIFYURL, "UTF-8");
        String path = MessageFormat.format(WEIXIN_AUTHORIZE_URL, SystemPropertiesConfig.WEIXIN_PAY_APPID, notifyUrl, mobilePhone);
        logger.info("subject: {}, path: {}", "微信获取用户openId前获取code", path);
//        UrlHttpUtil.doGet(path);
        logger.info("subject: {}, mobilePhone: {}， res：{}", "微信获取用户openId前获取code结果", mobilePhone, UrlHttpUtil.doGet(path));
//        if(StringUtil.isEmpty(res)){
//            return null;
//        }
//        return JsonUtil.jsonToMap(res);
    }

    /**
     * 获取openId的url
     * @param mobilePhone
     * @return
     * @throws Exception
     */
    public static String getMemberOpenIdCodeUrl(String mobilePhone) throws Exception{
        String notifyUrl = URLEncoder.encode(SystemPropertiesConfig.WEIXIN_PAY_OPENIDNOTIFYURL, "UTF-8");
        String path = MessageFormat.format(WEIXIN_AUTHORIZE_URL, SystemPropertiesConfig.WEIXIN_PAY_APPID, notifyUrl, mobilePhone);
        logger.info("subject: {}, path: {}", "微信获取用户openId前获取codeUrl", path);
        return path;
    }

    /**
     * 获取openId
     * @param code
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getMemberOpenId(String code){
        logger.info("subject: {}, code: {}, appid: {}, secret: {}", "微信获取用户openId", code, SystemPropertiesConfig.WEIXIN_PAY_APPID, SystemPropertiesConfig.WEIXIN_PAY_SECRET);

        String path = MessageFormat.format(WEIXIN_OPEN_ID_URL, SystemPropertiesConfig.WEIXIN_PAY_APPID, SystemPropertiesConfig.WEIXIN_PAY_SECRET, code);
        String res = HttpUtil.get(path);
        logger.info("subject: {}, code: {}， res：{}", "微信获取用户openId结果", code, res);
        if(StringUtil.isEmpty(res)){
            return null;
        }
        return JsonUtil.jsonToMap(res);
    }

    /**
     * 微信登录
     * @param code
     * @return
     */
    public static String weixinLogin(String code) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("appid", SystemPropertiesConfig.WEIXIN_LOGIN_APPID);
        params.put("secret", SystemPropertiesConfig.WEIXIN_LOGIN_SECRET);
        params.put("code", code);
        params.put("grant_type", LOGIN_GRANT_TYPE);
        String result = UrlHttpUtil.doGet(WEIXIN_LOGIN_URL, params);
        logger.info("subject: {}, result: {}", "微信登录", result);
        return result;
    }

    /**
     * 微信获取用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    public static String getWeixinUserinfo(String accessToken, String openid) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("openid", openid);
        params.put("lang", "zh_CN ");
        String result = UrlHttpUtil.doGet(WEIXIN_USERINFO_URL, params);
        logger.info("subject: {}, result: {}", "微信获取用户信息", result);
        return result;
    }

    public static void main(String[] args) {
       logger.info(getSessionKeyAndOpenid("023QF24h169bxy0R5f4h1Ef24h1QF24o"));
    }
}
