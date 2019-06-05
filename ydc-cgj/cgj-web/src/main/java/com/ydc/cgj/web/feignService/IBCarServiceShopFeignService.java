package com.ydc.cgj.web.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.model.cgj.BCarServiceShop;
import com.ydc.model.cgj.Pagination;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 车门店
 *
 * @author
 * @create 2018-11-01 9:16
 **/
@Service
@FeignClient(value = "service-store")
public interface IBCarServiceShopFeignService {

    /**
     * B端门店列表
     *
     * @param carStoreDTO
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/getBStoreList")
    String getBStoreList(@RequestBody CarStoreDTO carStoreDTO);


    /**
     * 更新门店是否上架
     *
     * @param carStoreDTO
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/updateStoreWhetherPutaway")
    String updateStoreWhetherPutaway(@RequestBody CarStoreDTO carStoreDTO);

    /**
     * 保存门店
     * @param list
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/insertCarServiceBatch")
    String insertCarServiceBatch(@RequestBody List<BCarServiceShop> list);

    /**
     * 优先门店列表
     * @param storeName
     * @param pagination
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/getPriorityCarStore", consumes = "application/json")
    Result getPriorityCarStore(@RequestParam(value = "storeName", required = false) String storeName, @RequestBody Pagination pagination);

    /**
     * 门店首页推荐（首页显示商品）
     * @param carStoreIds
     * @param priority
     * @param userId
     * @return
     */
    @PostMapping(value = "/bCarServiceShop/updatePriorityCarStore")
    Result updatePriorityCarStore(@RequestParam(value = "carStoreIds") String carStoreIds,
                                  @RequestParam(value = "priority") int priority,
                                  @RequestParam(value = "userId", required = false) Integer userId);
}
