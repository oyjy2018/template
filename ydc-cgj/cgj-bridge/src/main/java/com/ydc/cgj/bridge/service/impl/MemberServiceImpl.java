package com.ydc.cgj.bridge.service.impl;

import com.ydc.cgj.bridge.feignService.MemberFeignService;
import com.ydc.cgj.bridge.service.MemberService;
import com.ydc.model.cgj.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Override
    public Integer updateWeixinInfoByMobilePhone(Member member) {
        return memberFeignService.updateWeixinInfoByMobilePhone(member);
    }
}
