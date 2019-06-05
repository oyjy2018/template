package com.ydc.beans.shiro.web;

import com.ydc.beans.config.RedisPropertiesConfig;
import com.ydc.beans.redis.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;

import java.io.Serializable;

/**
 * @author
 * @create 2018-11-16 11:09
 **/
public class WebShiroRedisSessionDAO extends RedisSessionDAO {

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session instanceof WebShiroSession) {
            //如果只是更新最后访问时间，且有效时间30分钟
            if (!((WebShiroSession) session).isChanged() &&
                    RedisUtil.getExpireTimeType(getKeyPrefix() + session.getId()) > (30 * 60)) {
                return;
            }
            //默认应该是不需要更新redis的，只有更新其他字段在更新缓存
            ((WebShiroSession) session).setChanged(false);
        }
        super.update(session);
//        saveUserNameSessionId(session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId =  super.doCreate(session);
//        saveUserNameSessionId(session);
        return sessionId;
    }
}
