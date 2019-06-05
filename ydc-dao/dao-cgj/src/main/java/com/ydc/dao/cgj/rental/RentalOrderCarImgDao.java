package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderImgDTO;
import com.ydc.commom.view.dto.cgj.rental.RepayCarOrderImgDTO;
import com.ydc.model.cgj.rental.RentalOrderCarImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentalOrderCarImgDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    int insert(RentalOrderCarImg record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    int insertSelective(RentalOrderCarImg record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    RentalOrderCarImg selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    int updateByPrimaryKeySelective(RentalOrderCarImg record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    int updateByPrimaryKey(RentalOrderCarImg record);

    /**
     * 批量新增出车租车订单图片
     * @param list
     */
    void insertComeCarImgBatch(List<ComeCarOrderImgDTO> list);


    /**
     * 批量新增还车租车订单图片
     * @param list
     */
    void insertRepayCarImgBatch(List<RepayCarOrderImgDTO> list);

    /**
     * 根据
     * @param orderId
     * @return
     */
    List<RentalOrderCarImg> getRentalOrderCarImgByOrderId(Integer orderId);

    /**
     * 出车图片集合
     * @param orderId
     * @return
     */
    List<ComeCarOrderImgDTO> getComeCarOrderImgData(Integer orderId);

    /**
     * 更新租车图片状态
     * @param orderId
     * @param describeType
     * @return
     */
    Integer updateRentalCarImg(@Param("orderId") Integer orderId , @Param("describeType") Integer describeType);

    /**
     * 获取所有无效租车图片
     * @return
     */
    List<RentalOrderCarImg> getAllInvalidImg();

    /**
     * 批量删除无效图片
     * @param list
     */
    void batchDeleteInvalidImg(List<Integer> list);
}