package com.ydc.service.store.service;

import com.ydc.commom.view.dto.cgj.PositionDTO;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.commom.view.vo.cgj.CarStoreVO;
import com.ydc.commom.view.vo.cgj.RecommendCarStoreVO;
import com.ydc.commom.view.vo.cgj.order.BStoreListVO;
import com.ydc.model.cgj.BCarServiceShop;
import com.ydc.model.cgj.Pagination;

import java.util.List;
import java.util.Map;

/**
 * 汽车门店
 *
 * @author
 * @create 2018-11-01 14:34
 **/
public interface BCarServiceShopService {

    /**
     * 批量操作门店
     * @param list
     */
    void insertCarServiceBatch(List<BCarServiceShop> list);

    /**
     * B端门店列表
     * @param carStoreDTO
     * @return
     */
    List<BStoreListVO> getBStoreList(CarStoreDTO carStoreDTO);

    /**
     * 更新是否上架
     * @param carStoreDTO
     * @return
     */
    Integer updateStoreWhetherPutaway(CarStoreDTO carStoreDTO);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    BCarServiceShop selectByPrimaryKey(Integer id);

    /**
     * H5 门店列表
     * @param positionDTO
     * @return
     */
    List<CarStoreVO> getH5StoreList(PositionDTO positionDTO);

    /**
     * 按销量获取门店
     * @param positionDTO
     * @return
     */
    List<CarStoreVO> getH5StoreSalesVolumeList(PositionDTO positionDTO);

    /**
     * 首页推荐门店
     * @param positionDTO
     * @return
     */
    RecommendCarStoreVO getRecommendCarStore(PositionDTO positionDTO);

    /**
     * 首页门店
     * @param positionDTO
     * @return
     */
    RecommendCarStoreVO getHomePageCarStore(PositionDTO positionDTO);

    /**
     * 优先门店列表
     * @param storeName
     * @return
     */
    List<Map<String, Object>> getPriorityCarStore(String storeName, Pagination pagination);

    /**
     * 门店首页推荐（首页显示商品）
     * @param carStoreIds
     * @param priority
     * @param userId
     * @return
     */
    Integer updatePriorityCarStore(String carStoreIds, int priority, Integer userId);
}
