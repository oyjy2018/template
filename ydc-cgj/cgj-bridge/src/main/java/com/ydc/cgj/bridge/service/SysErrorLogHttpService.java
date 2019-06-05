package com.ydc.cgj.bridge.service;

import com.ydc.model.cgj.SysErrorLogHttp;

public interface SysErrorLogHttpService {

    /**
     * 添加记录
     * @param sysErrorLogHttp
     * @return
     */
    Integer insert(SysErrorLogHttp sysErrorLogHttp);

    /**
     * 更新重试结果
     * @param id
     * @param result
     * @return
     */
    Integer updateRepResult(Integer id, boolean result);
}
