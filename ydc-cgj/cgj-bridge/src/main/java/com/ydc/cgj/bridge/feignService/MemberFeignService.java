package com.ydc.cgj.bridge.feignService;

import com.ydc.model.cgj.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "service-user")
public interface MemberFeignService {

    @PostMapping(value = "/member/updateWeixinInfoByMobilePhone")
    Integer updateWeixinInfoByMobilePhone(@RequestBody Member member);
}
