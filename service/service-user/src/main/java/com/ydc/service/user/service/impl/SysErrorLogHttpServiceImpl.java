package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.common.SysErrorLogHttpDao;
import com.ydc.model.cgj.SysErrorLogHttp;
import com.ydc.service.user.service.SysErrorLogHttpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysErrorLogHttpServiceImpl implements SysErrorLogHttpService {

    @Resource
    private SysErrorLogHttpDao sysErrorLogHttpDao;

    @Override
    public Integer insert(SysErrorLogHttp sysErrorLogHttp) {
        return sysErrorLogHttpDao.insert(sysErrorLogHttp);
    }

    @Override
    public Integer updateRepResult(Integer id, boolean result) {
        return sysErrorLogHttpDao.updateRepResult(id, result);
    }
}
