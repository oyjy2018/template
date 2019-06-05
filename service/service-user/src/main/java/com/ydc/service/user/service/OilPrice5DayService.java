package com.ydc.service.user.service;

import com.ydc.model.cgj.OilPrice5Day;

import java.util.List;

/**
 * 油价查询
 *
 * @author Antoneo
 * @create 2018-09-08 16:02
 **/
public interface OilPrice5DayService {
    /**
     * 获取今日油价
     *
     * @param province 省份
     * @param city     城市
     * @return
     */
    OilPrice5Day selectToday(String province, String city);

    /**
     * 获取历时油价信息
     *
     * @param province 省份
     * @param city     城市
     * @return
     */
    List<OilPrice5Day> getHistory(String province, String city);
}
