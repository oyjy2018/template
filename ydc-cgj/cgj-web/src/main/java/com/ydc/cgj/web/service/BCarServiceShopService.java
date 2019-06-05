package com.ydc.cgj.web.service;


import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.model.cgj.Pagination;

import java.util.List;
import java.util.Map;

/**
 * B端门店
 *
 * @author
 * @create 2018-10-30 10:27
 **/
public interface BCarServiceShopService {

    /**
     * B端门店列表
     *
     * @param carStoreDTO
     * @return
     */
    String getBStoreList(CarStoreDTO carStoreDTO);

    /**
     * 更新门店是否上架
     *
     * @param carStoreDTO
     * @return
     */
    String updateStoreWhetherPutaway(CarStoreDTO carStoreDTO);

    /**
     * 保存门店
     * @param carStoreDTO
     * @return
     */
    String refreshCarStoreTemplate(CarStoreDTO carStoreDTO);

    /**
     * 优先门店列表
     * @param storeName
     * @return
     */
    Result getPriorityCarStore(String storeName, Pagination pagination);

    /**
     * 门店首页推荐（首页显示商品）
     * @param carStoreIds
     * @param priority
     * @param userId
     * @return
     */
    Result updatePriorityCarStore(String carStoreIds, int priority, Integer userId);
}
