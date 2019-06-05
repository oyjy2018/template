package com.ydc.dao.cgj.store;

import com.ydc.commom.view.dto.cgj.CommodityDTO;
import com.ydc.commom.view.dto.cgj.CommodityShowDTO;
import com.ydc.commom.view.dto.cgj.order.OrderCommodityDTO;
import com.ydc.model.cgj.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommodityDao {
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
    Commodity selectByTitle(@Param("title") String title);

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
    List<Map<String, Object>> getCommodityList(@Param("commodityDTO") CommodityDTO commodityDTO);

    /**
     * 获取商品信息记录总记录数
     *
     * @param commodityDTO
     * @return
     */
    Map<String, Object> getCommodityListCount(@Param("commodityDTO") CommodityDTO commodityDTO);

    /**
     * 更新是否首推状态
     *
     * @param commodityIds
     * @param hasShoutui
     * @return
     */
    int updateHasShoutuiByIds(@Param("commodityIds") String commodityIds, @Param("hasShoutui") Integer hasShoutui,
                              @Param("updateBy") Integer updateBy);

    /**
     * 更新发布状态
     *
     * @param commodityIds
     * @param releaseStatus
     * @return
     */
    int updateReleaseStatusByIds(@Param("commodityIds") String commodityIds,
                                 @Param("releaseStatus") Integer releaseStatus,
                                 @Param("releaseDate")Date releaseDate,
                                 @Param("shelveTime")Date shelveTime,
                                 @Param("updatePerson")String updatePerson,
                                 @Param("updateBy") Integer updateBy);

    /**
     * 查询预购商品
     * @param param
     * @return
     */
    List<OrderCommodityDTO> getOrderCommodityDTO(Map<String, Object> param);

    /**
     * 根据title获取商品list
     * @param title
     * @return
     */
    List<Integer> getCommodityListByTitle(@Param("title") String title);

    /**
     * 获取首页商品列表
     * @param commodityNum
     * @return
     */
    List<Integer> getHomePageCommodityList(int commodityNum);

    /**
     * 查询指定ID中0库存的商品信息
     * @param commodityIds
     * @return
     */
    List<Commodity> selectZeroInventoryByIds(@Param("commodityIds") String commodityIds);

    /**
     * 根据ID修改商品总库存
     * @param commodityId
     * @return
     */
    int updateTotalInventoryById(@Param("commodityId")Integer commodityId);

    /**
     * 获取券商品
     * @param sonClassifyCode
     * @return
     */
    Map<String, Object> getRollCommodity(String sonClassifyCode);
    /**
     * 优先商品列表
     * @param title
     * @return
     */
    List<Map<String, Object>> getPriorityCommodityList(@Param("title") String title);

    /**
     * 更新是否首推状态
     *
     * @param commodityIds
     * @param priority
     * @return
     */
    int updatePriority(@Param("commodityIds") String commodityIds, @Param("priority") int priority,
                              @Param("updateBy") Integer updateBy);
}