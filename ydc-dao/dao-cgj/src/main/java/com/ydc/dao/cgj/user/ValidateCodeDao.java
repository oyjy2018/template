package com.ydc.dao.cgj.user;

import com.ydc.model.cgj.ValidateCode;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ValidateCodeDao {
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
    int insert(ValidateCode record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int insertSelective(ValidateCode record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    ValidateCode selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKeySelective(ValidateCode record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    int updateByPrimaryKey(ValidateCode record);

    /**
     * 获取短信发送的次数
     *
     * @param mobilePhone
     * @param date
     * @return
     */
    Integer selectSendCount(
            @Param("mobilePhone") String mobilePhone,
            @Param("date") Date date);

    /**
     * 根据手机号码和短信类型来获取验证码（最新一条）
     *
     * @param mobilePhone
     * @param validateType
     * @return
     */
    ValidateCode selectValidateCode(@Param("mobilePhone") String mobilePhone, @Param("validateType") Integer validateType);

    /**
     * 将短信更新为已使用
     * @param mobilePhone
     * @param validateType
     * @return
     */
    int updateValidateCodeUsed(@Param("mobilePhone") String mobilePhone, @Param("validateType") Integer validateType);
}