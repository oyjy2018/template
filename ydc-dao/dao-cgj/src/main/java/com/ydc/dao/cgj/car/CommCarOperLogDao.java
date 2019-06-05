package com.ydc.dao.cgj.car;

import com.ydc.model.cgj.car.CommCarOperLog;

public interface CommCarOperLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CommCarOperLog record);

    int insertSelective(CommCarOperLog record);

    CommCarOperLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommCarOperLog record);

    int updateByPrimaryKeyWithBLOBs(CommCarOperLog record);

    int updateByPrimaryKey(CommCarOperLog record);
}