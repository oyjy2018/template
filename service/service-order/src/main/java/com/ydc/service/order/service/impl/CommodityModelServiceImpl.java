package com.ydc.service.order.service.impl;

import com.ydc.dao.cgj.store.CommodityModelDao;
import com.ydc.model.cgj.CommodityModel;
import com.ydc.service.order.service.CommodityModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommodityModelServiceImpl implements CommodityModelService {

    @Resource
    private CommodityModelDao commodityModelDao;

    @Override
    public CommodityModel selectByPrimaryKey(Integer commodityModelId) {
        return commodityModelDao.selectByPrimaryKey(commodityModelId);
    }

    @Override
    public int updateByIdAndVersion(CommodityModel commodityModel) {
        return commodityModelDao.updateByIdAndVersion(commodityModel);
    }
}
