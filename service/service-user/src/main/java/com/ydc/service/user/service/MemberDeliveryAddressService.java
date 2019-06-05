package com.ydc.service.user.service;

import com.ydc.model.cgj.MemberDeliveryAddress;

public interface MemberDeliveryAddressService {

    /**
     * 添加会员地址
     *
     * @param memberDeliveryAddress
     * @return
     */
    Integer insertMemberDeliveryAddress(MemberDeliveryAddress memberDeliveryAddress);

    /**
     * 更新会员地址
     *
     * @param memberDeliveryAddress
     * @return
     */
    Integer updateDeliveryAddressService(MemberDeliveryAddress memberDeliveryAddress);

    /**
     * 查看会员地址
     *
     * @param memberId
     * @return
     */
    MemberDeliveryAddress getMemberDeliveryAddress(Integer memberId);
}
