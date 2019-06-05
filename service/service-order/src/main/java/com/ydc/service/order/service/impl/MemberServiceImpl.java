package com.ydc.service.order.service.impl;

import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.model.cgj.Member;
import com.ydc.service.order.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 * @create 2018-11-22 16:58
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    MemberDao memberDao;

    @Override
    public Member getMemberByIdCard(String idCard) {
        return memberDao.getMemberByIdCard(idCard);
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberDao.selectByPrimaryKey(memberId);
    }
}
