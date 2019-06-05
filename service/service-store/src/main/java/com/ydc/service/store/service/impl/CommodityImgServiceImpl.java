package com.ydc.service.store.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.dao.cgj.store.CommodityImgDao;
import com.ydc.model.cgj.CommodityImg;
import com.ydc.service.store.service.CommodityImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品图片
 */
@Service
public class CommodityImgServiceImpl implements CommodityImgService {

    @Autowired
    private CommodityImgDao commodityImgDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commodityImgDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CommodityImg record) {
        return commodityImgDao.insert(record);
    }

    @Override
    public int insertSelective(CommodityImg record) {
        return commodityImgDao.insertSelective(record);
    }

    @Override
    public CommodityImg selectByPrimaryKey(Integer id) {
        return commodityImgDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CommodityImg record) {
        return commodityImgDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommodityImg record) {
        return commodityImgDao.updateByPrimaryKey(record);
    }

    @Override
    public List<CommodityImg> selectByCommodityId(Integer commodityId) {
        return commodityImgDao.selectByCommodityId(commodityId);
    }

    @Override
    public void batchInsert(List<CommodityImg> list) {
        commodityImgDao.batchInsert(list);
    }

    @Override
    public void batchInsertOrUpdate(List<CommodityImg> list) {
        commodityImgDao.batchInsertOrUpdate(list);
    }

    @Override
    public CommodityImg getValidCommodityImg(Integer homePageImgId) {
        return commodityImgDao.getValidCommodityImg(homePageImgId);
    }
}
