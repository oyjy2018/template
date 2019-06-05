package com.ydc.service.user.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ydc.commom.constant.DdConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.model.cgj.Dd;
import com.ydc.service.user.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 */

@RestController
@RequestMapping("/service/demo")
public class DemoServiceController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/print")
    public String printPort(@RequestParam(value = "name", defaultValue = "system") String name) {
        return name;
    }

/*    @ResponseBody
    @RequestMapping("/userapp")
    public String  queryUserAppList(){
        List<UserApp> list=demoService.queryUserAppList();
        return Result.success(list).toJSON();
    }*/

}
