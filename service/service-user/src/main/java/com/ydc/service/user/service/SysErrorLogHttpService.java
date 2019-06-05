package com.ydc.service.user.service;

import com.ydc.model.cgj.SysErrorLogHttp;

public interface SysErrorLogHttpService {

    Integer insert(SysErrorLogHttp sysErrorLogHttp);

    /**
     * 更新重试结果
     * @param id
     * @param result
     * @return
     */
    Integer updateRepResult(Integer id, boolean result);
}
