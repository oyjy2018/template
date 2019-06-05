package com.ydc.beans.shiro.web;

import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * web登陆授权认证
 *
 * @author
 * @create 2018-12-03 13:50
 **/
@Component
public class WebShiroRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(WebShiroRealm.class);


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User)SecurityUtils.getSubject().getPrincipal();
        logger.info("登录验证后进行权限认证....");
        logger.info("user:{}",JsonUtil.gsonStr(user));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole(String.valueOf(user.getRoleId()));
        authorizationInfo.setStringPermissions(ShiroAuthUtil.stringPermissions(user.getRoleId(),user.getServiceIdentifying()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("登录操作进行登录认证......");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        User user = JsonUtil.jsonToBean(userName, User.class);
        //设置盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUserName());
        //                               加密类型   加密资源   盐值加密   加密次数
        Object md = new SimpleHash("MD5", user.getMobilePhone(),salt, 1024);

        return new SimpleAuthenticationInfo(user, md, salt, userName);
    }

    //清空权限缓存
    public void clearCachedAuthorizationInfo(PrincipalCollection principals){
        super.clearCachedAuthorizationInfo(principals);
    }
}
