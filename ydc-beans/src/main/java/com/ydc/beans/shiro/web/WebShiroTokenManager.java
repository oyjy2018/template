package com.ydc.beans.shiro.web;

import com.ydc.beans.config.ShiroPropertiesConfig;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * token管理工具
 *
 * @author:gongjin
 * @date：2017年6月21日 下午6:06:12
 */
@Component
public class WebShiroTokenManager {

    private static Logger logger = LogManager.getLogger(WebShiroTokenManager.class);

    /**
     * 登录
     * @param user
     * @return
     */
    public static void login(User user) {
        WebShiroToken token = new WebShiroToken(JsonUtil.gsonStr(user), user.getMobilePhone());
        SecurityUtils.getSubject().login(token);
    }

    /**
     * 获取token
     *
     * @param user
     * @return
     */
    public static String getToken(User user) {
        String token = SecurityUtils.getSubject().getSession().getId().toString();
        RedisUtil.getRedisTemplate().opsForHash().put(ShiroPropertiesConfig.SHIRO_TOKEN, token, user.getUserName() + ":" + user.getJobNumber() + ":" + DateUtil.format(new Date(), DateUtil.DATATIMEF_STR));
        return token;
    }

    /**
     * shiro管理用户
     * @return
     */
    public static User getUser(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        logger.info("subject:{},principal:{}", "登录会员", JsonUtil.gsonStr(principal));
        return  principal instanceof User ? (User) principal : null;
    }
}
