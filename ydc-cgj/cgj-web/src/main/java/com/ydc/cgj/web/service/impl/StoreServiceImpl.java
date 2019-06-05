package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.service.StoreFeignService;
import com.ydc.cgj.web.service.StoreService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.CommodityDTO;
import com.ydc.commom.view.dto.cgj.StoreReqDTO;
import com.ydc.model.cgj.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreFeignService storeFeignService;

    @Override
    public String queryCommodityList(CommodityDTO commodityDTO) {
        return storeFeignService.queryCommodityList(commodityDTO);
    }

    @Override
    public String queryCommodityDetails(CommodityDTO commodityDTO) {
        return storeFeignService.queryCommodityDetails(commodityDTO);
    }

    @Override
    public String saveOrUpdateCommodity(StoreReqDTO srd) {
        return storeFeignService.saveOrUpdateCommodity(srd);
    }

    @Override
    public String homePageRecommend(StoreReqDTO srd) {
        return storeFeignService.homePageRecommend(srd);
    }

    @Override
    public String updateReleaseStatus(StoreReqDTO srd) {
        return storeFeignService.updateReleaseStatus(srd);
    }

    @Override
    public String modifyInventory(StoreReqDTO srd) {
        return storeFeignService.modifyInventory(srd);
    }

    @Override
    public Result getPriorityCommodityList(String title, Pagination pagination) {
        return storeFeignService.getPriorityCommodityList(title, pagination);
    }

    @Override
    public Result updatePriority(String commodityIds, int priority, Integer userId) {
        return storeFeignService.updatePriority(commodityIds, priority, userId);
    }
}
