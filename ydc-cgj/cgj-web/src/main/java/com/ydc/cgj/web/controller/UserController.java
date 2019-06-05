package com.ydc.cgj.web.controller;

import com.ydc.cgj.web.service.UserService;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 *
 * @author gongjin
 * @create 2018-09-06 10:01
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/updateUser")
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping(value = "/getUserByLoginName")
    public String getUserByLoginName(@RequestParam(value = "loginName") String loginName) {
        return JsonUtil.gsonStr(userService.getUserByLoginName(loginName));
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody User user) {
        return userService.insert(user);
    }
}