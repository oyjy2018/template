package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.mq.sms.service.SMSSendMessageService;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.security.rsa.RSAUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class DemoController {
    protected static final Logger logger = LogManager.getLogger(DemoController.class);

    @Autowired
    SMSSendMessageService sMSSendMessageService;

    /**
     * 获取指定字典表数据
     *
     * @param data
     * @return
     */
    @GetMapping(value = "/get")
    public String get(@RequestParam("data") String data) {
        String string;
        try {
            string=new String(Base64.getUrlDecoder().decode(data));
        }catch (Exception IllegalArgumentException){
            string=data;
        }
        return Result.success(string).toJSON();
    }

    @PostMapping(value = "/post")
    public String post(@RequestParam("data") String data) {
        return Result.success(data).toJSON();
    }

    @PostMapping(value = "/post2")
    public String post2(@RequestParam("data") String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        return Result.success(data).toJSON();
    }

    @PutMapping(value = "/put")
    public String put(@RequestParam("data") String data) {
        return Result.success(data).toJSON();
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestParam("data") String data) {
        return Result.success(data).toJSON();
    }

    @PostMapping(value = "/rsa")
    public String rsa(@RequestParam("data") String data){
        String privateKey = RedisUtil.redisGet(RedisConstant.RAS_PRIVATE_KEY).toString();
        String publicKey= RedisUtil.redisGet(RedisConstant.RAS_PUBLIC_KEY).toString();
        Map<String,Object> data1 = new HashMap<>();
        data1.put("privateKey",privateKey);
        data1.put("publicKey",publicKey);
        try {
            RSAUtil.demo(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtil.gsonStr(data1);

    }

}
