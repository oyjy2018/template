package com.ydc.dao.cgj.user;

import com.ydc.model.cgj.OilPrice5Day;

import java.util.List;

/**
 * 近5天油价
 *
 * @author Antoneo
 * @create 2018-09-08 11:00
 **/
public interface OilPrice5DayDao {
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
