package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcUser;
import org.springframework.stereotype.Service;

public interface YdhcUserDao {

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int insert(YdhcUser record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int insertSelective(YdhcUser record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    YdhcUser selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int updateByPrimaryKeySelective(YdhcUser record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int updateByPrimaryKey(YdhcUser record);

    /**
     * 根据手机号码获取用户
     * @param mobilePhone
     * @return
     */
    YdhcUser getUserByMobilePhone(String mobilePhone);
}