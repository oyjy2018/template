package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IMemberDeliveryAddressService;
import com.ydc.cgj.app.service.MemberDeliveryAddressService;
import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberDeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDeliveryAddressServiceImpl implements MemberDeliveryAddressService {

    @Autowired
    private IMemberDeliveryAddressService memberDeliveryAddressService;

    @Override
    public Result memberDeliveryAddressAdd(MemberDeliveryAddress memberDeliveryAddress) {
        return memberDeliveryAddressService.memberDeliveryAddressAdd(memberDeliveryAddress);
    }

    @Override
    public Result memberDeliveryAddressUpdate(MemberDeliveryAddress memberDeliveryAddress) {
        return memberDeliveryAddressService.memberDeliveryAddressUpdate(memberDeliveryAddress);
    }

    @Override
    public MemberDeliveryAddress memberDeliveryAddressGet(Integer memberId) {
        return memberDeliveryAddressService.memberDeliveryAddressGet(memberId);
    }
}
