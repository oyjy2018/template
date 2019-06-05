package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberDeliveryAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "service-user")
public interface IMemberDeliveryAddressService {

    @PostMapping(value = "/memberDeliveryAddress/memberDeliveryAddressAdd", consumes = "application/json")
    Result memberDeliveryAddressAdd(@RequestBody MemberDeliveryAddress memberDeliveryAddress);

    @PostMapping(value = "/memberDeliveryAddress/memberDeliveryAddressUpdate", consumes = "application/json")
    Result memberDeliveryAddressUpdate(@RequestBody MemberDeliveryAddress memberDeliveryAddress);

    @PostMapping("/memberDeliveryAddress/memberDeliveryAddressGet")
    MemberDeliveryAddress memberDeliveryAddressGet(Integer memberId);
}
