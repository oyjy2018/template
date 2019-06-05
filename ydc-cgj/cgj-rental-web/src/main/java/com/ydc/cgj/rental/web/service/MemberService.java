package com.ydc.cgj.rental.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;

/**
 * 会员信息
 *
 * @author
 * @create 2018-11-22 17:27
 **/
public interface MemberService {

    /**
     * 身份证查询会员信息
     * @param idCard
     * @return
     */
    Member getMemberByIdCard(String idCard);

    /**
     * 会员身份认证
     * @param member
     * @return
     */
    Result certificateIdentity(Member member);

    /**
     * 租车会员身份认证
     * @param memberApplicationVO
     * @return
     */
    Result rentalOrderMember(MemberApplicationVO memberApplicationVO);


    /**
     * 新增租车订，身份证认证通过，更新实名认证状态
     * @param memberApplicationVO
     * @return
     */
    Result updateMemberWhetherRealNameStatus( MemberApplicationVO memberApplicationVO);

    /**
     * 通过手机号查询会员姓名和身份证号
     * @param mobilePhone  手机号
     * @return
     */
    String getMemberNameAndIdCardByMobilePhone(String mobilePhone);
}
