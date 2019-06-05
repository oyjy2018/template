package com.ydc.service.user.controller;

import com.ydc.model.cgj.SysErrorLogHttp;
import com.ydc.service.user.service.SysErrorLogHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sysErrorLogHttp")
public class SysErrorLogHttpController {

    @Autowired
    private SysErrorLogHttpService sysErrorLogHttpService;

    @PostMapping(value = "/insert")
    public Integer insert(@RequestBody SysErrorLogHttp sysErrorLogHttp){
        return sysErrorLogHttpService.insert(sysErrorLogHttp);
    }

    /**
     * 更新重试结果
     * @param id
     * @param result
     * @return
     */
    @PostMapping(value = "/updateRepResult")
    public Integer updateRepResult(@RequestParam(value = "id") Integer id,
                            @RequestParam(value = "result") boolean result){
        return sysErrorLogHttpService.updateRepResult(id, result);
    }
}
