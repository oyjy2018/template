package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberDeliveryAddress;

public interface MemberDeliveryAddressService {

    /**
     * 添加地址
     *
     * @param memberDeliveryAddress
     * @return
     */
    Result memberDeliveryAddressAdd(MemberDeliveryAddress memberDeliveryAddress);

    /**
     * 更新地址
     *
     * @param memberDeliveryAddress
     * @return
     */
    Result memberDeliveryAddressUpdate(MemberDeliveryAddress memberDeliveryAddress);

    /**
     * 获取地址
     *
     * @param memberId
     * @return
     */
    MemberDeliveryAddress memberDeliveryAddressGet(Integer memberId);
}
