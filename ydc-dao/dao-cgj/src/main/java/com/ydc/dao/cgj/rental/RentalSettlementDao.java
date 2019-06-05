package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalSettlement2VO;
import com.ydc.commom.view.vo.cgj.rental.RentalSettlementVO;
import com.ydc.model.cgj.rental.RentalSettlement;
import org.apache.ibatis.annotations.Param;

public interface RentalSettlementDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:25:19
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:25:19
     */
    int insert(RentalSettlementDTO record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:25:19
     */
    int insertSelective(RentalSettlement record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-21 20:25:19
     */
    RentalSettlement selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:25:19
     */
    int updateByPrimaryKeySelective(RentalSettlement record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:25:19
     */
    int updateByPrimaryKey(RentalSettlement record);

    /**
     * 返回结算需要订单信息
     * @param orderId
     * @return
     */
    RentalSettlementVO getRentalSettlement(@Param("orderId") Integer orderId);

    /**
     * 结算信息
     * @param orderId
     * @return
     */
    RentalSettlement getRentalSettlementByOrderId(@Param("orderId") Integer orderId);

    /**
     * 结算明细
     * @param orderId
     * @return
     */
    RentalSettlement2VO getRentalSettlement2VOByOrderId(@Param("orderId") Integer orderId);

    /**
     * 通过订单id获取租车结算表详情
     * @param orderId
     * @return
     */
    RentalSettlement selectByOrderId(Integer orderId);
}