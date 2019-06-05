package com.ydc.beans.shiro.app;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 自定义授权和登录
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private ShiroRealmAuthentication shiroRealmAuthentication;

    /**
     * 登录认证
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        return shiroRealmAuthentication.doGetAuthenticationInfo(authcToken);
    }

    /**
     * 权限认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return shiroRealmAuthentication.doGetAuthorizationInfo(principals);
    }
}
