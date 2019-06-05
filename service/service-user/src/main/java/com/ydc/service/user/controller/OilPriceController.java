package com.ydc.service.user.controller;

import com.alibaba.fastjson.JSON;
import com.ydc.model.cgj.OilPrice5Day;
import com.ydc.service.user.service.OilPrice5DayService;
import com.ydc.service.user.service.impl.OilPrice5DayServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 油价查询
 *
 * @author Antoneo
 * @create 2018-09-08 16:06
 **/
@RestController
@RequestMapping(value = "/oil")
public class OilPriceController {

    @Resource
    OilPrice5DayServiceImpl oilPrice5DayService;

    /**
     * 查询今日油价
     *
     * @param province
     * @param city
     * @return
     */
    @RequestMapping(value = "/today", method = RequestMethod.POST)
    public Object getToday(@RequestParam String province, @RequestParam String city) {
        OilPrice5Day oilPrice5Day = oilPrice5DayService.selectToday(province, city);
        return JSON.toJSON(oilPrice5Day);
    }

    /**
     * 查询历史油价   近5天
     *
     * @param province
     * @param city
     * @return
     */
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public Object getOil5Day(@RequestParam String province, @RequestParam String city) {
        List<OilPrice5Day> oilPrice5DayList = oilPrice5DayService.getHistory(province, city);
        return JSON.toJSON(oilPrice5DayList);
    }
}
