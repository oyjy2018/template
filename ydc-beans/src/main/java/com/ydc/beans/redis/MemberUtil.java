package com.ydc.beans.redis;

import com.ydc.commom.constant.ShiroConstant;

import java.util.Set;

public class MemberUtil {

    /**
     * 会员下线（车管家）
     * @param mobilePhone
     */
    public static void memberLogout(String mobilePhone){
        memberLogout(mobilePhone, ShiroConstant.CGJ_SHIRO_USER_REDIS_KEY, ShiroConstant.CGJ_SHIRO_REDIS_KEY);
    }

    /**
     * 会员下线
     * @param mobilePhone
     * @param shiroUserKey
     * @param shiroKey
     */
    public static void memberLogout(String mobilePhone, String shiroUserKey, String shiroKey){
        if (mobilePhone != null){
            Set<Object> sessionIds = RedisUtil.redisSetGet(shiroUserKey + mobilePhone);
            if (sessionIds != null && sessionIds.size() > 0){
                sessionIds.forEach(sessionId ->
                        RedisUtil.remove(shiroKey + sessionId));
            }
            RedisUtil.remove(shiroUserKey + mobilePhone);
        }
    }
}
