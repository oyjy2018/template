package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.PositionDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @create 2018-10-31 11:57
 **/
public interface BCarServiceShopService {

    /**
     * H5 门店列表
     * @param positionDTO
     * @return
     */
    String getH5StoreList(PositionDTO positionDTO, HttpServletRequest request);


    /**
     * 按销量获取门店
     * @param positionDTO
     * @return
     */
    String getH5StoreSalesVolumeList(PositionDTO positionDTO, HttpServletRequest request);

    /**
     * 首页推荐门店
     * @param positionDTO
     * @return
     */
    Result getRecommendCarStore(PositionDTO positionDTO, HttpServletRequest request);


    /**
     * 首页门店
     * @param positionDTO
     * @param request
     * @return
     */
    Result getHomePageCarStore(PositionDTO positionDTO, HttpServletRequest request);
}
