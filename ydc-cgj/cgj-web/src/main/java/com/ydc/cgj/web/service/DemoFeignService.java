package com.ydc.cgj.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign 直接定义接口
 */

@Service
@FeignClient(value = "service-user")   //里面填写的是需要调用的服务名
public interface DemoFeignService {

    @RequestMapping(value = "/service/demo/print", method = RequestMethod.GET)
    String printService(@RequestParam(value = "name") String name);

}
