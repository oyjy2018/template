package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IMemberRollService;
import com.ydc.cgj.app.service.MemberRollService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.MemberRollDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRollServiceImpl implements MemberRollService {

    @Autowired
    private IMemberRollService memberRollService;

    @Override
    public Result getMemberRoll(MemberRollDto memberRollDto) {
        return memberRollService.getMemberRoll(memberRollDto);
    }
}
