package com.ydc.service.order.service.impl;

import com.ydc.dao.cgj.user.UserDao;
import com.ydc.model.cgj.User;
import com.ydc.service.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-11-23 15:59
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getValidUser() {
        return userDao.getValidUser();
    }
}
