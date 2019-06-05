package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberLogin;

public interface MemberLoginService {

    /**
     * 添加会员登录记录
     *
     * @param memberLogin
     * @return
     */
    Result memberLoginAdd(MemberLogin memberLogin);
}
