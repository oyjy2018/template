package com.ydc.cgj.rentalc.app.common;

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
        return (Member) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户id
     * @return
     */
    public static Integer getMemberId(){
        Member member = getMember();
        return member == null ? null : member.getId();
    }
}
