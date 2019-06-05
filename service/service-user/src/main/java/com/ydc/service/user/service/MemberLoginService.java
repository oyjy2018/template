package com.ydc.service.user.service;

import com.ydc.model.cgj.MemberLogin;

public interface MemberLoginService {

    /**
     * 添加会员登录记录
     *
     * @param memberLogin
     * @return
     */
    Integer insertMemberLogin(MemberLogin memberLogin);
}
