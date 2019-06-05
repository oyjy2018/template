package com.ydc.beans.shiro.app;

import com.ydc.beans.config.ShiroPropertiesConfig;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.shiro.ShiroUtil;
import com.ydc.commom.constant.ShiroConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;

import java.io.Serializable;

public class MyRedisSessionDAO extends RedisSessionDAO {

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session instanceof MyShiroSession) {
            //如果只是更新最后访问时间，且有效时间大于一天
            if (!((MyShiroSession) session).isChanged() &&
                    RedisUtil.getExpireTimeType(getKeyPrefix() + session.getId()) > 24 * 60 * 60L) {
                return;
            }
            //默认应该是不需要更新redis的，只有更新其他字段在更新缓存
            ((MyShiroSession) session).setChanged(false);
        }
        super.update(session);
        ShiroUtil.saveUserNameSessionId(session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId =  super.doCreate(session);
        ShiroUtil.saveUserNameSessionId(session);
        return sessionId;
    }
}
