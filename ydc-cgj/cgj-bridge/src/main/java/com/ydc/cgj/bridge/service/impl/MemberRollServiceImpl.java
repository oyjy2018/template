package com.ydc.cgj.bridge.service.impl;

import com.ydc.cgj.bridge.feignService.MemberRollFeignService;
import com.ydc.cgj.bridge.service.MemberRollService;
import com.ydc.commom.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberRollServiceImpl implements MemberRollService {

    @Autowired
    private MemberRollFeignService memberRollFeignService;

    @Override
    public Result batchUpdateMemberRollStatus(List<String> rollCodes, Integer status, List<Integer> loanIds) {
        return memberRollFeignService.batchUpdateMemberRollStatus(rollCodes, status, loanIds);
    }

    @Override
    public Result sendRoll(String memberInfos, String rollTypes, String params) {
        return memberRollFeignService.sendRoll(memberInfos, rollTypes, params);
    }
}
