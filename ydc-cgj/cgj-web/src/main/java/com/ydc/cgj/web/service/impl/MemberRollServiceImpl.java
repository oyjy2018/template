package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.BlankRollFeignService;
import com.ydc.cgj.web.feignService.MemberRollFeignService;
import com.ydc.cgj.web.service.MemberRollService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.BlankRollDto;
import com.ydc.commom.view.dto.cgj.MemberRollDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberRollServiceImpl implements MemberRollService {

    @Autowired
    private MemberRollFeignService memberRollFeignService;

    @Autowired
    private BlankRollFeignService blankRollFeignService;

    @Override
    public Result getBlankRollCount() {
        return memberRollFeignService.getBlankRollCount();
    }

    @Override
    public Result checkSendingRoll(String memberInfos, String rollSizeList) {
        return memberRollFeignService.checkSendingRoll(memberInfos, rollSizeList);
    }

    @Override
    public Result sendRoll(String memberInfos, String rollTypes, String params) {
        return memberRollFeignService.sendRoll(memberInfos, rollTypes, params);
    }

    @Override
    public Result updateRollStatus(String rollCode, Integer rollStatus) {
        return memberRollFeignService.updateRollStatus(rollCode, rollStatus);
    }

    @Override
    public Result getMemberRollList(MemberRollDto memberRollDto) {
        return memberRollFeignService.getMemberRollList(memberRollDto);
    }

    @Override
    public String getBlankRollList(BlankRollDto blankRollDto) {
        return blankRollFeignService.getBlankRollList(blankRollDto);
    }

    @Override
    public Result sendAssignMemberRoll(List<String> rollCodes, String params) {
        return memberRollFeignService.sendAssignMemberRoll(rollCodes, params);
    }

    @Override
    public String flushBlankRolls(List<String> rollCodes) {
        return blankRollFeignService.flushBlankRolls(rollCodes);
    }
}
