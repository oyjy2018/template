package com.ydc.dao.ydhc;

import com.ydc.model.ydhc.YdhcValidateCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;

public interface YdhcValidateCodeDao {
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
    int insert(YdhcValidateCode record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int insertSelective(YdhcValidateCode record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    YdhcValidateCode selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int updateByPrimaryKeySelective(YdhcValidateCode record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    int updateByPrimaryKey(YdhcValidateCode record);

    /**
     * 根据手机号码获取验证码
     * @param mobilePhone
     * @return
     */
    YdhcValidateCode selectValidateCode(String mobilePhone);

    /**
     * 获取已发短信条数
     * @param mobilePhone
     * @return
     */
    Integer selectSendCount(@Param("mobilePhone") String mobilePhone, @Param("date") Date date);
}