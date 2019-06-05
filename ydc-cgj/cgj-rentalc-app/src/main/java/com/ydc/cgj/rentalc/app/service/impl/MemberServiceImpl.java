package com.ydc.cgj.rentalc.app.service.impl;

import com.ydc.cgj.rentalc.app.feignService.MemberFeignService;
import com.ydc.cgj.rentalc.app.service.MemberService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Override
    public Member memberLogin(String mobilePhone) {
        return memberFeignService.memberLogin(mobilePhone);
    }

    @Override
    public Result memberRegister(MemberApplicationVO memberApplicationVO) {
        return memberFeignService.memberRegister(memberApplicationVO);
    }

    @Override
    public Result memberUpdatePassword(Integer memberId, String mobilePhone, String password) {
        return memberFeignService.memberUpdatePassword(memberId, mobilePhone, password);
    }

    @Override
    public Result checkMemberPassword(Integer memberId, String password) {
        return memberFeignService.checkMemberPassword(memberId, password);
    }

    @Override
    public Result checkMobileIsRegister(String mobilePhone, Integer application) {
        return memberFeignService.checkMobileIsRegister(mobilePhone, application);
    }

    @Override
    public List<MemberFile> getMemberFileByMemberIdAndType(Integer memberId, String types) {
        return memberFeignService.getMemberFileByMemberIdAndType(memberId, types);
    }
}
