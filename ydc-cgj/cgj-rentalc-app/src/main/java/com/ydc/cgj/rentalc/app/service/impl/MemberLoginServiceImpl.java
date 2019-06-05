package com.ydc.cgj.rentalc.app.service.impl;

import com.ydc.cgj.rentalc.app.feignService.MemberLoginFeignService;
import com.ydc.cgj.rentalc.app.service.MemberLoginService;
import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginServiceImpl implements MemberLoginService {

    @Autowired
    private MemberLoginFeignService memberLoginFeignService;

    @Override
    public Result memberLoginAdd(MemberLogin memberLogin) {
        return memberLoginFeignService.memberLoginAdd(memberLogin);
    }
}
