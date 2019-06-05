package com.ydc.service.sys.service.impl;

import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.dao.cgj.sys.CgjCarzoneCfgDao;
import com.ydc.model.cgj.CgjCarzoneCfg;
import com.ydc.service.sys.service.CgjCarzoneCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CgjCarzoneCfgServiceImpl implements CgjCarzoneCfgService{

    @Autowired
    private CgjCarzoneCfgDao cgjCarzoneCfgDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cgjCarzoneCfgDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CgjCarzoneCfg cgjCarzoneCfg) {
        return cgjCarzoneCfgDao.insert(cgjCarzoneCfg);
    }

    @Override
    public int insertSelective(CgjCarzoneCfg cgjCarzoneCfg) {
        return cgjCarzoneCfgDao.insertSelective(cgjCarzoneCfg);
    }

    @Override
    public CgjCarzoneCfg selectByPrimaryKey(Integer id) {
        return cgjCarzoneCfgDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CgjCarzoneCfg cgjCarzoneCfg) {
        return cgjCarzoneCfgDao.updateByPrimaryKeySelective(cgjCarzoneCfg);
    }

    @Override
    public int updateByPrimaryKey(CgjCarzoneCfg cgjCarzoneCfg) {
        return cgjCarzoneCfgDao.updateByPrimaryKey(cgjCarzoneCfg);
    }

    @Override
    public List<Map<String, Object>> queryCarzoneCfgList(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO) {
        return cgjCarzoneCfgDao.queryCarzoneCfgList(cgjCarzoneCfgQueDTO);
    }

    @Override
    public Map<String, Object> queryCarzoneCfgDetails(Integer id) {
        return cgjCarzoneCfgDao.queryCarzoneCfgDetails(id);
    }

    @Override
    public int deleteCarzoneCfg(CgjCarzoneCfg cgjCarzoneCfg) {
        return cgjCarzoneCfgDao.deleteCarzoneCfg(cgjCarzoneCfg);
    }

    @Override
    public int updateShowStatus(CgjCarzoneCfg cgjCarzoneCfg) {
        return cgjCarzoneCfgDao.updateShowStatus(cgjCarzoneCfg);
    }

    @Override
    public int queryCarzoneCfgCount(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO) {
        return cgjCarzoneCfgDao.queryCarzoneCfgCount(cgjCarzoneCfgQueDTO);
    }

}
