package com.ydc.dao.cgj.sys;


import com.ydc.model.cgj.sys.CgjAppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CgjAppVersionDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2019-01-12 14:06:25
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-01-12 14:06:25
     */
    int insert(CgjAppVersion record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-01-12 14:06:25
     */
    int insertSelective(CgjAppVersion record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2019-01-12 14:06:25
     */
    CgjAppVersion selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-01-12 14:06:25
     */
    int updateByPrimaryKeySelective(CgjAppVersion record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-01-12 14:06:25
     */
    int updateByPrimaryKey(CgjAppVersion record);

    /**
     * 查APP最新版本
     * @author: hejiangping
     * @date: 2019/1/12
     */
    Map<String, Object> getNewestVersion(@Param("platform") String platform);
}