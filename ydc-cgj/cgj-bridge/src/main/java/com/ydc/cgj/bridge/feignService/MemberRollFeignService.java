package com.ydc.cgj.bridge.feignService;

import com.ydc.commom.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
@FeignClient(value = "service-user")
public interface MemberRollFeignService {

    @PostMapping(value = "/memberRoll/batchUpdateMemberRollStatus")
    Result batchUpdateMemberRollStatus(@RequestParam(value = "rollCodes") List<String> rollCodes,
                                       @RequestParam(value = "status") Integer status,
                                       @RequestParam(value = "loanIds") List<Integer> loanIds);

    /**
     * 派券
     *
     * @param memberInfos
     * @param rollTypes
     * @return
     */
    @PostMapping(value = "/memberRoll/bridgeSendRoll")
    Result sendRoll(@RequestParam(value = "memberInfos") String memberInfos,
                    @RequestParam(value = "rollTypes") String rollTypes,
                    @RequestParam(value = "params") String params);
}
