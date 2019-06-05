package com.ydc.cgj.bridge.feignService;

import com.ydc.model.cgj.SysErrorLogHttp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-user")
public interface SysErrorLogHttpFeignService {

    /**
     * 添加记录
     * @param sysErrorLogHttp
     * @return
     */
    @PostMapping("/sysErrorLogHttp/insert")
    Integer insert(@RequestBody SysErrorLogHttp sysErrorLogHttp);

    /**
     * 更新重试结果
     * @param id
     * @param result
     * @return
     */
    @PostMapping("/sysErrorLogHttp/updateRepResult")
    Integer updateRepResult(@RequestParam(value = "id") Integer id,
                            @RequestParam(value = "result") boolean result);
}
