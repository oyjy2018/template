package com.ydc.service.sys.service.impl;

import com.ydc.dao.cgj.sys.CommImgDao;
import com.ydc.model.cgj.sys.CommImg;
import com.ydc.service.sys.service.CommImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hejiangping
 * @date 2018/12/27
 */
@Service
public class CommImgServiceImpl implements CommImgService {
    @Autowired
    CommImgDao commImgDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commImgDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CommImg record) {
        return commImgDao.insert(record);
    }

    @Override
    public int insertSelective(CommImg record) {
        return commImgDao.insertSelective(record);
    }

    @Override
    public CommImg selectByPrimaryKey(Integer id) {
        return commImgDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CommImg record) {
        return commImgDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommImg record) {
        return commImgDao.updateByPrimaryKey(record);
    }

    @Override
    public CommImg selectByCommIdAndType(Integer commId, Integer imgType) {
        return commImgDao.selectByCommIdAndType(commId,imgType);
    }
}
