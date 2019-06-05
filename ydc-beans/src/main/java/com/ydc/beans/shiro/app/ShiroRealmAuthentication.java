package com.ydc.beans.shiro.app;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * shiro 认证接口
 */
public interface ShiroRealmAuthentication {

    /**
     * 登录认证
     * @return
     */
    default AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken){
        return null;
    }

    default AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        return null;
    }
}
