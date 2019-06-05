package com.ydc.service.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberLogin;
import com.ydc.service.user.service.MemberLoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/memberLogin")
public class MemberLoginController {
    private Logger logger = LogManager.getLogger(MemberLoginController.class);

    @Autowired
    private MemberLoginService memberLoginService;

    /**
     * 添加用户登录记录
     * @param memberLogin
     * @return
     */
    @PostMapping(value = "/memberLoginAdd")
    public Result memberLoginAdd(@RequestBody MemberLogin memberLogin){
        logger.info("添加用户登录记录, memberLogin: " + memberLogin);
        return memberLoginService.insertMemberLogin(memberLogin) <= 0 ? Result.failure("添加失败，请稍后尝试") : Result.success();
    }
}
