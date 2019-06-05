package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.user.LogLoginDao;
import com.ydc.model.cgj.LogLogin;
import com.ydc.service.user.service.LogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gongjin
 * @create 2018-09-26 16:13
 **/
@Service
public class LogLoginServiceImpl implements LogLoginService {

    @Autowired
    LogLoginDao logLoginDao;


    @Override
    public Integer insert(LogLogin record) {
        return logLoginDao.insert(record);
    }
}
