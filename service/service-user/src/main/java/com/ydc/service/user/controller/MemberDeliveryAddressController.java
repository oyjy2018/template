package com.ydc.service.user.controller;

import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberDeliveryAddress;
import com.ydc.service.user.service.MemberDeliveryAddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/memberDeliveryAddress")
public class MemberDeliveryAddressController {
    private final Logger logger = LogManager.getLogger(MemberDeliveryAddressController.class);

    @Autowired
    private MemberDeliveryAddressService memberDeliveryAddressService;

    /**
     * 添加用户收货地址
     * @param memberDeliveryAddress
     * @return
     */
    @PostMapping("/memberDeliveryAddressAdd")
    public Result memberDeliveryAddressAdd(@RequestBody MemberDeliveryAddress memberDeliveryAddress) {
        logger.info("添加用户收货地址, memberDeliveryAddress: " + memberDeliveryAddress);
        memberDeliveryAddress.setStatus(CodeConstant.NORMAL_STATUS);
        memberDeliveryAddress.setCreateTime(new Date());
        memberDeliveryAddress.setUpdateTime(new Date());
        return memberDeliveryAddressService.insertMemberDeliveryAddress(memberDeliveryAddress) <= 0 ? Result.failure() : Result.success();
    }

    /**
     * 更新用户收货地址
     * @param memberDeliveryAddress
     * @return
     */
    @PostMapping("/memberDeliveryAddressUpdate")
    public Result memberDeliveryAddressUpdate(@RequestBody MemberDeliveryAddress memberDeliveryAddress) {
        logger.info("更新用户收货地址, memberDeliveryAddress: " + memberDeliveryAddress);
        return memberDeliveryAddressService.updateDeliveryAddressService(memberDeliveryAddress) < 0 ? Result.failure() : Result.success();
    }

    /**
     * 获取用户收货地址
     * @param memberId
     * @return
     */
    @PostMapping(value = "/memberDeliveryAddressGet")
    public MemberDeliveryAddress memberDeliveryAddressGet(@RequestBody Integer memberId) {
        logger.info("获取用户收货地址, memberId: " + memberId);
        return memberDeliveryAddressService.getMemberDeliveryAddress(memberId);
    }
}
