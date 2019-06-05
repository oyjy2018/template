package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.user.MemberDeliveryAddressDao;
import com.ydc.model.cgj.MemberDeliveryAddress;
import com.ydc.service.user.service.MemberDeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDeliveryAddressServiceImpl implements MemberDeliveryAddressService {

    @Autowired
    private MemberDeliveryAddressDao memberDeliveryAddressDao;

    @Override
    public Integer insertMemberDeliveryAddress(MemberDeliveryAddress memberDeliveryAddress) {
        return memberDeliveryAddressDao.insert(memberDeliveryAddress);
    }

    @Override
    public Integer updateDeliveryAddressService(MemberDeliveryAddress memberDeliveryAddress) {
        return memberDeliveryAddressDao.updateByPrimaryKeySelective(memberDeliveryAddress);
    }

    @Override
    public MemberDeliveryAddress getMemberDeliveryAddress(Integer memberId) {
        return memberDeliveryAddressDao.selectAddressByMemberId(memberId);
    }
}
