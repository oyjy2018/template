package com.ydc.service.sys.service;

import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.model.cgj.CgjCarzoneCfg;

import java.util.List;
import java.util.Map;

public interface CgjCarzoneCfgService {


    int deleteByPrimaryKey(Integer id);

    int insert(CgjCarzoneCfg cgjCarzoneCfg);

    int insertSelective(CgjCarzoneCfg cgjCarzoneCfg);

    CgjCarzoneCfg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CgjCarzoneCfg cgjCarzoneCfg);

    int updateByPrimaryKey(CgjCarzoneCfg cgjCarzoneCfg);
    /**
     * 获取车圈配置列表
     *
     * @param cgjCarzoneCfgQueDTO
     * @return
     */
    public List<Map<String, Object>> queryCarzoneCfgList(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO);

    /**
     * 获取车圈配置详情
     *
     * @param id
     * @return
     */
    public Map<String, Object> queryCarzoneCfgDetails(Integer id);


    public int deleteCarzoneCfg(CgjCarzoneCfg cgjCarzoneCfg);

    public int updateShowStatus(CgjCarzoneCfg cgjCarzoneCfg);

    int queryCarzoneCfgCount(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO);
}
