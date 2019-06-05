package com.ydc.dao.cgj.common;

import com.ydc.model.cgj.CfgFile;

public interface CfgFileDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CfgFile record);

    int insertSelective(CfgFile record);

    CfgFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CfgFile record);

    int updateByPrimaryKey(CfgFile record);
}