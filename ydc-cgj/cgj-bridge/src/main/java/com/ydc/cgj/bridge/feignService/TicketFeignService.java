package com.ydc.cgj.bridge.feignService;

import com.ydc.commom.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "service-store")
public interface TicketFeignService {

    /**
     * 批量更新空券状态
     * @param rollCodes
     * @param status
     * @return
     */
    @PostMapping(value = "/ticket/batchUpdateTicketStatus")
    Result batchUpdateBlankRollStatus(@RequestParam("rollCodes") List<String> rollCodes, @RequestParam("status") Integer status);
}
