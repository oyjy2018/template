package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.ICommodityService;
import com.ydc.cgj.app.service.CommodityService;
import com.ydc.commom.result.Result;
import com.ydc.model.cgj.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private ICommodityService commodityService;

    @Override
    public Result getCommodityList(String title, Pagination pagination) {
        return commodityService.getCommodityList(title, pagination);
    }

    @Override
    public Result getCommodityDetail(Integer commodityId) {
        return commodityService.getCommodityDetail(commodityId);
    }

    @Override
    public Result getHomePageCommodityList(int commodityNum) {
        return commodityService.getHomePageCommodityList(commodityNum);
    }

    @Override
    public Result getRollCommodity(String sonClassifyCode) {
        return commodityService.getRollCommodity(sonClassifyCode);
    }
}
