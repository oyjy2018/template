package com.ydc.dao.cgj.service;

import com.ydc.commom.view.dto.cgj.PositionDTO;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.commom.view.vo.cgj.CarStoreVO;
import com.ydc.commom.view.vo.cgj.order.BStoreListVO;
import com.ydc.model.cgj.BCarServiceShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BCarServiceShopDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    int insert(BCarServiceShop record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    int insertSelective(BCarServiceShop record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    BCarServiceShop selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    int updateByPrimaryKeySelective(BCarServiceShop record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 09:39:18
     */
    int updateByPrimaryKey(BCarServiceShop record);

    /**
     * B端门店列表
     * @param carStoreDTO
     * @return
     */
    List<BStoreListVO> getBStoreList(@Param("carStoreDTO") CarStoreDTO carStoreDTO);

    /**
     * 更新是否上架
     * @param carStoreDTO
     * @return
     */
    Integer updateStoreWhetherPutaway(@Param("carStoreDTO") CarStoreDTO carStoreDTO);

    /**
     * H5 门店列表
     * @param positionDTO
     * @return
     */
    List<CarStoreVO> getH5StoreList(@Param("positionDTO") PositionDTO positionDTO);

    /**
     * 批量操作门店
     * @param list
     */
    void insertCarServiceBatch(List<BCarServiceShop> list);

    /**
     * 首页推荐
     * @param positionDTO
     * @return
     */
    List<CarStoreVO> getRecommendCarStore(@Param("positionDTO") PositionDTO positionDTO);

    /**
     * 首页门店
     * @param positionDTO
     * @return
     */
    List<CarStoreVO> getHomePageCarStore(@Param("positionDTO") PositionDTO positionDTO);

    /**
     * 根据id或code获取门店
     * @param id
     * @return
     */
    BCarServiceShop selectByIdOrCode(@Param(value = "storeId") Integer id,
                                     @Param(value = "storeCode") String storeCode);

    /**
     * 批量更新门店
     * @param list
     * @param userId
     */
    void batchUpdateCarShopStatus(@Param("list") List<String> list,@Param("userId") Integer userId);

    /**
     * 优先门店列表
     * @param storeName
     * @return
     */
    List<Map<String, Object>> getPriorityCarStore(@Param("storeName") String storeName);

    /**
     * 更新是否首推状态
     *
     * @param carStoreIds
     * @param priority
     * @return
     */
    int updatePriorityCarStore(@Param("carStoreIds") String carStoreIds, @Param("priority") int priority,
                       @Param("updateBy") Integer updateBy);

}