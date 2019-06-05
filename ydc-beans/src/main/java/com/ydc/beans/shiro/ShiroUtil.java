package com.ydc.beans.shiro;

import com.ydc.beans.config.ShiroPropertiesConfig;
import com.ydc.beans.redis.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

public class ShiroUtil {

    /**
     * 添加用户和sessionId的关系
     * @param session
     */
    public static void saveUserNameSessionId(Session session){
        Object object = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (object != null){
            SimplePrincipalCollection collection = ((SimplePrincipalCollection)object);
            RedisUtil.redisSetSet(
                    getShiroRedisKey(ShiroPropertiesConfig.SHIRO_USER, collection.getRealmNames().iterator().next()), session.getId());
        }
    }

    /**
     * 获取redis key
     * @param keyPrefix
     * @param keyContent
     * @return
     */
    public static String getShiroRedisKey(String keyPrefix, String keyContent){
        if (keyPrefix == null) return keyContent;
        if (keyContent == null) return keyPrefix;
        return keyPrefix + keyContent;
    }
}
