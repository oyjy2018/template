package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.user.MemberLoginDao;
import com.ydc.model.cgj.MemberLogin;
import com.ydc.service.user.service.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberLoginService")
public class MemberLoginServiceImpl implements MemberLoginService {

    @Autowired
    private MemberLoginDao memberLoginDao;

    @Override
    public Integer insertMemberLogin(MemberLogin memberLogin) {
        return memberLoginDao.insert(memberLogin);
    }
}
