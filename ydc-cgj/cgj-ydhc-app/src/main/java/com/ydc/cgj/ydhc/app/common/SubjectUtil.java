package com.ydc.cgj.ydhc.app.common;

import com.ydc.model.cgj.Member;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * shiro subject工具类
 */
public class SubjectUtil {

    /**
     * 获取用户对象
     * @return
     */
    public static Member getMember() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        return principal instanceof Member ? (Member) principal : null;
    }
}
