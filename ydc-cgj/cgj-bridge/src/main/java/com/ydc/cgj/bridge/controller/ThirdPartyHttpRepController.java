package com.ydc.cgj.bridge.controller;

import com.ydc.cgj.bridge.mq.service.ThirdPartyMessageConsumeService;
import com.ydc.cgj.bridge.service.SysErrorLogHttpService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.model.cgj.SysErrorLogHttp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 第三方http请求异常重试
 */
@RestController
@RequestMapping(value = "/thirdParty")
public class ThirdPartyHttpRepController {
    private final Logger logger = LogManager.getLogger(ThirdPartyHttpRepController.class);

    @Autowired
    private SysErrorLogHttpService sysErrorLogHttpService;

    @PostMapping(value = "/preHttp/{methodName}")
    public String preHttp(@RequestBody SysErrorLogHttp sysErrorLogHttp,
                          @PathVariable("methodName") String methodName){
        logger.info("subject: {}, sysErrorLogHttp: {}, methodName: {}", "第三方http请求异常重试", sysErrorLogHttp, methodName);
        if (sysErrorLogHttp == null){
            return Result.failure("参数错误").toJSON();
        }
        final byte[] content = sysErrorLogHttp.getRequestParam() == null ? null : sysErrorLogHttp.getRequestParam().getBytes();
        boolean result = ((ThirdPartyMessageConsumeService)ServiceFactoryUtil.getBean(methodName)).consumeMessage(content, true);
        sysErrorLogHttpService.updateRepResult(sysErrorLogHttp.getId(), result);
        return result ? Result.success("重试成功").toJSON() : Result.failure("重试失败").toJSON();
    }
}
