package com.ydc.cgj.web.controller;


import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.web.constant.Constants;
import com.ydc.cgj.web.service.DemoFeignHystrixService;
import com.ydc.cgj.web.service.DemoFeignService;
import com.ydc.cgj.web.service.DictionaryDetailService;
import com.ydc.cgj.web.service.UserService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private DemoFeignService demoFeignService;

    @Autowired
    private DemoFeignHystrixService demoFeignHystrixService;

    @Autowired
    private DictionaryDetailService dictionaryDetailService;


    @GetMapping("/redis")
    public String redisTest(@RequestParam("parentDictCode") String parentDictCode) {
        //RedisUtil.redisGet(SystemUtil.REDIS_DICTIONARY_KEY);
//        List<DictionaryDetail> dictionaryDetails = dictionaryDetailService.getConfigInfoByParentDictCode(parentDictCode);
        Optional<List<DictionaryDetail>> dictionaryDetails = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(parentDictCode)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(parentDictCode)));
        return JsonUtil.gsonStr(dictionaryDetails.get());
    }


    @RequestMapping("/print")
    public String printPort(@RequestParam(value = "parentDictCode") String parentDictCode, @RequestParam(value = "dictKey") String dictKey) {
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(dictKey,parentDictCode)
                .orElseGet(() -> dictionaryDetailService.getDictionaryDetailByDictKey(dictKey, parentDictCode));
        return JsonUtil.gsonStr(dictionaryDetail);
    }

    @ResponseBody
    @RequestMapping("/print/hystrix")
    public String printPortHystrix(@RequestParam(value = "parentDictCode") String parentDictCode, @RequestParam(value = "dictKey") String dictKey) {
        Optional<DictionaryDetail> dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(dictKey, parentDictCode);
        return null;
    }

    @ResponseBody
    @RequestMapping("/print/feign")
    public String printPortFeign(@RequestParam(value = "name", defaultValue = "system") String name) {
        return demoFeignService.printService(name);
    }

    @ResponseBody
    @RequestMapping("/print/feign/hystrix")
    public String printPortFeignHystrix(@RequestParam(value = "name", defaultValue = "system") String name) {
        return demoFeignHystrixService.printService(name);
    }

    @GetMapping(value = "/jsonFormat")
    public String jsonFormat(){
        User user = userService.getUserByMobilePhoneNoStatus("18811879947");
        return JsonUtil.gsonStr(user);
    }
}
