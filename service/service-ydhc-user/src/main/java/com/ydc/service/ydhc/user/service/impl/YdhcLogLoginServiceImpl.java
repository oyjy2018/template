package com.ydc.service.ydhc.user.service.impl;

import com.ydc.dao.ydhc.YdhcLogLoginDao;
import com.ydc.model.ydhc.YdhcLogLogin;
import com.ydc.service.ydhc.user.service.YdhcLogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdhcLogLoginServiceImpl implements YdhcLogLoginService {

    @Autowired
    private YdhcLogLoginDao ydhcLogLoginDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ydhcLogLoginDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YdhcLogLogin record) {
        return ydhcLogLoginDao.insert(record);
    }

    @Override
    public int insertSelective(YdhcLogLogin record) {
        return ydhcLogLoginDao.insertSelective(record);
    }

    @Override
    public YdhcLogLogin selectByPrimaryKey(Integer id) {
        return ydhcLogLoginDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcLogLogin record) {
        return ydhcLogLoginDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcLogLogin record) {
        return ydhcLogLoginDao.updateByPrimaryKey(record);
    }
}
