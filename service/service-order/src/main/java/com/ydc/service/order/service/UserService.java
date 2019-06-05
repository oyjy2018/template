package com.ydc.service.order.service;

import com.ydc.model.cgj.User;

import java.util.List;

/**
 * 用户
 *
 * @author
 * @create 2018-11-23 15:59
 **/
public interface UserService {

    /**
     * 返回所有有效用户
     * @return
     */
    List<User> getValidUser();
}
