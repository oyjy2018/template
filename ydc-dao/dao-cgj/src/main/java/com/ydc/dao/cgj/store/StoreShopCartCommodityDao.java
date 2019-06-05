package com.ydc.dao.cgj.store;

import com.ydc.commom.view.dto.cgj.shopCart.ShopCartCommodityDTO;
import com.ydc.model.cgj.StoreShopCartCommodity;
import java.util.List;
import java.util.Map;

public interface StoreShopCartCommodityDao {
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
    int insert(StoreShopCartCommodity record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int insertSelective(StoreShopCartCommodity record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    StoreShopCartCommodity selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKeySelective(StoreShopCartCommodity record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKey(StoreShopCartCommodity record);

    /**
     * 根据条件查询 购物车商品
     * @param map
     * @return
     */
    List<StoreShopCartCommodity>  selectStoreShopCart(Map<String,Object> map);

    /**
     *  查询会员购物车 有效商品
     * @param map
     * @return
     */
    List<ShopCartCommodityDTO> getMemberValidationShopCartCommodity(Map<String,Object> map);

    /**
     * 查询会员 购物车 无效商品
     *
     */
    List<ShopCartCommodityDTO> getMemberInvalidationShopCartCommodity(Map<String,Object> map);

    /**
     *
     *  查询会员购物车 总数 与总价格
     *
     */
    Map<String,Object> getMemberShopCartNum(Map<String,Object> map);

    /**
     * 批量删除 购物车商品
     */
    int updateByIds(Map<String,Object> map);

    /**
     * 更新delete_status
     * @param map
     * @return
     */
    Integer updateDeleteStatus(Map<String,Object> map);




}