package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.MemberRollDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "service-user")
public interface IMemberRollService {

    /**
     * 根据type获取用户券列表
     * @param memberRollDto
     * @return
     */
    @PostMapping(value = "/memberRoll/getMemberRoll")
    Result getMemberRoll(@RequestBody MemberRollDto memberRollDto);
}
