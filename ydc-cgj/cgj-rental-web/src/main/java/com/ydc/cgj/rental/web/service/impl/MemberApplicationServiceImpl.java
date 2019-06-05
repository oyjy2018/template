package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.MemberApplicationFeignService;
import com.ydc.cgj.rental.web.service.MemberApplicationService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberApplicationServiceImpl implements MemberApplicationService {

    @Autowired
    private MemberApplicationFeignService memberApplicationFeignService;

    @Override
    public Result getMemberApplicationList(MemberDTO memberDTO) {
        return memberApplicationFeignService.getMemberApplicationList(memberDTO);
    }

    @Override
    public String insertMember(MemberApplicationVO memberApplicationVO) {
        return memberApplicationFeignService.insertMember(memberApplicationVO);
    }

    @Override
    public String updateMemberStatus(MemberDTO memberDTO) {
        return memberApplicationFeignService.updateMemberStatus(memberDTO);
    }

    @Override
    public String updateMemberApplication(MemberApplicationVO memberApplicationVO) {
        return memberApplicationFeignService.updateMemberApplication(memberApplicationVO);
    }

    @Override
    public Result getMemberApplicationById(int memberId) {
        return memberApplicationFeignService.getMemberApplicationById(memberId);
    }
}
