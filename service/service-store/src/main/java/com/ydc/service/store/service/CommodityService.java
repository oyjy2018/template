package com.ydc.service.store.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.CommodityDTO;
import com.ydc.commom.view.dto.cgj.CommodityShowDTO;
import com.ydc.commom.view.dto.cgj.StoreReqDTO;
import com.ydc.model.cgj.Commodity;
import com.ydc.model.cgj.Pagination;

import java.util.List;
import java.util.Map;

/**
 * 商品
 */
public interface CommodityService {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int insert(Commodity record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int insertSelective(Commodity record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    Commodity selectByPrimaryKey(Integer id);

    /**
     * 根据商品标题查询
     * @param title
     * @return
     */
    Commodity selectByTitle(String title);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKeySelective(Commodity record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKey(Commodity record);

    /**
     * 获取商品信息记录
     *
     * @param commodityDTO
     * @return
     */
    List<Map<String, Object>> getCommodityList(CommodityDTO commodityDTO);

    /**
     * 获取商品信息记录总记录数
     *
     * @param commodityDTO
     * @return
     */
    Map<String, Object> getCommodityListCount(CommodityDTO commodityDTO);

    /**
     * 保存或更新商品信息
     *
     * @param srd
     */
    Integer saveOrUpdateCommodity(StoreReqDTO srd);

    /**
     * 更新是否首推状态
     *
     * @param srd
     * @return
     */
    int updateHasShoutuiByIds(StoreReqDTO srd);

    /**
     * 更新发布状态
     *
     * @param srd
     * @return
     */
    Result updateReleaseStatusByIds(StoreReqDTO srd);

    /**
     * 获取商品列表
     *
     * @param title
     * @param pagination
     * @return
     */
    List<CommodityShowDTO> getCommodityList(String title, Pagination pagination);

    /**
     * 获取首页商品列表
     * @param commodityNum
     * @return
     */
    List<CommodityShowDTO> getHomePageCommodityList(int commodityNum);

    /**
     * 获取商品详情(从缓存取)
     *
     * @param commodityId
     * @return
     */
    CommodityShowDTO getCommodityDetailRedis(Integer commodityId);

    /**
     * 获取商品详情(从数据库里取)
     * @param commodityId
     * @return
     */
    CommodityShowDTO getCommodityDetailById(Integer commodityId);

    /**
     * 查询指定ID中0库存的商品信息
     * @param commodityIds
     * @return
     */
    List<Commodity> selectZeroInventoryByIds(String commodityIds);

    /**
     * 根据ID修改商品总库存
     * @param commodityId
     * @return
     */
    int updateTotalInventoryById(Integer commodityId);

    /**
     * 获取券商品
     * @param sonClassifyCode
     * @return
     */
    Map<String, Object> getRollCommodity(String sonClassifyCode);

    /**
     * 优先商品列表
     *
     * @param title
     * @param pagination
     * @return
     */
    List<Map<String, Object>> getPriorityCommodityList(String title, Pagination pagination);

    /**
     * 商品首页推荐（首页显示商品）
     * @param commodityIds
     * @param priority
     * @param userId
     * @return
     */
    Integer updatePriority(String commodityIds, int priority, Integer userId);
}
