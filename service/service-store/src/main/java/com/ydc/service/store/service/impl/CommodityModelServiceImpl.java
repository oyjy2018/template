package com.ydc.service.store.service.impl;

import com.ydc.commom.view.dto.cgj.StoreReqDTO;
import com.ydc.dao.cgj.store.CommodityModelDao;
import com.ydc.model.cgj.CommodityModel;
import com.ydc.service.store.service.CommodityModelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格
 */
@Service
public class CommodityModelServiceImpl implements CommodityModelService {

    private static Logger logger = LogManager.getLogger(CommodityModelServiceImpl.class);

    @Autowired
    private CommodityModelDao commodityModelDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commodityModelDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CommodityModel record) {
        return commodityModelDao.insert(record);
    }

    @Override
    public int insertSelective(CommodityModel record) {
        return commodityModelDao.insertSelective(record);
    }

    @Override
    public CommodityModel selectByPrimaryKey(Integer id) {
        return commodityModelDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CommodityModel record) {
        return commodityModelDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommodityModel record) {
        return commodityModelDao.updateByPrimaryKey(record);
    }

    @Override
    public List<CommodityModel> selectByCommodityId(Integer commodityId) {
        return commodityModelDao.selectByCommodityId(commodityId);
    }

    @Override
    public void batchInsert(List<CommodityModel> list) {
        commodityModelDao.batchInsert(list);
    }

    @Override
    public void batchInsertOrUpdate(List<CommodityModel> list) {
        commodityModelDao.batchInsertOrUpdate(list);
    }

    @Override
    public int modifyInventory(StoreReqDTO srd) {
        Integer commodityModelId = Integer.valueOf(srd.getCommodityModelId());
        Integer inventory = Integer.valueOf(srd.getInventory());
        Integer userId = Integer.valueOf(srd.getUserId());
        return commodityModelDao.modifyInventory(commodityModelId, inventory, userId);
    }

    @Override
    public List<CommodityModel> getCommodityModelList(Integer commodityId) {
        return commodityModelDao.getCommodityModelList(commodityId);
    }

    @Override
    public int deleteByCommodityModelIds(String commodityModelIds) {
        return commodityModelDao.deleteByCommodityModelIds(commodityModelIds);
    }
}
