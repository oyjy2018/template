package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.Pagination;

public interface CommodityService {

    /**
     * 获取商品列表
     *
     * @param title
     * @param pagination
     * @return
     */
    Result getCommodityList(String title, Pagination pagination);

    /**
     * 获取商品详情
     *
     * @param commodityId
     * @return
     */
    Result getCommodityDetail(Integer commodityId);

    /**
     * 获取首页商品列表
     * @param commodityNum
     * @return
     */
    Result getHomePageCommodityList(int commodityNum);

    /**
     * 获取券商品
     * @param sonClassifyCode
     * @return
     */
    Result getRollCommodity(String sonClassifyCode);
}
