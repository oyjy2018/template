package com.ydc.service.order.service;

import com.ydc.model.cgj.CommodityModel;

public interface CommodityModelService {

    /**
     * 获取对象
     * @param commodityModelId
     * @return
     */
    CommodityModel selectByPrimaryKey(Integer commodityModelId);

    /**
     * 根据版本号更新
     * @param commodityModel
     * @return
     */
    int updateByIdAndVersion(CommodityModel commodityModel);
}
