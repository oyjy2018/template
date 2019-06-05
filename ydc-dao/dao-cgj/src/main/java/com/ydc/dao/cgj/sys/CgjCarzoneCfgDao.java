package com.ydc.dao.cgj.sys;

import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.model.cgj.CgjCarzoneCfg;

import java.util.List;
import java.util.Map;

public interface CgjCarzoneCfgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CgjCarzoneCfg record);

    int insertSelective(CgjCarzoneCfg record);

    CgjCarzoneCfg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CgjCarzoneCfg record);

    int updateByPrimaryKey(CgjCarzoneCfg record);

    List<Map<String, Object>> queryCarzoneCfgList(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO);

    Map<String, Object> queryCarzoneCfgDetails(Integer id);

    int deleteCarzoneCfg(CgjCarzoneCfg cgjCarzoneCfg);

    int updateShowStatus(CgjCarzoneCfg cgjCarzoneCfg);

    int queryCarzoneCfgCount(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO);
}