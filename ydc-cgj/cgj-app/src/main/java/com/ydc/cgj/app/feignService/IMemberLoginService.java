package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.MemberLogin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "service-user")
public interface IMemberLoginService {

    /**
     * 添加会员登录记录
     *
     * @param memberLogin
     * @return
     */
    @PostMapping(value = "/memberLogin/memberLoginAdd", consumes = "application/json")
    Result memberLoginAdd(@RequestBody MemberLogin memberLogin);
}
