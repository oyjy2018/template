package com.ydc.service.store.service;

import com.ydc.model.cgj.CommodityImg;

import java.util.List;

/**
 * 商品图片
 */
public interface CommodityImgService {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int insert(CommodityImg record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int insertSelective(CommodityImg record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    CommodityImg selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int updateByPrimaryKeySelective(CommodityImg record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int updateByPrimaryKey(CommodityImg record);

    /**
     * 根据商品ID获取记录
     *
     * @param commodityId
     * @return
     */
    List<CommodityImg> selectByCommodityId(Integer commodityId);

    /**
     * 批量新增记录
     *
     * @param list
     */
    void batchInsert(List<CommodityImg> list);

    /**
     * 批量新增或修改记录
     *
     * @param list
     */
    void batchInsertOrUpdate(List<CommodityImg> list);

    /**
     * 查询商品图片
     *
     * @param homePageImgId
     * @return
     */
    CommodityImg getValidCommodityImg(Integer homePageImgId);
}
