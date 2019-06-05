package com.ydc.service.user.service.impl;


import com.ydc.dao.cgj.user.UserDao;
import com.ydc.model.cgj.User;
import com.ydc.service.user.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Value("${server.port}")
    private String port;

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> queryUserList() {
        return userDao.queryAll();
    }
/*
    @Override
    public List<RcsUser> queryUserAppList() {
        return userAppDao.queryUserApp();
    }*/
}
