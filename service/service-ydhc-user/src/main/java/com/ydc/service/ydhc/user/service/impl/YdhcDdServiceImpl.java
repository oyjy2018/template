package com.ydc.service.ydhc.user.service.impl;

import com.ydc.dao.ydhc.YdhcDdDao;
import com.ydc.model.ydhc.YdhcDd;
import com.ydc.service.ydhc.user.service.YdhcDdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdhcDdServiceImpl implements YdhcDdService {

    @Autowired
    private YdhcDdDao ydhcDdDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ydhcDdDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YdhcDd record) {
        return ydhcDdDao.insert(record);
    }

    @Override
    public int insertSelective(YdhcDd record) {
        return ydhcDdDao.insertSelective(record);
    }

    @Override
    public YdhcDd selectByPrimaryKey(Integer id) {
        return ydhcDdDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcDd record) {
        return ydhcDdDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcDd record) {
        return ydhcDdDao.updateByPrimaryKey(record);
    }

    @Override
    public YdhcDd getDdConfig() {
        return ydhcDdDao.getDdConfig();
    }
}
