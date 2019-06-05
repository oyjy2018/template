package com.ydc.cgj.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.ydc.cgj.app.feignService.IMemberService;
import com.ydc.cgj.app.service.MemberService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private IMemberService memberService;

    @Override
    public Member memberLogin(String mobilePhone) {
        return memberService.memberLogin(mobilePhone);
    }

    @Override
    public Result memberRegister(MemberApplicationVO memberApplicationVO) {
        return memberService.memberRegister(memberApplicationVO);
    }

    @Override
    public Result memberUpdatePassword(Integer memberId, String mobilePhone, String password) {
        return memberService.memberUpdatePassword(memberId, mobilePhone, password);
    }

    @Override
    public Result checkMemberPassword(Integer memberId, String password) {
        return memberService.checkMemberPassword(memberId, password);
    }

    @Override
    public Result checkMobileIsRegister(String mobilePhone, Integer application) {
        return memberService.checkMobileIsRegister(mobilePhone, application);
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberService.getMemberById(memberId);
    }

    @Override
    public Integer updateWeixinInfoByMobilePhone(Member member) {
        return memberService.updateWeixinInfoByMobilePhone(member);
    }

    @Override
    public Member getMemberByOpenId(String openId) {
        return memberService.getMemberByOpenId(openId);
    }

}
