package com.ydc.cgj.web.controller;

import com.ydc.cgj.web.service.OilPrice5DayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 油价查询
 *
 * @author Antoneo
 * @create 2018-09-08 17:28
 **/
@RestController
@RequestMapping(value = "/oil")
public class OilPriceController {

    @Resource
    OilPrice5DayService oilPrice5DayService;

    @RequestMapping(value = "/today", method = RequestMethod.POST)
    public Object getToday(@RequestParam String province, @RequestParam String city) {
        Object obj = oilPrice5DayService.getToday(province, city);
        return obj;
    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public Object getHistory(@RequestParam String province, @RequestParam String city) {
        Object obj = oilPrice5DayService.getHistory(province, city);
        return obj;
    }
}
