package com.ydc.dao.cgj.rental;

import com.ydc.model.cgj.rental.RentalDeposit;
import org.apache.ibatis.annotations.Param;
import sun.awt.SunHints;

import java.util.List;

public interface RentalDepositDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    int insert(RentalDeposit record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    int insertSelective(RentalDeposit record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    RentalDeposit selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    int updateByPrimaryKeySelective(RentalDeposit record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    int updateByPrimaryKey(RentalDeposit record);


    /**
     * 根据属性查询
     * @param record
     * @return
     */
    List<RentalDeposit> selectRentalDeposit(RentalDeposit record);



    /**
     * 租车结算，更新押金状态
     * @param rentalDeposit
     * @return
     */
    int updatePaymentStatus(RentalDeposit rentalDeposit);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     */
    RentalDeposit selectByOrderId(@Param(value = "orderId") Integer orderId,
                                  @Param(value = "depositType") Integer depositType,
                                  @Param(value = "orderType") Integer orderType);

    //根据订单id  订单类型  押金类型 更新状态
    int updateDeleteStatusByOrderIdAndOrederTypeAndDepositTypeAndPaymentStatus(int deleteStatus, Integer orderId, Integer orderType, Byte depositType, Byte paymentStatus);

}