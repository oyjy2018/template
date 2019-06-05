package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.user.OilPrice5DayDao;
import com.ydc.model.cgj.OilPrice5Day;
import com.ydc.service.user.service.OilPrice5DayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 油价查询
 *
 * @author Antoneo
 * @create 2018-09-08 16:03
 **/
@Service
public class OilPrice5DayServiceImpl implements OilPrice5DayService {

    @Resource
    OilPrice5DayDao oilPrice5DayDao;

    @Override
    public OilPrice5Day selectToday(String province, String city) {
        return oilPrice5DayDao.selectToday(province, city);
    }

    @Override
    public List<OilPrice5Day> getHistory(String province, String city) {
        return oilPrice5DayDao.getHistory(province, city);
    }
}
