package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;

public interface CommodityModelService {

    /**
     * 获取商品型号列表
     *
     * @param commodityId
     * @return
     */
    Result getCommodityModelList(Integer commodityId);
}
