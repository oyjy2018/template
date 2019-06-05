package com.ydc.cgj.rentalb.app.common;

import com.ydc.model.cgj.User;
import org.apache.shiro.SecurityUtils;


/**
 * 获取登录用户信息
 *
 * @author
 * @create 2018-11-22 14:33
 **/
public class UserUtil {

    /**
     * 获取登录对象
     * @return
     */
    public static User getUser(){
        Object obj = SecurityUtils.getSubject().getPrincipal();
        return obj instanceof User ? (User) obj : null;
    }
}
