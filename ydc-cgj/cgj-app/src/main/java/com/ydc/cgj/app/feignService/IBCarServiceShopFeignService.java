package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.PositionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author
 * @create 2018-10-31 11:56
 **/
@Service
@FeignClient(value = "service-store")
public interface IBCarServiceShopFeignService {

    /**
     * H5 门店列表
     * @param positionDTO
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/getH5StoreList")
    String getH5StoreList(@RequestBody PositionDTO positionDTO);


    /**
     * 按销量获取门店
     * @param positionDTO
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/getH5StoreSalesVolumeList")
    String getH5StoreSalesVolumeList(@RequestBody PositionDTO positionDTO);

    /**
     * H5 首页推荐
     * @param positionDTO
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/getRecommendCarStore")
    Result getRecommendCarStore(@RequestBody PositionDTO positionDTO);

    /**
     * 首页门店
     * @param positionDTO
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/getHomePageCarStore")
    Result getHomePageCarStore(@RequestBody PositionDTO positionDTO);

}
