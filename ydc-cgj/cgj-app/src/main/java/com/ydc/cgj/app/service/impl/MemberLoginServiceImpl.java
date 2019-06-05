package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IMemberLoginService;
import com.ydc.cgj.app.service.MemberLoginService;
import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginServiceImpl implements MemberLoginService {

    @Autowired
    private IMemberLoginService memberLoginService;

    @Override
    public Result memberLoginAdd(MemberLogin memberLogin) {
        return memberLoginService.memberLoginAdd(memberLogin);
    }
}
