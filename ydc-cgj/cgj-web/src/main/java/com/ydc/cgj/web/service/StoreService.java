package com.ydc.cgj.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.CommodityDTO;
import com.ydc.commom.view.dto.cgj.StoreReqDTO;
import com.ydc.model.cgj.Pagination;

public interface StoreService {

    /**
     * 获取商品列表
     *
     * @param commodityDTO
     * @return
     */
    public String queryCommodityList(CommodityDTO commodityDTO);

    /**
     * 获取商品详情
     *
     * @param commodityDTO
     * @return
     */
     String queryCommodityDetails(CommodityDTO commodityDTO);

    /**
     * 保存或修改商品信息
     *
     * @param srd
     * @return
     */
    public String saveOrUpdateCommodity(StoreReqDTO srd);

    /**
     * 首页推荐
     *
     * @param srd
     * @return
     */
    public String homePageRecommend(StoreReqDTO srd);

    /**
     * 更新发布状态
     *
     * @param srd
     * @return
     */
    public String updateReleaseStatus(StoreReqDTO srd);

    /**
     * 修改库存
     *
     * @param srd
     * @return
     */
    public String modifyInventory(StoreReqDTO srd);

    /**
     * 优先商品列表
     *
     * @param title
     * @param pagination
     * @return
     */
    Result getPriorityCommodityList(String title, Pagination pagination);

    /**
     * 商品首页推荐（首页显示商品）
     * @param commodityIds
     * @param priority
     * @param userId
     * @return
     */
    Result updatePriority(String commodityIds, int priority, Integer userId);
}
