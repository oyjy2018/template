package com.ydc.dao.cgj.common;

import com.ydc.model.cgj.common.Holiday;

import java.util.List;

public interface HolidayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Holiday record);

    int insertSelective(Holiday record);

    Holiday selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Holiday record);

    int updateByPrimaryKey(Holiday record);

    List<Holiday> getAll();
}