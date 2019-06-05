package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.ICommodityModelService;
import com.ydc.cgj.app.service.CommodityModelService;
import com.ydc.commom.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityModelServiceImpl implements CommodityModelService {

    @Autowired
    private ICommodityModelService commodityModelService;

    @Override
    public Result getCommodityModelList(Integer commodityId) {
        return commodityModelService.getCommodityModelList(commodityId);
    }
}
