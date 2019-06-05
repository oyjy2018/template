package com.ydc.dao.cgj.user;

import com.ydc.model.cgj.MemberDeliveryAddress;

public interface MemberDeliveryAddressDao {
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
    int insert(MemberDeliveryAddress record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int insertSelective(MemberDeliveryAddress record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    MemberDeliveryAddress selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKeySelective(MemberDeliveryAddress record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKey(MemberDeliveryAddress record);

    /**
     * 根据会员id查询会员收货地址
     *
     * @param memberId
     * @return
     */
    MemberDeliveryAddress selectAddressByMemberId(Integer memberId);
}