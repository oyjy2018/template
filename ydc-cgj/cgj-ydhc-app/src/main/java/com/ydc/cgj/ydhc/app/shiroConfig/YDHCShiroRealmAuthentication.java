package com.ydc.cgj.ydhc.app.shiroConfig;

import com.ydc.beans.shiro.app.ShiroRealmAuthentication;
import com.ydc.cgj.ydhc.app.service.YdhcUserService;
import com.ydc.commom.constant.ShiroConstant;
import com.ydc.commom.util.MD5Util;
import com.ydc.model.ydhc.YdhcUser;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "shiroRealmAuthentication")
public class YDHCShiroRealmAuthentication implements ShiroRealmAuthentication {

    @Autowired
    private YdhcUserService ydhcUserService;

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        YdhcUser ydhcUser = ydhcUserService.getUserByMobilePhone(userName);
        //用户不存在
        if (ydhcUser == null) {
            throw new AuthenticationException(ShiroConstant.NO_MEMBER_FAILURE_LOGIN);
        }
        return new SimpleAuthenticationInfo(ydhcUser, MD5Util.toMd5(password), userName);
    }
}
