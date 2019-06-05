package com.ydc.cgj.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 油价查询
 *
 * @author Antoneo
 * @create 2018-09-08 16:21
 **/
@Service
@FeignClient(value = "service-user")
public interface OilPrice5DayService {

    @RequestMapping(value = "/oil/today", method = RequestMethod.POST)
    Object getToday(@RequestParam(name = "province") String province, @RequestParam(name = "city") String city);

    @RequestMapping(value = "/oil/history", method = RequestMethod.POST)
    Object getHistory(@RequestParam(name = "province") String province, @RequestParam(name = "city") String city);
}
