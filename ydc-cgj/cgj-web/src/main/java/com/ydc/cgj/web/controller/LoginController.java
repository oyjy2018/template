package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.security.RSAKeyUtil;
import com.ydc.beans.shiro.web.WebShiroRealm;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.constant.Constants;
import com.ydc.cgj.web.service.ServiceUserRoleService;
import com.ydc.cgj.web.service.UserService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.dingtalk.demo.Env;
import com.ydc.commom.dingtalk.request.DingTalkUtil;
import com.ydc.commom.enums.LoginEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.Dd;
import com.ydc.model.cgj.ServiceUserRole;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * PC管理后台登陆
 *
 * @author gongjin
 * @create 2018-09-05 13:31
 **/
@Controller
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Autowired
    UserService userService;
    @Autowired
    ServiceUserRoleService serviceUserRoleService;
    @Autowired
    WebShiroRealm webShiroRealm;

    /**
     * 钉钉扫码请求地址
     *
     * @return
     */
    @GetMapping(value = "/getLoginParam")
    @ResponseBody
    public String getLoginParam(Map<String, Object> synMap) {
        try {
            logger.info("getLoginParam 请求");
            Optional<Dd> optional = Optional.ofNullable(DictionaryUtil.getdingtalkParamByServiceIdentifying(Constants.SERVICEIDENTIFYING)
                    .orElseGet(()->userService.getDdConfigByServiceIdentifying(Constants.SERVICEIDENTIFYING)));
            Dd dd = optional.get();
            logger.info("getLoginParam dd" + dd.toString());
            String redirectUri = StringUtil.isNotEmpty(dd.getRedirectUri()) ? dd.getRedirectUri() : SystemPropertiesConfig.CGJ_DD_REDIRECTURI;
            synMap.put("goto_url", String.format(Env.AUTHORIZATION, dd.getDdAppid(), redirectUri));
            synMap.put("code", ResultConstant.LOGIN_FAILURE_415);
            logger.info("getLoginParam 请求 goto_url" + String.format(Env.AUTHORIZATION, dd.getDdAppid(), redirectUri));
            return JsonUtil.gsonStr(synMap);
        } catch (Exception e) {
            logger.error("subject:{},e:{}","getLoginParam 请求异常" , e);
        }
        return JsonUtil.gsonStr(synMap);
    }

    /**
     * 授权成功后登陆
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/login")
    public String doLogin(HttpServletRequest request) {
        try {
            if (StringUtil.isEmpty(request.getParameter("code"))) {
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_404;
            }
            Optional<Dd> optional = Optional.ofNullable(DictionaryUtil.getdingtalkParamByServiceIdentifying(Constants.SERVICEIDENTIFYING)
                    .orElseGet(()->userService.getDdConfigByServiceIdentifying(Constants.SERVICEIDENTIFYING)));
            Dd dd = optional.get();
            JSONObject obj1 = null;
            try {
                obj1 = DingTalkUtil.gettoken(dd.getDdAppid(), dd.getDdAppsecret());
                if (null == obj1 || obj1.getInteger("errcode") != 0) {
                    logger.info("subject:" + LoginEnum.LG_405.getMessage() + ",obj1:" + obj1);
                    return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_405;
                }
            } catch (Exception e) {
                logger.error(LoginEnum.LG_406.getMessage(), e);
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_406;
            }
            String accessToken = obj1.get("access_token").toString();
            JSONObject obj2 = DingTalkUtil.getPersistentCode(accessToken, request.getParameter("code"));
            if (null == obj2 || obj2.getInteger("errcode") != 0) {
                logger.info("subject:" + LoginEnum.LG_407.getMessage() + ",obj2:" + obj2);
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_407;
            }
            String openid = obj2.getString("openid");
            String unionid = obj2.getString("unionid");
            String persistentCode = obj2.getString("persistent_code");
            JSONObject obj3 = DingTalkUtil.getSnsToken(accessToken, openid, persistentCode);
            if (null == obj3 || obj3.getInteger("errcode") != 0) {
                logger.info("subject:" + LoginEnum.LG_408.getMessage() + ",obj3:" + obj3 + ",unionid:" + unionid);
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_408;
            }
            String sns_token = obj3.getString("sns_token");
            JSONObject obj4 = DingTalkUtil.getUserInfo(sns_token);
            if (null != obj4 && obj4.getInteger("errcode") != 0) {
                User user = new User();
                user.setStatus(CodeConstant.DISABLE_STATUS);
                user.setDdUId(unionid);
                userService.updateUser(user);
            } else if (null == obj4) {
                logger.info("subject:" + LoginEnum.LG_409.getMessage() + ",obj4:" + obj4 + ",unionid:" + unionid);
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_409;
            }

            JSONObject obj5 = DingTalkUtil.getUseridByUnionid(unionid, dd.getDdCorpId(), dd.getDdCorpSecret());
            if (null == obj5 || obj5.getInteger("errcode") != 0) {
                logger.info("subject:" + LoginEnum.LG_409.getMessage() + ",obj5:" + obj5 + ",unionid:" + unionid);
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_409;
            }
            String userid = obj5.getString("userid");
            JSONObject obj6 = DingTalkUtil.get(userid, dd.getDdCorpId(), dd.getDdCorpSecret());
            if (null == obj6 || obj6.getInteger("errcode") != 0) {
                logger.info("subject:" + LoginEnum.LG_410.getMessage() + ",obj6:" + obj6 + ",unionid:" + unionid);
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_410;
            }

            String collectionAccountName = null;
            String collectionCardNumber = null;
            String collectionBankName = null;
            String collectionBankSubbranch = null;
            if (obj6.containsKey("extattr")) {
                String extattr = obj6.getString("extattr");
                if (StringUtil.isNotEmpty(extattr)) {
                    Map<String, Object> map = JsonUtil.jsonToMap(extattr);
                    if (map != null && !map.isEmpty()) {
                        collectionAccountName = map.get("户主姓名") == null ? null : map.get("户主姓名").toString();
                        collectionCardNumber = map.get("银行卡号") == null ? null : map.get("银行卡号").toString();
                        collectionBankName = map.get("银行名称") == null ? null : map.get("银行名称").toString();
                        collectionBankSubbranch = map.get("开户行") == null ? null : map.get("开户行").toString();
                    }
                }
            }
            logger.info("subject:根据userid获取成员详情,obj6:" + obj6);

            String loginName = obj6.getString("userid");
            String userName = obj6.getString("name");
            Date date = new Date();
            User user = userService.getUserByLoginName(loginName);
            logger.info("subject:{},user:{}", loginName + "查询用户信息", JsonUtil.gsonStr(user));
            if (null == user || StringUtil.isEmpty(user.getId())) {
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_416;
//                user = new User();
//                user.setUserName(userName);
//                user.setLoginName(loginName);
//                user.setMobilePhone(obj6.getString("mobile"));
//                user.setJobName(obj6.getString("position"));
//                user.setEmail(obj6.getString("email"));
//                user.setJobNumber(obj6.getString("jobnumber"));
//                user.setAvatarUrl(obj6.getString("avatar"));
//                user.setDdUId(unionid);
//                user.setDdId(obj6.getString("dingId"));
//                user.setCollectionAccountName(collectionAccountName);
//                user.setCollectionCardNumber(collectionCardNumber);
//                user.setCollectionBankName(collectionBankName);
//                user.setCollectionBankSubbranch(collectionBankSubbranch);
//                user.setStatus(CodeConstant.NORMAL_STATUS);
//                user.setCreateTime(date);
//                user.setCreateBy(CodeConstant.CREATE_BY);
//                user.setUpdateBy(CodeConstant.UPDATE_BY);
//                userService.insert(user);
//                user = userService.getUserByLoginName(loginName);
            } else {
                if (CodeConstant.NORMAL_STATUS == user.getStatus()) {
                    user.setUserName(userName);
                    user.setLoginName(loginName);
                    user.setMobilePhone(obj6.getString("mobile"));
                    user.setJobName(obj6.getString("position"));
                    user.setEmail(obj6.getString("email"));
                    user.setJobNumber(obj6.getString("jobnumber"));
                    user.setAvatarUrl(obj6.getString("avatar"));
                    user.setDdUId(unionid);
                    user.setDdId(obj6.getString("dingId"));
                    user.setCollectionAccountName(collectionAccountName);
                    user.setCollectionCardNumber(collectionCardNumber);
                    user.setCollectionBankName(collectionBankName);
                    user.setCollectionBankSubbranch(collectionBankSubbranch);
                    user.setStatus(CodeConstant.NORMAL_STATUS);
                    user.setUpdateBy(CodeConstant.UPDATE_BY);
                    user.setUpdateTime(date);
                    userService.updateUser(user);
                } else {
                    return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_411;
                }
            }
            final ServiceUserRole serviceUserRole = serviceUserRoleService.getServiceUserRoleByUserId(user.getId());
            user.setRoleId(serviceUserRole == null ? null : serviceUserRole.getCgjRoleId());
            user.setServiceIdentifying(Constants.SERVICEIDENTIFYING);


            WebShiroTokenManager.login(user);
            webShiroRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());

            String token = WebShiroTokenManager.getToken(user);
           logger.info(token);
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    userService.saveLogLogin(user.getId(), user.getUserName(), user.getLoginName(), null, request);
                    if(serviceUserRole == null){
                        ServiceUserRole serviceUserRole = new ServiceUserRole();
                        serviceUserRole.setUserId(user.getId());
                        serviceUserRole.setCreateTime(date);
                        serviceUserRole.setCreateBy(user.getId());
                        serviceUserRoleService.insertServiceUserRole(serviceUserRole);
                    }
                }
            });
            return SystemPropertiesConfig.CGJ_REDIRECT_URL_SUCCEED + token;
        } catch (DisabledAccountException lae) {
            return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_412;
        } catch (AccountException lae) {
            return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_412;
        } catch (Exception e) {
            logger.error("登陆访问异常", e);
            return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_413;
        }
    }


    @GetMapping(value = "/loginSuccess")
    @ResponseBody
    public String loginSuccess() {
        return Result.success("登陆成功").toJSON();
    }

    /**
     * token换取用户信息
     * @return
     */
    @GetMapping(value = "/getUserInfo")
    @ResponseBody
    public String getUserInfo( HttpServletResponse response) {
        User user = WebShiroTokenManager.getUser();
        if (user == null) response.setHeader("code", String.valueOf(ResultConstant.LOGIN_FAILURE_414));
        user.setPublicKey(RSAKeyUtil.getPublicKey());
        return Result.success(user).toJSON();
    }

    /**
     * 退出登录
     *
     * @param request
     */
    @GetMapping(value = "/quit")
    public String quit(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return Result.success("退出成功").toJSON();
    }

    /**
     * 越过扫码，直接登录
     *
     * @param mobilePhone
     * @return
     */
    @GetMapping(value = "/performance/{mobilePhone}")
    public String performance(@PathVariable(value = "mobilePhone") String mobilePhone, HttpServletRequest request) {
        try {
            logger.info("越过扫码，直接登录mobilePhone:" + mobilePhone + ";sysXingneng:" + SystemPropertiesConfig.SYS_XINGNENG);
            if ("on".equals(SystemPropertiesConfig.SYS_XINGNENG)) {
                if (mobilePhone == null) return "登录mobilePhone不能为空";
                User user = userService.getUserByMobilePhoneNoStatus(mobilePhone);
                if (user == null) return "登录mobilePhone不存在";
                final ServiceUserRole serviceUserRole = serviceUserRoleService.getServiceUserRoleByUserId(user.getId());
                user.setRoleId(serviceUserRole.getCgjRoleId());
                user.setServiceIdentifying(Constants.SERVICEIDENTIFYING);
                WebShiroTokenManager.login(user);
                webShiroRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
                String token = WebShiroTokenManager.getToken(user);
                cachedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        userService.saveLogLogin(user.getId(), user.getUserName(), user.getLoginName(), null, request);
                        ServiceUserRole serviceUserRole = serviceUserRoleService.getServiceUserRoleByUserId(user.getId());
                        if(serviceUserRole == null){
                            serviceUserRole = new ServiceUserRole();
                            serviceUserRole.setUserId(user.getId());
                            serviceUserRole.setCreateTime(new Date());
                            serviceUserRole.setCreateBy(user.getId());
                            serviceUserRoleService.insertServiceUserRole(serviceUserRole);
                        }
                    }
                });
                return SystemPropertiesConfig.CGJ_REDIRECT_URL_SUCCEED + token;
            }
            return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_412;
        } catch (Exception e) {
            logger.error("登陆访问异常", e);
            return SystemPropertiesConfig.CGJ_REDIRECT_URL_FAILURE + ResultConstant.LOGIN_FAILURE_413;
        }
    }

    /**
     * 清除指定帐户的AuthorizationInfo缓存条目。
     * @return
     */
    @PostMapping(value = "/clearCachedAuthorizationInfo")
    public String clearCachedAuthorizationInfo(){
        webShiroRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        return Result.success("成功").toJSON();
    }
}
