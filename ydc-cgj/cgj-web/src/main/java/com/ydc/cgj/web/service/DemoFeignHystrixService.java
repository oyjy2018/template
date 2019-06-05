package com.ydc.cgj.web.service;


import com.ydc.cgj.web.service.impl.DemoFeignHystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-user", fallback = DemoFeignHystrixServiceImpl.class)
public interface DemoFeignHystrixService {
    @RequestMapping(value = "/service/demo/print", method = RequestMethod.GET)
    String printService(@RequestParam(value = "name") String name);
}
