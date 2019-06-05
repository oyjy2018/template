package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcLogLogin;

public interface YdhcLogLoginDao {
    int deleteByPrimaryKey(Integer id);

    int insert(YdhcLogLogin record);

    int insertSelective(YdhcLogLogin record);

    YdhcLogLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcLogLogin record);

    int updateByPrimaryKey(YdhcLogLogin record);
}