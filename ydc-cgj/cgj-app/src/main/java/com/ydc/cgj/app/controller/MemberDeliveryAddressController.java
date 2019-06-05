package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.MemberDeliveryAddressService;
import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberDeliveryAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户收货地址
 */
@RestController
@RequestMapping("/memberDeliveryAddress")
public class MemberDeliveryAddressController {
    private static final Logger logger = LogManager.getLogger(MemberDeliveryAddressController.class);

    @Autowired
    private MemberDeliveryAddressService memberDeliveryAddressService;

    @PostMapping("/addMemberDeliveryAddress")
    public String addMemberDeliveryAddress(@RequestParam(value = "data") String data) {
        MemberDeliveryAddress memberDeliveryAddress = JSONObject.parseObject(data, MemberDeliveryAddress.class);
        memberDeliveryAddress.setUserId(SubjectUtil.getMemberId());
        logger.info("添加收货地址，address:{}", memberDeliveryAddress);
        return memberDeliveryAddressService.memberDeliveryAddressAdd(memberDeliveryAddress).toJSON();
    }

    @PostMapping("/updateMemberDeliveryAddress")
    public String updateMemberDeliveryAddress(@RequestParam(value = "data") String data) {
        MemberDeliveryAddress memberDeliveryAddress = JSONObject.parseObject(data, MemberDeliveryAddress.class);
        memberDeliveryAddress.setUserId(SubjectUtil.getMemberId());
        logger.info("更新收货地址，address:{}", memberDeliveryAddress);
        return memberDeliveryAddressService.memberDeliveryAddressUpdate(memberDeliveryAddress).toJSON();
    }

    @GetMapping("/getMemberDeliveryAddress")
    @SuppressWarnings("unchecked")
    public String getMemberDeliveryAddress() {
        return Result.success(memberDeliveryAddressService.memberDeliveryAddressGet(SubjectUtil.getMemberId())).toJSON();
    }
}
