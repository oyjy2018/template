package com.ydc.cgj.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.CommodityDTO;
import com.ydc.commom.view.dto.cgj.StoreReqDTO;
import com.ydc.model.cgj.Pagination;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-store")
public interface StoreFeignService {

    /**
     * 获取商品列表
     *
     * @param commodityDTO
     * @return
     */
    @PostMapping(value = "/commodity/queryCommodityList")
    String queryCommodityList(@RequestBody CommodityDTO commodityDTO);

    /**
     * 获取商品详情
     *
     * @param commodityDTO
     * @return
     */
    @PostMapping(value = "/commodity/queryCommodityDetails")
    String queryCommodityDetails(@RequestBody CommodityDTO commodityDTO);

    /**
     * 保存或修改商品信息
     *
     * @param srd
     * @return
     */
    @PostMapping(value = "/commodity/saveOrUpdateCommodity")
    public String saveOrUpdateCommodity(@RequestBody StoreReqDTO srd);

    /**
     * 首页推荐
     *
     * @param srd
     * @return
     */
    @PostMapping(value = "/commodity/homePageRecommend")
    public String homePageRecommend(@RequestBody StoreReqDTO srd);

    /**
     * 更新发布状态
     *
     * @param srd
     * @return
     */
    @PostMapping(value = "/commodity/updateReleaseStatus")
    public String updateReleaseStatus(@RequestBody StoreReqDTO srd);

    /**
     * 修改库存
     *
     * @param srd
     * @return
     */
    @PostMapping(value = "/commodity/modifyInventory")
    public String modifyInventory(@RequestBody StoreReqDTO srd);

    /**
     * 优先商品列表
     *
     * @param title
     * @param pagination
     * @return
     */
    @PostMapping(value = "/commodity/getPriorityCommodityList", consumes = "application/json")
    Result getPriorityCommodityList(@RequestParam(value = "title", required = false) String title, @RequestBody Pagination pagination);

    /**
     * 商品首页推荐（首页显示商品）
     * @param commodityIds
     * @param priority
     * @param userId
     * @return
     */
    @PostMapping(value = "/commodity/updatePriority")
    Result updatePriority(@RequestParam(value = "commodityIds") String commodityIds,
                          @RequestParam(value = "priority") int priority,
                          @RequestParam(value = "userId", required = false) Integer userId);
}
