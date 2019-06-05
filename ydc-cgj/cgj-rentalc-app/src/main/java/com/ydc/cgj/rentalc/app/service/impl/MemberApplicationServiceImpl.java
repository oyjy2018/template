package com.ydc.cgj.rentalc.app.service.impl;

import com.ydc.cgj.rentalc.app.feignService.MemberApplicationFeignService;
import com.ydc.cgj.rentalc.app.service.MemberApplicationService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.MemberApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberApplicationServiceImpl implements MemberApplicationService {

    @Autowired
    private MemberApplicationFeignService memberApplicationFeignService;

    @Override
    public MemberApplication getMemberApplicationByMobilePhone(String mobilePhone, Integer application) {
        return memberApplicationFeignService.getMemberApplicationByMobilePhone(mobilePhone, application);
    }

    @Override
    public MemberApplication getMemberApplicationByLoginName(String loginName, Integer application) {
        return memberApplicationFeignService.getMemberApplicationByLoginName(loginName, application);
    }

    @Override
    public Result checkIdentityCertificate(MemberApplicationVO memberApplicationVO) {
        return memberApplicationFeignService.checkIdentityCertificate(memberApplicationVO);
    }

    @Override
    public Result checkDriversLicenseCertificate(MemberApplicationVO memberApplicationVO) {
        return memberApplicationFeignService.checkDriversLicenseCertificate(memberApplicationVO);
    }

    @Override
    public Result certificateMember(MemberApplicationVO memberApplicationVO, int isIdentity) {
        return memberApplicationFeignService.certificateMember(memberApplicationVO, isIdentity);
    }
}
