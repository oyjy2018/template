package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.service.DemoFeignHystrixService;
import org.springframework.stereotype.Component;


@Component
public class DemoFeignHystrixServiceImpl implements DemoFeignHystrixService {


    @Override
    public String printService(String name) {
        return "feign hystrix :" + name;
    }
}
