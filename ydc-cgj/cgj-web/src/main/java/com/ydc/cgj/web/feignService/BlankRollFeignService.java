package com.ydc.cgj.web.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.BlankRollDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Service
@FeignClient(value = "service-store")
public interface BlankRollFeignService {

    /**
     * 获取空券列表
     * @param blankRollDto
     * @return
     */
    @PostMapping("/ticket/getBlankRollList")
    String getBlankRollList(@RequestBody BlankRollDto blankRollDto);

    /**
     * 刷新空券
     * @param rollCodes
     * @return
     */
    @PostMapping("/ticket/flushBlankRolls")
    String flushBlankRolls(@RequestParam(value = "rollCodes") List<String> rollCodes);
}
