package com.ydc.cgj.ydhc.app.controller;

import com.ydc.commom.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/call")
public class CallController {

    /**
     * 咨询客服
     *
     * @return
     */
    @GetMapping("/consult")
    public String consult() {
        return Result.success().toJSON();
    }
}
