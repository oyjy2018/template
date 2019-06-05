package com.ydc.cgj.rental.company.app.shiroConfig;

import com.ydc.beans.shiro.app.ShiroRealmAuthentication;
import com.ydc.cgj.rental.company.app.service.CompanyCustomerService;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.MD5Util;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "shiroRealmAuthentication")
public class CompanyShiroRealmAuthentication implements ShiroRealmAuthentication {


    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        RentalCompanyCustomerVO rentalCompanyCustomerVO = JsonUtil.jsonToBean(userName, RentalCompanyCustomerVO.class);
        String password = String.valueOf(token.getPassword());
        return new SimpleAuthenticationInfo(rentalCompanyCustomerVO, MD5Util.toMd5(password), userName);
    }
}
