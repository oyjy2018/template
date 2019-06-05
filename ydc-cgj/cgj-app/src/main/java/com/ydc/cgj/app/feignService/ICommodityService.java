package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.Pagination;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-store")
public interface ICommodityService {

    /**
     * 获取商品列表
     *
     * @param title
     * @param pagination
     * @return
     */
    @PostMapping(value = "/commodity/getCommodityList", consumes = "application/json")
    Result getCommodityList(@RequestParam(value = "title", required = false) String title, @RequestBody Pagination pagination);

    /**
     * 获取商品详情
     *
     * @param commodityId
     * @return
     */
    @PostMapping("/commodity/getCommodityDetail")
    Result getCommodityDetail(@RequestParam("commodityId") Integer commodityId);

    /**
     * 获取首页商品列表
     * @param commodityNum
     * @return
     */
    @PostMapping("/commodity/getHomePageCommodityList")
    Result getHomePageCommodityList(@RequestParam("commodityNum") int commodityNum);

    /**
     * 获取券商品
     * @param sonClassifyCode
     * @return
     */
    @PostMapping("/commodity/getRollCommodity")
    Result getRollCommodity(@RequestParam("sonClassifyCode") String sonClassifyCode);
}
