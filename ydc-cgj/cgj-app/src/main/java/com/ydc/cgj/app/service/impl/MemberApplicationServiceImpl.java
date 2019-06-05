package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IMemberApplicationService;
import com.ydc.cgj.app.service.MemberApplicationService;
import com.ydc.model.cgj.MemberApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberApplicationServiceImpl implements MemberApplicationService {

    @Autowired
    private IMemberApplicationService iMemberApplicationService;

    @Override
    public MemberApplication getMemberApplicationByMobilePhone(String mobilePhone, Integer application) {
        return iMemberApplicationService.getMemberApplicationByMobilePhone(mobilePhone, application);
    }

    @Override
    public MemberApplication getMemberApplicationByLoginName(String loginName, Integer application) {
        return iMemberApplicationService.getMemberApplicationByLoginName(loginName, application);
    }
}
