package com.ydc.service.order.service;

import com.ydc.model.cgj.Member;

/**
 * 会员信息
 *
 * @author
 * @create 2018-11-22 16:57
 **/
public interface MemberService {

    /**
     * 查询会员
     * @param idCard
     * @return
     */
    Member getMemberByIdCard(String idCard);

    /**
     * 查询会员
     * @param memberId
     * @return
     */
    Member getMemberById(Integer memberId);

}
