package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.UserFeignService;
import com.ydc.cgj.rental.web.service.MemberService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-11-22 17:27
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    UserFeignService userFeignService;

    @Override
    public Member getMemberByIdCard(String idCard) {
        return userFeignService.getMemberByIdCard(idCard);
    }

    @Override
    public Result certificateIdentity(Member member) {
        return userFeignService.certificateIdentity(member);
    }

    @Override
    public Result rentalOrderMember(MemberApplicationVO memberApplicationVO) {
        return userFeignService.rentalOrderMember(memberApplicationVO);
    }

    @Override
    public Result updateMemberWhetherRealNameStatus(MemberApplicationVO memberApplicationVO) {
        return userFeignService.updateMemberWhetherRealNameStatus(memberApplicationVO);
    }

    /**
     * 通过手机号查询会员姓名和身份证号
     * @param mobilePhone  手机号
     * @return
     */
    @Override
    public String getMemberNameAndIdCardByMobilePhone(String mobilePhone) {
        return userFeignService.getMemberNameAndIdCardByMobilePhone(mobilePhone);
    }
}
