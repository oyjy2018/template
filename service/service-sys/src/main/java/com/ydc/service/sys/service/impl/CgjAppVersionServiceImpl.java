package com.ydc.service.sys.service.impl;

import com.ydc.dao.cgj.sys.CgjAppVersionDao;
import com.ydc.model.cgj.sys.CgjAppVersion;
import com.ydc.service.sys.service.AdvertConfigService;
import com.ydc.service.sys.service.CgjAppVersionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author hejiangping
 * @date 2019/1/12
 */
@Service
public class CgjAppVersionServiceImpl implements CgjAppVersionService {
    protected static final Logger logger = LogManager.getLogger(AdvertConfigService.class);
    @Autowired
    CgjAppVersionDao cgjAppVersionDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cgjAppVersionDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CgjAppVersion record) {
        return cgjAppVersionDao.insert(record);
    }

    @Override
    public int insertSelective(CgjAppVersion record) {
        return cgjAppVersionDao.insertSelective(record);
    }

    @Override
    public CgjAppVersion selectByPrimaryKey(Integer id) {
        return cgjAppVersionDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CgjAppVersion record) {
        return cgjAppVersionDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CgjAppVersion record) {
        return cgjAppVersionDao.updateByPrimaryKey(record);
    }

    @Override
    public Map<String, Object> getNewestVersion(String platform) {
        return cgjAppVersionDao.getNewestVersion(platform);
    }
}
