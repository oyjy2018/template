package com.ydc.cgj.bridge.service.impl;

import com.ydc.cgj.bridge.feignService.SysErrorLogHttpFeignService;
import com.ydc.cgj.bridge.service.SysErrorLogHttpService;
import com.ydc.model.cgj.SysErrorLogHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysErrorLogHttpServiceImpl implements SysErrorLogHttpService {

    @Autowired
    private SysErrorLogHttpFeignService sysErrorLogHttpFeignService;

    @Override
    public Integer insert(SysErrorLogHttp sysErrorLogHttp) {
        return sysErrorLogHttpFeignService.insert(sysErrorLogHttp);
    }

    @Override
    public Integer updateRepResult(Integer id, boolean result) {
        return sysErrorLogHttpFeignService.updateRepResult(id, result);
    }
}
