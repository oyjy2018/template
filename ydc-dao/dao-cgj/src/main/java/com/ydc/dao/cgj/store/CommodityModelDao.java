package com.ydc.dao.cgj.store;

import com.ydc.model.cgj.CommodityModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityModelDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int insert(CommodityModel record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int insertSelective(CommodityModel record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    CommodityModel selectByPrimaryKey(Integer id);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @param id
     * @return
     */
    CommodityModel selectByPrimaryKeyNoCache(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int updateByPrimaryKeySelective(CommodityModel record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int updateByPrimaryKey(CommodityModel record);

    /**
     * 根据ID和版本号更新
     * @param record
     * @return
     */
    int updateByIdAndVersion(CommodityModel record);

    /**
     * 根据商品id查询记录
     *
     * @param commodityId
     * @return
     */
    List<CommodityModel> selectByCommodityId(Integer commodityId);

    /**
     * 批量新增记录
     *
     * @param list
     */
    void batchInsert(List<CommodityModel> list);

    /**
     * 批量新增或修改记录
     *
     * @param list
     */
    void batchInsertOrUpdate(List<CommodityModel> list);

    /**
     * 修改库存
     *
     * @param commodityModelId
     * @param inventory
     * @return
     */
    int modifyInventory(@Param("commodityModelId") Integer commodityModelId, @Param("inventory") Integer inventory,
                        @Param("updateBy") Integer updateBy);

    /**
     * 获取商品型号列表
     * @param commodityId
     * @return
     */
    List<CommodityModel> getCommodityModelList(Integer commodityId);

    /**
     * 根据ID集合删除数据
     * @param commodityModelIds
     * @return
     */
    int deleteByCommodityModelIds(@Param("commodityModelIds")String commodityModelIds);
}