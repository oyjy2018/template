package com.ydc.service.loan.service.impl;

import com.ydc.dao.cgj.user.UserDao;
import com.ydc.model.cgj.User;
import com.ydc.service.loan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> queryUserList() {
        return userDao.queryAll();

    }
}
