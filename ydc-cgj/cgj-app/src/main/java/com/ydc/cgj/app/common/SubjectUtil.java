package com.ydc.cgj.app.common;

import com.ydc.model.cgj.Member;
import org.apache.shiro.SecurityUtils;

/**
 * shiro subject工具类
 */
public class SubjectUtil {

    /**
     * 获取用户对象
     *
     * @return
     */
    public static Member getMember() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        return principal instanceof Member ? (Member) principal : null;
    }

    /**
     * 获取用户id
     * @return
     */
    public static Integer getMemberId(){
        Member member = getMember();
        return member == null ? null : member.getId();
    }

    /**
     * 获取用户姓名
     * @return
     */
    public static String getMemberName(){
        Member member = getMember();
        return member == null ? null : member.getMemberName();
    }
}
