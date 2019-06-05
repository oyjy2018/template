package com.ydc.cgj.app.shiroConfig;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.shiro.app.ShiroRealmAuthentication;
import com.ydc.cgj.app.service.MemberApplicationService;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.constant.ShiroConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.MD5Util;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.MemberApplication;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "shiroRealmAuthentication")
public class CGJShiroRealmAuthentication implements ShiroRealmAuthentication {

    @Autowired
    private MemberApplicationService memberApplicationService;

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        MemberApplication memberApplication = memberApplicationService.getMemberApplicationByMobilePhone(userName, MemberConstant.APPLICATION_CGJ);

        //用户不存在
        if (memberApplication == null) {
            throw new AuthenticationException(ShiroConstant.NO_MEMBER_FAILURE_LOGIN);
        }
        //用户已注销
        if (memberApplication.getMemberStatus() == MemberConstant.MEMBER_LOGOFF_STATUS){
            throw new AuthenticationException(ShiroConstant.MEMBER_LOGOFF_FAILURE_LOGIN);
        }
        //用户已锁定
        if (memberApplication.getMemberStatus() == MemberConstant.MEMBER_LOCKED_STATUS){
            throw new AuthenticationException(ShiroConstant.MEMBER_LOCKED_FAILURE_LOGIN);
        }
        //密码不正确
        if (!"".equals(password) && !MD5Util.getPassword(memberApplication.getMemberId(), password).equals(memberApplication.getMemberPassword())) {
            //添加登录失败次数
            RedisUtil.generate(RedisConstant.CGJ_PASSWORD_FAULT_KEY.concat(userName), DateUtil.getTomorrow());
            throw new AuthenticationException(ShiroConstant.PASSWORD_FAILURE_LOGIN);
        }
        MemberApplicationVO memberApplicationVO = new MemberApplicationVO();
        memberApplicationVO.setId(memberApplication.getMemberId());
        memberApplicationVO.setMemberLoginName(memberApplication.getMemberLoginName());
        memberApplicationVO.setMobilePhone(memberApplication.getMemberPhone());
        memberApplicationVO.setApplication(memberApplication.getApplication());
        memberApplicationVO.setPassword(memberApplication.getMemberPassword());
        memberApplicationVO.setStatus(memberApplication.getMemberStatus());
        return new SimpleAuthenticationInfo(memberApplicationVO, MD5Util.toMd5(password), userName);
    }
}
